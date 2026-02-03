package estate.entity;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
	 Long id;
	 String note;
	 Long customerId;
	 LocalDateTime createdDate;
	 LocalDateTime modifiedDate;
	 String createdBy;
	 String modifiedBy;
}
