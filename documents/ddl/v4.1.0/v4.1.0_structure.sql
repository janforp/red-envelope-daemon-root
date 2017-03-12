DROP TABLE if EXISTS re_module_user_info;
CREATE TABLE re_module_user_info(
  info_id               BIGINT UNSIGNED AUTO_INCREMENT COMMENT '信息id，自增长',
  mission_type          TINYINT(3) NULL DEFAULT 0 COMMENT '任务类型',
  mission_subtype       TINYINT(3) NULL DEFAULT 0 COMMENT '任务子类型',
  mission_id            BIGINT NULL COMMENT '任务id',
  user_id               int(10) NOT NULL COMMENT '用户id',
  platfrom              tinyint(1) NOT NULL COMMENT '平台; 0:ios ,1:andriod ',
  device_id             VARCHAR(200) NULL COMMENT '设备Id',
  device_name           VARCHAR(200) NULL COMMENT '设备名称',
  package_name          VARCHAR(100) NULL COMMENT '包名',
  channel_name          VARCHAR(100) NULL COMMENT '渠道',
  app_version           VARCHAR(45) NULL COMMENT 'app版本',
  user_ip               varchar(100) NULL COMMENT '用户ip',
  user_imsi             VARCHAR(255) NULL COMMENT '国际移动用户识别码imsi',
  user_imei             VARCHAR(255) NULL COMMENT '国际移动设备身份码imei',
  sim_num               VARCHAR(255) NULL COMMENT 'sim卡序列号',
  mobile_num            VARCHAR(20) NULL COMMENT '手机号',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
PRIMARY KEY (info_id)
) ENGINE=InnoDB COMMENT='用户参与模块个人信息表';


DROP TABLE IF EXISTS re_andriod_integral_wall;
CREATE TABLE re_andriod_integral_wall(
  wall_id               BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  app_name              varchar(256) NULL COMMENT '应用名称',
  app_url               varchar(256) NULL COMMENT '安装包链接',
  app_icon              varchar(256) NULL COMMENT '应用图标',
  app_package           varchar(256) NULL COMMENT '应用包名',
  app_desc              varchar(512) NULL COMMENT '应用简短描述',
  app_size              varchar(20) NULL COMMENT '应用大小(MB)',
  app_label             varchar(256) NULL COMMENT '应用标签',
  app_introduce         LONGTEXT NULL COMMENT '应用介绍',
  app_img               LONGTEXT NULL COMMENT '应用介绍图，图片的url,多张图片用;分开',
  start_time            varchar(20) NULL COMMENT '开始时间,如:2016-08-18 12:53:30',
  end_time              varchar(20) NULL COMMENT '结束时间,如:2016-08-18 12:53:30',
  is_limit_num          tinyint(1) DEFAULT '0' COMMENT '是否限量, 0:否, 1:是',
  total_num             INT(10) NULL COMMENT '任务总数量',
  left_num              INT(10) NULL COMMENT '任务剩余数量',
  total_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务总奖励',
  step_one_money        DECIMAL(10,2) DEFAULT 0.00 COMMENT '第一步总奖励',
  step_one_desc         varchar(512) NULL COMMENT '第一步描述',
  step_one_second       INT(10) DEFAULT 30 COMMENT '第一步打开时间(秒)',
  step_two_money        DECIMAL(10,2) DEFAULT 0.00 COMMENT '第二步每天奖励',
  step_two_day          INT(10) NULL COMMENT '第二步天数',
  step_two_second       INT(10) DEFAULT 30 COMMENT '第二步打开时间(秒)',
  wall_weight           TINYINT(3) NULL DEFAULT 0 COMMENT '权重大的靠前',
  is_end                tinyint(1) NULL DEFAULT 1 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  is_sim                tinyint(1) NULL DEFAULT 0 COMMENT '是否需要sim卡，0:不需要，1:需要',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (wall_id)
) ENGINE=InnoDB COMMENT='安卓积分墙';


DROP TABLE IF EXISTS re_andriod_user_task;
CREATE TABLE re_andriod_user_task(
  wall_id               BIGINT NOT NULL COMMENT '积分墙任务id',
  user_id               int(10) NOT NULL COMMENT '用户id',
  info_id               BIGINT NOT NULL COMMENT '信息id',
  task_status           tinyint(1) DEFAULT '0' COMMENT '状态, 0:进行中, 1:已完成, 2:已放弃',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  release_time          VARCHAR(20) NULL COMMENT '任务过期,释放时间',
  PRIMARY KEY (wall_id, user_id)
) ENGINE=InnoDB COMMENT='用户领取积分墙任务表';


