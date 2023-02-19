package controller;

import java.util.Scanner;

import view.AuthView;
import vo.AuthVO;
import vo.MemVO;

public class AuthController {
	private AuthView view = new AuthView();
	private AuthVO vo = new AuthVO();
	//관리자로그인 하는 메서드
	public boolean AuthLogin(Scanner scanner, AuthVO vo) throws Exception {
		view.loginPrint();
		//관리자 로그인 성공시 true
		if(view.inputLogin(scanner, vo)) {
			return true;
		}else {
			//뒤로갈시 false
			return false;
		}
	}
	
	//관리자 로그인 환경
	public void AuthLoginWindow(Scanner scanner, MemController mem, BoardController board, BookController book, RentalController rental) throws Exception {
		if(AuthLogin(scanner, vo)) {
			while (true) {
				view.authWindowPrint();			
				int select = Integer.parseInt(scanner.nextLine());
				switch (select) {
				case 1:// 도서 관리
					bookManagement(scanner, book);
					break;
				case 2://회원관리
					memManagement(scanner, rental, mem);
					break;
				case 3://요청 게시판
					reqBoard(scanner, board);
					break;
				case 4://독후감 게시판
					bookReport(scanner, board, mem);
					break;
				case 5://공지사항 게시판
					noticeBoard(scanner, board);
					break;
				case 6://로그아웃
					view.logoutPrint();
					return;

				default:
					break;
				}
			}
			
		}
	}
	
	public void memManagement(Scanner scanner, RentalController rental, MemController mem) throws Exception {
		while(true) {
			int select = view.memPrint(scanner);
			switch (select) {
			case 1://회원정보
				mem.selectMembers(scanner);
				break;
			case 2://회원대여목록
				rental.selectRentals(scanner);
				break;
			case 3://뒤로가기
				return;
			default:
				break;
			}
		}
	}

	public void bookManagement(Scanner scanner, BookController book) throws Exception {
		while(true) {
			int select = view.BookSelPrint(scanner);
			check:
			switch (select) {
			case 1://도서전체조회
				book.selectbooks();
				break ;
			case 2://도서상세검색
				while(true) {
					int sel = view.BookDetailPrint(scanner);
					switch (sel) {
					case 1://도서명 검색
						book.searchNonBookName(scanner);
						break;
					case 2://저자명 검색
						book.searchNonAuthorName(scanner);
						break;
					case 3://장르 검색
						book.searchNonGenreName(scanner);
						break;
					case 4://뒤로가기
						break check;
					default:
						break;
					}
				}
			case 3://도서추가
				book.insertBook(scanner);
				break;
			case 4://도서삭제
				book.deleteBook(scanner);
				break;
			case 5://뒤로가기
				return;
			default:
				break;
			}
		}
	}

	//요청게시판
		public void reqBoard (Scanner scanner, BoardController board) throws Exception {
			while(true) {
				board.selectReqBoards();
				int select = view.reqSelPrint(scanner);
				switch (select) {
				case 1://요청게시글 선택
					board.detailReqBoard(scanner);
					break ;
				case 2://요청게시글 삭제하기
					board.deleteAuthReqBoard(scanner);
					break;
				case 3://뒤로가기
					return;
				default:
					break;
				}
			}
			
		}
		//독후감게시판
		private void bookReport(Scanner scanner, BoardController board, MemController mem) throws Exception {
			while(true) {
				board.selectBookReports();
				int select = view.reqSelPrint(scanner);
				switch (select) {
				case 1://독후감게시글 선택
					board.detailMilegeBookReport(scanner, mem);
					break ;
				case 2://독후감게시글 삭제하기
					board.deleteAuthBookReport(scanner);
					break;
				case 3://뒤로가기
					return;
				default:
					break;
				}
			}
		}
		//공지사항게시판
		private void noticeBoard(Scanner scanner, BoardController board) throws Exception {
			while(true) {
				board.selectNoticeBoards();
				int select = view.noticeSelPrint(scanner);
				switch (select) {
				case 1://공지사항게시글 선택
					board.detailNoticeBoard(scanner);
					break ;
				case 2://공지사항게시글 등록하기
					board.insertNoticeBoard(scanner);
					break;
				case 3://공지사항게시글 수정하기
					board.updateNoticeBoard(scanner);
					break;
				case 4://공지사항게시글 삭제하기
					board.deleteNoticeBoard(scanner);
					break;
				case 5://뒤로가기
					return;
				default:
					break;
				}
			}
		}

}
