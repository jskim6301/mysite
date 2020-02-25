<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<jsp:include page="/WEB-INF/views/includes/header.jsp" /><!--  -->
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.request.contextPath }/user/login">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userVO.email }">
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					
					<c:if test="${not empty userVO }">
						<p>
							로그인이 실패 했습니다.
						</p>					
					</c:if>
					
			<%--    <c:if test="${result == 'fail'}">

					</c:if> --%>
					
					
<%-- 					<%
						String result = (String)request.getAttribute("result");
						if("fail".equals(result)){
					%>
						<p>
							로그인이 실패 했습니다.
						</p>
					<%
						}
					%> --%>					
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>