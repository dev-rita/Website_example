<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css" href="../css/admin.css"/>
</head>
<body>
<div id="aMain_wrap">
<%--관리자 페이지 메인 상단 --%>
<div id="aMain_header">
<%-- 관리자 로고 --%>
<div id="aMain_logo">
<a href="admin_index.jsp">
<img src="../Images/admin/admin_logo.png" alt="관리자 로고 그림"/>
</a>
</div>

<%--관리자 상단 메뉴 --%>
<div id="aMain_menu">
<ul>
<li><a href="#">공지사항</a></li>
<li><a href="#">게시판</a></li>
<li><a href="#">자료실</a></li>
<li><a href="#">회원관리</a></li>
</ul>
</div>

<%--우측메뉴 --%>
<div id="aMain_right">
<form>
<h3 class="aRight_title">관리자님 로그인을 환영합니다. <input type="submit" value="로그아웃"/></h3>
</form>
</div>
</div>

<div class="clear"></div>

<%--관리자 메인 본문 --%>
<div id="aMain_cont">
<h2 class="aMainCont_title">관리자 메인 화면입니다.</h2>
</div>
<div class="clear"></div>

<%--관리자 메인 하단 --%>
<div id="aMain_footer">
<h2 class="aFooter_title">서울시 동작구 장승배기로 7길 00빌딩 00호. TEL)02-0000-0000</h2>
</div>
</div>
</body>
</html>