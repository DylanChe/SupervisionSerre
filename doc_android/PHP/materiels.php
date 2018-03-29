<?php
	try 
	{
		$bdd = new PDO('mysql:host=localhost;dbname=supervision_serre;charset=utf8', 'projetbts', 'Nantes44');
	}
	catch (Exception $e)
	{
		die('Erreur : ' . $e->getMessage());
	}
	$reponse = $bdd->query('SELECT * FROM materiel');

	while ($donnees = $reponse->fetch())
	{
		echo $donnees['nom'];
	}

	$reponse->closeCursor();
?>
