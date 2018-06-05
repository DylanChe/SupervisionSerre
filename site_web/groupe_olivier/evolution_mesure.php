<?php
session_start();
require_once('inc/connect.php');
ini_set("display_errors",0);error_reporting(0);
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
    <script type='text/javascript' src='http://code.jquery.com/jquery.min.js'></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
    <style type="text/css">.entry-content {font-family: Helvetica Neue; font-size:15px; font-weight: normal; color:#6B6B6B;}</style>
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
                            <a href="etat_serre.php">Etat de la serre</a>
                        </li>
                        <li  class="active">
                            <a href="evolution_mesure.php">Evolution des mesures</a>
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

    <section class="our-services">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aligncenter">
                        <div class="container">
                            <br>
                            <form method="get" name="dates" action="#">
                                <div class="home-widget-area row col-md-4 center-block">
                                    <div>
                                        <div class="textwidget">
                                            <a>
                                                <img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
                                                <br>
                                                <br>
                                                <p>Capteur :</p>
                                                <select name="capteur" class="form-control">
                                                <?php
                                                    $reponse = $bdd->query('SELECT id, nom FROM materiel');
                                                    while($nom_capteur = $reponse->fetch()){
                                                ?>
                                                    <option value="<?= $nom_capteur['id'];?>"><?= $nom_capteur['nom'];?></option>
                                                <?php
                                                    }
                                                ?>
                                                </select>
                                                <br>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="home-widget-area row col-md-4 center-block">
                                    <div>
                                        <div class="textwidget">
                                            <a>
                                                <img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
                                                <br>
                                                <br>
                                                <p>Début :</p>
                                                <input type="date" class="form-control" name="date_1" id="dateid_1" required maxlength="10" required>
                                                <br>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="home-widget-area row col-md-4 center-block">
                                    <div>
                                        <div class="textwidget">
                                            <a>
                                                <img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
                                                <br>
                                                <br>
                                                <p>Fin :</p>
                                                <input type="date" class="form-control" name="date_2" id="dateid_2" required maxlength="10" required>
                                                <br>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="home-widget-area row col-md-12 center-block">
                                    <div class="textwidget">
                                        <button type="submit" value="submit" id="submit" class="btn btn-primary" onclick="disabled('1')">Valider</button>
                                        <hr>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <?php
            $_SESSION['dateid_1'] = $_GET['date_1'];
            $_SESSION['dateid_2'] = $_GET['date_2'];
            $_SESSION['capteurid'] = $_GET['capteur'];
            ?>

            <div id="graph" style="height: 500% " class="home">
            </div>
            <script type="text/javascript">

                var capteurs = [];
                var date = [];
                var valeurs = [];
                var unite = [];

                function get_sensors() {
                    $.ajax({
                        url: 'inc/recuperer_donnees.php',
                        method: "GET",
                        async: false,
                        success: function(data) {
                            capteurs = JSON.parse(data);
                        },
                        error: function(data) {
                            alert("Echec");
                        }
                    });
                    return capteurs;

                }

                function get_unite() {
                    $.ajax({
                        url: 'inc/recuperer_unite.php',
                        method: "GET",
                        async: false,
                        success: function(data) {
                            unite = JSON.parse(data);
                        },
                        error: function(data) {
                            alert("Echec");
                        }
                    });
                    return unite;

                }

                function get_dates() {
                    $.ajax({
                        url: 'inc/recuperer_date.php',
                        method: "GET",
                        async: false,
                        dataType: "json",
                        success: function(data) {
                            if(data){
                                for(var i = 0; i < data.length; i++){
                                    date.push(data[i].date_releve);
                                }
                            }
                        },
                        error: function(data) {
                            alert("Echec");
                        }
                    });
                    return date;

                }

                function get_values() {
                    $.ajax({
                        url: 'inc/recuperer_valeurs.php',
                        method: "GET",
                        async: false,
                        dataType: "json",
                        success: function(data) {
                            if(data){
                                for(var f = 0; f < data.length; f++){
                                    valeurs.push(data[f].valeur);
                                }
                            }
                        },
                        error: function(data) {
                            alert("Echec");
                        }
                    });
                    return valeurs;

                }

                var capteurs = get_sensors();
                var date = get_dates();
                var valeurs = get_values();
                var unite = get_unite();

                var dom = document.getElementById("graph");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    title: {
                        text: unite
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: [capteurs[0].nom]
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: date
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {

                            type: 'line',
                            stack: '总量',
                            data: valeurs,
                            name: [capteurs[0].nom]
                        }
                    ]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            </script>

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
                        <a href="etat_serre.php"><h5 class="widgetheading">Etat de la serre</h5></a>
                    </div>
                </div>

                <div class="col-lg-2">
                    <div class="widget" style="text-align: center">
                        <a href="evolution_mesure.php"><h5 class="widgetheading">Evolution des mesures</h5></a>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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