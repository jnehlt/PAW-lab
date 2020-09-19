import axios from "axios";

const API_URL = "http://0.0.0.0:8082/users/";

class AuthService {
    login(user) {
        return axios
            .post(API_URL + "login", {
                name: user.username,
                password: user.password
            })
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
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();