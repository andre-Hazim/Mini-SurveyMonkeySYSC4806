$(document).ready(function (){
    $("#fieldsButton").on("click", function (data){
        $.get("/getFieldRest", function (data,status) {
            let text = "<ul>";
            data.forEach(function (arrayItem){
                text += "<li>"+ JSON.stringify(arrayItem) + "</li>";
            });
            text += "</ul>";
            $("#fields").html(text);
        });
    });
});