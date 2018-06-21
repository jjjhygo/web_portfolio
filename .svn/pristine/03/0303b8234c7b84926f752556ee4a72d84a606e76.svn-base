<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>


<script type="text/javascript">
function popupOpen() {
    var popUrl = "<c:url value="/msg/goMakeFriends.do"/>"; //팝업창에 출력될 페이지 URL
    var popOption = "width=260, height=140, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(optoin)
    window.open(popUrl, "", popOption);
 }
 
function redirectToFB(friendUserId){
	console.log(friendUserId);
    window.opener.location.href="/web_portfolio/msg/goWrite.do?friendUserId="+friendUserId;
    self.close();
}
</script>
</head>
<body>
<form>
<input type="hidden" name="userId" id ="friendUserId" value="${friedns.friendUserId}"></input>
	<table class="table table-hover table-bordered ">
		<tr>
			<td colspan="2" align="center"><font style="font-weight:bold; color:darkgreen; font-size:1.3em;">&nbsp&nbsp&nbsp친구목록</font></td>
			<td>
			<input type="button" class="btn btn-primary btn-xs" onclick="popupOpen()" width="120" value="친구추가" title="친구추가" />
			</td>
		</tr>
	
		<tr>
			<th align="center">Id</th>
			<th align="center">이름</th>
		</tr>
		<c:forEach items="${result}" var=	"friends">
			<tr align="center">
				<td align="center"><a href="#" onclick="redirectToFB('${friends.friendUserId}')">${friends.friendUserId}</a></td>
				<td align="center"><a href="#" onclick="redirectToFB('<c:out value='${friends.friendUserId}'/>')">${friends.friendUserName}</a></td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<div class="text-center">
	<c:if test="${pageBlockStart!=1}">
		<a class="pre" href="/web_portfolio/msg/customerList.do?currentPageNo=${pageBlockStart-1}">이전페이지</a>
	</c:if>

	<c:forEach var="i" begin="${pageBlockStart}" end="${pageBlockEnd}"
		step="1">
		<a href="/web_portfolio/msg/customerList.do?currentPageNo=<c:out value='${i}'/>
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
			href="/web_portfolio/msg/customerList.do?currentPageNo=${pageBlockEnd+1}">다음페이지</a>
	</c:if>
	</div>
</body>
</html>