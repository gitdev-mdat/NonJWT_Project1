package estate.repository.implement;

import java.util.List;

import org.springframework.stereotype.Repository;

import estate.builder.BuildingSearchBuilder;
import estate.entity.Building;
import estate.repository.BuildingRepoCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BuildingRepoImpl implements BuildingRepoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Building> search(BuildingSearchBuilder builder) {

		StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		sql = JoinFunction(builder, sql);
		where = WhereFunction(builder, where);
		sql.append(where);


		Query query = entityManager.createNativeQuery(sql.toString(), Building.class);

		int index = 1;

		if (builder.getBuildingName() != null && !builder.getBuildingName().isBlank()) {
			query.setParameter(index++, "%" + builder.getBuildingName() + "%");
		}
		if (builder.getWard() != null && !builder.getWard().isBlank()) {
			query.setParameter(index++, "%" + builder.getWard() + "%");
		}
		if (builder.getDistrictId() != null) {
			query.setParameter(index++, builder.getDistrictId());
		}
		if (builder.getStreet() != null && !builder.getStreet().isBlank()) {
			query.setParameter(index++, "%" + builder.getStreet() + "%");
		}
		if (builder.getFloorArea() != null) {
			query.setParameter(index++, builder.getFloorArea());
		}
		if (builder.getNumberOfBasement() != null) {
			query.setParameter(index++, builder.getNumberOfBasement());
		}
		if (builder.getDirection() != null && !builder.getDirection().isBlank()) {
			query.setParameter(index++, "%" + builder.getDirection() + "%");
		}
		if (builder.getLevel() != null && !builder.getLevel().isBlank()) {
			query.setParameter(index++, "%" + builder.getLevel() + "%");
		}
		if (builder.getAreaF() != null) {
			query.setParameter(index++, builder.getAreaF());
		}
		if (builder.getAreaT() != null) {
			query.setParameter(index++, builder.getAreaT());
		}
		if (builder.getRentPriceF() != null) {
			query.setParameter(index++, builder.getRentPriceF());
		}
		if (builder.getRentPriceT() != null) {
			query.setParameter(index++, builder.getRentPriceT());
		}
		if (builder.getManagerName() != null && !builder.getManagerName().isBlank()) {
			query.setParameter(index++, "%" + builder.getManagerName() + "%");
		}
		if (builder.getManagerPhone() != null && !builder.getManagerPhone().isBlank()) {
			query.setParameter(index++, "%" + builder.getManagerPhone() + "%");
		}
		if (builder.getStaffId() != null) {
			query.setParameter(index++, builder.getStaffId());
		}

		if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
			for (String rentTypeCode : builder.getRentTypes()) {
				query.setParameter(index++, rentTypeCode);
			}
		}

		@SuppressWarnings("unchecked")
		List<Building> result = query.getResultList();

		return result;
	}

	private StringBuilder JoinFunction(BuildingSearchBuilder builder, StringBuilder join) {
		if (builder.getAreaF() != null || builder.getAreaT() != null) {
			join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id ");
		}
		if (builder.getStaffId() != null) {
			join.append(" INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id ");
		}

		if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
			join.append(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ");
			join.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid");
		}
		return join;
	}

	private StringBuilder WhereFunction(BuildingSearchBuilder builder, StringBuilder where) {
		if (builder.getBuildingName() != null && !builder.getBuildingName().isBlank()) {
			where.append(" AND b.name LIKE ? ");
		}
		if (builder.getWard() != null && !builder.getWard().isBlank()) {
			where.append(" AND b.ward LIKE ? ");
		}
		if (builder.getDistrictId() != null) {
			where.append(" AND b.districtid = ? ");
		}
		if (builder.getStreet() != null && !builder.getStreet().isBlank()) {
			where.append(" AND b.street LIKE ? ");
		}
		if (builder.getFloorArea() != null) {
			where.append(" AND b.floorarea = ? ");
		}
		if (builder.getNumberOfBasement() != null) {
			where.append(" AND b.numberofbasement = ? ");
		}
		if (builder.getDirection() != null && !builder.getDirection().isBlank()) {
			where.append(" AND b.direction LIKE ? ");
		}
		if (builder.getLevel() != null && !builder.getLevel().isBlank()) {
			where.append(" AND b.level LIKE ? ");
		}

		if (builder.getAreaF() != null) {
			where.append(" AND ra.value >= ? ");
		}
		if (builder.getAreaT() != null) {
			where.append(" AND ra.value <= ? ");
		}

		if (builder.getRentPriceF() != null) {
			where.append(" AND b.rentprice >= ? ");
		}
		if (builder.getRentPriceT() != null) {
			where.append(" AND b.rentprice <= ? ");
		}

		if (builder.getManagerName() != null && !builder.getManagerName().isBlank()) {
			where.append(" AND b.managername LIKE ? ");
		}
		if (builder.getManagerPhone() != null && !builder.getManagerPhone().isBlank()) {
			where.append(" AND b.managerphonenumber LIKE ? ");
		}
		if (builder.getStaffId() != null) {
			where.append(" AND ab.staffid = ? ");
		}

		if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
			where.append(" AND rt.code IN (");
			for (int i = 0; i < builder.getRentTypes().size(); i++) {
				where.append("?");
				if (i < builder.getRentTypes().size() - 1) {
					where.append(",");
				}
			}
			where.append(") ");
		}
		return where;
	}

}
