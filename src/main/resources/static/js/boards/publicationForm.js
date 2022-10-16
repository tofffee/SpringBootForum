isPublicationFormShown = false

function show_hide_PublicationForm(){
    var publicationForm = $('#publicationFormDivContainer');
    if(isPublicationFormShown === true){
        publicationForm.hide();
        isPublicationFormShown = false;
    }
    else{
        publicationForm.show();
        isPublicationFormShown = true;
    }

}

/*
function validatePublicationForm() {
    let x = document.forms["myForm"]["fname"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
}*/
