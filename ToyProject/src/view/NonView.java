package view;

import java.util.Scanner;

import vo.MemVO;

public class NonView {
	public void memWindowPrint() {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                    🏫 대덕 도서관 🏫                  ");
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                                                        ");
		System.out.println("                🔍 1. 도서 검색                         ");
		System.out.println("                📪 2. 요청 게시판                        ");
		System.out.println("                📝 3. 독후감 게시판                      ");
		System.out.println("                📢 4. 공지사항 게시판                    ");
		System.out.println("                   5. 뒤로가기                           ");
		System.out.println("                                                        ");
		System.out.println("   ────────────────────────────────────────────────────");
	}
	public int reqSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글 선택 2. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int noticeSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글 선택 2.뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서전체조회 2. 도서상세검색 3. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookDetailPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서명 검색 2. 저자명 검색 3. 장르 검색 4. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
}
