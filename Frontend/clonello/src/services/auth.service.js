import axios from "axios";

const API_URL = 'http://localhost:8082/users/';

class AuthService {
    login(user) {
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: { "name": user.name, "password": user.password }
          };
          return fetch(API_URL + "login", requestOptions) 
        // return axios
        //     .post(API_URL + "login", {
        //         name: user.name,
        //         password: user.password
        //     })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data))
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user')
    }

    register(user) {
        return axios.post(API_URL + "sign", {
            firstName: user.firstName,
            lastName: user.lastName,
            name: user.name,
            password: user.password
        });
    }
}

export default new AuthService();