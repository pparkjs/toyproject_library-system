package controller;

import java.util.List;
import java.util.Scanner;

import model.BookService;
import view.BookView;
import vo.BookVO;
import vo.MemVO;

public class BookController {

	private BookService service = new BookService();
	private BookView view = new BookView();

	//전체 책목록 조회
	public void selectbooks() throws Exception{
		List<BookVO> list = service.selectbooks();
		view.printBooks(list);
	}

	// 책 등록

	public void insertBook(Scanner scanner) throws Exception{
		BookVO vo = view.inputInsertBook(scanner);
		int insertBook = service.insertBook(vo);
		view.insertResult(insertBook);
	}

	// 책 이름으로 검색
	public void searchBookName(Scanner scanner, RentalController rental, MemVO memInfor, BookController book) throws Exception {
		String bookNameSearch = view.bookNameSearch(scanner);
		List<BookVO> list = service.searchBookName(bookNameSearch);
		view.bookSearch(list,scanner,rental, memInfor, book);

	}

	// 저자 이름으로 검색
	public void searchAuthorName(Scanner scanner, RentalController rental, MemVO memInfor, BookController book) throws Exception {
		String authorNameSearch = view.authorNameSearch(scanner);
		List<BookVO> list = service.searchAuthorName(authorNameSearch);
		view.bookSearch(list,scanner,rental, memInfor, book);

	}	

	// 분야명으로 책 검색
	public void searchGenreName(Scanner scanner, RentalController rental, MemVO memInfor,BookController book) throws Exception {
		String genreNameSearch = view.genreNameSearch(scanner);
		List<BookVO> list = service.searchGenreName(genreNameSearch);
		view.bookSearch(list,scanner,rental, memInfor,book);
	}

	// 비회원책 이름으로 검색
	public void searchNonBookName(Scanner scanner) throws Exception {
		String bookNameSearch = view.bookNameSearch(scanner);
		List<BookVO> list = service.searchBookName(bookNameSearch);
		view.bookNonSearch(list,scanner);

	}

	// 비회원저자 이름으로 검색
	public void searchNonAuthorName(Scanner scanner) throws Exception {
		String authorNameSearch = view.authorNameSearch(scanner);
		List<BookVO> list = service.searchAuthorName(authorNameSearch);
		view.bookNonSearch(list,scanner);

	}	

	// 비회원분야명으로 책 검색
	public void searchNonGenreName(Scanner scanner) throws Exception {
		String genreNameSearch = view.genreNameSearch(scanner);
		List<BookVO> list = service.searchGenreName(genreNameSearch);
		view.bookNonSearch(list,scanner);
	}

	// 책 삭제
	public void deleteBook(Scanner scanner) throws Exception {
		String deleteBook = view.deleteBook(scanner);
		int count = service.deleteBook(deleteBook);
		view.deleteResult(count);
	}
	
	public BookVO searchBookNameQty(String bookName) throws Exception {
		BookVO check = service.searchBookNameQty(bookName);
		return check;
		
	}

}
