package Repository.Implement;
import java.sql.Connection;
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
	public List<Integer> getRentAreasByBuildingId(Long buildingId) {
		List<Integer> rentAreas = new ArrayList<>();
		String sql = "SELECT value FROM rentarea WHERE buildingid = ?";
		try (
			Connection cnn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement stm = cnn.prepareStatement(sql)) {
			stm.setLong(1, buildingId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				rentAreas.add(rs.getInt("value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentAreas;
	}	
}


