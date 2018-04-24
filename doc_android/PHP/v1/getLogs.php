<?php
	try 
	{
		$bdd = new PDO('mysql:host=localhost;dbname=supervision_serre;charset=utf8', 'projetbts', 'Nantes44');
		$reponse = $bdd->query('SELECT journal.id, journal.est_fonctionnel, journal.date_panne, materiel.nom FROM journal, materiel WHERE journal.id_materiel = materiel.id ');
		$output = $reponse->fetchAll(PDO::FETCH_ASSOC);

		echo(json_encode($output));

		$reponse->closeCursor();
	}
	catch (Exception $e)
	{
		die('Erreur : ' . $e->getMessage());
	}
	
?>
