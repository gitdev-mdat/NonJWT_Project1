package estate.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingRentType {
	 Long id;
	 Long buildingId;
	 Long rentTypeId;	
}
