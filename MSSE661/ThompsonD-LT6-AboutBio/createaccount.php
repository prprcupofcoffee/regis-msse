<?php
include 'validate.php';

$firstName = $_POST["first-name"];
$lastName = $_POST["last-name"];
$emailAddress = $_POST["email-address"];
$userName = $_POST["user-name"];
$password = $_POST["password"];
$confirmPassword = $_POST["password-confirm"];

$validator = new Validate;

if ($validator->validateName($firstName) &&
    $validator->validateName($lastName) &&
    $validator->validateEmailAddress($emailAddress) &&
    $validator->validateUserName($userName) &&
    $validator->validatePassword($password) &&
    $validator->validateConfirmPassword($password, $confirmPassword)) {
    header('Location: about.html');
} else {
    header('Location: createaccount.html');
}
?>
