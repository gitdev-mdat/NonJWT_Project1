package estate.repository.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import estate.builder.BuildingSearchBuilder;
import estate.entity.Building;
import estate.repository.interf.BuildingRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BuildingRepoImpl implements BuildingRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Building> search(BuildingSearchBuilder builder) {

	    StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
	    StringBuilder where = new StringBuilder(" WHERE 1=1 ");
	    sql = JoinFunction(builder,sql);
	    where = WhereFunction(builder,where);
	    sql.append(where);

	    List<Object[]> rows;

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
	private StringBuilder JoinFunction(BuildingSearchBuilder builder,StringBuilder join) {
		  if (builder.getAreaF() != null || builder.getAreaT() != null) {
		        join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id ");
		    }
		    if (builder.getStaffId() != null) {
		        join.append(" INNER JOIN assignmentbuilder ab ON ab.buildingid = b.id ");
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
	        where.append(" AND b.managerphone LIKE ? ");
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
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <S extends Building> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteAllInBatch(Iterable<Building> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Building getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Building getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Building getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Building> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Building> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Building> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Building entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Building> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Building> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Building> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public <S extends Building> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Building> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends Building> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends Building, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}
}
