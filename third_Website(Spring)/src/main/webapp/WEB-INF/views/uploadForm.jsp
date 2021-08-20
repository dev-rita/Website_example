<%@ page  contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form method="post" action="uploadFormAction" enctype="multipart/form-data">
<%-- 파일을 첨부해서 서버에 업로드 할려면 반드시 post방식이어야 하고, 폼 태그 내에 enctype="multipart/form-data"를 꼭 지정해야 한다. --%>

<input type="file" name="uploadFile" multiple />
<%--최근 브라우저에서는 multiple속성을 사용하여 다중 이진파일을 동시에 서버에 업로드 할 수 있다.
	이 속성은 IE 10이상에서만 사용가능 하다. --%>

<input type="submit" value="파일 업로드"/>

</form>
</body>
</html>