package estate.repository.interf;
import java.util.*;


import estate.dto.request.BuildingDTORequest;
import estate.entity.Building;

public interface BuildingRepository {
	public List<Building> search(BuildingDTORequest building); 

}
