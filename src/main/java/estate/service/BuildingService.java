package estate.service;

import java.util.ArrayList;
import java.util.List;

import estate.builder.BuildingSearchBuilder;
import estate.convertor.BuildingConvertor;
import estate.dto.request.BuildingDTORequest;
import estate.dto.response.BuildingDTOResponse;
import estate.entity.Building;

import estate.repository.interf.BuildingRepo;

import estate.util.Validation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements IBuildingService {
	@Autowired
	private BuildingRepo buildingRepo;
	@Autowired
	private BuildingConvertor buildingConvertor;

	public List<BuildingDTOResponse> search(Map<String, String> params, List<String> typeCodes) {
		// handle params
		BuildingDTORequest building = new BuildingDTORequest();

		if (Validation.isValid(params.get("buildingName"))) {
			building.setBuildingName(params.get("buildingName"));
		}
		if (Validation.isValid(params.get("ward"))) {
			building.setWard(params.get("ward"));
		}
		if (Validation.isValid(params.get("street"))) {
			building.setStreet(params.get("street"));
		}	
		if (Validation.isValid(params.get("districtId"))) {
			building.setDistrictId(Long.parseLong(params.get("districtId")));
		}

		if (Validation.isValid(params.get("areaF"))) {
			building.setAreaF(Integer.parseInt(params.get("areaF")));
		}

		if (Validation.isValid(params.get("areaT"))) {
			building.setAreaT(Integer.parseInt(params.get("areaT")));
		}

		if (Validation.isValid(params.get("rentPriceF"))) {
			building.setRentPriceF(Integer.parseInt(params.get("rentPriceF")));
			System.out.println("RentPriceF: " + building.getRentPriceF());
		}

		if (Validation.isValid(params.get("rentPriceT"))) {
			building.setRentPriceT(Integer.parseInt(params.get("rentPriceT")));
		}

		if (Validation.isValid(params.get("staffId"))) {
			building.setStaffId(Long.parseLong(params.get("staffId")));
		}
		if (Validation.isValid(params.get("managerName"))) {
			building.setManagerName(params.get("managerName"));
		}
		if (Validation.isValid(params.get("managerPhone"))) {
			building.setManagerPhone(params.get("managerPhone"));
		}
		if (Validation.isValid(params.get("staffId"))) {
			building.setStaffId(Long.parseLong(params.get("staffId")));
		}
		if (Validation.isValid(params.get("level"))) {
			building.setLevel(params.get("level"));
		}
		if (Validation.isValid(params.get("floorArea"))) {
			building.setFloorArea(Integer.parseInt(params.get("floorArea")));
		}
		if(Validation.isValid(params.get("numberOfBasement"))) {
			building.setNumberOfBasement(Integer.parseInt(params.get("numberOfBasement")));
		}
		if (typeCodes != null) {
			building.setRentTypes(typeCodes);
		}

		BuildingSearchBuilder builder = buildingConvertor.convertToBuilder(building);
		List<Building> b = buildingRepo.search(builder);
		List<BuildingDTOResponse> result = new ArrayList<>();
		for (Building item : b) {

			BuildingDTOResponse dto = buildingConvertor.convertToResponseDTO(item);
			result.add(dto);
		}
		return result;
	}
}
