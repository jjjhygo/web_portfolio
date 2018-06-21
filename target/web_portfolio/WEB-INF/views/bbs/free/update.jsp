<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- tag library 선언 : fmt tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<!-- css 설정 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" />
<!-- js 설정 -->
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

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
<!-- editor -->
<script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	//Tab
	$( "#tabs" ).tabs();
});
function doDelete(attachSeq){   // 매개변수 데이터타입 필요 없음 (자바 스크립트에는 타입이 존재하지 않음)
// 	   if(confirm("첨부파일을 삭제하시겠습니까?")){
// 	         window.location.href='/web_portfolio/delAttach.do?attachSeq=' + attachSeq + '&currentPageNo=${currentPageNo}';
alert("첨부파일을 삭제하시겠습니까?")
var frm = document.readForm;
	frm.action = "/web_portfolio/delAttach.do?attachSeq=" + attachSeq + "&currentPageNo=${currentPageNo}";
	frm.method = "POST";
	frm.submit();
	      }
</script>
</head>
<body>
	<!-- board_area -->
	<div class="board_area">
		<form action="/web_portfolio/update.do" name="readForm"
			enctype="multipart/form-data" method="post">
			<input type="hidden" name="num" value="${board.num}"></input> <input
				type="hidden" name="currentPageNo" value="${currentPageNo}"></input>
			<fieldset>
				<legend>게시글 수정</legend>

				<!-- board write table -->
				<table summary="표 내용은 게시글 수정 박스입니다." class="table table-hover">
					<caption>게시글 수정 박스</caption>
					<colgroup>
						<col width="20%" />
						<col width="80%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="tright"><label for="board_write_name">글번호</label></th>
							<td class="tleft"><input type="text" id="seq"
								value="${board.num}" readOnly="readOnly" title="글번호 입력박스"
								class="input_100" /></td>

							<tr>
								<th class="tright"><label for="board_write_name">작성자</label></th>
								<td class="tleft"><input type="text" name="userName"
									value="${board.userName}" readOnly="readOnly" id="name"
									title="작성자 입력박스" class="input_100" /></td>
							</tr>
							<tr>
								<th class="tright"><label for="board_write_title">제목</label></th>
								<td class="tleft"><input type="text" name="title"
									value="${board.title }" id="board_write_title" title="제목 입력박스"
									class="input_380" /></td>
							</tr>
							<tr>
								<th class="tright"><label for="board_write_title">내용</label></th>
								<td class="tleft">
									<div class="editer">
										<p>
											<textarea name="contents" rows="25" cols="75">${board.contents}</textarea>
											<script>
																	CKEDITOR.replace( 'contents' );
																</script>
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<th class="tright" rowspan="2"><label
									for="board_write_title">첨부파일</label></th>
								<td class="tleft"><c:choose>
										<c:when test="${attachments != null}">
											<c:forEach items="${attachments}" var="attach">
												<a
													href="<c:url value='/bbs/free/fileDownload.do?attachSeq=${attach.attachSeq}'/>">
													${attach.filename} (<fmt:formatNumber pattern="#,##0"
														value="${attach.fileSize}" /> KB)
												</a>
												<input type="button" class="btn btn-primary" onclick="doDelete(${attach.attachSeq})"
													value="삭제" title="삭제" />
												<br />
											</c:forEach>
										</c:when>
										<c:otherwise>
											<input type="file" class="btn btn-primary btn-xs" name="attachFile" />
											<br />
											<input type="file" class="btn btn-primary btn-xs" name="attachFile" />
										</c:otherwise>
									</c:choose></td>
							</tr>
					</tbody>
				</table>
				<!-- //board write table -->

				<!-- bottom button -->
				<div class="btn_bottom">
					<div class="btn_bottom_right">
						<a
							href='<c:url value="/bbs/free/list.do?currentPageNo=${currentPageNo}" />'>
							<input type="button" class="btn btn-primary" value="취소" title="취소" />
						</a> <input type="submit" class="btn btn-primary btn-xs" value="완료" title="완료" />
					</div>
				</div>
				<!-- //bottom button -->

			</fieldset>
		</form>
	</div>
	<!-- //board_area -->
</body>
</html>