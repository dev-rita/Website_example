<%@ page contentType="text/html; charset=UTF-8"%>
<%-- jsp 주석문 기호. jsp주석문은 html주석문과는 다르게 브라우저 출력창 뿐만 아니라 페이지 소스보기에서도 안보인다.
그래서 보안성이 우수하다. 상단,하단 코드를 반복하지 않기 위해서 jsp사용한다.-중복x, html코드 포함.
%@는 지시자. page 디렉티브 지시자로 어떤 jsp의 정의를 내리는 부분이다.
contentType="text/html; charset=UTF-8"속성값의 뜻은 브라우저에 출력되는 문자와 HTML태그적용, 언어 코딩 타입을 utf-8로 설정한다.--%>


<link rel="stylesheet" type="text/css" href="../css/main.css" /><%-- ../는 상대경로로서 한단계 상위폴더로 이동한다. --%>
<%--외부 스타일 시트 방법--%>
<%-- 메뉴 영역 --%>
	<div id="wrap">
		<header>
			<%--header는 html5에서 추가된 태그로 상단 머릿 부분을 지정할 때 사용한다. --%>
			<%-- 회사로고 --%>
			<div id="logo">
				<a href="../index.jsp"><img src="../Images/logo1.PNG" width="103" height="35" alt="logo" /></a><br/>
			</div>
			
			<%-- 로그인/회원가입 --%>
			<div id="login">
				<a href="../member/login.jsp"><img src="../Images/login.PNG" width="18" height="13" alt="login" align="center" />로그인</a>
			</div>	
			<div id="join">
				<a href="../member/join.jsp"><img src="../Images/join.PNG" width="17" height="15" alt="join" align="center"/>회원가입</a>
			</div>
			<%-- 상단 메뉴 --%>
			<nav>
				<%-- nav는 html5에서 추가된 태그로 메뉴 구성할 때 사용 --%>
				<ul>
					<li><a href="../articles/community.jsp"><img src="../Images/community1.PNG" width="25" height="25" align="center" alt="community">커뮤니티</a></li>
				</ul>
			</nav>
		</header>

		<div class="clear"></div>