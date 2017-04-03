/* 
 * Contains tests for the validators in script/validate.js.
 */ 
require('should');

// load tests from validate.js
//
var fs = require('fs');
var fileData = fs.readFileSync('script/validate.js', 'utf8');
eval(fileData);

// validation tests
//
describe('Validation', function() {

    // name validation
    //
    describe('validateName', function() {
        it('should fail when name is empty', function() {
            validateName('').should.equal(false);
        });

        it('should fail when name is null', function() {
            validateName(null).should.equal(false); 
        });

        it('should fail when name is undefined', function() {
            validateName(undefined).should.equal(false); 
        });

        it('should fail when name is whitespace', function() {
            validateName(' ').should.equal(false); 
        });

        it('should succeed when name is nonblank', function() {
            validateName('stuff').should.equal(true); 
        });
    });
    
    // user name validation
    //
    describe('validateUserName', function() {
        it('should fail when user name is empty', function() {
            validateUserName('').should.equal(false);
        });

        it('should fail when user name is null', function() {
            validateUserName(null).should.equal(false); 
        });

        it('should fail when user name is undefined', function() {
            validateUserName(undefined).should.equal(false); 
        });

        it('should fail when user name is whitespace', function() {
            validateUserName(' ').should.equal(false); 
        });

        it('should fail when user name contains whitespace', function() {
            validateUserName('a a').should.equal(false); 
        });

        it('should succeed when user name is nonblank', function() {
            validateUserName('stuff').should.equal(true); 
        });
    });

    // email address validation
    describe('validateEmailAddress', function() {
        it('should fail when email address is empty', function() {
            validateEmailAddress('').should.equal(false); 
        });

        it('should fail when email address is null', function() {
            validateEmailAddress(null).should.equal(false); 
        });

        it('should fail when email address is undefined', function() {
            validateEmailAddress(undefined).should.equal(false); 
        });

        it('should succeed when email address is valid', function() {
            validateEmailAddress('stuff').should.equal(true); 
        });
    });

    // password validation
    describe('validatePassword', function() {
        it('should fail when password is empty', function() {
            validatePassword('').should.equal(false); 
        });

        it('should fail when password is null', function() {
            validatePassword(null).should.equal(false); 
        });

        it('should fail when password is undefined', function() {
            validatePassword(undefined).should.equal(false); 
        });

        it('should fail when password is too short', function() {
            validatePassword('stuff').should.equal(false); 
        });

        it('should succeed when password is valid', function() {
            validatePassword('stuffing').should.equal(true); 
        });
    });

    // confirm password validation
    describe('validateConfirmingPassword', function() {
        it('should fail when password is empty but not confirming password', function() {
            validateConfirmPassword('', 'x').should.equal(false); 
        });

        it('should fail when password is null but not confirming password', function() {
            validateConfirmPassword(null, 'x').should.equal(false); 
        });

        it('should fail when password is undefined but not confirming password', function() {
            validateConfirmPassword(undefined, 'x').should.equal(false); 
        });

        it('should fail when confirm password is empty but not password', function() {
            validateConfirmPassword('x', '').should.equal(false); 
        });

        it('should fail when confirm password is null but not password', function() {
            validateConfirmPassword('x', null).should.equal(false); 
        });

        it('should fail when confirm password is undefined but not password', function() {
            validateConfirmPassword('x', undefined).should.equal(false); 
        });

        it('should fail when password and confirm password do not match', function() {
            validateConfirmPassword('x', 'y').should.equal(false); 
        });

        it('should succeed when password and confirm password are both empty', function() {
            validateConfirmPassword('', '').should.equal(true); 
        });

        it('should succeed when password and confirm password are both null', function() {
            validateConfirmPassword(null, null).should.equal(true); 
        });

        it('should succeed when password and confirm password are both undefined', function() {
            validateConfirmPassword(undefined, undefined).should.equal(true); 
        });

        it('should succeed when password and confirm password match', function() {
            validateConfirmPassword('x', 'x').should.equal(true); 
        });
    });
});

