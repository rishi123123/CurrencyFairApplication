INSERT INTO `message` ( `userid`,`currencyFrom`, `currencyTo`, `amount_sell`, `amount_buy`, `rate`, `time_placed`, `originating_country`)
VALUES
	('1234','EUR', 'GBP', 12.34, 34.5, 0.7, '2017-06-09 11:54:07', 'FR'),
	('1235','EUR', 'GBP', 10.34, 134.5, 0.7, '2017-07-10 11:53:07', 'FR'),
	('1234','GBP', 'EUR', 12.34, 34.5, 0.7, '2017-06-09 11:54:07', 'UK'),
	('1235','GBP', 'EUR', 50.34, 534.5, 0.7, '2018-07-10 11:53:07', 'UK'),
	('1234','EUR', 'INR', 22.34, 24.5, 0.7, '2016-06-09 11:54:07', 'IT'),
	('1235','EUR', 'INR', 100.34, 1340.5, 0.7, '2016-07-10 11:53:07','IT'),
	('1234','INR', 'EUR', 12.34, 34.5, 0.7, '2017-06-09 11:54:07', 'IND'),
	('1235','INR', 'EUR', 10.34, 134.5, 0.7, '2017-07-10 11:53:07', 'IND');
