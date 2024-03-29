function emp_register() {
    var width = '700';
    var height = '700';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;

    // 팝업 창 열기
    var popupWindow = window.open("emp_register", "현직자 신청하기", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");

    // 팝업 내용 스타일 조절
    if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.maxHeight = '80vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
        };
    }
}
function my_emp_register(user_num) {
    var width = '700';
    var height = '700';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;

    // 팝업 창 열기
    window.open("my_emp_register?user_num="+user_num, "나의 프로필 확인하기", "width=" + width  + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");
    
     if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.Height = '100vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
        };
    }
    
}
function see_emp_register(user_num) {
    var width = '700';
    var height = '700';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
	
    
    // 팝업 창 열기
    var popupWindow = window.open("see_emp_register?user_id="+user_num, "현직자 프로필 확인하기", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");
	
    if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.Height = '100vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
        };
    }
}

function send_advice(user_num) {
    var width = '700';
    var height = '600';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
	
    
    // 팝업 창 열기
    var popupWindow = window.open("send_advice?user_id="+user_num, "첨삭 요청하기", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");
	
    if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.Height = '100vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
        };
    }
}
function answer_advice(user_num) {
    var width = '600';
    var height = '430';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
	
    
    // 팝업 창 열기
    var popupWindow = window.open("answer_advice?user_id="+user_num, "쪽지 보내기", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");
	
    if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.Height = '60vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
        };
    }
}

function make_chat(user_num){
	var width = '700';
    var height = '700';

    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
	
    
    // 팝업 창 열기
    var popupWindow = window.open("make_chat?user_num="+user_num, "쪽지 보내기", "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + ",scrollbars=yes");
	
    if (popupWindow) {
        popupWindow.onload = function () {
            var popupContent = popupWindow.document.body; // 팝업 창 내용이 들어가는 부분
            popupContent.style.overflowY = 'auto'; // 세로 스크롤바를 항상 표시하도록 설정
            popupContent.style.Height = '60vh'; // 팝업 창의 최대 높이를 설정 (가능하면 vh 단위 사용)
            chatOpen();
        };
    }
}