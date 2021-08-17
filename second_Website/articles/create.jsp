<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="../css/create.css" />
<!DOCTYPE html>
<html>
<head>
<script src="../js/jquery.js"></script>
<script type="text/javascript" src="../api/editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	function w_check() {
		
		var elClickedObj = $("#form");
		oEditors.getById["b_cont"].exec("UPDATE_CONTENTS_FIELD", []);
		var board_type = $("#board_type > option:selected").val(); 
		var title = $("#b_title").val(); 
		var content = $("#b_cont").val(); 
		
		
		if (board_type == "") { 
		alert("카테고리를 선택해주세요.");  
		return false ;} 
		
		if (title == null || title == "") { 
		alert("제목을 입력해주세요."); 
		$("#title").focus(); 
		return false ;
		 } 
		if(content == "" || content == null || content == '<p><br></p>') 

		{ alert("본문을 작성해주세요."); 
		oEditors.getById["b_cont"].exec("FOCUS"); //포커싱 
		return false;
		 } 
		 try {
				var result= confirm("글 작성을 하시겠습니까?");
				if(result==true){//확인
					alert("글 작성이 완료되었습니다!");
	            elClickedObj.submit();//return true;
	            }else{return false;}
	        } catch(e) {}
		
	}
</script>
<meta charset="UTF-8">
<title>새 글 쓰기</title>
<jsp:include page="../include/header2.jsp" />
<div class="line" id="article">
	<h5>새 글 쓰기</h5>
	<div style="width: 300px; height: 50px;"></div>
	<form method="post" action="board8_ok.jsp" onsubmit="return w_check();"><!-- onsubmit="return w_check();" -->
		<div id="member"><a href="#"><img src="../Images/member.PNG" width="40" height="35" alt="member" /></a> <h6><a href="#">글쓴이</a></h6> <br/>
		<img src="../Images/score.PNG" width="10" height="10" alt="score" /><h6>0</h6></div>
		<%--type 속성을 생략하면 기본값으로 text이다. --%>
		<div id="typing">
		<select id="board_type">
		<option value="" selected="selected">게시판을 선택해주세요.</option>
		<option value="community">커뮤니티</option>
		<option value="q&a">Q&amp;A</option>
		</select>
		<div id="title"><input type="text" name="b_title" id="b_title" size="80" Placeholder="제목을 입력해 주세요." /><br/><br/></div>
		<div id="tag"><input type="text" name="b_tag" id="b_tag" size="80" Placeholder="Tags." /><br/><br/></div>
		<div id="cont"><textarea name="b_cont" id="b_cont" rows="10" cols="80"></textarea><br/><br/></div>
		<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
		 oAppRef: oEditors,
		 elPlaceHolder: "b_cont",
		 sSkinURI: "../api/editor/SmartEditor2Skin.html",
		 fCreator: "createSEditor2"
		});
		</script>
		<div id="recapcha"><div>로봇이 아닙니다.</div></div>
		<input class="btn_reset" id=reset type="reset" value="취소" /> <input class="btn_submit" id="submit" type="submit" value='등록' /> <br/><br/>

		</div>
	</form>

	<jsp:include page="../include/footer2.jsp" />
	<script>
		$(document).ready(function(){
			$("#reset").click(function(){
				if(confirm("글 작성을 취소 하시겠습니까?")==true){//확인
				alert('글 작성을 취소합니다.');
				history.go(-1);
				}else{ return false;}
					//this.reset(); 쓴거 없앰
			});

		});
		</script>