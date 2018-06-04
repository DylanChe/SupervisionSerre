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
								<div class="home-widget-area row col-sm-6">
									<div>
										<div class="textwidget" style="text-align: center">
											<a>
												<img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
												<br>
												<br>
												<p>Début : <input type="date" name="date_1" id="dateid_1" required maxlength="10" style="text-align: center" required onclick="verif_date()"></p>
												<br>
											</a>
										</div>
									</div>
								</div>
								<div class="home-widget-area row col-sm-6">
									<div>
										<div class="textwidget" style="text-align: center">
											<a>
												<img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
												<br>
												<br>
												<span><p>Fin : <input type="date" name="date_2" id="dateid_2" required maxlength="10" style="text-align: center" required></p></span>
												<br>
											</a>
										</div>
									</div>
								</div>
								<div class="textwidget" style="text-align: center">
									<button type="submit" value="submit" id="submit" class="btn btn-primary" onclick="disabled('1')">Valider</button>
									<hr>
								</div>
							</form>
                    </div>
                </div>
            </div>
        </div>


           <!-- <script type="text/javascript">
                function verif_date()
                {
                    var date_debut = new Date(document.forms[0]["dates"]["dateid_1"]) ;
                    var date_fin = new Date(document.forms[0]["dates"]["dateid_2"]) ;
                    console.log(date_debut);
                    if ( date_debut > date_fin )
                    {
                        window.alert("Les dates sont éronnées.");
                        document.forms.dates.submit.disabled = true;
                    }
                    else
                    {
                        document.forms.dates.submit.disabled = false;
                    }
                }
            </script>-->

            <script type="text/javascript">
                $('submit').click(function(){
                    alert("test");
                    $(this).attr("disabled","disabled");
                });
            </script>

            <div id="graph" style="height: 500% " class="home">
        </div>
            <!-- <script type="text/javascript" src="inc/recuperer_donnees.js"></script> -->
        <script type="text/javascript">

                var capteurs = [];
                var date = [];
                var valeurs = [];

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

                function get_dates() {
                    $.ajax({
                        url: 'inc/recuperer_date.php',
                        method: "GET",
                        async: false,
                        success: function(data) {
                            date = JSON.parse(data);
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
                        success: function(data) {
                            valeurs = JSON.parse(data);
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

                console.log(valeurs);
				var dom = document.getElementById("graph");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
						title: {
							text: 'C1TEST'
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
                            data: [date[0].date_releve, date[1].date_releve, date[2].date_releve, date[3].date_releve, date[4].date_releve, date[5].date_releve, date[6].date_releve, date[7].date_releve]
                        },
						yAxis: {
							type: 'value'
						},
						series: [
                            {

                                type: 'line',
                                stack: '总量',
                                data: [valeurs[0].valeur, valeurs[1].valeur, valeurs[2].valeur, valeurs[3].valeur, valeurs[4].valeur, valeurs[5].valeur, valeurs[6].valeur, valeurs[7].valeur],
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