<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jsp 태그 라이브러리 -->
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Borad List</h3>
                </div>
                <div class="card-body">
                <table class="table table-bordered">
                <thead>
                <tr>
                    <th style="width: 10%">bno</th>
                    <th style="width: 30%">title</th>
                    <th style="width: 25%">writer</th>
                    <th style="width: 25%">regdate</th>
                    <th style="width: 10%">viewcount</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list }" var="boardVO">
                    <tr>
                        <td>${boardVO.bno }</td>
                        <td><a href="/board/get?bno=${boardVO.bno }">${boardVO.title }</a></td>
                        <td>${boardVO.writer }</td>                
                        <td>
                        <fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd" /></td>
                        <td>${boardVO.viewcount }</td>
                    </tr>
                    </c:forEach>
                    </tbody>
					</table>
					</div>
                
                <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                <li class="page-item"><a class="page-link" href="#">«</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">»</a></li>
                </ul>
                </div>
                </div>
        </div>
    </div>
</div>
</body>
</html>