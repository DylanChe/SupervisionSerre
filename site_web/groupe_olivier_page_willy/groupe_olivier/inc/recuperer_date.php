<?php

require_once('connect.php');

function getFullDate($dbt)
{
    $request = $dbt->prepare( 'SELECT date_releve FROM releve WHERE id_materiel = 1 AND date_releve BETWEEN "18-03-31" AND "18-04-31" ORDER BY date_releve ASC ' );
    return $request->execute() ?  $request->fetchAll() : null;
}

$dates = getFullDate($bdd);

print json_encode($dates);
?>