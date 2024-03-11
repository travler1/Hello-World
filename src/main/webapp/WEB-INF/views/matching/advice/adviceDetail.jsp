<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/advice.css">
<script src="${pageContext.request.contextPath}/js/matching/register.js"></script>
    <div class="page-main">
        <ul class="content">
            <li class="main-text">보낸 사람 
                <c:if test="${sender == loginUser}">나</c:if>
                <c:if test="${sender != loginUser}">${sender}님</c:if>
            </li>
            <li class="main-text">받은 사람 
                <c:if test="${receiver == loginUser}">나</c:if>
                <c:if test="${receiver != loginUser}">${receiver}님</c:if>
            </li>
            <li class="main-text">보낸 날짜 ${advice.date_sent}</li>
            <li class="main-text">첨삭 진행상태 : 
            	<c:if test="${advice.advice_complete==0}">첨삭 완료전</c:if>
				<c:if test="${advice.advice_complete==1}">첨삭완료</c:if>
            </li>
            <li class="advice_content">
            ${advice.advice_content}
            </li>
            <li class="main-text">
           		<a href="download.do?advice_num=${advice.advice_num}">첨부파일</a>
            </li>
        </ul>
        <div class="align-center">
            <input type="button" class="adviceButton" value="첨삭 답장하기" onclick="send_advice(${advice.sender})">
            <input type="button" class="adviceButton" value="삭제" onclick="send_advice(${advice.sender})">
            <input type="button" class="adviceButton" value="목록" onclick="location.href=''">
        </div>
    </div>