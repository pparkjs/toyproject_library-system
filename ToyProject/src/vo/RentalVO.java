package vo;

import java.sql.Date;

public class RentalVO {
	   private String rentalNumber;
	   private String memId;
	   private String bookId;
	   private String overdueCheck;
	   private String rentalReturnCheck; //
	   private Date rentalDate;  //
	   private Date rentalReturnSchedule; //
	   private Date rentalReturnDate;  //
	   private String bookTitle;
	   private int overDay;
	   private String name;
	   // member
	   private int Mileage;
	   private int Money;
	  
	   // 돈으로 빌리는 용
	   public RentalVO(String memId, int Money, int Mileage) {
		// TODO Auto-generated constructor stub
		   this.memId = memId;
		   this.Mileage = Mileage;
		   this.Money = Money;
	}
	   // 마일리지로 빌리는 용
	   public RentalVO(String memId, int Mileage) {
		// TODO Auto-generated constructor stub
		   this.memId = memId;
		   this.Mileage = Mileage;
	}
	   // select 용
	   
	   
	   public RentalVO(String rentalNumber, String memId, String bookId, String overdueCheck, String rentalReturnCheck,
			   Date rentalDate, Date rentalReturnSchedule, Date rentalReturnDate, String bookTitle) {
		   this.rentalNumber = rentalNumber;
		   this.memId = memId;
		   this.bookId = bookId;
		   this.overdueCheck = overdueCheck;
		   this.rentalReturnCheck = rentalReturnCheck;
		   this.rentalDate = rentalDate;
		   this.rentalReturnSchedule = rentalReturnSchedule;
		   this.rentalReturnDate = rentalReturnDate;
		   this.bookTitle = bookTitle;
	   }
	   //select에서 연체일 추가된거
	   public RentalVO(String rentalNumber, String memId, String bookId, String overdueCheck, String rentalReturnCheck,
			   Date rentalDate, Date rentalReturnSchedule, Date rentalReturnDate, String bookTitle, int overDay) {
		   this.rentalNumber = rentalNumber;
		   this.memId = memId;
		   this.bookId = bookId;
		   this.overdueCheck = overdueCheck;
		   this.rentalReturnCheck = rentalReturnCheck;
		   this.rentalDate = rentalDate;
		   this.rentalReturnSchedule = rentalReturnSchedule;
		   this.rentalReturnDate = rentalReturnDate;
		   this.bookTitle = bookTitle;
		   this.overDay = overDay;
	   }
//	   public RentalVO(String name2, String rentalNumber2, Date rentalDate2, Date rentalReturnSchedule2,
//			   String overdueCheck2, String memId2, Date rentalReturnDate2, String bookId2, String bookName,
//			   String rentalReturnCheck2, int overDay2) {
//		   // TODO Auto-generated constructor stub
//	   }
	   //select에서 연체일과 회원이름 추가된거
	   public RentalVO(String name, String rentalNumber, Date rentalDate, Date rentalReturnSchedule, String overdueCheck, String memId,
			   Date rentalReturnDate, String bookId, String bookTitle, String rentalReturnCheck, int overDay) {
		   this.name = name;
		   this.rentalNumber = rentalNumber;
		   this.memId = memId;
		   this.bookId = bookId;
		   this.overdueCheck = overdueCheck;
		   this.rentalReturnCheck = rentalReturnCheck;
		   this.rentalDate = rentalDate;
		   this.rentalReturnSchedule = rentalReturnSchedule;
		   this.rentalReturnDate = rentalReturnDate;
		   this.bookTitle = bookTitle;
		   this.overDay = overDay;
	   }
	   
	   // insert 용
	
	public RentalVO(String rentalNumber, String memId, String bookTitle) {
		this.rentalNumber = rentalNumber;
		this.memId = memId;
		this.bookTitle = bookTitle;
		
	} 

	// update 용 (반납, 연장)
	public RentalVO(String memId, String bookTitle) {
		this.memId = memId;
		this.bookTitle = bookTitle;
	}   
	
	public String getRentalNumber() {
		return rentalNumber;
	}


	public void setRentalNumber(String rentalNumber) {
		this.rentalNumber = rentalNumber;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getOverdueCheck() {
		return overdueCheck;
	}

	public void setOverdueCheck(String overdueCheck) {
		this.overdueCheck = overdueCheck;
	}

	public String getRentalReturnCheck() {
		return rentalReturnCheck;
	}

	public void setRentalReturnCheck(String rentalReturnCheck) {
		this.rentalReturnCheck = rentalReturnCheck;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getRentalReturnSchedule() {
		return rentalReturnSchedule;
	}

	public void setRentalReturnSchedule(Date rentalReturnSchedule) {
		this.rentalReturnSchedule = rentalReturnSchedule;
	}

	public Date getRentalReturnDate() {
		return rentalReturnDate;
	}

	public void setRentalReturnDate(Date rentalReturnDate) {
		this.rentalReturnDate = rentalReturnDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public int getOverDay() {
		return overDay;
	}
	public void setOverDay(int overDay) {
		this.overDay = overDay;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMileage() {
		return Mileage;
	}
	public void setMileage(int mileage) {
		Mileage = mileage;
	}
	public int getMoney() {
		return Money;
	}
	public void setMoney(int money) {
		Money = money;
	}
	@Override
	public String toString() {	
		
		return String.format("  %s %11s %15s %15s %4s %15tF %6s %6s %7d일 %7d원 %15tF %s ",name ,rentalNumber,memId,bookId, rentalReturnCheck, rentalDate, rentalReturnSchedule, overdueCheck,overDay,overDay*100, rentalReturnDate,bookTitle);
	}
	   
	
}
