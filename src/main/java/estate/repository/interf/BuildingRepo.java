package estate.repository.interf;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import estate.builder.BuildingSearchBuilder;
import estate.dto.request.BuildingDTORequest;
import estate.entity.Building;

public interface BuildingRepo extends JpaRepository<Building,Long> {
	public List<Building> search(BuildingSearchBuilder builder); 

}
