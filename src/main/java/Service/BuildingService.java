package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Request.BuildingDTORequest;
import DTO.Response.BuildingDTOResponse;
import DTO.Request.*;
import Entity.Building;
import Entity.District;
import Repository.Implement.*;
import Repository.Interface.*;

public class BuildingService {	
	private final BuildingRepo buildingRepo = new BuildingImpl();
	private final DistrictRepo districtRepo = new DistrictImpl();
	private final RentAreaRepo rentAreaRepo = new RentAreaImpl();
	public List<BuildingDTOResponse> search(BuildingDTORequest building) {
		List<Building> b = buildingRepo.search(building);
		List<BuildingDTOResponse> result = new ArrayList<>();
		for (Building item : b) {
			BuildingDTOResponse dto = new BuildingDTOResponse();
			
			dto.setBuildingName(item.getName());
			
			District district = districtRepo.findDistrictById(item.getDistrictId());
			StringBuilder address = new StringBuilder();
			if (item.getStreet() != null) {
				address.append(item.getStreet());
			}
			if (item.getWard() != null) {
				if (address.length() > 0) address.append(", ");
				address.append(item.getWard());
			}
			if (district != null && district.getName() != null) {
				if (address.length() > 0) address.append(", ");
				address.append(district.getName());
			}
			dto.setAddress(address.toString());
			
			dto.setNumberOfBasement(item.getNumberOfBasement());
			dto.setManagerName(item.getManagerName());
			dto.setManagerPhone(item.getManagerPhoneNumber());
			dto.setFloorArea(item.getFloorArea());
			dto.setRentPrice(item.getRentPrice());
			dto.setServiceFee(item.getServiceFee());
			dto.setBrokerageFee(item.getBrokerageFee());
			
			List<Integer> rentAreas = rentAreaRepo.getRentAreasByBuildingId(item.getId());
			dto.setRentArea(rentAreas);
			
			if (item.getFloorArea() != null && rentAreas != null && !rentAreas.isEmpty()) {
				int totalRentArea = rentAreas.stream().mapToInt(Integer::intValue).sum();
				dto.setEmptyArea(item.getFloorArea() - totalRentArea);
			}
			
			result.add(dto);
		}
		return result;
	}
}
