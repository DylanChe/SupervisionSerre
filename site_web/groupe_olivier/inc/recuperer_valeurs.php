<?php
session_start();
require_once('connect.php');

function getFullOfValues($dba)
{
    $capteur = $_SESSION['capteurid'][0];
    $date_1 = $_SESSION['dateid_1'];
    $date_2 = $_SESSION['dateid_2'];
    $request = $dba->prepare('SELECT valeur FROM releve WHERE id_materiel = "'.$capteur.'" AND date_releve BETWEEN "'.$date_1.'" AND "'.$date_2.'"');

    return $request->execute() ? $request->fetchAll() : null;
}

$releves = getFullOfValues($bdd);

print json_encode($releves);
?>