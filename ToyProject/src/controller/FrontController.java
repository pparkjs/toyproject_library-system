package controller;

import java.util.Scanner;

import vo.MemVO;

public class FrontController {
	private Scanner scanner = new Scanner(System.in);
	private MemController mem = new MemController();
	private BookController book = new BookController();
	private BoardController board = new BoardController();
	private NonController non = new NonController();
	private AuthController auth = new AuthController();
	private RentalController rental = new RentalController();
	
	public void process() throws Exception {
		while(true) {
			int select = mem.start(scanner);
			switch (select) {
		
			case 1://회원 로그인
				MemVO memLogin = mem.memLogin(scanner);//로그인 된 객체 담은 것
				if(memLogin != null) {
					mem.memWindoWSelect(scanner, memLogin, board, book, rental);//회원 화면					
				}
				break;
			case 2://비회원 로그인
				non.NonLoginWindow(scanner, board, book, rental);
				break;
			case 3://관리자 로그인
				auth.AuthLoginWindow(scanner, mem, board, book, rental);
				break;
			case 4://회원 가입 
				mem.memJoin(scanner); 
				break;
			case 5: 
			System.out.println("\t\t< 프로그램을 종료합니다. > ");
			System.exit(select);
			break;
			default:
				break;
			}
		}
	}
}
