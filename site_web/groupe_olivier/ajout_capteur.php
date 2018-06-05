<?php
    require_once('inc/connect.php');
?>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Groupe Oliver - Supervision</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <!-- css -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="css/flexslider.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>

<body>
<div class="home-page" id="wrapper">




    <!-- HEADER -->

    <header>
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type= "button">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html"><img alt="logo" src="img/logo.png"></a>
                </div>

                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="index.php">Accueil</a>
                        </li>
                        <li>
                            <a href="etat_serre.php">État de la serre</a>
                        </li>
                        <li>
                            <a href="evolution_mesure.php">Évolution des mesures</a>
                        </li>
                        <li class="active">
                            <a href="ajout_capteur.php">Ajouter un capteur</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>

    <!-- FIN HEADER -->

	<section class="our-services">
		<div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <form action="inc/ajouter_type_materiel.php" method="post">
                        <div class="panel-group col-sm-3">
                            <div class="panel panel-primary">
                                <div class="panel-heading" style="text-align: center">
                                    <p>Ajouter un type de matériel</p>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Materiel_Actuel">Type de matériel présent :</label>
                                    <textarea type="text" class="form-control" name="Materiel_Actuel" id="input_Materiel_Actuel" placeholder="<?php
                                    $reponse = $bdd->query('SELECT nom FROM type_materiel');
                                    while($type_materiel = $reponse->fetch()){
                                        echo($type_materiel['nom']);
                                        echo ", ";
                                    } ?>" disabled></textarea>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Type_Materiel2">Type du materiel :</label>
                                    <input type="text" class="form-control" name="Type_Materiel2" id="input_Type_Materiel2" placeholder="Microcontrôleur" required>
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Ajouter le capteur</button>
                                <br>
                            </div>
                        </div>
                    </form>
                    <form method="post" action="inc/ajouter_materiel.php">
                        <div class="panel-group col-sm-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading" style="text-align: center">
                                    <p>Ajouter un materiel</p>
                                </div>
                                <div class="panel-body">
                                    <label for="input_material_Now">Matériels présent :</label>
                                    <textarea type="text" class="form-control" name="material_Now" id="input_material_Now" placeholder="<?php
                                    $reponse = $bdd->query('SELECT nom FROM materiel');
                                    while($materiel_present = $reponse->fetch()){
                                        echo($materiel_present['nom']);
                                        echo ", ";
                                    } ?>" disabled></textarea>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Nom_Capteur">Nom du materiel :</label>
                                    <input type="text" class="form-control" name="Nom_Capteur" id="input_Nom_Capteur" placeholder="Solarimètre Ref55" required>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Abrev">Abréviation :</label>
                                    <input type="text" class="form-control" name="Abrev" id="input_Abrev" placeholder="Solar" required>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Type_Materiel">Type de matériel :</label>
                                            <select name="type_Materiel" class="form-control" id="type_Materiel">
                                                <?php
                                                $reponse = $bdd->query('SELECT id, nom FROM type_materiel');
                                                while($type_materiel_2 = $reponse->fetch()){
                                                    ?>
                                                    <option value="<?= $type_materiel_2['id'];?>"><?= $type_materiel_2['nom'];?></option>
                                                    <?php
                                                }
                                                ?>
                                            </select>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Type_Capteur">Unité du matériel :</label>
                                        <select name="unite" class="form-control" id="unite_Materiel">
                                            <?php
                                            $reponse = $bdd->query('SELECT id, unite FROM unite');
                                            while($type_unite = $reponse->fetch()){
                                                ?>
                                                <option value="<?= $type_unite['id'];?>"><?= $type_unite['unite'];?></option>
                                                <?php
                                            }
                                            ?>
                                        </select>
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Ajouter le capteur</button>
                                <br>
                            </div>
                        </div>
                    </form>

                    <form action="inc/ajouter_unite.php" method="post">
                        <div class="panel-group col-sm-3">
                            <div class="panel panel-primary">
                                <div class="panel-heading" style="text-align: center">
                                    <p>Ajouter une unité</p>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Unite_Actuel">Unité présente :</label>
                                    <textarea type="text" class="form-control" name="Unite_Actuel" id="input_Unite_Actuel" placeholder="<?php
                                    $reponse = $bdd->query('SELECT unite FROM unite');
                                    while($unite_presente = $reponse->fetch()){
                                        echo($unite_presente['unite']);
                                        echo ", ";
                                    } ?>" disabled></textarea>
                                </div>
                                <div class="panel-body">
                                    <label for="input_nom_Unite">Unité à ajouter :</label>
                                    <input type="text" name="nom_Unite" class="form-control" id="input_nom_Unite" placeholder="Kg">
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Ajouter l'unité</button>
                                <br>
                            </div>
                        </div>
                    </form>
                    <form action="inc/maj_periode.php" method="post">
                        <div class="panel-group col-sm-3">
                            <div class="panel panel-primary">
                                <div class="panel-heading" style="text-align: center">
                                    <p>Définir une période</p>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Periode_Actuelle">La période actuelle est :</label>
                                    <input type="text" class="form-control" id="input_Periode_Actuelle" placeholder="<?php
                                    $reponse = $bdd->query('SELECT periode FROM parametre');
                                    while($parametre_defini = $reponse->fetch()){
                                        echo($parametre_defini['periode']);
                                    } ?>" disabled>
                                </div>
                                <div class="panel-body">
                                    <label for="input_Periode">Définir la période (en sec) :</label>
                                    <input type="number" name="periode" class="form-control" id="input_Periode" placeholder="7200">
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Changer la période</button>
                                <br>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
		</div>
	</section>
	
	<!-- FOOTER -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="widget">
                        <h5 class="widgetheading">Groupe Olivier, la Bonodière, 44115 Haute-Goulaine</h5>
                    </div>
                </div>

                <div class="col-lg-2">
                </div>

                <div class="col-lg-2">
                    <div class="widget" style="text-align: center">
                        <a href="etat_serre.php"><h5 class="widgetheading">État de la serre</h5></a>
                    </div>
                </div>

                <div class="col-lg-2">
                    <div class="widget" style="text-align: center">
                        <a href="evolution_mesure.php"><h5 class="widgetheading">Évolution des mesures</h5></a>
                    </div>
                </div>

                <div class="col-lg-2">
                    <div class="widget" style="text-align: center">
                        <a href="ajout_capteur.php"><h5 class="widgetheading">Ajouter un capteur</h5></a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p style="text-align: center">©Copyright 2018 - Conception et réalisation par RINEAU Willy et GERARD Samuel</p>
                </div>
            </div>
        </div>


    </footer>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.fancybox.pack.js"></script>
<script src="js/jquery.fancybox-media.js"></script>
<script src="js/portfolio/jquery.quicksand.js"></script>
<script src="js/portfolio/setting.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script src="js/animate.js"></script>
<script src="js/custom.js"></script>
</body>
</html>