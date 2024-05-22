<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <!-- jsp 태그 라이브러리 -->
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<!--<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
            <div class="card card-primary">
            <div class="card-header">
            <h3 class="card-title">글 수정</h3>
            </div>
            <form action="/board/modify" method="post">
            
            <div class="card-body">
             <div class="form-group">
            <label for="title">bno</label>
            <input type="text" class="form-control" id="bno" name="bno" value="${board.bno }" readonly>
            </div>
            
            <div class="form-group">
            <label for="title">title</label>
            <input type="text" class="form-control" id="title" placeholder="Enter title" value="${board.title }">
            </div>
            <div class="form-group">
                <label for="content">content</label>
                <input type="text" class="form-control" id="content" placeholder="Enter content" value="${board.content }">
            </div>
            <div class="form-group">
                <label for="writer">writer</label>
                <input type="text" class="form-control" id="writer" placeholder="Enter writer" value="${board.writer }">
                </div>
            </div>
        </div>
        
        <div class="card-footer">
        <button type="submit" class="btn btn-primary">Submit</button>
        </div>
        </form>
        </div>

</body>
</html>