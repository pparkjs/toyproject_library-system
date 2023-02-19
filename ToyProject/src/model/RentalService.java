package model;

import java.util.List;

import vo.RentalVO;

public class RentalService {

	private RentalDAO dao = new RentalDAO();


	// 전문가용 대여 조회
	public List<RentalVO> selectRentals() throws Exception{
		return dao.selectRentals();
	}

	// 회원용 대여 조회
	public List<RentalVO> selectRental(String userName) throws Exception{
		return dao.selectRental(userName);
	}

	// 대여 등록
	public int insertRental(RentalVO vo) throws Exception{
		return dao.insertRental(vo);
	}

	// 반납
	public int returnBook(RentalVO vo) throws Exception {
		return dao.returnBook(vo);
	}

	// 대출연장
	public int extentionRental(RentalVO vo) throws Exception {
		return dao.extentionRental(vo);

	}
	//--------------------------------------------------------------------------------	
	// 대여 수단 선택 돈으로 
	public int selectMoney(RentalVO vo) throws Exception {
		return dao.selectMoney(vo);
	}

	public int selectMileage(RentalVO vo) throws Exception {
		return dao.selectMileage(vo);
	}	

}
