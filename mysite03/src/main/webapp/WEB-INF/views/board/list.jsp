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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="get">
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
					<c:set var="cnt" value='${fn:length(list) }'/>
					
					<c:forEach items='${map.list}' var='vo' varStatus='status'>
					<tr>
						<td>${map.count-(map.displayPost) -status.index }</td>
						
						<c:choose>
							<c:when test="${fn:length(vo.contents) > 0 }"> <!-- 내용이 있을 경우 -->
							
								<c:choose>
								<c:when test="${vo.depth>0}">
									<td style="text-align:left; padding-left:${30*vo.depth }px"><img src='/mysite03/assets/images/reply.png'><a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title }</a></td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${30*vo.depth }px"><a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title }</a></td>							
								</c:otherwise>								
								</c:choose>								
																								
							</c:when>
							
							<c:otherwise><!-- 내용이 없을경우 -->
								<c:choose>
								<c:when test="${vo.depth>0}">
									<td style="text-align:left; padding-left:${30*vo.depth }px"><img src='/mysite03/assets/images/reply.png'>${vo.title }</td>
								</c:when>
								<c:otherwise>
									<td>${vo.title }</td>
								</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
						
						<c:choose>
							<c:when test="${fn:length(vo.contents) > 0 }"> <!-- 내용이 있을 경우 -->						
								<td>${vo.userName }</td>
								<td>${vo.hit }</td>
								<td>${fn:substring(vo.regDate,0,19) }</td>
								<%-- <td><fmt:formatDate value="${vo.regDate}" pattern="yyyy-MM-dd" /></td> --%>										
								<c:if test="${vo.userName==authUser.name }">
									<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no}" class="del">삭제</a></td>
								</c:if>
							</c:when>
							<c:otherwise>
								<td>${vo.userName }</td>
								<td>${vo.hit }</td>
								<td>${fn:substring(vo.regDate,0,19) }</td>
								<c:if test="${vo.userName==authUser.name }">
									<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no}" class="del">삭제</a></td>
								</c:if>								
							</c:otherwise>
						</c:choose>
													
					</tr>						
					</c:forEach>
				</table>
			

				<!-- pager 추가 -->
				<div class ="pager">
				<div class="selected">
					<c:if test="${map.prev}">
						<span>[ <a href="${pageContext.request.contextPath }/board?num=${map.startPageNum -1}">이전</a> ]</span>
					</c:if>
					
					
						<c:forEach begin="${map.startPageNum}" end="${map.endPageNum}" var="num">
							<span>
								<c:if test="${map.currentPage != num }">
									<a href="${pageContext.request.contextPath }/board?num=${num}">${num}</a>
								</c:if>
								
								<c:if test="${map.currentPage == num }">
									<b>${num}</b>
								</c:if>
											
							</span>
						</c:forEach>
								
					<c:if test="${map.next}">
						<span>[ <a href="${pageContext.request.contextPath }/board?num=${map.endPageNum+1}">다음</a> ]</span>
					</c:if>
				</div>
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