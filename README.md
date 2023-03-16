# 소개
Oracle SQL과 Java를 학습하고 JDBC를 배운 후 개념을 확립하기 위해서 <br>
JDBC를 배운뒤 java와 oracle을 연계하여 콘솔을 통해 실행되는 도서관시스템을 제작하였습니다. 
<br><br>
# Design Pattern
**MVC 패턴**을 학습 한 후 MVC 패턴에 어긋나지 않도록 주의하며 효율적으로 코드를 작성 하였습니다.
<br><br>
# MVC 작성 규칙
1. JDBC를 사용하는 CRUD 작업은 해당 DAO에 작성 <br><br>
2. DAO에서 원하는 데이터를 받고 보내기 위해 JavaBeans 패턴(필드, 생성자, getter/setter)으로 작성한 저장할 VO 클래스 생성  <br><br>
3. 모든 DAO는 Service를 거쳐서 사용되었습니다. <br><br>
4. 각 DAO는 해당 controller에서 작업이 수행 되며 실제 콘솔에 보여질 메시지들은 View에서만 다루었습니다. <br><br>
5. 하나의 FrontController클래스에 모든 DAO의 Controller 객체를 생성해서 사용할 수 있게 하였습니다. <br><br>
6. 마지막으로 실행할 main메서드는 Application클래스에 생성하여 FrontController 객체를 생성해 실행할 수 있도록 하였습니다. <br><br>

