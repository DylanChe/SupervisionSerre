<?php
session_start();
require_once('connect.php');


function getFullDate($dbt)
{
    $capteur = $_SESSION['capteurid'][0];
    $date_1 = $_SESSION['dateid_1'];
    $date_2 = $_SESSION['dateid_2'];
    $request = $dbt->prepare( 'SELECT date_releve FROM releve WHERE id_materiel = "'.$capteur.'" AND date_releve BETWEEN "'.$date_1.'" AND "'.$date_2.'" ORDER BY date_releve ASC ' );
    return $request->execute() ?  $request->fetchAll() : null;
}

$dates = getFullDate($bdd);

print json_encode($dates);
?>