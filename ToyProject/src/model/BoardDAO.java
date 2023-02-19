package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;
import vo.AuthVO;
import vo.BoardVO;

public class BoardDAO {
	// 요청게시판 전체 검색용 메서드
	public List<BoardVO> selectReqBoards() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date ");
		sb.append("FROM ");
		sb.append("    reqboard ");
		sb.append("ORDER BY 1 ");
		String sql = sb.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<BoardVO> list = new ArrayList<>();
		while (resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			list.add(new BoardVO(num, title, memId, registerDate, modifyDate));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	// 요청게시판 해당 회원이 작성한 게시글 삭제or수정을 위해 Board 객체를 만드는 메서드
	public BoardVO selectReqBoard(int no, String Id) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		BoardVO board = null;
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    content, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date ");
		sb.append("FROM ");
		sb.append("    reqboard ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		sb.append("    AND mem_id = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, no);
		statement.setString(2, Id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String content = resultSet.getString("content");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			board = new BoardVO(num, title, content, memId, registerDate, modifyDate);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return board;
	}

	// 요청게시판 등록
	public int insertReqBoard(BoardVO vo,  String memId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO reqboard ( ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    content, ");
		sb.append("    mem_id ");
		sb.append(") VALUES ( ");
		sb.append("    req_seq.NEXTVAL, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ? ");
		sb.append(") ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle());
		statement.setString(2, vo.getContent());
		statement.setString(3, memId);
		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}

	// 요청게시판 수정
	public int updateReqBoard(BoardVO vo, int no) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append("    reqboard ");
		sb.append("SET ");
		sb.append("    title = ?, ");
		sb.append("    content = ?, ");
		sb.append("    modify_date = sysdate ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle());
		statement.setString(2, vo.getContent());
		statement.setInt(3, no);

		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}

	// 요청게시판의 게시글 삭제
	public int deleteReqBoard(int deleteNum) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append("    reqboard ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, deleteNum);

		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}
	
	//해당 게시판 선택해서 보이는 메서드
	public BoardVO detailReqBoard(int searchNum) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		BoardVO board = null;
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date, ");
		sb.append("    content ");
		sb.append("FROM ");
		sb.append("    reqboard ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, searchNum);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			String content = resultSet.getString("content");
			board = new BoardVO(num, title, content, memId, registerDate, modifyDate);
		}
		connection.close();
		statement.close();
		resultSet.close();
		return board;
	}
//-----------------------------------------------------------------------------------------------------------------------------------
	// 독후감게시판 전체 검색용 메서드
	public List<BoardVO> selectBookReports() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date ");
		sb.append("FROM ");
		sb.append("    bookreport ");
		sb.append("ORDER BY 1 ");
		String sql = sb.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<BoardVO> list = new ArrayList<>();
		while (resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			list.add(new BoardVO(num, title, memId, registerDate, modifyDate));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	// 독후감게시판 해당 회원이 작성한 게시글 삭제or수정을 위해 Board 객체를 만드는 메서드
	public BoardVO selectBookReport(int no, String Id) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		BoardVO board = null;
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    content, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date ");
		sb.append("FROM ");
		sb.append("    bookreport ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		sb.append("    AND mem_id = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, no);
		statement.setString(2, Id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String content = resultSet.getString("content");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			board = new BoardVO(num, title, content, memId, registerDate, modifyDate);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return board;
	}

	// 독후감게시판 등록
	public int insertBookReport(BoardVO vo,  String memId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO bookreport ( ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    content, ");
		sb.append("    mem_id ");
		sb.append(") VALUES ( ");
		sb.append("    report_seq.NEXTVAL, ");
		sb.append("    ?, ");
		sb.append("    ?, ");
		sb.append("    ? ");
		sb.append(") ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle());
		statement.setString(2, vo.getContent());
		statement.setString(3, memId);
		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}

	// 독후감게시판 수정
	public int updateBookReport(BoardVO vo, int no) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append("    bookreport ");
		sb.append("SET ");
		sb.append("    title = ?, ");
		sb.append("    content = ?, ");
		sb.append("    modify_date = sysdate ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getTitle());
		statement.setString(2, vo.getContent());
		statement.setInt(3, no);

		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}

	// 독후감게시판의 게시글 삭제
	public int deleteBookReport(int deleteNum) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append("    bookreport ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, deleteNum);

		int count = statement.executeUpdate();

		statement.close();
		connection.close();

		return count;
	}
	
	//해당 게시판 선택해서 본문 보는 메서드
	public BoardVO detailBookReport(int searchNum) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		BoardVO board = null;
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("    num, ");
		sb.append("    title, ");
		sb.append("    mem_id, ");
		sb.append("    register_date, ");
		sb.append("    modify_date, ");
		sb.append("    content ");
		sb.append("FROM ");
		sb.append("    bookreport ");
		sb.append("WHERE ");
		sb.append("    num = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, searchNum);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			int num = resultSet.getInt("num");
			String title = resultSet.getString("title");
			String memId = resultSet.getString("mem_id");
			Timestamp registerDate = resultSet.getTimestamp("register_date");
			Timestamp modifyDate = resultSet.getTimestamp("modify_date");
			String content = resultSet.getString("content");
			board = new BoardVO(num, title, content, memId, registerDate, modifyDate);
		}
		connection.close();
		statement.close();
		resultSet.close();
		return board;
	}
	//독후감쓴 회원 마일리지 부여 메서드
	public int memReportMilegeInsert(String memId) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
		String user = "basic_project";
		String password = "java";
		BoardVO board = null;
		Connection connection = DriverManager.getConnection(url, user, password);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE member ");
		sb.append("    SET ");
		sb.append("        mem_mileage = mem_mileage + 50 ");
		sb.append("WHERE ");
		sb.append("    mem_id = ? ");
		String sql = sb.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, memId);
		
		int count = statement.executeUpdate();
		
		connection.close();
		statement.close();
		return count;
	}
	
//----------------------------------------------------------------------------------------------------------------------------
	// 공지사항게시판 전체 검색용 메서드
		public List<BoardVO> selectNoticeBoards() throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("    num, ");
			sb.append("    title, ");
			sb.append("    register_date, ");
			sb.append("    modify_date ");
			sb.append("FROM ");
			sb.append("    noticeboard ");
			sb.append("ORDER BY 1 ");
			String sql = sb.toString();
			ResultSet resultSet = statement.executeQuery(sql);
			List<BoardVO> list = new ArrayList<>();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String title = resultSet.getString("title");
				Timestamp registerDate = resultSet.getTimestamp("register_date");
				Timestamp modifyDate = resultSet.getTimestamp("modify_date");
				list.add(new BoardVO(num, title,"관리자", registerDate, modifyDate));
			}

			resultSet.close();
			statement.close();
			connection.close();
			return list;
		}

