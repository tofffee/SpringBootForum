isPublicationFormShown = false

function show_hide_PublicationForm(){
    var publicationForm = $('#boardPage_publicationFormDiv_id');
    if(isPublicationFormShown === true){
        publicationForm.hide();
        isPublicationFormShown = false;
    }
    else{
        publicationForm.show();
        isPublicationFormShown = true;
    }

}
