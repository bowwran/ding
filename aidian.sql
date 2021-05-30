/*
Navicat MySQL Data Transfer

Source Server         : 11
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : aidian

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-05-23 22:27:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `authority` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '爱点点', '123', '0');
INSERT INTO `admin` VALUES ('2', '苏', '123', '0');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `typeid` int(4) unsigned DEFAULT NULL,
  `burden` varchar(50) DEFAULT NULL,
  `brief` varchar(500) DEFAULT NULL,
  `price` float unsigned DEFAULT NULL,
  `sums` int(4) unsigned DEFAULT '0',
  `price1` float unsigned DEFAULT NULL,
  `sums1` int(4) unsigned DEFAULT '0',
  `imgpath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('12', '招牌烤鸡', '10', '鸡肉', '暂无', '26', '0', '23', '0', 'img/m_fenzhengrou.gif');
INSERT INTO `menus` VALUES ('14', '苦瓜虾仁', '2', '苦瓜、虾', '暂无', '26', '0', '24', '4', 'img/m_tangcupaigu.gif');
INSERT INTO `menus` VALUES ('15', '华夫饼', '1', '面粉、糖', '暂无', '15', '0', '12', '4', 'img/m_xianroucaifan.gif');
INSERT INTO `menus` VALUES ('17', '盖浇饭', '1', '米饭、肉酱', '暂无', '25', '0', '21', '1', 'img/m_wuxianglvrou.gif');
INSERT INTO `menus` VALUES ('18', '煎饼果子', '1', '青菜、火腿', '暂无', '8', '0', '6', '1', 'img/m_huanggualapi.gif');
INSERT INTO `menus` VALUES ('19', '蜜汁鸡翅', '11', '鸡腿、糖、盐', '暂无', '38', '0', '32', '1', 'img/m_shuizhuyu.gif');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `times` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('6', '新增菜单《苦瓜虾仁》', '苦瓜虾仁超级好吃。再挑食的小朋友都无法拒绝酸甜的口味，吃光排骨，再用糖醋汁拌米饭，今天的饭量见长。', '2021-05-20 13:49:40');
INSERT INTO `notice` VALUES ('7', '本店特色《盖浇饭》', '记得小时候每每妈妈做咸肉菜饭，我都要吃上二大碗，那个香啊那个好吃啊，真的不知道怎样来形容。吃过的朋友，大家都懂的，呵呵!11', '2021-05-20 13:53:39');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `userid` int(4) unsigned DEFAULT NULL,
  `menuid` int(4) unsigned DEFAULT NULL,
  `menusum` int(4) unsigned DEFAULT NULL,
  `times` varchar(20) DEFAULT NULL,
  `delivery` int(4) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('16', '2', '12', '2', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('17', '1', '14', '1', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('19', '4', '15', '2', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('23', '4', '14', '1', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('24', '4', '17', '1', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('25', '2', '15', '1', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('27', '2', '18', '1', '2021-05-19 13:16:28', '1');
INSERT INTO `orders` VALUES ('28', '2', '19', '1', '2021-05-19 13:16:28', '0');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '凉拌菜');
INSERT INTO `types` VALUES ('2', '炒菜');
INSERT INTO `types` VALUES ('6', '炒饭');
INSERT INTO `types` VALUES ('10', '蒸菜');
INSERT INTO `types` VALUES ('11', '川菜');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `age` int(4) unsigned DEFAULT NULL,
  `card` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `type` int(4) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('2', '222', '222', '2221', '女', '241', '211111111111111111', '惠州市', '13988888888', '123@163.com', '110044', '0');
INSERT INTO `users` VALUES ('3', 'sunday', '123', '张三', '男', '26', '4222222222222222', '惠城区', '13901001111', '13901001111@139.com', '101000', '0');
INSERT INTO `users` VALUES ('4', '1', '1', '1', '男', '1', '1', '1', '1', '1', '1', '0');
