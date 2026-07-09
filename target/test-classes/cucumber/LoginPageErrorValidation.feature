@tag 
Feature: Error Validations

@ErrorValidation
Scenario Outline: Validate the Logi Page Errors
Given I landed on Ecommerce site
When  Logged in with username <username> with passowrd <password>
Then "Incorrect email or password." Error Message Should display


Examples:
|username               |password  |
|babusvb95@gmail.com    |Babu@123 |