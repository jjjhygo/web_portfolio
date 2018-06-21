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

<title>받은 쪽지 목록페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<style type="text/css">
body {
	margin-right: 0px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	//Tab
	$("#tabs").tabs();
	
	$('#btnSearch').click(function (){
		var type = $('#searchType option:selected').val();
		var text = $('#searchText').val();
		//배열로 나오고 0번째 값을 꺼낸다.
		var frm= $('#searchForm')[0];
		frm.action ='${pageContext.request.contextPath}/bbs/free/list.do';
		frm.submit();
	});
});
</script>
</head>
<body>
<br />
						<!-- board_area -->
						<div class="board_area">
							<form method="get">
							
								<fieldset>
									<legend>받은 쪽지함</legend>
									<!-- board list table -->
									<table summary="표 내용은 받은 쪽지함 목록입니다."
										class="table table-hover">
										<colgroup>
											<col width="10%" />
											<col width="15%" />
											<col width="50%" />
											<col width="25%" />
										</colgroup>
										<thead>
											<tr>
												<th scope="col">수신확인</th>
												<th scope="col">보낸사람</th>
												<th scope="col">제목</th>
												<th scope="col">보낸일자</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${result}" var="result">
												<tr>
													<td>
													<c:choose>
													<c:when test="${result.msgCheck==1}">
													읽음
													</c:when>
													<c:otherwise>
													안읽음
													</c:otherwise>
													</c:choose>
													</td>
													<td class="tleft"><span class="bold">
													${result.userIdSend}
													</span></td>
													<td><a href='<c:url value="/msg/readMsg.do?seq=${result.msgSeq}&currentPageNo=${currentPageNo}&receive=${receive}" />'>${result.msgTitle}</a></td>
													<td>${result.sendDate}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- //board list table -->

									<!--paginate start -->
									<div class="text-center">
									<ul class="paginate">
										<c:if test="${pageBlockStart!=1}">
											<a class="pre"
												href="/web_portfolio/msg/list.do?currentPageNo=${pageBlockStart-1}&receive=${receive}">이전페이지</a>
										</c:if>
										
										<c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}" step="1">
											<a href="/web_portfolio/msg/list.do?currentPageNo=<c:out value='${i}'/>
													&searchType=<c:out value='${searchType}'/>
													&searchText=<c:out value='${searchText}'/>
													&receive=${receive}">
												<c:choose>

													<c:when test="${i==currentPageNo}">
														<strong><c:out value="${i}" /></strong>
													</c:when>

													<c:otherwise>
														<c:out value="${i}" />
													</c:otherwise>

												</c:choose>
											</a>
										</c:forEach>

										<c:if test="${pageBlockStart+pageBlockSize<=totalPage}">
											<a class="next"
												href="/web_portfolio/msg/list.do?currentPageNo=${pageBlockEnd+1}&receive=${receive}">다음페이지</a>
										</c:if>
										</ul>
									</div>
									<!--//paginate end -->

									<!-- bottom button -->
									<div class="btn_bottom">
										<div class="btn_bottom_right">
										<!-- <c:if test="${userId!=null}"> </c:if>-->
										
										
										</div>
									</div>
									<!-- //bottom button -->

								</fieldset>
							</form>
						</div>
						<!-- //board_area -->
</body>
</html>