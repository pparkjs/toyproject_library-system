package view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import controller.BookController;
import controller.RentalController;
import vo.BookVO;
import vo.MemVO;

public class BookView {


	public int bookMenu(Scanner scanner) {
		System.out.println();
		System.out.println("\t1.목록 2.등록 3.삭제 4.종료 ");
		return Integer.parseInt(scanner.nextLine());
	}

	public void printBooks(List<BookVO> list) {

		if(list.isEmpty()) {
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("                                 없는 자료 입니다, 다시 검색해주세요                              ");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
		} else {
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println(" 도서번호\t책이름\t\t\t저자\t\t출판사\t\t출판일   ");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
			for (BookVO vo : list) {
				System.out.println(vo);
			}
		}
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────");
	}

	public BookVO inputInsertBook(Scanner scanner) {
		System.out.println("   ────────────────────────────────────────────────────");
		System.out.print("               책 ID : ");
		String bookID = scanner.nextLine();
		System.out.print("               제목 : ");
		String title = scanner.nextLine();
		System.out.print("               저자 : ");
		String author = scanner.nextLine();
		System.out.print("               장르 : ");
		String genre = scanner.nextLine();
		System.out.print("              부장르 : ");
		String subGenre = scanner.nextLine();
		System.out.println("\t< 발행일 형식 yyyy-mm-dd >    ");
		System.out.print("              발행일 : ");
		String publishedDate = scanner.nextLine();
		Date publishedDate2 = Date.valueOf(publishedDate);
		System.out.print("              출판사 : ");
		String publisher = scanner.nextLine();
		System.out.print("               위치 : ");
		int location = Integer.parseInt(scanner.nextLine());
		System.out.println("   ────────────────────────────────────────────────────");		
		return new BookVO(bookID, title, author, genre, subGenre,
				publishedDate2, publisher, location);
	}


	public void insertResult(int count) {
		if (count>0) {
			System.out.println("\t< 도서가 정상적으로 등록되었습니다. >   ");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("\t< 도서가 정상적으로 등록되지 않았습니다. 관리자에게 문의하세요 >     ");
			System.out.println();
		}
	}

	public String deleteBook(Scanner scanner) {
		System.out.println("\t< 검색할 도서명을 입력하세요 >   ");
		System.out.println();
		System.out.print("\t도서명> ");
		return scanner.nextLine();
	}

	public void deleteResult(int count) {
		if (count > 0) {
			System.out.println();
			System.out.println("\t< 도서가 삭제되었습니다. >     ");
		} else {
			System.out.println();
			System.out.println("\t< 해당 도서명이 존재하지 않습니다. 다시 시도해주세요 >");
			System.out.println();
		}
	}

	public String bookNameSearch(Scanner scanner) {
		System.out.println("\t< 검색할 도서명을 입력하세요 >   ");
		System.out.println();
		System.out.print("\t도서명> ");
		return scanner.nextLine();
	}

	public String authorNameSearch(Scanner scanner) {
		System.out.println("\t< 검색할 저자 이름을 입력하세요 > ");
		System.out.println();
		System.out.print("\t저자명> ");
		return scanner.nextLine();
	}

	public String genreNameSearch(Scanner scanner) {
		System.out.println("\t< 검색할 분야명을 입력하세요 >    ");
		System.out.println();
		System.out.print("\t분야명> ");
		return scanner.nextLine();
	}	



	public void bookSearch(List<BookVO> list, Scanner scanner, RentalController rental, MemVO memInfor, BookController book) throws Exception {

		if(list.isEmpty()) {
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("                                 없는 자료 입니다. 다시 검색해주세요                              ");
		} else {

			while(true) {
				System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 도서번호\t책이름\t\t저자\t부장르\t출판사\t출판일\t\t수량\t위치 ");
				System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for (BookVO vo : list) {
					System.out.println(" " + vo.getBookId() +"\t"+ vo.getTitle() +"\t\t"+ vo.getAuthor()+"\t"+vo.getSubGenre()+"\t"+vo.getPublisher()
					+"\t"+vo.getPublishedDate()+'\t'+vo.getQty()+"\t"+vo.getLocation());
				}
				System.out.println();

				
				System.out.println("\t 1. 대여하기 2. 뒤로가기");
				int select = Integer.parseInt(scanner.nextLine());
				switch (select) {
				case 1://대여하기
					check:
					while(true) {
						System.out.println("\t 1. 현금대여 2. 마일리지대여 3.뒤로가기");
						int point = Integer.parseInt(scanner.nextLine());
						switch (point) {
						case 1://돈으로 계산
							System.out.println("──────────────────────────────────────────────────────────");
							System.out.println("\t현재 보유 현금 : " + memInfor.getMemMoney());
							System.out.println("\t현재 보유 마일리지 : " + memInfor.getMemMileage());
							System.out.println("──────────────────────────────────────────────────────────");
							int countMoney = rental.selectMoney(memInfor);
							if(countMoney == 0) {
								return;
							}
							break;
						case 2://마일리지로 계산
							System.out.println("──────────────────────────────────────────────────────────");
							System.out.println("\t현재 보유 마일리지 : " + memInfor.getMemMileage());
							System.out.println("──────────────────────────────────────────────────────────");
		    	 			int countMile = rental.selectMileage(memInfor);
		    	 			if(countMile == 0) {
		    	 				return;
		    	 			}
							break;
						case 3:
							return;
						default:
							break;
						}
						rental.insertRental(scanner,memInfor,book);
						System.out.println("\t 대여를 이어서 하시겠습니까? y/n");
						String yN = scanner.nextLine();
						switch (yN) {
						case "y":
							break;
						case "n":
							break check;
						default:
							break;
						}
					}
				case 2://뒤로가기
					return;
				default:
					break;
				}

			}
		}


	}
	//비회원용 도서검색
	public void bookNonSearch(List<BookVO> list, Scanner scanner) throws Exception {
		if(list.isEmpty()) {
			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("                                 없는 자료 입니다. 다시 검색해주세요                              ");
		} else {
			while(true) {
				System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 도서번호\t책이름\t\t저자\t부장르\t출판사\t출판일\t\t수량\t위치 ");
				System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
				for (BookVO vo : list) {
					System.out.println(" " + vo.getBookId() +"\t"+ vo.getTitle() +"\t\t"+ vo.getAuthor()+"\t"+vo.getSubGenre()+"\t"+vo.getPublisher()
					+"\t"+vo.getPublishedDate()+'\t'+vo.getQty()+"\t"+vo.getLocation());
					System.out.println();
				}
				System.out.println();
				System.out.println("\t 1. 뒤로가기");
				int select = Integer.parseInt(scanner.nextLine());
				switch (select) {
				case 1://뒤로가기
					return;
				default:
					break;
				}
			}
		}
	}


}
