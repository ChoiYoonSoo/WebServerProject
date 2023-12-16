<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/201921031/resources/css/admin/create_account_result.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>회원가입 실패</h3>
				
			</div>
			
			<div class="others">
				
				<a href="<c:url value='/201921031/admin/member/createAccountForm' />">회원가입</a>
				<a href="<c:url value='/201921031/admin/member/loginForm' />">로그인</a>
				
			</div>
		
		</div>
		
	</section>
	
	
</body>
</html>