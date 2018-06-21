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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
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
</script>
<script>
  function doReply(){ 
	  var title = $('#msg_write_title').val();
	  if(title==''){
		  alert("쪽지제목을 입력해주세요.");
		  $('#msg_write_title').focus();
		  return;
	  }
	  var frm = document.replyForm;
	  frm.action ="/web_portfolio/msg/reply.do?receive=${receive}&currentPageNo=${currentPageNo}&seq=${seq}";
	  frm.method = "POST";
	  frm.submit();
  }
</script>
</head>
<body>
	<!-- board_area -->
	<div class="board_area">
		<form name="replyForm" enctype="multipart/form-data" method="post">
			<input type="hidden" name="userId" value="${sessionScope.userId}"></input>
			<fieldset>
				<legend>Sea & Food 글쓰기</legend>

				<!-- board write table -->
				<table summary="표 내용은 Message 답장 박스입니다." class="table table-hover">
					<caption>Message 답장 박스</caption>
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
								value="${receive }" readonly="readonly" /></div></td>
						</tr>
						<tr>
							<th class="tright"><label for="board_write_userIdReceive">받는
									사람</label></th>
							<td class="tleft"><div class=col-xs-2><input class="form-control" type="text" id="userIdReceive"
								name="userIdReceive" value="${send}" readonly="readonly"
								title="받는 사람 아이디 입력박스" class="input_100" /></div></td>
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
										<textarea id="contents" name="contents" rows="25" cols="100"></textarea>
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
						<a
							href='<c:url value="/msg/readMsg.do?receive=${receive}&currentPageNo=${currentPageNo}&seq=${seq }" />'>
							<input type="button" class="btn btn-primary" value="취소" title="취소" />
						</a> <input type="button" class="btn btn-primary" onclick="doReply()" value="쪽지보내기"
							title="쪽지보내기" />

					</div>
				</div>
				<!-- //bottom button -->

			</fieldset>
		</form>
	</div>
	<!-- //board_area -->
</body>
</html>