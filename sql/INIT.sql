# User table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                                `phone` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID，手机号码',
                                `nickname` varchar(255) NOT NULL,
                                `password` varchar(32) DEFAULT NULL,
                                `avatar` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
                                `register_date` datetime DEFAULT NULL COMMENT '注册时间',
                                PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` VALUES ('15000000000', 'user0', 'user0p', null, '2019-01-09 17:08:16');

# Goods table
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品Id',
                                 `name` varchar(16) DEFAULT NULL COMMENT '商品名称',
                                 `title` varchar(64) DEFAULT NULL COMMENT '商品标题',
                                 `img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
                                 `detail` longtext COMMENT '商品的详情介绍',
                                 `price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
                                 `stock` int(11) DEFAULT NULL COMMENT '库存数量',
                                 `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
                                 `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `goods` VALUES ('1', 'iphoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '8765.00', '0', '2021-12-04 21:51:23', '2022-12-31 21:51:27');
INSERT INTO `goods` VALUES ('2', '华为Meta9', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/meta10.png', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00', '0', '2017-12-04 21:40:14', '2022-12-31 21:51:27');
INSERT INTO `goods` VALUES ('3', 'iphone8', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphone8.png', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '5589.00', '100', '2017-12-04 21:40:14', '2022-12-31 21:51:27');
INSERT INTO `goods` VALUES ('4', '小米6', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/mi6.png', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00', '10000', '2017-12-04 21:40:14', '2022-12-31 21:51:27');

# Order
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_phone` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
                                 `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址ID',
                                 `status` tinyint(4) DEFAULT '0' COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
                                 `price` decimal(10,2) DEFAULT '0.00' COMMENT '订单总价',
                                 `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
                                 `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;