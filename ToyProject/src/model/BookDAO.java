package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.BookVO;


public class BookDAO {
   
   // 책 목록조회
   public List<BookVO> selectbooks() throws Exception{
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      
      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
      String user = "basic_project";
      String password = "java";
      
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement statement = connection.createStatement();
      
      StringBuilder builder = new StringBuilder();
      builder.append("SElECT");
      builder.append("    book_id,");
      builder.append("    book_title,");
      builder.append("    book_author,");
      builder.append("    book_genre,");
      builder.append("    book_subgenre,");
      builder.append("    book_published_Date,");
      builder.append("    book_qty,");
      builder.append("    book_mileage,");
      builder.append("    book_location,");
      builder.append("    book_rental_fee, ");
      builder.append("    book_publisher ");      
      builder.append(" FROM ");
      builder.append("  book ");
      
      String sql = builder.toString();
      ResultSet resultSet = statement.executeQuery(sql);
      
      List<BookVO> list = new ArrayList<>();
      
      while(resultSet.next()) {
         String bookId = resultSet.getString("book_id");
         String title = resultSet.getString("book_title");
         String author = resultSet.getString("book_author");
         String genre = resultSet.getString("book_genre");
         String subGenre = resultSet.getString("book_subgenre");
         Date publishedDate = resultSet.getDate("book_published_date");
         int qty = resultSet.getInt("book_qty"); 
         int mileage = resultSet.getInt("book_mileage"); 
         int location = resultSet.getInt("book_location"); 
         int rentalFee = resultSet.getInt("book_rental_fee"); 
         String publisher = resultSet.getString("book_publisher");
         list.add(new BookVO(bookId, title, author, genre, subGenre,
               publishedDate, qty, mileage, location,rentalFee,publisher));
      }
      resultSet.close();
      statement.close();
      connection.close();
      
      return list;
      
   }
   
   // 책 등록
   
   public int insertBook(BookVO vo) throws Exception{
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      
      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
      String user = "basic_project";
      String password = "java";
      
      Connection connection = DriverManager.getConnection(url, user, password);
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO book (");
      builder.append("    book_id,");      
      builder.append("    book_title,");      
      builder.append("    book_author,");      
      builder.append("    book_genre,");      
      builder.append("    book_subgenre,");      
      builder.append("    book_published_date,");      
      builder.append("    book_publisher,");      
      builder.append("    book_location");
      builder.append(")");
      builder.append("VALUES ");
      builder.append("    ( ");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?,");
      builder.append("    ?");
      builder.append(")");
      
      
      String sql = builder.toString();
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, vo.getBookId());
      statement.setString(2, vo.getTitle());
      statement.setString(3, vo.getAuthor());
      statement.setString(4, vo.getGenre());
      statement.setString(5, vo.getSubGenre());
      statement.setDate(6, vo.getPublishedDate());
      statement.setString(7, vo.getPublisher());
      statement.setInt(8, vo.getLocation());
      int count = statement.executeUpdate();
      
      statement.close();
      connection.close();
      
      return count;
   }
   
   // 저자로 책 검색
   
