# 개인 프로젝트 : Hello World!
#### 이름 : 서현진
#### 개발 기간 : ( 2024-01-10 ~ 2024-02-20 )
#### 프로젝트 아이디어 개요 : 
학원 수업을 진행하면서 느낀 궁금점이 있었다. 수료한 학생들이 취업하는 회사들의 정보와 그들과의 커뮤니케이션이 필요했다. 

학원 수강 후 개발 일을 시작한 현직자와 현재 학원 수업을 진행 중인 학생을 연결시켜주고 어떠한 회사로 취업했는지

취업 당시 자격증은 어떤 것들이 있었는지, 취업한 회사의 지역은 어디인지 다양한 정보를 제공해준다.

#### 운영체제 : Window10
#### 데이터베이스 : Oracle
#### 프레임워크 : Spring
#### 도구 및 언어 : Eclipse | Java, JQuery, Ajax, JavaScript, CSS, HTML, BootStrap, KAKAO API, Google eChart
---
## 주요 기능
+ 회원가입기능(이메일,비밀번호,닉네임 외 선택추가사항)/현직자 등록시 간단한 입력 폼 작성
+ 관리자가 일반회원(수강생)->현직자 회원등급 변경
+ Kakao API를 활용한 지도 api 구현(마커클러스터러+커스텀오버레이)
+ 현직자 등록시 입력한 정보들을 도넛차트로 산출
+ 웹소켓을 이용한 실시간 채팅
+ 유저 간 첨삭기능(쪽지기능에 파일 첨부)
+ 마이페이지에서 주고 받은 첨삭 확인 및 답장기능
+ ck에디터를 활용한 게시판(CRUD)기능 좋아요/댓글
---
## 시연 결과
|시연|결과화면|
|---|---|
|메인 페이지|![메인페이지](https://github.com/travler1/Hello-World/blob/master/%EB%A9%94%EC%9D%B8.jpg) |
|로그인 페이지|![로그인페이지](https://github.com/travler1/Hello-World/blob/master/%EB%A1%9C%EA%B7%B8%EC%9D%B8.jpg)|
|취업현황 페이지| ![취업현황페이지](https://github.com/travler1/Hello-World/blob/master/%EC%B7%A8%EC%97%85%ED%98%84%ED%99%A9.jpg) ![레벨축소](https://github.com/travler1/Hello-World/blob/master/%EB%A0%88%EB%B2%A8%EC%B6%95%EC%86%8C%EC%B9%B4%EC%B9%B4%EC%98%A4%EB%A7%B5.jpg)|
|현직자 신청하기 폼|![현직자신청하기폼](https://github.com/travler1/Hello-World/blob/master/%ED%98%84%EC%A7%81%EC%9E%90%20%EC%8B%A0%EC%B2%AD%ED%95%98%EA%B8%B0%20%ED%8F%BC.jpg)|
|현직자프로필|![현직자프로필](https://github.com/travler1/Hello-World/blob/master/%ED%98%84%EC%A7%81%EC%9E%90%ED%94%84%EB%A1%9C%ED%95%84.jpg)|
|첨삭기능|![첨삭기능](https://github.com/travler1/Hello-World/blob/master/%EC%B2%A8%EC%82%AD%EA%B8%B0%EB%8A%A5.png)|
|채팅기능|![채팅기능](https://github.com/travler1/Hello-World/blob/master/%EC%B1%84%ED%8C%85%EA%B8%B0%EB%8A%A5.jpg)|
|게시판 메인페이지|![게시판메인페이지](https://github.com/travler1/Hello-World/blob/master/%EA%B2%8C%EC%8B%9C%ED%8C%90%EB%A9%94%EC%9D%B8.jpg)|
|게시판 글쓰기|![게시판글쓰기](https://github.com/travler1/Hello-World/blob/master/%EA%B2%8C%EC%8B%9C%ED%8C%90%EA%B8%80%EC%93%B0%EA%B8%B0.jpg)|
|게시판 글상세|![게시판글상세](https://github.com/travler1/Hello-World/blob/master/%EA%B2%8C%EC%8B%9C%ED%8C%90%EA%B8%80%EC%83%81%EC%84%B8.jpg)|
|DB설계|![DB설계](https://github.com/travler1/Hello-World/blob/master/DB%EC%84%A4%EA%B3%84.jpg)|
|프로젝트 중 어려웠던 점|카카오지도 api 기능 혼합 사용<br>-	카카오지도에 현직자로 등록된 사용자들이 두 가지 방법으로 출력. (지도를 축소하면 지역별 숫자로 표시되는 마커 클러스터기능, 확대하면 사용자의 프로필사진이 출력되는 커스텀 오버레이기능)<br><br>카카오지도에 데이터 전달<br>-	각 사용자들의 프로필과 위도/경도 좌표를 전달하는 방법이 쉽지 않았음<br><br>파일 출력 기능<br>-	프로필 클릭메뉴 중 첨삭기능은 상대방에게 메시지와 첨삭받을 파일을 첨부해서 전달하는 기능<br>-	파일의 이름은 DB에 UUID를 추가하여 저장, 실제 파일은 프로젝트 내 upload폴더에 저장됨<br>-	버튼 클릭 시 upload폴더 내에 있는 파일을 가져오는 게 쉽지 않았음.|
|해결방안|1.	카카오지도 api 혼합 사용<br>각각의 api 기능을 혼합해서 사용<br><br>2.	카카오지도에 데이터 전달<br>사용자들의 mem_num(멤버테이블에 저장된 사용자번호)와 위도/경도를 담은 리스트 형태로 만든 후 ObjectMapper 클래스의 writeValueAsString 메서드를 이용해 카카오지도에서 원하는 json 형식으로 만들어서 전달<br>![카카오지도JSON](https://github.com/travler1/Hello-World/blob/master/%EC%B9%B4%EC%B9%B4%EC%98%A4%EC%A7%80%EB%8F%84JSON.jpg)<br><br>3.	파일출력기능 <br>OutputStream과 FileInputStream 클래스를 이용하여 파일이름이 저장된 테이블의 고유번호(advice_num)로부터 파일이름을 불러온 후 파일입력객체를 생성하여 파일 전달 ![파일다운로드메서드](https://github.com/travler1/Hello-World/blob/master/%ED%8C%8C%EC%9D%BC%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%EB%A9%94%EC%84%9C%EB%93%9C.jpg)|
|깨달은점|-	웹소켓을 이용한 채팅 기능의 원리 파악<br>-	DB에 저장된 회원들이 많아질 경우 카카오지도에 다량의 데이터 생성 시 대응 고려할 것<br>-	수정과 유지보수를 위해 주석을 세밀하게 작성하는 것. <br>자신의 메서드가 어떤 기능을 하는지 알아볼 수 있어야 수정과 유지보수가 원활할 수 있음<br>-	컨트롤러와 서비스를 분리하여 로직은 서비스에서 정의하고 컨트롤러는 기능만 알 수 있게 명시|







