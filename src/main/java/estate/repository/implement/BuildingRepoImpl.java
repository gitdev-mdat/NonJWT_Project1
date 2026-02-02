package estate.repository.implement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import estate.dto.request.BuildingDTORequest;
import estate.entity.Building;
import estate.repository.interf.BuildingRepo;

public class BuildingRepoImpl implements BuildingRepo {
	private static final String USER = "root";
	private static final String PASSWORD = "12345";
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	@Override
	public List<Building> search(BuildingDTORequest building) {

	    StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
	    StringBuilder where = new StringBuilder(" WHERE 1=1 ");
	    sql = JoinFunction(building,sql);
	    where = WhereFunction(building,where);
	    sql.append(where);

	    List<Building> result = new ArrayList<>();

	    try (
	        Connection cnn = DriverManager.getConnection(URL, USER, PASSWORD);
	        PreparedStatement stm = cnn.prepareStatement(sql.toString());
	    ) {

	        int index = 1;

	        if (building.getBuildingName() != null && !building.getBuildingName().isBlank()) {
	            stm.setString(index++, "%" + building.getBuildingName() + "%");
	        }
	        if (building.getWard() != null && !building.getWard().isBlank()) {
	            stm.setString(index++, "%" + building.getWard() + "%");
	        }
	        if (building.getDistrictId() != null) {
	            stm.setLong(index++, building.getDistrictId());
	        }
	        if (building.getStreet() != null && !building.getStreet().isBlank()) {
	            stm.setString(index++, "%" + building.getStreet() + "%");
	        }
	        if (building.getFloorArea() != null) {
	            stm.setInt(index++, building.getFloorArea());
	        }
	        if (building.getNumberOfBasement() != null) {
	            stm.setInt(index++, building.getNumberOfBasement());
	        }
	        if (building.getDirection() != null && !building.getDirection().isBlank()) {
	            stm.setString(index++, "%" + building.getDirection() + "%");
	        }
	        if (building.getLevel() != null && !building.getLevel().isBlank()) {
	            stm.setString(index++, "%" + building.getLevel() + "%");
	        }
	        if (building.getAreaF() != null) {
	            stm.setInt(index++, building.getAreaF());
	        }
	        if (building.getAreaT() != null) {
	            stm.setInt(index++, building.getAreaT());
	        }
	        if (building.getRentPriceF() != null) {
	            stm.setInt(index++, building.getRentPriceF());
	        }
	        if (building.getRentPriceT() != null) {
	            stm.setInt(index++, building.getRentPriceT());
	        }
	        if (building.getManagerName() != null && !building.getManagerName().isBlank()) {
	            stm.setString(index++, "%" + building.getManagerName() + "%");
	        }
	        if (building.getManagerPhone() != null && !building.getManagerPhone().isBlank()) {
	            stm.setString(index++, "%" + building.getManagerPhone() + "%");
	        }
	        if (building.getStaffId() != null) {
                stm.setLong(index++, building.getStaffId());
            }

            if (building.getRentType() != null && !building.getRentType().isEmpty()) {
                for (String rentTypeCode : building.getRentType()) {
                    stm.setString(index++, rentTypeCode);
                }
            }
            String finalSql = stm.toString();
            System.out.println("finalSql: " + finalSql);
	        // ----------------------
	        ResultSet rs = stm.executeQuery();
	        while (rs.next()) {
	            Building b = new Building();
	            b.setId(rs.getLong("id"));
	            b.setName(rs.getString("name"));
	            b.setStreet(rs.getString("street"));
	            b.setWard(rs.getString("ward"));
	            b.setDistrictId(rs.getLong("districtid"));
	            b.setNumberOfBasement(rs.getInt("numberofbasement"));
	            b.setManagerName(rs.getString("managername"));
	            b.setManagerPhoneNumber(rs.getString("managerphonenumber"));
	            b.setFloorArea(rs.getInt("floorarea"));
	            b.setRentPrice(rs.getInt("rentprice"));
	            b.setServiceFee(rs.getString("servicefee"));
	            b.setBrokerageFee(rs.getBigDecimal("brokeragefee"));
	            result.add(b);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	private StringBuilder JoinFunction(BuildingDTORequest building,StringBuilder join) {
		  if (building.getAreaF() != null || building.getAreaT() != null) {
		        join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id ");
		    }
		    if (building.getStaffId() != null) {
		        join.append(" INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id ");
		    }

		    if (building.getRentType() != null && !building.getRentType().isEmpty()) {
		        join.append(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ");
		        join.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid");
		    }
		return join;
	}
	private StringBuilder WhereFunction(BuildingDTORequest building, StringBuilder where) {
		if (building.getBuildingName() != null && !building.getBuildingName().isBlank()) {
	        where.append(" AND b.name LIKE ? ");
	    }
	    if (building.getWard() != null && !building.getWard().isBlank()) {
	        where.append(" AND b.ward LIKE ? ");
	    }
	    if (building.getDistrictId() != null) {
	        where.append(" AND b.districtid = ? ");
	    }
	    if (building.getStreet() != null && !building.getStreet().isBlank()) {
	        where.append(" AND b.street LIKE ? ");
	    }
	    if (building.getFloorArea() != null) {
	        where.append(" AND b.floorarea = ? ");
	    }
	    if (building.getNumberOfBasement() != null) {
	        where.append(" AND b.numberofbasement = ? ");
	    }
	    if (building.getDirection() != null && !building.getDirection().isBlank()) {
	        where.append(" AND b.direction LIKE ? ");
	    }
	    if (building.getLevel() != null && !building.getLevel().isBlank()) {
	        where.append(" AND b.level LIKE ? ");
	    }

	    if (building.getAreaF() != null) {
	        where.append(" AND ra.value >= ? ");
	    }
	    if (building.getAreaT() != null) {
	        where.append(" AND ra.value <= ? ");
	    }

	    if (building.getRentPriceF() != null) {
	        where.append(" AND b.rentprice >= ? ");
	    }
	    if (building.getRentPriceT() != null) {
	        where.append(" AND b.rentprice <= ? ");
	    }

	    if (building.getManagerName() != null && !building.getManagerName().isBlank()) {
	        where.append(" AND b.managername LIKE ? ");
	    }
	    if (building.getManagerPhone() != null && !building.getManagerPhone().isBlank()) {
	        where.append(" AND b.managerphone LIKE ? ");
	    }
	   	if (building.getStaffId() != null) {
            where.append(" AND ab.staffid = ? ");
        }
        // đoạn này e có tham khảo
        if (building.getRentType() != null && !building.getRentType().isEmpty()) {
            where.append(" AND rt.code IN (");
            for (int i = 0; i < building.getRentType().size(); i++) {
                where.append("?");
                if (i < building.getRentType().size() - 1) {
                    where.append(",");
                }
            }
            where.append(") ");
        }
        return where;
	}
}
