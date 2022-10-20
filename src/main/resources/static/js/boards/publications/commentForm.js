
function sendCommentRest(){
    let text = $("#textOfComment").val();

    fetch("/boards/Music/2",
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
