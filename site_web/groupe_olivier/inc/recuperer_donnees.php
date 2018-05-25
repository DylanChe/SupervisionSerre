<?php

require_once('connect.php');



function getFullListOfSensor($dbh)
{
    $request = $dbh->prepare( 'SELECT nom FROM materiel WHERE id = 1' );
    return $request->execute() ?  $request->fetchAll() : null;
}

function getFullDate($dbt)
{
    $request = $dbt->prepare( 'SELECT date_releve FROM releve WHERE id_materiel = 1 AND date_releve BETWEEN "18-03-31" AND "18-04-31" ORDER BY date_releve ASC 
' );
    return $request->execute() ?  $request->fetchAll() : null;
}

function getFullOfValues($dba)
{
    $request = $dba->prepare('SELECT valeur FROM releve WHERE id_materiel = 1 AND date_releve BETWEEN "18-03-31" AND "18-04-31"');
    return $request->execute() ? $request->fetchAll() : null;
}
//function getFullOfValues2($dba)
//{
//    $request = $dba->prepare('SELECT valeur,date_releve,id_unite FROM releve WHERE id_materiel = 2 AND date_releve BETWEEN "18-03-31" AND "18-04-31"');
//    return $request->execute() ? $request->fetchAll() : null;
//}

//function getGapOfDate($dbl)
//{
//$request = $dbl->prepare('SELECT date_releve FROM releve WHERE date_releve BETWEEN $date_debut AND $date_fin');
//return $request->execute() ? $request->fetchAll() : null;
//}

$sensors = getFullListOfSensor($bdd);

$dates = getFullDate($bdd);

$releves = getFullOfValues($bdd);


//$dates = getGapOfDate($bdd);
//var_dump($dates);

// ----- DATA ------
$legend = [
    'data' => [$sensors]
];

$series = [
    'name' => [$sensors],
    'type' => ['line'],
    'data' => [$releves]
];

$xAxis = [
    'type' => ['category'],
    'boundaryGap' => [false],
    'data' => [$dates]
];
print json_encode($legend);
print json_encode($series);
print json_encode($xAxis);
print "https://hastebin.com/uzexanepag.json";
?>