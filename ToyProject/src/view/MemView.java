package view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.RentalController;
import vo.BookVO;
import vo.MemVO;

public class MemView {
	//아이디 입력하는 메소드
	public String printIdCheck(Scanner scanner) {
		System.out.println("         < 중복되지 않는 아이디를 입력하십시오. > ");
		System.out.print("           🔒 ID : ");
		String id = scanner.nextLine();

		return id;
	}

	//회원가입 정보 입력하는 메소드
	public MemVO insertMemInfor(String id, Scanner scanner) {
		String name;
		while(true) {
			System.out.print("           🔑 PW : ");
			String pw = scanner.nextLine();
			System.out.print("           🔑 PW 확인 : ");
			String pwCheck = scanner.nextLine();
			if(pw.equals(pwCheck)) {
				System.out.println("         < PW 확인 완료 >");
				System.out.println();
				System.out.print("           😃 이름 : ");
				name = scanner.nextLine();
				System.out.print("           🏚️ 주소 : ");
				String addr = scanner.nextLine();
				System.out.print("           📞 전화번호 : ");
				String phoneNum = scanner.nextLine();

				String add1 = "";
				String add2 = "";

				while (true) {
					System.out.println();
					System.out.println("         < xxxxxx-xxxxxxx 형식으로 입력 바랍니다. >");
					System.out.print("           🎫 주민번호 : ");
					try {
						String[] add = scanner.nextLine().split("-");
						add1 = add[0];
						add2 = add[1];
						break;
					} catch (Exception e) {
						System.out.println();
						System.out.println("         < 주민번호 형식에 맞지 않습니다. 다시 입력해주세요 >");
						//					e.printStackTrace();
					} 
				}
				System.out.print("           📧 이메일 : ");
				System.out.println();
				String email = scanner.nextLine();
				return new MemVO(id, name, pw, addr, phoneNum, add1, add2, email);
			}else {
				System.out.println();
				System.out.println("         < 서로 다른 비밀번호 입력입니다. 다시 입력하십시오. >");
			}

		}
	}

	public boolean inforInsertResult(int count) {
		if (count > 0) {
			System.out.println("         < 회원가입이 완료 되었습니다. >");
			System.out.println();
			return true;
		} else {
			System.out.println("         < 회원가입에 실패하였습니다. >");
			System.out.println();
			return false;
		}

	}


	public MemVO loginResult(MemVO login) {
		if (login != null) {
			System.out.println("\t\t< " + login.getMemName() + "님이 로그인 하셨습니다. >");
			System.out.println();
			return login;
		} else {
			System.out.println()
			;
			System.out.println("  < 아이디 또는 비밀번호가 잘못 되었습니다. 다시 입력해주세요. >");
			return login;
		} 
	}
	//로그인 id pw 입력하는 메소드
	public MemVO inputLogin(Scanner scanner) {
		System.out.print("           🔒 ID : ");
		String id = scanner.nextLine();
		System.out.println();
		System.out.print("           🔑 PW : ");
		String pw = scanner.nextLine();
		System.out.println();
		return new MemVO(id, pw);
	}

	public void printRepeat() {
		System.out.println("         < 중복된 아이디 입니다. 다시 입력해 주세요. >");
		System.out.println();
	}


	public void startPrint() {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                    🏫 대덕 도서관 🏫                ");
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                                                     ");
		System.out.println("               📖   1. 회원 로그인     📖            ");
		System.out.println("                                                     ");
		System.out.println("               📖   2. 비회원 로그인   📖            ");
		System.out.println("                                                     ");
		System.out.println("               📖   3. 관리자 로그인   📖            ");
		System.out.println("                                                     ");
		System.out.println("               📖   4. 회원 가입       📖            ");
		System.out.println("                                                     ");
		System.out.println("               📖   5. 종료            📖            ");
		System.out.println("                                                     ");
		System.out.println("   ────────────────────────────────────────────────────");

	}
	
	public void loginPrint() {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                       🏫 로그인 🏫                ");
		System.out.println("   ────────────────────────────────────────────────────");
	}
	
	public void loginPrint2() {
		System.out.println();
	    System.out.println("               📖  1. 로그인 2. 뒤로가기  📖            ");
	}
	
	public void memWindowPrint(MemVO mem) {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                    🏫 대덕 도서관 🏫                  ");
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("    회원 : " + mem.getMemName());
		System.out.println("                                                        ");
		System.out.println("                🔍 1. 도서                         ");
		System.out.println("                🏚️ 2. 마이 페이지                        ");
		System.out.println("                📪 3. 요청 게시판                        ");
		System.out.println("                📝 4. 독후감 게시판                      ");
		System.out.println("                📢 5. 공지사항 게시판                    ");
		System.out.println("                   6. 로그아웃                           ");
		System.out.println("                                                         ");
		System.out.println("   ────────────────────────────────────────────────────");
	}
	
	public void memJoinPrint() {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                    🏫 회원 가입 🏫                  ");
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.println("                                                     ");
	}
	
