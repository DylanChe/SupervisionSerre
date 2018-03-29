<?php
include('inc/connect.php');
?>

<!DOCTYPE html>
<html lang="fr-FR" prefix="og: http://ogp.me/ns#">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Groupe Olivier - Supervision</title>
    <link rel="profile" href="http://gmpg.org/xfn/11">
    <link rel="pingback" href="http://www.groupe-olivier.fr/xmlrpc.php">
    <link href='http://fonts.googleapis.com/css?family=Neuton:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Nothing+You+Could+Do' rel='stylesheet' type='text/css'>
    <!-- favicon -->


    <!--[if IE]><![endif]-->

    <!--[if lt IE 9]>
    <script src="http://www.groupe-olivier.fr/wp-content/themes/unite/inc/js/html5shiv.min.js"></script>
    <script src="http://www.groupe-olivier.fr/wp-content/themes/unite/inc/js/respond.min.js"></script>
    <![endif]-->

    <!-- This site is optimized with the Yoast WordPress SEO plugin v1.7.4 - https://yoast.com/wordpress/plugins/seo/ -->
    <link rel="canonical" href="" />
    <meta property="og:locale" content="fr_FR" />
    <meta property="og:type" content="website" />
    <meta property="og:title" content="Groupe Olivier - Groupe Olivier" />
    <meta property="og:description" content="Il s'agit du site Web comportant la supervision de l'ensemble des serres du Groupe Olivier. &nbsp;" />
    <meta property="og:url" content="" />
    <!-- / Yoast WordPress SEO plugin. -->

    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="alternate" type="application/rss+xml" title="Groupe Olivier &raquo; Flux" href="http://www.groupe-olivier.fr/feed/" />
    <link rel="alternate" type="application/rss+xml" title="Groupe Olivier &raquo; Flux des commentaires" href="http://www.groupe-olivier.fr/comments/feed/" />
    <link rel='stylesheet' id='contact-form-7-css'  href='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7/includes/css/styles.css?ver=4.0.1' type='text/css' media='all' />
    <link rel='stylesheet' id='jquery-ui-theme-css'  href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/redmond/jquery-ui.min.css?ver=1.10.3' type='text/css' media='all' />
    <link rel='stylesheet' id='jquery-ui-timepicker-css'  href='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7-datepicker/js/jquery-ui-timepicker/jquery-ui-timepicker-addon.min.css?ver=4.0.22' type='text/css' media='all' />
    <link rel='stylesheet' id='unite-bootstrap-css'  href='http://www.groupe-olivier.fr/wp-content/themes/unite/inc/css/bootstrap.min.css?ver=4.0.22' type='text/css' media='all' />
    <link rel='stylesheet' id='unite-icons-css'  href='http://www.groupe-olivier.fr/wp-content/themes/unite/inc/css/font-awesome.min.css?ver=4.0.22' type='text/css' media='all' />
    <link rel='stylesheet' id='unite-style-css'  href='http://www.groupe-olivier.fr/wp-content/themes/unite/style.css?ver=4.0.22' type='text/css' media='all' />
    <link rel='stylesheet' id='frs-css-css'  href='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/css/frs.css?ver=2.0.3' type='text/css' media='all' />
    <link rel='stylesheet' id='frs-position-css'  href='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/css/frs-position.css?ver=2.0.3' type='text/css' media='all' />
    <link rel='stylesheet' id='Open Sans-css'  href='http://fonts.googleapis.com/css?family=Open+Sans%3A400italic%2C700italic%2C400%2C700&#038;ver=2.0.3' type='text/css' media='all' />
    <!-- This site uses the Yoast Google Analytics plugin v5.2.8 - Universal enabled - https://yoast.com/wordpress/plugins/google-analytics/ -->
    <script type="text/javascript">
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','__gaTracker');

        __gaTracker('create', 'UA-58366683-1', 'auto');
        __gaTracker('set', 'forceSSL', true);
        __gaTracker('send','pageview');
    </script>
    <!-- JQuery for open the calendar/-->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#datepicker" ).datepicker({
                altField: "#datepicker",
                closeText: 'Fermer',
                prevText: 'Précédent',
                nextText: 'Suivant',
                currentText: 'Aujourd\'hui',
                monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
                monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
                dayNames: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
                dayNamesShort: ['Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.', 'Dim.'],
                dayNamesMin: ['L', 'M', 'M', 'J', 'V', 'S', 'D'],
                weekHeader: 'Sem.',
                dateFormat: 'dd-mm-yy'
            });
			$( "#datepicker2" ).datepicker({
                altField: "#datepicker2",
                closeText: 'Fermer',
                prevText: 'Précédent',
                nextText: 'Suivant',
                currentText: 'Aujourd\'hui',
                monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
                monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
                dayNames: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
                dayNamesShort: ['Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.', 'Dim.'],
                dayNamesMin: ['L', 'M', 'M', 'J', 'V', 'S', 'D'],
                weekHeader: 'Sem.',
                dateFormat: 'dd-mm-yy'
            });
        });
    </script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-includes/js/jquery/jquery.js?ver=1.11.1'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/js/frs.js?ver=2.0.3'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/js/jquery.touchSwipe.min.js?ver=4.0.22'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/js/imagesloaded.min.js?ver=2.0.3'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/themes/unite/inc/js/bootstrap.min.js?ver=4.0.22'></script>
    <script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/themes/unite/inc/js/main.min.js?ver=4.0.22'></script>
    <link rel="EditURI" type="application/rsd+xml" title="RSD" href="http://www.groupe-olivier.fr/xmlrpc.php?rsd" />
    <link rel="wlwmanifest" type="application/wlwmanifest+xml" href="http://www.groupe-olivier.fr/wp-includes/wlwmanifest.xml" />
    <meta name="generator" content="WordPress 4.0.22" />
    <style type="text/css">.entry-content {font-family: Helvetica Neue; font-size:15px; font-weight: normal; color:#6B6B6B;}</style>
</head>

<!-- ------------ -->
<!-- HEADER DEBUT -->
<!-- ------------ -->

<body class="home page page-id-2 page-template page-template-page-homepage-php custom-background">
<div id="page" class="hfeed site">
    <div id="head">
        <div class="container">
            <div class="row">
                <header id="masthead" class="site-header col-sm-12" role="banner">
                    <div class="row">
                        <div class="site-branding col-lg-3 col-md-2 col-sm-12">
                            <h1 class="hidden"><a href="index.php" rel="home">Groupe Olivier</a></h1>
                            <div id="logo" class="row">
                                <a href="index.php"><img src="http://www.groupe-olivier.fr/wp-content/uploads/2015/01/logo-groupe-olivier.jpg"  height="111" width="180" alt="Groupe Olivier"/></a>

                            </div><!-- Fin du logo. -->


                        </div><!-- Fermeture du branding. -->

                        <div class="nav-header col-md-8 col-lg-7 col-lg-offset-3 col-sm-12">
                            <nav class="navbar navbar-default row" role="navigation">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>

                                </div>

                                <div class="collapse navbar-collapse navbar-ex-collapse">
                                    <ul id="menu-menu-principale" class="nav navbar-nav">
                                        <li id="menu-item-2" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-41 dropdown">
                                            <a title="etat_serre" href="etat_serre.html"=>Etat de la serre</a>
                                        </li>
                                        <li id="menu-item-3" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-42 dropdown">
                                            <a title="evolution_mesure" href="evolution_mesure.html"=>Visualisation des mesures</a>
                                        </li>
                                    </ul></div>
                            </nav><!-- Fermeture de la navigation du site.  -->
                        </div><!-- Fermeture de nav-header. -->
                    </div>
                </header><!-- Fermeture du header. -->
            </div>
        </div>
    </div>

    <!-- ---------- -->
    <!-- HEADER FIN -->
    <!-- ---------- -->
    <!-- ----------------- -->
    <!-- TESTIMONIAL DEBUT -->
    <!-- ----------------- -->

    <div class="container">
        <br>
        <div class="home-widget-area row col-sm-6">
            <div>
                <div class="textwidget" id="calendarMain1" style="text-align: center">
                    <a>
                        <br>
                        <img style="border: 0 none;" src="inc/img/calendrier.png" alt="" width="150" height="150" />
                        <br>
                        <br>
                        <span><p>Début : <input type="text"  id="datepicker"></p></span>
                        <br>
                    </a>
                </div>
            </div>
        </div>
        <div class="home-widget-area row col-sm-6">
            <div>
                <div class="textwidget" id="calendarMain2" style="text-align: center">
                    <a>
                        <br>
                        <img style="border: 0 none;" src="inc/img/calendrier.png" alt="" width="150" height="150" />
                        <br>
                        <br>
                        <span><p>Fin : <input type="text" id="datepicker2"></p></span>
                        <br>
                    </a>
                </div>
            </div>
        </div>
        <div class="textwidget" style="text-align: center">
            <button type="button" class="btn btn-primary">Valider</button>
            <hr>
        </div>

        <!-- -----------------------------------------------------------------------------------------------------------
        ---------------------------------------ECHO-LE-JS-POUR-FONCTIONNER----------------------------------------------
        ------------------------------------------------------------------------------------------------------------ -->

        <?php
        $resultats=$bdd->query("SELECT * FROM type_materiel");
        while( $resultat = $resultats->fetch() )
        {
        $tab[] =  $resultat['nom']; // On récupère les données sous forme de tableau, pour récupérer les valeurs 1à1.
        }
        $nom1 = $tab[0];
        echo $nom1;

        echo'<div id="graph" style="height: 500%" class="home">
        </div>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
            <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
            <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
            <script type="text/javascript">
                var dom = document.getElementById("graph");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    tooltip: {
                        trigger: \'axis\'
                    },
                    legend: {
                        data:[("$nom1"),\'Temperature_int\',\'Intensité_soleil\',\'Vitesse_vent\',\'Pluviometrie\']
                    },
                    grid: {
                        left: \'3%\',
                        right: \'4%\',
                        bottom: \'3%\',
                        containLabel: true
                    },
                    xAxis: {
                        type: \'category\',
                        boundaryGap: false,
                        data: [\'19/02/2018\',\'20/02/2018\',\'21/02/2018\',\'22/02/2018\',\'23/02/2018\',\'24/02/2018\',\'25/02/2018\',\'26/02/2018\',\'27/02/2018\',\'28/02/2018\',\'29/02/2018\',\'30/02/2018\']
                    },
                    yAxis: {
                        type: \'value\'
                    },
                    series: [
                        {
                            name:("$nom1"),
                            type:\'line\',
                            data:[20, 21, 20, 24, 21, 22, 23, 10, 12, 15, 45, 74]
                        },
                        {
                            name:\'Temperature_int\',
                            type:\'line\',
                            data:[16, 17, 16, 15, 14, 14, 12, 1, 45, 12, 42, 15]
                        },
                        {
                            name:\'Intensité_soleil\',
                            type:\'line\',
                            data:[150, 232, 201, 154, 190, 330, 410, 125, 451, 745, 625, 452]
                        },
                        {
                            name:\'Vitesse_vent\',
                            type:\'line\',
                            data:[40, 50, 51, 62, 71, 78, 105, 74, 54, 12, 12, 45]
                        },
                        {
                            name:\'Pluviometrie\',
                            type:\'line\',
                            data:[200, 150, 120, 145, 175, 200, 154, 145, 165, 184, 200, 121]
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            </script>
    </div>';
        $resultats->closeCursor();
        ?>

    <!-- --------------- -->
    <!-- TESTIMONIAL FIN -->
    <!-- --------------- -->

    <footer id="colophon" class="site-footer" role="contentinfo">
        <div class="site-info container">
            <div class="row">
                <div class="adresse col-md-10">
                    <div id="text-8" class="widget widget_text">
                        <div class="textwidget"><p>Groupe Olivier, la Bonodière, 44115 Haute-Goulaine </p>
                        </div>
                    </div>
                </div>
                <nav role="navigation" class="col-md-2">
                    <ul id="menu-pied-de-page" class="nav footer-nav clearfix">
                        <li id="menu-item-145" class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-2 current_page_item menu-item-145">
                            <a href="index.html">Accueil</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div><!-- .site-info -->
    </footer><!-- #colophon -->
    <div id="bottom-footer">
        <div class="site-info container">
            <div class="row">
                <div class="copyright col-md-12">
                    © copyright
                </div>
            </div>
        </div>
    </div>
</div><!-- #page -->

<link rel='stylesheet' id='frs-skin-default-css'  href='http://www.groupe-olivier.fr/wp-content/plugins/fluid-responsive-slideshow/skins/frs-skin-default.css?ver=2.0.3' type='text/css' media='all' />
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7/includes/js/jquery.form.min.js?ver=3.51.0-2014.06.20'></script>
<script type='text/javascript'>
    /* <![CDATA[ */
    var _wpcf7 = {"loaderUrl":"http:\/\/www.groupe-olivier.fr\/wp-content\/plugins\/contact-form-7\/images\/ajax-loader.gif","sending":"Envoi en cours ..."};
    /* ]]> */
</script>
<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/i18n/jquery.ui.datepicker-fr.min.js?ver=1.10.3'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7-datepicker/js/jquery-ui-timepicker/jquery-ui-timepicker-addon.min.js?ver=4.0.22'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7-datepicker/js/jquery-ui-timepicker/i18n/jquery-ui-timepicker-fr.js?ver=4.0.22'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-includes/js/jquery/ui/jquery.ui.widget.min.js?ver=1.10.4'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-includes/js/jquery/ui/jquery.ui.mouse.min.js?ver=1.10.4'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-includes/js/jquery/ui/jquery.ui.button.min.js?ver=1.10.4'></script>
<script type='text/javascript' src='http://www.groupe-olivier.fr/wp-content/plugins/contact-form-7-datepicker/js/jquery-ui-sliderAccess.js?ver=4.0.22'></script>
</body>
</html>