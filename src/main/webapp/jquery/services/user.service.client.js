function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.findUserByUsername = findUserByUsername;
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
                if (response.ok) {
                    return response.json();
                } else {
                    alert("An error occurred. Please see console.");
                    console.log(response);
                }
            })
    }

    function findUserById(userId) {
        if (userId != undefined) {
            return fetch(self.url + "/" + userId)
                .then(function (response) {
                    return response.json();
                });
        } else {
            alert("An error occurred. Please see console.");
            console.log(response);
        }
    }

    function updateUser(userId, user) {
        return fetch(self.url + "/" + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                alert("An error occurred. Please see console.");
                console.log(response);
            }
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
            if (response.ok) {
                return response.json();
            } else {
                alert("An error occurred. Please see console.");
                console.log(response);
            }
        });
    }

    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                return undefined;
            }
        });
    }

    function updateProfile(user) {
        return fetch(self.updateProfileUrl, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                alert("An error occurred. Please see console.");
                console.log(response);
            }

        });
    }

    function findUserByUsername(username) {
        return fetch(self.url+"?username="+username)
            .then(function (response) {
                if(response.ok){
                    return response.json();
                }else{
                    alert("An error occurred. Please see console.");
                    console.log(response);
                }
            });
    }
}