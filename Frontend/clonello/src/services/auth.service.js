import axios from "axios";

const API_URL = 'http://0.0.0.0:8082/users/';

class AuthService {
    login(user) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        var raw = JSON.stringify({"name":user.name,"password": user.password});
        var requestOptions = {  method: 'POST',  headers: myHeaders,  body: raw,  redirect: 'follow'};
        return fetch(API_URL + 'login', requestOptions)
        .then(response => response.text())
        .then(token => {        
            if (token) {            
                localStorage.setItem('user', JSON.stringify({ ...user, token: JSON.stringify(token) }));
        }}).catch(error => console.log('error', error));
          
        // return axios
        //     .post(API_URL + "login", {
        //         name: user.name,
        //         password: user.password
        //     })
            

            //     return result.data;
            // });
    
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