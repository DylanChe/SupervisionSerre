<?php
	try 
	{
		$bdd = new PDO('mysql:host=localhost;dbname=supervision_serre;charset=utf8', 'projetbts', 'Nantes44');
		$reponse = $bdd->query('SELECT id, nom, etat FROM materiel WHERE id_type_materiel = 2');
		$output = $reponse->fetchAll(PDO::FETCH_ASSOC);

		echo(json_encode($output));

		$reponse->closeCursor();
	}
	catch (Exception $e)
	{
		die('Erreur : ' . $e->getMessage());
	}
	
?>
