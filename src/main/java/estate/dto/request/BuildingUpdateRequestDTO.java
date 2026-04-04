package estate.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingUpdateRequestDTO {
	Long id;
	String name; // entity Building
	String ward ; // ____ 
	Long districtId;
	String street;
	String structure;
	Integer floorArea;
	Integer numberOfBasement;
	String direction;
	String level;
	List<Integer> rentAreas; // xem lai String hay RentArea
	Integer rentPrice;
	String rentPriceDescription;
	String serviceFee;
	String overTimeFee;
	String managerName; // entity User
	String managerPhoneNumber; // ____
	List<String> rentTypes;
	BigDecimal brokerageFee;
	String note;
}
