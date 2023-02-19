package view;

import java.util.Scanner;

import vo.AuthVO;
import vo.MemVO;

public class AuthView {
	
	public void authWindowPrint() {
		System.out.println(" ────────────────────────────────────────────────────");
		System.out.println("                      관 리 자                       ");
		System.out.println(" ────────────────────────────────────────────────────");
		System.out.println("                                                     ");
		System.out.println("              🔍 1. 도서 관리                         ");
		System.out.println("              🏷️ 2. 회원 관리                        ");
		System.out.println("              📪 3. 요청 게시판                     ");
		System.out.println("              📝 4. 독후감 게시판                    ");
		System.out.println("              📢 5. 공지사항 게시판                  ");
		System.out.println("                                                     ");
		System.out.println("                 6. 로그아웃                         ");
		System.out.println("                                                     ");
		System.out.println(" ────────────────────────────────────────────────────");
	}
	
	public void loginPrint() {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                       🏫 로그인 🏫                ");
		System.out.println("   ────────────────────────────────────────────────────");
	}
	
	public boolean inputLogin(Scanner scanner, AuthVO vo) {
		while (true) {
			System.out.print("           🔒 ID : ");
			String id = scanner.nextLine();
			System.out.println();
			System.out.print("           🔑 PW : ");
			String pw = scanner.nextLine();
			System.out.println();
			if (id.equals(vo.getId())) {
				if (pw.equals(vo.getPw())) {
					System.out.println("\t < 관리자계정 접속완료 >");
					return true;
				}
			} else {
				System.out.println("  < 아이디 또는 비밀번호가 잘못 되었습니다. 다시 입력해주세요. >");
				loginPrint2();
				int back = Integer.parseInt(scanner.nextLine());
				switch (back) {
				case 1://로그인이어서
					break;
				case 2://뒤로가기
					return false;
				default:
					break;
				}
			}
		
		}
	}
	
	public void loginPrint2() {
		System.out.println();
	    System.out.println("               📖  1. 로그인 2. 뒤로가기  📖            ");
	}
	
	public void logoutPrint() {
		System.out.println("\t\t 관리자님이 로그아웃 하셨습니다.");
		System.out.println();
	}
	public int reqSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글 선택 2. 삭제하기 3. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	public int noticeSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글 선택 2. 등록하기 3. 수정하기 4. 삭제하기 5.뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서전체조회 2. 도서상세검색 3. 도서추가 4. 도서삭제 5. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookDetailPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서명 검색 2. 저자명검색 3. 장르검색 4. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int memPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 회원정보 2. 회원대여목록 3. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
}
