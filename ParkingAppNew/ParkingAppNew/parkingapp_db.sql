# MySQL-Front 3.2  (Build 8.0)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

# Host: localhost    Database: parkingapp_db
# ------------------------------------------------------
# Server version 5.1.43-community

DROP DATABASE IF EXISTS `parkingapp_db`;
CREATE DATABASE `parkingapp_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `parkingapp_db`;

#
# Table structure for table area_admin_register_tbl
#

CREATE TABLE `area_admin_register_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_name` varchar(30) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(11) NOT NULL,
  `email_id` varchar(30) NOT NULL,
  `phone_number` varchar(30) NOT NULL,
  `lat` varchar(50) NOT NULL DEFAULT '0',
  `longitude` varchar(50) NOT NULL,
  `number_for_two_wheeler` varchar(11) NOT NULL,
  `two_wheeler_for_1hr` varchar(11) NOT NULL,
  `two_wheeler_after_1hr` varchar(11) NOT NULL,
  `number_for_four_wheeler` varchar(11) NOT NULL,
  `four_wheeler_for_1hr` varchar(11) NOT NULL,
  `four_wheeler_after_1hr` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

#
# Dumping data for table area_admin_register_tbl
#

INSERT INTO `area_admin_register_tbl` VALUES (1,'aa','aaa','aaa','a@g.com','9961811389','12232','','5','5','5','5','5','5');
INSERT INTO `area_admin_register_tbl` VALUES (6,'aldrin ','aldrin','aldrin','aldrin','123456','9.967015273232757','76.29683420062065','4','6','6','3','6','7');
INSERT INTO `area_admin_register_tbl` VALUES (7,'abc','abc','abc','abc@gmail.com ','9446046809','9.963582329716585','76.2949338555336','6','6','6','5','8','9');
INSERT INTO `area_admin_register_tbl` VALUES (8,'abcdefgh','anu','anu','anu@gmail.com','9567763727','9.964382780723788','76.29932831972839','9','10','5','5','20','10');
INSERT INTO `area_admin_register_tbl` VALUES (9,'asd','asd','asd','asd@gmail .com ','9446036809','9.968320614433042','76.29251886159182','4','5','6','8','6','6');
INSERT INTO `area_admin_register_tbl` VALUES (10,'zxc','zxc','zxc','zxc','7012113920','0.0','0.0','7','8','9','5','7','8');
INSERT INTO `area_admin_register_tbl` VALUES (11,'qwd','qwd','qwd','qwd','2467898654','9.970939206240997','76.29804722964764','6','7','3','5','7','8');


#
# Table structure for table bank_tbl
#

CREATE TABLE `bank_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(20) NOT NULL,
  `cvv_no` int(11) NOT NULL DEFAULT '0',
  `exp_date` varchar(11) NOT NULL,
  `balance` varchar(11) NOT NULL DEFAULT '0',
  `card_no` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Dumping data for table bank_tbl
#

INSERT INTO `bank_tbl` VALUES (1,'anoop',222,'Jan/2020','16000','1234');


#
# Table structure for table booking_tbl
#

CREATE TABLE `booking_tbl` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `pa_id` varchar(11) NOT NULL,
  `user_id` varchar(11) NOT NULL,
  `booking_time` varchar(11) NOT NULL,
  `for_time` varchar(11) NOT NULL,
  `slot_type` varchar(20) NOT NULL,
  `status_of_parking_area_admin` varchar(30) NOT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Dumping data for table booking_tbl
#

INSERT INTO `booking_tbl` VALUES (1,'1','1','12','6','2','a');


#
# Table structure for table feedback_tbl
#

CREATE TABLE `feedback_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `pa_id` int(11) NOT NULL DEFAULT '0',
  `feedback` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table feedback_tbl
#



#
# Table structure for table login_tbl
#

CREATE TABLE `login_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL,
  `password` varchar(11) NOT NULL,
  `user_type` varchar(25) NOT NULL,
  `status` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

#
# Dumping data for table login_tbl
#

INSERT INTO `login_tbl` VALUES (2,'admin','admin','admin','A');
INSERT INTO `login_tbl` VALUES (13,'kripa','kripa','PARK EM USER','A');
INSERT INTO `login_tbl` VALUES (14,'anu','anu','PARK EM USER','P');


#
# Table structure for table parking_tbl
#

CREATE TABLE `parking_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) NOT NULL,
  `check_in_time` varchar(11) NOT NULL,
  `check_out_time` varchar(11) NOT NULL,
  `amount` varchar(11) NOT NULL,
  `slot_type` varchar(30) NOT NULL,
  `booking_id` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table parking_tbl
#



#
# Table structure for table user_register_tbl
#

CREATE TABLE `user_register_tbl` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email_id` varchar(30) NOT NULL,
  `phone_number` varchar(21) NOT NULL,
  `balance` varchar(11) NOT NULL,
  `qrcode_path` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

#
# Dumping data for table user_register_tbl
#

INSERT INTO `user_register_tbl` VALUES (7,'kripa','kripa','krip@gmail.com','9567673727','0','QrCode\\kripa\\QRCode.png');
INSERT INTO `user_register_tbl` VALUES (8,'anu','anu','anu','9446046806','0','QrCode\\anu\\QRCode.png');


#
# Table structure for table wallet_tbl
#

CREATE TABLE `wallet_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `balance` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table wallet_tbl
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
