package controller;

import java.util.List;
import java.util.Scanner;

import model.MemService;
import vo.BookVO;
import vo.MemVO;
import view.MemView;

public class MemController {
	private MemService service = new MemService();
	private MemView view = new MemView();
	//회원가입 하는 메서드
	public void memJoin(Scanner scanner) throws Exception {
		view.memJoinPrint();
		while(true){
			String id = view.printIdCheck(scanner);
			boolean idCheck = service.idCheck(id);
			//id중복이 아닌경우
			if(idCheck) {
				MemVO mem = view.insertMemInfor(id, scanner);
				int count = service.insertMemInfor(mem);
				boolean inforInsertResult = view.inforInsertResult(count);
				if(inforInsertResult) {
					break;
				}

			}else {
				view.printRepeat();
			}

		}
	}

	//로그인 하는 메서드
	public MemVO memLogin(Scanner scanner) throws Exception {
		view.loginPrint();
		while(true) {
			MemVO vo = view.inputLogin(scanner);
			String id = vo.getMemId();
			String pw = vo.getMemPw();
			MemVO login = service.idPwSelect(id, pw);
			MemVO loginResult = view.loginResult(login);
			if (loginResult != null) {
				return login;				
			}else {
				view.loginPrint2();
				int back = Integer.parseInt(scanner.nextLine());
				
				switch (back) {
				case 1:
					break;
				case 2:
					return login;
				default:
					break;
				}
			}
			
		}
	}
	
	//초기 시작 화면 뜨는 메서드
	public int start(Scanner scanner) {
		view.startPrint();
		return Integer.parseInt(scanner.nextLine());
	}
	
	// 회원의 목록 선택후 실행할 메소드
	public void memWindoWSelect(Scanner scanner, MemVO memInfor, BoardController board, BookController book, RentalController rental) throws Exception {
		while (true) {
			view.memWindowPrint(memInfor);
			int select = Integer.parseInt(scanner.nextLine());
			switch (select) {
			case 1:// 도서
				books(scanner,book,rental,memInfor);
				break;
			case 2://마이페이지
				MemVO vo = view.myPagePrint(memInfor, scanner, rental);
				if (vo != null) {
					service.memInforUpdate(vo);
				}
				System.out.println();
				break;
			case 3://요청 게시판
				reqBoard(scanner, memInfor, board);
				break;
			case 4://독후감 게시판
				bookReport(scanner, memInfor, board);
				break;
			case 5://공지사항 게시판
				noticeBoard(scanner, board);
				break;
			case 6://로그아웃
				view.logoutPrint(memInfor);
				return;

			default:
				break;
			}
		}
	}
	
	public void books(Scanner scanner, BookController book, RentalController rental, MemVO memInfor) throws Exception {
		while(true) {
			int select = view.BookSelPrint(scanner);
			check:
			switch (select) {
			case 1://도서전체조회
				book.selectbooks();
				break ;
			case 2://도서상세검색
				flag:
				while(true) {
					int sel = view.BookDetailPrint(scanner);
					switch (sel) {
					case 1://도서명 검색
						book.searchBookName(scanner, rental, memInfor, book);
						break;
					case 2://저자명 검색
						book.searchAuthorName(scanner, rental, memInfor, book);
						break;
					case 3://장르 검색
						book.searchGenreName(scanner, rental, memInfor, book);
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

	//요청게시판
	public void reqBoard (Scanner scanner, MemVO mem, BoardController boardC) throws Exception {
		while(true) {
			boardC.selectReqBoards();
			int select = view.reqSelPrint(scanner);
			switch (select) {
			case 1://요청게시글 선택
				boardC.detailReqBoard(scanner);
				break ;
			case 2://요청게시판 등록
				boardC.insertReqBoard(scanner, mem);
				break ;
			case 3://요청게시글 수정하기
				boardC.updateReqBoard(scanner, mem);
				break;
			case 4://요청게시글 삭제하기
				boardC.deleteReqBoard(scanner, mem);
				break;
			case 5://뒤로가기
				return;
			default:
				break;
			}
		}
		
	}
	//독후감게시판
	private void bookReport(Scanner scanner, MemVO mem, BoardController boardC) throws Exception {
		while(true) {
			boardC.selectBookReports();
			int select = view.reqSelPrint(scanner);
			switch (select) {
			case 1://독후감게시글 선택
				boardC.detailBookReport(scanner);
				break ;
			case 2://독후감게시판 등록
				boardC.insertBookReport(scanner, mem);
				break ;
			case 3://독후감게시글 수정하기
				boardC.updateBookReport(scanner, mem);
				break;
			case 4://독후감게시글 삭제하기
				boardC.deleteBookReport(scanner, mem);
				break;
			case 5://뒤로가기
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
	
	//전체 회원목록목록 조회
		public void selectMembers(Scanner scanner) throws Exception{
			List<MemVO> list = service.selectMembers();
			view.printMembers(list, scanner);
		}
	
}
