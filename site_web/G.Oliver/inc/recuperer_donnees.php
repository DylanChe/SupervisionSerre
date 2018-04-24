<?php

require_once('connect.php');



function getFullListOfSensor($dbh)
{
    $request = $dbh->prepare( 'SELECT nom FROM materiel' );
    return $request->execute() ?  $request->fetchAll() : null;
}

function getFullListOfIDSensor($dbh)
{
    $request = $dbh->prepare( 'SELECT id FROM materiel' );
    return $request->execute() ?  $request->fetchAll() : null;
}

function getFullOfValues1($dba)
{
    $request = $dba->prepare('SELECT valeur FROM releve WHERE id_materiel = 1');
    return $request->execute() ? $request->fetchAll() : null;
}
function getFullOfValues2($dba)
{
    $request = $dba->prepare('SELECT valeur FROM releve WHERE id_materiel = 2');
    return $request->execute() ? $request->fetchAll() : null;
}

//function getGapOfDate($dbl)
//{
    //$request = $dbl->prepare('SELECT date_releve FROM releve WHERE date_releve BETWEEN $date_debut AND $date_fin');
    //return $request->execute() ? $request->fetchAll() : null;
//}

$sensors = getFullListOfSensor($bdd);
var_dump($sensors);

$IDsensors = getFullListOfIDSensor($bdd);
var_dump($IDsensors);

$releves1 = getFullOfValues1($bdd);
var_dump($releves1);
$releves2 = getFullOfValues2($bdd);
var_dump($releves2);

//$dates = getGapOfDate($bdd);
//var_dump($dates);

// ----- DATA ------
$legend = [
    'data' => [$sensors]
];

$series = [
    'name' => [$sensors[$i]],
    'type' => ['line'],
    'data' => [$releves[$i]]
];

$xAxis = [
    'type' => ['category'],
    'boundaryGap' => [false],
    'data' => [$dates]
];
echo json_encode($legend);
echo json_encode($series);
echo json_encode($xAxis);


?>