
let rememberedLastShownHiddenReplyToCommentDiv = null;
let isRememberedLastShownHiddenReplyToCommentDivLook = false;

function showHiddenReplyToCommentDiv(parent_comment_id){

    if (rememberedLastShownHiddenReplyToCommentDiv === null){
        rememberedLastShownHiddenReplyToCommentDiv = $("#publicationPage_replyToCommentDiv_id_"+parent_comment_id);
        rememberedLastShownHiddenReplyToCommentDiv.show()
        isRememberedLastShownHiddenReplyToCommentDivLook = true;
    } else {
        let newHiddenReplyToCommentDivClicked = $("#publicationPage_replyToCommentDiv_id_"+parent_comment_id);
        if (rememberedLastShownHiddenReplyToCommentDiv[0].id !== newHiddenReplyToCommentDivClicked[0].id){
            rememberedLastShownHiddenReplyToCommentDiv.hide();
            rememberedLastShownHiddenReplyToCommentDiv = newHiddenReplyToCommentDivClicked;
            rememberedLastShownHiddenReplyToCommentDiv.show();
            isRememberedLastShownHiddenReplyToCommentDivLook = true;
        } else if (rememberedLastShownHiddenReplyToCommentDiv[0].id === newHiddenReplyToCommentDivClicked[0].id) {
            if (isRememberedLastShownHiddenReplyToCommentDivLook === false) {
                rememberedLastShownHiddenReplyToCommentDiv.show();
                isRememberedLastShownHiddenReplyToCommentDivLook = true
            } else {
                rememberedLastShownHiddenReplyToCommentDiv.hide();
                isRememberedLastShownHiddenReplyToCommentDivLook = false
            }
        }
    }

}

function sendCommentAjax(){
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