// Websocket Endpoint url
var endPointURL = "ws://localhost:8080/TestProj/ws";
 
var chatClient = null;
 
function connect () {
    chatClient = new WebSocket(endPointURL);
    chatClient.onmessage = function (event) {
        var messagesArea = document.getElementById("messages");
        messagesArea.value = messagesArea.value + event.data + "\n";
        messagesArea.scrollTop = messagesArea.scrollHeight;
    };
}
 
function disconnect () {
    chatClient.close();
}
 
function sendMessage() {
    var inputElement = document.getElementById("messageInput");
    var message = inputElement.value.trim();
    if (message !== "") {
        chatClient.send(message);
        inputElement.value = "";
    }
    inputElement.focus();
}

