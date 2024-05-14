<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩 사용하기 위한 추가 작업 -->
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
<!-- 부트스트랩의 첫 시작은 div태그로 시작을 한다. -->
	<div class="container">
	  
	  <div class="card card-primary">
		<div class="card-header">
			<h3 class="card-title">Quick Example</h3>
		</div>
		
		
		<form>
		<div class="card-body">
		<div class="form-group">
		<label for="exampleInputEmail1">Email address</label>
		<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
		</div>
		<div class="form-group">
		<label for="exampleInputPassword1">Password</label>
		<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
		</div>
		<div class="form-group">
		<label for="exampleInputFile">File input</label>
		<div class="input-group">
		<div class="custom-file">
		<input type="file" class="custom-file-input" id="exampleInputFile">
		<label class="custom-file-label" for="exampleInputFile">Choose file</label>
		</div>
		<div class="input-group-append">
		<span class="input-group-text">Upload</span>
		</div>
		</div>
		</div>
		<div class="form-check">
		<input type="checkbox" class="form-check-input" id="exampleCheck1">
		<label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		</div>
		
		<div class="card-footer">
		<button type="submit" class="btn btn-primary">Submit</button>
		</div>
		</form>
		</div>
	  
	</div>
</body>
</html>