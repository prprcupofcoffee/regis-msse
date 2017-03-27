/* 
 * Helpers for account creation.
 */

/* Send account creation information to the server.
 */
submitAccount = function() {

    // hide any previous warning or result
    
    $("#account-response-message")
        .removeClass("form-input-hidden")
        .addClass("form-input-visible");

    // collect these fields from the form
    //
    var accountData =
            [ "first-name",
              "last-name",
              "email-address",
              "user-name",
              "password",
              "password-confirm"];

    // stitch together into x-www-form-encoded
    // format
    //
    var postData = accountData
            .map(function(s) { return s + "=" + document.getElementById(s).value; })
            .reduce(function(acc, val) { return acc + "&" + val; }, "create-account=t");

    // send the data to the server
    //
    $.post({
        url: "createaccount.php",
        data: postData,
        processData: false,
        success: function(data) {
            var json = JSON.parse(data);
            if (json && json.result) {
                $("#account-response-message > .message").text(json.result);
                $("#account-response-message")
                        .removeClass("form-input-hidden")
                        .addClass("form-input-visible");

                if (json.success)
                    $("#account")[0].reset();
            }
        }
    });
    
    return false;
};
