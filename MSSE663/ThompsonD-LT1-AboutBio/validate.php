<?php

/*
 * Validators for forms on this site.
 */

class Validate {
    /* Validate an email address.
     * 
     * An email address:
     * - must not be falsy
     */

    public function validateEmailAddress($addr) {
        // address may not be empty
        //
        if (empty($addr)) {
            return false;
        }

        // anything else is OK
        //
        return true;
    }

    /* Validate a name.
     * 
     * A name:
     * - must not be falsy
     * - must not contain only whitespace
     */

    public function validateName($name) {
        // name may not be empty
        //
        if (empty($name)) {
            return false;
        }

        // name may not consist only of whitespace
        //
        if (empty(trim($name))) {
            return false;
        }

        // anything else is OK
        //
        return true;
    }

    /* Validate a password.
     * 
     * A password:
     * - must not be falsy
     * - must not be fewer than eight characters in length
     */

    public function validatePassword($password) {
        // password may not be empty
        //
        if (empty($password)) {
            return false;
        }

        // password must be long enough
        //
        if (strlen($password) < 8) {
            return false;
        }

        // anything else is OK
        //
        return true;
    }

    /* Validate a confirming password.
     * 
     * A confirming password:
     * - must match the password (including falsy)
     */

    public function validateConfirmPassword($password, $confirmingPassword) {

        // password and confirming password must match
        //
        if ($password != $confirmingPassword) {
            return false;
        }

        // anything else is OK
        //
        return true;
    }

    /* Validate a user name.
     * 
     * A user name:
     * - must not be falsy
     * - must not contain only whitespace
     */

    public function validateUserName($name) {
        // name may not be empty
        //
        if (empty($name)) {
            return false;
        }

        // name may not contain whitespace
        //
        if (strpos($name, ' ')) {
            return false;
        }

        // anything else is OK
        //
        return true;
    }

}

?>
