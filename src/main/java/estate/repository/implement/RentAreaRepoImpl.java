package estate.repository.implement;

import java.util.List;

import org.springframework.stereotype.Repository;

import estate.entity.RentArea;
import estate.repository.interf.RentAreaRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class RentAreaRepoImpl implements RentAreaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RentArea> getRentAreasByBuildingId(Long buildingId) {
		String jpql = "SELECT ra FROM RentArea ra WHERE ra.building.id = :buildingId";
		TypedQuery<RentArea> query = entityManager.createQuery(jpql, RentArea.class);
		query.setParameter("buildingId", buildingId);
		return query.getResultList();
	}

}

