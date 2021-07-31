<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../css/main.css" /><%-- ../는 상대경로로서 한단계 상위폴더로 이동한다. --%>
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<link rel="stylesheet" type="text/css" href="../css/notice.css" />
<link rel="stylesheet" type="text/css" href="../css/join.css" />
<%--외부 스타일 시트 방법--%>
</head>
<body>
	<%-- 헤더영역 --%>
	<div id="wrap">
		<header>
			<%--header는 html5에서 추가된 태그로 상단 머릿 부분을 지정할 때 사용한다. --%>
			<div id="login">
				<a href="#">login</a> |<a href="../member/join.jsp"> Join</a>
			</div>
			<%-- 회사로고 --%>
			<div id="logo">
				<img src="../Images/logo.gif" width="265" height="62" alt="Fun Web" />
			</div>

			<%-- 상단 메뉴 --%>
			<nav>
				<%-- nav는 html5에서 추가된 태그로 메뉴 구성할 때 사용 --%>
				<ul>
					<li><a href="../index.jsp">HOME</a></li>
					<li><a href="../company/welcome.jsp">COMPANY</a></li>
					<li><a href="#">SOLUTIONS</a></li>
					<li><a href="../center/notice.jsp">CUSTOMER CENTER</a></li>
					<li><a href="#">CONTACT US</a></li>
				</ul>
			</nav>
		</header>

		<div class="clear"></div>
