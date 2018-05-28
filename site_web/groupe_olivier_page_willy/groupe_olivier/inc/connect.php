<?php

// database settings
$db = null;
$db_engine = 'mysql';
$host = '92.222.92.147';
$charset = 'utf8';

$db_user = 'projetbts';
$db_password = 'Nantes44';
$db_base = 'supervision_serre';
$dsn = "mysql:host=$host;dbname=$db_base;charset=$charset";


// Connection to our database
try{

    $dsn = "mysql:host=$host;dbname=$db_base;charset=$charset";
    $options = [
        PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_OBJ,
        PDO::ATTR_EMULATE_PREPARES   => true,
    ];
    $bdd = new PDO($dsn, $db_user, $db_password, $options);

}catch (PDOException $e){
    print(json_encode(array('outcome' => false, 'message' => 'Impossible de se connecter !')));
}
