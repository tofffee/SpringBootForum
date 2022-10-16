
function sendMessageRest(){
    let text = $("#textOfMessage").val();

    $.ajax({
        url: '/chat',
        method: 'post',
        headers: {
            'Content-Type':'application/json'
        },
        data:
            JSON.stringify({
                textOfMessage: text
            }),
        success: function(data){
            console.log("sendMessageRest.js : success sendMessageRest " + " ( "+ data + " ) " )
        },
        error : function (data){
            console.log("sendMessageRest.js : success sendMessageRest " + " ( "+ data + " ) " )
        }
    });
}