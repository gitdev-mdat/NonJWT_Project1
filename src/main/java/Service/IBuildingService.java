package Service;

import java.util.List;

import DTO.Request.BuildingDTORequest;
import DTO.Response.BuildingDTOResponse;
import java.util.Map;
public interface IBuildingService {
	public List<BuildingDTOResponse> search(Map<String,String> params);
}
