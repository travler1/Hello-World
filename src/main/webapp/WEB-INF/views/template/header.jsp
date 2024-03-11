<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<!-- 상단 시작-->
<body>
    <header>
	<a href="${pageContext.request.contextPath}/main/main"><img src="${pageContext.request.contextPath}/images/logo.jpg" id="logo"></a>
 		<nav>
        	<a class="header-menu" href="${pageContext.request.contextPath}/matching/main">취업현황</a>
        	<a class="header-menu" href="${pageContext.request.contextPath}/board/list">게시판</a>
    	</nav>

<div class="align-right">
	<c:if test="${!empty user}">
	</c:if>
	<c:if test="${!empty user}">
	[<span class="user_name">${user.name}</span>]님
	<a href="${pageContext.request.contextPath}/member/myPage">
	<img src="${pageContext.request.contextPath}/member/photoView"  class="my-photo image">
	</a> 
	</c:if>
	
	<c:if test="${!empty user}">
	<a href="${pageContext.request.contextPath}/member/logout"><img src="${pageContext.request.contextPath}/images/logout.png" class="image"></a>
	</c:if>

	<c:if test="${!empty user && user.auth ==9}">
	<a href="${pageContext.request.contextPath}/main/admin">관리자페이지</a>
	</c:if>
</div>
    </header>
</body>
<!-- 상단 끝-->