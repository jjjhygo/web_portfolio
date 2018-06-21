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
<%--펼치기/접기--%>
</style>
<!-- 접기/펼치기 -->


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
function doCmt(){
	var frm = document.readForm;
	frm.action ="/web_portfolio/cmtInsert.do";
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
			<input type="hidden" name="userId" id="userId"
				value="${board.userId}"></input> <input type="hidden" name="num"
				value="${board.num}"></input> <input type="hidden"
				name="currentPageNo" value="${currentPageNo}"></input>
			<fieldset>
				<legend>쪽지 내용</legend>
				<!-- board detail table -->
				<table summary="표 내용은 쪽지 내용입니다." class="table table-hover">
					<caption>Sea & Food 게시물 상세 내용</caption>
					<colgroup>
						<col width="%" />
						<col width="%" />
						<col width="%" />
						<col width="%" />
						<col width="%" />
						<col width="%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="tright" width="90">보낸사람</th>
							<td colspan="5" class="tleft">${msg.userIdSend}</td>
						</tr>
						<tr>
							<th class="tright">받은사람</th>
							<td colspan="5" class="tleft">${msg.userIdReceive}</td>
						</tr>
						<tr>
							<th class="tright">제목</th>
							<td colspan="5" class="tleft">${msg.msgTitle}</td>
						</tr>
						<tr>
							<td colspan="6" class="tleft">
								<div class="body">${msg.msgContents}</div>
							</td>
						</tr>
						<tr>
							<th class="tright">작성일</th>
							<td class="tleft">${msg.sendDate}</td>
						</tr>
					</tbody>
				</table>
				<!-- //board detail table -->

				<!-- bottom button -->
				<div class="btn_bottom">

					<div class="btn_bottom_right">
						<c:if test="${send!=null}">
							<a
								href="/web_portfolio/msg/list.do?currentPageNo=${currentPageNo}&send=${send}">
								<input type="button" class="btn btn-primary" value="목록"
								title="목록" />
							</a>
						</c:if>
						<c:if test="${receive!=null}">
							<a
								href="/web_portfolio/msg/list.do?currentPageNo=${currentPageNo}&receive=${receive}">
								<input type="button" class="btn btn-primary" value="목록"
								title="목록" />
							</a>
						</c:if>
					</div>
					<div class="btn_bottom_right">
						<c:if test="${receive!=null }">
							<a
								href="/web_portfolio/msg/goReply.do?seq=${msg.msgSeq }&receive=${receive}&currentPageNo=${currentPageNo}">
								<input type="button" class="btn btn-primary" value="답장"
								title="답장" />
							</a>
						</c:if>

					</div>
				</div>
				<!-- //bottom button -->

			</fieldset>
		</form>
	</div>
	<!-- //board_area -->
</body>
</html>