<!-- admin/member/songList.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp" />

    <link href="<c:url value='/201921031/resources/css/admin/listup_admins.css' />" rel="stylesheet" type="text/css">
    <style>
    	#section_wrap .admin_list table thead tr th{
    		background-color:#6aad6a;
    	}
    
    </style>
    
</head>
<body>

    <jsp:include page="../../include/header.jsp" />

    <section>
  

        <div id="section_wrap">

            <div class="word">
                <h3>노래 검색</h3>
                <div>
				    <button style=margin-right:80px; onclick="location.href='<c:url value='/201921031/admin/member/mainload' />'">메인으로</button>
				    <input type="text" id="search" name="search" placeholder="검색어 입력">
				    <button style=margin-right:80px onclick="searchSongs()">검색</button>
				    
				    <input type="text" id="newPlaylist" name="newPlaylist" placeholder="플레이리스트 이름 입력">
				    <button onclick="createNewPlaylist()">플레이리스트 생성</button>
				</div>
                
                
            </div>

            <div class="admin_list">

                <table>
                    <thead>
                        <tr>
                            <th>가수 &nbsp;<button onclick="nameSort()">이름정렬</button></th>
                            <th>제목 &nbsp;<button onclick="titleSort()">제목정렬</button></th>
                            <th>장르 &nbsp;<button onclick="genreSort()">장르정렬</button></th>
                            <th>플레이리스트 추가</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${songVos}">
                            <tr>
                                <td>${item.singer}</td>
                                <td>${item.title}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty item.genre}">
                                            <input type="text" id="genreInput_${item.songId}" name="genreInput" placeholder="장르 입력">
                                            <button onclick="updateGenre('${item.songId}')">장르 추가</button>
                                        </c:when>
                                        <c:otherwise>
                                            ${item.genre}
                                        </c:otherwise>
                          		
                                    </c:choose>
                                    
                                </td>
                                <td>
                                	<select id="playlistSelect_${item.songId}" name="playlistSelect">
									    <c:forEach var="playlist" items="${playlists}">
									    	<c:if test="${playlist.playlistName ne 'Default Playlist'}">
									    		<option value="${playlist.playlistId}" >${playlist.playlistName}</option>
									    	</c:if>
									        
									    </c:forEach>
									</select>
									<button onclick="addMusicToPlaylist('${item.songId}')">노래 추가</button>
                                	
                                	
                    
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>

    </section>

    <script>
        function searchSongs() {
            var keyword = document.getElementById("search").value;
            location.href = "<c:url value='/201921031/admin/member/searchSongs' />?keyword=" + keyword;
        }
        
        function nameSort(){
            location.href = "<c:url value='/201921031/admin/member/nameSort' />"
        }
        
        function titleSort(){
            location.href = "<c:url value='/201921031/admin/member/titleSort' />"
        }
        
        function genreSort(){
            location.href = "<c:url value='/201921031/admin/member/genreSort' />"
        }

        function updateGenre(songId) {
            var genreInput = document.getElementById("genreInput_" + songId).value;
            location.href = "<c:url value='/201921031/admin/member/genreAdd' />?songId=" + songId + "&genreInput=" + genreInput;
        }
        
        function createNewPlaylist() {
            var playlistName = document.getElementById("newPlaylist").value;
            location.href = "<c:url value='/201921031/admin/member/createNewPlaylist' />?playlistName=" + playlistName;
        }
        
        function addMusicToPlaylist(songId) {
            var playlistSelect = document.getElementById("playlistSelect_" + songId);
            var selectedPlaylistId = playlistSelect.value;
            location.href = "<c:url value='/201921031/admin/member/playlistAdd' />?songId=" + songId + "&playlistId=" + selectedPlaylistId;
        }


            
    </script>

</body>
</html>
