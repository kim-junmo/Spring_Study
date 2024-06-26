<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, td, th {
 	border: 1px solid;
	}
	
	table {
  	width: 100%;
 	border-collapse: collapse;
	}
</style>
</head>
<body>
	<h3>Employees 테이블 리스트</h3>
	<table>
		<tr>
			<th>employee_id</th>
			<th>emp_name</th>
			<th>email</th>
			<th>salary</th>
			<th>commission_pct</th>
			<th>department_id</th>
			<th>update_date</th>
		</tr>
		<c:forEach items="${emp_list}" var="empVO">
			<tr>
				<td>${empVO.employee_id }</td>
				<td>${empVO.emp_name }</td>
				<td>${empVO.email }</td>
				<td>${empVO.salary }</td>
				<td>${empVO.commission_pct }</td>
				<td>${empVO.department_id }</td>
				<td>${empVO.update_date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>