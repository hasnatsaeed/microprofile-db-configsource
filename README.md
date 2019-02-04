# microprofile-db-configsource

Create the required database scheme by running the db script **create-table.sql** under resources folder

Now run the following maven commands

```
mvn clean install
```
This will build the war file (**microprofile-db-configsource-1.0-SNAPSHOT.war**).
```
mvn dependency:copy@copy-payara-micro
```
This will download (**payara micro 5.184**) server and copy it to the application's target folder
```
mvn exec:exec@payara-uber-jar
```
This will create the required Uber jar (**microprofile-db-configsource-1.0-SNAPSHOT.jar**) and place it under the target folder of the application.

Now access the application's target folder via command line and run the following command:
```
java -jar .\microprofile-db-configsource-1.0-SNAPSHOT.jar
```
The above command will start the payara micro server and also deploy **microprofile-db-configsource-1.0-SNAPSHOT.war** onto it.
Once the server starts we can access the endpoint as:
```
http://<HOST>:8085/microprofile-db-configsource-1.0-SNAPSHOT/rest/api/test
```
The value of **HOST** is logged in the command line when the payara micro server starts.

Happy C0ding!! :)
