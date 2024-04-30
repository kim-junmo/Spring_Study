<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시판 글쓰기</h3>
<!-- form태그는 기본적으로 post를 사용한다. -->
<!-- name은 VO의 필드명과 일치시켜야 한다. 그래서 복붙 -->
<form action="/board/write" method="post"> 
	제목: <input type="text" name ="title"><br>
	내용: <input type="text" name ="content"><br>
	작성자: <input type="text" name ="writer"><br>
	<input type="submit" value="글저장">
</form>
</body>
</html>