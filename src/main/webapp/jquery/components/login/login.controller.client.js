(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var $form;
    $(main);
    function main() {
        $form = $('form');
        $loginBtn = $('.btn-login');
        $loginBtn.click(login);
    }
    function login(event) {
        var redirect = undefined;
        var queryString = "?id=";
        $loginBtn = $(event.currentTarget);
        var formGroups = $('form').find('.form-group');
        redirect = $loginBtn.data('redirect-to');
        $usernameFld = formGroups.find('#username');
        $passwordFld = formGroups.find('#password');
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.login(user).then(function (user) {
            alert("Welcome " + user.username);
            window.location.href = redirect+queryString+user.id;
        });

    }
})();