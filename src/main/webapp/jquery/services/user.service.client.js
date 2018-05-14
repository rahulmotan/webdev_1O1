function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.updateProfile = updateProfile;
    this.updateProfileUrl = '/api/profile';
    this.registerUrl = '/api/register';
    this.loginUrl = '/api/login';
    this.url = '/api/user';

    var self = this;

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            })
    }

    function findUserById(userId) {
        return fetch(self.url + "/" + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(userId, user) {
        return fetch(self.url + "/" + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        })
    }

    function deleteUser(userId) {
        return fetch(self.url + "/" + userId, {
            method: 'delete',
        }).then(function (response) {
            return response
        })
    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        })
    }

    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }
    function updateProfile(user) {
        return fetch(self.updateProfileUrl, {
            method: 'put',
            body: JSON.stringify(user),
            headers:{
                'content-type':'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }
}