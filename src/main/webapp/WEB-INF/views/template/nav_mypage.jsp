<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- MyPage 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			
			<img src="${pageContext.request.contextPath}/member/photoView" width="200" height="200" class="my-photo">

			<div class="camera" id="photo_btn">
				<img src="${pageContext.request.contextPath}/images/camera.png" width="35">
			</div>
		</li>
		<li>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" name="myPhoto" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">
			</div>
		</li>
	</ul>
	<ul>
		<li>
			<input type="button" class="menu-btn" value="게시판활동" onclick="location.href='${pageContext.request.contextPath}/myPage/board'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="첨삭관리함" onclick="location.href='${pageContext.request.contextPath}/myPage/myAdvice'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="비밀번호변경" onclick="location.href='${pageContext.request.contextPath}/myPage/changePassword'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="채팅" onclick="location.href='${pageContext.request.contextPath}/myPage/talkList'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="회원탈퇴" onclick="location.href='${pageContext.request.contextPath}/myPage/delete'">
		</li>
	</ul>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.profile.js"></script>
<!-- MyPage 메뉴 끝 -->