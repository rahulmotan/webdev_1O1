(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    var form;
    $(main);

    function main() {
        form = $('.signup-form');
        $registerBtn = form.find('.btn-primary');
        $registerBtn.click(register);
    }

    function validateUsername(username) {
        return userService.findUserByUsername(username)
            .then(function (user) {
                if (user != undefined || user.username != undefined && user.username == "") {
                    return false;
                } else {
                    return true;
                }
            });
    }

    function register(event) {
        var allValid = true;
        $registerBtn = $(event.currentTarget);
        form = $registerBtn.parent().parent().parent();
        $usernameFld = form.find('#usernameFld');
        $passwordFld = form.find('#passwordFld');
        $verifyPasswordFld = form.find('#verifyPasswordFld');
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var verifyPassword = $verifyPasswordFld.val();
        if (username == undefined || username.trim().length == 0) {
            alert("Username cannot be empty");
            allValid = false;
        }
        if (password.length == 0 || password == undefined) {
            alert("Enter a valid password");
            allValid = false;
        }
        if (password != verifyPassword) {
            alert("Passwords do not match!");
            allValid = false;
        }
        if (allValid) {
            if (validateUsername(username)) {
                var user = new User();
                user.setFirstName("");
                user.setLastName("");
                user.setUsername(username);
                user.setPassword(password);
                userService.register(user).then(function (user) {
                    if (user != undefined && user.username != undefined) {
                        alert("Registered! " + user.username);
                    } else {
                        alert("Sorry! This username is already taken.");
                        return;
                    }
                });
            } else {
                alert("Sorry! This username is already taken.");
                return;
            }
        }

    }

})();
