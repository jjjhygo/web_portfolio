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

<title>친구 목록 페이지</title>
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

<script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//Tab
		$("#tabs").tabs();
	});
</script>

</head>
<body>
						<!-- board_area -->
						<div class="board_area">
							<form name="emailForm" enctype="multipart/form-data" action="/web_portfolio/sendMessage.do" method="post">
							<input type="hidden" name="userId" value="${sessionScope.userId}"></input>
								<fieldset>
									<legend>email</legend>

									<!-- board write table -->
									<table summary="email 보내기"
										class="board_write_table">
										<caption>email</caption>
										<colgroup>
											<col width="20%" />
											<col width="80%" />
										</colgroup>
										<tbody>
											<tr>
												<th class="tright"><label for="email_write_username">your name</label></th>
												<td class="tleft"><input class="form-control input-sm" name="name" id="yourname" placeholder="Your name" type="text" /></td>
											</tr>
											<tr>
												<th class="tright"><label for="email_write_youremail">your email</label></th>
												<td class="tleft"><input class="form-control input-sm" name="email" id="youremail" type="email" placeholder="Your email"/>
												 </td>
											</tr>
											<tr>
												<th class="tright"><label for="msg_write_type">구분</label></th>
												<td class="tleft"><div class="col-xs-3"><select class="form-control input-sm" id="searchType" name="searchType" title="searchType">
 													<option value="subject"
 														<c:if test="${searchType == 'subject' }">selected</c:if>>Subject</option>
 													<option value="suggestion"
 														<c:if test="${searchType == 'suggestion' }">selected</c:if>>Suggestion</option>
 													<option value="secret"
 														<c:if test="${searchType == 'secret' }">selected</c:if>>Top Secret</option></select></div></td>
											</tr>
											<tr>
												<th class="tright"><label for="msg_write_title">내용</label></th>
												<td class="tleft">
													<div class="editer">
														<p>
														<textarea class="form-control" name="message" id="message" rows="10" placeholder="Your message"></textarea>
															<script>
																CKEDITOR.replace('message');
															</script>
														</p>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<!-- //board write table -->

									<!-- bottom button -->
									<div class="btn_bottom">
										<div class="btn_bottom_right">
											 <input type="submit" class="btn btn-primary" value="submit" title="보내기" />

										</div>
									</div>
									<!-- //bottom button -->

								</fieldset>
							</form>
						</div>
						<!-- //board_area -->
</body>
</html>