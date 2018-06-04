<?php
require_once('connect.php');

function addNewMaterialType($bdd){
    $type_materiel = $_POST['Type_Materiel2'];
    $request = $bdd->prepare( 'INSERT INTO type_materiel(nom) VALUE(:nom) ' );
    $request->execute(array(
        'nom' => $type_materiel
    ));
}
?>

