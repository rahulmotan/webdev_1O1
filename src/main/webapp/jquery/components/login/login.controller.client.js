(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var $form;
    $(main);
    function main() {
        $form = $('form');
        $loginBtn = $form.find('.btn');
        $loginBtn.click(login);
    }
    function login(event) {
        var response = undefined;
        $loginBtn = $(event.currentTarget);
        var formGroups = $('form').find('.form-group');
        $usernameFld = formGroups.find('#username');
        $passwordFld = formGroups.find('#password');
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var user = new User()
        user.setUsername(username);
        user.setPassword(password);
        userService.login(user).then(function (user) {
           alert("Welcome " + user.username);
        });

    }
})();