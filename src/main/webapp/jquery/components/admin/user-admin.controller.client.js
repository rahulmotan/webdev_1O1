(function () {
    jQuery(main);
    var tbody;
    var template;

    function main(){
        tbody = $('tbody');
        template = $('.wbdv-template');
        $('.wbdv-create').click(createUser);
        var promise = fetch("https/localhost:8080/api/user");
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers);
    }

    function renderUsers(users) {
        for (var i=0;i<users.length;i++){
            var user = users[i];
            var clone = template.clone();
            clone.find('.username')
                .html(user.username);
            tbody.append(clone);
        }
    }
    function createUser() {
        const form = $('.wbdv-form');
        username = form.find('#usernameFld').val();
        password = form.find('#passwordFld').val();
        firstName = form.find('#firstNameFld').val();
        lastName = form.find('#lastNameFld').val();

        var user = {
            username : username,
            password : password,
            firstName : firstName,
            lastName : lastName
        };

        fetch("https/localhost:8080/api/user", {
            method: 'post',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        })

    }
})();