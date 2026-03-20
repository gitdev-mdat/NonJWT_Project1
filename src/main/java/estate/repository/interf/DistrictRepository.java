package estate.repository.interf;


import estate.entity.District;

public interface DistrictRepository {
	public District findDistrictById(Long districtId);
}
