<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

   <script type="text/javascript">
   function secretBoard(writeIdx, currentPageNo){
      window.open("secret.do","pop","width=500, height=100,location=no,status=no,toolbar=no");
      var tmp;
      
      if(tmp == 1){
      var frm = document.secretForm ;
      frm.action = "/web_portfolio/bbs/free/read.do?writeIdx="+writeIdx+"&currentPageNo="+currentPageNo+"&c=1"
      frm.method = "post";
      frm.submit();
      }
   }
   </script>
</head>
<body>
   <div class="container">
      <!-- 컨테이너 시작 -->
      <br /> <br /> <br />
      <h3>자유 게시판</h3>
      <br />
      <table class="table table-striped table-hover table-bordered ">
         <colgroup>
            <col width="10%" />
            <col width="50%" />
            <col width="10%" />
            <col width="20%" />
            <col width="10%" />
         </colgroup>
         <thead>
            <tr class="info">
               <th class="text-center">번호</th>
               <th class="text-center">제목</th>
               <th class="text-center">작성자</th>
               <th class="text-center">작성일</th>
               <th class="text-center">조회</th>
            </tr>
         </thead>

         <tbody>
            <c:forEach items="${board}" var="re">
               <tr>
                  <td align="center"><c:out value="${re.writeIdx}" /></td>
                  <c:if test="${re.secret == 1}">
                     <td align="center"><a href="#"
                        onclick="secretBoard(${re.writeIdx },${currentPageNo})"> <span
                           class="bold"> <c:if test="${re.secret == 1}">[비공개]</c:if>
                              <c:out value="${re.title}" />
                        </span>
                     </a></td>
                  </c:if>
                  <c:if test="${re.secret == 0}">
                     <td align="center"><a
                        href="/web_portfolio/bbs/free/read.do?writeIdx=${re.writeIdx }&currentPageNo=${currentPageNo}&c=1">
                           <c:out value="${re.title}" />
                     </a></td>
                  </c:if>
                  <td align="center"><c:out value="${re.userId}" /></td>
                  <td align="center"><c:out value="${re.date}" /></td>
                  <td align="center"><c:out value="${re.views}" /></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>

      <div class="btn pull-right">
         <c:if test="${sessionScope.userId != ''}"></c:if>
         <a
            href="/web_portfolio/bbs/free/goWrite.do?currentPageNo=${currentPageNo }">
            <input class="btn btn-primary" type="button" value="글쓰기" title="글쓰기" />
         </a>
      </div>

      <!--  페이징 시작 -->
      <div class="text-center">
         <ul class="pagination">
            <c:if test="${currentPageNo != 1 }">
               <li class='first-page'><a
                  href='/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=1'>&laquo;</a></li>
            </c:if>
            <c:if test="${blockStart != 1}">
               <li><a
                  href='/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=${blockStart - 1}'>&lsaquo;</a></li>
            </c:if>

            <c:forEach var="i" begin="${blockStart }" end="${blockEnd}" step="1">
               <c:choose>
                  <c:when test="${i == currentPageNo}">
                     <!-- 조건 1 -->
                     <li class='active'><a
                        href="/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=<c:out value='${i }' />">${i }</a></li>
                  </c:when>
                  <c:otherwise>
                     <!-- 조건 1, 조건2도 아닐 때 -->
                     <li><a
                        href="/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=<c:out value='${i }' />">${i }</a></li>
                  </c:otherwise>
               </c:choose>

            </c:forEach>

            <c:if test="${blockStart + blockSize < totalPageNo}">
               <li><a
                  href='/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=${blockEnd + 1 }'>&rsaquo;</a></li>
            </c:if>
            <c:if test="${currentPageNo != totalPageNo }">
               <li class='last-page'><a
                  href='/web_portfolio/bbs/free/list.do?searchText=${searchText }&searchType=${searchType }&currentPageNo=${totalPageNo}'>&raquo;</a></li>
            </c:if>
         </ul>
      </div>
      <!-- bottom button -->

      <!-- //bottom button -->

      <!--  페이징 끝 -->
      <!-- 컨테이너 끝-->
   </div>
</body>
</html>