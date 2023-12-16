<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/201921031/resources/css/admin/login_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/login_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
			
			<div class="word">
			
				<h3>로그인</h3>
				
			</div>
			
			<div class="login_form">
			
				<form action="<c:url value='/201921031/admin/member/loginConfirm' />" name="login_form" method="post">
					
					<input type="text"		name="username" 		placeholder="사용자 이름 입력"> <br>
					<input type="submit"	value="로그인"> 
					
				</form>
				
			</div>
			
	
		
		</div>
		
	</section>
	

	
</body>
</html>