package model;

import java.util.List;
import java.util.Scanner;

import vo.MemVO;

public class MemService {
private MemDAO dao = new MemDAO();
	
	public boolean idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}
	
	public int insertMemInfor(MemVO mem) throws Exception {
		return dao.insertMemInfor(mem);
	}
	
	public MemVO idPwSelect(String id, String pw) throws Exception {
		return dao.idPwSelect(id, pw);
	}
	
	public int memInforUpdate(MemVO mem) throws Exception {
		return dao.memInforUpdate(mem);
	}
	public List<MemVO> selectMembers() throws Exception{
		return dao.selectMembers();
	}
}
