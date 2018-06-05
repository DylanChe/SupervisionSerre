<?php
session_start();
require_once('connect.php');

function getTheID($dba)
{
    $capteur = $_SESSION['capteurid'][0];
    $request = $dba->prepare('SELECT id FROM materiel WHERE nom = "'.$capteur.'"');
    return $request->execute() ? $request->fetchAll() : null;
}

$id_materiel = getTheUnity($bdd);

function getTheUnity($qsz)
{
    $request = $qsz->prepare('SELECT unite FROM unite 
INNER JOIN materiel_unite ON unite.id = materiel_unite.id_unite -- ID de l unité -- Materiel unité
INNER JOIN materiel_unite ON materiel_unite.id_unite -- Materiel ID unité -- Materiel ID
INNER JOIN materiel ON -- Materiel ID -- 
');
}