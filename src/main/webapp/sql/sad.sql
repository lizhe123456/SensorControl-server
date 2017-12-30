CREATE TABLE zlcm_user(
	`uid` INT(12) UNSIGNED auto_increment,
	`username` VARCHAR(32),
	`code` VARCHAR(6),
	`password` VARCHAR(64),
	`salt` VARCHAR(32),
	`state` TINYINT(4) DEFAULT 0 COMMENT '登录状态',
	`locked` TINYINT(4) DEFAULT 0 COMMENT '锁定（0正常，1锁定）',
	`register_ip` VARCHAR(32),
	`register_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`last_login_ip` VARCHAR(32),
	`last_login_time` TIMESTAMP NOT NULL,
	PRIMARY KEY (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO zlcm_user(`uid`,`username`,`password`) VALUES(10,'lizhe','123456')
INSERT INTO zlcm_user_details(`uid`,`nickname`,`avatar`,real_name,idCrad,phone,sex) VALUES(10,'李哲','123456','李哲','420202020200305120','17688943972',0)

CREATE TABLE zlcm_user_details(
	`uid` INT(12) UNSIGNED NOT NULL,
	`nickname` VARCHAR(50),
	`avatar` VARCHAR(100),
	`real_name` VARCHAR(20),
	`idCrad` VARCHAR(32),
	`phone` VARCHAR(11),
	`sex` TINYINT(4) DEFAULT 0 COMMENT '0男，1女',
	`birthday` datetime,
	`email` VARCHAR(32),
	`stor_id` INT(20),
	`credit` INT(5) NOT NULL DEFAULT 100,
	PRIMARY KEY (`uid`),
	CONSTRAINT `USER_ID` FOREIGN KEY (`uid`) REFERENCES `zlcm_user` (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_store(
	`sid` INT(20) UNSIGNED auto_increment,
	`name` VARCHAR(50) COMMENT '公司全称',
	`address` VARCHAR(50) COMMENT '地址',
	`organization` VARCHAR(20) COMMENT '组织机构',
	`iphone` VARCHAR(11),
	`legal_person` VARCHAR(10) COMMENT '法人',
	`business_license` VARCHAR(100) COMMENT '企业工商营业执照',
	`state` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '0: 待审, 1: 通过'
	PRIMARY KEY(`sid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_advert(
	`aid` INT(20) UNSIGNED auto_increment,
	`did` INT(12) UNSIGNED,
	`uid` INT(12) UNSIGNED,
	`advert_img` VARCHAR(100) NOT NULL COMMENT '海报',
	`text_info` VARCHAR(500) NOT NULL COMMENT '文字信息',
	`start_time` datetime NOT NULL COMMENT '开始时间',
	`duration` BIGINT NOT NULL COMMENT '持续时间',
	`state` TINYINT(4) NOT NULL COMMENT '0:待审,1:审核成功,2:审核失败',
	`hits` INT(20) NOT NULL COMMENT '点击量',
	`recommend` TINYINT(4) NOT NULL COMMENT '0:默认 1:推荐',
	`iphone` VARCHAR(11) NOT NULL COMMENT '联系方式',
	`sid` INT(20) UNSIGNED COMMENT '商店信息',
	`auditing_info` VARCHAR(100) COMMENT '审核备注',
	`address` VARCHAR(100) COMMENT '地址',
	PRIMARY KEY(`aid`),
	CONSTRAINT `ADVERT_DID` FOREIGN KEY (`did`) REFERENCES `zlcm_device` (`did`),
	CONSTRAINT `ADVERT_SID` FOREIGN KEY (`sid`) REFERENCES `zlcm_store` (`sid`),
	CONSTRAINT `ADVERT_UID` FOREIGN KEY (`uid`) REFERENCES `zlcm_user` (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



CREATE TABLE zlcm_order(
	`oid` INT(20) UNSIGNED auto_increment,
	`uid` INT(12),
	`aid` INT(20),
	`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`state` TINYINT(4) DEFAULT 0 NOT NULL COMMENT '0:待支付,1:已支付,2:已取消',
	`price` decimal(10,2) NOT NULL,
	`duration` LONG NOT NULL COMMENT '持续时间',
	PRIMARY KEY(`oid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_wallet(
	`wid` INT(12) UNSIGNED auto_increment,
	`uid` INT(12),
	`record` INT(10),
	`money` DECIMAL(10,2),
	PRIMARY KEY(`wid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_device(
	`did` INT(20) UNSIGNED auto_increment,
	`mac` VARCHAR(50),
	`address` VARCHAR(50),
	`dlatitude` DECIMAL(18,15),
	`dlongitude` DECIMAL(18,15),
	`ip` VARCHAR(20),
	`state` TINYINT(4) DEFAULT 0 COMMENT '0:可用,1:不可用',
	`household` INT(10) COMMENT '小区住户',
	`desc` VARCHAR(100),
	PRIMARY KEY(`did`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



CREATE TABLE zlcm_visitors_flowrate(
	`did` INT(20),
	`visitors_flowrate` INT(10) COMMENT '人流量指数'
)


CREATE TABLE zlcm_feedback(
	`fid` INT(20) UNSIGNED auto_increment,
	`uid` INT(12),
	`text` VARCHAR(500),
	`iphone` VARCHAR(11),
	PRIMARY KEY(`fid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_log(
	`lid` INT(50) UNSIGNED auto_increment,
	`type` VARCHAR(50),
	`title` VARCHAR(50),
	`remoteAddr` VARCHAR(100),
	`requestUri` VARCHAR(100),
	`params` VARCHAR(100),
	`exception` VARCHAR(100),
	`method` VARCHAR(10),
	`ip` VARCHAR(32),
	`operateDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`timeout` VARCHAR(30) NOT NULL,
	`uid` INT(12) UNSIGNED,
	PRIMARY KEY(`lid`),
	KEY (`uid`),
	CONSTRAINT `LOG_USER_ID` FOREIGN KEY (`uid`) REFERENCES `zlcm_user` (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE zlcm_identity_info(
	`uid` INT(12) UNSIGNED,
	`address` VARCHAR(50),
	`idcard` VARCHAR(30),
	`begin` VARCHAR(20) COMMENT '签发日期',
	`department` VARCHAR(20) COMMENT '签发机关',
	`end` VARCHAR(20) COMMENT '失效日期',
	`front_orderid` INT(30) COMMENT '正面聚合查询流水号',
	`back_orderid` INT(30) COMMENT '反面聚合查询流水号',
	`front_img` VARCHAR(50) COMMENT '正面照',
	`back_img` VARCHAR(50) COMMENT '反面照',
	PRIMARY KEY(`uid`),	
	CONSTRAINT `IDENTITY_USER_ID` FOREIGN KEY (`uid`) REFERENCES `zlcm_user` (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SELECT * FROM zlcm_user_details z WHERE z.uid = 15;
SELECT avatar,nickname FROM zlcm_user_details z WHERE z.uid = 15;

INSERT INTO zlcm_device(mac,address,dlongitude,dlatitude,ip,household,`desc`) VALUES('HR:G5:J7:56','七雄路立方城',114.135172,30.622077,'192.168.1.2',2000,'李哲自用版') 
INSERT INTO zlcm_advert(did,uid,advert_img,text_info) 
VALUES(1,15,'http://localhost:8080/img/recruit.jpg','') 


SELECT did,address,ip,dlatitude,dlongitude,household FROM zlcm_device WHERE 30.615392 < dlatitude < 33.615392 OR 114.128736 < dlongitude < 116.128736  OR state = 0

SELECT * , (round(6367000 * 2 * asin(
sqrt(pow(sin(((t_shop.lat *
        pi()
    ) / 180 -
    23 * pi()
) / 180) / 2), 2) + cos(
    (23 * pi()) / 180) *
cos((t_shop.lat * pi()) /
    180) * pow(sin(((
        t_shop.lon *
        pi()) /
    180 - (113 *
        pi()) /
    180) / 2), 2)))))
AS distance FROM t_shop ORDER BY distance ASC


select did,address,ip,dlatitude,dlongitude,household from zlcm_device where
dlatitude > 30.615373-(5/111) and
dlatitude < 30.615373+(5/111) and
dlongitude > 114.128725-(5/111) and
dlongitude < 114.128725+(5/111)
order by ACOS(SIN((30.615373 * pi()) / 180 ) *SIN((dlatitude * pi()) / 180 ) +COS((30.615373 * pi()) / 180 ) * COS((dlatitude * pi()) / 180 ) *COS((114.128725* pi()) / 180 - (dlongitude * pi()) / 180 ) ) * 6380

SELECT * FROM zlcm_log z LEFT JOIN (SELECT ud.avatar, FROM zlcm_user_details ud)zlcm_user_details u ON z.uid = u.uid 

SELECT ad.advert_img,ad.text_info,ad.start_time,ad.hits,ad.iphone ,a.nickname,a.avatar,a.real_name,a.stor_id 
FROM zlcm_advert ad
INNER JOIN 
(SELECT ud.uid,ud.nickname,ud.avatar,ud.real_name,ud.stor_id FROM zlcm_user_details ud) 
as a 
ON ad.uid = a.uid WHERE ad.state = 1
ORDER BY ad.hits DESC

SELECT ad.aid,ad.advert_img,ad.text_info,ad.start_time,ad.hits,ad.iphone ,a.nickname,a.avatar,a.real_name,a.stor_id 
    FROM zlcm_advert ad INNER JOIN 
    (SELECT ud.uid,ud.nickname,ud.avatar,ud.real_name,ud.stor_id FROM zlcm_user_details ud) as a 
    ON ad.uid = a.uid WHERE ad.state = 1 
    ORDER BY ad.hits DESC limit 0,10

 SELECT ad.aid,ad.advert_img,ad.text_info,ad.start_time,ad.hits,ad.iphone ,a.nickname,a.avatar,a.credit,real_name,a.stor_id
    FROM zlcm_advert ad INNER JOIN
    (SELECT ud.uid,ud.nickname,ud.credit,ud.avatar,ud.real_name,ud.stor_id FROM zlcm_user_details ud) as a
    ON ad.uid = a.uid WHERE ad.state = 1 AND recommend = 1
    ORDER BY ad.start_time DESC limit 0 ,10

SELECT a.advert_img FROM zlcm_advert a JOIN (select aid from zlcm_advert WHERE did = 1) b ON a.aid = b.aid

select advert_img from zlcm_advert WHERE did = 1



