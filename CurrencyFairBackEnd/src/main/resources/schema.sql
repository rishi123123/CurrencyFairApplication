CREATE TABLE `message` (
  `id` int(11) AUTO_INCREMENT,
  `userID` varchar(20) DEFAULT NULL,
  `currencyFrom` varchar(10) DEFAULT NULL,
  `currencyTo` varchar(10) DEFAULT NULL,
  `amount_sell` double DEFAULT NULL,
  `amount_buy` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `time_placed` datetime DEFAULT NULL,
  `originating_country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (id)
);