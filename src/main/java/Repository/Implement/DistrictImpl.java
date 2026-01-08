package Repository.Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import Entity.District;
import Repository.Interface.DistrictRepo;

public class DistrictImpl implements DistrictRepo {
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	@Override
	public District findDistrictById(Long districtId) {
		String sql = "SELECT * FROM district WHERE district.id = ?";
		District district = null;
		try (
			Connection cnn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement stm = cnn.prepareStatement(sql)){
			stm.setLong(1, districtId);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				district = new District();
				district.setId(rs.getLong("id"));
				district.setCode(rs.getString("code"));
				district.setName(rs.getString("name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return district;
	}
	
}
