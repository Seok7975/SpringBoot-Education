<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>웹소켓 채팅</title></head>
<body>
    <script>
    function chatWinOpen() {
        var id = document.getElementById("chatId");
        if (id.value == "") {
            alert("대화명을 입력 후 채팅창을 열어주세요.");
            id.focus();
            return;
        }
        //채팅창을 팝업으로 오픈한다. 
        window.open("chatWindow.do?chatId=" + id.value, "", 
        		"width=350,height=450");
        //다음 창을 띄울수 있도록 기존 입력값을 삭제한다. 
        id.value = "";
    }
    </script>
    <h2>웹소켓 채팅 - 대화명 적용해서 채팅창 띄워주기</h2>
    대화명 : <input type="text" id="chatId" />
    <button onclick="chatWinOpen();">채팅 참여</button>
</body>
</html>



