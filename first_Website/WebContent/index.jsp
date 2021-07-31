<%@ page contentType="text/html; charset=UTF-8"%>
<%-- jsp 주석문 기호. jsp주석문은 html주석문과는 다르게 브라우저 출력창 뿐만 아니라 페이지 소스보기에서도 안보인다.
그래서 보안성이 우수하다. 상단,하단 코드를 반복하지 않기 위해서 jsp사용한다.-중복x, html코드 포함.
%@는 지시자. page 디렉티브 지시자로 어떤 jsp의 정의를 내리는 부분이다.
contentType="text/html; charset=UTF-8"속성값의 뜻은 브라우저에 출력되는 문자와 HTML태그적용, 언어 코딩 타입을 utf-8로 설정한다.
db(jdbc)연동 안되는 부분 먼저 ui 만들기--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Fun Web</title>
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<%--외부 스타일 시트 방법--%>
</head>
<body>
	<%-- 헤더영역 --%>
	<div id="wrap">
		<header>
			<%--header는 html5에서 추가된 태그로 상단 머릿 부분을 지정할 때 사용한다. --%>
			<div id="login">
				<a href="#">login</a> |<a href="./member/join.jsp"> Join</a>
			</div>
			<%-- 회사로고 --%>
			<div id="logo">
				<img src="./Images/logo.gif" width="265" height="62" alt="Fun Web" />
			</div>

			<%-- 상단 메뉴 --%>
			<nav>
				<%-- nav는 html5에서 추가된 태그로 메뉴 구성할 때 사용 --%>
				<ul>
					<li><a href="index.jsp">HOME</a></li>
					<li><a href="./company/welcome.jsp">COMPANY</a></li>
					<li><a href="#">SOLUTIONS</a></li>
					<li><a href="./center/notice.jsp">CUSTOMER CENTER</a></li>
					<li><a href="#">CONTACT US</a></li>
				</ul>
			</nav>
		</header>

		<div class="clear"></div>

		<%--메인 이미지--%>
		<div id="main_img">
			<img src="./Images/main_img.jpg" width="971" height="282" />
		</div>

		<div class="clear"></div>

		<%--메인 본문 --%>
		<article id="main_cont">
			<%-- article은 html5에서 추가된 태그로 본문 내용을 지정할 때 사용한다. --%>
			<div id="solution">
				<div id="hosting">
				<h3>Web Hosting Solution</h3>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				</div>
				
				<div id="security">
				<h3>Web Security Solution</h3>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				</div>
				
				<div id="payment">
				<h3>Web payment Solution</h3>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				<p>Lorem .. UT vel est</p>
				</div>
			</div>
			
			<div class="clear"></div>
			
			<div id="sec_news">
			<h3><span class="orange">Security</span> News</h3>
			<dl><%--dl은 정의 리스트 dt는 제목 dd는 설명--%>
			<dt><a href="#">Vivamus ... quis... nisi.</a></dt>
			<dd><a href="#">Proin ... ante... risus.</a></dd>
			<dt><a href="#">Vivamus ... quis... nisi.</a></dt>
			<dd><a href="#">Proin ... ante... risus.</a></dd>
			<dt><a href="#">Vivamus ... quis... nisi.</a></dt>
			<dd><a href="#">Proin ... ante... risus.</a></dd>
			<dt><a href="#">Vivamus ... quis... nisi.</a></dt>
			<dd><a href="#">Proin ... ante... risus.</a></dd>
			</dl>
			</div>
			
			<div id="news_notice">
			<h3 class="brown">News &amp; Notice</h3><%-- &amp;는 &기호를 표시한다. --%>
			<table>
			<tr>
			<td class="contxt"><a href="#">Vivamus ... quis... nisi.</a></td>
			<td>2021.06.18</td>
			</tr>
			<tr>
			<td class="contxt"><a href="#">Vivamus ... quis... nisi.</a></td>
			<td>2021.06.18</td>
			</tr>
			<tr>
			<td class="contxt"><a href="#">Vivamus ... quis... nisi.</a></td>
			<td>2021.06.18</td>
			</tr>
			<tr>
			<td class="contxt"><a href="#">Vivamus ... quis... nisi.</a></td>
			<td>2021.06.18</td>
			</tr>
			<tr>
			<td class="contxt"><a href="#">Vivamus ... quis... nisi.</a></td>
			<td>2021.06.18</td>
			</tr>
			</table>
			</div>
		</article>
		 <div class="clear"></div>
		<%-- 메인하단 --%>
		<footer>
			<%--footer는 html5에서 추가된 태그로 하단영역을 지정할 때 주로 사용한다. --%>
		<hr/>
		<div id="copy">
		All contents Copyright 2021 FunWeb Inc. all rights reserved<br/>
		Contact mail : funweb@funwebbiz.com | Tel +82 64 123 4315 | Fax +82 62 123 4321
		</div>
		
		<div id="social">
		<img src="./Images/facebook.gif" width="33" height="33" alt="FaceBook"/>
		<img src="./Images/twitter.gif" width="33" height="34" alt="Twitter"/>
		</div>
		</footer>
	</div>
</body>
</html>