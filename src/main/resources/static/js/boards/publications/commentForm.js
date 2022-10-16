
function sendCommentRest(){
    let text = $("#textOfComment").val();

    $.ajax({
        url: '/boards/Music/2',
        method: 'post',
        headers: {
            'Content-Type':'application/json'
        },
        data:
            JSON.stringify({
                textOfComment: text
                }),
        success: function(data){
            console.log("commentForm.js : success sendCommentRest " + " ( "+ data + " ) " )
        },
        error : function (data){
            console.log("commentForm.js : error sendCommentRest " + " ( "+ data + " ) " )
        }
    });
}
