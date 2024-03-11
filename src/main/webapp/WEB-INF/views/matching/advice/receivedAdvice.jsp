<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/advice.css">
<!-- 내용 시작 -->
<div class="page-main">
	<br>
	<div class="align-center">
	<button class="listButton" id="myAdviceButton" onclick="myAdvice()">받은 첨삭요청함</button>
	<button class="listButton" id="myAdviceButtonSent" onclick="myAdviceSent()">보낸 첨삭요청함</button>
	</div>
	<br><br>
	<form action="/myPage/myAdvice" id="asearch_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield" style="height:44px;">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>보낸사람</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>보낸사람+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">`
				<input type="submit" value="찾기">
			</li>
			<li>
			</li>
		</ul>
	</form>
	<br><br>
	<c:if test="${count == 0}">
	<div class="result-display">받은 첨삭이 없습니다.</div>
	</c:if>
	<c:if test="${count >0 }">
	<table class="striped-table">
		<tr>
			<td class="align-center">보낸 사람</td>
			<td class="align-center">전송일</td>
			<td class="align-center">읽은 날짜</td>
			<td class="align-center">첨삭 진행상태</td>
		</tr>
		<c:forEach var="advice" items="${list}">
		<tr>
			<td class="align-center">
				<a href="${pageContext.request.contextPath}/myPage/adviceDetail?advice_num=${advice.advice_num}">
				${advice.name}
				</a>
			</td>
			<td class="align-center">
				<a href="${pageContext.request.contextPath}/myPage/adviceDetail?advice_num=${advice.advice_num}">
			${advice.date_sent}
				</a>
			</td>
			<c:if test="${!empty advice.date_read}">
				<td class="align-center">${advice.date_read}</td>
			</c:if>
			<c:if test="${empty advice.date_read}">
				<td class="align-center">읽지 않음</td>
			</c:if>
			<td class="align-center">
				<c:if test="${advice.advice_complete==0}">첨삭 완료전</c:if>
				<c:if test="${advice.advice_complete==1}">첨삭완료</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script>
	$(function(){
		//검색 유효성 체크
		$('#asearch_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}	
		});//end of submit
	});

    function myAdvice() {
        window.location.href = '${pageContext.request.contextPath}/myPage/myAdvice';
    }

    function myAdviceSent() {
        window.location.href = '${pageContext.request.contextPath}/myPage/myAdviceSent';
    }
</script>
<!-- 내용 끝 -->
