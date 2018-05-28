function get_sensors() {
    $.ajax({
        url: 'inc/recuperer_donnees.php',
        method: "GET",
        success: function(data) {
            var donnees = JSON.parse(data);
        },
        error: function(data) {
            var erreur = JSON.parse(data)
            console.log(erreur);
        }
    });
    return donnees;
};
