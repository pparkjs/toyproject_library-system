package view;

import java.util.List;
import java.util.Scanner;

import controller.MemController;
import vo.BoardVO;

public class BoardView {
	public void printBoards(List<BoardVO> list) {
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t번호  제목\t\t\t\t작성자\t\t작성일\t\t\t수정일");
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for (BoardVO vo : list) {
			System.out.println("\t " + vo.getNum() +"    "+ vo.getTitle() +"\t\t"+ vo.getMemId() 
			+"\t"+ vo.getRegisterDate() +"\t"+vo.getModifyDate());
		}
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
	}
	
	//공지사항용
	public void printNoticeBoards(List<BoardVO> list) {
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t번호  제목\t\t\t\t작성자\t\t작성일\t\t\t수정일");
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		for (BoardVO vo : list) {
			System.out.println("\t " + vo.getNum() +"    "+ vo.getTitle() +"\t\t"+ "관리자" 
			+"\t"+ vo.getRegisterDate() +"\t"+vo.getModifyDate());
		}
		System.out.println(" ─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
	}
	

	public BoardVO inputInsertBoard(Scanner scanner) {
		System.out.print("\t제목: ");
		String title = scanner.nextLine();
		System.out.print("\t내용: ");
		String content = scanner.nextLine();

		return new BoardVO(title, content);
	}

	public void insertResult(int count) {
		if (count > 0) {
			System.out.println();
			System.out.println("\t< 게시글이 정상적으로 등록되었습니다. >");
			System.out.println();
		} else {
			System.out.println("\t< 게시글이 등록되지 않았습니다. 관리자에게 문의하세요. >");
		}
	}
	//관리자용
	public void insertNoticeResult(int count) {
		if (count > 0) {
			System.out.println();
			System.out.println("\t< 게시글이 정상적으로 등록되었습니다. >");
			System.out.println();
		} else {
			System.out.println("\t< 게시글이 등록되지 않았습니다. >");
		}
	}

	public int inputSearchNum(Scanner scanner) {
		System.out.print("\t수정할 번호를 선택하세요> ");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public BoardVO printUpBoard(BoardVO board) {
		if (board != null) {
			return board;
		} else {
			System.out.println();
			System.out.println("\t< 회원의 게시글이 아니므로 수정할 수 없습니다. 다시 선택하세요.>");
			System.out.println();
			return board;
		}
	}
	//공지사항용
	public BoardVO printUpNoticeBoard(BoardVO board) {
		if (board != null) {
			return board;
		} else {
			System.out.println();
			System.out.println("\t< 해당 번호가 존재하지 않습니다. 다시 선택하세요. >");
			System.out.println();
			return board;
		}
	}
	
	public BoardVO printDelBoard(BoardVO board) {
		if (board != null) {
			return board;
		} else {
			System.out.println();
			System.out.println("\t< 회원의 게시글이 아니므로 삭제할 수 없습니다. 다시 선택하세요.>");
			System.out.println();
			return board;
		}
	}

	public void printUpdateBoard(BoardVO board) {
		if (board != null) {
			System.out.println("수정할 게시글 : " + board);
			System.out.println("수정");
		} else {
			System.out.println("해당 제목의 게시글이 존재하지 않습니다.");
		}
	}

	public BoardVO updateBoard(BoardVO board, Scanner scanner) {
		boolean run = true;
		while (run) {
			System.out.println();
			System.out.println("\t1. 제목 2. 본문 3. 뒤로가기");
			System.out.println();
			System.out.print("\t수정할 부분을 선택하세요>");
			int select = Integer.parseInt(scanner.nextLine());
			System.out.println();

			switch (select) {
			case 1:
				System.out.println("\t수정하실 제목을 입력하세요.");
				System.out.print("\t> ");
				String title = scanner.nextLine();
				board.setTitle(title);
				System.out.println();
				System.out.println("\t< 제목이 정상적으로 수정되었습니다. >");
				break;
			case 2:
				System.out.println("\t수정하실 내용을 입력하세요.");
				System.out.print("\t> ");
				String content = scanner.nextLine();
				board.setContent(content);
				System.out.println();
				System.out.println("\t< 본문이 정상적으로 수정되었습니다. >");
				break;
			case 3:
				run = false;
				break;
			default:
				System.out.println("잘못된 접근입니다. 다시 입력해주세요.");
				break;
			}
			System.out.println();
		}
		return board;
	}


	public void updateResult(int count) {
		if (count > 0) {
			System.out.println("\t< 게시글이 정상적으로 수정되었습니다. >");
		} else {
			System.out.println("\t게시글이 수정되지 않았습니다. 관리자에게 문의하세요.");
		}
	}
	//공지사항용
	public void updateNoticeResult(int count) {
		if (count > 0) {
			System.out.println("\t< 게시글이 정상적으로 수정되었습니다. >");
		} else {
			System.out.println("\t게시글이 수정되지 않았습니다.");
		}
	}

	public int deletePrintBoard(Scanner scanner) {
		System.out.println("\t정말로 삭제하시겠습니까? y/n");
		String response = scanner.nextLine();
		switch (response) {
		case "y":
			System.out.print("\t삭제할 번호를 선택하세요> ");
			return Integer.parseInt(scanner.nextLine());
		case "n":
			break;
		default:
			break;
		}
		return 0;
	}

	public void deleteResult(int count) {
		if (count > 0) {
			System.out.println();
			System.out.println("\t< 게시글이 정상적으로 삭제되었습니다. >");
			System.out.println();
		} else {
			System.out.println("\t< 게시글이 삭제되지 않았습니다. 관리자에게 문의하세요. >");

		}
	}
	
	public void deleteNoticeResult(int count) {
		if (count > 0) {
			System.out.println("\t< 게시글이 정상적으로 삭제되었습니다. >");
			System.out.println();
		} else {
			System.out.println("\t< 게시글이 삭제되지 않았습니다. 다시 시도하세요. >");

		}
	}
	
	public int reqNumSearch(Scanner scanner) {
		System.out.print("\t원하는 게시글의 번호를 선택하세요> ");
		int num = Integer.parseInt(scanner.nextLine());
		return num;
	}

	public void datailPrint(Scanner scanner, BoardVO vo) {
		if (vo != null) {
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t번호 : " + vo.getNum() + " 제목 : " + vo.getTitle() + " 작성자 : " + vo.getMemId() + " 작성일 : " 
					+ vo.getRegisterDate() + " 수정일 : " +  vo.getModifyDate());
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t" + vo.getContent());
			System.out.println();
			System.out.println("\t 1.목록보기");
			int back = Integer.parseInt(scanner.nextLine());
			switch (back) {
			case 1:
				break;

			default:
				break;
			}
		} else {
			System.out.println("\t 해당 게시글이 존재하지 않습니다.");
		}
		
	}
	//관리자 마일리지부여용
	public String datailMilegePrint(Scanner scanner, BoardVO vo, MemController mem) {
		if (vo != null) {
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t번호 : " + vo.getNum() + " 제목 : " + vo.getTitle() + " 작성자 : " + vo.getMemId() + " 작성일 : " 
					+ vo.getRegisterDate() + " 수정일 : " +  vo.getModifyDate());
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t" + vo.getContent());
			System.out.println();
			while (true) {
				System.out.println("\t 1.마일리지 부여하기 2.뒤로가기");
				int back = Integer.parseInt(scanner.nextLine());
				switch (back) {
				case 1:
					return vo.getMemId();
				case 2:
					return null;
				default:
					break;
				}
			}
		} else {
			System.out.println("\t 해당 게시글이 존재하지 않습니다.");
		}
		return null;
		
	}
	//공지사항용
	public void datailNoticePrint(Scanner scanner, BoardVO vo) {
		if (vo != null) {
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t번호 : " + vo.getNum() + " 제목 : " + vo.getTitle() + " 작성자 : 관리자 작성일 : " 
					+ vo.getRegisterDate() + " 수정일 : " +  vo.getModifyDate());
			System.out.println(" ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t" + vo.getContent());
			System.out.println();
			System.out.println("\t 1.목록보기");
			int back = Integer.parseInt(scanner.nextLine());
			switch (back) {
			case 1:
				break;

			default:
				break;
			}
		} else {
			System.out.println("\t 해당 게시글이 존재하지 않습니다.");
		}
		
	}

	public void mileageInsertResult(int count) {
		if (count > 0) {
			System.out.println();
			System.out.println("\t< 50 point 가 정상적으로 부여 되었습니다. >");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("\t< 마일리지 부여 실패 >");
			System.out.println();
		}
	}
}
