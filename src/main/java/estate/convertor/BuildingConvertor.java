package estate.convertor;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import estate.builder.BuildingSearchBuilder;
import estate.dto.request.BuildingDTORequest;
import estate.dto.response.BuildingDTOResponse;
import estate.entity.Building;
import estate.entity.District;
import estate.entity.RentArea;
import estate.repository.interf.BuildingRepo;
import estate.repository.interf.DistrictRepo;
import estate.repository.interf.RentAreaRepo;
import estate.util.Validation;

@Component
public class BuildingConvertor {
	@Autowired
	private DistrictRepo districtRepo;
	@Autowired
	private RentAreaRepo rentAreaRepo;
	@Autowired
	private ModelMapper modelMapper;
	public BuildingDTOResponse convertToResponseDTO(Building item) {
		BuildingDTOResponse dto = modelMapper.map(item,BuildingDTOResponse.class);
		District district = districtRepo.findDistrictById(item.getDistrictId());
		StringBuilder address = new StringBuilder();
		if (item.getStreet() != null) {
			address.append(item.getStreet());
		}
		if (item.getWard() != null) {
			if (address.length() > 0) address.append(" ");
			address.append(item.getWard());
		}
		if (district != null && district.getName() != null) {
			if (address.length() > 0) address.append(", ");
			address.append(district.getName());
		}
		dto.setAddress(address.toString());
		
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
		return dto;
	}
	
	public BuildingSearchBuilder convertToBuilder(BuildingDTORequest dto) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
																.buildingName(dto.getBuildingName())
																.areaF(dto.getAreaF())
																.areaT(dto.getAreaT())
																.direction(dto.getDirection())
																.districtId(dto.getDistrictId())
																.floorArea(dto.getFloorArea())
																.level(dto.getLevel())
																.managerName(dto.getManagerName())
																.managerPhone(dto.getManagerPhone())
																.rentPriceF(dto.getRentPriceF())
																.rentPriceT(dto.getRentPriceT())
																.rentTypes(dto.getRentTypes())
																.numberOfBasement(dto.getNumberOfBasement())
																.staffId(dto.getStaffId())
																.street(dto.getStreet())
																.ward(dto.getWard())
																.build();
		return builder;
	}
}
