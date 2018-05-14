function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
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
}

function UserService() {
    this.register = register;
    this.url = 'http://localhost:8080/api/register';
    var self = this;

    function register(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        })
    }
}