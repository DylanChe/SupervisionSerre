<?php
try
{
    $bdd = new PDO('mysql:host=92.222.92.147;dbname=supervision_serre', 'projetbts', 'Nantes44');
    $bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(Exception $e)
{
    die('Erreur : '.$e->getMessage());
}
?>