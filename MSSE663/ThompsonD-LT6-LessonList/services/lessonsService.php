<?php
if ("GET" != filter_input(INPUT_SERVER, "REQUEST_METHOD")) {    
    include './models/Error.php';
    
    http_response_code(400);
    header("Content-Type: application/json");
    $error = new Error('Bad lessons request', 400);
    echo json_encode($error);
    exit();
}

// set up data for REST request
//
include './models/Lesson.php';
$lessons = [];

$lessons[] = new Lesson(1, 'img/jquery.png', 'jQuery - Getting Started', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475980/View');
$lessons[] = new Lesson(2, 'img/jquery-ajax.png', 'jQuery - AJAX', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475981/View');
$lessons[] = new Lesson(3, 'img/angularjs.png', 'Angular JS - Introduction', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475982/View');
$lessons[] = new Lesson(4, 'img/angularjs-templates.png', 'Angular JS - Views and Templates', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475983/View');
$lessons[] = new Lesson(5, 'img/angularjs-rest.png', 'Angular JS - Routing, Events, REST, and HTTP', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475984/View');
$lessons[] = new Lesson(6, 'img/slider.jpg', 'Angular JS - Content Sliders', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475985/View');
$lessons[] = new Lesson(7, 'img/google-maps.png', 'Google Maps API', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475986/View');
$lessons[] = new Lesson(8, 'img/google-places.jpg', 'Google Places API', 'https://worldclass.regis.edu/d2l/le/content/200883/viewContent/2475987/View');

// if no "id" parameter, return the entire list
//
if (null == filter_input(INPUT_GET, 'id')) {
    http_response_code(200);
    header("Content-Type: application/json");
    echo json_encode($lessons);
    exit();
}

// if the "id" parameter is in range, return that lesson
//
$id = intval(filter_input(INPUT_GET, 'id')) - 1;
if ($id < count($lessons)) {
    http_response_code(200);
    header("Content-Type: application/json");
    echo json_encode($lessons[$id]);
    exit();
}

// otherwise, indicate lesson is not found
//
include './models/Error.php';

http_response_code(404);
header("Content-Type: application/json");
$error = new Error('Lesson not found', 404);
echo json_encode($error);
exit();
?>