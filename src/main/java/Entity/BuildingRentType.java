package Entity;

public class BuildingRentType {
	private Long id;
	private Long buildingId;
	private Long rentTypeId;
	public BuildingRentType(Long buildingId, Long rentTypeId) {
		super();
		this.buildingId = buildingId;
		this.rentTypeId = rentTypeId;
	}
	public BuildingRentType(Long id, Long buildingId, Long rentTypeId) {
		super();
		this.id = id;
		this.buildingId = buildingId;
		this.rentTypeId = rentTypeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long getRentTypeId() {
		return rentTypeId;
	}
	public void setRentTypeId(Long rentTypeId) {
		this.rentTypeId = rentTypeId;
	}
	
}
