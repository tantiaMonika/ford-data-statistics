# ford-data-statistics

# About the application

This RESTFul API provides statistics for the daily stock prices of the Ford Motor Company over available data of 22 years.
It provides two types of data based on different requests.
1.Close rates over time
2.Average close over a period. The period can be either :Year, Month (within a given year), Day (within a given month)

# Loading data into application

At the time of application startup, data from CSV file "F.CSV" is loaded into H2 database automatically by SpringBoot, which in turn is used for further processing whenever a request for the same is received. The CSV file is placed in project resources folder.

# To access database 

1. Access the url:-
http://localhost:8080/h2-console
2. Change JDBC URL to jdbc:h2:mem:testdb
3. click connect.

# How to run the application

1. Checkout the application in Eclipse or any other IDE (JRE 1.8 is required)
2. Click on run as Java Application.

# Endpoints

1. To access data for Close rates over time,  access below endpoint on browser/PostMan:-
http://localhost:8080/fordDataStatistics/closeRates

2. To access data for Average close over a period, access below endpoint on browser/PostMan:-
http://localhost:8080/fordDataStatistics/avgClose?year={year}&month={month}&day={day}
with providing values for parameters specified between {}. 
for example:http://localhost:8080/fordDataStatistics/avgClose?year=1996&month=12&day=12
where year=1996, month=12 and date=12

*In above url, year is a mandetory parameter and month and date are optional parameters.*
