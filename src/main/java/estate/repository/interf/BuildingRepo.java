package estate.repository.interf;

import org.springframework.data.jpa.repository.JpaRepository;

import estate.entity.Building;
import estate.repository.BuildingRepoCustom;

public interface BuildingRepo extends JpaRepository<Building, Long>, BuildingRepoCustom {
}
