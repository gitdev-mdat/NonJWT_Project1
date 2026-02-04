package estate.service;

import java.util.List;


import estate.dto.response.BuildingDTOResponse;

import java.util.Map;
public interface IBuildingService {
	public List<BuildingDTOResponse> search(Map<String,String> params,List<String> typeCodes);
}
