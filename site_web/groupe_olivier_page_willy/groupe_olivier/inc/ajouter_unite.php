<?php
require_once('connect.php');
    $type_unite = $_POST['nom_Unite'];
    $request = $bdd->prepare( 'INSERT INTO unite(unite) VALUE(:unite) ' );
    $request->execute(array(
        'unite' => $type_unite
    ));
    header('Location: http://localhost/groupe_olivier/ajout_capteur.php');
?>