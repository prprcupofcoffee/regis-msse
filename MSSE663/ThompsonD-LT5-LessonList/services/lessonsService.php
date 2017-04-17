<?php
if (filter_input(INPUT_SERVER, "REQUEST_METHOD") == "GET") {    
   
    include './models/penguin.php';
    $penguins = [];
    
    $penguins[] = new Penguin('King penguin', 'Aptenodytes patagonicus');
    $penguins[] = new Penguin('Emperor penguin', 'Aptenodytes forsteri');
    $penguins[] = new Penguin('Adélie penguin', 'Pygoscelis adeliae');
    
    http_response_code(200);
    header("Content-Type: application/json");
    echo json_encode($penguins);
    exit();
}
else {
    include './models/error.php';
    
    http_response_code(400);
    header("Content-Type: application/json");
    $error = new Error('Bad lessons request', 400);
    echo json_encode($error);
    exit();
}
?>