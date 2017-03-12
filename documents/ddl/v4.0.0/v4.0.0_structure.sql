DROP TABLE IF EXISTS re_package_channel_update;
CREATE TABLE re_package_channel_update(
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  channel_name        varchar(20) NOT NULL COMMENT '如:Wandoujia',
  package_name        varchar(40) NOT NULL COMMENT '如:com.wj.hongbao',
  version_code        int(10) NULL COMMENT '版本号',
  push_time           varchar(20) NULL COMMENT '推送时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (device_id, channel_name, package_name)
) ENGINE=InnoDB COMMENT='渠道包更新表';


DROP TABLE IF EXISTS re_share_mission;
CREATE TABLE `re_share_mission` (
  `mission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `money`       decimal(10,2) unsigned DEFAULT '0.00' COMMENT '朋友圈每次点击得到的奖励',
  `mission_title` varchar(128) DEFAULT NULL COMMENT '任务名字',
  `mission_icon` varchar(128) DEFAULT NULL COMMENT '任务图标',
  `interface_url` varchar(128) DEFAULT NULL COMMENT '接口链接',
  `callback_url` varchar(128) DEFAULT NULL COMMENT '回调链接,如:广告链接',
  `mission_desc` varchar(256) DEFAULT NULL COMMENT '任务描述',
  `mission_text` varchar(256) DEFAULT NULL COMMENT '任务所需文字',
  `mission_img` varchar(128) DEFAULT NULL COMMENT '任务所需图标',
  `example_img` varchar(128) DEFAULT NULL COMMENT '示例图片',
  `total_click_times` int(10) NOT NULL  COMMENT '共需点击次数',
  `left_click_times` int(10) NOT NULL  COMMENT '剩余点击次数',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  `is_end` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  `start_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '任务开始时间',
  PRIMARY KEY (`mission_id`)
) ENGINE=InnoDB COMMENT='app分享/转发任务';


DROP TABLE IF EXISTS re_share_mission_detail;
CREATE TABLE `re_share_mission_detail` (
  `mission_id` bigint(20) unsigned NOT NULL  COMMENT '任务id',
  `user_id`    int(10) NOT NULL COMMENT '用户id',
  `open_id`    VARCHAR(40) NOT NULL COMMENT '微信openid',
  `click_time` varchar(20) DEFAULT NULL COMMENT '点击分享链接的时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`mission_id`,`open_id`)
) ENGINE=InnoDB COMMENT='app分享/转发任务详情';


DROP TABLE IF EXISTS re_password_red;
CREATE TABLE re_password_red(
  id                        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  red_password              VARCHAR(20) NULL COMMENT '红包口令',
  total_num                 INT(10) NULL DEFAULT 0 COMMENT '总数',
  left_num                  INT(10) NULL DEFAULT 0 COMMENT '剩余数',
  min_money                 decimal(10,2) unsigned DEFAULT '0.00' COMMENT '最小金额(元)',
  max_money                 decimal(10,2) unsigned DEFAULT '0.00' COMMENT '最大金额(元)',
  end_time                  VARCHAR(20) NULL DEFAULT NULL COMMENT '关闭时间',
  create_time               VARCHAR(20) NULL COMMENT '创建时间',
  update_time               VARCHAR(20) NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='口令红包';


DROP TABLE IF EXISTS re_password_red_detail;
CREATE TABLE re_password_red_detail(
  password_id           bigint(20) NOT NULL COMMENT '口令红包id',
  user_id               int(10) NOT NULL COMMENT '领取红包用户id',
  money                 decimal(10,2) unsigned NOT NULL COMMENT '领取金额(元)',
  receive_time          VARCHAR(20) NULL DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (password_id, user_id)
) ENGINE=INNODB COMMENT='口令红包领取记录';


DROP TABLE IF EXISTS re_parameter_configure;
CREATE TABLE re_parameter_configure(
  `configure_id`          VARCHAR(50)  NOT NULL COMMENT '配置项',
  `configure_desc`        VARCHAR(50)  NULL COMMENT '配置说明',
  `configure_one`         VARCHAR(500) NULL COMMENT '配置内容1',
  `configure_two`         VARCHAR(500) NULL COMMENT '配置内容2',
  `configure_three`       VARCHAR(500) NULL COMMENT '配置内容3',
  `configure_four`        VARCHAR(500) NULL COMMENT '配置内容4',
  `configure_five`        VARCHAR(500) NULL COMMENT '配置内容5',
  PRIMARY KEY (configure_id)
) ENGINE=INNODB COMMENT='参数配置表';


DROP TABLE IF EXISTS re_newcomer_mission;
CREATE TABLE re_newcomer_mission(
  mission_id          BIGINT UNSIGNED AUTO_INCREMENT COMMENT '任务ID',
  mission_name        varchar(128) NOT NULL COMMENT '任务名字',
  mission_award       decimal(10,2) unsigned NOT NULL COMMENT '金额(元)',
  mission_type        TINYINT(3) DEFAULT 0 COMMENT '任务类型',
  mission_status      tinyint(1) DEFAULT '2' COMMENT '显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  mission_order       tinyint(3) NOT NULL DEFAULT '0' COMMENT '任务权重, 值较大者排在前面',
  create_time         varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time         varchar(20) NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id)
) ENGINE=INNODB COMMENT='新手任务表';


