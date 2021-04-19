# Mike's Gmail Email Service

## Functionality

This Email service allows you to connect with a Gmail email account by selecting one of the defined endpoints. It offers
4 elements of functionality listed below. I made the decision to focus on injecting variables defined in the
application.yml file into the controller methods. This means there are no main code changes, and anyone can use it by
simply adjusting the values in the application.yml file. 

- Read Single Email (provide a subject to search for) - /read-single-email
- Read All Emails - /read-emails
- Send Email - /send-email
- Delete Email (based on subject you provide) - /delete-email

## Instructions for usage:

- application.yml - enter your gmail address and password on lines 3,4,11,12,22,23 respectively
- application.yml - line 27 enter the email subject you would like to delete from your inbox, this will be used when
  calling the /delete-email endpoint
- application.yml - line 28 enter the email subject you would like to search for, this will be used when searching for a
  subject in when calling the /the read-single-email endpoint

## Running the controller unit tests:

- Replace all variables which refer to userName and password with your own personal details. Provide a subject to
  delete (subjectToDelete), a subject to search for (subjectToFind) and then run the test cases. 

## Improvements to make:

- Use mocking framework such as Mockito to mock each method call rather than call the controller method in the unit
  tests
