<?php
require_once('connect.php');

    $nom_capteur = $_POST['Nom_Capteur'];
    $abreviation = $_POST['Abrev'];
    $id_type_materiel = $_POST['type_Materiel'];
    $id_unite = $_POST['unite'];

    $request = $bdd->prepare( 'INSERT INTO materiel(nom, abreviation, est_fonctionnel, id_type_materiel) VALUE(:nom, :abreviation, :est_fonctionnel, :id_type_materiel) ' );
    $request->execute(array(
        'nom' => $nom_capteur,
        'abreviation' => $abreviation,
        'est_fonctionnel' => "1",
        'id_type_materiel' => $id_type_materiel
    ));

    function getID($pou){
        $nom_capteur = $_POST['Nom_Capteur'];
        $information = $pou->prepare('SELECT id FROM materiel WHERE nom = "'.$nom_capteur.'"');
        return $information->execute() ? $information->fetchAll() : null;
    }
    $ID = getID($bdd);
    $req = $bdd->prepare( 'INSERT INTO materiel_unite(id, id_unite) VALUE(:id, :id_unite) ' );
    $req->execute(array(
        'id' => $ID[0]['id'],
        'id_unite' => $id_unite

    ));
?>

<script type="text/javascript">
    alert('Le capteur a bien été ajouté.');
    document.location.href = 'http://92.222.92.147/ajout_capteur.php';
</script>

