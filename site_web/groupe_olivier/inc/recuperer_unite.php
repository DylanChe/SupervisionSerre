<?php
session_start();
require_once('connect.php');

$capteur = $_SESSION['capteurid'][0];

function getUnityID($dba)
{
    global $capteur;
    $request = $dba->prepare('SELECT id_unite FROM materiel_unite WHERE id = "'.$capteur.'"');
    return $request->execute() ? $request->fetchAll() : null;
}

$id_unite = getUnityID($bdd);

function getUnity($gha)
{
    global $id_unite;
    $request = $gha->prepare('SELECT unite FROM unite WHERE id = "'.$id_unite[0]['id_unite'].'"');
    return $request->execute() ? $request->fetchAll() : null;
}

$unite = getUnity($bdd);

print json_encode($unite[0][0]);
?>

