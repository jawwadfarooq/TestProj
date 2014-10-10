<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket Client</title>
 <script type="text/javascript">      
	 var ws = new WebSocket("ws://localhost:8080/TestProj/example");
	 ws.onopen = function()
	 {
		alert("Web Socket is connected!!");			        
	 };
	 ws.onmessage = function (evt) 
	 { 			     	
		var msg = evt.data;
		alert("Message received:" +  msg);
	 };
	 ws.onclose = function()
	 { 
		alert("Connection is closed..."); 
	 };
	 
	 function sendMessage() {
			ws.send("Message to server from client");
		}
					
  </script>
</head>
<body>
 	<a href="javascript:sendMessage()">Send message</a>
</body>
</html>
