$(function() {
    $('#etage').change(function() {
        $.post('inc/ActuSalle.php', // La page de la requête
            {
                batiment: $("#batiment").val(), // Id du menu des batiments
                etage: $("#etage").val() // Id du menu des étages
            },

            function(data) // Fonction qui gère le retour
            {
                if(data != 'erreur') // Si il n'y a pas d'erreures
                {
                    $('#salle').html(data); // Ajout des options dans le select
                }
                else
                {
                    alert("erreur"); // Affichage du message en cas d'erreur
                }
            }
        );
    });
});