

let stompClient = null;
let socket = null;

function connect(event) {

    socket = new SockJS('/ws');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);

    alert("connecting");
    console.log("chat.js : connecting")

    event.preventDefault();
}


function onConnected() {

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    alert("connected");
    console.log("chat.js : successfully connected")
}
function onError(error) {

    alert("on error connected")
    console.log("chat.js : error connected")
}

function sendMessageJs(event) {
    let messageContent = $("#messageInput").val();
    if(messageContent && stompClient) {
        let chatMessage = {
            textOfChatMessage: messageContent
           // type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
        alert("message was sent");
        console.log("message was sent");
    }
}

function onMessageReceived(payload) {

    let message = JSON.parse(payload.body);

    alert("message was recieved");
    console.log("message was recieved:" + message);

}

function disconnectFromChat(event)
{
    stompClient.disconnect(
        function()
    {
        socket.close();
        alert("disconnectFromChat");
        console.log("disconnectFromChat");
        alert(stompClient.status);
    });

}
