DROP TABLE IF EXISTS re_xuanshang_banner;
CREATE TABLE re_xuanshang_banner(
  banner_id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'BannerID',
  banner_title          varchar(64)  NOT NULL COMMENT 'Banner标题',
  banner_img            varchar(256)  NOT NULL COMMENT 'Banner图片',
  banner_status         tinyint(1) DEFAULT 2 COMMENT 'Banner状态; 0:ios开启,1:android开启,2:全部开启,3:全部关闭',
  banner_url            varchar(256)  DEFAULT NULL COMMENT  'Banner链接',
  banner_weight         tinyint(3) unsigned DEFAULT 0 COMMENT '权重(0-100),大的排前面',
  min_version_code      int(10) NULL DEFAULT 0 COMMENT '最低显示版本号',
  max_version_code      int(10) NULL DEFAULT 0 COMMENT '最高显示版本号',
  show_channel_name     LONGTEXT NULL COMMENT '显示的渠道名',
  show_package_name     LONGTEXT NULL COMMENT '显示的包名',
  is_show               TINYINT(1) NULL DEFAULT 0 COMMENT '审核状态是否显示（默认为0） 0-不显示；1-显示',
  create_time       VARCHAR(20) NOT NULL COMMENT '创建时间',
  update_time       VARCHAR(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (banner_id)
) ENGINE=InnoDB COMMENT='悬赏任务banner';


DROP TABLE IF EXISTS re_xuanshang;
CREATE TABLE re_xuanshang(
  id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  user_id           INT(10) NOT NULL COMMENT '发任务用户id',
  total_num         INT(10) NOT NULL COMMENT '任务总个数',
  pass_num          INT(10) NOT NULL COMMENT '审核通过个数',
  single_money      decimal(10,2) unsigned DEFAULT '0.00' COMMENT '单个任务金额(元)',
  mission_desc      VARCHAR(256) NULL COMMENT '任务描述',
  mission_img       VARCHAR(1500) NULL COMMENT '图片用分号隔开',
  is_delete         tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除，0:未删除，1:已删除',
  create_time       VARCHAR(20) NOT NULL COMMENT '创建时间',
  update_time       VARCHAR(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='悬赏任务列表';


DROP TABLE IF EXISTS re_xuanshang_detail;
CREATE TABLE re_xuanshang_detail(
  id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  xuanshang_id      BIGINT NOT NULL COMMENT '悬赏任务id',
  user_id           INT(10) NOT NULL COMMENT '提交悬赏任务用户id',
  mission_text      VARCHAR(256) NULL COMMENT '提交备注',
  mission_img       VARCHAR(1500) NULL COMMENT '提交的图片用分号隔开',
  detail_status     tinyint(1) DEFAULT 0 COMMENT '状态; 0:已提交,待审核,1:已通过,2:未通过,3:任务已经完成,该记录自动过期',
  reason            VARCHAR(256) NULL COMMENT '未通过原因',
  is_delete         tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除，0:未删除，1:已删除',
  create_time       VARCHAR(20) NULL COMMENT '创建时间',
  update_time       VARCHAR(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='悬赏任务提交详情表';


DROP TABLE IF EXISTS re_android_mission;
CREATE TABLE re_android_mission(
  mission_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  app_name              varchar(256) NULL COMMENT '应用名称',
  app_icon              varchar(256) NULL COMMENT '应用图标',
  app_label             varchar(50) NULL COMMENT '应用标签',
  app_size              varchar(20) NULL COMMENT '应用大小(MB)',
  app_url               varchar(256) NULL COMMENT '安装包链接',
  app_package           varchar(256) NULL COMMENT '应用包名',
  app_desc              varchar(256) NULL COMMENT '应用短描述',
  auxiliary_time        tinyint(1) DEFAULT '0' COMMENT '附加任务激活时机, 0:当天, 1:次日',
  app_introduce         LONGTEXT NULL COMMENT '应用介绍',
  app_img               LONGTEXT NULL COMMENT '应用介绍图，图片的url,多张图片用;分开',
  start_time            varchar(10) NULL COMMENT '开始日期,如:2016-08-18',
  end_time              varchar(10) NULL COMMENT '结束日期,如:2016-08-18',
  total_num             INT(10) NULL COMMENT '任务总数量',
  left_num              INT(10) NULL COMMENT '任务剩余数量',
  activate_money        DECIMAL(10,2) DEFAULT 0.00 COMMENT '激活奖励',
  activate_desc         varchar(512) NULL COMMENT '激活描述',
  activate_start_time   varchar(8) NULL COMMENT '激活任务开始时间 如:09:00:00',
  activate_end_time     varchar(8) NULL COMMENT '激活任务结束时间 如:19:00:00',
  activate_time         INT(4) NULL COMMENT '打开时长(秒)',
  mission_weight        TINYINT(3) NULL DEFAULT 0 COMMENT '权重大的靠前',
  keep_day              INT(4) DEFAULT 0 COMMENT '留存天数',
  auxiliary_num         INT(4) DEFAULT 0 COMMENT '附属任务个数',
  operator_limit        varchar(20) NULL COMMENT '运营商限制, 如: 移动',
  address_limit         varchar(100) NULL COMMENT '地域限制, 如: 浙江',
  sim_limit             tinyint(1) NULL DEFAULT 0 COMMENT '是否需要sim卡，0:不需要，1:需要',
  num_limit             tinyint(1) DEFAULT '0' COMMENT '是否限量, 0:否, 1:是',
  is_end                tinyint(1) NULL DEFAULT 0 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  is_delete             tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除，0:未删除，1:已删除',
  total_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务总奖励',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id)
) ENGINE=InnoDB COMMENT='安卓积分墙任务';


DROP TABLE IF EXISTS re_android_mission_depth;
CREATE TABLE re_android_mission_depth(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  depth_id              INT(4) NULL COMMENT '深度任务id, 第几天打开',
  depth_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '奖励',
  depth_desc            varchar(512) NULL COMMENT '描述',
  depth_start_time      varchar(8) NULL COMMENT '开始时间 如:09:00:00',
  depth_keep            DECIMAL(4,2) DEFAULT 0.00 COMMENT '留存',
  depth_time            INT(4) NULL COMMENT '体验时长(秒)',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, depth_id)
) ENGINE=InnoDB COMMENT='安卓积分墙任务深度体验';


DROP TABLE IF EXISTS re_android_mission_depth_keep;
CREATE TABLE re_android_mission_depth_keep(
  mission_id                BIGINT NOT NULL COMMENT '任务id',
  depth_id                  INT(4) NULL COMMENT '深度任务id, 第几天打开',
  data_day                  varchar(10) NOT NULL COMMENT '数据日期',
  activate_num              INT(10) NOT NULL COMMENT '激活量',
  plan_num                  INT(10) NULL COMMENT '计划量',
  real_num                  INT(10) NULL COMMENT '实际量',
  PRIMARY KEY (mission_id, depth_id, data_day)
) ENGINE=InnoDB COMMENT='安卓积分墙深度体验留存表';


DROP TABLE IF EXISTS re_android_mission_install;
CREATE TABLE re_android_mission_install(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  app_package           varchar(256) NULL COMMENT '应用包名',
  mission_status        TINYINT(1) DEFAULT 0 COMMENT '状态: 0-已安装未体验完成 1-已完成(已完成定时会删除)',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, user_id)
) ENGINE=InnoDB COMMENT='用户积分墙任务安装临时表';


DROP TABLE IF EXISTS re_android_mission_finish;
CREATE TABLE re_android_mission_finish(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  app_package           varchar(256) NULL COMMENT '应用包名',
  start_day             varchar(10) NOT NULL COMMENT '开始任务的日期',
  end_day               varchar(10) NOT NULL COMMENT '最后任务的日期',
  total_day             TINYINT(3) NOT NULL COMMENT '任务总天数',
  mission_no            TINYINT(3) NOT NULL COMMENT '最后完成时的任务编号',
  total_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '用户获取到的总奖励',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, user_id)
) ENGINE=InnoDB COMMENT='用户完成任务表';


DROP TABLE IF EXISTS re_android_mission_finish_detail;
CREATE TABLE re_android_mission_finish_detail(
  detail_id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  start_day             varchar(10) NOT NULL COMMENT '开始任务的日期',
  mission_no            TINYINT(3) NOT NULL COMMENT '任务编号, 这个任务的第几天任务',
  mission_money         DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务奖励',
  create_time           VARCHAR(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (detail_id)
) ENGINE=InnoDB COMMENT='用户完成任务明细表';


DROP TABLE IF EXISTS re_mission_require;
CREATE TABLE re_mission_require(
  require_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  require_name          varchar(128) NULL COMMENT '名字',
  require_value         varchar(128) NULL COMMENT '值',
  PRIMARY KEY (require_id)
) ENGINE=InnoDB COMMENT='任务审核要求';


DROP TABLE IF EXISTS re_android_auxiliary_mission;
CREATE TABLE re_android_auxiliary_mission(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  mission_no            TINYINT(3) NOT NULL COMMENT '任务编号',
  mission_title         varchar(256) NULL COMMENT '任务标题',
  mission_label         varchar(50) NULL COMMENT '任务标签',
  total_num             INT(6) NOT NULL COMMENT '总量',
  left_num              INT(6) NOT NULL COMMENT '剩余量',
  mission_desc          LONGTEXT NULL COMMENT '任务详情',
  mission_img           LONGTEXT NULL COMMENT '图片',
  mission_money         DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务奖励',
  check_require         LONGTEXT NULL COMMENT '审核要求',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, mission_no)
) ENGINE=InnoDB COMMENT='安卓积分墙附属任务';


DROP TABLE IF EXISTS re_android_auxiliary_mission_finish;
CREATE TABLE re_android_auxiliary_mission_finish(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  mission_no            TINYINT(3) NOT NULL COMMENT '任务编号',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  data_day              varchar(10) NOT NULL COMMENT '完成积分墙激活日期',
  mission_status        TINYINT(1) DEFAULT 0 COMMENT '状态 0-审核中 1-已通过 2-未通过 3-重新提交',
  commit_text           varchar(512) NULL COMMENT '提交内容',
  commit_img            LONGTEXT NULL COMMENT '提交图片',
  check_text            varchar(512) NULL COMMENT '审核备注',
  abandon_time          BIGINT NULL COMMENT '重新提交状态下, 超过这个时间就自动放弃',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, mission_no, user_id)
) ENGINE=InnoDB COMMENT='用户完成积分墙附属任务';


DROP TABLE IF EXISTS re_ios_mission;
CREATE TABLE re_ios_mission(
  mission_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  app_name              varchar(256) NULL COMMENT '应用名称',
  app_icon              varchar(256) NULL COMMENT '应用图标',
  app_label             varchar(50) NULL COMMENT '应用标签',
  app_size              varchar(20) NULL COMMENT '应用大小(MB)',
  app_url               varchar(256) NULL COMMENT '安装包链接',
  app_desc              varchar(256) NULL COMMENT '应用短描述',
  app_introduce         LONGTEXT NULL COMMENT '应用介绍',
  app_img               LONGTEXT NULL COMMENT '应用介绍图，图片的url,多张图片用;分开',
  start_time            varchar(20) NULL COMMENT '开始时间,如:2016-08-18 12:53:30',
  end_time              varchar(20) NULL COMMENT '结束时间,如:2016-08-18 12:53:30',
  mission_weight        TINYINT(3) NULL DEFAULT 0 COMMENT '权重大的靠前',
  step_num              INT(4) DEFAULT 1 COMMENT '任务个数',
  operator_limit        varchar(20) NULL COMMENT '运营商限制, 如: 移动',
  address_limit         varchar(100) NULL COMMENT '地域限制, 如: 浙江',
  is_end                tinyint(1) NULL DEFAULT 0 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  is_delete             tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除，0:未删除，1:已删除',
  total_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务总奖励',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id)
) ENGINE=InnoDB COMMENT='ios任务';


DROP TABLE IF EXISTS re_ios_mission_step;
CREATE TABLE re_ios_mission_step(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  step_id               TINYINT(3) NOT NULL COMMENT '步骤编号',
  mission_title         varchar(256) NULL COMMENT '任务标题',
  mission_desc          LONGTEXT NULL COMMENT '任务描述',
  total_num             INT(6) NOT NULL COMMENT '总量',
  left_num              INT(6) NOT NULL COMMENT '剩余量',
  mission_money         DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务奖励',
  mission_label         varchar(50) NULL COMMENT '任务标签',
  mission_img           LONGTEXT NULL COMMENT '图片',
  check_require         LONGTEXT NULL COMMENT '审核要求',
  is_btn                tinyint(1) NULL DEFAULT 1 COMMENT '是否有按钮，0:无，1:有',
  btn_title             varchar(50) NULL COMMENT '按钮标题',
  btn_url               varchar(512) NULL COMMENT '按钮链接',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, step_id)
) ENGINE=InnoDB COMMENT='ios任务明细';


