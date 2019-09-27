/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : yiseven

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-09-27 16:02:18
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
INSERT INTO `account` VALUES ('1', '47058.33', '2019-09-19 18:10:03', '2019-09-19 18:10:05');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '测试1', '刷单用了', '1', '500.25', '邓锦辉', '2019-09-19 18:12:55', '2019-09-19 18:12:55');
INSERT INTO `record` VALUES ('2', '测试2', '额外收入', '0', '300.00', '邓锦辉', '2019-09-19 18:13:39', '2019-09-19 18:13:39');
INSERT INTO `record` VALUES ('51', '其他', '收入1000块。', '0', '1000.00', '123', '2019-09-25 23:20:09', '2019-09-25 23:20:09');
INSERT INTO `record` VALUES ('52', '拿货开销', 'qwe', '1', '10.00', '123', '2019-09-25 23:26:25', '2019-09-25 23:26:25');
INSERT INTO `record` VALUES ('53', '其他', '', '0', '20000.00', '123', '2019-09-25 23:30:25', '2019-09-25 23:30:25');
INSERT INTO `record` VALUES ('57', '拿货开销', 'asd', '1', '20.00', '123', '2019-09-25 23:33:23', '2019-09-25 23:33:23');
INSERT INTO `record` VALUES ('58', '店铺开销', '123', '1', '0.00', '123', '2019-09-25 23:34:42', '2019-09-25 23:34:42');
INSERT INTO `record` VALUES ('59', '拿货开销', '123', '1', '2222.00', '123', '2019-09-25 23:35:14', '2019-09-25 23:35:14');
INSERT INTO `record` VALUES ('60', '拿货开销', '12312', '1', '0.67', '123', '2019-09-25 23:44:16', '2019-09-25 23:44:16');
INSERT INTO `record` VALUES ('61', '拿货开销', '232', '1', '0.50', '123', '2019-09-25 23:46:11', '2019-09-25 23:46:11');
INSERT INTO `record` VALUES ('62', '其他', '额外收入', '0', '15000.00', '123', '2019-09-26 00:00:45', '2019-09-26 00:00:45');
INSERT INTO `record` VALUES ('63', '店铺开销', '', '1', '500.25', '123', '2019-09-26 16:33:08', '2019-09-26 16:33:08');
INSERT INTO `record` VALUES ('64', '拿货开销', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '1', '888.00', '123', '2019-09-27 11:40:15', '2019-09-27 11:40:15');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) NOT NULL,
  `role` int(4) NOT NULL COMMENT '閻熸瑦甯熸竟?',
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

INSERT INTO `user` VALUES ('13', '123', '123', '123@qq.com', '123', '1', '2019-09-22 16:36:25', '2019-09-22 16:36:25', '1');
INSERT INTO `user` VALUES ('14', '999', '999', '999@qq.com', '999', '0', '2019-09-22 18:57:06', '2019-09-22 18:57:06', '1');
INSERT INTO `user` VALUES ('15', '666', '666', '666@qq.com', '666', '0', '2019-09-24 22:29:34', '2019-09-24 22:29:34', '1');
INSERT INTO `user` VALUES ('16', '111', 'string', 'string', 'string', '0', '2019-09-26 18:25:34', '2019-09-26 18:25:34', '1');
INSERT INTO `user` VALUES ('18', 'aaa', 'string', 'string', '123444', '0', '2019-09-26 19:47:12', '2019-09-26 19:47:12', '1');
