/* 
 * Validators for forms on this site.
 */

/* Validate an email address.
 * 
 * An email address:
 * - must not be falsy
 */
validateEmailAddress = function(addr) {
    // address may not be empty
    //
    if (!addr)
        return false;
    
    // anything else is OK
    //
    return true;
};

/* Validate a name.
 * 
 * A name:
 * - must not be falsy
 * - must not contain only whitespace
 */
validateName = function(name) {
    // name may not be empty
    //
    if (!name)
        return false;
    
    // name may not consist only of whitespace
    //
    if (!name.trim())
        return false;
    
    // anything else is OK
    //
    return true;
};

/* Validate a password.
 * 
 * A password:
 * - must not be falsy
 * - must not be fewer than eight characters in length
 */
validatePassword = function(password) {
    // password may not be empty
    //
    if (!password)
        return false;
    
    // password must be long enough
    //
    if (password.length < 8)
        return false;
    
    // anything else is OK
    //
    return true;
};

/* Validate a confirming password.
 * 
 * A confirming password:
 * - must match the password (including falsy)
 */
validateConfirmPassword = function(password, confirmingPassword) {

    // password and confirming password must match
    //
    if (password !== confirmingPassword)
        return false;
    
    // anything else is OK
    //
    return true;
};

/* Validate a user name.
 * 
 * A user name:
 * - must not be falsy
 * - must not contain only whitespace
 */
validateUserName = function(name) {
    // name may not be empty
    //
    if (!name)
        return false;
    
    // name may not contain whitespace
    //
    if (name.includes(' '))
        return false;
    
    // anything else is OK
    //
    return true;
};

/* Validate a form element.
 * 
 * - run the specified validation function with the indicated test value
 * - if the function returns false, show the indicated message
 * - if the function returns true, hide the indicated message
 * - return the validation status
 */
validateField = function(validator, messageFieldId, valueIdA, valueIdB) {
    var valueA = document.getElementById(valueIdA);
    var valueB = document.getElementById(valueIdB);
    var ret = validator(valueA ? valueA.value : null,
                        valueB ? valueB.value : null);

    var field = document.getElementById(messageFieldId);
    var classes = field.className;
    if (ret) {
        if (classes.indexOf('form-input-visible') >= 0) {
            classes = classes.replace(/ *form-input-visible */, ' form-input-hidden ');
        }
    } else {
        if (classes.indexOf('form-input-hidden') >= 0) {
            classes = classes.replace(/ *form-input-hidden */, ' form-input-visible ');
        }
    }
    field.className = classes;
    
    return ret;
};

/* Validate the input.
 */
validateInput = function() {
    var ret =
            validateField(validateName, 'first-name-error', 'first-name') &
            validateField(validateName, 'last-name-error', 'last-name') &
            validateField(validateEmailAddress, 'email-address-error', 'email-address') &
            validateField(validateUserName, 'user-name-error', 'user-name') &
            validateField(validatePassword, 'password-error', 'password') &
            validateField(validateConfirmPassword, 'password-confirm-error', 'password', 'password-confirm');
    
    return ret;
};