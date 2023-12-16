<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<jsp:include page="../../include/title.jsp" />

	<link href="<c:url value='/201921031/resources/css/admin/listup_admins.css' />" rel="stylesheet" type="text/css">
	
	<style>
    .no-link-style {
        text-decoration: none;
        color: inherit;
    }
    
    #section_wrap .admin_list table thead tr th{
    	background-color: #6aad6a;
    }
</style>
	

</head>
<body>
	
	<jsp:include page="../../include/header.jsp" />
	
	<section>
	<h3 style="text-align: center;">${playlistName}</h3>
	<div id="section_wrap">
	<button onclick="location.href='<c:url value='/201921031/admin/member/mainload' />'">메인으로</button><br><br>
		
		<div class="admin_list">

                <table>
				    <thead>
				        <tr>
				            <th>가수</th>
                            <th>제목</th>
                            <th>장르</th>
                            <th>삭제</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="item" items="${myplaylist}">
				
					<tr>
					   <td>${item.singer}</td>
					   <td>${item.title}</td>
					   <td>${item.genre}</td>
					   <td><button onclick="removeList('${item.songId}', '${item.playlistId}')">노래 삭제</button></td>
					   
					</tr>
						        
						       
						</c:forEach>
				        
				        
				    </tbody>
				</table>
                
                

            </div>
            </div>
		
	</section>
	
	<script>
		function removeList(songId,playlistId) {
	        location.href = "<c:url value='/201921031/admin/member/removeList' />?songId=" + songId + "&playlistId=" + playlistId;
	    }
	
	</script>
	
	
</body>
</html>