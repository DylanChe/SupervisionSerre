<?php


require_once('connect.php');



function getFullListOfSensor($dbh)
{
    $request = $dbh->prepare( 'SELECT nom FROM materiel WHERE id = 1' );
    return $request->execute() ?  $request->fetchAll() : null;
}

$sensors = getFullListOfSensor($bdd);

print json_encode($sensors);
?>