package estate.repository.implement;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import estate.entity.RentArea;
import estate.repository.interf.RentAreaRepository;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	private static final String USER = "root";
	private static final String PASSWORD = "12345";
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
				Timestamp tsCD = rs.getTimestamp("createddate");
				ra.setCreatedDate(tsCD != null ? tsCD.toLocalDateTime(): null);
				ra.setModifiedBy(rs.getString("modifiedby"));
				Timestamp tsMD = rs.getTimestamp("modifieddate") ;
				ra.setModifiedDate(tsMD != null ? tsMD.toLocalDateTime(): null);
				rentAreas.add(ra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentAreas;
	}

		
	}	


