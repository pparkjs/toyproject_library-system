package controller;

import java.util.List;
import java.util.Scanner;

import model.BoardService;
import vo.BoardVO;
import vo.MemVO;
import view.BoardView;

public class BoardController {
	private BoardService service = new BoardService();
	private BoardView view = new BoardView();

	//요청게시판 전체검색
	public void selectReqBoards() throws Exception {
		List<BoardVO> list = service.selectReqBoards();
		view.printBoards(list);
	}

	//요청게시판 등록
	public void insertReqBoard(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId();
		BoardVO vo = view.inputInsertBoard(scanner);
		int insertReqBoard = service.insertReqBoard(vo, memId);
		view.insertResult(insertReqBoard);
	}

	//해당 회원의 요청게시판 수정하는 메서드
	public void updateReqBoard(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId(); // 회원아이디

		while (true) {
			int num = view.inputSearchNum(scanner); //수정할 게시글 
			BoardVO vo = service.selectReqBoard(num, memId);
			BoardVO board = view.printUpBoard(vo);
			if (board != null) {
				BoardVO updateBoard = view.updateBoard(board, scanner);
				int count = service.updateReqBoard(updateBoard, num);
				view.updateResult(count);
				break;
			}
		} 

	}

	//요청게시판 게시글삭제
	public void deleteReqBoard(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId();
		while(true) {
			int num = view.deletePrintBoard(scanner);//삭제할 게시글
			if (num != 0) {
				BoardVO vo = service.selectReqBoard(num, memId);//회원의 게시글만 받음
				BoardVO board = view.printDelBoard(vo);
				if(board != null) {
					int deleteBoard = service.deleteReqBoard(num);
					view.deleteResult(deleteBoard);		
					break;
				}
			} else {
				return;
			}
		}
	}

	//요청게시판 게시글 선택 메서드
	public void detailReqBoard(Scanner scanner) throws Exception {
		int reqNum = view.reqNumSearch(scanner);
		BoardVO board = service.detailReqBoard(reqNum);
		view.datailPrint(scanner, board);

	}
	//	 -----------------------------------------------------------------------------------------------
	//독후감게시판 전체검색
	public void selectBookReports() throws Exception {
		List<BoardVO> list = service.selectBookReports();
		view.printBoards(list);
	}

	//독후감게시판 등록
	public void insertBookReport(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId();
		BoardVO vo = view.inputInsertBoard(scanner);
		int insertBookReport = service.insertBookReport(vo, memId);
		view.insertResult(insertBookReport);
	}

	//해당 회원의 독후감게시판 수정하는 메서드
	public void updateBookReport(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId(); // 회원아이디

		while (true) {
			int num = view.inputSearchNum(scanner); //수정할 게시글 
			BoardVO vo = service.selectBookReport(num, memId);
			BoardVO board = view.printUpBoard(vo);
			if (board != null) {
				BoardVO updateBoard = view.updateBoard(board, scanner);
				int count = service.updateBookReport(updateBoard, num);
				view.updateResult(count);
				break;
			}
		} 

	}

	//독후감게시판 게시글삭제
	public void deleteBookReport(Scanner scanner, MemVO mem) throws Exception {
		String memId = mem.getMemId();
		while(true) {
			int num = view.deletePrintBoard(scanner);//삭제할 게시글
			if (num != 0) {
				BoardVO vo = service.selectBookReport(num, memId);//회원의 게시글만 받음
				BoardVO board = view.printDelBoard(vo);
				if(board != null) {
					int deleteBoard = service.deleteBookReport(num);
					view.deleteResult(deleteBoard);		
					break;
				}
			} else {
				return;
			}
		}
	}

	//독후감게시판 게시글 선택 메서드
	public void detailBookReport(Scanner scanner) throws Exception {
		int reqNum = view.reqNumSearch(scanner);
		BoardVO board = service.detailBookReport(reqNum);
		view.datailPrint(scanner, board);

	}
	//		------------------------------------------------------------------------------------

	//		------------------------------------------------------------------------------------
	//비회원요청게시판 전체검색
	public void selectNonReqBoards() throws Exception {
		List<BoardVO> list = service.selectReqBoards();
		view.printBoards(list);
	}

	//비회원요청게시판 게시글 선택 메서드
	public void detailNonReqBoard(Scanner scanner) throws Exception {
		int reqNum = view.reqNumSearch(scanner);
		BoardVO board = service.detailReqBoard(reqNum);
		view.datailPrint(scanner, board);

	}
	//		-----------------------------------------------------------------------------------
	//관리자 요청게시판 게시글삭제
	public void deleteAuthReqBoard(Scanner scanner) throws Exception {
		int num = view.deletePrintBoard(scanner);//삭제할 게시글
		if (num != 0) {
			int deleteBoard = service.deleteReqBoard(num);
			view.deleteResult(deleteBoard);		
		} else {
			return;
		}

	}

	//관리자 독후감게시판 게시글삭제
	public void deleteAuthBookReport(Scanner scanner) throws Exception {
		int num = view.deletePrintBoard(scanner);//삭제할 게시글
		if (num != 0) {
			int deleteBoard = service.deleteBookReport(num);
			view.deleteResult(deleteBoard);		
		} else {
			return;
		}
	}

	//관리자 공지사항게시판 전체 조회
	public void selectNoticeBoards() throws Exception {
		List<BoardVO> list = service.selectNoticeBoards();
		view.printNoticeBoards(list);
	}

	//공지사항게시판 게시글 선택 메서드
	public void detailNoticeBoard(Scanner scanner) throws Exception {
		int reqNum = view.reqNumSearch(scanner);
		BoardVO board = service.detailNoticeBoard(reqNum);
		view.datailNoticePrint(scanner, board);

	}
	//공지사항게시판 등록
	public void insertNoticeBoard(Scanner scanner) throws Exception {
		BoardVO vo = view.inputInsertBoard(scanner);
		int insertNoticeBoard = service.insertNoticeBoard(vo);
		view.insertResult(insertNoticeBoard);
	}

	//공지사항게시판 수정하는 메서드
	public void updateNoticeBoard(Scanner scanner) throws Exception {
		while (true) {
			int num = view.inputSearchNum(scanner); //수정할 게시글 
			BoardVO vo = service.selectNoticeBoard(num);
			BoardVO board = view.printUpNoticeBoard(vo);
			if (board != null) {
				BoardVO updateBoard = view.updateBoard(board, scanner);
				int count = service.updateNoticeBoard(updateBoard, num);
				view.updateNoticeResult(count);
				break;
			}
		} 
	}
	
	//공지사항 독후감게시판 게시글삭제
	public void deleteNoticeBoard(Scanner scanner) throws Exception {
		int num = view.deletePrintBoard(scanner);//삭제할 게시글
		if (num != 0) {
			int deleteBoard = service.deleteNoticeBoard(num);
			view.deleteNoticeResult(deleteBoard);		
		} else {
			return;
		}
	}
	
	//마일리지 독후감게시판 게시글 선택 메서드
	public void detailMilegeBookReport(Scanner scanner, MemController mem) throws Exception {
		int reqNum = view.reqNumSearch(scanner);
		BoardVO board = service.detailBookReport(reqNum);
		String memId = view.datailMilegePrint(scanner, board, mem);
		if(memId != null) {
			int count = service.memReportMilegeInsert(memId);
			view.mileageInsertResult(count);
		}

	}
}
