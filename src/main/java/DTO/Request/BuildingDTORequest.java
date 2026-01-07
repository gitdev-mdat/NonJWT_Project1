package DTO.Request;

import java.util.List;

public class BuildingDTORequest {
	String buildingName; // entity Building
	String ward ; // ____ 
	Long districtId;
	String street;
	Integer floorArea;
	Integer numberOfBasement;
	String direction;
	String level;
	Integer areaF; // chưa biết lấy ở đâu
	Integer areaT; // ______
	Integer rentPriceF; // entity Building
	Integer rentPriceT; // ___
	String managerName; // entity User
	String managerPhone; // ____
	Long staffId; // entity User
	List<String> rentType; // entity RentType
	public BuildingDTORequest(String buildingName, String ward, Long districtId, String street, Integer floorArea,
			Integer numberOfBasement, String direction, String level, Integer areaF, Integer areaT, Integer rentPriceF,
			Integer rentPriceT, String managerName, String managerPhone, Long staffId, List<String> rentType) {
		super();
		this.buildingName = buildingName;
		this.ward = ward;
		this.districtId = districtId;
		this.street = street;
		this.floorArea = floorArea;
		this.numberOfBasement = numberOfBasement;
		this.direction = direction;
		this.level = level;
		this.areaF = areaF; 
		this.areaT = areaT;  
		this.rentPriceF = rentPriceF;
		this.rentPriceT = rentPriceT;
		this.managerName = managerName;
		this.managerPhone = managerPhone;
		this.staffId = staffId;
		this.rentType = rentType;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getAreaF() {
		return areaF;
	}
	public void setAreaF(Integer areaF) {
		this.areaF = areaF;
	}
	public Integer getAreaT() {
		return areaT;
	}
	public void setAreaT(Integer areaT) {
		this.areaT = areaT;
	}
	public Integer getRentPriceF() {
		return rentPriceF;
	}
	public void setRentPriceF(Integer rentPriceF) {
		this.rentPriceF = rentPriceF;
	}
	public Integer getRentPriceT() {
		return rentPriceT;
	}
	public void setRentPriceT(Integer rentPriceT) {
		this.rentPriceT = rentPriceT;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public List<String> getRentType() {
		return rentType;
	}
	public void setRentType(List<String> rentType) {
		this.rentType = rentType;
	}
	
	
}
 