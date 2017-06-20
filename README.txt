Project Name : bestDeals
OS: Mac
Technologies used : java 8, Spring boot, Spring Data, json, FasterXML/jackson, Mockito, H2 in-memory DB and other spring boot starter dependencies. 

Build and deploy in Command-Line:
1) Build the maven project using : mvn clean install
2) The jar file will be created in the 'target' folder (bestdeals-0.0.1-SNAPSHOT.jar).
3) If you need to run the jar file from the command-line, please type the command as follows. and provide the port number you would like to use
	java -jar -Dserver.port=<port number> target/bestdeals-0.0.1-SNAPSHOT.jar

Alternatively you can use your desired IDE ( i have used IntelliJ) and run the spring boot application class (BestDealsApplication.java) 

Currency converter API:
I have used https://forex.1forge.com/1.0.1/quotes API for currency conversion.
Junits tests are added for controller and service.

API/s :

If you need to test the API/s externally, use 'Advanced REST client' (Chrome) or 'Postman' or any desired test client.

APIs are as follows:

1) '/deals' (POST) 
	This api will create a deals record. Please send the json with the desired inputs. The sample input and output json/s are as follows.
	
input json :
{
"principle": 3400,
"rate": 4,
"time": 3,
"interestType": "Compound",
"compoundingPeriod": 2,
"fromCurrency": "GBP",
"toCurrency": "USD"
}

Output json:

{
"principle": 3400,
"rate": 4,
"time": 3,
"dealAmount": 3154935.582,
"dealInterestAmount": 3150607.824,
"baseCurrencyAmount": 2478600,
"baseCurrencyInterestAmount": 2475200,
"interestType": "Compound",
"compoundingPeriod": 2,
"fromCurrency": "GBP",
"toCurrency": "USD",
"createdOn": {
"hour": 0,
"minute": 42,
"second": 18,
"nano": 810000000,
"dayOfYear": 171,
"dayOfWeek": "TUESDAY",
"month": "JUNE",
"dayOfMonth": 20,
"year": 2017,
"monthValue": 6,
"chronology": {
"id": "ISO",
"calendarType": "iso8601"
}
},
"fxRate": 1.27287,
"id": 2
}

2) '/deals/<id>'  (GET) 	
	This API will get the deal by the id provided by the user.
	
3) '/deals'  (GET) 
	This API will retrieve all the deals available.


