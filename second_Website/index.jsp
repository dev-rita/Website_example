<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 컴퓨터/폰에서 해상도에 맞게 알아서 디자인이 변경되는 반응형 웹 메타태그 -->
<meta name="viewport" content="width=device-width", initial-scale="1">
<title>OKKY</title>
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<%--외부 스타일 시트 방법 --%>
</head>
<body>
<body>
	<%-- 메뉴 영역 --%>
	<div id="wrap">
		<header>
			<%--header는 html5에서 추가된 태그로 상단 머릿 부분을 지정할 때 사용한다. --%>
			<%-- 회사로고 --%>
			<div id="logo">
				<a href="index.jsp"><img src="./Images/logo1.PNG" width="103" height="35" alt="logo" /></a><br/>
			</div>
			
			<%-- 로그인/회원가입 --%>
			<div id="login">
				<a href="./member/login.jsp"><img src="./Images/login.PNG" width="18" height="13" alt="login" align="center" />로그인</a>
			</div>	
			<div id="join">
				<a href="./member/join.jsp"><img src="./Images/join.PNG" width="17" height="15" alt="join" align="center"/>회원가입</a>
			</div>
		<%-- 상단 메뉴 --%>
			<nav>
				<%-- nav는 html5에서 추가된 태그로 메뉴 구성할 때 사용 --%>
				<ul>
					<li><a href="./articles/community.jsp"><img src="./Images/community1.PNG" width="25" height="25" align="center" alt="community">커뮤니티</a></li>
				</ul>
			</nav>
		</header>
	
		<%-- 메인하단 --%>
		<footer>
			<%--footer는 html5에서 추가된 태그로 하단영역을 지정할 때 주로 사용한다. --%>
		<hr/>
		<div id="copy">
				<a href="#">About OKKY</a> | <a href="#">개인정보보호</a> | <a href="#">광고문의</a> | <a href="#">Contact</a> | <a href="#">Facebook</a> | <a href="#">Github v1.5.4</a>
		<hr/>
		<strong>상호명</strong> : 이브레인 | <strong>대표명</strong> : 노상범 | <strong>사업자등록번호</strong> : 144-81-32887 | <strong>문의</strong> : info@okky.kr<br/>
		<strong>주소</strong> : 서울 강남구 봉은사로 303 TGL경복빌딩 502호(06103)
		</div>
		
		</footer>
		</div>
</body>
</html>