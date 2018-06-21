<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<!-- css 설정 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" />
<!-- js 설정 -->
<script type="text/javascript"src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript"src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<title>회원 정보 페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
	// 자바 스크립트 영역
	function doDelete(){
		var pw = $('#userPw').val();
		if(pw==undefined||pw==''){
			alert("비밀번호를 입력하세요.");
			$('#userPw').focus();
			return;
		}
		var frm = document.readForm;
		frm.action = "/web_portfolio/userDelete.do";
		frm.method="post";
		frm.submit();
	}
	
	function doLogout(){
		//confirm은 true와 false를 반환하기 때문에 if안에 바로 쓸수 있다.
		if(confirm("로그아웃 하시겠습니까?")){
		window.location.href ='/pf_user/logout.do';
		}
	}
// 	function init(){
// 		var msg='${msg}';
// 		if(msg !=''){
// 			alert(msg);
// 		}
// 	}
	function goUpdate(){
		
		var nickname = $('#nickname').val();
		if(nickname ==''){
			alert("닉네임을 입력하세요");
			$('#nickname').focus();
			return;
		}
		if(nickname.length <3){
			alert("닉네임은 3글자 이상 써주세요");
			$('#nickname').focus();
			return;
		}
		// var userPw = document.getElementById("userPw").value;
		userPw = $('#userPw').val();
		if(userPw ==''){
			alert("비밀번호를 입력하세요.");
			//아래에 있는 비밀번호 ID값을 가져온다. #에 ID가 있으면 ID를 가지고 온다.
			//document.getElementById("userPw").focus();
			$('#userPw').focus();
			return;
		}
	var frm = document.readForm;
	frm.action = "/web_portfolio/profileUpdate.do";
	frm.method = "post";
	frm.submit();
	}
</script>
</head>
	<!-- body안의 태그들이 불러와 지면 init()을 실행한다. -->
<body onload="init()">
	<!-- wrap -->
	<div id="wrap">

		<!-- container -->
		<div id="container">

			<!-- content -->
			<div id="content">
				
				<br /> <br />
					
				<!-- title board detail -->
				<div class="title_board_detail">회원 정보</div>
				<!-- //title board detail -->

				<!-- board_area -->
				<div class="board_area">
					<form name="readForm" method="post">
					<input type="hidden" name="seq" value="${user.seq }"></input>
					<input type="hidden" name="userId" value="${user.userId }"></input>
						<fieldset>
							<legend>회원 상세 내용</legend>

							<!-- board detail table -->
							<table summary="회원 정보 입니다." class="table table-hover">
								<caption>회원  상세 내용</caption>
								<colgroup>
									<col width="%" />
									<col width="%" />
									<col width="%" />
									<col width="%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="tright">ID</th>
										<td class="tleft" colspan="3">
										<div class="col-xs-3">
										<input class="form-control" type="text" id="userId" name="userId" value="${user.userId}" title="ID 입력박스" readOnly="readOnly" class="input_150" />
										</div></td>
									</tr>
									<tr>
										<th class="tright">성명</th>
										<td class="tleft" colspan="3">
										<div class="col-xs-3">
										<input class="form-control" type="text" id="userName" name="userName" value="${user.userName}" title="성명 입력박스" readOnly="readOnly" class="input_150" />
										</div></td>
									</tr>
									<tr>
										<th class="tright">별명</th>
										<td class="tleft" colspan="3">
										<div class="col-xs-3">
										<input class="form-control" type="text" id="nickname" name="nickname" value="${user.nickname}" title="별명 입력박스" class="input_150" />
										</div></td>
									</tr>
									<tr>
										<th class="tright">이메일</th>
										<td class="tleft" colspan="3">
										<div class="col-xs-6">
										<input class="form-control" type="text" id="email" name="email" value="${user.email}" title="이메일 입력박스" class="input_150" />
										</div></td>
									</tr>
									
									<tr>
										<th class="tright">관리자 여부</th>
										<td class="tleft" colspan="3">
										<c:choose>
										<c:when test="${user.isAdmin==1}">
										&nbsp&nbsp&nbsp관리자
										</c:when>
										<c:otherwise>
										일반회원
										</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr>
										<th class="tright">비밀번호</th>
										<td colspan="3" class="tleft">
										<div class="col-xs-3">
											<input class="form-control" type="password" id="userPw" name="password" title="비밀번호 입력박스" class="input_100" />
											</div>
											<c:if test="${msg!=null}">
											${msg}
											</c:if>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //board detail table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_left">
									<a href='<c:url value="profile.do?userId=${user.userId }" />'>
									<input type="button" class="btn btn-primary" onclick="doCancle()" value="취소" title="취소" />
									</a>
								</div>
								<div class="btn_bottom_right">
									<input type="button" class="btn btn-primary" onclick="goUpdate()" value="완료" title="완료" /> 
								</div>
							</div>
							<!-- //bottom button -->

						</fieldset>
					</form>
				</div>
				<!-- //board_area -->

			</div>
			<!-- //content -->

		</div>
		<!-- //container -->

	</div>
	<!-- //wrap -->

</body>
</html>