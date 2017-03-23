/* 
 * UI modifiers for forms on this site.
 */

/* Change the border color of an input element when the focus enters.
 */
enterInput = function(el) {
    el.className = el.className.concat(" input-highlight");
};

/* Change the border color of an input element when the focus leaves.
 */
leaveInput = function(el) {
    el.className = el.className.replace(/ *input-highlight */, "");
};

/* Change the border color of an element when the mouse enters.
 */
enterSection = function(el) {
    el.className = el.className.concat(" border-highlight");
};

/* Change the border color of an element when the mouse leaves.
 */
leaveSection = function(el) {
    el.className = el.className.replace(/ *border-highlight */, "");
};

hideAccountMessage = function(el) {
    if (el.className.indexOf('form-input-visible') >= 0) {
        el.className = el.className.replace(/ *form-input-visible */, ' form-input-hidden ');
    }
};

showAccountMessage = function(el) {
    if (el.className.indexOf('form-input-hidden') >= 0) {
        el.className = el.className.replace(/ *form-input-hidden */, ' form-input-visible ');
    }
};
