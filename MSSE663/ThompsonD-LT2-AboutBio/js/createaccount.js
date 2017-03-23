/* 
 * Helpers for account creation.
 */

/* Send account creation information to the server.
 */
submitAccount = function() {

    var hideAccountMessage = function(el) {
        $(el).removeClass("form-input-visible")
             .addClass("form-input-hidden");
    };

    var showAccountMessage = function(el) {
        $(el).removeClass("form-input-hidden")
             .addClass("form-input-visible");
    };

    hideAccountMessage("#account-success-message");
    hideAccountMessage("#account-error-message");
    hideAccountMessage("#account-inuse-message");

    var accountData =
            [ "first-name",
              "last-name",
              "email-address",
              "user-name",
              "password",
              "password-confirm"];

    var postData = accountData
            .map(function(s) { return s + "=" + document.getElementById(s).value; })
            .reduce(function(acc, val) { return acc + "&" + val; }, "create-account=t");

    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState === 4 && request.status === 200) {
            var text = request.responseText;
            var json = JSON.parse(text);
            if (json.success) {
                showAccountMessage("#account-success-message");
                $("#account")[0].reset();
            } else if (json.usernameExists) {
                showAccountMessage("#account-inuse-message");
            } else {
                showAccountMessage("#account-error-message");
            }
        }
    };
    
    request.open("POST", "createaccount.php");
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(postData);
    
    return false;
};