		// 공지사항게시판 등록
		public int insertNoticeBoard(BoardVO vo) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			Connection connection = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO noticeboard ( ");
			sb.append("    num, ");
			sb.append("    title, ");
			sb.append("    content ");
			sb.append(") VALUES ( ");
			sb.append("    notice_seq.NEXTVAL, ");
			sb.append("    ?, ");
			sb.append("    ? ");
			sb.append(") ");
			String sql = sb.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getContent());
			int count = statement.executeUpdate();

			statement.close();
			connection.close();

			return count;
		}

		// 공지사항게시판 수정
		public int updateNoticeBoard(BoardVO vo, int no) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			Connection connection = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append("    noticeboard ");
			sb.append("SET ");
			sb.append("    title = ?, ");
			sb.append("    content = ?, ");
			sb.append("    modify_date = sysdate ");
			sb.append("WHERE ");
			sb.append("    num = ? ");
			String sql = sb.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getContent());
			statement.setInt(3, no);

			int count = statement.executeUpdate();

			statement.close();
			connection.close();

			return count;
		}

		// 공지사항게시판의 게시글 삭제
		public int deleteNoticeBoard(int deleteNum) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			Connection connection = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append("    noticeboard ");
			sb.append("WHERE ");
			sb.append("    num = ? ");
			String sql = sb.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, deleteNum);

			int count = statement.executeUpdate();

			statement.close();
			connection.close();

			return count;
		}
		
		//공지사항 해당 게시판 선택해서 보이는 메서드
		public BoardVO detailNoticeBoard(int searchNum) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			BoardVO board = null;
			Connection connection = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("    num, ");
			sb.append("    title, ");
			sb.append("    register_date, ");
			sb.append("    modify_date, ");
			sb.append("    content ");
			sb.append("FROM ");
			sb.append("    noticeboard ");
			sb.append("WHERE ");
			sb.append("    num = ? ");
			String sql = sb.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, searchNum);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int num = resultSet.getInt("num");
				String title = resultSet.getString("title");
				Timestamp registerDate = resultSet.getTimestamp("register_date");
				Timestamp modifyDate = resultSet.getTimestamp("modify_date");
				String content = resultSet.getString("content");
				board = new BoardVO(num, title, content,"관리자", registerDate, modifyDate);
			}
			connection.close();
			statement.close();
			resultSet.close();
			return board;
		}
		
		// 공지사항게시판 작성한 게시글 수정을 위해 Board 객체를 만드는 메서드
		public BoardVO selectNoticeBoard(int no) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
			String user = "basic_project";
			String password = "java";
			BoardVO board = null;
			Connection connection = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("    num, ");
			sb.append("    title, ");
			sb.append("    content, ");
			sb.append("    register_date, ");
			sb.append("    modify_date ");
			sb.append("FROM ");
			sb.append("    noticeboard ");
			sb.append("WHERE ");
			sb.append("    num = ? ");
			String sql = sb.toString();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, no);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Timestamp registerDate = resultSet.getTimestamp("register_date");
				Timestamp modifyDate = resultSet.getTimestamp("modify_date");
				board = new BoardVO(num, title, content,"관리자", registerDate, modifyDate);
			}
			resultSet.close();
			statement.close();
			connection.close();
			return board;
		}
//	--------------------------------------------------------------------------------------

	
}
