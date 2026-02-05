package estate.controller;

import estate.dto.response.BuildingDTOResponse;
import estate.service.BuildingService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
	@Autowired
	private BuildingService buildingService;
	@GetMapping("/api/search")
	public List<BuildingDTOResponse> searchBuilding(@RequestParam Map<String,String> params, @RequestParam(value="typeCode",required = false) List<String>typeCodes) {
		List<BuildingDTOResponse> building = buildingService.search(params,typeCodes);
		return building;
	}
} 
