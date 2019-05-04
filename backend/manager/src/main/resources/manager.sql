/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : manager

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 05/05/2019 02:00:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `user` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `changeword` int(10) NOT NULL,
  `pwdmd5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('manager', 'manager', 666666, '1d0258c2440a8d19e716292b231e3190');
INSERT INTO `admin` VALUES ('wonder', 'wonder', 111111, '10cf1fdf6ad958eeffa9853f6885cec9');

-- ----------------------------
-- Table structure for number
-- ----------------------------
DROP TABLE IF EXISTS `number`;
CREATE TABLE `number`  (
  `number` int(11) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `type`(`type`) USING BTREE,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `type` (`type`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of number
-- ----------------------------
INSERT INTO `number` VALUES (11, 'book');
INSERT INTO `number` VALUES (10, 'chair');
INSERT INTO `number` VALUES (11, 'chalk');
INSERT INTO `number` VALUES (10, 'classroom');
INSERT INTO `number` VALUES (2000, 'computer');
INSERT INTO `number` VALUES (10, 'desktop');
INSERT INTO `number` VALUES (9, 'notepad');
INSERT INTO `number` VALUES (10, 'pen');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 'book');
INSERT INTO `type` VALUES (2, 'chair');
INSERT INTO `type` VALUES (3, 'chalk');
INSERT INTO `type` VALUES (4, 'classroom');
INSERT INTO `type` VALUES (5, 'computer');
INSERT INTO `type` VALUES (6, 'desktop');
INSERT INTO `type` VALUES (7, 'notepad');
INSERT INTO `type` VALUES (8, 'pen');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `changeword` int(10) NOT NULL,
  `pwdmd5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('kalicat', 'kalicat', 111111, '630d49e81fda986be775021a8db692cf');
INSERT INTO `user` VALUES ('manager', 'manager', 222222, '1d0258c2440a8d19e716292b231e3190');
INSERT INTO `user` VALUES ('tomcat', 'jerry', 111111, '30035607ee5bb378c71ab434a6d05410');

SET FOREIGN_KEY_CHECKS = 1;
