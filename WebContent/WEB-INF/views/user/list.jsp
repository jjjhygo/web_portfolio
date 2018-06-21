<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>

<title>회원 목록</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<style type="text/css">
body {
	margin-right: 0px;
}
/* container layout */
#container {
	margin: 0 auto;
	padding: 20px 0;
}

#content {
	display: table-cell;
	width: 750px;
	padding: 0 0 0 0px;
}
/* //container layout */
.search {
	margin-bottom: 5px;
	text-align: right;
}

.search .btn_search {
	height: 20px;
	line-height: 20px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #e9e9e9;
	background-color: #f7f7f7;
	font-size: 12px;
	text-align: center;
	cursor: pointer;
}

.ui-widget .ui-widget {
	font-size: 0.7em !important;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-migrate-1.4.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value='/resources/jqgrid/js/i18n/grid.locale-kr.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/jqgrid/js/jquery.jqGrid.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jqgrid/css/ui.jqgrid.css'/>" />

<script type="text/javascript">

$(document).ready(function(){
	//Tab
	$( "#tabs" ).tabs();
	var tabHeight = $('#tabs').height();
	var initGridWidth = 0;
	var initGridHeight = 0;
									//jqGrid함수가 실행이 되면 
	var userGrid = jQuery("#userGrid").jqGrid({
		url : '<c:url value="/getUserlistData.do"/>', //데이터를 가져오기 위한 url
		mtype: "POST",
	    data : { },
	    datatype: "json",
	    page : $('#currentPageNo').val(),	// 보여줄 페이지 번호
	    jsonReader : {
          	root: "rows",
          	page: "page",
          	total: "total",
          	records: "records",
          	repeatitems: false,
          	cell: "cell",
          	id: "seq"			// pk 컬럼/변수 
      	},
	    colNames: ["번호", "아이디", "이름", "닉네임", "관리자 권한", "날짜"],
	    colModel: [															//editoptions:{size:"20", maxlength:"50"} 데이터 사이즈 설정
	               { name: "seq", index:'seq', width: 30, align:'center', editable:true, editoptions:{readonly:true}}, // name:dto에 들어가는 변수명, index:sql컬럼명
	               { name: "userId", index:'user_id', width: 100, align:'center', editable:true, editoptions:{readonly:true}},
	               { name: "userName", index:'user_name', width: 150, align:'center', editable:true, editoptions:{readonly:true}, editoptions:{size:"20", maxlength:"15"},hidden:true},
	               { name: "nickname", index:'nickname', width: 80, align:'center', editrules:{required:true}, editable:true},
	               { name: "isAdmin", index:'is_admin',width: 50, align:'center', editable:true, edittype:'select', editoptions:{value:{'-':'선택','1':'예','0':'아니오'}},hidden:true},
	               { name: "createDate", index:'create_date', width: 80, align:'center', editable:true}
	    ],
	    autowidth: false,
	    width:800,
	    height: 360,
	    rowNum: 15,
	    rownumbers: false,
	    sortname: "seq",  //최초 정렬할 컬럼명
	    sortorder: "desc", //최초 정렬 방식
	    scrollrows : true,
	    viewrecords: true,
	    gridview: true,
	    autoencode: true,
	    altRows: true,
	    pager: '#userGrid-pager', //div id
        caption: "게시판", // set captin to any string you wish and it will appear on top of the grid
        loadComplete : function(){
        	var gridId = 'userGrid';
        	var gridParentWidth = $('#gbox_' + gridId).parent().width();
        	var gridParentHeight = $('#gbox_' + gridId).parent().height();
        	//그리드 가로 길이 맞춤
        	$('#'+ gridId).jqGrid('setGridWidth', gridParentWidth);
        	
//         	if(initGridHeight ==0) initGridHeight = $('#gbox_userGrid').parent().height();
        	//index.jsp - iframe 길이 맞춤
        	var t = $('demoFrame', window.parent.document);
        	$(t).height(tabHeight + gridParentHeight + 20);
        },
        
        onCellSelect: function (rowId, cellIdx, value, event){ //게시판의 cell을 클릭해서 개발자창을 띄워서 몇번째 매개변수까지 찍히는지 확인한다.
        							//console.log(rowId)		//어떤 값이 날아오는지 수작업으로 확인해야 한다.
        },
        onSelectRow: function(rowId, tf, event){ //한 줄을 선택
        	$.ajax({//ajax함수 호출
        		url: '/web_portfolio/user/checkPk.do',//호출할 url
        		type: "post", //get or post
        		data: {'seq' : rowId},//파라미터 키와 값 뒤쪽 userId는 변수이다. 위쪽에 선언되어있다. 유효성 검사에 있다.
        		                          //앞의 키이름이 바뀌면 Controller의 키 이름도 바뀌어야한다.
        		success: function(result, textStatus, jqXHR){//통신 성공시 //callback함수
        			//result는  controller에서 보내주는 값
//         			alert(result);
        		
        		},
        		error : function(jqXHR, textStatus, errorThrown){//통신 실패시
        			console.log(jqXHR);
        			console.log(textStatus);
        			console.log(errorThrown);
        		}
        
        	});
        }
	});
	
	//navButtons  기타 버튼들
	jQuery(userGrid).jqGrid('navGrid', "#userGrid-pager",
		{ 	//navbar options
			edit: true,
			add: false,
			del: true,
			search: false,
			refresh: true,
			view: true
		},
		{//수정
		width:'auto',
		editCaption:'회원 정보 수정',
		url : '<c:url value="/user/editUser.do"/>',
		closeOnEscape:true,
		closeAfteredit: true,
		reloadAfterSubmit:true,
		recreateForm:true,
		viewPagerButtons: false,
		beforeShowForm : function(form){//html태그 form을 보여주기 전의 event
			
			var rowId = $("#userGrid").jqGrid('getGridParam', 'selrow');
			//rowId로 row 값 가져오기 : return type = Object
			var rowData = $("#userGrid").jqGrid('getRowData', rowId);
// 			alert(rowData.isAdmin);
			console.log(rowData);
			
			$('tr#tr_isAdmin', form[0]).show();
			//select에 속해있는 option을 뜻함.
			$('select#isAdmin option[value='+rowData.isAdmin+']', form[0]).attr({'selected':true});
			
			//console.log($(form[0]).html()); //jQuery 이용해 HTML 태그 값 확인하기.
			$('tr#tr_userName', form[0]).show();//tr태그를 찾아가고 그 tr태그의 id를 form[0]에서 찾고 보여줘. hidden으로 숨겨진걸 edit에서 볼수있다.
			
	},						  //post가 form이다.
		beforeSubmit : function(post, formId){//유효성 검사를 할 수 있다.
			console.log('beforeSubmit : ' + post);
			if(post.userName == ''){
				return [false, "사용자명을 입력하세요."];
			}	
			if(post.isAdmin == ''){
				return [false, "운영자 여부를 선택하세요."];
			}
			if(post.nickname == ''){
				return [false, "닉네임을 입력하세요."];
			}
			if(post.nickname.length < 3){
				return[false,"닉네임은 3자 이상입니다."];
			}
// 			var regExp = /^[0-9a-zA-z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3,4}$/i;
// 			//match로 비교하고 일치하지 않으면 null 이 나온다.
// 			if(post.nickname.match(regExp)==null){ 
// 				return [false, "이메일 형식이 올바르지 않습니다."];
// 			}
			return[true, "", ""];
		},
		afterComplete : function(response, postdata){
// 			console.log('editOption - afterComplete');
// 			console.log(response);
		var data = response.responseJSON;
			if(data = 1){
				alert("수정완료");
			}
			else if(data =5){
				alert("수정이상");
			}
			else if(data =6){
				alert("수정오류");
			}
		},
		afterSubmit : function(response, postdata){
			return [true, '', ''];
		}
		}, //edit에 대한 설정 설정을 하지 않으려면 빈값으로 둔다.
		{}, //add에 대한 설정
		{//del에 대한 설정   //jsonReader의 ID가 파라미터로 찍힌다.
			caption : '회원정보 삭제',
			msg: "선택한 회원을 삭제 하시겠습니까?",
			recreateForm: true,
			url : '<c:url value="/user/delUser.do"/>',
			beforeShowForm : function(e){
				var form = $(e[0]);
				return;
			}, //비동기통신에서 response는 result와 같다 바꿔도 무방.
			afterComplete : function(result, postdata){
				console.log('delOption - afterComplete');
				console.log(result.responseJSON);
				console.log('--------------------------');
				//객체 받을때, ex)map, arrayList, int, long 등
				//var data = result.responseJSON;
				//return Map : data={key:value} -> data.key;
				//var data = {'result':success}
				//data.result ->'success'
				//return 1 : data=1;	
				
				//기본형 받을때 ex)String 등
				//var data = result.responseText;
				//return "ERROR" : data="ERROR";
				
				var data = result.responseJSON;
				if(data == 1){
					alert("삭제되었습니다.");
				}
				else if(data == 5){
					alert("삭제 이상");
				}
				else if(data == 7){
					alert("예외 발생");
				}
				
			},
			onClick : function(e){
				
			}
		}
	);
	
	$('#btnSearch').click(function(){
		var searchText = $('#searchText').val();
		var searchType = $('#searchType').val();
		
		alret(searchText);
		alret(searchType);
		
		$('#userGrid').jqGrid('setGridParam',{
		postData:{
			'searchType' : searchType,
			'searchText' : searchText
		},
		page : 1
	}).trigger("reloadGrid");
		
	});
	
})


</script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">회원 목록</a></li>
		</ul>
		<div id="tabs-1">
			<!-- wrap -->
			<div id="wrap">

				<!-- container -->
				<div id="container">

					<!-- content -->
					<div id="content">

						<!-- board_area -->
						<div class="board_area">

							<!-- board_search -->
							<div class="search">
								<select selected="selected" id="searchType" title="선택메뉴">
									<option value="all">전체</option>
									<option value="userId">ID</option>
									<option value="userName">이름</option>
								</select> <input id="searchText" type="text" title="검색어 입력박스"
									class="input_100" /> <input id="btnSearch" type="button"
									value="검색" title="검색버튼" class="btn_search" />
							</div>
							<!-- //board_search -->

							<table id="userGrid"></table>
							<!-- tr/td 구성 -->
							<div id="userGrid-pager"></div>
							<!-- paging 구성 -->
						</div>
						<!-- //board_area -->

					</div>
					<!-- //content -->

				</div>
				<!-- //container -->

			</div>
			<!-- //wrap -->
		</div>
	</div>

</body>
</html>