package estate.repository.implement;

import org.springframework.stereotype.Repository;

import estate.entity.District;
import estate.repository.interf.DistrictRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DistrictRepoImpl implements DistrictRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public District findDistrictById(Long districtId) {
		return entityManager.find(District.class, districtId);
	}

}
