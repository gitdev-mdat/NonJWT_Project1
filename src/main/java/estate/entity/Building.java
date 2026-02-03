package estate.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Building {
	 Long id;
	 String name;
	 String street;
	 String ward;
	 Long districtId;
	 String structure;
	 Integer numberOfBasement;
	 Integer floorArea;
	 String direction;
	 String level;
	 Integer rentPrice;
	 String rentPriceDescription;
	 String serviceFee;
	 String carFee;
	 String motorbikeFee;
	 String overTimeFee;
	 String waterFee;
	 String electricityFee;
	 String deposit;
	 String payment;
	 String rentTime;
	 String decorationTime;
	 BigDecimal brokerageFee;
	 String note;
	 String linkOfBuilding;
	 String map;
	 String image;
	 LocalDateTime createdDate;
	 LocalDateTime modifiedDate;
	 String createdBy;
	 String modifiedBy;
	 String managerName;
	 String managerPhoneNumber;
	 
}
