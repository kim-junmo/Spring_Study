<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .uploadResult {
       width: 100%;
       background-color: gray;
    }
    
    .uploadResult ul {
       display: flex;
       flex-flow: row;
       justify-content: center;
       align-items: center;
    }
    
    .uploadResult ul li {
       list-style: none;
       padding: 10px;
    }
    
    .uploadResult ul li img {
       width: 100px;
    }

    .bigPictureWrapper {
      position: absolute;
      display: none;
      justify-content: center;
      align-items: center;
      top:0%;
      width:100%;
      height:100%;
      background-color: gray; 
      z-index: 100;
    }
    
    .bigPicture {
    position: relative;
    display:flex;
    justify-content: center;
    align-items: center;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h3>Ajax를 이용한 업로드</h3>
    <div class="bigPictureWrapper">
        <div class="bigPicture"></div>
    </div>

    <div class="upploadDiv">
    <!-- name이라는 속성은 복수의 의미를 가지고 있고 중복이 될 수 있다. -->
        <input type="file" name="uploadFile" multiple>
    </div>

    <div class="uploadResult">
        <ul></ul>
    </div>
    <button id="uploadBtn">Upload</button>
<script>

    $(document).ready(function() {

        //파일전송 클릭버튼
        $("#uploadBtn").on("click", function() {
            let formData = new FormData(); //form태그에 해당하는 객체

            // <input type="file" name="uploadFile" multiple>를 가르킴. 복수
            // name="uploadFile이 id로 변경이 되고 input#uploadFile이 되면 단수로 변경됨.
            let inputFile = $("input[name='uploadFile']"); 

            //inputFile[0] : 첫번째 태그 <input type="file" name="uploadFile" multiple>를 가르킴
            //inputFile[0].files : 선택한 파일들(name이라는 속성은 복수의 성질을 지니고 있기 때문).
            let files = inputFile[0].files;

            console.log(files);

            for(let i=0; i<files.length; i++) {

            	//uploadFile 이름으로 파일을 전송하고, 스프링에서 참조하게 됨.
            	//즉, 파일들을 uploadFile이 이름으로 보내기 때문에 스프링에서도 동일해야함.
                formData.append("uploadFile", files[i]);
                
                
            }

            $.ajax({
                url:'/upload/uploadAjaxAction',
              //보통 기본값 true으로 생략을 하게 됨, false의미는 key:value값의 구조를 QueryString값으로 변환.
              //QueryString : 주소?문자열을 의미함. 
                processData: false, 
              //보통 기본값 true으로 생략을 하게 됨 "application/x-www-form-urlencoded;charset=UTF-8"  "multipart/form-data" 인코딩을 사용하여 전송.
              //false값 : "multipart/form-data" true값 : "application/x-www-form-urlencoded;charset=UTF-8" 
                contentType: false,	
                data: formData, //스프링으로(서버로)전송한 데이터
                type: 'post',	//요청방식
                dataType: 'json',	//스프링에서 호출된 메서드 리턴(데이터) 타입
                success: function(result) { //result은 컨트롤러에 list가 jsongud식으로 들어간다.

                //업로드 파일목록 정보     
                	for(let i=0; i<result.length; i++) {
                        console.log("날짜폴더명" + result[i].uploadPath);
                    	console.log("클라이언트에서 보낸 파일명" + result[i].fileName); //fileName은 AttachFileDTO형식을 가져다 사용한다.
						console.log("중복되지 않는 파일명", result[i].uuid);
						console.log("이미지파일여부", result[i].image);
                	}
                    
                showUploadedFile(result);
                }
            });
        });


        //파일 삭제 클릭. X버튼 클릭
        //$("정적선택자").on("click", "동적선택자", function() {
            //data-(: 키워드) + (변수명처럼 )이름 : 코드가 숨겨진다.
        $(".uploadResult").on("click", "span", function() {
            console.log("삭제이벤트");

            let targetFile = $(this).data("file"); // data-file
            let type = $(this).data("type"); // data-type

            //파일명에서 날짜 폴더명을 분리해야 한다.
            console.log("targetFile", targetFile);
            console.log("type", type);

            $.ajax({
                url: 'daleteFile',
                data: {fileName : targetFile, type : type}, //자바스크립트 object 파일
                dataType: 'text', //스프링 메서드 리턴타입
                type: 'post',
                success: function(result) {
                    if(result = "success") {
                        alert("이미지가 삭제됨")
                    }
                }
            });
        });


    }); // ready end

    //파일 업로드 목록 정보가 출력될 위치를 참조.
	let uploadResult = $(".uploadResult ul");

    //업로드한 파일 정보를 리스트 형태로 출력.
    //uploadResultArr에 result가 들어옴, 업로드한 파일정보를 지니고 있음.
	function showUploadedFile(uploadResultArr) {
		let str = "";

		$(uploadResultArr).each(function(i, obj) {


			if(!obj.image) {  // 일반파일
				let fileCalPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid);

				str += "<li><div><a href='/upload/download?fileName=" + fileCalPath + "'><img src='/img/attach.png'>" +
				obj.fileName + "</a><span style='cursor:pointer;' data-file=\'" + fileCalPath + "\' data-type='file'> X </span></div></li>";
			}else {			// 이미지파일

                //자바스크립트 인코딩 uri = https://oper0116.tistory.com/17
                //let originPath = obj.uploadPath + "/" + "s_" + obj.uuid //에러발생 400. 역슬래시 문자를 서버에서 허용안함.
				let fileCalPath = encodeURIComponent(obj.uploadPath + "/" + "s_" + obj.uuid);
				let originPath = obj.uploadPath + "\\" + obj.uuid;

				originPath = originPath.replace(new RegExp(/\\/g), "/");

				str += "<li><a href=\"javascript:showImage('" + originPath + "')\"><img src='display?fileName=" + fileCalPath + "'></a>" +
					"<span style='cursor:pointer;' data-file=\'" + fileCalPath + "\' data-type='image'> X </span></li>";
			}
		});

		uploadResult.append(str);
	}

</script>
</body>
</html>