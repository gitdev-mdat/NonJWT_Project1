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
@Entity
@Table(name="assignmentbuilding")
public class AssignmentBuilding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name="staffid")
	User user;
	
	@ManyToOne
	@JoinColumn(name="buildingid")
	Building building;
	
	@Column(name="createddate")
	LocalDateTime createdDate;
	
	@Column(name="modifieddate")
	LocalDateTime modifiedDate;
	
	@Column(name="createdby")
	String createdBy;
	
	@Column(name="modifiedby")
	String modifiedBy;
}
