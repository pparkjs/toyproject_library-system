package vo;

import java.sql.Timestamp;

public class BoardVO {
   private  int num;
   private String title;
   private String content;
   private String memId;
   private Timestamp registerDate;
   private Timestamp modifyDate;
   
   
   //update할 때 쓰이는 생성자
   public BoardVO(int num, String title, String content) {
      super();
      this.num = num;
      this.title = title;
      this.content = content;
   }

   //게시글 insert
   //게시글 넘버는 시퀀스가 알아서 번호 매겨주고 date는 sysdate가 처리해줌
   public BoardVO(String title, String content, String memId) {
      super();
      this.title = title;
      this.content = content;
      this.memId = memId;
   }
 //게시글 insert
   //게시글 넘버는 시퀀스가 알아서 번호 매겨주고 공지사항 게시판이라 mem_id 없음
   public BoardVO(String title, String content) {
      super();
      this.title = title;
      this.content = content;
   }
   
   //게시판에 있는 게시글 전체 조회할 때 쓰이는 생성자 (게시글 넘버 제목 작성자 작성일 수정일) 
   public BoardVO(int num, String title, String memId, Timestamp registerDate, Timestamp modifyDate) {
      super();
      this.num = num;
      this.title = title;
      this.memId = memId;
      this.registerDate = registerDate;
      this.modifyDate = modifyDate;
   }
   

//공지사항 게시판에 있는 게시글 전체 조회할 때 쓰이는 생성자 (게시글 넘버 제목 작성일 수정일) 
   public BoardVO(int num, String title, Timestamp registerDate, Timestamp modifyDate) {
	      super();
	      this.num = num;
	      this.title = title;
	      this.registerDate = registerDate;
	      this.modifyDate = modifyDate;
	   }
   
   //선택한 게시글 전체 보는 생성자
   public BoardVO(int num, String title, String content, String memId, Timestamp registerDate, Timestamp modifyDate) {
	super();
	this.num = num;
	this.title = title;
	this.content = content;
	this.memId = memId;
	this.registerDate = registerDate;
	this.modifyDate = modifyDate;
}

// getter setter 생성
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getMemId() {
      return memId;
   }
   public void setMemId(String memId) {
      this.memId = memId;
   }
   public Timestamp getRegisterDate() {
      return registerDate;
   }
   public void setRegisterDate(Timestamp registerDate) {
      this.registerDate = registerDate;
   }
   public Timestamp getModifyDate() {
      return modifyDate;
   }
   public void setModifyDate(Timestamp modifyDate) {
      this.modifyDate = modifyDate;
   }
   
//   @Override
//   public String toString() {
//      return String.format("boardVO [num=%s, title=%s, content=%s, memId=%s, registerDate=%s, modifyDate=%s]",
//            num, title, content, memId, registerDate, modifyDate);
//   }
   
   

}