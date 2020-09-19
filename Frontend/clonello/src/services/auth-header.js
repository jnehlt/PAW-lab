export default function authHeader() {
    //return authorization header with basic auth credentials
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        return { 'Authorization': 'Bearer' + user.accessToken };
    } else {
        return {};
    }
}