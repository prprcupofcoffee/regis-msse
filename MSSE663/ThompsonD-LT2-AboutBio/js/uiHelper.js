/* 
 * UI modifiers for forms on this site.
 */

uiHelper = (function() {
    /* 
     * UI modifiers for forms on this site.
     */

    /* Change the border color of an input element when the focus enters.
     */
    var enterInput = function() {
        $(this).addClass("input-highlight");
    };

    /* Change the border color of an input element when the focus leaves.
     */
    var leaveInput = function() {
        $(this).removeClass("input-highlight");
    };

    /* Change the border color of an element when the mouse enters.
     */
    var enterSection = function() {
        $(this).addClass("border-highlight");
    };

    /* Change the border color of an element when the mouse leaves.
     */
    var leaveSection = function() {
        $(this).removeClass("border-highlight");
    };
    
    var setLoginFormVisible = function() {
        $("#login-form").fadeIn(200);
    }

    // Public API
    //
    return {
        enterInput: enterInput,
        enterSection: enterSection,
        leaveInput: leaveInput,
        leaveSection: leaveSection,
        setLoginFormVisible: setLoginFormVisible
    };
})();
