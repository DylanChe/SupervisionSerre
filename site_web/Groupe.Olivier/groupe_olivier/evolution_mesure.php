<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Groupe Oliver - Supervision</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <!-- css -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="css/jcarousel.css" rel="stylesheet">
    <link href="css/flexslider.css" rel="stylesheet">
    <link href="js/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
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
								<form method="get">
								<div class="home-widget-area row col-sm-6">
									<div>
										<div class="textwidget" style="text-align: center">
											<a>
												<img style="border: 0 none;" src="img/calendrier.png" alt="" width="150" height="150" />
												<br>
												<br>
												<p>Début : <input type="date" name="date_1" required maxlength="10" style="text-align: center"></p>
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
												<span><p>Fin : <input type="date" name="date_2" required maxlength="10" style="text-align: center"></p></span>
												<br>
											</a>
										</div>
									</div>
								</div>
								<div class="textwidget" style="text-align: center">
									<button type="submit" value="submit" class="btn btn-primary">Valider</button>
									<hr>
								</div>
							</form>
                    </div>
                </div>
            </div>
        </div>
    
	
	    <div id="graph" style="height: 500% " class="home">
        </div>

        <script type="text/javascript">
				var dom = document.getElementById("graph");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
						title: {
							text: 'Graphique'
						},
						tooltip: {
							trigger: 'axis'
						},
						legend: {
							data:['Solarimetre','Pluviometre','Temperature de l\'air','Temperature de l\'eau','Vitesse du vent']
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
							data: ['Date','Date','Date','Date','Date','Date','Date']
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								name:'Solarimetre',
								type:'line',
								stack: '总量',
								data:[120, 132, 101, 134, 90, 230, 210]
							},
							{
								name:'Pluviometre',
								type:'line',
								stack: '总量',
								data:[220, 182, 191, 234, 290, 330, 310]
							},
							{
								name:'Temperature de l\'air',
								type:'line',
								stack: '总量',
								data:[150, 232, 201, 154, 190, 330, 410]
							},
							{
								name:'Temperature de l\'eau',
								type:'line',
								stack: '总量',
								data:[320, 332, 301, 334, 390, 330, 320]
							},
							{
								name:'Vitesse du vent',
								type:'line',
								stack: '总量',
								data:[820, 932, 901, 934, 1290, 1330, 1320]
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
</body>
</html>