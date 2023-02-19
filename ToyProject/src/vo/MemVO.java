package vo;

import java.sql.Timestamp;

public class MemVO {
	private String memId;
	private String memName;
	private String memPw;
	private String memAddr;
	private String memPhoneNum;
	private int memMileage;
	private String memRegno1;
	private String memRegno2;
	private Timestamp memRegistration;
	private String memEmail;
	private int memMoney;
	
	public MemVO() {
	}

	public MemVO(String memId, String memPw) {
		this.memId = memId;
		this.memPw = memPw;
	}

//회원 정보 전체 select
	public MemVO(String memId, String memName, String memAddr, String memPhoneNum, String memEmail,
			Timestamp memRegistration, String memRegno1, int memMileage) {
		this.memId = memId;
		this.memName = memName;
		this.memAddr = memAddr;
		this.memPhoneNum = memPhoneNum;
		this.memMileage = memMileage;
		this.memRegno1 = memRegno1;
		this.memRegistration = memRegistration;
		this.memEmail = memEmail;
	}


	// insert 할 때 사용
	public MemVO(String memId, String memName, String memPw, String memAddr, String memPhoneNum, String memRegno1, String memRegno2, String memEmail) {
		this.memId = memId;
		this.memName = memName;
		this.memPw = memPw;
		this.memAddr = memAddr;
		this.memPhoneNum = memPhoneNum;
		this.memRegno1 = memRegno1;
		this.memRegno2 = memRegno2;
		this.memEmail = memEmail;
	}

	// select 할 때 사용(회원이 마이페이지 들어가면 조회할 것들) 
	public MemVO(String memId, String memName, String memAddr, String memPhoneNum, String memRegno1,
			String memRegno2, String memEmail, int memMileage ,Timestamp memRegistration, int memMoney) {
		this.memId = memId;
		this.memName = memName;
		this.memAddr = memAddr;
		this.memPhoneNum = memPhoneNum;
		this.memRegno1 = memRegno1;
		this.memRegno2 = memRegno2;
		this.memEmail = memEmail;
		this.memMileage = memMileage;
		this.memRegistration = memRegistration;
		this.memMoney = memMoney;
	}

	// update 할 때 사용(개인이 마이페이지에서 수정할 것들)
	public MemVO(String memAddr, String memPhoneNum, String memEmail) {
		this.memAddr = memAddr;
		this.memPhoneNum = memPhoneNum;
		this.memEmail = memEmail;
	}

	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public String getMemPhoneNum() {
		return memPhoneNum;
	}

	public void setMemPhoneNum(String memPhoneNum) {
		this.memPhoneNum = memPhoneNum;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public String getMemRegno1() {
		return memRegno1;
	}

	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}

	public String getMemRegno2() {
		return memRegno2;
	}

	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}

	public Timestamp getMemRegistration() {
		return memRegistration;
	}

	public void setMemRegistration(Timestamp memRegistration) {
		this.memRegistration = memRegistration;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}


	
	public int getMemMoney() {
		return memMoney;
	}

	public void setMemMoney(int memMoney) {
		this.memMoney = memMoney;
	}


	@Override
	public String toString() {
		return String.format(
				"%s, %s, %s, %s, %s, %s, %s, %s, %s",
				memId, memName, memAddr, memPhoneNum, memMileage, memRegno1, memRegno2, memEmail, memMoney);
	}
}