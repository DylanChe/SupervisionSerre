<?php
require_once('connect.php');
    $type_materiel = $_POST['Type_Materiel2'];
    $request = $bdd->prepare( 'INSERT INTO type_materiel(nom) VALUE(:nom) ' );
    $request->execute(array(
        'nom' => $type_materiel
    ));
    header('Location: http://localhost/groupe_olivier/ajout_capteur.php');
?>

