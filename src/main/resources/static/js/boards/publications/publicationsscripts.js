
//isHiddenReplyToCommentDivShown = false;


function showHiddenReplyToCommentDiv(id){

    console.log(id);
    let hiddenReplyToCommentDiv = $("#reply_to_comment_div_"+id);
    // if(isHiddenReplyToCommentDivShown === true){
    //     hiddenReplyToCommentDiv.hide();
    //     isHiddenReplyToCommentDivShown = false;
    // }
    // else{
        hiddenReplyToCommentDiv.show();
      //  isHiddenReplyToCommentDivShown = true;
   // }
}

function sendCommentRest(){
    let text = $("#textOfComment").val();
    let publicationId = $("#publicationIdHiddenValue").val();
    fetch("/boards/Music/"+publicationId,
        {
            headers:
                {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            method: "POST",
            body: JSON.stringify({
                textOfComment: text
            }),
        })
        .then(function(res){ console.log(res) })
        .catch(function(res){ console.log(res) })

}