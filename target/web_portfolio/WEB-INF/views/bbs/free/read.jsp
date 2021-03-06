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
<%--테이블 행 펼치고/접기 --%>
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

<script type="text/javascript">
$(document).ready(function(){
	//Tab
	$( "#tabs" ).tabs();
});
// 접기/펼치기
var bDisplay = true;
function doDisplay(){
    var con = document.getElementById("myDIV");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}

//delete
function doDelete(){
	//document는 body부분을 뜻한다. readForm은 body안의 Form태그의 name이다.
	if(confirm("정말 삭제 하시겠습니까?")){
	var frm = document.readForm;
	frm.action = "/web_portfolio/delete.do"
	frm.method = "POST";
	frm.submit();
	}
}
function goUpdate(){
	var frm = document.readForm;
	frm.action = "/web_portfolio/goUpdate.do"
	frm.method = "POST";
	frm.submit();
}
function doCmt(currentPageNo){
	var frm = document.readForm;
	frm.action ="/web_portfolio/cmtInsert.do?currentPageNo="+currentPageNo;
	frm.method ="POST";
	frm.submit();
}
function docmtDelete(seq){
	var id = $('#id').val();
// 	var userId = $('#userId').val();
	if(id==''){
		alert("로그인을 해주세요");
		return;
	}
	var frm = document.readForm;
	frm.action ="/web_portfolio/cmtDelete.do?seq="+seq;
	frm.method ="POST";
	frm.submit();
}
</script>
</head>
<body>
	<!-- board_area -->
	<div class="board_area">
		<form name="readForm">
			<input type="hidden" name="id" id="id" value="${sessionScope.userId}"></input>
			<input type="hidden" name="userId" id="userId" value="${board.userId}"></input>
			<input type="hidden" name="num" value="${board.num}"></input>
			<input type="hidden" name="currentPageNo" value="${currentPageNo}"></input>
			<fieldset>
				<legend>게시물 상세 내용</legend>
				<!-- board detail table -->
				<table summary="표 내용은 게시물의 상세 내용입니다." class="table table-hover">
					<caption>Sea & Food 게시물 상세 내용</caption>
					<colgroup>
						<col width="%" />
						<col width="%" />
						<col width="%" />
						<col width="%" />
						<col width="%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="tright">제목</th>
							<td colspan="5" class="tleft">${board.title}</td>
						</tr>
						<tr>
							<th class="tright">작성자</th>
							<td colspan="5" class="tleft">${board.userName}</td>
						</tr>
						<tr>
							<th class="tright">작성일</th>
							<td>${board.completeDate }</td>
							<th class="tright">조회수</th>
							<td class="tright">${board.hits}</td>
						</tr>
						<tr>
							<td colspan="6" class="tleft">
								<div class="body">${board.contents}</div>
							</td>
						</tr>
						<tr>
							<th class="tright">첨부파일</th>
							<td colspan="5" class="tleft"><c:forEach
									items="${attachments}" var="att">
									<a href="<c:url value='/bbs/free/fileDownload.do?attachSeq=${att.attachSeq}'/>">
										${att.filename} (<fmt:formatNumber pattern="#,##0"
											value="${att.fileSize/1024}" />KB)</br>
								</c:forEach> </a></td>
						</tr>
						<tr>
							<th class="tright">비밀번호</th>
							<td colspan="5" class="tleft"><input type="password"
								name="password" title="비밀번호 입력박스"
								class="form-control input_100" /> <c:if
									test="${msg!=null}">${msg}</c:if></td>
						</tr>
						<tr height="5">
							<th colspan="6"><댓글></th>
						</tr>
						<c:forEach items="${comment}" var="comment">
							<tr>
								<th class="tcenter" width="100"><blink> <big>${comment.nickname}</big></blink>
									<br /> <small>${comment.createDate}</small></th>
								<td colspan="5" class="tleft">${comment.contents}
									<div class="btn_bottom_right">
										<input type="button" class="btn btn-primary"
											onclick="docmtDelete(${comment.seq})" value="삭제" title="댓글삭제" />
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6" class="tleft"><textarea id="contents"
									name="contents" rows="4" cols="120"></textarea>
								<div class="btn_bottom_right">
									<input type="button" class="btn btn-primary" onclick="doCmt(${currentPageNo})" value="입력" title="댓글입력" />
								</div></td>
						</tr>
					</tbody>
				</table>
				<!-- //board detail table -->

				<!-- bottom button -->
				<div class="btn_bottom">
					<div class="btn_bottom_right">
						<c:if test="${sessionScope.userId == board.userId}">

							<input type="button" class="btn btn-primary" onclick="goUpdate()" value="수정" title="수정" />

							<input type="button" class="btn btn-primary" onclick="doDelete()" value="삭제" title="삭제" />
						</c:if>
						<a href="/web_portfolio/bbs/free/list.do?currentPageNo=${currentPageNo}">
							<input type="button" class="btn btn-primary" value="목록" title="목록" />
						</a>
					</div>
				</div>
				<!-- //bottom button -->
			</fieldset>
		</form>
	</div>
	<!-- //board_area -->
</body>
</html>