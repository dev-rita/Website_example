<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Logout Page</h1>
	
	<form method="post" action="customLogout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<%--POST방식으로 처리되기때문에 CSRF 토큰값을 같이 지정한다. --%>
	
	<input type="submit" value="로그아웃"/>
	</form>
</body>
</html>