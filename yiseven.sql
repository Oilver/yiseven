/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : yiseven

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-10-09 19:19:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `balance` double(10,2) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '32278.00', '2019-09-19 18:10:03', '2019-09-19 18:10:05');

-- ----------------------------
-- Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(1) NOT NULL COMMENT '濞翠焦鎸夌猾璇茬€烽敍鍫熸暪閸忋儯鈧焦鏁崙鎭掆偓浣糕偓鐔绘嵍閿?',
  `value` double(10,2) NOT NULL,
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `balance` double(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('65', '雇人刷单', '', '1', '1500.00', '邓锦辉', '2019-10-08 14:35:40', '2019-10-08 14:35:40', '0.00');
INSERT INTO `record` VALUES ('66', '店铺开销', '12312', '1', '2222.00', '邓锦辉', '2019-10-09 19:16:53', '2019-10-09 19:16:53', '37278.00');
INSERT INTO `record` VALUES ('67', '雇人刷单', '123123', '1', '5000.00', '邓锦辉', '2019-10-09 19:17:31', '2019-10-09 19:17:31', '32278.00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) NOT NULL,
  `role` int(4) NOT NULL COMMENT '閻熸瑦甯熸竟?',
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('26', '邓锦辉', 'f715334e8ea49b97642a3c64ac3e406d', '543527507@qq.com', '18819258246', '1', '2019-10-08 14:33:57', '2019-10-08 14:33:57', '1');
