DROP TABLE if EXISTS re_index_mission_navigation;
CREATE TABLE re_index_mission_navigation(
  navigation_id         BIGINT UNSIGNED AUTO_INCREMENT COMMENT '导航id，自增长',
  navigation_name       VARCHAR(200) NULL COMMENT '导航名称',
  navigation_img        VARCHAR(200) NULL COMMENT '导航图片',
  navigation_url        VARCHAR(200) NULL COMMENT '导航链接',
  navigation_status     tinyint(1) DEFAULT 2 COMMENT '状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭',
  navigation_weight     tinyint(3) DEFAULT 0 COMMENT '权重0-100, 越大越靠前',
  create_time           VARCHAR(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           VARCHAR(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
PRIMARY KEY (navigation_id)
) ENGINE=InnoDB COMMENT='首页任务模块导航表';


DROP TABLE if EXISTS re_welfare_type;
CREATE TABLE re_welfare_type(
  type_id               tinyint(3) UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  type_name             VARCHAR(200) NULL COMMENT '名称',
  type_img              VARCHAR(200) NULL COMMENT '图片',
  type_status           tinyint(1) DEFAULT 2 COMMENT '状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭',
  sub_type_name         varchar(200) DEFAULT NULL COMMENT '子类型,用逗号分隔开，如:爱奇艺,优酷',
  create_time           VARCHAR(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           VARCHAR(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
PRIMARY KEY (type_id)
) ENGINE=InnoDB COMMENT='福利类型表';


DROP TABLE IF EXISTS re_welfare;
CREATE TABLE re_welfare(
  welfare_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT '福利id，自增长',
  welfare_title         VARCHAR(256) NULL COMMENT '福利标题',
  welfare_icon          VARCHAR(256) NULL COMMENT '福利图标',
  welfare_reward        VARCHAR(256) NULL COMMENT '奖励,如:100元红包券,10元观影券,购物券等',
  welfare_type          TINYINT(3) NULL DEFAULT 0 COMMENT '分类: 0-未分类',
  sub_type_name         varchar(200) DEFAULT NULL COMMENT '子类型,用逗号分隔开，如:爱奇艺,优酷',
  is_selection          TINYINT(1) NULL DEFAULT 0 COMMENT '是否精选: 0-否,1-是',
  merchant_icon         VARCHAR(256) NULL COMMENT '商家图标',
  merchant_name         VARCHAR(256) NULL COMMENT '商家名称',
  participants_num      INT(10) NOT NULL DEFAULT 0 COMMENT '参加人数',
  welfare_banner        VARCHAR(256) NULL COMMENT '福利banner',
  welfare_detail        longtext NULL COMMENT '福利详情',
  welfare_status        tinyint(1) DEFAULT 2 COMMENT '状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭',
  button_name           VARCHAR(30) DEFAULT '直达链接' COMMENT '按钮名称',
  button_url            VARCHAR(256) NULL COMMENT '按钮链接',
  welfare_weight        tinyint(3) DEFAULT 0 COMMENT '权重0-100, 越大越靠前',
  end_time              VARCHAR(20) NULL COMMENT '结束时间,如:2016-08-18 12:53:30',
  create_time           VARCHAR(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           VARCHAR(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (welfare_id)
) ENGINE=INNODB COMMENT='福利表';


DROP TABLE IF EXISTS re_recommend_mission_type;
CREATE TABLE `re_recommend_mission_type` (
  `type_id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `type_name` varchar(200) DEFAULT NULL COMMENT '主类型名称',
  `sub_type_name` varchar(200) DEFAULT NULL COMMENT '子类型,用逗号分隔开，如:爱奇艺,优酷' ,
  `type_img` varchar(200) DEFAULT NULL COMMENT '图片',
  `type_status` tinyint DEFAULT '2' COMMENT '状态; 0:ios开启, 1:andriod开启, 2:全部开启, 3:全部关闭',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB COMMENT='审核／注册任务类型表';


DROP TABLE IF EXISTS re_loan_mall;
CREATE TABLE re_loan_mall(
  `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `title`    varchar(128) DEFAULT NULL COMMENT '标题',
  `icon`    varchar(128) DEFAULT NULL COMMENT '图标',
  `desc`    varchar(256) DEFAULT NULL COMMENT '描述',
  `order_money`   decimal(10,2)  DEFAULT 0.00 COMMENT '排序金额(万元),如:0.1(万)',
  `display_money`  VARCHAR(20)  DEFAULT '0' COMMENT '展示金额,根据实际情况输入',
  `month_interest_rate`   decimal(10,2)  DEFAULT 0 COMMENT '月利率',
  `labels`    varchar(256) DEFAULT NULL COMMENT '标签,用逗号分开',
  `participants_num` INT(10) NULL DEFAULT 0 COMMENT '已申请人数',
  `click_url` varchar(128) DEFAULT NULL COMMENT '点击按钮跳转的链接',
  `to_account_time`   INT(10)  DEFAULT '0' COMMENT '到账时间,如5,表示5天',
  `create_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  `update_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  `is_end` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='贷款中心';
