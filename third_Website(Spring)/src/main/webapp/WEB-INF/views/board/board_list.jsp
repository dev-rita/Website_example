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
<td colspan="5" align="right">총 게시물 수:<strong>${totalCount}</strong>
</tr>
<tr>
<th>번호</th> <th>제목</th> <th>글쓴이</th> <th>조회수</th> <th>등록날짜</th>
</tr>

<c:if test="${!empty list}">
<c:forEach var="b" items="${list}">
<tr>
<th>${b.bno}</th>
<th><a href="/controller/board/board_cont?bno=${b.bno}&page=${page}&state=cont">${b.title}</a>
<c:if test="${b.replycnt != 0}">&nbsp; &nbsp; &nbsp; <strong>[댓글개수 :${b.replycnt} 개]</strong></c:if>
<!-- &nbsp는 한칸의 빈공백 -->
</th>
<!-- board_cont?bno=번호&page=페이지번호 가 주소창에 노출되는 get방식으로
 bno, page 파라미터 이름에 각각 번호와 페이지번호가 담겨져서 전달된다.-->
 <!-- 뒤에 &state=cont를 추가함. 구분값 cont가 담겨져서 전달된다. -->
<th>${b.writer}</th>
<th>${b.viewcnt}</th>
<th>${b.regdate}</th>
</tr>
</c:forEach>
</c:if>

<c:if test="${empty list}">
<tr>
<th colspan="5">게시물 목록이 없습니다!</th>
</tr>
</c:if>

<%--페이징 즉 쪽나누기 출력 --%>
<tr>
<th colspan="5">
<c:if test="${page <= 1}">
[이전]&nbsp;
</c:if>
<c:if test="${page > 1 }">
<a href="/controller/board/board_list?page=${page-1}">[이전]</a>&nbsp;
</c:if>
<%--쪽번호 출력 --%>
<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
<c:if test="${a == page}"><%--현재 쪽 번호가 선택되었다면 --%>
<${a}>
</c:if>
<c:if test="${a != page}"><%--현재 쪽 번호가 선택 안된 경우 --%>
<a href="/controller/board/board_list?page=${a}">[${a}]</a>&nbsp;
</c:if>
</c:forEach>

<c:if test="${page >= maxpage }">
[다음]
</c:if>
<c:if test="${page < maxpage }">
<a href="/controller/board/board_list?page=${page+1}">[다음]</a>
</c:if>
</th>
</tr>

<tr>
<th colspan="5"><input type="button" value="글쓰기" onclick="location='/controller/board/board_write?page=${page}';"/></th>
<%-- 책갈피 기능(페이징에서 내가 본 쪽번호로 이동하는 기능)을 구현하기 위해서 ?page=페이지번호를 get으로 전달함. --%>
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