DROP TABLE IF EXISTS re_ios_mission_finish;
CREATE TABLE re_ios_mission_finish(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  step_id               TINYINT(3) NOT NULL COMMENT '步骤编号',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  mission_status        TINYINT(1) DEFAULT 0 COMMENT '状态 0-审核中 1-已通过 2-未通过 3-重新提交',
  commit_text           varchar(512) NULL COMMENT '提交内容',
  commit_img            LONGTEXT NULL COMMENT '提交图片',
  check_text            varchar(512) NULL COMMENT '审核备注',
  abandon_time          BIGINT NULL COMMENT '重新提交状态下, 超过这个时间就自动放弃',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, step_id, user_id)
) ENGINE=InnoDB COMMENT='用户完成ios任务情况表';


DROP TABLE IF EXISTS re_award_mission;
CREATE TABLE re_award_mission(
  mission_id            INT(2) NOT NULL COMMENT '任务id',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  platform              TINYINT(1) DEFAULT 0 COMMENT '0：ios，1：安卓',
  mission_money         DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务奖励',
  is_get                TINYINT(1) DEFAULT 0 COMMENT '0：未领取，1：已领取',
  create_time           varchar(11) NULL COMMENT '创建时间,如:2016-08-18',
  PRIMARY KEY (mission_id,user_id)
) ENGINE=InnoDB COMMENT='赚钱领取奖励任务';


