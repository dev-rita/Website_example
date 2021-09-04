<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 수정</title>
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/bbs.js"></script>
<link rel="stylesheet" type="text/css" href="./resources/css/bbs.css" />
</head>
<body>
 <div id="bWrite_wrap">
  <h2 class="bWrite_title">자료실 수정</h2>
  <form method="post" action="bbs_edit_ok" onsubmit="return write_check();" enctype="multipart/form-data">
   <%-- 파일 첨부 기능을 만드는 조건: 1. 폼태그 보내는 방식이 method=post이어야 한다.get은 안된다. method속성을 생략하면 기본값이 get이다.그러므로 method속성을 생략하면
                                                       안된다.
                              2. 폼태그 내에 enctype="multipart/form-data"를 지정해야 한다. --%>
   <input type="hidden" name="bbs_no" value="${b.bbs_no}" />
   <%--페이징 책갈피 기능 구현을 위해서 히든으로 페이지 번호를 전달 --%>
   <input type="hidden" name="page" value="${page}" />
                                 
   <table id="bWrite_t">
    <tr>
     <th>글쓴이</th>
     <td><input name="bbs_name" id="bbs_name" size="14" value="${b.bbs_name}" /></td><%--type속성을 생략하면 기본으로 text이다. --%>
    </tr>
    <tr>
     <th>제목</th>
     <td><input name="bbs_title" id="bbs_title" size="34" value="${b.bbs_title}" /></td>
    </tr>
    <tr>
     <th>비밀번호</th>
     <td><input type="password" name="bbs_pwd" id="bbs_pwd" size="14" /></td>
    </tr>
    <tr>
     <th>글내용</th>
     <td>
     <textarea name="bbs_cont" id="bbs_cont" rows="8" cols="36">${b.bbs_cont}</textarea><%-- 주의할 것은 textarea에는 value속성이 없다. --%>
     </td>
    </tr>
    <tr>
     <th>파일첨부</th>
     <td><input type="file" name="bbs_file" /><br/>${b.bbs_file}</td>
    </tr>    
   </table>
   
   <div id="bWrite_menu">
     <input type="submit" value="글수정" />
     <input type="reset" value="취소" onclick="$('#bbs_name').focus();" />
     <input type="button" value="목록" onclick="location='bbs_list?page=${page}';" />
   </div>                           
  </form>
 </div>
</body>
</html>