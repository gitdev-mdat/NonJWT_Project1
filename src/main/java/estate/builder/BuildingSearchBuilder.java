package estate.builder;

import java.util.List;

public class BuildingSearchBuilder {

	private String buildingName;
	private String ward;
	private Long districtId;
	private String street;
	private Integer floorArea;
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private Integer areaF;
	private Integer areaT;
	private Integer rentPriceF;
	private Integer rentPriceT;
	private String managerName;
	private String managerPhone;
	private Long staffId;
	private List<String> rentTypes;

	private BuildingSearchBuilder(Builder builder) {
		this.buildingName = builder.buildingName;
		this.ward = builder.ward;
		this.districtId = builder.districtId;
		this.street = builder.street;
		this.floorArea = builder.floorArea;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.areaF = builder.areaF;
		this.areaT = builder.areaT;
		this.rentPriceF = builder.rentPriceF;
		this.rentPriceT = builder.rentPriceT;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.staffId = builder.staffId;
		this.rentTypes = builder.rentTypes;
	}

	// ===== GETTERS ONLY =====
	public String getBuildingName() { return buildingName; }
	public String getWard() { return ward; }
	public Long getDistrictId() { return districtId; }
	public String getStreet() { return street; }
	public Integer getFloorArea() { return floorArea; }
	public Integer getNumberOfBasement() { return numberOfBasement; }
	public String getDirection() { return direction; }
	public String getLevel() { return level; }
	public Integer getAreaF() { return areaF; }
	public Integer getAreaT() { return areaT; }
	public Integer getRentPriceF() { return rentPriceF; }
	public Integer getRentPriceT() { return rentPriceT; }
	public String getManagerName() { return managerName; }
	public String getManagerPhone() { return managerPhone; }
	public Long getStaffId() { return staffId; }
	public List<String> getRentTypes() { return rentTypes; }

	// ===== BUILDER =====
	public static class Builder {
		private String buildingName;
		private String ward;
		private Long districtId;
		private String street;
		private Integer floorArea;
		private Integer numberOfBasement;
		private String direction;
		private String level;
		private Integer areaF;
		private Integer areaT;
		private Integer rentPriceF;
		private Integer rentPriceT;
		private String managerName;
		private String managerPhone;
		private Long staffId;
		private List<String> rentTypes;

		public Builder buildingName(String buildingName) {
			this.buildingName = buildingName;
			return this;
		}

		public Builder ward(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder districtId(Long districtId) {
			this.districtId = districtId;
			return this;
		}

		public Builder street(String street) {
			this.street = street;
			return this;
		}

		public Builder floorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}

		public Builder numberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder direction(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder level(String level) {
			this.level = level;
			return this;
		}

		public Builder areaF(Integer areaF) {
			this.areaF = areaF;
			return this;
		}

		public Builder areaT(Integer areaT) {
			this.areaT = areaT;
			return this;
		}

		public Builder rentPriceF(Integer rentPriceF) {
			this.rentPriceF = rentPriceF;
			return this;
		}

		public Builder rentPriceT(Integer rentPriceT) {
			this.rentPriceT = rentPriceT;
			return this;
		}

		public Builder managerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder managerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder staffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}

		public Builder rentTypes(List<String> rentTypes) {
			this.rentTypes = rentTypes;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
