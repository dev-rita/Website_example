<%@ page contentType="text/html; charset=UTF-8"%>
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