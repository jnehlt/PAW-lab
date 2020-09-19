# Jak uruchomić
#### Baza danych
Aby uruchomić serwer należy uruchomić kontener dockera z postgreSQL<br>
W tym celu należy w terminalu wpisac:<br>
<code>docker run --name psServer -p 5423:5432 -e POSTGRES_PASSWORD=postgres -d postgres</code><br>
<code>docker exec -it psServer bash</code><br>
<code>psql -U postgres postgres</code><br>
<code>CREATE DATABASE</code><br>
<code>\q</code><br>
<code>exit</code>
<br>
Spowoduje to utworzenie kontera oraz bazy danych postgreSQL
<br>

#### Serwer
Należy zbudować projekt Backend<br>
<code>gradle build -x test</code><br>
oraz uruchomić<br>
<code>gradle run</code>


# Dokumentacja API
## Użytkownicy
<h3>Dodanie uzytkownika</h3><br>
POST
<code>.../users/sign</code><br>
<b>parametry</b><br>
<code>
    MODEL USER
</code>
<br>
<h2></h2>
<h3>Logowanie uzytkownika</h3><br>
POST
<code>.../users/login</code><br>
<b>parametry</b><br>
<code>
MODEL USER FOR LOGIN
</code>
<br>
<b>Odpowiedź</b>
Token i  HTTPSTATUS 200
<br>
<h2></h2>
<h3>Aktualizacja uzytkownika</h3><br>
PUT
<code>.../users</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
    MODEL USER
</code>
<br>
<h2></h2>
<h3>Usunięcie uzytkownika</h3><br>
DELETE<code>.../users</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
MODEL USER
</code>
<br>
<h2></h2>
<h3>Wylogowanie uzytkownika</h3><br>
GET<code>.../users/logout</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<h2></h2>
## LISTY
<h2></h2>
<h3>Pobranie wszystkich list</h3><br>
GET<code>.../lists</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>

<h2></h2>
<h3>Pobranie listy po id</h3><br>
GET<code>.../lists/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<h2></h2>
<h3>Dodanie listy</h3><br>
POST <code>.../lists/</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
MODEL LISTS
</code>
<h2></h2>
<h3>Usunięcie listy po id</h3><br>
DELETE <code>.../lists/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<h2></h2>
<h3>Aktualizacja listy po id</h3><br>
PUT <code>.../lists/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
MODEL LISTS
</code>
<h2></h2>
## Karty
<h3>Pobranie wszystkich kart danej listy</h3><br>
GET <code>.../lists/{id}/cards</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>

<h2></h2>
<h3>Pobranie karty po id</h3><br>
GET <code>.../cards/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>

<h2></h2>
<h3>Wstawienie karty</h3><br>
POST <code>.../cards</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
MODEL CARD
</code>

<h2></h2>
<h3>Usunięcie karty</h3><br>
DELETE <code>.../cards/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<h2></h2>
<h3>Aktualizacja karty</h3><br>
PUT <code>.../cards/{id}</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
MODEL CARD
</code>
<h2></h2>
##MODELE
USER<br>
{<br>
"firstName": "String",<br>
"lastName": "String",<br>
"password": "String",<br>
"email": "String"<br>
}
<br>
<br>
USER FOR LOGIN<br>
{<br>
"name": "String", // tutaj przekazac email ale parametr ma sie nazywac "name"<br> 
"password": "String",<br>
}
<br>/////
LIST
<br>
{<br>
"name": "String",<br>
"userId: Int,<br>
"arrayId" :0<br>
}<br>
////////////////////
<br>
CARD
<br>
{<br>
"listId" : Int,<br>
"name" : String,<br>
"content" : "String", <- moze byc null<br>
"description" : "String",<- moze byc null<br>
"complete" : Boolean,<- moze byc null<br>
"deadline" : "DateTime"<- moze byc null<br>
}<br>
        
    

