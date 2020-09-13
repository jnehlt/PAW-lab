#Jak uruchomić
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


####Dokumentacja API
<h3>Dodanie uzytkownika</h3><br>
POST
<code>.../users/sign</code><br>
<b>parametry</b><br>
<code>
    {<br>
       "firstName": "String",<br>
        "lastName": "String",<br>
        "password": "String",<br>
        "email": "String"<br>
    }
</code>
<br>
///////////////////////////////////////////////////////////////////////////
<h3>Logowanie uzytkownika</h3><br>
POST
<code>.../users/login</code><br>
<b>parametry</b><br>
<code>
    {<br>
       "name": "String",<br> - email
        "password": "String",<br>
    }
</code>
<br>
<b>Odpowiedź</b>
Token i  HTTPSTATUS 200
<br>
///////////////////////////////////////////////////////////////////////////
<h3>Aktualizacja uzytkownika</h3><br>
PUT
<code>.../users</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
    {<br>
       "firstName": "String",<br>
        "lastName": "String",<br>
        "password": "String",<br>
        "email": "String"<br>
    }
</code>
<br>
///////////////////////////////////////////////////////////////////////////
<h3>Usunięcie uzytkownika</h3><br>
DELETE<code>.../users</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>
<b>parametry</b><br>
<code>
    {<br>
       "firstName": "String",<br>
        "lastName": "String",<br>
        "password": "String",<br>
        "email": "String"<br>
    }
</code>
<br>
///////////////////////////////////////////////////////////////////////////
<h3>Wylogowanie uzytkownika</h3><br>
GET<code>.../users/logout</code><br>
<b>nagłówek<b><br>
<code>"Authorization" : "Bearer token....."</code><br>

