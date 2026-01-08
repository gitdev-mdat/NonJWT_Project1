package Controller;

import Service.BuildingService;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.Request.*;
import DTO.Response.*;

@RestController
public class BuildingController {
	private BuildingService buildingService = new BuildingService();
	@PostMapping("/api/search")
	public List<BuildingDTOResponse> searchBuilding(BuildingDTORequest dto) {
		List<BuildingDTOResponse> building = buildingService.search(dto);
		return building;
	}
} 