   public List<BookVO> searchAuthorName(String authorName) throws Exception {
	      
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
	      String user = "basic_project";
	      String password = "java";
	      
	      Connection connection = DriverManager.getConnection(url, user, password);
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      
	      builder.append("SELECT                 ");
	      builder.append("    book_id,             ");
	      builder.append("    book_title,           ");
	      builder.append("    book_published_date,  ");
	      builder.append("    book_publisher,        ");
	      builder.append("    book_genre,           ");
	      builder.append("    book_mileage,          ");
	      builder.append("    book_author,          ");
	      builder.append("    book_qty,             ");
	      builder.append("    book_location,       ");
	      builder.append("    book_subgenre,        ");
	      builder.append("    book_rental_fee       ");
	      builder.append(" FROM                       ");
	      builder.append("    book                  ");
	      builder.append(" WHERE                      ");
	      builder.append("    book_author LIKE '"+authorName+"%' ");

	      String sql = builder.toString();      
	      ResultSet resultSet = statement.executeQuery(sql);
	      
	      List<BookVO> list = new ArrayList<>();
	      
	      while(resultSet.next()) {
	    	  String bookId  = resultSet.getString("book_id");
	    	  String title = resultSet.getString("book_title");
	    	  Date publishedDate = resultSet.getDate("book_published_date");
	    	  String genre = resultSet.getString("book_genre");
	    	  int mileage = resultSet.getInt("book_mileage");
	    	  String author = resultSet.getString("book_author");
	    	  int qty = resultSet.getInt("book_qty");
	    	  int location = resultSet.getInt("book_location");
	    	  String subGenre = resultSet.getString("book_subgenre");
	    	  int rentalfee  = resultSet.getInt("book_rental_fee");
	    	  String publisher = resultSet.getString("book_subgenre");
	    			  
	    	  list.add(new BookVO(bookId, title, author, genre, subGenre, publishedDate,
	    			  qty, mileage, location, rentalfee, publisher));    	  
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return list;
	   }   
   
   // 분야로 책 검색
   public List<BookVO> searchGenreName(String genreName) throws Exception {
	      
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
	      String user = "basic_project";
	      String password = "java";
	      
	      Connection connection = DriverManager.getConnection(url, user, password);
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      
	      builder.append("SELECT ");
	      builder.append("    book_id, ");
	      builder.append("    book_title, ");
	      builder.append("    book_published_date, ");
	      builder.append("    book_publisher, ");
	      builder.append("    book_genre, ");
	      builder.append("    book_mileage, ");
	      builder.append("    book_author, ");
	      builder.append("    book_qty, ");
	      builder.append("    book_location, ");
	      builder.append("    book_subgenre, ");
	      builder.append("    book_rental_fee ");
	      builder.append("FROM ");
	      builder.append("    book ");
	      builder.append("WHERE ");
	      builder.append("    book_genre LIKE '%"+genreName+"%' ");

	      String sql = builder.toString();      
	      ResultSet resultSet = statement.executeQuery(sql);
	      
	      List<BookVO> list = new ArrayList<>();
	      
	      while(resultSet.next()) {
	    	  String bookId  = resultSet.getString("book_id");
	    	  String title = resultSet.getString("book_title");
	    	  Date publishedDate = resultSet.getDate("book_published_date");
	    	  String genre = resultSet.getString("book_genre");
	    	  int mileage = resultSet.getInt("book_mileage");
	    	  String author = resultSet.getString("book_author");
	    	  int qty = resultSet.getInt("book_qty");
	    	  int location = resultSet.getInt("book_location");
	    	  String subGenre = resultSet.getString("book_subgenre");
	    	  int rentalfee  = resultSet.getInt("book_rental_fee");
	    	  String publisher = resultSet.getString("book_subgenre");
	    			  
	    	  list.add(new BookVO(bookId, title, author, genre, subGenre, publishedDate,
	    			  qty, mileage, location, rentalfee, publisher));    	  
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return list;
	   }
	      
   
   
   // 제목으로 책 검색

   public List<BookVO> searchBookName(String name) throws Exception {
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
      String user = "basic_project";
      String password = "java";
      
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement statement = connection.createStatement();
      StringBuilder builder = new StringBuilder();
      
      builder.append("SELECT ");
      builder.append("    book_id, ");
      builder.append("    book_title, ");
      builder.append("    book_published_date, ");
      builder.append("    book_publisher, ");
      builder.append("    book_genre, ");
      builder.append("    book_mileage, ");
      builder.append("    book_author, ");
      builder.append("    book_qty, ");
      builder.append("    book_location, ");
      builder.append("    book_subgenre, ");
      builder.append("    book_rental_fee ");
      builder.append("FROM ");
      builder.append("    book ");
      builder.append("WHERE ");
      builder.append("    book_title LIKE '%"+name+"%' ");

      String sql = builder.toString();      
      ResultSet resultSet = statement.executeQuery(sql);
      
      List<BookVO> list = new ArrayList<>();
      
      while(resultSet.next()) {
    	  String bookId  = resultSet.getString("book_id");
    	  String title = resultSet.getString("book_title");
    	  Date publishedDate = resultSet.getDate("book_published_date");
    	  String genre = resultSet.getString("book_genre");
    	  int mileage = resultSet.getInt("book_mileage");
    	  String author = resultSet.getString("book_author");
    	  int qty = resultSet.getInt("book_qty");
    	  int location = resultSet.getInt("book_location");
    	  String subGenre = resultSet.getString("book_subgenre");
    	  int rentalfee  = resultSet.getInt("book_rental_fee");
    	  String publisher = resultSet.getString("book_subgenre");
    			  
    	  list.add(new BookVO(bookId, title, author, genre, subGenre, publishedDate,
    			  qty, mileage, location, rentalfee, publisher));    	  
      }
      resultSet.close();
      statement.close();
      connection.close();
      return list;
   }
   
   public BookVO searchBookNameQty(String name) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
	      String user = "basic_project";
	      String password = "java";
	      Connection connection = DriverManager.getConnection(url, user, password);
	      StringBuilder builder = new StringBuilder();
	      builder.append("SELECT ");
	      builder.append("    book_id, ");
	      builder.append("    book_title, ");
	      builder.append("    book_published_date, ");
	      builder.append("    book_publisher, ");
	      builder.append("    book_genre, ");
	      builder.append("    book_mileage, ");
	      builder.append("    book_author, ");
	      builder.append("    book_qty, ");
	      builder.append("    book_location, ");
	      builder.append("    book_subgenre, ");
	      builder.append("    book_rental_fee ");
	      builder.append("FROM ");
	      builder.append("    book ");
	      builder.append("WHERE ");
	      builder.append("    book_title = ? ");
	      builder.append("    and book_qty>0 ");
	      String sql = builder.toString();      
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, name);
	      ResultSet resultSet = statement.executeQuery();
	      BookVO vo = null;
	      
	      while(resultSet.next()) {
	    	  String bookId  = resultSet.getString("book_id");
	    	  String title = resultSet.getString("book_title");
	    	  Date publishedDate = resultSet.getDate("book_published_date");
	    	  String genre = resultSet.getString("book_genre");
	    	  int mileage = resultSet.getInt("book_mileage");
	    	  String author = resultSet.getString("book_author");
	    	  int qty = resultSet.getInt("book_qty");
	    	  int location = resultSet.getInt("book_location");
	    	  String subGenre = resultSet.getString("book_subgenre");
	    	  int rentalfee  = resultSet.getInt("book_rental_fee");
	    	  String publisher = resultSet.getString("book_subgenre");
	    			  
	    	  vo =new BookVO(bookId, title, author, genre, subGenre, publishedDate,
	    			  qty, mileage, location, rentalfee, publisher);    	  
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return vo;
	   }
   
   

   // 책 삭제
   
   public int deleteBook(String deleteBook) throws Exception {
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      
      String url = "jdbc:oracle:thin:@192.168.146.53:1521:xe";
      String user = "basic_project";
      String password = "java";
      
      Connection connection = DriverManager.getConnection(url, user, password);
      StringBuilder builder = new StringBuilder();
      builder.append("delete ");
      builder.append("FROM ");
      builder.append("    book ");
      builder.append("WHERE ");
      builder.append("    book_title = ? ");
      String sql = builder.toString();
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, deleteBook);
      int count = statement.executeUpdate();
      statement.close();
      connection.close();
      return count;
   }
   

}











