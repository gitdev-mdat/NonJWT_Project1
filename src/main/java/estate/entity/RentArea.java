package estate.entity;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentArea {
	 Long id;
	 Long buildingId;
	 Integer value;
	 String createdBy;
	 LocalDateTime createdDate;
	 String modifiedBy;
	 LocalDateTime modifiedDate;
	
}
