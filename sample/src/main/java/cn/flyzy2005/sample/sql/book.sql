/*
Navicat MySQL Data Transfer

Source Server         : fly
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : gzgdm

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-05-04 16:32:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '馆藏数量',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='图书表';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1000', 'Java程序设计', '10');
INSERT INTO `book` VALUES ('1001', '数据结构', '9');
INSERT INTO `book` VALUES ('1002', '设计模式', '10');
INSERT INTO `book` VALUES ('1003', '编译原理', '10');
