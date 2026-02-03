package estate.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentBuilding {
	Long id;
	Long staffId;
	Long buildingId;
	LocalDateTime createdDate;
	LocalDateTime modifiedDate;
	String createdBy;
	String modifiedBy;
}
