(function () {
    var $username, $phone, $email, $dateOfBirth, $role;
    var $updateBtn, $logoutBtn;
    var $form;
    var userService = new UserServiceClient();

    $(main);
    function main() {
        $form = $('form');
        $updateBtn = $form.find('.btn-update');
        $logoutBtn = $('.btn-logout');
        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
    }
    
    function updateProfile(event) {
        $updateBtn = $(event.currentTarget);
        $form = $('form');
        $username = $form.find('#username');
        $phone = $form.find('#phone');
        $email = $form.find('#email');
        $dateOfBirth = $form.find('#dob');
        $role = $form.find('#role');
        var username = $username.val();
        var phone = $phone.val();
        var email = $email.val();
        var role = $role.val();
        var dateOfBirth = $dateOfBirth.val();
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        user.setDateOfBirth(dateOfBirth);
        userService.updateProfile(user).then(function (responseUser) {
                alert(responseUser.username+" Updated Successfully!");
            });
    }
    
    function logout(event) {
        $logoutBtn = $(event.currentTarget);
        var redirectPage = $logoutBtn.data('redirect-to');
        window.location.href = redirectPage;
    }
})();