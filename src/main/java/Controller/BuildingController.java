package Controller;

@RestController
public class BuildingController {
	private BuildingService buildingService;
	@PostMapping("/api/search")
	public List<BuildingDTOResponse> searchBuilding(@RequestParam BuildingDTORequest) {
		BuildingDTOResponse building = buildingService.find 
		return 
	}
} 
