package controller;

import java.util.Scanner;

import vo.MemVO;
import view.NonView;

public class NonController {
	NonView view = new NonView();
	
	public void NonLoginWindow(Scanner scanner, BoardController board, BookController book, RentalController rental) throws Exception {
		while (true) {
			view.memWindowPrint();
			int select = Integer.parseInt(scanner.nextLine());
			switch (select) {
			case 1:// 도서 검색
				books(scanner,book,rental);
				break;
			case 2://요청게시판
				reqBoard(scanner, board);
				break;
			case 3://독후감 게시판
				bookReport(scanner, board);
				break;
			case 4://공지사항 게시판
				noticeBoard(scanner, board);
				break;
			case 5://뒤로가기
				return;

			default:
				break;
			}
		}
	}
	
	public void books(Scanner scanner, BookController book, RentalController rental) throws Exception {
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

			case 3://뒤로가기
				return;
			default:
				break;
			}
		}
	}
	//비회원요청게시판
	public void reqBoard (Scanner scanner, BoardController boardC) throws Exception {
		while(true) {
			boardC.selectReqBoards();
			int select = view.reqSelPrint(scanner);
			switch (select) {
			case 1://요청게시글 선택
				boardC.detailReqBoard(scanner);
				break ;
			case 2://뒤로가기
				return;
			default:
				break;
			}
		}
		
	}
	
	//비회원독후감게시판
	public void bookReport (Scanner scanner, BoardController boardC) throws Exception {
		while(true) {
			boardC.selectBookReports();
			int select = view.reqSelPrint(scanner);
			switch (select) {
			case 1://요청게시글 선택
				boardC.detailBookReport(scanner);
				break ;
			case 2://뒤로가기
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
			case 2://뒤로가기
				return;
			default:
				break;
			}
		}
	}
}
