<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" /><!--  -->
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }" method="get">
					<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th> 
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:forEach items="${map.list }" var="vo" varStatus="status">
						<tr>
							<td>${map.count-(map.displayPost) - status.index}</td>
							<c:choose>
								<c:when test="${vo.depth > 0 }">
									<td class="left" style="text-aling:left; padding-left:${20*vo.depth}px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.request.contextPath}/board/view/${vo.no}?p=${map.currentPage}&kwd=${map.keyword}">${vo.title }</a>								
									</td>
								</c:when>
								<c:otherwise>
									<td class="left" style="texxt-align:left">
										<a href="${pageContext.request.contextPath}/board/view/${vo.no}?p=${map.currentPage}&kwd=${map.keyword}">${vo.title }</a>
									</td>
								</c:otherwise>
							</c:choose>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:choose>
									<c:when test="${not empty authUser && authUser.no == vo.userNo }">
										<a href="${pageContext.request.contextPath }/board/delete/${vo.no}?p=${map.currentPage}&kwd=${map.keyword}" class="del">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>								
							</td>
						</tr>
					</c:forEach>
					
								
				</table>
			

				<!-- pager 추가 -->
				<div class ="pager">
						<c:if test="${map.prev }">
							<span>[ <a href="${pageContext.request.contextPath }/board?p=${map.startPageNum -1}">이전</a> ]</span>
						</c:if>
						
						<c:forEach begin="${map.startPageNum }" end="${map.endPageNum }" var="page">
							<span>
								<c:if test="${map.currentPage != page }">
									<a href="${pageContext.request.contextPath }/board?p=${page}">${page }</a>
								</c:if>
								<c:if test="${map.currentPage == page }">
									<div class="selected">${page }</div>
								</c:if>								
							</span>
						</c:forEach>
						
						<c:if test="${map.next }">
							<span>[ <a href="${pageContext.request.contextPath }/board?p=${map.endPageNum +1}">다음</a> ]</span>
						</c:if>
				</div>
				<!-- pager 추가 -->				
				
				<div class="bottom">
					<c:if test="${not empty authUser }">
					<a href="${pageContext.request.contextPath }/board/write?p=${map.currentPage}&kwd=${map.keyword}" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>