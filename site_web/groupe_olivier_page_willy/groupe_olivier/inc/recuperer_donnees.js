$(document).ready(function () {
    $.ajax({
        url: 'inc/recuperer_donnees.php',
        method: "GET",
        success: function(data) {
            var donnees = JSON.parse(data);
            return donnees;
        },
        error: function(data) {
            var erreur = JSON.parse(data)
            console.log(erreur);
        }
    });
});
