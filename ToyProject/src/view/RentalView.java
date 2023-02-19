package view;


import java.util.List;
import java.util.Scanner;

import vo.RentalVO;


public class RentalView {

	public void printRentals(List<RentalVO> list, Scanner scanner) {

		if(list.isEmpty()) {
			System.out.println("\t < 대여목록이 없습니다. >");
		}else {
			while (true) {
				System.out.println(
						"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(
						"  이름      대여번호         회원ID       책번호    반납여부    대여일   반납예정일   연체여부  연체일   연체료      반납일      책이름           ");
				System.out.println(
						"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for (RentalVO rentalVO : list) {
					if (rentalVO.getOverDay() > 0) {
						rentalVO.setOverdueCheck("Y");
					} else {
						rentalVO.setOverdueCheck("N");
					}

					if (rentalVO.getRentalReturnCheck().equals("Y")) {
						rentalVO.setOverDay(0);
					}

					System.out.println(rentalVO);
					
				} 
				System.out.println(
						"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("\t 1. 뒤로가기");
				int select = Integer.parseInt(scanner.nextLine());
				switch(select) {
				case 1:
					return;
				}
			}
		}
		System.out.println();

	}


	public void printRental(List<RentalVO> list, String name) {
		if(list.isEmpty()) {
			System.out.println("\t< "+name+"회원님의 대여 목록이 없습니다. >");
		} else {
			System.out.println();
			System.out.println("     ■ "+name+" 회원님의 대여 목록 ");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("  대여번호\t회원ID\t\t대여일\t\t\t반납예정일\t   연체여부\t연체일\t연체료\t책이름");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			for (RentalVO rentalVO : list) {

				if(rentalVO.getOverDay()>0) {
					rentalVO.setOverdueCheck("Y");
				}else {
					rentalVO.setOverdueCheck("N");
				}

				System.out.println("    "+rentalVO.getRentalNumber()+"\t"+rentalVO.getMemId()+"\t"+rentalVO.getRentalDate()+"\t\t"+rentalVO.getRentalReturnSchedule()
				+"\t\t"+rentalVO.getOverdueCheck()+"\t"+rentalVO.getOverDay()+"일"+"\t"+rentalVO.getOverDay()*100+"원"+"\t "+rentalVO.getBookTitle());
				System.out.println();
			}
		}	
	}

	public RentalVO inputInsertRental(Scanner scanner, String Id) {
		System.out.println("\t 대여할 도서명을 입력하세요.");
		System.out.println();
		System.out.print("\t도서명> ");
		String bookTitle = scanner.nextLine();
		return new RentalVO(Id, bookTitle);
	}

	public void rentalInsertResult(int count) {
		if(count>0) {
			System.out.println();
			System.out.println("\t< 정상적으로 대출되었습니다. > ");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("\t< 해당하는 도서가 없습니다. >");		
			System.out.println();
		}
	}



	//반납 확인
	public void rentalReturnResult(int count) {
		if(count>0) {
			System.out.println();
			System.out.println("\t< 정상적으로 반납되었습니다. > ");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("\t< 해당 도서를 보유하고 있지 않습니다. >   ");	
			System.out.println();
		}
	}

	//반납
	public RentalVO rentalReturnBook(Scanner scanner, String memId) {
		System.out.println("\t< 반납할 도서명을 입력하세요. > ");
		System.out.println();
		System.out.print("\t도서명> ");
		String bookTitle = scanner.nextLine();
		return new RentalVO(memId,bookTitle);
	}



	//연장 확인
	public void rentalExtentionResult(int count) {
		if(count>0) {
			System.out.println();
			System.out.println("\t< 정상적으로 연장되었습니다. > ");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("\t< 해당 도서를 보유하고 있지 않습니다. >   ");	
			System.out.println();
		}
	}

	//반납
	public RentalVO rentalExtentionReturn(Scanner scanner, String memId) {
		System.out.println();
		System.out.println("\t< 연장할 도서명을 입력하세요 > ");
		System.out.println();
		System.out.print("\t도서명> ");
		String bookTitle = scanner.nextLine();
		return new RentalVO(memId,bookTitle);
	}

	//------------------------------------------------------------------------------------	
	// 뭘로 빌릴지 선택


	// 빌릴거 수단 ID 입력 -- 돈으로 빌렸을때
	public RentalVO SelectMoneyInputId(int money, int mileage, String memId) {
		money = money-300;
		mileage = mileage + 50;
		return new RentalVO(memId,money,mileage);
	}

	// 빌릴거 수단 ID 입력 -- 마일리지로 빌렸을때

	public RentalVO SelectMileageInputId(String memId) {
		return new RentalVO(memId,-300);
	}

	// 돈으로 렌탈
	public int bookRenatalWithMoney(int count) {
		if(count>0) {
			System.out.println();
			return count;
		} else {
			System.out.println("\t< 잔액이 부족합니다. >   ");	
			System.out.println();
			return count;
		}
	}	

	public int bookRenatalWithMileage(int count) {
		if(count>0) {
			System.out.println();
			return count;
		} else {
			System.out.println("\t< 마일리지가 부족합니다. >   ");	
			System.out.println();
			return count;
		}
	}


	public void NonBookPrint() {
		System.out.println();
		System.out.println("\t< 존재하지않는 도서입니다. >");
		System.out.println();
	}	



}
