package estate.controller;

import estate.dto.request.BuildingCreateRequestDTO;
import estate.dto.request.BuildingRequestDTO;
import estate.dto.request.BuildingUpdateRequestDTO;
import estate.dto.response.BuildingResponseDTO;
import estate.entity.Building;
import estate.exception.InvalidBuildingDTOException;
import estate.service.BuildingService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
	@Autowired
	private BuildingService buildingService;
	@GetMapping("/api/search")
	public List<BuildingResponseDTO> searchBuilding(@RequestParam Map<String,String> params, @RequestParam(value="typeCode",required = false) List<String>typeCodes) {
		List<BuildingResponseDTO> building = buildingService.search(params,typeCodes);
		return building;
	}
	public void validateBuildingDTO(BuildingCreateRequestDTO building) {
		if (building.getName() == null || building.getName().isBlank()) {
			throw new InvalidBuildingDTOException("Building name and number of basement are required fields.");
		} else {
			System.out.println("Building DTO is valid");
		}
	}
	@PostMapping("/api/create")
	public Building createBuilding (@RequestBody BuildingCreateRequestDTO building) {
		validateBuildingDTO(building);
		return buildingService.create(building);
	}
	
	@PutMapping("/api/update")
	public Building updateBuilding (@RequestBody BuildingUpdateRequestDTO building) {
		if (building.getName() == null || building.getName().isBlank() ) {
			throw new InvalidBuildingDTOException("Building name and number of basement are required fields.");
		} else {
			System.out.println("Building DTO is valid");
		}
		if (building.getId() == null) {
			throw new InvalidBuildingDTOException("Building ID is required for update.");
		}
		return buildingService.update(building);
	}
	@DeleteMapping("api/buildings/{ids}")
	public String deleteBuilding (@PathVariable List<Long> ids) {
		buildingService.delete(ids);
		return "Deleted successfully";
	}
} 
