<?php

require_once('connect.php');

function getFullOfValues($dba)
{
    $request = $dba->prepare('SELECT valeur FROM releve WHERE id_materiel = 1 AND date_releve BETWEEN "18-03-31" AND "18-04-31"');
    return $request->execute() ? $request->fetchAll() : null;
}

$releves = getFullOfValues($bdd);

print json_encode($releves);
?>