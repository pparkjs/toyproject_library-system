package controller;


import java.util.List;
import java.util.Scanner;

import model.RentalService;
import view.RentalView;
import vo.BookVO;
import vo.MemVO;
import vo.RentalVO;

public class RentalController {
	
	private RentalService service = new RentalService();
	private RentalView view = new RentalView();
	
	
	// 관리자용 대여 조회
	public void selectRentals(Scanner scanner) throws Exception{
		List<RentalVO> list = service.selectRentals();
		view.printRentals(list, scanner);
	}
	
	// 회원용 대여 조회
	public void selectRental(Scanner scanner, MemVO memInfor) throws Exception{
		String memId = memInfor.getMemId();
		String memName = memInfor.getMemName();
		List<RentalVO> list = service.selectRental(memId);
		view.printRental(list, memName);
	}
	
	
	// 대여 등록
	public void insertRental(Scanner scanner, MemVO memInfor, BookController book) throws Exception{
		String memId = memInfor.getMemId();
		RentalVO vo = view.inputInsertRental(scanner, memId);
		String bookTitle = vo.getBookTitle();
		BookVO check = book.searchBookNameQty(bookTitle);
		if(check != null) {
			int insert = service.insertRental(vo);
			view.rentalInsertResult(insert);
		}else {
			view.NonBookPrint();
		}
	}
	
	// 책 반납
	public void returnBook(Scanner scanner, MemVO memInfor) throws Exception {
		String memId = memInfor.getMemId();
		RentalVO vo = view.rentalReturnBook(scanner, memId);
		int returnBook = service.returnBook(vo);
		view.rentalReturnResult(returnBook);
	}

	// 책 연장
	public void extentionRental(Scanner scanner, MemVO memInfor) throws Exception {
		String memId = memInfor.getMemId();
		RentalVO vo = view.rentalExtentionReturn(scanner, memId);
		int returnExtention = service.extentionRental(vo);
		view.rentalExtentionResult(returnExtention);
	}		
	
	//--------------------------------------------------------------------------------------------
	// 대여 수단 선택 돈으로 
	public int selectMoney(MemVO memInfor) throws Exception {
		int memMoney = memInfor.getMemMoney();
		int memMileage = memInfor.getMemMileage();
		String memId = memInfor.getMemId();
		
		RentalVO vo = view.SelectMoneyInputId(memMoney,memMileage,memId);
		int rentalMethod = service.selectMoney(vo);
		int count = view.bookRenatalWithMoney(rentalMethod);
		return count;
	}
	
	// 대여 수단 선택 마일리지로 
	public int selectMileage(MemVO memInfor) throws Exception {
		String memId = memInfor.getMemId();
		RentalVO vo = view.SelectMileageInputId(memId);
		int rentalMethod = service.selectMileage(vo);
		int count = view.bookRenatalWithMileage(rentalMethod); 
		return count;
		
	}

}
