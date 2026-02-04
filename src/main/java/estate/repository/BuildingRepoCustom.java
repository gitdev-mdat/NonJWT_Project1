package estate.repository;

import java.util.List;
import estate.builder.BuildingSearchBuilder;
import estate.entity.Building;
public interface BuildingRepoCustom {
    List<Building> search(BuildingSearchBuilder builder);
}
