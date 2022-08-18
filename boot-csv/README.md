# **boot-csv**

This application connect with another application(boot-db-data)<br>
It takes input as csv file and provide output as success code and write data into the another csv file.<br>


This application internally call the boot-db-data application using endpoint(http://localhost:8082/customer/getCustomer/{customerId}).<br>
It takes customerId as input using path parameter and provide the complete information like customerId and name of customer as output.<br>

Endpoint for boot-csv application http://localhost:8084/uploadFile<br>
Refer customer.csv file for input to the application

Endpoint for boot-db-data application http://localhost:8084/uploadFile




