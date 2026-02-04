package estate.repository.interf;
import java.util.*;

import estate.builder.BuildingSearchBuilder;
import estate.dto.request.BuildingDTORequest;
import estate.entity.Building;

public interface BuildingRepo {
	public List<Building> search(BuildingSearchBuilder builder); 

}
