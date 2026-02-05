package estate.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTORequest {
	String buildingName; // entity Building
	String ward ; // ____ 
	Long districtId;
	String street;
	Integer floorArea;
	Integer numberOfBasement;
	String direction;
	String level;
	Integer areaF; // entity rentarea
	Integer areaT; // ______
	Integer rentPriceF; // entity Building
	Integer rentPriceT; // ___
	String managerName; // entity User
	String managerPhone; // ____
	Long staffId; // entity assignmentbuilding
	List<String> rentTypes;
}
 