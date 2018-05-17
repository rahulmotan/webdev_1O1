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
        username = username.trim();
        user.setUsername(username);
        user.setPassword(password);
        if (username !== undefined && username.length > 0) {
            userService.login(user).then(function (user) {
                //alert("Welcome " + user.username);
                if (user != undefined) {
                    window.location.href = redirect + queryString + user.id;
                } else {
                    $('#notificationModal').modal('show');
                }

            });
        } else {
            $('#notificationModal').modal('show');
        }


    }
})();