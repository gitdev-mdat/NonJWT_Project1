package estate.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	 String name;
	 String code;
	 
	 @Column(name="createddate")
	 LocalDateTime createdDate;
	 
	 @Column(name="modifieddate")
	 LocalDateTime modifiedDate;
	 
	 @Column(name="createdby")
	 String createdBy;
	 
	 @Column(name="modifiedby")
	 String modifiedBy;
			
}
