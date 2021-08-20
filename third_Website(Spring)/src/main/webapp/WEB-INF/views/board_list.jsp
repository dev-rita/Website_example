<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 목록</title>
</head>
<body>
<table border="1">
<tr>
<td colspan="4" align="right">총 게시물 수:<strong>${totalCount}</strong>
</tr>
<tr>
<th colspan="4"><input type="button" value="글쓰기" onclick="location='/controller/board_insert';"/></th>
</tr>
</table>
<script>
var msg="${result}";
//자바스크립트에서 스프링 컨트롤러 클래스의 키이름에 저장된 값을 가져올 때 JSTL or EL(표현언어)을 사용할 수 있다.
//여기서는 EL을 사용함.

if(msg=='success'){
	alert('게시물 처리에 성공했습니다!');
}
</script>
</body>
</html>