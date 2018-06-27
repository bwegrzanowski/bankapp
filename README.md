# Simple BankingApp for managing bank accounts\
Entities:\
-User (name, surname, email, bank account, password)\
-BankAccount(account number, balance, money available to withdraw, currency, user)

Features:\
-active session 10 minutes/1 hour (depends on checkbox 'Remember me')\
-repositories for entities and handling it/obtaining from database\
-logging without saving user's password in session or cookie\
-logging out\
-authentication filter\
-registering new users with random account number\
-activation mail being sent authomatically after registration\
-once url in activation mail be clicked, it activates account and redirects to your account\
-you cannot log in until you activate account\
-after each action redirection to information page