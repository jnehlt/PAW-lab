const { default: Card } = require("../models/card");
const { default: authHeader } = require("./auth-header");

class CardService {
    add_card (card) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        myHeaders.append(authHeader());       
        var raw = JSON.stringify({ "listId": card.listId, "name": card.name, "content": card.content, "description": card.description, "complete": card.complete, "deadline": card.deadline });
        var requestOptions = {  method: 'POST',  headers: myHeaders,  body: raw };
        return fetch(API_URL + 'cards', requestOptions)
        .then(response => response.text()).
        catch(error => console.log('error', error));
    }
    


    delete_card (id) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        myHeaders.append(authHeader()); 
        var requestOptions = {  method: 'DELETE',  headers: myHeaders,  body: JSON.stringify({  }) };
    }

    update_card () {

    }

    get_card_by_id () {
        
    }

    get_all_cards () {

    }

    get_all_lists () {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        myHeaders.append(authHeader());
        var requestOptions = { method: 'GET', headers: myHeaders };
        return fetch(API_URL + 'lists', requestOptions)
        .then(response => response.json()).then(data => (this.totalVuePackages = data.total));
    }

    get_list_by_id (id) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        myHeaders.append(authHeader());
        var requestOptions = { method: 'GET', headers: myHeaders, body: {"arrayId": id.arrayId}};
        return fetch(API_URL + 'lists', requestOptions)
        .then(response => response.json())
    }

    add_list (list) {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");       
        myHeaders.append(authHeader());       
        var raw = JSON.stringify({ "name": list.name, "userId": list.userId, "arrayId": list.arrayId });
        var requestOptions = {  method: 'POST',  headers: myHeaders,  body: raw };
        return fetch(API_URL + 'lists', requestOptions)
        .then(response => response.text())
        .catch(error => console.log('error', error));
    }

    delete_list () {
        
    }
}