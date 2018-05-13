(function () {
    jQuery(main);
    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        //$('.wbdv-template').hide()
        findAllUsers();
        $('.wbdv-create').css('cursor', 'pointer');
        $('.wbdv-remove').css('cursor', 'pointer');
        $('.wbdv-edit').css('cursor', 'pointer');
        $('.wbdv-search').css('cursor', 'pointer');
        $('.wbdv-update').css('cursor', 'pointer');
        $('.wbdv-create').click(createUser);

    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function renderUsers(users) {
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id)
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);
        }
    }

    function createUser() {
        var form = $('.wbdv-form');
        username = form.find('#usernameFld').val();
        password = form.find('#passwordFld').val();
        firstName = form.find('#firstNameFld').val();
        lastName = form.find('#lastNameFld').val();
        role = form.find('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService
            .createUser(user)
            .then(findAllUsers)
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn.parent().parent().attr("id");
         userService.deleteUser(userId).then(findAllUsers);
    }
})();