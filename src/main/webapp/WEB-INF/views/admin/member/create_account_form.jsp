<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/201921031/resources/css/admin/create_account_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/create_account_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>회원가입</h3>
				
			</div>
		
			<div class="create_account_form">
			
				<form action="<c:url value='/201921031/admin/member/createAccountConfirm' />" name="create_account_form" method="post">
					
					<input type="text" name="username" placeholder="사용자 이름 입력"> <br>
					<input type="submit" value="회원가입"> 
					
				</form>
				
			</div>
			
			<div class="login">
				
				<a href="<c:url value='/201921031/admin/member/loginForm' />">로그인</a>
				
			</div>
		
		</div>
		
	</section>
	
	
</body>
</html>