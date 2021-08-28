<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC 게시판 글쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%-- jQuery 라이브러리 cdn 최신 주소 --%>
</head>
<script>
function check(){
	if($.trim($('#writer').val())==''){
		alert('글쓴이를 입력하세요!');
		$('#writer').val('').focus();//입력필드를 초기화(val(''))하고 포커스 이동(focus())
		return false;
	}
	
	if($.trim($('#title').val())==''){
		alert('글 제목을 입력하세요!');
		$('#title').val('').focus();//
		return false;
	}
	
	if($.trim($('#content').val())==''){
		alert('글 내용을 입력하세요!');
		$('#content').val('').focus();//
		return false;
	}
}
</script>
<body>
<form method="post" action="board_write_ok" onsubmit="return check();">
글쓴이:<input type="text" name="writer" id="writer" size="14" placeholder="글쓴이를 입력"/><br/><br/>
<%-- placeholder는 희미하게 안내메시지 같은 문자를 표시 --%> 
글제목:<input type="text" name="title" id="title" size="38" placeholder="글 제목을 입력.."/><br/><br/>
글내용:<textarea name="content" id="content" rows="10" cols="36" placeholder="글 내용을 입력.."></textarea><br/><br/>
<hr/>
<input type="submit" value="입력"/>
<input type="reset" value="취소" onclick="$('#writer').focus();"/>
<%--책갈피 기능 구현 --%>
<input type="button" value="목록" onclick="location='/controller/board/board_list?page=${page}';"/>
</form>
</body>
</html>