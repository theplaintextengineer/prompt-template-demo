-- Drop tables if they exist
DROP TABLE IF EXISTS account_transaction;
DROP TABLE IF EXISTS bank_account;
DROP TABLE IF EXISTS app_user;

-- AppUser table
CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- BankAccount table
CREATE TABLE bank_account (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    balance REAL NOT NULL,
    CONSTRAINT fk_bankaccount_user FOREIGN KEY (user_id) REFERENCES app_user(id)
);

-- AccountTransaction table
CREATE TABLE account_transaction (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    bank_account_id BIGINT NOT NULL,
    amount REAL NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_transaction_account FOREIGN KEY (bank_account_id) REFERENCES bank_account(id)
);

-- Reset sequences to start from 10000
ALTER SEQUENCE app_user_id_seq RESTART WITH 10000;
ALTER SEQUENCE bank_account_id_seq RESTART WITH 10000;
ALTER SEQUENCE account_transaction_id_seq RESTART WITH 10000;