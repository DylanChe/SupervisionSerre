<?php
require_once('connect.php');

function addNewUnityType($bdd){
    $type_unite = $_POST['Nom_Unite'];
    $request = $bdd->prepare( 'INSERT INTO unite(unite) VALUE(:unite) ' );
    $request->execute(array(
        'unite' => $type_unite
    ));
}
?>