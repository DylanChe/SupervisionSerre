<?php
require_once('connect.php');

    $periode = $_POST['periode'];
    $request = $bdd->prepare( 'UPDATE parametre SET periode = :periode WHERE id = "1"' );
    $request->execute(array(
        'periode' => $periode
    ));
?>

<script type="text/javascript">
    alert('La période a été modifiée.');
    document.location.href = 'http://92.222.92.147/ajout_capteur.php';
</script>

