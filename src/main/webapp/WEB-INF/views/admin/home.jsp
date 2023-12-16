<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<jsp:include page="../include/title.jsp" />

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
	
	<jsp:include page="../include/header.jsp" />
	
	<section>
	<div id="section_wrap">
	<button onclick="location.href='<c:url value='/201921031/admin/member/search' />'">create playlist</button><br><br>
		
		<div class="admin_list">

                <table>
				    <thead>
				        <tr>
				            <th>내 플레이리스트</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="item" items="${playlists}">
						    <c:if test="${item.playlistName ne 'Default Playlist'}">
				
					<tr>
					    <td><a href="<c:url value='/201921031/admin/member/playlistLoad?playlistId=${item.playlistId}&playlistName=${item.playlistName}'/>">${item.playlistName}</a></td>
					</tr>
						        
						        
						    </c:if>
						</c:forEach>
				        
				        
				    </tbody>
				</table>
                
                

            </div>
            </div>
		
	</section>
	
	
</body>
</html>