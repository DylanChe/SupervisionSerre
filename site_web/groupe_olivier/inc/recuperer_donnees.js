$(document).ready(function () {
    $.ajax({
        url: 'recuperer_donnees.php',
        method: "GET",
        success: function(data) {
            //var donnees = JSON.parse(data);
            console.log(data); // affiche que c'est un objet
        },
        error: function(data) {
            console.log(data);
        }
    });
});
