package Repository.Interface;

import Entity.District;

import Entity.*;

public interface DistrictRepo {
	public District findDistrictById(Long districtId);
}
