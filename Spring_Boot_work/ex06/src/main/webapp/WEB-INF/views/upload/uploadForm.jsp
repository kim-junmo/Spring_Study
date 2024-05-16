<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Form 태그를 이용한 파일 업로드</h3>
<!-- Form 태그를 enctype="application/x-www-form-urlencoded" 기본값, 생략함, -->
<form method="post" action="uploadFormAction" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple> <!-- multiple: 파일을 복수로 선택가능하게 해준다. -->
	<button type="submit">전송</button>
</form>
</body>
</html>