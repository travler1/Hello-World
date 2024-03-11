<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 내용 시작 -->
<meta name="google-signin-client_id"
	content="1070543844726-2r3lgjacasr2slbtnnl3101at75dlkag.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>

<!-- 카카오톡 로그인 -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js"
	integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8"
	crossorigin="anonymous"></script>
<script>
	Kakao.init('566a419ae45939c643cbf4a047155a00'); // 사용하려는 앱의 JavaScript 키 입력
</script>
<div id="notion-app">
	<div class="notion-app-inner notion-light-theme"
		style="color: rgb(55, 53, 47); fill: currentcolor; line-height: 1.5; font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont,&amp; amp; quot; Segoe UI&amp;amp; quot; , Helvetica , &amp;amp; quot; Apple Color Emoji&amp;amp; quot; , Arial , sans-serif, &amp;amp; quot; Segoe UI Emoji&amp;amp; quot; , &amp; amp; quot; Segoe UI Symbol&amp;amp; quot;; -webkit-font-smoothing: auto; background-color: white;">
		<div style="height: 100%;">

			<div
				style="position: fixed; z-index: 99; font-family: inter, -apple-system, BlinkMacSystemFont,&amp; amp; quot; Segoe UI&amp;amp; quot; , Helvetica , &amp;amp; quot; Apple Color Emoji&amp;amp; quot; , Arial , sans-serif, &amp;amp; quot; Segoe UI Emoji&amp;amp; quot; , &amp; amp; quot; Segoe UI Symbol&amp;amp; quot;; width: 100%;">
				<nav
					style="display: flex; align-items: center; justify-content: space-between; width: 100%; height: 54px;">
					<div style="flex-shrink: 0;">
						<a href="${pageContext.request.contextPath}/main/main"
							style="display: block; color: inherit; text-decoration: none; user-select: none; cursor: pointer;">
							<img id="logo"
							src="${pageContext.request.contextPath}/images/logo.jpg"
							style="height: 40px; margin-top: 15px;">
						</a>
					</div>
				</nav>
			</div>


			<main
				style="color: rgb(4, 4, 4); fill: currentcolor; line-height: 1.5; font-family: inter, -apple-system, BlinkMacSystemFont,&amp; amp; quot; Segoe UI&amp;amp; quot; , Helvetica , &amp;amp; quot; Apple Color Emoji&amp;amp; quot; , Arial , sans-serif, &amp;amp; quot; Segoe UI Emoji&amp;amp; quot; , &amp; amp; quot; Segoe UI Symbol&amp;amp; quot;; background: rgb(255, 254, 252); font-size: 17px; -webkit-font-smoothing: antialiased;">
				<div style="display: flex; flex-direction: row; width: 100%;">
					<section
						style="padding-left: 40px; padding-right: 40px; width: 100%; margin: 0px auto; overflow: visible;">
						<div style="width: 100%; border-radius: 4px;">
							<div style="width: 100%; max-width: 1260px; margin: 0px auto;">
								<div
									style="display: flex; flex-direction: column; align-items: center;">
									<div
										style="font-size: 50px; margin-top: 12vh; font-weight: 700; margin-bottom: 24px; text-align: center; line-height: 1.1; max-width: 380px;">로그인</div>

									<div class="notion-login"
										style="width: 100%; display: flex; flex-direction: column; align-items: center; max-width: 320px; margin-bottom: 16vh;">
										<div
											style="display: flex; width: 100%; flex-direction: column;">
											<div style="flex:1;display: flex; justify-content: space-between;">
												<form:form id="member_login" action="login" modelAttribute="memberVO">
													<div class="login_choice" style="flext:1;display: flex; justify-content: space-between;">
														<div class="student" style="width:180px;height:180px;border-radius: 8px; border: 1px solid #ccc;">
															<div style="text-align:center"><input type="radio" value="student" name="login_choice" class="login_choice" style="text-align:center"></div>
															<div style="text-align:center"><img src="${pageContext.request.contextPath}/images/student.png" style="width:100px;text-align:center;" ></div>
															<p style="text-align:center">수강생으로 로그인</p>
														</div>
														&nbsp;&nbsp;&nbsp;
														<div class="worker" style="flex:1;width:180px;height:180px;border-radius: 8px; border: 1px solid #ccc;">
															<div style="text-align:center"><input type="radio" value="worker" name="login_choice" class="login_choice"></div>
															<div style="text-align:center"><img src="${pageContext.request.contextPath}/images/worker.png" style="width:100px;"></div>
															<p style="text-align:center">현직자로 로그인</p>
														</div>
													</div><br>
													<div class="form_input">
													<form:label path="email"
														style="display: block; margin-bottom: 4px; font-size: 12px; color: rgba(55, 53, 47, 0.65); font-weight: 500;">이메일</form:label>
													<div class="notion-focusable-within"
														style="display: flex; align-items: center; width: 100%; font-size: 15px; line-height: 26px; position: relative; border-radius: 4px; box-shadow: rgba(15, 15, 15, 0.1) 0px 0px 0px 1px inset; background: rgba(242, 241, 238, 0.6); cursor: text; padding: 4px 10px; margin-top: 4px; margin-bottom: 8px;">
														<form:input path="email" placeholder="이메일을 입력하세요."
															cssClass="form-input" autocomplete="off"
															aria-label="이메일을 입력하세요."
															style="font-size: inherit; line-height: inherit; border: none; background: none; width: 100%; display: block; resize: none; padding: 0px;" />
														<form:errors path="email" element="div"
															cssClass="error-color" />
													</div>
													<form:label path="passwd"
														style="display: block; margin-bottom: 4px; font-size: 12px; color: rgba(55, 53, 47, 0.65); font-weight: 500;">비밀번호</form:label>
													<div class="notion-focusable-within"
														style="display: flex; align-items: center; width: 100%; font-size: 15px; line-height: 26px; position: relative; border-radius: 4px; box-shadow: rgba(15, 15, 15, 0.1) 0px 0px 0px 1px inset; background: rgba(242, 241, 238, 0.6); cursor: text; padding: 4px 10px; margin-top: 4px; margin-bottom: 8px;">
														<form:password path="passwd" placeholder="비밀번호를 입력하세요."
															cssClass="form-input" aria-label="비밀번호를 입력하세요."
															style="font-size: inherit; line-height: inherit; border: none; background: none; width: 100%; display: block; resize: none; padding: 0px;" />
														<form:errors path="passwd" element="div"
															cssClass="error-color" />
													</div>
													<input type="checkbox" name="auto" id="autologin">자동로그인
														<form:button class="login-btn" aria-disabled="false"
														role="button" tabindex="0"
														style="border:none; user-select: none; transition: background 20ms ease-in 0s; cursor: pointer; display: inline-flex; align-items: center; justify-content: center; white-space: nowrap; height: 36px; border-radius: 4px; font-size: 14px; line-height: 1; padding-left: 12px; padding-right: 12px; font-weight: 500; background: rgb(35, 131, 226); box-shadow: rgba(15, 15, 15, 0.1) 0px 0px 0px 1px inset, rgba(15, 15, 15, 0.1) 0px 1px 2px; color: white; margin-top: 6px; margin-bottom: 12px; width: 100%;">로그인</form:button>
													</div>
												</form:form>
											</div>

											<div
												style="font-size: 14px; color: rgb(235, 87, 87); text-align: center; display: none; width: 100%;"></div>
											<div
												style="color: rgba(55, 53, 47, 0.65); font-size: 14px; line-height: 1.6; margin-top: 8px; margin-bottom: 8px; text-align: center;">
												<a href="${pageContext.request.contextPath}/member/register" class="notion-link"style="display: inline; color: inherit; text-decoration: underline; user-select: none; cursor: pointer;">
												회원가입하기</a>&nbsp;&nbsp;&nbsp;&nbsp; 
												<a href="#" class="notion-link" style="display: inline; color: inherit; text-decoration: underline; user-select: none; cursor: pointer;">
												비밀번호찾기</a>
											</div>
											<div
												style="color: rgba(55, 53, 47, 0.65); font-size: 14px; line-height: 1.6; margin-top: 8px; margin-bottom: 8px; text-align: center;">
												소셜로그인으로 3초만에 회원가입!</div>
											<div class="snslogin" style="text-align: center;">
												<div class="g-signin2" data-onsuccess="onSignIn"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>

			</main>
		</div>
	</div>
</div>









