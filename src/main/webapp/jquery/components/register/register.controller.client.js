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
    function register(event) {
        $registerBtn = $(event.currentTarget);
        form = $registerBtn.parent().parent().parent();
        $usernameFld = form.find('#usernameFld');
        $passwordFld = form.find('#passwordFld');
        $verifyPasswordFld = form.find('#verifyPasswordFld');
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var verifyPassword = $verifyPasswordFld.val();
        if (password != verifyPassword){
            alert("Passwords do not match!")
        }
        var user = new User();
        user.setFirstName("");
        user.setLastName("");
        user.setUsername(username);
        user.setPassword(password);
        userService.register(user).then(function (user) {
            alert("Registered!" + user.username);
        });
    }

})();
