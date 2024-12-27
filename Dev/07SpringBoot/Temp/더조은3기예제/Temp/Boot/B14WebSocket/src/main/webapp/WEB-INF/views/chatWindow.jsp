<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<html>
<head>
<title>웹소켓 채팅</title>
<script>
/* 페이지가 로드되면 제일 먼저 웹소켓 객체를 생성한다. 이때 사용하는 주소는
웹소켓 설정 클래스에서 지정한 요청명을 사용해야한다. */
var webSocket
    = new WebSocket("ws://192.168.0.162:8586/myChatServer");

//전역변수 생성
var chatWindow, chatMessage, chatId;

/* 채팅창이 열리면 대화창, 메세지입력창, 대화명 표시란으로 사용할 DOM객체를
저장한다. */
window.onload = function() {
    chatWindow = document.getElementById("chatWindow");
    chatMessage = document.getElementById("chatMessage");
    chatId = document.getElementById('chatId').value;    
}

//입력된 메세지를 전송할때 호출한다. 
function sendMessage() {
	//메세지를 대화창에 표시한다. 
    chatWindow.innerHTML += "<div class='myMsg'>" + 
    							chatMessage.value + "</div>"
    //웹소켓 서버로 메세지를 전송한다. 전송 형식은 "채팅아이디|메세지내용"
    webSocket.send(chatId + '|' + chatMessage.value); 
    //다음 메세지를 즉시 입력할 수 있도록 비워준다. 							
    chatMessage.value = ""; 
    //대화창의 스크롤을 처리한다. 
    chatWindow.scrollTop = chatWindow.scrollHeight; 
}

//웹소켓 서버에서 접속종료
function disconnect() {
    webSocket.close();
}

//메세지 입력후 엔터키를 누르면 즉시 메세지 전송  
function enterKey() {
	console.log("키 눌러짐");
	//Enter 키의 코드값이 13임 
    if (window.event.keyCode == 13) {  
        sendMessage();
    }
}

//웹소켓 서버에 연결되었을때 호출
webSocket.onopen = function(event) {   
    chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다.<br/>";
};

//웹소켓 서버가 종료되었을때 호출 
webSocket.onclose = function(event) {
    chatWindow.innerHTML += "웹소켓 서버가 종료되었습니다.<br/>";
};

//에러가 발생했을때 호출
webSocket.onerror = function(event) { 
    alert(event.data);
    chatWindow.innerHTML += "채팅 중 에러가 발생하였습니다.<br/>";
}; 

//웹소켓 서버가 메세지를 받았을때 호출 
webSocket.onmessage = function(event) {
	//대화명과 메세지를 분리한다. 메세지 전송시 |(파이프)로 연결하고있다.
    var message = event.data.split("|");  
	//보낸 사람의 대화명
    var sender = message[0];
	//메세지 내용 
    var content = message[1]; 
    if (content != "") {
        if (content.match("/")) { 
        	//메세지에 /가 포함되어 있으면 명령어로 인식하여 처리한다. 
            if (content.match(("/" + chatId))) {
            	/* 귓속말은 지정된 특정 사용자에게만 보여진다. 서버에서는
            	모든 사용자에게 메세지를 Echo하지만 if문을 통해 한사람
            	에게만 디스플레이 처리된다. */
                var temp = content.replace(("/" + chatId), 
                		"[귓속말] : ");
                chatWindow.innerHTML += "<div>" + sender 
                	+ temp + "</div>";
            }
        }
        else {  
        	//슬러쉬가 없다면 일반적인 메세지로 판단한다. 
            chatWindow.innerHTML += "<div>" + sender + " : " 
            	+ content + "</div>";
        }
    }
    //스크롤 처리 
    chatWindow.scrollTop = chatWindow.scrollHeight; 
};
</script>
<style>
#chatWindow{border:1px solid black; width:270px; height:310px; 
	overflow:scroll; padding:5px;}
#chatMessage{width:236px; height:30px;}
#sendBtn{height:30px; position:relative; top:2px; left:-2px;}
#closeBtn{margin-bottom:3px; position:relative; top:2px; left:-2px;}
#chatId{width:143px; height:24px; border:1px solid #AAAAAA; 
	background-color:#EEEEEE;}
.myMsg{text-align:right;}
</style>
</head>

<body> 
    대화명 : 
    <input type="text" id="chatId" value="${ param.chatId }" 
    	readonly />
    <button id="closeBtn" onclick="disconnect();">채팅 종료</button>
    <div id="chatWindow"></div>
    <div>
        <input type="text" id="chatMessage" onkeyup="enterKey();">
        <button id="sendBtn" onclick="sendMessage();">전송</button>
    </div>    
</body>
</html>

