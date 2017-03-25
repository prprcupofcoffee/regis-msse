<?php
include 'validate.php';

$success = false;

// make sure we were POSTed to
//
if (filter_input(INPUT_SERVER, "REQUEST_METHOD") != "POST" ||
    filter_input(INPUT_POST, "create-account") != "t") {
    $result = "There was an error creating the account.";
} else {
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
        $result = "Your account could not be created. Please check your input.";
    } else if (add_user($firstName, $lastName, $emailAddress, $userName, $password, $result)) {
        $result = "The account was created successfully.";
        $success = true;
    }
}

$json = json_encode([
    "result" => $result,
    "success" => $success
]);
echo($json);

function add_user($firstName, $lastName, $emailAddress, $userName, $password, &$result) {
    $connection = new PDO('mysql:host=localhost;dbname=AboutBio;charset=utf8', 'root', 'msse661');
    
    // check whether the username is already claimed
    //
    $statement = $connection->prepare('SELECT COUNT(*) FROM user WHERE username = :username');
    if ($statement->execute([':username' => $userName])) {
        $row = $statement->fetch();
        if ($row[0] > 0) {
            $result = "The username you selected is already being used.";
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
