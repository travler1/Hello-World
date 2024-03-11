<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.title{
	font-size:20px;
}
li{
	font-size:16px;
}
.title h2 {
  margin-right: 10px;
}
</style>
<!-- 내용 시작 -->
<div class="page-main">
	<div class="title">
		<h2>회원 상세 정보 <c:if test="${member.auth==1}">(수강생)</c:if><c:if test="${member.auth==2}">(현직자)</c:if></h2> 
		<input type="button" value="회원 정보 수정" onclick="location.href='update'">
	</div>
	
	<h3>필수정보</h3>
	<ul>
		<li></li>
		<li>이메일 : ${member.email}</li>
		<li>이름 : ${member.name}</li>
	</ul><br>
	<h3>선택정보</h3>
	<ul>
		<li></li>
		<li><c:if test="${!empty member.phone}">전화번호 : ${member.phone}</c:if></li>
		<li><c:if test="${!empty member.zipcode}">우편번호 : ${member.zipcode}</c:if></li>
		<li><c:if test="${!empty member.address1}">주소 : ${member.address1} ${member.address2}</c:if></li>
		<li><c:if test="${!empty member.reg_date}">가입날짜 : ${member.reg_date}</c:if></li>
		<c:if test="${!empty member.modify_date}">
			<li>정보 수정일 : ${member.modify_date}</li>
		</c:if>
	</ul>
</div>
<!-- 내용 끝 -->
