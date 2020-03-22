SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (2, 'lisi', '123456', '李四', 20, 'lisi@qq.com');
INSERT INTO `tb_user` VALUES (3, 'wangwu', '123456', '王五', 19, 'wangwu@qq.com');
INSERT INTO `tb_user` VALUES (4, 'zhaoiu', '123456', '赵六', 21, 'zhaoliu@qq.com');
INSERT INTO `tb_user` VALUES (5, 'sunqi', '123456', '孙七', 22, 'sunqi@qq.com');
INSERT INTO `tb_user` VALUES (12, '曹操1', '123456', NULL, 98, 'caocao@qq.com');
INSERT INTO `tb_user` VALUES (15, 'liubei', '123456', '刘备', 30, 'liubei@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
