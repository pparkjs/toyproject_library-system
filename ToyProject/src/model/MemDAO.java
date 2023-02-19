package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;
import vo.MemVO;

public class MemDAO {
	
	//id 중복 체크하는 메서드
	public boolean idCheck(String id) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    MEM_ID ");
		sb.append("FROM ");
		sb.append("    MEMBER ");
		sb.append("WHERE ");
		sb.append("    MEM_ID = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			String memId = resultSet.getString("mem_id");
			// 입력 한 id가 member테이블의 id와 같으면 false 리턴
			if(memId.equals(id)) {
				connection.close();
				statement.close();
				resultSet.close();
				return false;
			}
		}
		connection.close();
		statement.close();
		resultSet.close();
		return true;
	}
	
	//회원가입한 정보 insert하는 메서드
	public int insertMemInfor(MemVO mem) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO member ( ");
		sb.append("    mem_id, ");
		sb.append("    mem_name, ");
		sb.append("    mem_pw, ");
		sb.append("    mem_addr, ");
		sb.append("    mem_phon_num, ");
		sb.append("    mem_regno1, ");
		sb.append("    mem_regno2, ");
		sb.append("    mem_email ");
		sb.append(") VALUES ( ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ? ");
		sb.append(") ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, mem.getMemId());
		statement.setString(2, mem.getMemName());
		statement.setString(3, mem.getMemPw());
		statement.setString(4, mem.getMemAddr());
		statement.setString(5, mem.getMemPhoneNum());
		statement.setString(6, mem.getMemRegno1());
		statement.setString(7, mem.getMemRegno2());
		statement.setString(8, mem.getMemEmail());
		
		int count = statement.executeUpdate();
		statement.close();
		connection.close();
		
		return count;
	}
	
	//로그인위한 id pw 조회 메서드
	public MemVO idPwSelect(String id, String pw) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    mem_name, ");
		sb.append("    mem_id, ");
		sb.append("    mem_phon_num, ");
		sb.append("    mem_regno1, ");
		sb.append("    mem_regno2, ");
		sb.append("    mem_addr, ");
		sb.append("    mem_email, ");
		sb.append("    mem_mileage,");
		sb.append("MEM_REGISTRATION_DATE, ");
		sb.append("    mem_money ");
		sb.append("FROM ");
		sb.append("    member ");
		sb.append("WHERE ");
		sb.append("    mem_id = ? ");
		sb.append("    AND   mem_pw = ? ");
		String sql = sb.toString();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		statement.setString(2, pw);
		ResultSet resultSet = statement.executeQuery();
		MemVO mem = null;
		
		while(resultSet.next()) {
			String memName = resultSet.getString("mem_name");
			String memId = resultSet.getString("mem_id");
			String memPhoneNum = resultSet.getString("mem_phon_num");
			String memRegno1 = resultSet.getString("mem_regno1");
			String memRegno2 = resultSet.getString("mem_regno2");
			String memAddr = resultSet.getString("mem_addr");
			String memEmail = resultSet.getString("mem_email");
			int memMileage = resultSet.getInt("mem_mileage");
			Timestamp memRegiTimestamp = resultSet.getTimestamp("MEM_REGISTRATION_DATE");
			int memMoney = resultSet.getInt("mem_money");
			mem = new MemVO(memId, memName, memAddr, memPhoneNum, memRegno1, memRegno2, memEmail, memMileage,memRegiTimestamp, memMoney);
		}
		
		statement.close();
		connection.close();
		resultSet.close();
		return mem;
	}
	
	//수정된 개인정보 업데이트할 메서드
	public int memInforUpdate(MemVO mem) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE member ");
		sb.append("    SET ");
		sb.append("        mem_addr = ?, ");
		sb.append("        mem_phon_num = ?, ");
		sb.append("        mem_email = ? ");
		sb.append("WHERE ");
		sb.append("    mem_id = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, mem.getMemAddr());
		statement.setString(2, mem.getMemPhoneNum());
		statement.setString(3, mem.getMemEmail());
		statement.setString(4, mem.getMemId());

		int count = statement.executeUpdate();
		connection.close();
		statement.close();
		
		return count;
	}
	
	public List<MemVO> selectMembers() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    mem_id, ");
		sb.append("    mem_name, ");
		sb.append("    mem_addr, ");
		sb.append("    mem_phon_num, ");
		sb.append("    mem_email, ");
		sb.append("    mem_registration_date, ");
		sb.append("    mem_regno1, ");
		sb.append("    mem_mileage ");
		sb.append("FROM ");
		sb.append("    member ");
		String sql = sb.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<MemVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String memId = resultSet.getString("mem_id");
			String memName = resultSet.getString("mem_name");
			String memAddr = resultSet.getString("mem_addr");
			String memPhoneNum = resultSet.getString("mem_phon_num");
			String memEmail = resultSet.getString("mem_email");
			Timestamp memRegist = resultSet.getTimestamp("mem_registration_date");
			String memRegno1 = resultSet.getString("mem_regno1");
			int memMileage = resultSet.getInt("mem_mileage");
			list.add(new MemVO(memId, memName, memAddr, memPhoneNum, memEmail, memRegist, memRegno1, memMileage));
		}
		connection.close();
		statement.close();
		resultSet.close();
		return list;
	}

	
}
