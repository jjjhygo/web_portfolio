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
function doMakeFriends() {
	var userId = $('#userId').val();
	if(userId ==''){
		alert("Id를 입력하세요.");
	$('#userId').focus();
	}
		  $.ajax({//ajax함수 호출
				url: '/web_portfolio/chkId.do',//호출할 url
				type: "post", //get or post
				data: {'userId' : userId},//파라미터 키와 값 뒤쪽 userId는 변수이다. 위쪽에 선언되어있다. 유효성 검사에 있다.
				                          //앞의 키이름이 바뀌면 Controller의 키 이름도 바뀌어야한다.
				success: function(result, textStatus, jqXHR){//통신 성공시 //callback함수
					if(result == 1){
						var frm = $('form[name=friendFrom]')[0];
						frm.action = "/web_portfolio/msg/makeFriends.do";
						frm.method = "post";
						frm.submit();
					}
					else{//중복 또는 문제가 있든...
						//Id가 중복됩니다(경고창)
						alert("Id가 없습니다.");
						//커서를 Id입력하는 곳으로 이동
						$('#userId').focus();
						
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
</script>
</head>
<body>
<form name="friendFrom">
	<table class="table table-hover" >
		<tr>
			<td>
			<div class="col-xs-12">
			<input class="form-control" type="text" name="userId" id="userId" value="친구Id" title="친구Id입력" /></div></td>
			<td><input type="button" class="btn btn-primary btn-xs" onclick="doMakeFriends()" value="추가" title="친구추가" /></td>
		</tr>
		<tr>
		<td colspan="2">
		<c:if test="${msg!=null }">
		${msg }
		</c:if>
		</td>
		</tr>
	</table>
	</form>
</body>
</html>