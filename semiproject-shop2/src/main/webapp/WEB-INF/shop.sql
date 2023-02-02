-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.18-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- shop 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `shop`;

-- 테이블 shop.auth_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `auth_info` (
  `auth_code` int(11) NOT NULL,
  `auth_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`auth_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `goods_code` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `cart_quantity` int(11) NOT NULL COMMENT '카트 물건 수량',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`,`customer_id`),
  KEY `FK_cart_customer` (`customer_id`),
  CONSTRAINT `FK_cart_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_cart_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.customer 구조 내보내기
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_code` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(100) NOT NULL,
  `customer_pw` varchar(100) NOT NULL COMMENT '반정규화',
  `customer_name` varchar(100) NOT NULL,
  `customer_phone` varchar(100) NOT NULL,
  `point` int(11) NOT NULL COMMENT '반정규화',
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`customer_code`),
  UNIQUE KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.customer_address 구조 내보내기
CREATE TABLE IF NOT EXISTS `customer_address` (
  `address_code` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`address_code`),
  KEY `FK__customer` (`customer_id`),
  CONSTRAINT `FK__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.emp 구조 내보내기
CREATE TABLE IF NOT EXISTS `emp` (
  `emp_code` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` varchar(100) NOT NULL,
  `emp_pw` varchar(100) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `active` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT 'Y 계정사용가능, N 계정사용불가',
  `auth_code` int(11) DEFAULT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`emp_code`),
  UNIQUE KEY `emp_id` (`emp_id`),
  KEY `FK_emp_auth_info` (`auth_code`),
  CONSTRAINT `FK_emp_auth_info` FOREIGN KEY (`auth_code`) REFERENCES `auth_info` (`auth_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods` (
  `goods_code` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) NOT NULL,
  `goods_price` int(11) NOT NULL,
  `soldout` enum('Y','N') NOT NULL COMMENT 'Y 품절',
  `emp_id` varchar(100) NOT NULL COMMENT '상품등록한 스텝ID',
  `hit` int(11) NOT NULL DEFAULT 0,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.goods_img 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods_img` (
  `goods_code` int(11) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `origin_name` varchar(100) NOT NULL,
  `content_type` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`goods_code`),
  CONSTRAINT `FK_goods_img_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_code` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(200) NOT NULL,
  `notice_content` text NOT NULL,
  `emp_id` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`notice_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `order_code` int(11) NOT NULL AUTO_INCREMENT,
  `goods_code` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `address_code` int(11) NOT NULL,
  `order_quantity` int(11) NOT NULL,
  `order_price` int(11) NOT NULL,
  `order_state` enum('결제','취소','배송중','배송완료','구매확정') NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`),
  KEY `FK_orders_goods` (`goods_code`),
  KEY `FK_orders_customer` (`customer_id`),
  KEY `FK_orders_customer_address` (`address_code`),
  CONSTRAINT `FK_orders_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_customer_address` FOREIGN KEY (`address_code`) REFERENCES `customer_address` (`address_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orders_goods` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.outid 구조 내보내기
CREATE TABLE IF NOT EXISTS `outid` (
  `id` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp() COMMENT '탈퇴일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='새로운 아이디 생성시 outid + emp + customer 존재하지 않는 아이디 생성가능';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.point_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `point_history` (
  `order_code` int(11) NOT NULL,
  `point_kind` enum('적립','사용') NOT NULL,
  `point` int(11) NOT NULL,
  `createdate` int(11) NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`,`point_kind`),
  CONSTRAINT `FK__orders` FOREIGN KEY (`order_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='포인트 이력테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.pw_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `pw_history` (
  `customer_id` varchar(100) NOT NULL,
  `pw` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`customer_id`,`pw`),
  CONSTRAINT `FK__pw_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='pw변경이력이 고객댕 최근 3개까지만';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.question 구조 내보내기
CREATE TABLE IF NOT EXISTS `question` (
  `question_code` int(11) NOT NULL AUTO_INCREMENT,
  `orders_code` int(11) NOT NULL,
  `category` enum('배송','반품','교환','기타') NOT NULL,
  `question_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`question_code`),
  KEY `FK_question_orders` (`orders_code`),
  CONSTRAINT `FK_question_orders` FOREIGN KEY (`orders_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.question_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `question_comment` (
  `comment_code` int(11) NOT NULL AUTO_INCREMENT,
  `question_code` int(11) NOT NULL,
  `comment_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`comment_code`),
  KEY `FK__question` (`question_code`),
  CONSTRAINT `FK__question` FOREIGN KEY (`question_code`) REFERENCES `question` (`question_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `order_code` int(11) NOT NULL,
  `review_memo` text NOT NULL,
  `createdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_code`),
  CONSTRAINT `FK_review_orders` FOREIGN KEY (`order_code`) REFERENCES `orders` (`order_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='orders테이블에서 order_state값이 구매확정이 되면 고객이 review 등록 가능';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shop.site__counter 구조 내보내기
CREATE TABLE IF NOT EXISTS `site__counter` (
  `counter_date` date NOT NULL DEFAULT curdate(),
  `couter_num` int(11) NOT NULL,
  PRIMARY KEY (`counter_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
