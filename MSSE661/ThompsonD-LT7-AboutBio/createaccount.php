<?php
include 'validate.php';

$firstName = filter_input(INPUT_POST, "first-name");
$lastName = filter_input(INPUT_POST, "last-name");
$emailAddress = filter_input(INPUT_POST, "email-address");
$userName = filter_input(INPUT_POST, "user-name");
$password = filter_input(INPUT_POST, "password");
$confirmPassword = filter_input(INPUT_POST, "password-confirm");

$validator = new Validate;

if (!$validator->validateName($firstName) ||
    !$validator->validateName($lastName) ||
    !$validator->validateEmailAddress($emailAddress) ||
    !$validator->validateUserName($userName) ||
    !$validator->validatePassword($password) ||
    !$validator->validateConfirmPassword($password, $confirmPassword)) {
    header('Location: createaccount.html');
} else if (!add_user($firstName, $lastName, $emailAddress, $userName, $password)) {
    header('Location: accounterror.html');
} else {
    header('Location: about.html');
}

function add_user($firstName, $lastName, $emailAddress, $userName, $password) {
    $connection = new PDO('mysql:host=localhost;dbname=AboutBio;charset=utf8', 'root', 'msse661');
    $statement = $connection->prepare('INSERT INTO user (firstname, lastname, emailaddress, username, password) ' .
                                                'VALUES (:firstname, :lastname, :emailaddress, :username, :password)');
    $result = $statement->execute([
        ':firstname' => $firstName,
        ':lastname' => $lastName,
        ':emailaddress' => $emailAddress,
        ':username' => $userName,
        ':password' => password_hash($password, PASSWORD_DEFAULT)
    ]);
    
    return $result;
}
?>
