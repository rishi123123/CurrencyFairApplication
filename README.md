## CURRENCY FAIR - MESSAGE DASHBOARD SYSTEM

The Project is built using Java 8 on the Spring-Boot FrameWork for the Backend and React.js for the Front End Application.

### BACKEND - SPRING-BOOT
AWS:
	The backend built war is deployed on AWS Elastic BeanStalk. URL : http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/
	example api call : http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/consumeMessage

	The Backend is built on the SpringBoot-Framework with Java 8. The application accepts message in the format 
	
	```json
	{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP",
	"amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471,
	"timePlaced" : "24-JAN-18 10:27:44", "originatingCountry" : "FR"}
	```

	at URL : http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/consumeMessage as a POST HTTP Method.

	The application returns a Success Message saying : "Message was successfully inserted".

	The Backend Data is processed to populate the page with 
	1. Messages Table with filters for Currency from (example: EUR), Currency To (example : INR), Originating Country (example = FR)
	2. Messages In Last 60 Second Window Table with the same filters as above. This table shows messages in and its statistics in the 
	last 60 Second time frame. It also shows the statistics for the time window.
	3. Section Graphs.
	3 Graphs are displayed on the dashboard. On Click of the Update Graph button the graphs are loaded with data from api calls to the 
	backed and displayed.
	Graph - Count Line Chart
		The count line chart shows the amount of transactions against the currency. 
	Graph - Pie chart
		The Pie chart shows a representation of the ratio of the countries from which the transactions are originating.
	Graph - Line Chart
		The line chart shows the amount of transactions per year on a line graph which makes it very easy to visualize the change in 
		volume of transactions.

AWS:
	The backend built war is deployed on AWS Elastic BeanStalk. URL : http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/
	example api call : http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/consumeMessage

### Front End - React Application
	The front end is built using React.js.

	AWS:
		The application is hosted on AWS S3 buckets. Built and deployed using npm. npm run build and then npm run deploy will 
		automatically deploy the changes to the S3 bucket.
		URL : http://currencyfairapp.com.s3-website-eu-west-1.amazonaws.com/

	1. Messages Table with filters for Currency from (example: EUR), Currency To (example : INR), Originating Country (example = FR)
	2. Messages In Last 60 Second Window Table with the same filters as above. This table shows messages in and its statistics in 
	the last 60 Second time frame. It also shows the statistics for the time window.
	3. Section Graphs.
	3 Graphs are displayed on the dashboard. On Click of the Update Graph button the graphs are loaded with data from api calls 
	to the backed and displayed.
	Graph - Count Line Chart
		The count line chart shows the amount of transactions against the currency. 
	Graph - Pie chart
		The Pie chart shows a representation of the ratio of the countries from which the transactions are originating.
	Graph - Line Chart
		The line chart shows the amount of transactions per year on a line graph which makes it very easy to visualize the change 
		in volume of transactions.


