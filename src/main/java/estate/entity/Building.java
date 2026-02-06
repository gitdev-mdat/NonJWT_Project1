package estate.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="building")
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	
	@Column
	 String name;
	
	@Column
	 String street;
	
	@Column
	 String ward;
	
	@ManyToOne
	@JoinColumn(name="districtid")
	District district;
	
	@Column
	 String structure;
	
	@Column(name="numberofbasement")
	 Integer numberOfBasement;
	
	@Column(name="floorarea")
	Integer floorArea;
	 
	@Column
	String direction;
	 
	@Column
	String level;
	
	@Column(name="rentprice")
	Integer rentPrice;
	
	@Column(name="rentpricedescription")
	String rentPriceDescription;
	
	@Column(name="servicefee")
	String serviceFee;
	 
	@Column(name="carfee")
	String carFee;
	
	@Column(name="motorbikefee")
	String motorbikeFee;
	 
	@Column(name="overtimefee")
	String overTimeFee;
	 
	@Column(name="waterfee")
	String waterFee;
	 
	@Column(name="electricityfee")
	String electricityFee;
	 
	@Column
	String deposit;
	 
	@Column
	String payment;
	 
	@Column(name="renttime")
	String rentTime;
	 
	@Column(name="decorationtime")
	String decorationTime;
	 
	@Column(name="brokeragefee")
	BigDecimal brokerageFee;
	 
	@Column
	String note;
	 
	@Column(name="linkofbuilding")
	String linkOfBuilding;
	 
	@Column
	String map;
	 
	@Column
	String image;
	 
	@Column(name="createddate")
	LocalDateTime createdDate;
	 
	@Column(name="modifieddate")
	LocalDateTime modifiedDate;
	 
	@Column(name="createdby")
	String createdBy;
	 
	@Column(name="modifiedby")
	String modifiedBy;
	 
	@Column(name="managername")
	String managerName;
	
	@Column(name="managerphonenumber")
	String managerPhoneNumber;
	
	@OneToMany(mappedBy="building")
	List<RentArea> rentAreas;
}
