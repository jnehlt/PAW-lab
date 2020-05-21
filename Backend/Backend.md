#Jak uruchomić
#### Baza danych
Aby uruchomić serwer należy uruchomić kontener dockera z postgreSQL<br>
W tym celu należy w terminalu wpisac:<br>
<code>docker run --name psServer -p 5423:5432 -e POSTGRES_PASSWORD=postgres -d postgres<br>
docker exec -it psServer bash<br>
psql -U postgres postgres<br>
CREATE DATABASE<br>
\q<br>
exit
</code>
<br>
Spowoduje to utworzenie kontera oraz bazy danych postgreSQL
<br>

#### Serwer
Należy zbudować projekt Backend<br>
<code>gradle build -x test<br></code>
oraz uruchomić<br>
<code>gradle run</code>