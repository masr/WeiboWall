
--
-- 表的结构 `weibo`
--

CREATE TABLE `weibo` (
      `weibo_id` int(10) unsigned NOT NULL auto_increment,
      `date` int(10) unsigned NOT NULL,
      `telephone` varchar(45) NOT NULL,
      `user` varchar(45) NOT NULL,
      `content` text NOT NULL,
      PRIMARY KEY  (`weibo_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=34 ;

--
-- 导出表中的数据 `weibo`
--

INSERT INTO `weibo` VALUES(1, 13232, '12312312', '小毛', '高云加油！！！');
INSERT INTO `weibo` VALUES(2, 321213123, '23213123', '李德佳', '高运你是最强的！！哇哈哈哈哈哈！！！');
INSERT INTO `weibo` VALUES(3, 3123, '32121331132', '阿萨', '台湾妹好漂亮啊！！！');
INSERT INTO `weibo` VALUES(6, 1305342437, '15151810907', 'lidejia', 'ok');
INSERT INTO `weibo` VALUES(7, 1305342738, '+8615151810907', '李德佳', '加油！');
INSERT INTO `weibo` VALUES(8, 1305343032, '+8615195806337', '小毛', '哈哈');
INSERT INTO `weibo` VALUES(9, 1305343251, '+8615195805890', '歪滴', '啦啦');
INSERT INTO `weibo` VALUES(13, 1305343593, '+8615151810907', '李德佳', '加油');
INSERT INTO `weibo` VALUES(15, 1305343822, '+8615151810907', '李德佳', '你好');
INSERT INTO `weibo` VALUES(16, 1305343824, '+8615195805890', '歪滴', '啦啦啦');

