package estate.convertor;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import estate.builder.BuildingSearchBuilder;
import estate.dto.request.BuildingCreateRequestDTO;
import estate.dto.request.BuildingRequestDTO;
import estate.dto.request.BuildingUpdateRequestDTO;
import estate.dto.response.BuildingResponseDTO;
import estate.entity.Building;
import estate.entity.District;
import estate.entity.RentArea;
import estate.exception.IllegalArguementException;
import estate.repository.interf.DistrictRepo;
import estate.repository.interf.RentAreaRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

@Component
public class BuildingConvertor {
	@Autowired
	private DistrictRepo districtRepo;
	@Autowired
	private RentAreaRepo rentAreaRepo;
	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public BuildingResponseDTO convertToResponseDTO(Building item) {
		BuildingResponseDTO dto = modelMapper.map(item, BuildingResponseDTO.class);
		District district = item.getDistrict();
		StringBuilder address = new StringBuilder();
		if (item.getStreet() != null) {
			address.append(item.getStreet());
		}
		if (item.getWard() != null) {
			if (!address.isEmpty())
				address.append(" ");
			address.append(item.getWard());
		}
		if (district != null && district.getName() != null) {
			if (!address.isEmpty())
				address.append(", ");
			address.append(district.getName());
		}
		dto.setAddress(address.toString());

		List<RentArea> rentAreas = item.getRentAreas();
		ArrayList<Integer> rent = new ArrayList<>();
		for (RentArea r : rentAreas) {
			rent.add(r.getValue());
		}

		dto.setRentArea(rent.toString());

		if (item.getFloorArea() != null && rentAreas != null && !rentAreas.isEmpty()) {

			int totalRentArea = rentAreas.stream().mapToInt(RentArea::getValue).sum();

			int emptyArea = item.getFloorArea() - totalRentArea;
			dto.setEmptyArea(Math.max(emptyArea, 0));
		}
		return dto;
	}

	public BuildingSearchBuilder convertToBuilder(BuildingRequestDTO dto) {
		return new BuildingSearchBuilder.Builder().buildingName(dto.getBuildingName()).areaF(dto.getAreaF())
				.areaT(dto.getAreaT()).direction(dto.getDirection()).districtId(dto.getDistrictId())
				.floorArea(dto.getFloorArea()).level(dto.getLevel()).managerName(dto.getManagerName())
				.managerPhone(dto.getManagerPhone()).rentPriceF(dto.getRentPriceF()).rentPriceT(dto.getRentPriceT())
				.rentTypes(dto.getRentTypes()).numberOfBasement(dto.getNumberOfBasement()).staffId(dto.getStaffId())
				.street(dto.getStreet()).ward(dto.getWard()).build();
	}

	public Building convertToEntity(BuildingCreateRequestDTO dto) {
		Building building = modelMapper.map(dto, Building.class);
		District district = entityManager.find(District.class, dto.getDistrictId());
		if (district != null) {
			building.setDistrict(district);
		} else {
			throw new IllegalArguementException("Invalid District Id: " + dto.getDistrictId());
		}
		entityManager.persist(building);
		entityManager.flush();
		List<RentArea> rentAreaList = new ArrayList<>();

		dto.getRentAreas().forEach(areaValue -> {
			RentArea ra = new RentArea();
			ra.setValue(areaValue);
			ra.setBuilding(building);
			entityManager.persist(ra);
			rentAreaList.add(ra);
		});

		building.setRentAreas(rentAreaList);
		return building;
	}

	public Building updateToEntity(BuildingUpdateRequestDTO dto) {
		Building building = entityManager.find(Building.class, dto.getId());
		District district = entityManager.find(District.class, dto.getDistrictId());
		if (district != null) {
			building.setDistrict(district);
		} else {
			throw new IllegalArguementException("Invalid District Id: " + dto.getDistrictId());
		}
		entityManager.createQuery("DELETE FROM RentArea ra WHERE ra.building.id = " + building.getId()).executeUpdate();
		List<RentArea> rentAreaList = new ArrayList<>();
		dto.getRentAreas().forEach(areaValue -> {
			RentArea ra = new RentArea();
			ra.setValue(areaValue);
			ra.setBuilding(building);
			entityManager.persist(ra);
			rentAreaList.add(ra);
		});

		building.setRentAreas(rentAreaList);
		return building;
	}
}
