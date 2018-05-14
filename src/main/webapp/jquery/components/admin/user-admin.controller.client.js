(function () {
    jQuery(main);
    var tbody;
    var template;
    var userService = new UserServiceClient();
    var form;

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        //$('.wbdv-template').hide()
        findAllUsers();
        $('.wbdv-create').css('cursor', 'pointer');
        $('.wbdv-remove').css('cursor', 'pointer');
        $('.wbdv-edit').css('cursor', 'pointer');
        $('.wbdv-update').css('cursor', 'pointer');
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);

    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function renderUsers(users) {
        tbody = $('tbody');
        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id)
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(findUserById)
            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);
        }
    }

    function createUser() {
        form = $('.wbdv-form');
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
        var userId = deleteBtn.parent().parent().parent().attr("id");
        alert(userId);
         userService.deleteUser(userId).then(findAllUsers);
    }
    
    function updateUser() {
        form = $('.wbdv-form');
        userId = form.find('#userIdFld').val();
        username = form.find('#usernameFld').val();
        password = form.find('#passwordFld').val();
        firstName = form.find('#firstNameFld').val();
        lastName = form.find('#lastNameFld').val();
        role = form.find('#roleFld').val();
        var user = {
            id: userId,
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService.updateUser(userId, user).then(findAllUsers)
    }

    function findUserById(event) {
        var editBtn = $(event.currentTarget);
        form = $('.wbdv-form');
        var parentTableRow = $(editBtn).parent().parent().parent();
        userId = parentTableRow.attr('id');
        userService.findUserById(userId).then(renderUser);
    }

    function renderUser(user){
        form.find('#userIdFld').val(user.id);
        form.find('#usernameFld').val(user.username);
        form.find('#passwordFld').val(user.password);
        form.find('#firstNameFld').val(user.firstName);
        form.find('#lastNameFld').val(user.lastName);
        form.find('#roleFld').val(user.role);
    }
})();