<?php
session_start();
require_once('connect.php');



function getFullListOfSensor($dbh)
{
    $capteur = $_SESSION['capteurid'][0];
    $request = $dbh->prepare( 'SELECT nom FROM materiel WHERE id = "'.$capteur.'"' );
    return $request->execute() ?  $request->fetchAll() : null;
}

$sensors = getFullListOfSensor($bdd);

print json_encode($sensors);
?>