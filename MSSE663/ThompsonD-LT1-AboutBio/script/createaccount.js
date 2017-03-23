/* 
 * Helpers for account creation.
 */

/* Send account creation information to the server.
 */
submitAccount = function() {
    hideAccountMessage(document.getElementById("account-success-message"));
    hideAccountMessage(document.getElementById("account-error-message"));
    hideAccountMessage(document.getElementById("account-inuse-message"));

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
                showAccountMessage(document.getElementById("account-success-message"));
                document.getElementById("account").reset();
            } else if (json.usernameExists) {
                showAccountMessage(document.getElementById("account-inuse-message"));
            } else {
                showAccountMessage(document.getElementById("account-error-message"));
            }
        }
    };
    
    request.open("POST", "createaccount.php");
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(postData);
    
    return false;
};

/* Process results of creating an account.
 */
leaveInput = function(el) {
    el.className = el.className.replace(/ *input-highlight */, "");
};
