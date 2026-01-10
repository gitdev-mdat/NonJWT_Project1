package Repository.Implement;
import java.sql.Connection;
import Entity.RentArea;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Repository.Interface.*;
public class RentAreaImpl implements RentAreaRepo {
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	@Override
	public List<RentArea> getRentAreasByBuildingId(Long buildingId) {
		List<RentArea> rentAreas = new ArrayList<>();
		String sql = "SELECT * FROM rentarea WHERE buildingid = ?";
		try (
			Connection cnn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement stm = cnn.prepareStatement(sql)) {
			stm.setLong(1, buildingId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				RentArea ra = new RentArea();
				ra.setId(rs.getLong("id"));
				ra.setBuildingId(rs.getLong("buildingid"));
				ra.setValue(rs.getInt("value"));
				ra.setCreatedBy(rs.getString("createdby"));
				ra.setCreatedDate(rs.getTimestamp("createddate").toLocalDateTime());
				ra.setModifiedBy(rs.getString("modifiedby"));
				ra.setModifiedDate(rs.getTimestamp("modifieddate").toLocalDateTime());
				rentAreas.add(ra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentAreas;
	}

		
	}	


