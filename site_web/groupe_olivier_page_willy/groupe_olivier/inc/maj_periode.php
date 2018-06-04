<?php
require_once('connect.php');

function majPeriod($bdd){
    $periode = $_POST['periode'];
    $request = $bdd->prepare( 'UPDATE periode SET periode = :periode WHERE id = 1' );
    $request->execute(array(
        'periode' => $periode
    ));
}
?>