<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 프로그램 아작스로 이진파일 업로드</title>
<script src="./resources/js/jquery.js"></script>
<script>
$(document).ready(function(){
	$('#uploadBtn').on('click',function(e){
		var formData = new FormData();
		//첨부 파일을 업로드 하는 또 다른 방식은 jQuery ajax를 이용해서 파일 데이터만을 전송하는 법이다.
		//ajax를 이용하는 첨부파일 처리는 FormData라는 객체를 이용하는데 IE 10이후 버전부터 지원됨으로 
		//브라우저에 제약이 있을 수 있다.
		
		var inputFile=$("input[name='uploadFile']");//file 객체를 구함. jQuery에서 네임파라미터 이름을 사용하는 법
		
		var files=inputFile[0].files;//첫 번째 파일객체에서 첨부한 파일을 배열로 구한다.
		
		//첨부한 파일을 formData에 추가
		for(var i=0;i<files.length;i++){
			formData.append("uploadFile",files[i]);
		}//for
		
		$.ajax({
			url:'/controller/uploadAjaxAction'//서버 매핑주소
			,processData:false //데이터를 컨텐트 타입에 맞게 변환
			,contentType:false //요청 컨텐트 타입
			,data:formData //ajax를 이용해 폼데이터 자체를 전송
			,type:'POST' //보내는 방식
			,success:function(result){
				//받아오는 것이 성공시 호출, 받아온 자료는 result 매개변수에 저장됨.
			}
		});//jQuery ajax
	});
});

</script>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<input type="file" name="uploadFile" multiple/>
	<hr/>
	<button id="uploadBtn">file Upload</button>
</body>
</html>