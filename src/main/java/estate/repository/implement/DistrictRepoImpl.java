package estate.repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import java.sql.*;

import estate.entity.District;
import estate.repository.interf.DistrictRepo;
import estate.util.ConnectionJDBC;
@Repository
public class DistrictRepoImpl implements DistrictRepo {
	@Override
	public District findDistrictById(Long districtId) {
		String sql = "SELECT district.* FROM district WHERE district.id = ?";
		District district = null;
		try (
			Connection cnn = ConnectionJDBC.getConnection();
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
