(function () {
    var $username, $phone, $email, $dateOfBirth, $role;
    var $updateBtn, $logoutBtn;
    var $form;
    var userService = new UserServiceClient();
    var queryParams;
    var userId;
    $(main);

    function main() {
        $form = $('form');
        $updateBtn = $form.find('.btn-update');
        $logoutBtn = $('.btn-logout');
        queryParams = window.location.href.split('?');
        if (queryParams != undefined) {
            if (queryParams.length >= 1) {
                userId = queryParams[1].split('=');
            }
        }
        populateForm(userId[1],$form);
        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
    }

    function populateForm(id, form) {
        userService.findUserById(id).then(function (user) {
            form.find('#username').val(user.username);
            form.find('#phone').val(user.phone);
            form.find('#email').val(user.email);
            //form.find('#dob').val(user.dateOfBirth);
            form.find('#role').val(user.role);
        });
    }

    function updateProfile(event, userId) {
        $updateBtn = $(event.currentTarget);
        $form = $('form');
        $username = $form.find('#username');
        $phone = $form.find('#phone');
        $email = $form.find('#email');
        $dateOfBirth = $form.find('#dob');
        $role = $form.find('#role');
        var id = userId[1];
        var username = $username.val();
        var phone = $phone.val();
        var email = $email.val();
        var role = $role.val();
        var dateOfBirth = $dateOfBirth.val();
        var user = new User();
        user.setId(id)
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        user.setDateOfBirth(dateOfBirth);
        userService.updateProfile(user).then(function (responseUser) {
            alert(responseUser.username + " Updated Successfully!");
        });
    }

    function logout(event) {
        $logoutBtn = $(event.currentTarget);
        var redirectPage = $logoutBtn.data('redirect-to');
        window.location.href = redirectPage;
    }
})();