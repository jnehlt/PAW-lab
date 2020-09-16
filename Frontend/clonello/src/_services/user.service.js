import { authHeader } from "../_helpers";

export const userService = {
    login,
    logout,
    singup
};

function login(username, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ firstName, password })
    };

    return fetch("http://0.0.0.0:8082/users", requestOptions).then(handleResponse).then(user => {
        //login successful if there's a user in the response
        if (user) {
            //store user details and basic auth credentials in local storage
            user.authdata = window.btoa(username + ':' + password).setItem('user', JSON.stringify(user));
        }

        return user;
    });
}

function logout() {
    localStorage.removeItem("user");
}


function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                //auto logout if 401 response returned from api
                logout();
                location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }
        
        return data;
    })
}