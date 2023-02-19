package model;

import java.util.List;

import vo.BookVO;

public class BookService {
	
	private BookDAO dao = new BookDAO();
	
	// 책 목록조회
	public List<BookVO> selectbooks() throws Exception{
		return dao.selectbooks();
	}
	
	// 책 등록
	
	public int insertBook(BookVO vo) throws Exception{
		return dao.insertBook(vo);
	}
	// 없는책 걸러내기위함
	public BookVO searchBookNameQty(String name) throws Exception {
		return dao.searchBookNameQty(name);
	}
	
	// 책 삭제

	public int deleteBook(String deleteBook) throws Exception {
		return dao.deleteBook(deleteBook);
	}
	
	// 책 이름으로 검색
	public List<BookVO> searchBookName(String name) throws Exception {
		return dao.searchBookName(name);
	}
	
	// 저자 이름으로 검색
	public List<BookVO> searchAuthorName(String authorName) throws Exception {
		return dao.searchAuthorName(authorName);
	}
	
	 // 분야로 책 검색
	public List<BookVO> searchGenreName(String genreName) throws Exception {
		return dao.searchGenreName(genreName);
	}

}
