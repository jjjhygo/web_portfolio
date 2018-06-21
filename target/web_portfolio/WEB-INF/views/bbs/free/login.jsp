<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>로그인 & 회원가입</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var panelOne = $('.form-panel.two').height(), 
			panelTwo = $('.form-panel.two')[0].scrollHeight;
	
		$('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
			e.preventDefault();
	
		    $('.form-toggle').addClass('visible');
		    $('.form-panel.one').addClass('hidden');
		    $('.form-panel.two').addClass('active');
		    $('.form').animate({
		      'height': panelTwo
		    }, 200);
		});

		$('.form-toggle').on('click', function(e) {
		    e.preventDefault();
		    $(this).removeClass('visible');
		    $('.form-panel.one').removeClass('hidden');
		    $('.form-panel.two').removeClass('active');
		    $('.form').animate({
		      'height': panelOne
		    }, 200);
		});
	});
	3020515470921
	function doJoin(){
		//유효성검사는 하나의 항목마다 if를 쓴다.   
		                                              //.value는 값을 가지고 오라는 뜻.
		var userId = document.getElementById("j_userId").value;
		if(userId==undefined || userId == ''){
			alert("ID를 입력하세요.");           //.focus() 는 커서가 찾아가서 깜빡인다.
			document.getElementById("j_userId").focus();
			return;//javascript 한정. function을 종료한다. Id를 입력하지 않으면 submit이 안된다. 
			       //유효성검사에서 통과를 못했기에 함수 종료 .return 시킨다.
		}
		var userName=$('#j_userName').val();
		if(userName==undefined || userName==''){
			alert("이름을 입력하세요");
			$('j_userName').focus();
			return;
		}
		// #셀렉터를 쓰면 코드 단축가능//#은 id라는 표시 #뒤는 id값, 값을 가지고 오는 함수 val()
		var userPw= $('#j_userPw').val();
		if(userPw==undefined || userPw==''){
			alert("PW를 입력하세요.");
			$('#j_userPw').focus();
			return;
		}
		var cUserPw=$('#j_cUserPw').val();
		if(cUserPw==undefined || cUserPw==''){
			alert("cPW를 입력하세요.");
			$('#j_cUserPw').focus();
			return;
		}
		var nickname=$('#j_nickname').val();
		if(nickname==undefined || nickname==''){
			alert("nickname을 입력하세요.");
			$('#j_nickname').focus();
			return;
		}
		//비밀번호와 비밀번호확인 값이 같은지 확인
		if(userPw != cUserPw){
		alert("비밀번호가 다릅니다.")
		$('#j_userPw').focus();
		return;
		}
		var email=$('#j_email').val();
		if(email==undefined || email==''){
			alert("이름을 입력하세요");
			$('#j_email').focus();
			return;
		}
		//ajax 이용하여 ID중복 확인 후 중복 아닐 때 폼 전송
		$.ajax({//ajax함수 호출
			url: '/web_portfolio/chkId.do',//호출할 url
			type: "post", //get or post
			data: {'userId' : userId},//파라미터 키와 값 뒤쪽 userId는 변수이다. 위쪽에 선언되어있다. 유효성 검사에 있다.
			                          //앞의 키이름이 바뀌면 Controller의 키 이름도 바뀌어야한다.
			success: function(result, textStatus, jqXHR){//통신 성공시 //callback함수
				if(result == 0){
					var frm = $('form[name=joinForm]')[0];
					frm.action = "/web_portfolio/user/join.do";
					frm.method = "post";
					frm.submit();
				}
				else{//중복 또는 문제가 있든...
					//Id가 중복됩니다(경고창)
					alert("Id가 중복됩니다");
					//커서를 Id입력하는 곳으로 이동
					$('#j_userId').focus();
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
<body background="resources/image/gray.jpg" height="5000" width="1500">
	<!-- 디자인 출처 : http://www.blueb.co.kr/?c=2/34&where=subject%7Ctag&keyword=%EB%A1%9C%EA%B7%B8%EC%9D%B8&uid=4050 -->
	<!-- Form-->
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header"><img src="<c:url value="/resources/image/register.png"/>" align ="right" height="70" width="120" />
				<h1>Account Login</h1>
				<c:if test="${msg!=null}">
				${msg}
				</c:if>
			</div>
			
			<!-- 로그인 -->
			<div class="form-content">
				<form action="/web_portfolio/login.do" method="post">
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="userId" name="userId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="userPw" name="userPw" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> 
							<input type="checkbox" />Remember Me
						</label>
						<a href="#" class="form-recovery">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit">Log In</button>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 회원가입 -->
		<div class="form-panel two">
			<div class="form-header">
				<h1>Register Account</h1>
			</div>
			<div class="form-content">
				<form action = "/web_portfolio/user/join.do" name="joinForm">
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="j_userId" name="j_userId"  required="required" />
					</div>
					<div class="form-group">
						<label for="username">User Name</label> 
						<input type="text" id="j_userName" name="j_userName"  required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="j_userPw" name="j_userPw" required="required" />
					</div>
					<div class="form-group">
						<label for="cpassword">Confirm Password</label> 
						<input type="password" id="j_cUserPw" name="j_cUserPw" required="required" />
					</div>
					<div class="form-group">
						<label for="email">Nickname</label> 
						<input type="email" id="j_nickname" name="j_nickname" required="required" />
					</div>
					<div class="form-group">
						<label for="username">E-mail</label> 
						<input type="text" id="j_email" name="j_email"  required="required" />
					</div>
					<div class="form-group">
						<button type="button" onclick="doJoin()">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
