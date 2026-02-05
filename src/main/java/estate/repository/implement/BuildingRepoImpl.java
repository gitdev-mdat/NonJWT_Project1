package estate.repository.implement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import estate.builder.BuildingSearchBuilder;
import estate.entity.Building;
import estate.repository.interf.BuildingRepo;
import estate.util.ConnectionJDBC;
@Repository
public class BuildingRepoImpl implements BuildingRepo {
	@Override
	public List<Building> search(BuildingSearchBuilder builder) {

	    StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
	    StringBuilder where = new StringBuilder(" WHERE 1=1 ");
	    sql = JoinFunction(builder,sql);
	    where = WhereFunction(builder,where);
	    sql.append(where);

	    List<Building> result = new ArrayList<>();

	    try (
	        Connection cnn = ConnectionJDBC.getConnection();
	        PreparedStatement stm = cnn.prepareStatement(sql.toString());
	    ) {

	        int index = 1;

	        if (builder.getBuildingName() != null && !builder.getBuildingName().isBlank()) {
	            stm.setString(index++, "%" + builder.getBuildingName() + "%");
	        }
	        if (builder.getWard() != null && !builder.getWard().isBlank()) {
	            stm.setString(index++, "%" + builder.getWard() + "%");
	        }
	        if (builder.getDistrictId() != null) {
	            stm.setLong(index++, builder.getDistrictId());
	        }
	        if (builder.getStreet() != null && !builder.getStreet().isBlank()) {
	            stm.setString(index++, "%" + builder.getStreet() + "%");
	        }
	        if (builder.getFloorArea() != null) {
	            stm.setInt(index++, builder.getFloorArea());
	        }
	        if (builder.getNumberOfBasement() != null) {
	            stm.setInt(index++, builder.getNumberOfBasement());
	        }
	        if (builder.getDirection() != null && !builder.getDirection().isBlank()) {
	            stm.setString(index++, "%" + builder.getDirection() + "%");
	        }
	        if (builder.getLevel() != null && !builder.getLevel().isBlank()) {
	            stm.setString(index++, "%" + builder.getLevel() + "%");
	        }
	        if (builder.getAreaF() != null) {
	            stm.setInt(index++, builder.getAreaF());
	        }
	        if (builder.getAreaT() != null) {
	            stm.setInt(index++, builder.getAreaT());
	        }
	        if (builder.getRentPriceF() != null) {
	            stm.setInt(index++, builder.getRentPriceF());
	        }
	        if (builder.getRentPriceT() != null) {
	            stm.setInt(index++, builder.getRentPriceT());
	        }
	        if (builder.getManagerName() != null && !builder.getManagerName().isBlank()) {
	            stm.setString(index++, "%" + builder.getManagerName() + "%");
	        }
	        if (builder.getManagerPhone() != null && !builder.getManagerPhone().isBlank()) {
	            stm.setString(index++, "%" + builder.getManagerPhone() + "%");
	        }
	        if (builder.getStaffId() != null) {
                stm.setLong(index++, builder.getStaffId());
            }

            if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
                for (String rentTypeCode : builder.getRentTypes()) {
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
	private StringBuilder JoinFunction(BuildingSearchBuilder builder,StringBuilder join) {
		  if (builder.getAreaF() != null || builder.getAreaT() != null) {
		        join.append(" INNER JOIN rentarea ra ON ra.buildingid = b.id ");
		    }
		    if (builder.getStaffId() != null) {
		        join.append(" INNER JOIN assignmentbuilding ab ON ab.buildingid = b.id ");
		    }

		    if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
		        join.append(" INNER JOIN buildingrenttype brt ON brt.buildingid = b.id ");
		        join.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid");
		    }
		return join;
	}
	private StringBuilder WhereFunction(BuildingSearchBuilder builder, StringBuilder where) {
		if (builder.getBuildingName() != null && !builder.getBuildingName().isBlank()) {
	        where.append(" AND b.name LIKE ? ");
	    }
	    if (builder.getWard() != null && !builder.getWard().isBlank()) {
	        where.append(" AND b.ward LIKE ? ");
	    }
	    if (builder.getDistrictId() != null) {
	        where.append(" AND b.districtid = ? ");
	    }
	    if (builder.getStreet() != null && !builder.getStreet().isBlank()) {
	        where.append(" AND b.street LIKE ? ");
	    }
	    if (builder.getFloorArea() != null) {
	        where.append(" AND b.floorarea = ? ");
	    }
	    if (builder.getNumberOfBasement() != null) {
	        where.append(" AND b.numberofbasement = ? ");
	    }
	    if (builder.getDirection() != null && !builder.getDirection().isBlank()) {
	        where.append(" AND b.direction LIKE ? ");
	    }
	    if (builder.getLevel() != null && !builder.getLevel().isBlank()) {
	        where.append(" AND b.level LIKE ? ");
	    }

	    if (builder.getAreaF() != null) {
	        where.append(" AND ra.value >= ? ");
	    }
	    if (builder.getAreaT() != null) {
	        where.append(" AND ra.value <= ? ");
	    }

	    if (builder.getRentPriceF() != null) {
	        where.append(" AND b.rentprice >= ? ");
	    }
	    if (builder.getRentPriceT() != null) {
	        where.append(" AND b.rentprice <= ? ");
	    }

	    if (builder.getManagerName() != null && !builder.getManagerName().isBlank()) {
	        where.append(" AND b.managername LIKE ? ");
	    }
	    if (builder.getManagerPhone() != null && !builder.getManagerPhone().isBlank()) {
	        where.append(" AND b.managerphonenumber LIKE ? ");
	    }
	   	if (builder.getStaffId() != null) {
            where.append(" AND ab.staffid = ? ");
        }
 
        if (builder.getRentTypes() != null && !builder.getRentTypes().isEmpty()) {
            where.append(" AND rt.code IN (");
            for (int i = 0; i < builder.getRentTypes().size(); i++) {
                where.append("?");
                if (i < builder.getRentTypes().size() - 1) {
                    where.append(",");
                }
            }
            where.append(") ");
        }
//		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
//
//	    for (Field field : fields) {
//	        String fieldName = field.getName();
//
//	 
//	        if (fieldName.equals("staffId")
//	                || fieldName.equals("rentTypes")
//	                || fieldName.startsWith("area")
//	                || fieldName.startsWith("rentPrice")) {
//	            continue;
//	        }
//
//	        field.setAccessible(true);
//
//	        try {
//	            Object value = field.get(builder);
//	            if (value != null) {
//	                if (field.getType().equals(String.class)) {
//	                    where.append(" AND b.")
//	                         .append(fieldName.toLowerCase())
//	                         .append(" LIKE ? ");
//	                } else {
//	                    where.append(" AND b.")
//	                         .append(fieldName.toLowerCase())
//	                         .append(" = ? ");
//	                }
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
	    return where;
	}
}
