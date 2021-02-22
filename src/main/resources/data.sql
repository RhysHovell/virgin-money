DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATE NOT NULL,
    vendor varchar(45) NOT NULL,
    type varchar(20) NOT NULL,
    amount DECIMAL NOT NULL,
    category varchar(30) DEFAULT NULL
);

INSERT INTO transactions (transaction_date, vendor, type, amount, category) VALUES
    ('2020-11-04','Morrisons','card','10.40','Groceries'),
    ('2020-10-28','CYBG','direct debit','600','MyMonthlyDD'),
    ('2020-10-28','PureGym','direct debit','40','MyMonthlyDD'),
    ('2020-10-01','M&S','card','5.99','Groceries'),
    ('2020-09-30','McMillan','internet','10', NULL);