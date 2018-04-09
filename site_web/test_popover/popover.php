<?php
include('connect.php');
?>
<!DOCTYPE html>

<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<?php
$releve=$bdd->query("SELECT * FROM `releve` WHERE id_materiel=1 ORDER BY `releve`.`date_releve` DESC LIMIT 1");
        
		$releves = $releve->fetch();
		print_r $releves ;
		/*while( $materiel = $materiels->fetch() )
        {
        $tab[] =  $materiel['nom']; // On récupère les données sous forme de tableau, pour récupérer les valeurs 1à1.
        }
        $taille_tab = count($tab); // On compte le nombre de valeurs dans le tableau.
        $nom = "nom";
        for ($i = 0; $i <= $taille_tab-1; $i++){
            // à completer pour que ça puisse le faire en fonction du nombre de
            //valeur dans la BDD.
        }
        $nom1 = $tab[0]; // On récupère le nom du capteur 1.
        $nom2 = $tab[1]; // On récupère le nom du capteur 2.
        $nom3 = $tab[2]; // On récupère le nom du capteur 3.
		echo $nom1; 
		echo $nom2;
		echo $nom3; */
?>

<div class="container">
  <h3>Popover Example</h3>
  <p>Popovers are not CSS-only plugins, and must therefore be initialized with jQuery: select the specified element and call the popover() method.</p>
  <a href="#" data-toggle="popover" title="Popover Header" data-content="Some content inside the popover">Toggle popover</a>
</div>

<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});
</script>

</body>










