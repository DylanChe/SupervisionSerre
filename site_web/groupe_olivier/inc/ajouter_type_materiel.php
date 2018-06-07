<?php
require_once('connect.php');
    $type_materiel = $_POST['Type_Materiel2'];
    $request = $bdd->prepare( 'INSERT INTO type_materiel(nom) VALUE(:nom) ' );
    $request->execute(array(
        'nom' => $type_materiel
    ));
?>

<script type="text/javascript">
    alert('Le type de capteur a bien été ajouté.');
    document.location.href = 'http://92.222.92.147/ajout_capteur.php';
</script>