DROP TABLE IF EXISTS re_ios_mission_user_situation;
CREATE TABLE re_ios_mission_user_situation(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  user_id               INT(10) NOT NULL COMMENT '用户id',
  app_name              varchar(256) NULL COMMENT '应用名称',
  app_icon              varchar(256) NULL COMMENT '应用图标',
  step_id               TINYINT(3) NOT NULL COMMENT '当前步骤编号',
  mission_status        TINYINT(1) DEFAULT 0 COMMENT '状态 0-审核中 1-已通过 2-未通过 3-重新提交',
  total_money           DECIMAL(10,2) DEFAULT 0.00 COMMENT '任务总奖励',
  gain_money            DECIMAL(10,2) DEFAULT 0.00 COMMENT '已获得任务总奖励',
  end_time              varchar(20) NULL COMMENT '任务结束时间,如:2016-08-18 12:53:30',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time           varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (mission_id, user_id)
) ENGINE=InnoDB COMMENT='最新完成ios任务情况表';


DROP TABLE IF EXISTS re_auxiliary_statistic;
CREATE TABLE re_auxiliary_statistic(
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  step_id               TINYINT(3) NOT NULL COMMENT '当前步骤编号',
  platform              TINYINT(1) NOT NULL COMMENT '平台 0-ios 1-安卓',
  statistic_time        varchar(20) NULL COMMENT '日期时间,如:2016-08-18',
  submit_num            INT(10) DEFAULT 0 COMMENT '提交数量',
  PRIMARY KEY (mission_id, step_id,platform,statistic_time)
) ENGINE=InnoDB COMMENT='苹果高额任务日统计及安卓附加任务日完成统计';


DROP TABLE IF EXISTS re_user_commission_record;
CREATE TABLE re_user_commission_record(
  data_day          varchar(10) NOT NULL COMMENT '日期',
  user_id           INT(10) NOT NULL COMMENT '邀请人id',
  invited_user_id   INT(10) NOT NULL COMMENT '被邀请人id',
  total_money       decimal(10,2) unsigned DEFAULT '0.00' COMMENT '总金额(元)',
  gain_money        decimal(10,2) unsigned DEFAULT '0.00' COMMENT '获得佣金(元)',
  create_time       VARCHAR(20) NULL COMMENT '创建时间',
  update_time       VARCHAR(20) NULL COMMENT '更新时间',
  PRIMARY KEY (data_day, user_id, invited_user_id)
) ENGINE=INNODB COMMENT='佣金记录表';