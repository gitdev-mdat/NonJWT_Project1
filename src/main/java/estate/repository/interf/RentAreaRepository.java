package estate.repository.interf;

import java.util.List;
import estate.entity.RentArea;
public interface RentAreaRepository {
	List<RentArea> getRentAreasByBuildingId(Long buildingId);
}
