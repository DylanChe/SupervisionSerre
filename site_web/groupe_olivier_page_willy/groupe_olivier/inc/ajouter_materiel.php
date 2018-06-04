<?php
require_once('connect.php');

function addNewMaterial($bdd){
    $nom_capteur = $_POST['Nom_Capteur'];
    $abreviation = $_POST['Abrev'];
    $type_materiel = $_POST['Type_Materiel'];
    $id_unite = $_POST['capteur'];

    $request = $bdd->prepare( 'INSERT INTO materiel(nom, abreviation, est_fonctionnel, id_type_materiel) VALUE(:nom, :abreviation, :est_fonctionnel, :id_type_materiel) ' );
    $request->execute(array(
        'nom' => $nom_capteur,
        'abreviation' => $abreviation,
        'est_fonctionnel' => "0",
        'id_type_materiel' => $type_materiel

    ));

    $request = $bdd->prepare( 'INSERT INTO materiel_unite(id, id_unite) VALUE(:id, :id_unite) ' );
    $request->execute(array(
        'id' => "à faire",
        'id_unite' => $id_unite

    ));
}

