package model;

import java.util.List;

import vo.BoardVO;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	public List<BoardVO> selectReqBoards() throws Exception {
		return dao.selectReqBoards();
	}

	public BoardVO selectReqBoard(int no, String id) throws Exception {
		return dao.selectReqBoard(no, id);
	}

	public int insertReqBoard(BoardVO vo, String memId) throws Exception {
		return dao.insertReqBoard(vo, memId);
	}

	public int updateReqBoard(BoardVO vo, int no) throws Exception {
		return dao.updateReqBoard(vo, no);
	}

	public int deleteReqBoard(int deleteNum) throws Exception {
		return dao.deleteReqBoard(deleteNum);
	}

	public BoardVO detailReqBoard(int searchNum) throws Exception {
		return dao.detailReqBoard(searchNum);
	}
//	---------------------------------------------------------------------------------------
	public List<BoardVO> selectBookReports() throws Exception {
		return dao.selectBookReports();
	}

	public BoardVO selectBookReport(int no, String id) throws Exception {
		return dao.selectBookReport(no, id);
	}

	public int insertBookReport(BoardVO vo, String memId) throws Exception {
		return dao.insertBookReport(vo, memId);
	}

	public int updateBookReport(BoardVO vo, int no) throws Exception {
		return dao.updateBookReport(vo, no);
	}

	public int deleteBookReport(int deleteNum) throws Exception {
		return dao.deleteBookReport(deleteNum);
	}

	public BoardVO detailBookReport(int searchNum) throws Exception {
		return dao.detailBookReport(searchNum);
	}
	
	public int memReportMilegeInsert(String memId) throws Exception {
		return dao.memReportMilegeInsert(memId);
	}
//-------------------------------------------------------------------------------
	public List<BoardVO> selectNoticeBoards() throws Exception {
		return dao.selectNoticeBoards();
	}

	public int insertNoticeBoard(BoardVO vo) throws Exception {
		return dao.insertNoticeBoard(vo);
	}

	public int updateNoticeBoard(BoardVO vo, int no) throws Exception {
		return dao.updateNoticeBoard(vo, no);
	}

	public int deleteNoticeBoard(int deleteNum) throws Exception {
		return dao.deleteNoticeBoard(deleteNum);

	}
	
	public BoardVO detailNoticeBoard(int searchNum) throws Exception {
		return dao.detailNoticeBoard(searchNum);
	}
	
	public BoardVO selectNoticeBoard(int no) throws Exception {
		return dao.selectNoticeBoard(no);
	}
	
}
