<?php
include('inc/connect.php');
?>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Groupe Oliver - Supervision</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="refresh" content="10; url=etat_serre.php">
    <!-- css -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="css/jcarousel.css" rel="stylesheet">
    <link href="css/flexslider.css" rel="stylesheet">
    <link href="js/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="css/style1.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>

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
                        <li class="active">
                            <a href="etat_serre.php">État de la serre</a>
                        </li>
                        <li>
                            <a href="evolution_mesure.php">Évolution des mesures</a>
                        </li>
                        <li>
                            <a href="ajout_capteur.php">Ajouter un capteur</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>

    <!-- FIN HEADER -->

    <body>

    <?php
    $getreleves=[];
    //Récupération des données propres à chaques capteurs dan la base de donnée
    function getreleve($dbc) {
        $request = $dbc->prepare ("SELECT nom, CONCAT(valeur, unite) FROM releve, materiel, unite WHERE date_releve IN ( SELECT MAX(date_releve) FROM releve GROUP BY id_materiel ) AND releve.id_materiel = materiel.id AND releve.id_unite = unite.id GROUP BY id_materiel");

        return $request->execute() ? $request->fetchAll() : null;
    }

    $getreleves = getreleve($bdd);
    ?>

    <section class="our-services" >
        <div class="container"  >
            <div class="row" >
                <div class="col-lg-8">
                    <img  src='img/serre.png' style="height: 106% ; width: 106%">
                </div>
                <div class="col-lg-4 center-block" >
                    <table>
                        <tr>
                            <th> Capteur </th>
                            <th> Valeur </th>
                        </tr>
                        <tr>
                            <td>
                                <?php
                                foreach($getreleves as $releve)
                                {
                                    echo '<p>'.$releve[0].'</p>';
                                }
                                ?>
                            </td>
                            <td>
                                <?php
                                foreach($getreleves as $releve)
                                {
                                    echo '<p>'.$releve[1].'</p>';
                                }
                                ?>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </section>


    </body>
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

    <!-- FIN FOOTER -->
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
<script src="js/owl-carousel/owl.carousel.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>