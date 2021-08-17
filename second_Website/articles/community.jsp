<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<title>커뮤니티</title>
<link rel="stylesheet" type="text/css" href="../css/mainhe2.css" />
<%--외부 스타일 시트 방법 --%>
</head>
<body>
	<%-- 메뉴 영역 --%>
			<%--header는 html5에서 추가된 태그로 상단 머릿 부분을 지정할 때 사용한다. --%>
			<div class="line" id="menu">
			<%-- 회사로고 --%>
			
			<div id="logo">
				<a href="../index.jsp"><img src="../Images/logo.PNG" width="38" height="35" alt="logo" /></a><br/>
			</div>
			
			<%-- 로그인/회원가입 --%>
			<div id="login">
				<a href="../member/login.jsp"><img src="../Images/login.PNG" width="22" height="20" alt="login" /></a>
			</div>	
			<div id="join">
				<a href="../member/join.jsp"><img src="../Images/join.PNG" width="22" height="22" alt="join" /></a>
			</div>
			
		<%-- 상단 메뉴 --%>
			<nav>
				<%-- nav는 html5에서 추가된 태그로 메뉴 구성할 때 사용 --%>
				<ul>
					<li><a href="community.jsp"><img src="../Images/community.PNG" width="45" height="45" align="center" alt="community"></a></li>
				</ul>
			</nav>
			</div>
			
			<div class="line" id="sidemenu">
			<h5>커뮤니티</h5>
			<ul>
			<li><a href="community.jsp">All</a>
			<li><a href="notice.jsp">공지사항</a>
			<li><a href="life.jsp">사는얘기</a>
			</ul>
			</div>
		
			<div class="line" id="article">
			<h5>커뮤니티</h5>
			<a  href="create.jsp"> <img id="create_btn" src="../Images/write.PNG" alt="write" width="90" height="25"></a>
		
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
