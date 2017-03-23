<?php
include 'validate.php';

// make sure we were POSTed to
//
if (filter_input(INPUT_SERVER, "REQUEST_METHOD") != "POST") {
    http_response_code(500);
    return;
}
if (filter_input(INPUT_POST, "create-account") != "t") {
    http_response_code(500);
    return;
}

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
    $result = "invalidData";
} else if (add_user($firstName, $lastName, $emailAddress, $userName, $password, $result)) {
    $result = "success";
}

$json = "{ \"$result\": true }";
echo($json);

function add_user($firstName, $lastName, $emailAddress, $userName, $password, &$result) {
    $connection = new PDO('mysql:host=localhost;dbname=AboutBio;charset=utf8', 'root', 'msse661');
    
    // check whether the username is already claimed
    //
    $statement = $connection->prepare('SELECT COUNT(*) FROM user WHERE username = :username');
    if ($statement->execute([':username' => $userName])) {
        $row = $statement->fetch();
        if ($row[0] > 0) {
            $result = "usernameExists";
            return false;
        }
    }
    
    $statement = $connection->prepare('INSERT INTO user (firstname, lastname, emailaddress, username, password) ' .
                                                'VALUES (:firstname, :lastname, :emailaddress, :username, :password)');
    $sqlret = $statement->execute([
        ':firstname' => $firstName,
        ':lastname' => $lastName,
        ':emailaddress' => $emailAddress,
        ':username' => $userName,
        ':password' => password_hash($password, PASSWORD_DEFAULT)
    ]);
    
    return true;
}
?>
