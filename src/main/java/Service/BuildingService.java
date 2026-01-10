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
import Entity.RentArea;
import Repository.Implement.*;
import Repository.Interface.*;
import java.util.Map;
public class BuildingService implements IBuildingService {	
	private final BuildingRepo buildingRepo = new BuildingImpl();
	private final DistrictRepo districtRepo = new DistrictImpl();
	private final RentAreaRepo rentAreaRepo = new RentAreaImpl();
	public List<BuildingDTOResponse> search(Map<String,String> params) {
		// handle params
		BuildingDTORequest building = new BuildingDTORequest();

	    building.setBuildingName(params.get("buildingName"));
	    building.setWard(params.get("ward"));
	    building.setStreet(params.get("street"));

	    if (params.get("districtId") != null) {
	        building.setDistrictId(Long.parseLong(params.get("districtId")));
	    }

	    if (params.get("areaF") != null) {
	        building.setAreaF(Integer.parseInt(params.get("areaF")));
	    }

	    if (params.get("areaT") != null) {
	        building.setAreaT(Integer.parseInt(params.get("areaT")));
	    }

	    if (params.get("rentPriceF") != null) {
	        building.setRentPriceF(Integer.parseInt(params.get("rentPriceF")));
	    }

	    if (params.get("rentPriceT") != null) {
	        building.setRentPriceT(Integer.parseInt(params.get("rentPriceT")));
	    }

	    if (params.get("staffId") != null) {
	        building.setStaffId(Long.parseLong(params.get("staffId")));
	    }
		//-----
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
			
			List<RentArea> rentAreas = rentAreaRepo.getRentAreasByBuildingId(item.getId());
			ArrayList<Integer> rent = new ArrayList<>();
			for (RentArea r : rentAreas) {
				rent.add(r.getValue());
			}
			dto.setRentArea(rent.toString());
			
			if (item.getFloorArea() != null && rentAreas != null && !rentAreas.isEmpty()) {

			    int totalRentArea = rentAreas.stream()
			                                 .mapToInt(RentArea::getValue)
			                                 .sum();

			    int emptyArea = item.getFloorArea() - totalRentArea;
			    dto.setEmptyArea(Math.max(emptyArea, 0));
			}
			
			result.add(dto);
		}
		return result;
	}
}
