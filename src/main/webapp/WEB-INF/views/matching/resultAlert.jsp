<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
    alert('${message}');
    window.opener.location.href = '${url}'; // 부모 창의 URL 변경
    window.close(); // 현재 창(팝업) 닫기
</script>