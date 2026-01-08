package Repository.Interface;
import java.util.*;
import Entity.Building;
import DTO.Request.*;
public interface BuildingRepo {
	public List<Building> search(BuildingDTORequest building); 

}
