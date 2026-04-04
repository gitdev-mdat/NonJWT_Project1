package estate.entity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name="rentarea")
public class RentArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	
	@ManyToOne
	@JoinColumn(name="buildingid")
	@JsonBackReference
	Building building;
	
	@Column
	Integer value;
	
	@Column(name="createdby")
	String createdBy;
	
	@Column(name="createddate")
	LocalDateTime createdDate;
	
	@Column(name="modifiedby")
	String modifiedBy;
	
	@Column(name="modifieddate")
	LocalDateTime modifiedDate;
}
	