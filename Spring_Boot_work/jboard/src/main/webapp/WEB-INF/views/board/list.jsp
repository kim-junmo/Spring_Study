<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!-- jsp 태그 라이브러리 -->
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

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
			<div class="card-header">
			<h3 class="card-title">글쓰기 폼</h3>
		</div>
		
		<div class="card-body">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th style="width: 10%">Bno</th>
						<th style="width: 50%">Title</th>
						<th style="width: 10%">Write</th>
						<th style="width: 20%">regdate</th>
						<th style="width: 10%">viewcount</th>
					</tr>
				</thead>
			<tbody>
			<c:forEach items="${list }" var="boardVO">
				<tr>
				<td>${boardVO.bno }</td>
				<td><a href="/board/get?bno=${board.bno }&type=${board.cri.type}&keyword=${board.cri.keyword }&pageNum=${board.cri.pageNum }&amount=${board.cri.amount }&">${board.title }</a></td>
					<td>${boardVO.writer }</td>
					<td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd"/></td>
					<td>${boardVO.viewcount }</td>
				</tr>
			</c:forEach>
	
		</tbody>
		</table>
		</div>
		
		<div class="card-footer clearfix">
			<div class="float-left">
				<form id="searchForm" action="/board/list" method="get">
					<div class="form-group">
						<select name="type">
							<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected' : '' }" />>---</option>
							<option value="T" <c:out value="${pageMaker.cri.type == 'T' ? 'selected' : '' }" />>제목</option>
							<option value="C" <c:out value="${pageMaker.cri.type == 'C' ? 'selected' : '' }" />>내용</option>
							<option value="W" <c:out value="${pageMaker.cri.type == 'W' ? 'selected' : '' }" />>작성자</option>
							<option value="TC" <c:out value="${pageMaker.cri.type == 'TC' ? 'selected' : '' }" />>제목 or 내용</option>
							<option value="TW" <c:out value="${pageMaker.cri.type == 'TW' ? 'selected' : '' }" />>제목 or 작성자</option>
							<option value="TCW" <c:out value="${pageMaker.cri.type == 'TCW' ? 'selected' : '' }" />>제목 or 내용 or 작성자</option>
						</select>
						<input type="text" name="keyword" placeholder="검색어를 입력해주세요" value="${board.cri.keyword }">
						<input type="hidden" name="pageNum" value="1">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
						<input type="submit" name="btn btn-primary btn-sm" value="search">
					</div>
				</form>
			</div>
			
			<ul class="pagination pagination-sm m-0 float-right">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.startPage-1 }&amount=${pageMaker.cri.amount}">이전</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="page">
					<li class="page-item" ${pageMaker.cri.pageNum == page?"action" : ""}>
					<a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.cri.pageNum }&amount=${pageMaker.cri.amount}">${page }</a></li>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<li class="page-item"><a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.cri.pageNum }&amount=${pageMaker.cri.amount}">다음</a></li>
				</c:if>
			</ul>
		</div>
	</div>
        </div>
    </div>

<script>

${document}.ready(function() {
	
	let actionForm = $("actionForm");
	
	$("a.move").on("click", function(e) {
		e.preventDefault();
		
		let bno = $(this).attr("href");
		$("input[name='bno']").val(bno);
		
		actionForm.attr("attr", "/board/get");
		actionForm.submit();
	});
	
	$("ul.pagination a.page-link").on("click", function(e) {
		e.preventDefault();
		
		$("input[name='pageNum']").val($(this).attr("href"));
		
		actionForm.attr("action", "/board/list");
		actionForm.submit();
		
	});
	
});
</script> 
</body>
</html>