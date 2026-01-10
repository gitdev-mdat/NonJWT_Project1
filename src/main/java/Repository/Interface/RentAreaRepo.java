package Repository.Interface;

import java.util.List;
import Entity.*;
public interface RentAreaRepo {
	List<RentArea> getRentAreasByBuildingId(Long buildingId);
}
