CREATE DATABASE `esock_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `address` (
  `address_id` bigint(11) NOT NULL,
  `user_profile_id` bigint(11) NOT NULL,
  `address_name` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `postcode` varchar(45) NOT NULL,
  `address_line_1` varchar(250) NOT NULL,
  `address_line_2` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `order_id` bigint(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `address_id` bigint(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `product_id` bigint(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `purchase` (
  `purchase_id` bigint(11) NOT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `product_id` bigint(11) NOT NULL,
  `order_id` bigint(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL,
  `user_profile_id` bigint(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_profile` (
  `user_profile_id` bigint(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
