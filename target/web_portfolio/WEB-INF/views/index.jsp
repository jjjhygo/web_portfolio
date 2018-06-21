<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- tag library 선언 : fmt tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- font 설정 -->
<link href="https://fonts.googleapis.com/css?family=Stylish" rel="stylesheet">

<!-- css 설정 -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" />
<!-- js 설정 -->
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>YH's community</title>
	
    <script type="text/javascript" src="<c:url value="/resources/proper/js/proper.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

	<!-- Latest compiled and minified CSS -->
	<link href="https://fonts.googleapis.com/css?family=Stylish" rel="stylesheet">
	
	<style type="text/css">
	.examples {padding-left: 10px;}
    .wrp { width: 100%; text-align: center; margin-top:15px;}
    .frm { text-align: left; width: 1050px; margin: auto;  }
    .fldLbl { white-space: nowrap; } 
	.panel-body {
		font-size: 85%;
		height: 300px; 
		overflow: auto;
	}
	.btn_bottom_right {
		float: right;
	}
	.btn_bottom_right input[type="button"] {
		height: 25px;
		line-height: 25px;
		padding: 0 10px;
		vertical-align: middle;
		border: 1px solid #e9e9e9;
		background-color: #f7f7f7;
		font-size: 12px;
		text-align: center;
		cursor: pointer;
	}
	</style>
	<div id="google_translate_element" align="center"></div>
	<script type="text/javascript">
	function googleTranslateElementInit() {
 	 new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
	}
	</script>
	<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
	
    <script type="text/javascript">
        jQuery(document).ready(function () {
        	
            $("#accordion").collapse();
            
            // 초기 페이지 세팅
            $("#demoFrame").attr("src", '<c:url value="/home.do" />');
			//$("span",".gheader").html('Intro Page');
            
			$(".list-group-item").on("click", function(){
				//$("span",".gheader").html( $(this).text() );
			});
			
        });
        function dologout(){
    		//confirm은 true와 false를 반환하기 때문에 if안에 바로 쓸수 있다.
    		if(confirm("로그아웃 하시겠습니까?")){
    		window.location.href ='/web_portfolio/logout.do';
    		}
    	}
        function resize(obj){
        	obj.style.height = obj.contentWindow.document.body.scrollHeight + 700 + 'px';
        }
        
    </script>
</head>
<body background="resources/image/gray.jpg" height="5000" width="1500">
	<h4 style="text-align: center;" class ="gheader"><span style="font-size: medium"></span></h4>
	<div id="Form1" class="wrap">
        <div id="wrap" class="frm">
			<div class="btn-group btn-group-justified" role="group" 
			style="margin-bottom:15px;">
				<div class="btn-group" role="group">
					<a class="btn btn-default" href='<c:url value="/index.do"/>'>  <span class ="glyphicon glyphicon-home"></span>HOME </a>
				</div>
				<div class="btn-group" role="group">
					<a class="btn btn-default" href='<c:url value="/profile.do?userId=${sessionScope.userId}" />' target="demoFrame" >
					개인 프로필
					 </a>
					 <c:if test="${msgpf!=null}">
							${msgpf}
							</c:if>
				</div>
			</div>
            <!-- Content -->
            <table cellspacing="10" cellpadding="10">
            	<tr>
            		<td colspan="2" style="padding-bottom: 5px; !important">
            			<!-- bottom button -->
						<div class="btn_bottom">
							<div class="btn_bottom_right">
							<c:if test="${msg!=null}">
							${msg}
							</c:if>
							<c:if test='${sessionScope.userId != null }'>
					${sessionScope.userId}님! 반갑습니다!
					</c:if>
						<form name="logoutForm"></form>
								<c:if test="${sessionScope.userId != null}">
								<input type="button" class="btn btn-primary" onclick="dologout()" value="로그아웃" title="로그아웃" />
								</c:if>
								<c:if test="${sessionScope.userId==null}">
								<a href='<c:out value="gologin.do"/>'>
								<input type="button" class="btn btn-primary" value="로그인" title="로그인" />
								</a>
								</c:if>
							</div>
						</div>
            		</td>
					<h4>TODAY:&nbsp ${todayCount}</h4>
            		<h4>TOTAL:&nbsp ${totalCount}</h4>
            	</tr>
                <tr>
                	<td style="vertical-align: top;width:250px;">
						<div class="panel-group" id="accordion">
							<!-- 메뉴 그룹 -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
										<!-- 메뉴1 -->
										자유게시판
										</a>
									</h4>
								</div>
								<div id="collapse1" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="examples list-group">
											<a href='<c:url value="/bbs/free/list.do"/>' class="list-group-item" target="demoFrame">
												자유게시판
											</a>
										</div>
									</div>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
										<!-- 메뉴2 -->
										쪽지함
										</a>
									</h4>
								</div>
								<div id="collapse3" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="examples list-group">
											<a href='<c:url value="/msg/goWrite.do"/>' class="list-group-item" target="demoFrame">
												쪽지 쓰기
											</a>
										</div>
										<div class="examples list-group">
											<a href='<c:url value="/msg/list.do?send=${sessionScope.userId}"/>' class="list-group-item" target="demoFrame">
												보낸 쪽지함
											</a>
										</div>
										<div class="examples list-group">
											<a href='<c:url value="/msg/list.do?receive=${sessionScope.userId}"/>' class="list-group-item" target="demoFrame">
												받은 쪽지함
											</a>
										</div>
									</div>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse4">
										<!-- 메뉴3 -->
										관리자에게
										</a>
									</h4>
								
								</div>
								<div id="collapse4" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="examples list-group">
											<a href='<c:url value="/goSendMessage.do"/>' class="list-group-item" target="demoFrame">
												이메일 보내기
											</a>
										</div>
									</div>
								</div>
							</div>
							<c:if test="${sessionScope.userId!='' && sessionScope.isAdmin==1}">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
										<!-- 메뉴4 -->
										관리자 권한
										</a>
									</h4>
								</div>
								<div id="collapse2" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="examples list-group">
											<a href='<c:url value="/user/list.do"/>' class="list-group-item" target="demoFrame">
												회원 목록
											</a>
										</div>
									</div>
								</div>
							</div>
							</c:if>
							<div class="panel panel-default">
								<div class="panel-heading">
								<table align="center">
								<tr>
								<td align="center" colspan="2">
								<font style="font-weight:bold; color:000000; font-size:1.5em;">최근 방문한 회원</font>
								</td>
								</tr>
								<tr>
									<td align="center">
									<font style="font-weight:bold; color:0066CC; font-size:1.1em;">아이디</font>
									</td>
									<td align="center">
									<font style="font-weight:bold; color:0066CC; font-size:1.3em;">시간</font>
									</td>						
								</tr>
								<c:forEach items="${result}" var="re">
								<tr>
									<td width="70" align="center">
									${re.userId}
									</td>
									<td width="140" align="center">
									<fmt:formatDate value="${re.visitDate}" pattern="yyyy.MM.dd HH:mm" />
									</td>
								</tr>
								</c:forEach>
								</table>
								</div>
								</div>
						</div>
                    </td>
                	<td style="vertical-align: top;width:800px;">
                    	<%-- 우측 본문 --%>
                        <iframe id="demoFrame" name="demoFrame" style="width: 850px; border-width: 0;" onload="resize(this)"></iframe>
                    </td>
            </table>
           
        </div>
    </div>
</body>
</html>