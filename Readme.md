## Demo Api for ESG

### 1. Read CSV from directory & 2. Parse the CSV file

In the demo, you can find a class called CsvUtils. Inside this class a method called getDataFromCsv which takes a string as a parameter, in this case the file path to the csv stored in resources.

Using quite a common technique it reads the csv line by line, whilst doing so splitting the line it's on at each comma to indicate each piece of data on that line. This is then stored in an array and accessed to create a new customer object which I myself created.

A list of customers is returned from this method.

### 3. Send the data to a POST API to (4.) persist the data

Also found in the CsvUtils class, a method called sendDataToApi. This takes the list of customers from the first two steps as a parameter.

Using a restTemplate instance, I sent that data to a POST API found in the controller class, line by line as stated. I saved the data using a JPA repository and a h2 database using the inbuilt save method.

### 5. GET API for retrieving customer

I created an endpoint for retrieving a customer by their reference.

I passed the customer reference in the header of the request, which was sent to the service method. Here I called the repository and returned the customer if one was found. If not I threw an exception.


#### error handling

Error handling was done by a custom exception class to be used as a template. An instance of this class was then created when an error matching one I have thrown to one in the custom exception handler. See exception package. 

#### technologies used:

* spring boot
* junit5
* h2 database
* Jpa