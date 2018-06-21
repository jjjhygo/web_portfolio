<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- css 설정 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" />

<!-- js 설정 -->
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시물 목록페이지</title>
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
	<!-- board_search -->
	<div class="board_search">
		<form class="form-inline" id="searchForm">
			<select id="searchType" name="searchType" title="선택메뉴">
				<option value="all"
					<c:if test="${searchType eq 'all'}">selected</c:if>>전체</option>
				<option value="userName"
					<c:if test="${searchType eq 'userName'}">selected</c:if>>작성자</option>
				<option value="title"
					<c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
			</select>
			<input type="text" id="searchText" name="searchText" name="searchText" title="검색어 입력박스" class="form-control input_100" />
				<input type="button" class="btn btn-primary btn-xs" id="btnSearch" value="검색" title="검색버튼" class="btn_search" />
		</form>
	</div>
	<!-- //board_search -->

	<!-- board_area -->
	<div class="board_area">
		<form method="get">

			<fieldset>
				<legend>자유게시판</legend>
				<!-- board list table -->
				<table summary="표 내용은 Sea & Food 게시물의 목록입니다."
					class="table table-hover">
					<colgroup>
						<col width="10%" />
						<col width="50%" />
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody class="table table-hover">
						<c:forEach items="${result}" var="result">
							<tr>
								<td>${result.num}</td>
								<td class="tleft"><span class="bold"> <a
										href="/web_portfolio/bbs/free/readArticle.do?currentPageNo=${currentPageNo}&num=${result.num}">
											${result.title} </a></span></td>
								<td>${result.userName}</td>
								<td>${result.completeDate}</td>
								<td class="tright">${result.hits}</td>
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
							href="/web_portfolio/bbs/free/list.do?currentPageNo=${pageBlockStart-1}">이전페이지</a>
					</c:if>
					<c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}"
						step="1">
						<a href="/web_portfolio/bbs/free/list.do?currentPageNo=<c:out value='${i}'/>
													&searchType=<c:out value='${searchType}'/>
													&searchText=<c:out value='${searchText}'/>">
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
							href="/web_portfolio/bbs/free/list.do?currentPageNo=${pageBlockEnd+1}">다음페이지</a>
					</c:if>
				</ul>
				</div>
				<!--//paginate end -->

				<!-- bottom button -->
				<div class="btn pull-right">
					<div class="btn_bottom_right">
						<!-- <c:if test="${userId!=null}"> </c:if>-->
						<a href="/web_portfolio/goWrite.do?currentPageNo=${currentPageNo}&num=${num}">
							<input type="button" class="btn btn-primary" value="글쓰기" title="글쓰기" />
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