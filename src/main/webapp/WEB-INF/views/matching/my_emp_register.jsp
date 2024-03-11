<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/emp_register.css">
<script>
	function closePopup(){
		window.close();
	}
</script>

<h2>[${user.name}]님의 현직자 신청 정보</h2>
<div class="marquee">
	<p>
		<b>${user.name}</b>님의 취업을 진심으로 축하드립니다!
	</p>
</div>
<table>
	<tr>
		<td style="width:200px">이름</td>
		<td colspan="7">${user.name}</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td colspan="5">${user.email}</td>
	</tr>
	<tr>
		<td>회사규모</td>
		<td colspan="5">${empVO.comsize}</td>
	</tr>
	<tr>
		<td>기업인력규모</td>
		<td colspan="5">${empVO.compeople}</td>
	</tr>
	<tr>
		<td>지원분야</td>
		<td colspan="5">${empVO.field}</td>
	</tr>
	<tr>
		<td>직무</td>
		<td colspan="5">${empVO.role} <c:if test="${empVO.role=='6'}">기타</c:if>
		</td>
	</tr>
	<tr>
		<td>지원형태</td>
		<td colspan="5">${empVO.career}</td>
	</tr>
	<tr>
		<td>연봉 공개유무 : ${empVO.salary_status}
		</td>
		<td colspan="5">${empVO.salary}</td>
	</tr>
	<tr>
		<td>수료 후 준비기간</td>
		<td colspan="5">${empVO.periodtime}</td>
	</tr>
	<tr>
		<td>학력</td>
		<td colspan="5">
			${empVO.education}
		</td>
	</tr>
	<tr>
		<td>전공 유무</td>
		<td colspan="5"><c:out value="${empVO.major}" /></td>
	</tr>
	<tr>
		<td>취업 당시 보유 자격증</td>
		<td colspan="5">
			${empVO.certification}
		</td>
	</tr>
	<tr>
		<td>취업회사 지역</td>
		<td colspan="5">
			${empVO.location}
		</td>
	</tr>
	<tr>
			<td>취업한 회사 지역</td>
			<td colspan="5">
				<div class="map_wrap">
    			<div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    			<div class="hAddr">
      			  <span class="title">지도중심기준 행정동 주소정보</span>
       			  <span id="centerAddr"></span>
   				</div>
		    	<input type="hidden" id="location_api" name="location_api" value="${empVO.location_api}">
		    	<input type="hidden" id="location_api_lat" name="location_api_lat" value="${empVO.location_api_lat}">
		    	<input type="hidden" id="location_api_lng" name="location_api_lng" value="${empVO.location_api_lng}">
		    	<input type="hidden" id="user_num" name="user_num" value="${empVO.mem_num}">
		</div>
			</td>
		</tr>
	<tr>
		<td>취업일시</td>
		<td colspan="5">
			${empVO.workstart}
		</td>
	</tr>
	<tr>
		<td>합격인증</td>
		<td colspan="5">
			<img src="${pageContext.request.contextPath}/upload/${empVO.filename}" class="emp_auth">
		</td>
	</tr>
	<tr>
		<td>후배 쌍용인들에게 <br>조언 한 마디!</td>
		<td colspan="5">
			${empVO.advice}	
		</td>
	</tr>

</table>
<div class="button">
	<input type="button" class="submit" value="수정"> 
	<input type="button" class="submit" value="닫기" onclick="closePopup()">
</div>
<br>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e592360b98e7e7af7c0620352a4709c&libraries=services"></script>
<script>

	//지도의 위치 정보 받아오기
	var api = $('#location_api').val();
	var lat = $('#location_api_lat').val();
	var lng = $('#location_api_lng').val();
	var user_num = $('#user_num').val();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(lat,lng), // 지도의 중심좌표
	        level: 7 // 지도의 확대 레벨
	    };  
	
	 console.log(location_api);
	
	 var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
		    center : new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표 
		    level : 9 // 지도의 확대 레벨 
		});
	 
	
	 
 	var content = '<div class ="label"><span class="left"></span><span class="center">'
 				 +'<br><img src="${pageContext.request.contextPath}/matching/viewProfile?userNum='
 				 +user_num+'" width="40" height="40" class="my-photo"></span><span class="right">'
 				 +'</span></div>';

 	// 커스텀 오버레이가 표시될 위치입니다 
 	var position = new kakao.maps.LatLng(lat,lng);  

 	// 커스텀 오버레이를 생성합니다
 	var customOverlay = new kakao.maps.CustomOverlay({
 	    position: position,
 	    content: content   
 	});

 	// 커스텀 오버레이를 지도에 표시합니다
 	customOverlay.setMap(map);
	
</script>
