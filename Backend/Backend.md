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