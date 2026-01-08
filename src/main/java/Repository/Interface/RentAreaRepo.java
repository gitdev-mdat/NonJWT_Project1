package Repository.Interface;

import java.util.List;

public interface RentAreaRepo {
	List<Integer> getRentAreasByBuildingId(Long buildingId);
}
