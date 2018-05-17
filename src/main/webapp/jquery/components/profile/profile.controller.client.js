(function () {
    var $username, $phone, $email, $dateOfBirth, $role;
    var $updateBtn, $logoutBtn;
    var $form;
    var userService = new UserServiceClient();
    var queryParams;
    var userId;
    var flag = false;
    $(main);

    function main() {
        if(flag){
            $('.alert').toggleClass('hide');
        }
        $form = $('form');
        $updateBtn = $form.find('.btn-update');
        $logoutBtn = $('.btn-logout');
        if (window.location.href.includes("?")) {
            queryParams = window.location.href.split('?');
            if (queryParams != undefined && queryParams.length > 1) {
                userId = queryParams[1].split('=');
                if (userId != undefined && userId != "") {
                    populateForm(userId[1], $form);
                }

            }
        }
        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
    }

    function populateForm(id, form) {
        userService.findUserById(id).then(function (user) {
            var date = new Date(user.dateOfBirth).toISOString();
            var now = new Date(date);
            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);
            var dob = date.split('T')[0];
            form.find('#username').val(user.username);
            form.find('#phone').val(user.phone);
            form.find('#email').val(user.email);
            form.find('#role').val(user.role);
            if(user.dateOfBirth!= undefined && user.dateOfBirth.length>0){
                form.find('#dob').val(dob);
            }
        });
    }
    function validateUser(user) {
        if(user.username == undefined || user.username.length == 0){
            alert("Enter username.");
            return false;
        }
        if(user.phone == undefined || user.phone.length == 0){
            alert("Enter phone.");
            return false;
        }
        if(user.role == undefined || user.role.length == 0){
            alert("Enter role.");
            return false;
        }
        if(user.dateOfBirth == undefined || user.dateOfBirth == 0){
            alert("Enter Date");
            return false;
        }
        if(user.email == undefined || user.email.length == 0){
            alert("Enter Email");
            return false;
        }
        return true;
    }

    function updateProfile(event) {
        event.preventDefault();
        var user = new User();
        $updateBtn = $(event.currentTarget);
        queryParams = window.location.href.split('?');
        if (window.location.href.includes("?")) {
            queryParams = window.location.href.split('?');
            if (queryParams != undefined) {
                if (queryParams.length >= 1) {
                    userId = queryParams[1].split('=');
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
                    var dateOfBirth = new Date($dateOfBirth.val());
                    var dob = new Date(dateOfBirth.getTime()-dateOfBirth.getTimezoneOffset()*-60000);
                    user.setId(id)
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setRole(role);
                    user.setDateOfBirth(dob);
                    if(validateUser(user)){
                        userService.updateProfile(user).then(function (responseUser) {
                            if (responseUser !== undefined) {
                                $('.alert').removeClass('hide').addClass('show');
                            }else{
                                window.location.href = "/jquery/components/profile/profile.template.client.html"
                            }
                        });
                    } else {
                        return;
                    }
                }
            }
        }
    }

    function logout(event) {
        $logoutBtn = $(event.currentTarget);
        var redirectPage = $logoutBtn.data('redirect-to');
        window.location.href = redirectPage;
    }
})();