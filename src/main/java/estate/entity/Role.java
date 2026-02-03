package estate.entity;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
	 Long id;
	 String name;
	 String code;
	 LocalDateTime createdDate;
	 LocalDateTime modifiedDate;
	 String createdBy;
	 String modifiedBy;
			
}
