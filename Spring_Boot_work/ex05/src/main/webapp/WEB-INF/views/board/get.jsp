<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jsp 태그 라이브러리 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    table, th, td {
        border: 1px solid;
    }
    
    table {
        width: 70%;
        border-collapse: collapse;
    }
</style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<!--<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 1) Include Handlebars from a CDN -->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>

<!-- 2) Handlebar Trmplate -->
<script id="reply-template" type="text/x-handlebars-template">
    <table id="replytable">
        {{#each .}} <!--데이터가 복수이면 each를 사용한다.-->
        <tr>
            <td>[<span id="rno_{{rno}}">{{rno}}</span>] <span id="replyer_{{rno}}">{{replyer}}</span> {{convertDate replydate}}</td>
        </tr>
        <tr>
            <td><span id="retext_{{rno}}">{{retext}}</span></td>
        </tr>
        <tr>
            <td>
                <button type="button" name="btnReplyModify" data-rno="{{rno}}" class="btn btn-primary btn-sm">수정</button>
                <button type="button" name="btnReplyDelete" data-rno="{{rno}}" class="btn btn-danger btn-sm">삭제</button>
            </td>
        </tr>
        {{/each}}
    </table>
</script>

<script>
//handlebar Template메서 사용할 사용자 정의 함수 작업
    Handlebars.registerHelper("convertDate", function(replydate) {
    const date = new Date(replydate);
    
    let month = (date.getMonth()+1 < 10 ? "0" + (date.getMonth()+1) : date.getMonth()+1);
    let day = (date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate());
    return date.getFullYear() + "/" + month + "/" + day;
})
</script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">
                    <h3 class="card-title">게시물 조회</h3>
                </div>
                <form id="actionForm" action="/board/list" method="get">
               		<input type="hidden" name="bno" value="${boardVO.bno }">
                	<input type="hidden" name="pageNum" value="${cri.pageNum }">
                	<input type="hidden" name="amount" value="${cri.amount }">
                	<input type="hidden" name="type" value="${cri.type }">
                	<input type="hidden" name="keyword" value="${cri.keyword }">
                </form>
                <form action="/board/get" method="post">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="title">bno</label>
                            <input type="text" class="form-control" id="bno" name="bno" readonly value="${boardVO.bno}">
                        </div>
                        <div class="form-group">
                            <label for="title">title</label>
                            <input type="text" class="form-control" id="title" name="title" readonly value="${boardVO.title}">
                        </div>
                        <div class="form-group">
                            <label for="content">content</label>
                            <textarea class="form-control" rows="3" id="content" name="content" readonly>${boardVO.content}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="writer">writer</label>
                            <input type="text" class="form-control" id="writer" name="writer" readonly value="${boardVO.writer}">
                        </div>
                        <div class="form-group">
   							<label for="regdate">regdate</label>
  							<input type="text" class="form-control" id="regdate" name="regdate" readonly value="<fmt:formatDate value='${boardVO.regdate}' pattern='yyyy-MM-dd' />">
                        </div>
                        <div class="form-group">
                            <label for="updatedate">updatedate</label>
                            <input type="text" class="form-control" id="updatedate" name="updatedate" readonly value="<fmt:formatDate value='${boardVO.updatedate}' pattern='yyyy-MM-dd' />">
                        </div>
                        <div class="form-group">
                            <label for="viewcount">viewcount</label>
                            <input type="text" class="form-control" id="viewcount" name="viewcount" readonly value="${boardVO.viewcount}">
                        </div>
                    </div>
                    <div class="card-footer">
               		<button type="button" class="btn btn-primary" onclick="fn_modify()">Modify</button>
                    <button type="button" class="btn btn-danger" onclick="fn_delete(${boardVO.bno})">Delete</button>
                    <button type="button" class="btn btn-primary" onclick="fn_list();">List</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="btnReplyWrite">
                댓글쓰기
            </button>
            <!-- 댓글목록 위치-->
            <div id = "replyList"></div>
            <!-- 댓글페이지 위치 -->
            <div id = "replyPager"></div>
        </div>
    </div>
</div>

<script>

    let actionForm = document.getElementById("actionForm"); //form태그 참조

    function fn_modify() {
        actionForm.action = "/board/modify";
        actionForm.submit();
    }
    
    function fn_delete(bno) {
        if(!confirm(bno + " 번 게시물을 삭제하겠습니까?")) return;

        //location.href = "/board/delete?bno=" + bno;

        //<form id="actionForm" action="/board/list" method="get">
        //actionForm.setAttribute("action","/board/delete"); //주소변경
        actionForm.action = "/board/delete";
        actionForm.submit();
    }

    function fn_list() {
    	actionForm.setAttribute("action","/board/list");
        actionForm.submit();
    }
    
    //JQuery 작업을 하기 위한 기본.
    //ready()이벤트 메서드. 브라우저가 모든 웹페이지의 내용을 읽고 시작되는 이벤트
    $(document).ready(function() {

        /*
        댓글페이지번호 클릭이벤트 : $("nave ul li a").on("이벤트명", function() {
        아래 선택자에 참조되는 태그가 동적으로 생성된 경우에는 이벤트 설정 불가(엄청 중요)
        $("nave ul li a").on("click", function() {
        })
        동적태그로 이벤트를 설정하는 경우 1. 정적태그를 먼저 찾는다.
        $("정적태그 선택자").on("이벤트명", "동적태그 선택자", function() {
        })
    */
        //동적태그로 이벤트를 설정하는 경우
        //블럭 1    2   3   4   5   6   7   8   9   10 [다음] 
        //버튼을 누를 때 마다 해당 코드가 작동하게 됨. 페이지 번호 값만 변경이 되고 url은 고정.
        //즉, replyPage만 달라짐.
        $("div#replyPager").on("click", "li a", function(e) {
            e.preventDefault(); //a태그의 herf속성의 링크기능 없애기.
            //클릭했던 a 태그를 참조한다.
            replyPage = $(this).attr("href");

            //console.log("페이지", replyPage);
            
            url = "/replies/pages/" + bno + "/" + replyPage;
            getPage(url);
        });

        //댓글쓰기 대화상자 버튼 클릭. document.getElementById("btnReplyWrite")의 기능과 유사하다.
        //$("댓글쓰기 버튼 태그를 참조하는 선택자"), 아이디를 참조할 땐 #을 사용한다.
        $("#btnReplyWrite").on("click", function() {
            // console.log("댓글 버튼 클릭");

            //초기화 작업(수정버튼을 눌렀을 때 기존값을 지우기 위해.)
            $("#reply_rno").html("");
            $("#replyer").val("");
            $("#retext").val("");

            //모달버튼 화면 보임/숨김작업 네임 속성에 btnModalReply이 있는 버튼
            //<button name="btnModalReply"></button>
            $("button[name='btnModalReply']").hide(); //등록, 수정, 삭제 3개 버튼 화면에서 숨김
            $("#btnModalReplySave").show(); //다시 등록버튼 화면에서 보임.

            $("#replyDialog").modal('show');
        });

        //1)모달 대화상자 댓글 등록
        $("#btnModalReplySave").on("click", function() {
            // console.log("댓글등록 버튼 클릭");
            //$("#replyer").val(): <input type='text' id=replyer> 태그의 value값
            //$("#replyer").val(): 댓글 작성자, 내용 읽어오기
            let replyer = $("#replyer").val();
            let retext = $("#retext").val();

            //댓글 데이터를 json포맷으로 서버에 전송.
            // let replyData = {bno: ${boardVO.bno}, replyer}
            //1) 댓글등록데이터를 자바스크립트의 object문법으로 표현
            let replyData = {bno: ${boardVO.bno}, replyer: replyer, retext: retext};

            //2) 댓글데이터를 JSON으로 변환하여 서버에 전송
            // console.log(JSON.stringify(replyData));

            // return;

            $.ajax({
                type: 'post',
                url : '/replies/new',
                headers : {
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"
                },
                dataType: 'text', //스프링 주소의 메서드 리턴타입
                data: JSON.stringify(replyData), //서버로 전송할 json데이터
                success: function(data) {
                    if(data == "success") {
                        alert("댓글 등록됨");
                        let url = "/replies/pages/" + bno + "/" + replyPage;
                        // console.log("url", url); 콘솔 확인 용, 코딩할 때 확인 후 다음과정으로 넘어가야한다.
                        getPage(url);

                        //댓글 작성자, 내용 초기화
                        
                        $("#replyer").val("");
                        $("#retext").val("");

                        //modal dialog 화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });
        });

        //2)모달 대화상자 댓글 수정
        $("#btnModalReplyUpdate").on("click", function() {
            let replyer = $("#replyer").val();
            let retext = $("#retext").val();

            //댓글 데이터를 json포맷으로 서버에 전송.
            //1) 댓글수정데이터를 자바스크립트의 object문법으로 표현
            let replyData = {rno: $("#reply_rno").html(), replyer: replyer, retext: retext};

            //2) 댓글데이터를 JSON으로 변환하여 서버에 전송
            // console.log(JSON.stringify(replyData));

            // return;

            $.ajax({
                type: 'put', //댓글 수정 작업은 rest API에서는 put, patch요청방식 사용.
                url : '/replies/modify',
                headers : {
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "PUT"
                },
                dataType: 'text', //스프링 주소의 메서드 리턴타입
                data: JSON.stringify(replyData), //서버로 전송할 json데이터
                success: function(data) {
                    if(data == "success") {
                        alert("댓글 수정됨");
                        let url = "/replies/pages/" + rno + "/" + replyPage;
                        // console.log("url", url); 콘솔 확인 용, 코딩할 때 확인 후 다음과정으로 넘어가야한다.
                        getPage(url);

                        //댓글 번호, 댓글 작성자, 내용 초기화
                        $("#reply_rno").html(""); // ("") 공백으로 초기화
                        $("#replyer").val(""); // ("") 공백으로 초기화
                        $("#retext").val(""); // ("") 공백으로 초기화

                        //modal dialog 화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });
        });


        //3)모달 대화상자 댓글 삭제
        $("#btnModalReplyDelete").on("click", function() {


            //댓글 데이터를 json포맷으로 서버에 전송.
            //1) 댓글삭제데이터를 자바스크립트의 object문법으로 표현
            let replyData = {rno: $("#reply_rno").html()};

            //2) 댓글데이터를 JSON으로 변환하여 서버에 전송
            // console.log(JSON.stringify(replyData));

            // return;

            $.ajax({
                type: 'delete', //댓글 삭제 작업은 rest API에서는 delete요청방식 사용.
                url : '/replies/delete/' + $("#reply_rno").html(),
                headers : {
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: 'text', //스프링 주소의 메서드 리턴타입
                //data: JSON.stringify(replyData), //서버로 전송할 json데이터
                success: function(data) {
                    if(data == "success") {
                        alert("댓글 삭제됨");
                        let url = "/replies/pages/" + rno + "/" + replyPage;
                        // console.log("url", url); 콘솔 확인 용, 코딩할 때 확인 후 다음과정으로 넘어가야한다.
                        getPage(url);

                        //댓글 번호, 댓글 작성자, 내용 초기화
                        $("#reply_rno").html(""); // ("") 공백으로 초기화
                        $("#replyer").val(""); // ("") 공백으로 초기화
                        $("#retext").val(""); // ("") 공백으로 초기화

                        //modal dialog 화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });
        });

        //댓글 목록에서 수정버튼을 클릭시 $("정적태그선택자").on("이벤트명", "동적태그선택자", function()) {
        $("div#replyList").on("click", "button[name='btnReplyModify']", function() {
            // console.log("수정버튼을 클릭");
            //$(this)는 클릭한 수정버튼을 관리, 
            //parents("table#replytable") : 조상들중 table#replytable 선택자에 해당하는 태그
            //$(this).parents() : <td>태그;
            //$(this).parents().parents(); : <tr>태그; 참조
            let rno = $(this).data("rno"); //<button data-rno="500">수정</button>
            let replyer = $(this).parents("table#replytable").find("#replyer_" + rno).html();
            let retext = $(this).parents("table#replytable").find("#retext_" + rno).html();

            // console.log("rno", rno);
            // console.log("replyer", replyer);
            // console.log("retext", retext);

            //모달버튼 화면 보임/숨김작업 네임 속성에 btnModalReply이 있는 버튼
            //<button name="btnModalReply"></button>
            $("button[name='btnModalReply']").hide(); //등록, 수정, 삭제 3개 버튼 화면에서 숨김
            $("#btnModalReplyUpdate").show(); //다시 수정버튼 화면에서 보임.

            //모달 대화상자에 값을 삽입(출력)하는 작업.
            $("#reply_rno").html(rno); //일반태그인 <span> 태그이기 때문에 html을 사용
            //<input>태그는 val
            $("#replyer").val(replyer); 
            $("#retext").val(retext);

            $("#replyDialog").modal('show');
        });

                //댓글 목록에서 삭제버튼을 클릭시 $("정적태그선택자").on("이벤트명", "동적태그선택자", function()) {
                    $("div#replyList").on("click", "button[name='btnReplyDelete']", function() {
            // console.log("삭제버튼을 클릭");
            //$(this)는 클릭한 삭제버튼을 관리, 
            //parents("table#replytable") : 조상들중 table#replytable 선택자에 해당하는 태그
            //$(this).parents() : <td>태그;
            //$(this).parents().parents(); : <tr>태그; 참조
            let rno = $(this).data("rno"); //<button data-rno="500">삭제</button>
            let replyer = $(this).parents("table#replytable").find("#replyer_" + rno).html();
            let retext = $(this).parents("table#replytable").find("#retext_" + rno).html();

            // console.log("rno", rno);
            // console.log("replyer", replyer);
            // console.log("retext", retext);

            //모달버튼 화면 보임/숨김작업 네임 속성에 btnModalReply이 있는 버튼
            //<button name="btnModalReply"></button>
            $("button[name='btnModalReply']").hide(); //등록, 수정, 삭제 3개 버튼 화면에서 숨김
            $("#btnModalReplyDelete").show(); //다시 수정버튼 화면에서 보임.

            //모달 대화상자에 값을 삽입(출력)하는 작업.
            $("#reply_rno").html(rno); //일반태그인 <span> 태그이기 때문에 html을 사용
            //<input>태그는 val
            $("#replyer").val(replyer); 
            $("#retext").val(retext);

            $("#replyDialog").modal('show');
        });

    });


    //게시물 글번호 확보
    let bno = ${boardVO.bno }; //게시물 번호 511
    let replyPage = 1; //댓글 목록중 1번째 페이지/

    //let url = "댓글 목록과 댓글 페이지 정보를 요청하는 메핑 주소";
    let url = "/replies/pages/" + bno + "/" + replyPage;

    // console.log("url", url); 콘솔 확인 용, 코딩할 때 확인 후 다음과정으로 넘어가야한다.

    getPage(url); //댓글 데이터를 가지고 오는 코드
    //댓글 목록 함수
    function getPage(url) {

        //$.getJSON: ajax 기능을 지원.
        $.getJSON(url, function(data) {
            //replyController의 데이터 이름으로 data.list, data.pageMaker이 
            //function(data)로 데이터가 삽입된다.
            // console.log("list", data.list);
            // console.log("pageMaker", data.pageMaker);
            /*
            let result = "";
            for(let i=0; i<data.list.length; i++) {
                result += "댓글번호; " + data.list[i].rno + "<br>";
                result += "댓글내용; " + data.list[i].retext + "<br>";
            }

            $("#replyList").html(result);  //#replyList : CSS 선택자
            */
            displayReplyData(data.list, $("#replyList"), $("#reply-template"));
            displayReplyPaging(data.pageMaker, $("#replyPager"));
            /*
            data.list : replyData참조
            $("#replyList") : target참조
            $("#reply-template") : template참조
            */
        });
    }
    /*
    댓글목록 데이터 바인딩
    replyData : 댓글목록데이터, 
    target : 댓글목록 출력될 태그위치
    template : 댓글 목록 UI 핸들바 탬플릿
    */
    function displayReplyData(replyData, target, template) {
        let templateObj = Handlebars.compile(template.html());
        let replyHtml = templateObj(replyData);

        //console.log("댓글목록: ", replyHtml);

        target.empty(); // target변수가 참조하는 태그위치에 내용을 지운다.
        target.append(replyHtml); // target변수가 참조하는 태그위치에 자식레벨로 replyHtml변수의 내용을 추가한다.

    }



    //댓글 페이징 작업
    //pageData: 페이징에 필요한 데이터
    //target : 페이징 기능이 삽입된 데이터
    function displayReplyPaging(pageData, target) {
        
        /*
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
        */
        
        let pageStr = '<nav aria-label="Page navigation example">';
        pageStr += '<ul class="pagination">';

    //이전표시여부 작업
    if(pageData.prev) {
        pageStr += '<li class="page-item">';
        pageStr += '<a class="page-link" href="' + (pageData.startPage - 1) + '">Previous</a></li>';
    }

    // 페이지번호 작업
    for(let i = pageData.startPage; i <= pageData.endPage; i++) {
        let curPageClass = (pageData.cri.pageNum == i) ? 'active' : '';
        pageStr += '<li class="page-item ' + curPageClass + '">';
        pageStr += '<a class="page-link" href="' + i + '">' + i + '</a></li>';
    }

    //다음표시여부 작업
    if(pageData.next) {
        pageStr += '<li class="page-item">';
        pageStr += '<a class="page-link" href="' + (pageData.endPage + 1) + '">Next</a></li>';
    }

    pageStr += '</ul></nav>';

    //target변수가 참조하는 태그내용에 pageStr변수의 값을 삽입(대입)
    target.html(pageStr);
}
</script>



<!-- Modal -->
<div class="modal fade" id="replyDialog" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">댓글<span id="reply_rno"></span></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>

   			<div class="modal-body">
        		<div class="form-group">
            	<label for="replyer">replyer</label>
            	<input type="text" class="form-control" id="replyer" placeholder="Enter writer...">
          	</div>
          
        	<div class="form-group">
            	<label for="retext">retext</label>
            	<textarea class="form-control" id="retext" rows="3"></textarea>
        	</div>
    		</div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" name="btnModalReply" id="btnModalReplySave">등록</button>
                <button type="button" class="btn btn-primary" name="btnModalReply" id="btnModalReplyUpdate">수정</button>
                <button type="button" class="btn btn-primary" name="btnModalReply" id="btnModalReplyDelete">삭제</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>