# ArrayList 응용하여 MVC에 맞게 코딩
## MVC는 무엇인가?
### 기존에는 하나의 클래스에 모든 기능을 다 집어넣었다면 MVC는 각각의 기능에 맞게 코드를 나눠놓은 것이다. M은 Model, V는 View, C는 Controller를 뜻한다.

> #### Model - 비지니스 로직(업무에 필요한 데이터 처리를 하는 로직)을 처리하는 기능을 넣는 곳이다. 결과를 Controller를 통해 View로 전달하게 된다.
> #### View - 화면 출력 부분에 해당하는 기능을 넣는 곳이다. Model에서 처리하는 Database 연동과 같은 로직을 구현하지 않는다.
> #### Controller - View와 Model을 연결해주는 부분

### MVC 패턴이 많이 쓰이는 이유는 디자이너는 Model 부분을 신경쓰지 않고 View부분만 집중하여 개발 할 수 있고, 프로그래머는 View부분을 신경쓰지 않고 Model 부분만 신경쓸 수 있기 때문이다.
