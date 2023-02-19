package vo;

import java.sql.Date;

public class BookVO {
   private String bookId;
   private String title;
   private String author;
   private String genre;
   private String subGenre;
   private Date publishedDate;
   private String publisher;
   private int qty; // 기본값
   private int mileage; // 기본값
   private int location;
   private int rentalFee; // 기본값
   
//   private int retalNumber;
//   private String memId;
//   private String overdueCheck;
//   private String rentalReturnCheck;
//   private Date rentalDate;
//   private Date rentalReturnScheldule;
//   private Date rentalReturnDate;

   // insert, select할 때 사용
   public BookVO(String bookId, String title, String author, String genre, String subGenre, Date publishedDate,
         int qty, int mileage, int location, int rentalFee, String publisher) {
      super();
      this.bookId = bookId;
      this.title = title;
      this.author = author;
      this.genre = genre;
      this.subGenre = subGenre;
      this.publishedDate = publishedDate;
      this.qty = qty;
      this.mileage = mileage;
      this.location = location;
      this.rentalFee = rentalFee;
      this.publisher = publisher;
   }
   
   // insert할 때 사용
   public BookVO(String bookId, String title, String author, String genre, String subGenre, Date publishedDate,
        String publisher, int location) {
      super();
      this.bookId = bookId;
      this.title = title;
      this.author = author;
      this.genre = genre;
      this.subGenre = subGenre;
      this.publishedDate = publishedDate;
      this.location = location;
      this.publisher =publisher;

   }
   
 
   // bookId를 이용해 해당 책 삭제용
   public BookVO(String bookId) {
      this.bookId = bookId;
   }

   public String getBookId() {
      return bookId;
   }

   public void setBookId(String bookId) {
      this.bookId = bookId;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getGenre() {
      return genre;
   }

   public void setGenre(String genre) {
      this.genre = genre;
   }

   public String getSubGenre() {
      return subGenre;
   }

   public void setSubGenre(String subGenre) {
      this.subGenre = subGenre;
   }

   public Date getPublishedDate() {
      return publishedDate;
   }

   public void setPublishedDate(Date publishedDate) {
      this.publishedDate = publishedDate;
   }

   public int getQty() {
      return qty;
   }

   public void setQty(int qty) {
      this.qty = qty;
   }

   public int getMileage() {
      return mileage;
   }

   public void setMileage(int mileage) {
      this.mileage = mileage;
   }

   public int getLocation() {
      return location;
   }

   public void setLocation(int location) {
      this.location = location;
   }

   public int getRentalFee() {
      return rentalFee;
   }

   public void setRentalFee(int rentalFee) {
      this.rentalFee = rentalFee;
   }

   @Override
   public String toString() {
		return String.format(	  
            "%s ㅣ %s ㅣ %s ㅣ %s ㅣ %s\n",
            bookId, title, author, publisher, publishedDate);
   }

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

}