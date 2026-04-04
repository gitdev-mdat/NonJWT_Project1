 package estate.service;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import estate.builder.BuildingSearchBuilder;
import estate.convertor.BuildingConvertor;
import estate.dto.request.BuildingCreateRequestDTO;
import estate.dto.request.BuildingRequestDTO;
import estate.dto.request.BuildingUpdateRequestDTO;
import estate.dto.response.BuildingResponseDTO;
import estate.entity.Building;
import estate.entity.District;
import estate.entity.RentArea;
import estate.exception.InvalidBuildingDTOException;
import estate.repository.implement.BuildingRepoImpl;
import estate.repository.implement.DistrictRepoImpl;
import estate.repository.implement.RentAreaRepoImpl;
import estate.repository.interf.BuildingRepo;
import estate.repository.interf.DistrictRepo;
import estate.repository.interf.RentAreaRepo;
import estate.util.Validation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BuildingService implements IBuildingService {	
	@Autowired
	private BuildingRepo buildingRepo;
	@Autowired
	private BuildingConvertor buildingConvertor;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<BuildingResponseDTO> search(Map<String,String> params, List<String> typeCodes) {
		// handle params
		BuildingRequestDTO building = new BuildingRequestDTO();	
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
		List<BuildingResponseDTO> result = new ArrayList<>();
		for (Building item : b) {
		
			BuildingResponseDTO dto= buildingConvertor.convertToResponseDTO(item);
			result.add(dto);
		}
		return result;
	}
	@Override
	@Transactional
	public Building create(BuildingCreateRequestDTO building) {
		if (!Validation.isValid(building.getName())) {
			throw new InvalidBuildingDTOException("thiếu tên toà nhà");
		}
		if (building.getRentPrice() == null || building.getRentPrice() <= 0) {
			throw new InvalidBuildingDTOException("giá thuê không hợp lệ");
		}
		Building b =  buildingConvertor.convertToEntity(building);
		return b;
	}
	@Override
	@Transactional
	public Building update(BuildingUpdateRequestDTO building) {
		Building b = buildingConvertor.updateToEntity(building);
		entityManager.merge(b); // update entity
		return b;
	}
	@Override
	@Transactional
	public void delete(List<Long> ids) {
		for (Long i : ids) {
			Building building = entityManager.find(Building.class, i);
			if (building != null) {
				entityManager.createQuery("DELETE FROM RentArea ra WHERE ra.building.id = " + i).executeUpdate();
				entityManager.remove(building);
			}
		}
	}
}
