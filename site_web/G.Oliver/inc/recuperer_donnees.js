$("#donnees"){
    $.ajax({
        url : 'recuperer_donnees.php',
        type : 'GET',
        dataType : 'json',
        success: function(data) {
            return (data);
        },
        error: function() {
            return "Hello";
        }
    });
};
