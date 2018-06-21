<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>

<!-- css 설정 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" />

<!-- js 설정 -->
<script type="text/javascript"
	src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<title>게시물 목록페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<style type="text/css">
body {
	margin-right: 0px;
}

.ui-tabs .ui-tabs-panel {
	padding: 0px;
	!
	important;
}
</style>
<script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//Tab
		$("#tabs").tabs();
	});

  function dowrite(){ 
	  var userIdReceive = $('#userIdReceive').val();
	  if(userIdReceive==''){
		  alert("받는 사람의 ID를 입력해주세요.");
		  $('#userIdReceive').focus();
		  return;
	  }
	  var title = $('#msg_write_title').val();
	  if(title==''){
		  alert("쪽지제목을 입력해주세요.");
		  $('#msg_write_title').focus();
		  return;
	  }
	  $.ajax({//ajax함수 호출
			url: '/web_portfolio/chkId.do',//호출할 url
			type: "post", //get or post
			data: {'userId' : userIdReceive},//파라미터 키와 값 뒤쪽 userId는 변수이다. 위쪽에 선언되어있다. 유효성 검사에 있다.
			                          //앞의 키이름이 바뀌면 Controller의 키 이름도 바뀌어야한다.
			success: function(result, textStatus, jqXHR){//통신 성공시 //callback함수
				if(result == 1){
					var frm = $('form[name=writeForm]')[0];
					frm.action = "/web_portfolio/msg/write.do";
					frm.method = "post";
					frm.submit();
				}
				else{//중복 또는 문제가 있든...
					//Id가 중복됩니다(경고창)
					alert("Id가 없습니다.");
					//커서를 Id입력하는 곳으로 이동
					$('#userIdReceive').focus();
				}
			
			},
			error : function(jqXHR, textStatus, errorThrown){//통신 실패시
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
		//alert("2번"); //비동기 통신이기 때문에 2번이 먼저 나온다.
  }
  function popupOpen() {
      var popUrl = "<c:url value="/msg/customerList.do"/>"; //팝업창에 출력될 페이지 URL
      var popOption = "width=350, height=500, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)
      window.open(popUrl, "", popOption);
   }
</script>
</head>
<body>

	<!-- board_area -->
	<div class="board_area">
		<form name="writeForm" enctype="multipart/form-data"
			action="/web_portfolio/write.do" method="post">
			<input type="hidden" name="userId" value="${sessionScope.userId}"></input>
			<fieldset>
				<legend>쪽지 글쓰기 박스</legend>

				<!-- board write table -->
				<table summary="쪽지 글쓰기 박스" class="table table-hover">
					<caption>쪽지 글쓰기 박스</caption>
					<colgroup>
						<col width="20%" />
						<col width="80%" />
					</colgroup>
					<c:if test="${msg!=null}">${msg}</c:if>
					<tbody>
						<tr>
							<th class="tright"><label for="msg_write_userIdSend">보내는
									사람</label></th>
							<td class="tleft"><div class=col-xs-2><input class="form-control" type="text" id="userIdSend"
								name="userIdSend" title="보내는 사람 아이디 입력박스" class="input_100"
								value="${sessionScope.userId}" readonly="readonly" /></div></td>
						</tr>
						<tr>
							<th class="tright"><label for="board_write_userIdReceive">받는
									사람</label></th>
							<td class="tleft"><div class=col-xs-2><input class="form-control" type="text" id="userIdReceive"
								name="userIdReceive" value="${friendUserId}"
								title="받는 사람 아이디 입력박스" class="input_100" /></div> <input
								type="button" class="btn btn-primary btn-xs"
								onclick="popupOpen()" value="주소록" title="주소록" /></td>
						</tr>
						<tr>
							<th class="tright"><label for="msg_write_title">제목</label></th>
							<td class="tleft"><div class=col-xs-8><input class="form-control" type="text" id="msg_write_title"
								name="title" title="제목 입력박스" class="input_380" /></div></td>
						</tr>
						<tr>
							<th class="tright"><label for="msg_write_title">내용</label></th>
							<td class="tleft">
								<div class="editer">
									<p>
										<textarea id="contents" class="form-control" name="contents" rows="25" cols="100"></textarea>
										<script>
																CKEDITOR.replace('contents');
															</script>
									</p>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- //board write table -->

				<!-- bottom button -->
				<div class="btn_bottom">
					<div class="btn_bottom_right">
						<a href='<c:url value="/index.do" />'> <input type="button"
							class="btn btn-primary" value="취소" title="취소" />
						</a> <input type="button" class="btn btn-primary" onclick="dowrite()"
							value="쪽지보내기" title="쪽지보내기" />

					</div>
				</div>
				<!-- //bottom button -->

			</fieldset>
		</form>
	</div>
	<!-- //board_area -->


</body>
</html>