package estate.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	
	@ManyToOne
	@JoinColumn(name="roleid")
	Role role;
	
	@ManyToOne
	@JoinColumn(name="userid")
	User user;
	
	@Column(name="createddate")
	 LocalDateTime createdDate;
	
	@Column(name="modifieddate")
	 LocalDateTime modifiedDate;
	
	@Column(name="createdby")
	 String createdBy;
	
	@Column(name="modifiedby")
	 String modifiedBy;	
}
