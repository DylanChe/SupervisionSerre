<?php
require_once('connect.php');

    $nom_capteur = $_POST['Nom_Capteur'];
    $abreviation = $_POST['Abrev'];
    $id_type_materiel = $_POST['type_Materiel'];
    $id_unite = $_POST['unite'];

    $request = $bdd->prepare( 'INSERT INTO materiel(nom, abreviation, est_fonctionnel, id_type_materiel) VALUE(:nom, :abreviation, :est_fonctionnel, :id_type_materiel) ' );
    $request->execute(array(
        'nom' => $nom_capteur,
        'abreviation' => $abreviation,
        'est_fonctionnel' => "1",
        'id_type_materiel' => $id_type_materiel

    ));

    //$request = $bdd->prepare( 'INSERT INTO materiel_unite(id, id_unite) VALUE(:id, :id_unite) ' );
    //$request->execute(array(
    //    'id' => "à faire",
    //    'id_unite' => $id_unite
    //
    //));
    header('Location: http://localhost/groupe_olivier/ajout_capteur.php');

