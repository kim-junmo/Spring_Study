<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>상품정보</h3>
<!-- 필드처럼 참조하지만 내부적으로는 getmethod가 사용된다. -->
<p>상품이름? <B>${product.name }</B></p>
<p>상품이름? <B>${product.price }</B></p>
</body>
</html>