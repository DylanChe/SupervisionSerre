<?php
require_once('connect.php');

    $periode = $_POST['periode'];
    $request = $bdd->prepare( 'UPDATE parametre SET periode = :periode WHERE id = "1"' );
    $request->execute(array(
        'periode' => $periode
    ));
    header('Location: http://localhost/groupe_olivier/ajout_capteur.php');
?>