<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainpage.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="1070543844726-2r3lgjacasr2slbtnnl3101at75dlkag.apps.googleusercontent.com">
<div class="g-signin2" data-onsuccess="onSignIn"></div>
<div class="main-background">
	<div class="greeting-quote">
		Hello ,  World !
	</div>
	
	<div class="info">
		코딩을 처음 시작했을 때를 기억하시나요?<br>
		Hello, World를 치며 두려움보단 설렘으로 가득 찼던 그 때..<br>
		이제 취업을 위해 진짜 세상과 마주칠 여러분들을 위해<br>
		Hello World!가 함께 하겠습니다.<br>
	</div>
	<c:if test="${empty user}">
	<button class="login button" onclick="location.href='${pageContext.request.contextPath}/main/login'" >Log In</button>
	</c:if>
	<c:if test="${!empty user}">
	<button class="main button" onclick="location.href='${pageContext.request.contextPath}/matching/main'" >취업현황 바로가기</button>
	</c:if>
</div>
<script>
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}
</script>