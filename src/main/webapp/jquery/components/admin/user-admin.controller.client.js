(function () {
    jQuery(main);
    var tbody;
    var template;

    function main(){
        tbody = $('tbody');
        template = $('.wbdv-template');
        $('.wbdv-create').css('cursor', 'pointer');
        $('.wbdv-create').click(createUser);
        var promise = fetch("http://localhost:8080/api/user");
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers);
    }

    function renderUsers(users) {
        for (var i=0;i<users.length;i++){
            var user = users[i];
            var clone = template.clone();
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
            username : username,
            password : password,
            firstName : firstName,
            lastName : lastName,
            role: role
        };

        fetch("http://localhost:8080/api/user", {
            method: 'post',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        })

    }
})();