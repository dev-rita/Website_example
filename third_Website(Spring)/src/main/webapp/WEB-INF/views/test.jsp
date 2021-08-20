<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery ajax 댓글</title>
<style>
#modDiv{
width:300px; height:100px; background-color:gray;
position:absolute; top:50%; left:50%;
margin-top:-50px; margin-left:-150px;
padding:10px;
z-index:1000;/*position속성이 absolute or fixed로 설정된 곳에서 사용한다.
이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 값이 큰 것이 앞에 나온다.*/
}
</style>
</head>
<body>
<%--댓글 수정화면 --%>
<div id="modDiv" style="display:none;"> <%--css에서 display:none;은 해당영역을 안보이게 한다. --%>
<div class="modal-title"></div><%--수정창에서 댓글 번호가 출력되는 부분 --%>
<div>
<textarea rows="3" cols="30" id="replytext"></textarea>
</div>
<div>
<button type="button" id="replyModBtn">댓글 수정</button>
<button type="button" id="replyDelBtn">댓글 삭제</button>
<button type="button" onclick="modDivClose();">닫기</button>
</div>
</div>

<%--댓글 등록 --%>
<div>
<div>
댓글 작성자:<input type="text" name="replyer" id="newReplyWriter"/>
</div>
<br/>
<div>
댓글 내용:<textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
</div>
<br/>
<button id="replyAddBtn">댓글등록</button>
</div>

<br/>
<hr/>
<br/>

<%--댓글 목록 --%>
<ul id="replies"></ul>

<%--jQuery 라이브러리 읽어옴 --%>
<script src="./resources/js/jquery.js"></script>
<script>
var bno=3;//게시판 번호

getAllList();//댓글목록함수
function getAllList(){
	$.getJSON("/controller/replies/all/"+bno,function(data){
		//비동기식으로 받아오는 것이 성공시 받아온 데이터는 data매개변수에 저장
		var str="";
		$(data).each(function(){//each()함수로 반복
			str+="<li data-rno='"+this.rno+"' class='replyLi'>"
			+this.rno+" : <span class='com' style='color:blue;font-weight:bold;'>"
			+this.replytext+"</span>"+"<button>댓글수정</button></li><br/>";
		});
		$('#replies').html(str);//해당영역에 태그와 문자를 함께 변경 적용
	});
}//getAllList()

//댓글 등록
$('#replyAddBtn').on("click",function(){
         var replyer = $('#newReplyWriter').val();//댓글 작성자
         var replytext = $('#newReplyText').val();//댓글 내용
         
         $.ajax({
            type:'post',
            url:'/controller/replies',
            headers:{
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "POST"
            },
            dataType:'text',
            data:JSON.stringify({
               bno:bno,
               replyer:replyer,
               replytext:replytext
            }),
            success:function(result){//받아오는 것이 성공시 호출됨
               if(result == 'SUCCESS'){
                  alert('등록 되었습니다!');
                  getAllList();
               }
            }
         });
      });

//댓글 수정화면
$('#replies').on("click",".replyLi button",function(){
	var reply=$(this).parent();//parent() 부모 요소는 li태그를 가리킴
	var rno=reply.attr('data-rno');//댓글 번호
	var replytext=reply.children('.com').text();//댓글내용 문자만 가져옴.
	
	$(".modal-title").html(rno);
	$("#replytext").val(replytext);
	$('#modDiv').show('slow');
});

//댓글 수정화면 닫기
function modDivClose(){
	$('#modDiv').hide('slow');
}

//댓글 수정 완료
$('#replyModBtn').on('click',function(){
	var rno=$('.modal-title').html();//댓글번호
	var replytext=$('#replytext').val();//수정할 댓글 내용

	$.ajax({
		type:'put',
		url:'/controller/replies/'+rno,
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"PUT"
		},
		data:JSON.stringify({replytext:replytext}),
		dataType:'text',
		success:function(result){
			if(result == 'SUCCESS'){
				alert('댓글이 수정 되었습니다!');
				$('#modDiv').hide('slow');//댓글 수정화면을 감춘다.
				getAllList();//댓글목록 호출
			}
		}
	});
});

//댓글 삭제
$('#replyDelBtn').on("click",function(){
	var rno=$('.modal-title').html();//댓글 번호
	
	$.ajax({
		type:'delete',
		url:'/controller/replies/'+rno,
		headers:{
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override":"DELETE"
		},
		dataType:'text',
		success:function(result){
			if(result =="SUCCESS"){
				alert('댓글이 삭제 되었습니다!');
				$('#modDiv').hide('slow');
				getAllList();
			}
		}
	});
});
</script>
</body>
</html>