<?php
require_once('connect.php');
    $type_unite = $_POST['nom_Unite'];
    $request = $bdd->prepare( 'INSERT INTO unite(unite) VALUE(:unite) ' );
    $request->execute(array(
        'unite' => $type_unite
    ));
?>

<script type="text/javascript">
    alert("L'unité a bien été ajoutée.");
    document.location.href = 'http://localhost/groupe_olivier/ajout_capteur.php';
</script>
