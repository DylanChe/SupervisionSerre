<?php
include('connect.php');
?>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="10; url=test.php">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/popover.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<?php
//Récupération des données propres à chaques capteurs dan la base de donnée
/*
$pluviometres=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=1 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
		$pluviometre = $pluviometres->fetch();   
        $tab[] =  $pluviometre['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.

$anemometres_v=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=2 AND id_unite=3 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
		$anemometre_v = $anemometres_v->fetch();   
        $tab[] =  $anemometre_v['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.        

$anemometres_d=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=2 AND id_unite=2 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
        $anemometre_d = $anemometres_d->fetch();
        $tab[] =  $anemometre_d['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.

$solarimetres=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=3 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
        $solarimetre = $solarimetres->fetch();
        $tab[] =  $solarimetre['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.

$temp_tuyau=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=4 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
        $temperature_tuyau = $temp_tuyau->fetch();
        $tab[] =  $temperature_tuyau['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.

$temp_serre=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=5 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
        $temperature_serre = $temp_serre->fetch();
        $tab[] =  $temperature_serre['valeur']; // On récupère les données sous forme de tableau, pour récupérer les valeurs.
*/
function getFruit($dbc) {
    $request = $dbc->prepare ("SELECT valeur, id_unite, date_releve, id_materiel FROM releve");
    return $request->execute() ? $request->fetchAll() : null;
}

$getfruit = getFruit($bdd);
var_dump($getfruit);

?>

        
        <!--
        <div>
            <div id="zone-serre">
                <!--<img class="wid wid-pluviometre" src="inc/img/pluviometre" alt="Pluviométrie" title="Pluviométrie" />-->
            <!--</div>

        <!--</div>-->

        <div>

            <img src="img/pluviometre" alt="Pluviométrie" title="Pluviométrie" /> </a>

            <img class="wid wid-vitesse_vent" src="img/vitesse_vent" alt="Vitesse du vent" title="Vitesse du vent" />

            <img src="img/direction_vent" alt="Direction du vent" title="Direction du vent" /> </a>

            <img src="img/solarimetre" alt="intensité lumineuse" title="Intensité lumineuse" /> </a>

            <img src="img/temperature_tuyau.png" alt="Température de l'eau des tuyaux" title="Température de l'eau des tuyaux" /> </a>

            <img src="img/temperature.png" alt="Température sous serre" title="Température sous serre" /> </a>

        </div>

</body>










