<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
*{
	margin:0;
	padding:0;
}

.gnb-menu {
	background:#282828;
	height:50px;
	display:flex;
	justify-content:space-around;
	align-items:center;
}

.gnb-menu a {
	font-size:24px;
	color:#fff;
	text-decoration:none;
	position:relative;
	padding:6px 12px;
}

.gnb-menu a::after {
	content:"";
	position:absolute;
	bottom:0;
	left:50%;
	transform:translateX(-50%);
	width:0;
	height:4px;
	background:#f1c40f;
	transition:all .5s ease-out;
}

.gnb-menu a:hover::after {
	width:100%;
}
</style>
<body>
	<nav class="gnb-menu">
		<a href="">쪽지함</a>
		<a href="">첨삭관리함</a>
		<a href="">내가 쓴 글</a>
	</nav>
</body>