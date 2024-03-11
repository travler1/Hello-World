<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/hwChat.js"></script>
<div id="talkDetail" class="page-main">
	<div id="chatting_message" class="hide"></div>
	<form method="post" id="detail_form">
		<input type="hidden" name="chatRoom_num" id="chatRoom_num" value="${chatRoom_num}">
		<div id="textareaBox" class="hide">
			<textarea rows="20" cols="40" name="chat_message" id="chat_message"></textarea>
				 <div id="message_btn">
					<input type="submit" value="전송" >
				</div>    
		</div>
	</form>
</div>      