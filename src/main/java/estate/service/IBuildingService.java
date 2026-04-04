package estate.service;

import java.util.List;

import estate.dto.request.BuildingCreateRequestDTO;
import estate.dto.request.BuildingUpdateRequestDTO;
import estate.dto.response.BuildingResponseDTO;
import estate.entity.Building;

import java.util.Map;
public interface IBuildingService {
	public List<BuildingResponseDTO> search(Map<String,String> params,List<String> typeCodes);
	public Building create(BuildingCreateRequestDTO building);
	public Building update(BuildingUpdateRequestDTO building);
	public void delete(List<Long> ids);
}