DROP TABLE IF EXISTS re_andriod_deep_mission;
CREATE TABLE re_andriod_deep_mission(
  wall_id               BIGINT NOT NULL COMMENT '积分墙任务id',
  user_id               int(10) NOT NULL COMMENT '用户id',
  info_id               BIGINT NOT NULL COMMENT '信息id',
  finish_times          tinyint(3) DEFAULT '0' COMMENT '完成次数',
  last_finish_time      varchar(20) NULL COMMENT '最近一次完成日期,如:2016-08-18',
  start_time            varchar(20) NULL COMMENT '开始日期,如:2016-08-18',
  end_time              varchar(20) NULL COMMENT '结束日期,如:2016-08-18',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (wall_id, user_id)
) ENGINE=InnoDB COMMENT='安卓积分墙深度任务表';


DROP TABLE IF EXISTS re_article_mission;
CREATE TABLE `re_article_mission` (
  `article_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `article_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '文章分类，0:自己爬的文章，1:客户的链接',
  `article_url` varchar(500) DEFAULT NULL COMMENT '若该文章的类型是0，则此字段是后期点击跳转的广告链接，若该文章的类型是1，则就是文章链接',
  `is_directly_go_ad` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否直接跳转只广告页面，1：直接跳转，0：不跳转',
  `article_title` varchar(128) DEFAULT NULL COMMENT '文章标题',
  `article_content` LONGTEXT DEFAULT NULL COMMENT '文章内容',
  `article_icon` varchar(128) DEFAULT NULL COMMENT '文章封面图',
  `article_display_time` varchar(20) DEFAULT NULL COMMENT '文章显示时间,如:2016-08-18',
  `article_author` varchar(100) DEFAULT NULL COMMENT '文章作者信息',
  `app_read_money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT 'app上阅读奖励，每个用户只有一次有效阅读',
  `wx_click_money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '单个有效好友点击获得的奖励',
  `total_click_times` int(10) NOT NULL COMMENT '共需点击次数',
  `left_click_times` int(10) NOT NULL COMMENT '剩余点击次数',
  `start_mission_time` varchar(20) DEFAULT NULL COMMENT '任务开始时间,如:2016-08-18 12:53:30',
  `end_mission_time` varchar(20) DEFAULT NULL COMMENT '任务结束时间,如:2016-08-18 12:53:30',
  `create_time` varchar(20) DEFAULT NULL COMMENT '任务创建时间,如:2016-08-18 12:53:30',
  `update_time` varchar(20) DEFAULT NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  `is_end` tinyint(1) NOT NULL DEFAULT '1' COMMENT '该文章获得是否结束，0:已结束，1:进行中',
  `article_weight` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该文章权重，排序时越大越靠前',
  `read_times` int(10) NOT NULL DEFAULT '0' COMMENT '总阅读数',
  `praise_times` int(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `original_url` varchar(256) DEFAULT NULL COMMENT '阅读原文链接',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB COMMENT='app转发文章任务';


DROP TABLE IF EXISTS re_article_ad;
CREATE TABLE `re_article_ad` (
  `ad_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `article_id` bigint(20) unsigned NOT NULL  COMMENT '分享文章任务的id',
  `ad_icon` varchar(128) DEFAULT NULL COMMENT '广告图',
  `ad_url` varchar(128) DEFAULT NULL COMMENT '广告跳转链接',
  `ad_order` tinyint(1) NOT NULL DEFAULT '1' COMMENT '广告位置,0上面:1:中间2:底部,3:左边悬浮，4：右边悬浮，将来可能汇扩展',
  `is_display` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该广告是否显示，0:不显示，1:显示',
  `create_time` varchar(20) DEFAULT NULL COMMENT '任务创建时间,如:2016-08-18 12:53:30',
  `update_time` varchar(20) DEFAULT NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB COMMENT='app转发文章任务广告表';


DROP TABLE IF EXISTS re_article_click_detail;
CREATE TABLE `re_article_click_detail` (
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
  `user_id` int(10) NOT NULL COMMENT '转发用户id',
  `open_id` varchar(40) NOT NULL COMMENT '读者的微信openid',
  `is_praised` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该阅读人是否点赞，0:没有点赞，1:已点赞',
  `praise_time` varchar(20) DEFAULT NULL COMMENT '点赞时间,如:2016-08-18 12:53:30',
  `click_time` varchar(20) DEFAULT NULL COMMENT '点击月的的时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`article_id`,`open_id`)
) ENGINE=InnoDB COMMENT='app转发文章点击详情';


DROP TABLE IF EXISTS re_article_read;
CREATE TABLE `re_article_read` (
  `article_id` bigint(20) unsigned NOT NULL  COMMENT '分享文章任务的id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `read_time` varchar(20) DEFAULT NULL COMMENT '阅读时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`article_id`,`user_id`)
) ENGINE=InnoDB COMMENT='app转发文章阅读表';