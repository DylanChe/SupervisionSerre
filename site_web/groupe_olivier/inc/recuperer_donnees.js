$(document).ready(function () {
    $.ajax({
        url: 'inc/recuperer_donnees.php',
        method: "GET",
        success: function(data) {
            var donnees = JSON.parse(data);
            console.log(donnees); // affiche que c'est un objet
        },
        error: function(data) {
            var erreur = JSON.parse(data)
            console.log(erreur);
        }
    });
});