	public MemVO myPagePrint(MemVO mem, Scanner scanner, RentalController rental) throws Exception {
		MemVO memInforUpdate = null;
		while(true) {
			System.out.println("   ────────────────────────────────────────────────────");
			System.out.println("                    🏫 마이페이지 🏫                  ");
			System.out.println("   ────────────────────────────────────────────────────");
			System.out.println("     ■ 나의 로그인 정보                               ");
			System.out.println("                                                     ");
			System.out.println("      아이디 : " + mem.getMemId());
			System.out.println("      이름 : " + mem.getMemName());
			System.out.println("                                                     ");
			System.out.println("     ■ 나의 개인정보                               ");
			System.out.println("                                                     ");
			System.out.println("      휴대폰번호 : " + mem.getMemPhoneNum());
			System.out.println("      주소 : " + mem.getMemAddr());
			System.out.println("      이메일 : " + mem.getMemEmail());
			System.out.println("      생년월일 : " + mem.getMemRegno1());
			System.out.println("                                                     ");
			System.out.println("     ■ 나의 상태                                 ");
			System.out.println("                                                     ");
			System.out.println("      마일리지 : " + mem.getMemMileage() + " point");
			System.out.println("      보유현금 : " + mem.getMemMoney() + " 원");
			System.out.println("                                                     ");
			System.out.println("\t  1. 개인정보 수정하기 2. 나의 대여목록  3. 뒤로가기 ");
			
			int select = Integer.parseInt(scanner.nextLine());
			check:
			switch (select) {
			case 1://개인정보 수정하기
				memInforUpdate = memInforUpdate(scanner, mem);
				break;
			case 2://나의 대여목록
				while(true) {
					rental.selectRental(scanner, mem);
					
					System.out.println("\t  1. 반납하기 2. 연장하기 3. 뒤로가기");
					int sel = Integer.parseInt(scanner.nextLine());
					switch (sel) {
					case 1://반납하기
						rental.returnBook(scanner, mem);
						break;
					case 2://연장하기
						rental.extentionRental(scanner, mem);
						break;
					case 3://뒤로가기
						break check;
					default:
						break;
					}
				}
			case 3://뒤로가기
				return memInforUpdate;
			default:
				System.out.println("잘몬된 접근입니다. 다시 입력해주세요");
				break;
			}
		}
	}
	
	
	
	//개인정보 수정하는 메서드
	public MemVO memInforUpdate(Scanner scanner, MemVO mem) {
		check :
		while(true) {
			System.out.println();
			System.out.println("\t\t< 수정할 정보를 입력하세요. >");
			System.out.println();
			System.out.println("\t1. 휴대폰번호  2. 주소  3.이메일  4.뒤로가기");
			String phoneNum;
			String addr;
			String email;
			int select = Integer.parseInt(scanner.nextLine());
			
			switch (select) {
			case 1:
				System.out.printf("\t기존 휴대폰 번호 : %s  \n\t수정할 휴대폰 번호 : ", mem.getMemPhoneNum() );
				phoneNum = scanner.nextLine();
				mem.setMemPhoneNum(phoneNum);
				changePrint();
				break;
			case 2:
				System.out.printf("\t기존 주소 : %s  \n\t수정할 주소 : ", mem.getMemAddr() );
				addr = scanner.nextLine();
				mem.setMemAddr(addr);
				changePrint();
				break;
			case 3:
				System.out.printf("\t기존 이메일 : %s  \n\t수정할 이메일 : ", mem.getMemEmail() );
				email = scanner.nextLine();
				mem.setMemEmail(email);
				changePrint();
				break;
			case 4:
				break check;
			default:
				System.out.println("잘몬된 접근입니다. 다시 입력해주세요");
				break;
			}
		}
		return mem;
	}
	
	public void changePrint() {
		System.out.println("\n\t\t< 변경이 완료 되었습니다. >");
		System.out.println();
		System.out.println("   ────────────────────────────────────────────────────");
	}
	
	public void logoutPrint(MemVO mem) {
		System.out.println("\t\t< " + mem.getMemName() + "님이 로그아웃 하셨습니다. >");
		System.out.println();
	}

	public int reqSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글선택 2. 게시글작성 3. 수정하기 4. 삭제하기 5. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}

	public int noticeSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 게시글선택 2.뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookSelPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서전체조회 2. 도서상세검색  3. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int BookDetailPrint (Scanner scanner) {
		System.out.println();
		System.out.println("\t1. 도서명검색 2. 저자명검색 3. 장르검색 4. 뒤로가기");
		return Integer.parseInt(scanner.nextLine());
	}

	public void printMembers(List<MemVO> list, Scanner scanner) {

		if(list.isEmpty()) {
			System.out.println(" \t< 회원이 없습니다. 분발하세요.. >");
		} else {
			while (true) {
				System.out.println(
						"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println("       ID\t이름\t주소\t\t\t휴대폰번호\t\tE-mail\t\t\t가입일\t\t      생년월일  마일리지   ");
				System.out.println(
						"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for (MemVO vo : list) {
					System.out.println("   " + vo.getMemId() + "\t" + vo.getMemName() + "\t" + vo.getMemAddr()
							+ "\t" + vo.getMemPhoneNum() + "\t\t" + vo.getMemEmail() + "\t"
							+ vo.getMemRegistration() + "\t" + vo.getMemRegno1() + "\t" + vo.getMemMileage()+" point");
					System.out.println();
				} 
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("\t 1. 뒤로가기");
				int select = Integer.parseInt(scanner.nextLine());
				switch(select) {
				case 1:
					return;
				}
				
			}
		}

	}
	
	
}