
/*==============================================================*/
/* Table: ucenter_user_log                                      */
/*==============================================================*/
create table ucenter_user_log
(
  user_log_id          int unsigned not null auto_increment comment '编号',
  uid              CHAR comment '用户编号',
  content              varbinary(100) comment '内容',
  ip                   varchar(20) comment '操作IP地址',
  agent                varbinary(200) comment '操作环境',
  create_time          timestamp default CURRENT_TIMESTAMP comment '操作时间',
  primary key (user_log_id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';


--
-- 表的结构 `user`
-- 用户表
--
CREATE TABLE IF NOT EXISTS `user`(
  `uid` CHAR NOT NULL DEFAULT '' COMMENT '用户id',
  `name` CHAR(16) NOT NULL DEFAULT '' COMMENT '昵称',
  `sex` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '用户性别',
  `birthday` timestamp NOT NULL DEFAULT '0000-00-00' COMMENT '生日',
  `login_frequency` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '登录次数',
  `register_ip` CHAR(20) NOT NULL DEFAULT '0' COMMENT '注册ip',
  `register_time` CHAR(16) NOT NULL DEFAULT '0000-00-00 00:00' COMMENT '注册时间',
  `last_login_ip` timestamp NOT NULL DEFAULT '0' COMMENT '最后登录ip',
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00' COMMENT '最后登录时间',
  `signature` varchar(300) comment '个性签名',
  `real_name` varchar(20) comment '真实姓名',
  `question` varchar(100) comment '帐号安全问题',
  `answer` varchar(100) comment '帐号安全答案',
  `reserve_3` INT(20) UNSIGNED DEFAULT '0',
  `reserve_4` INT(20) UNSIGNED DEFAULT '0',
  `reserve_5` INT(20) UNSIGNED DEFAULT '0',
  `reserve_6` INT(20) UNSIGNED DEFAULT '0',
  `reserve_7` INT(20) UNSIGNED DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';


--
-- 表的结构 `user_ucenter`
-- 用户中心表
--
CREATE TABLE `ucenter_user` (
  `uid` CHAR COMMENT '用户id',
  `username` varchar(16) COMMENT '用户名',
  `password` varchar(32) comment '密码(MD5(密码+盐))',
  `salt` varchar(32) comment '盐',
  `email` varchar(32) COMMENT '邮箱',
  `phone` varchar(15) COMMENT '手机号',
  `state` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '登录状态',
  `register_ip` INT(20) COMMENT '注册ip',
  `register_time` datetime COMMENT '注册时间',
  `last_login_ip` INT(20)  COMMENT '最后登录ip',
  `last_login_time` datetime COMMENT '最后登录时间',
  primary key(`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户中心表 ';

--
-- OAuth登录表 `login_oauth`
--
CREATE TABLE `ucenter_user_oauth` (
  user_oauth_id        int unsigned not null auto_increment comment '编号',
  uid              CHAR not null comment '帐号编号',
  oauth_id             int unsigned not null comment '认证方式编号',
  open_id              varbinary(50) not null comment '第三方ID',
  status               tinyint(4) unsigned comment '绑定状态(0:解绑,1:绑定)',
  create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
  primary key (user_oauth_id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='认证方式表' ;

create table ucenter_oauth
(
  oauth_id             int unsigned not null auto_increment comment '编号',
  name                 varchar(20) comment '认证方式名称',
  primary key (oauth_id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='认证方式表';







