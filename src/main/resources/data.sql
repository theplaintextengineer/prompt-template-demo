INSERT INTO app_user (name) VALUES ('Ankit'); 
INSERT INTO app_user (name) VALUES ('Shreya');
INSERT INTO app_user (name) VALUES ('Rohan'); 
INSERT INTO app_user (name) VALUES ('Priya'); 
INSERT INTO app_user (name) VALUES ('Neha');  

INSERT INTO bank_account (user_id, balance) VALUES (10000, 45000.75);   
INSERT INTO bank_account (user_id, balance) VALUES (10000, 120000.00);  

INSERT INTO bank_account (user_id, balance) VALUES (10001, 80000.50);   

INSERT INTO bank_account (user_id, balance) VALUES (10002, 250000.00);  
INSERT INTO bank_account (user_id, balance) VALUES (10002, 15000.00);   

INSERT INTO bank_account (user_id, balance) VALUES (10003, 60000.00);  

INSERT INTO bank_account (user_id, balance) VALUES (10004, 95000.25);   

INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10000, 10000, 50000.00, 'CREDIT'); 
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10000, 10000, 15000.00, 'DEBIT');  
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10000, 10001, 20000.00, 'CREDIT'); 

INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10001, 10002, 5000.00, 'DEBIT');   
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10001, 10002, 1200.00, 'CREDIT');  

INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10002, 10003, 100000.00, 'CREDIT');
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10002, 10004, 8000.00, 'DEBIT');   

INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10003, 10005, 10000.00, 'CREDIT'); 
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10003, 10005, 2000.00, 'DEBIT');   

INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10004, 10006, 95000.00, 'CREDIT'); 
INSERT INTO account_transaction (user_id, bank_account_id, amount, type) VALUES (10004, 10006, 12000.00, 'DEBIT');   