DROP TABLE IF EXISTS re_newcomer_mission_detail;
CREATE TABLE re_newcomer_mission_detail(
  mission_id          BIGINT unsigned NOT NULL COMMENT '任务ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  money               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  draw_time           varchar(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, user_id)
) ENGINE=INNODB COMMENT='新手任务明细表';


DROP TABLE IF EXISTS re_financial_mall;
CREATE TABLE re_financial_mall(
  `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `title`    varchar(128) DEFAULT NULL COMMENT '标题',
  `icon`    varchar(128) DEFAULT NULL COMMENT '图标',
  `desc`    varchar(256) DEFAULT NULL COMMENT '描述',
  `money`   decimal(10,2) unsigned DEFAULT NULL COMMENT '起投金额,如:70,表示70元',
  `investment_time`   INT(10)  DEFAULT '0' COMMENT '投资期限,如5,表示5天',
  `earning` decimal(10,2) unsigned DEFAULT NULL COMMENT '年化收益,如:15,表示15%',
  `package_name` varchar(100) DEFAULT NULL COMMENT '包名',
  `android_package_url` varchar(128) DEFAULT NULL COMMENT '安卓下载app链接',
  `ios_package_url` varchar(128) DEFAULT NULL COMMENT '苹果下载app链接,一般为打开app store',
  `labels`    varchar(256) DEFAULT NULL COMMENT '标签,用逗号分开',
  `click_url` varchar(128) DEFAULT NULL COMMENT '点击按钮跳转的链接',
  `mall_order` TINYINT(3)  DEFAULT 1 COMMENT '权重,越大越靠前',
  `platform` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '平台,0:IOS,1:ANDROID,2:全开,3:全关',
  `create_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  `update_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  `is_end` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='理财大厅';


DROP TABLE IF EXISTS re_load_html;
CREATE TABLE re_load_html(
  html_id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  html_url            varchar(512) NOT NULL COMMENT '链接',
  html_status         TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态, 0:关闭, 1:打开',
  total_num           INT(10) NULL DEFAULT 0 COMMENT '总需次数',
  complete_num        INT(10) NULL DEFAULT 0 COMMENT '完成次数',
  start_time          varchar(8) NULL COMMENT '开始时间，如09:00:00',
	end_time            varchar(8) NULL COMMENT '结束时间，如19:00:00',
  create_time         varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time         varchar(20) NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (html_id)
) ENGINE=INNODB COMMENT='网页自动加载';


DROP TABLE IF EXISTS re_load_html_detail;
CREATE TABLE re_load_html_detail(
  detail_id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  html_id             BIGINT NOT NULL COMMENT '链接id',
  device_id           VARCHAR(100) NOT NULL COMMENT '设备Id',
  create_time         varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (detail_id)
) ENGINE=INNODB COMMENT='网页加载明细表';


DROP TABLE IF EXISTS re_user_data_statistics;
CREATE TABLE re_user_data_statistics (
  package_name              VARCHAR(100) NOT NULL COMMENT '包名',
  channel_name              VARCHAR(100) NOT NULL COMMENT '渠道',
  data_time                 VARCHAR(10) NOT NULL COMMENT '日期',
  today_user                int(10) DEFAULT '0' COMMENT '今日新增用户',
  total_user                int(10) DEFAULT '0' COMMENT '总用户数',
  today_user_give_coin      int(10) DEFAULT '0' COMMENT '新增用户发放金币',
  today_total_give_coin     int(10) DEFAULT '0' COMMENT '今日总发放金币',
  today_user_expend_coin    int(10) DEFAULT '0' COMMENT '新增用户消耗金币',
  today_total_expend_coin   int(10) DEFAULT '0' COMMENT '今日总消耗金币',
  today_user_give_money     DECIMAL(10,2) DEFAULT '0.00' COMMENT '新增用户发放金额(元)',
  today_total_give_money    DECIMAL(10,2) DEFAULT '0.00' COMMENT '今日总发放金额(元)',
  today_user_expend_money   DECIMAL(10,2) DEFAULT '0.00' COMMENT '新增用户消耗金额(元)',
  today_total_expend_money  DECIMAL(10,2) DEFAULT '0.00' COMMENT '今日总消耗金额(元)',
  PRIMARY KEY (package_name, channel_name, data_time)
) ENGINE=InnoDB COMMENT='用户数据统计表';


DROP TABLE IF EXISTS re_mission_module_statistics;
CREATE TABLE `re_mission_module_statistics` (
  `one_date` varchar(20) NOT NULL COMMENT '日期，格式：2016-11-26',
  `module` tinyint(1) NOT NULL COMMENT '模块编号，如：1表示高额任务模块',
  `dis_part_amount` int(10) DEFAULT '0' COMMENT '参与任务总用户数，每个用户只统计一次',
  `total_part_amount` int(10) DEFAULT '0' COMMENT '参与任务总次数，单个用户可能统计多次',
  `dis_comp_amount` int(10) DEFAULT '0' COMMENT '完成任务总用户数，每个用户只统计一次',
  `total_comp_amount` int(10) DEFAULT '0' COMMENT '完成任务总次数，单个用户可能统计多次',
  `should_pay_money` decimal(10,2) DEFAULT '0.00' COMMENT '应发奖励，元',
  `final_pay_money` decimal(10,2) DEFAULT '0.00' COMMENT '实发奖励，元',
  `module_desc` varchar(150) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`one_date`,`module`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模块数据统计表'