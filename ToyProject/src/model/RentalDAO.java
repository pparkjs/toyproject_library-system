package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.RentalVO;



public class RentalDAO {

	// 대여 목록 조회
	// 관리자용
	public List<RentalVO> selectRentals() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";      
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT");
		builder.append("    c.mem_name,");
		builder.append("    r.rental_number,");
		builder.append("    r.rental_date,");
		builder.append("    r.rental_return_schedule,");
		builder.append("    r.overdue_check,");
		builder.append("    r.mem_id,"); 
		builder.append("    r.book_id,");
		builder.append("    b.book_title,");
		builder.append("    r.rental_return_date,");
		builder.append("    r.rental_return_check,");
		builder.append("    case when (trunc(sysdate)-trunc(r.RENTAL_RETURN_SCHEDULE)) >=0 then ");
		builder.append("                     (trunc(sysdate)-trunc(r.RENTAL_RETURN_SCHEDULE)) ");
		builder.append("                 else  ");
		builder.append("                     0  ");
		builder.append("           end as overday ");
		builder.append(" FROM ");
		builder.append("    rental r, ");
		builder.append("    book b, ");
		builder.append("    member c ");
		builder.append(" WHERE ");
		builder.append(" b.book_id = r.book_id ");
		builder.append(" and c.mem_id = r.mem_id ");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<RentalVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String name = resultSet.getString("mem_name");
			String rentalNumber = resultSet.getString("rental_number");
			Date rentalDate= resultSet.getDate("rental_date");
			Date rentalReturnSchedule= resultSet.getDate("rental_return_schedule");
			String overdueCheck = resultSet.getString("overdue_check");
			String memId = resultSet.getString("mem_id");
			Date rentalReturnDate = resultSet.getDate("rental_return_date");
			String bookId = resultSet.getString("book_id");
			String bookTitle = resultSet.getString("book_title");
			String rentalReturnCheck = resultSet.getString("rental_return_check");
			int overDay = resultSet.getInt("overDay");
			list.add(new RentalVO(name, rentalNumber, rentalDate, rentalReturnSchedule, overdueCheck, memId, 
					rentalReturnDate, bookId, bookTitle, rentalReturnCheck, overDay));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}


	// 회원용
	public List<RentalVO> selectRental(String Id) throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";

		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT");
		builder.append("    r.rental_number, ");
		builder.append("    r.rental_date,");
		builder.append("    r.rental_return_schedule, ");
		builder.append("    r.overdue_check, ");
		builder.append("    r.mem_id, "); 
		builder.append("    r.book_id, ");
		builder.append("    b.book_title, ");
		builder.append("    r.rental_return_date, ");
		builder.append("    r.rental_return_check, ");
		builder.append("    case when (trunc(sysdate)-trunc(RENTAL_RETURN_SCHEDULE)) >=0 then ");
		builder.append("                     (trunc(sysdate)-trunc(RENTAL_RETURN_SCHEDULE)) ");
		builder.append("                 else  ");
		builder.append("                     0  ");
		builder.append("           end as overday ");
		builder.append(" FROM ");
		builder.append("    rental r, ");
		builder.append("    book b ");
		builder.append(" WHERE ");
		builder.append("    mem_id = ? ");
		builder.append(" AND   rental_return_check = 'N' ");
		builder.append(" AND   b.book_id = r.book_id ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Id);
		ResultSet resultSet = statement.executeQuery();

		List<RentalVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String rentalNumber = resultSet.getString("rental_number");
			Date rentalDate= resultSet.getDate("rental_date");
			Date rentalReturnSchedule= resultSet.getDate("rental_return_schedule");
			String overdueCheck = resultSet.getString("overdue_check");
			String memId = resultSet.getString("mem_id");
			String bookId = resultSet.getString("book_id");
			String bookName = resultSet.getString("book_title");
			Date rentalReturnDate = resultSet.getDate("rental_return_date");
			String rentalReturnCheck = resultSet.getString("rental_return_check");
			int overDay = resultSet.getInt("overDay");
			list.add(new RentalVO(rentalNumber, memId, bookId, 
					overdueCheck, rentalReturnCheck, rentalDate, 
					rentalReturnSchedule, rentalReturnDate,bookName,overDay));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return list;

	}


	// 대여 등록
	// 대여를 등록할때는 반납예정일은 sysdate +7, 빌린날은 sysdate,
	// 빌린거 체크, 연체체크는 기본값 N, 반납일도 null로 기본 초기화;
	public int insertRental(RentalVO vo) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();   
		builder.append("    INSERT INTO rental (      ");
		builder.append("    rental_number,        ");
		builder.append("    mem_id,              ");
		builder.append("    book_id               ");
		builder.append(") VALUES ( ");
		builder.append("    'REN' ");
		builder.append("    || rental_num_seq.NEXTVAL, ");
		builder.append("    ?, ");
		builder.append("    ( ");
		builder.append("        SELECT ");
		builder.append("            book_id ");
		builder.append("        FROM ");
		builder.append("            book ");
		builder.append("        WHERE ");
		builder.append("            book_title = ? ");
		builder.append("            AND   book_qty > 0 ");
		builder.append("    ) ");
		builder.append(") ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);;
		statement.setString(1, vo.getMemId());
		statement.setString(2, vo.getBookTitle());
        int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}

	// 반납
	//   회원이 도서 반납버튼 선택(업데이트)
	//   -> 연체여부 = N
	//       반납일 = sysdate
	//       반납여부 = Y
	//   그리고 회원 마이페이지에 나의 대여 목록에 대여한 책목록 반납한거 없어져야함 (위과정에서 N이아니므로 걸러짐)

	public int returnBook(RentalVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE rental                   ");
		builder.append("    SET                        ");
		builder.append("        rental_return_check = 'Y', ");
		builder.append("        rental_return_date = sysdate ");
		builder.append(" WHERE                        ");
		builder.append("    book_id = ( select book_id ");
		builder.append("                 from book ");
		builder.append("                where book_title = ?) ");
		builder.append("    and rental_return_check = 'N'  ");
		builder.append("    and mem_id = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getBookTitle());
		statement.setString(2, vo.getMemId());
		int count= statement.executeUpdate();
		statement.close();
		connection.close();      
		return count;
	}

	// 책 연장 
	public int extentionRental(RentalVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE rental                   ");
		builder.append("    SET                        ");
		builder.append("        rental_return_schedule = rental_return_schedule+7 ");
		builder.append(" WHERE                        ");
		builder.append("    book_id = ( select book_id ");
		builder.append("                 from book ");
		builder.append("                where book_title = ?) ");
		builder.append("       and mem_id = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getBookTitle());
		statement.setString(2, vo.getMemId());

		int count= statement.executeUpdate();
		statement.close();
		connection.close();      
		return count;
	}
	
	//------------------------------------------------------------------------------------------------------------	
	
	   // 대여 수단 선택 돈으로 
		public int selectMoney(RentalVO vo) throws Exception {
			
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		      
		    String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		    String user = "basic_project";
		    String password = "java";
		      
		    Connection connection = DriverManager.getConnection(url, user, password);
		    StringBuilder builder = new StringBuilder();
		    builder.append(" UPDATE MEMBER  ");
		    builder.append("   SET mem_money = ?, ");
		    builder.append("       mem_mileage = ? ");
		    builder.append(" WHERE ");
		    builder.append("       mem_id = ? ");
		    builder.append("       and mem_money>=300" );
		
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, vo.getMoney());
			statement.setInt(2, vo.getMileage());
			statement.setString(3, vo.getMemId());
			
			int count= statement.executeUpdate();
		    statement.close();
		    connection.close();		
			return count;
		}
			   
		 // 대여 수단 선택 마일리지로	
	
		public int selectMileage(RentalVO vo) throws Exception {
			
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		      
		    String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		    String user = "basic_project";
		    String password = "java";
		      
		    Connection connection = DriverManager.getConnection(url, user, password);
		    StringBuilder builder = new StringBuilder();
		    builder.append("UPDATE MEMBER ");
		    builder.append("   SET ");
		    builder.append("       mem_mileage = mem_mileage- 300 ");
		    builder.append(" WHERE ");
		    builder.append("       mem_id = ? ");
		    builder.append("       and mem_mileage>=300 " );
		
			String sql = builder.toString();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, vo.getMemId());
			
			int count= statement.executeUpdate();
		    statement.close();
		    connection.close();		
			return count;
		}	


}