
@tag 
Feature: purchase order from Ecommerce website


Background:
Given I landed on Ecommerce site

@Regression
Scenario Outline: Positive test of Submitting the order

Given Logged in with username <username> with passowrd <password>
When I add product <productName> to cart
And Checkout the product <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

Examples:
|username               |password  |productName|
|babusvb95@gmail.com|Babu@1234 |ZARA COAT 3|


