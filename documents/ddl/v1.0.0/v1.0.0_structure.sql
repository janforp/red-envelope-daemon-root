/**
DROP DATABASE IF EXISTS red_envelope;
CREATE DATABASE red_envelope DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
**/

USE red_envelope;

DROP TABLE IF EXISTS re_package_channel;
CREATE TABLE re_package_channel(
  channel_name        varchar(20) NOT NULL COMMENT '如:Wandoujia',
  package_name        varchar(40) NOT NULL COMMENT '如:com.wj.hongbao',
  app_id              TINYINT(3) NULL DEFAULT 0 COMMENT 'app的渠道包名id',
  app_name            varchar(200) NULL COMMENT '如:红包达人',
  app_version         varchar(20) NULL COMMENT '版本名,如:1.0.0',
  version_code        int(10) NULL COMMENT '版本号',
  app_icon            varchar(200) NULL COMMENT '如:http://dev.image.lswuyou.cn/hongbao/0611389f-3300-4cfe-a40a-0e7448c25f3a',
  apk_url             VARCHAR(512) NULL COMMENT 'apk链接',
  update_remark       LONGTEXT NULL COMMENT '更新说明',
  is_force            TINYINT(1) NULL DEFAULT 0 COMMENT '是否强制更新 0-否， 1-是',
  status              TINYINT(1) NULL COMMENT '状态,如 0-审核中, 1-已通过,2-未通过',
  notes               varchar(400) NOT NULL COMMENT '备注',
  upload_time         varchar(20) NULL COMMENT '上传时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (channel_name, package_name)
) ENGINE=InnoDB COMMENT='渠道包名组合表';


DROP TABLE IF EXISTS re_user;
CREATE TABLE re_user(
  user_id         int(10) UNSIGNED AUTO_INCREMENT COMMENT 'user_id，自增长',
  re_id           VARCHAR(32) NOT NULL COMMENT 're_id，对外展示的id，非空，唯一，只能修改一次',
  nickname        VARCHAR(32) NOT NULL COMMENT '昵称，非空',
  real_name       VARCHAR(32) NULL COMMENT '真实姓名',
  portrait        VARCHAR(256) COMMENT '头像，可空',
  user_key        VARCHAR(36) NOT NULL COMMENT 'user_key，非空，唯一（UUID），不可修改',
  user_secret     VARCHAR(32) NOT NULL COMMENT 'user_secret，非空；用于用户与服务端通信时，采用的对称加密秘钥',
  mobile          VARCHAR(11) NULL COMMENT  '用户手机',
  password        VARCHAR(32) NOT NULL COMMENT  '密码',
  gender          TINYINT(3) DEFAULT 0 NOT NULL COMMENT '性别，非空，默认0；0：未知；1：男；2：女；',
  birthday        VARCHAR(10) NULL COMMENT '生日，可空',
  profession      VARCHAR(50) NULL COMMENT  '职业',
  hobby           VARCHAR(150) NULL COMMENT '爱好',
  address         VARCHAR(150) NULL COMMENT  '地址',
  user_money      decimal(10,2) unsigned DEFAULT '0.00' COMMENT '账户余额',
  today_money     decimal(10,2) unsigned DEFAULT '0.00' COMMENT '今日已赚',
  user_score      int(10) unsigned NOT NULL DEFAULT '0' COMMENT '账户积分',
  sign_time       BIGINT NULL COMMENT '签到时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  sign_count      int(10) DEFAULT 0 NOT NULL  COMMENT '连续签到次数',
  bind_type       TINYINT(3) NOT NULL COMMENT '绑定类型；0:无, 1：微信，2：QQ，3：微博',
  user_status     TINYINT(3) DEFAULT 1 NOT NULL COMMENT '用户状态，非空，默认1（有效）;0：封号；1：有效',
  user_type       TINYINT(3) DEFAULT 0 NOT NULL COMMENT '用户类型，非空，默认0（普通用户）;0：普通用户；1：内部用户；',
  invitation_code VARCHAR(10) NOT NULL COMMENT '邀请码',
  app_id          TINYINT(3) NULL DEFAULT 0 COMMENT '注册时app的渠道包名id',
  create_time     BIGINT NOT NULL COMMENT '注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  free_times      TINYINT(1) NULL DEFAULT 1 COMMENT '免费抢红包机会',
  gain_times      INT(4) NULL DEFAULT 0 COMMENT '获得的抢红包机会',
  mission_times   INT(4) NULL DEFAULT 0 COMMENT '发布悬赏任务的机会',
  update_time     BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='注册用户表';
ALTER TABLE re_user ADD INDEX IDX_USER_KEY_USER_SECRET (USER_KEY,USER_SECRET);
ALTER TABLE re_user ADD INDEX IDX_MOBILE (MOBILE);


DROP TABLE IF EXISTS re_user_channel;
CREATE TABLE re_user_channel(
  id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  user_id           INT(10) NOT NULL COMMENT '用户id',
  platform          TINYINT(1) NULL COMMENT '平台 0-iOS, 1-Android',
  device_id         VARCHAR(200) NULL COMMENT '设备Id',
  device_name       VARCHAR(200) NULL COMMENT '设备名称',
  app_id            TINYINT(3) NULL DEFAULT 0 COMMENT '注册时app的渠道包名id',
  package_name      VARCHAR(100) NULL COMMENT '包名',
  channel_name      VARCHAR(100) NULL COMMENT '渠道',
  app_version       VARCHAR(45) NULL COMMENT 'app版本',
  user_ip           varchar(255) NULL COMMENT '注册ip',
  user_imsi         VARCHAR(255) NULL COMMENT '国际移动用户识别码imsi',
  user_imei         VARCHAR(255) NULL COMMENT '国际移动设备身份码imei',
  create_time       VARCHAR(20) NULL COMMENT '时间',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='注册用户来源表';
ALTER TABLE re_user_channel ADD INDEX IDX_USER_ID (USER_ID);


DROP TABLE IF EXISTS re_user_login_detail;
CREATE TABLE re_user_login_detail(
  id                  BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  platfrom            tinyint(1) NOT NULL COMMENT '平台; 0:ios ,1:andriod ',
  version             varchar(20) NULL COMMENT '版本',
  package_name        VARCHAR(255) NULL COMMENT '包名',
  channel_name        VARCHAR(255) NULL COMMENT '渠道名',
  device_id           VARCHAR(255) NULL COMMENT '设备Id',
  device_name         VARCHAR(255) NULL COMMENT '设备名称',
  login_ip            varchar(255) NULL COMMENT '登录IP',
  login_address       varchar(255) NULL COMMENT '登录地址',
  login_longitude     varchar(255) NULL COMMENT '经度',
  login_latitude      varchar(255) NULL COMMENT '纬度',
  login_time          varchar(20) NULL COMMENT '登录时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='用户登录明细表';
ALTER TABLE re_user_login_detail ADD INDEX IDX_USER_ID (USER_ID);


DROP TABLE IF EXISTS re_user_bind;
CREATE TABLE re_user_bind(
  user_id                 int(10) UNSIGNED NOT NULL COMMENT 're_user.user_id，主键',
  bind_type               TINYINT(3) NOT NULL COMMENT '绑定类型；0:无, 1：微信，2：QQ，3：微博',
  bind_id                 VARCHAR(32) NOT NULL COMMENT '第三方登录获得的openId/uid',
  bind_time               BIGINT NOT NULL COMMENT '创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (user_id)
) ENGINE=INNODB COMMENT='第三方登录账户绑定表';


DROP TABLE IF EXISTS re_banner;
CREATE TABLE re_banner(
  banner_id             int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'BannerID',
  banner_title          varchar(64)  NOT NULL COMMENT 'Banner标题',
  banner_img            varchar(256)  NOT NULL COMMENT 'Banner图片',
  banner_status         tinyint(1) DEFAULT 2 COMMENT 'Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  banner_url            varchar(256)  DEFAULT NULL COMMENT  'Banner链接',
  banner_order          tinyint(3) unsigned DEFAULT 0 COMMENT 'Banner排序，值越小，越靠前',
  limit_version         VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  max_version           VARCHAR(45) NULL DEFAULT '9.9.9' COMMENT '最高版本',
  limit_channel_name    LONGTEXT NULL COMMENT '显示的渠道名',
  limit_package_name    LONGTEXT NULL COMMENT '显示的包名',
  is_show               TINYINT(1) NULL DEFAULT 0 COMMENT '审核状态是否显示（默认为0） 0-不显示；1-显示',
  min_version_code      INT(10) NULL DEFAULT 0 COMMENT '最小显示版本号',
  max_version_code      INT(10) NULL DEFAULT 10000 COMMENT '最大显示版本号',
  PRIMARY KEY (banner_id)
) ENGINE=InnoDB COMMENT='首页banner';


DROP TABLE IF EXISTS re_verify_code;
CREATE TABLE re_verify_code (
  id                  BIGINT UNSIGNED AUTO_INCREMENT,
  code                VARCHAR(6) NOT NULL COMMENT '验证码值',
  cellphone           VARCHAR(16) NOT NULL COMMENT '手机号',
  generate_time       BIGINT NOT NULL COMMENT '验证码生成时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  code_verify_status  TINYINT(3) DEFAULT 0 NOT NULL COMMENT 'code的状态 0：未验证，1：验证通过，2：验证失败；（1小时内有效）',
  code_verify_time    BIGINT DEFAULT NULL COMMENT 'code验证时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='短信验证码';
ALTER TABLE re_verify_code ADD  INDEX cellphone (cellphone);


DROP TABLE IF EXISTS re_navigation;
CREATE TABLE re_navigation(
  navigation_id         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'navigationID',
  navigation_title      varchar(64)  NOT NULL COMMENT 'navigation标题',
  navigation_img        varchar(256)  NOT NULL COMMENT 'navigation图片',
  navigation_status     tinyint(1) DEFAULT 2 COMMENT 'navigation状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  navigation_url        varchar(256)  DEFAULT NULL COMMENT  'navigation链接',
  navigation_order      tinyint(3) unsigned DEFAULT 0 COMMENT 'navigation排序，值越小，越靠前',
  limit_version         VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  max_version           VARCHAR(45) NULL DEFAULT '9.9.9' COMMENT '最高版本',
  limit_channel_name    LONGTEXT NULL COMMENT '显示的渠道名',
  limit_package_name    LONGTEXT NULL COMMENT '显示的包名',
  is_show               TINYINT(1) NULL DEFAULT 0 COMMENT '审核状态是否显示（默认为0） 0-不显示；1-显示',
  PRIMARY KEY (navigation_id)
) ENGINE=InnoDB COMMENT='首页navigation';


DROP TABLE IF EXISTS re_discover;
CREATE TABLE re_discover(
  discover_id           INT(10) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  discover_title        VARCHAR(128) NULL COMMENT '标题',
  discover_description  VARCHAR(128) NULL COMMENT '描述',
  discover_img          VARCHAR(256) NULL COMMENT '图片',
  discover_url          VARCHAR(256) NULL COMMENT '链接',
  discover_status       TINYINT(1) DEFAULT 2 COMMENT 'discover状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  discover_order        TINYINT(3) UNSIGNED DEFAULT 0 COMMENT '排序，值越小，越靠前',
  limit_version         VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  max_version           VARCHAR(45) NULL DEFAULT '9.9.9' COMMENT '最高版本',
  limit_channel_name    LONGTEXT NULL COMMENT '显示的渠道名',
  limit_package_name    LONGTEXT NULL COMMENT '显示的包名',
  PRIMARY KEY (discover_id)
) ENGINE=INNODB COMMENT='发现列表';


DROP TABLE IF EXISTS re_start_ad;
CREATE TABLE re_start_ad(
  ad_id               INT(10) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  ad_title            VARCHAR(128) NULL COMMENT '标题',
  ad_img              VARCHAR(256) NULL COMMENT '图片',
  ad_url              VARCHAR(256) NULL COMMENT '链接',
  ad_status           TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  ad_skip             TINYINT(1) DEFAULT 0 COMMENT '能否跳过; 0:能,1:不能',
  ad_duration         TINYINT(3) DEFAULT 5 COMMENT '持续时间(秒); 默认5秒',
  PRIMARY KEY (ad_id)
) ENGINE=INNODB COMMENT='启动页广告';


DROP TABLE IF EXISTS re_fixed_red;
CREATE TABLE re_fixed_red(
  fixed_id            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  fixed_title         VARCHAR(128) NULL COMMENT '名称',
  fixed_amount        int(10) NOT NULL COMMENT '红包数量',
  fixed_remainder     int(10)  NULL DEFAULT 0 COMMENT '剩余红包数量',
  fixed_url           VARCHAR(256) NULL COMMENT '详情链接',
  ad_url              VARCHAR(256) NULL COMMENT '跳转链接',
  fixed_hour          VARCHAR(4) NULL COMMENT '时',
  fixed_minute        VARCHAR(4) NULL COMMENT '分',
  fixed_second        VARCHAR(4) NULL COMMENT '秒',
  fixed_status        TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  PRIMARY KEY (fixed_id)
) ENGINE=InnoDB COMMENT='定时红包';


DROP TABLE IF EXISTS re_sort_red;
CREATE TABLE re_sort_red(
  red_id                INT(10) UNSIGNED AUTO_INCREMENT COMMENT '红包ID',
  red_name              VARCHAR(128) NULL COMMENT '红包名字',
  red_img               VARCHAR(128) NULL COMMENT '红包图标',
  merchant_name         VARCHAR(128) NULL COMMENT '商家名字',
  merchant_detail       VARCHAR(128) NULL COMMENT '商家详情',
  red_reward_desc       VARCHAR(128) NULL COMMENT '奖励描述,如:100元红包券,10元观影券,购物券等',
  reward_money          INT(6) NULL DEFAULT 100 COMMENT '奖励金额(单位:分)',
  extra_reward_desc     VARCHAR(128) NULL COMMENT '额外奖励描述,如:100元红包券,10元观影券,购物券等',
  extra_money           INT(6) NULL DEFAULT 100 COMMENT '额外奖励金额(单位:分)',
  red_sort              INT(3) NOT NULL COMMENT '红包分类',
  red_desc              varchar(255) NULL COMMENT '红包说明',
  detail_url            VARCHAR(256) NULL COMMENT '详情链接',
  detail_deitor         longtext NULL COMMENT '详情编辑html',
  button_deitor         longtext NULL COMMENT '按钮编辑html,多个按钮之间用;;(两个分号)分开',
  red_order             INT(4)   NOT NULL DEFAULT 0 COMMENT '排序,值较小者排在较前',
  red_status            TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  show_or_not           TINYINT(1) NULL DEFAULT 1 COMMENT '任务开关,控制是否需要在页面显示,0:不显示,1:显示',
  start_time            BIGINT NOT NULL COMMENT '开始时间',
  end_time              BIGINT NOT NULL COMMENT '结束时间',
  create_time           BIGINT NOT NULL COMMENT '创建时间',
  PRIMARY KEY (red_id)
) ENGINE=INNODB COMMENT='红包表';


DROP TABLE IF EXISTS re_score_detail;
CREATE TABLE re_score_detail(
  score_id            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  app_id              TINYINT(3) NULL DEFAULT 0 COMMENT '注册时app的渠道包名id',
  score               INT(10) NULL DEFAULT 0 COMMENT '积分数量',
  score_type          tinyint(1) NULL COMMENT '类型;0:消费积分,1:获得积分',
  score_content       varchar(255) NULL COMMENT '说明',
  score_time          varchar(20) NULL COMMENT '时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (score_id)
) ENGINE=InnoDB COMMENT='积分明细表';
ALTER TABLE re_score_detail ADD INDEX IDX_USER_ID (USER_ID);
ALTER TABLE re_score_detail ADD INDEX IDX_APP_ID_SCORE_TYPE (APP_ID,SCORE_TYPE);


DROP TABLE IF EXISTS re_withdraw_sort;
CREATE TABLE re_withdraw_sort(
  withdraw_id                INT(10) UNSIGNED AUTO_INCREMENT COMMENT '提现分类ID',
  withdraw_type              VARCHAR(128) NULL COMMENT '提现类型',
  withdraw_name              VARCHAR(128) NULL COMMENT '名字',
  withdraw_img               VARCHAR(128) NULL COMMENT '图标',
  withdraw_explain           VARCHAR(128) NULL COMMENT '副标题',
  withdraw_url               VARCHAR(256) NULL COMMENT '跳转链接',
  withdraw_money             VARCHAR(128) NULL COMMENT '提现金额,如:10&30&50',
  to_account_money           VARCHAR(128) NULL COMMENT '到账金额,如:9&29.5&49.9',
  withdraw_desc              longtext NULL COMMENT '提现说明',
  withdraw_times             TINYINT(3) NULL DEFAULT 1 COMMENT '提现次数',
  withdraw_status            TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  withdraw_order             TINYINT(3)   NOT NULL DEFAULT 0 COMMENT '排序,值较小者排在较前',
  PRIMARY KEY (withdraw_id)
) ENGINE=INNODB COMMENT='提现分类表';


DROP TABLE IF EXISTS re_account_detail;
CREATE TABLE re_account_detail(
  detail_id           BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  app_id              TINYINT(3) NULL DEFAULT 0 COMMENT '注册时app的渠道包名id',
  account_money       decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  detail_type         tinyint(1) NULL COMMENT '类型;0:支出,1:收入',
  mission_type        TINYINT(3) NULL DEFAULT 0 COMMENT '任务分类',
  mission_subtype     TINYINT(3) NULL DEFAULT 0 COMMENT '任务子分类',
  detail_content      varchar(255) NULL COMMENT '详情',
  detail_time         varchar(20) NULL COMMENT '时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (detail_id)
) ENGINE=InnoDB COMMENT='会员账户明细表';
ALTER TABLE re_account_detail ADD INDEX IDX_APP_ID (APP_ID);
ALTER TABLE re_account_detail ADD INDEX IDX_US_MI_MI (USER_ID,MISSION_SUBTYPE,MISSION_TYPE);


DROP TABLE IF EXISTS re_receive_detail;
CREATE TABLE re_receive_detail(
  detail_id           BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  red_type            tinyint(1) NULL COMMENT '红包类型 0-定时红包,1-兑换码红包,2-现金红包',
  red_id              int(10) unsigned NOT NULL COMMENT '红包ID',
  red_money           decimal(10,2) unsigned DEFAULT '0.00' COMMENT '红包金额',
  red_order           INT(4) NULL DEFAULT NULL COMMENT '抢红包的时间排序，对于某一个定时红包，用户的先后排名，根据此排名发放前三名的奖励',
  detail_time         varchar(20) NULL COMMENT '时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (detail_id)
) ENGINE=InnoDB COMMENT='红包领取明细表';


DROP TABLE IF EXISTS re_withdraw_bind;
CREATE TABLE re_withdraw_bind(
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  open_id             VARCHAR(40) NOT NULL COMMENT '微信openid',
  nickname            VARCHAR(128) NULL COMMENT '微信昵称',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB COMMENT='提现微信绑定表';


DROP TABLE IF EXISTS re_withdraw_detail;
CREATE TABLE re_withdraw_detail(
  withdraw_id         BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  withdraw_type       VARCHAR(128) NULL COMMENT '提现类型',
  withdraw_account    VARCHAR(128) NULL COMMENT '提现账户',
  account_name        VARCHAR(128) NULL COMMENT '账户名称',
  withdraw_status     tinyint(1) NOT NULL DEFAULT 0 COMMENT '提现状态; 0:审核中, 1:已完成, 2:未通过',
  remarks             VARCHAR(256) NULL COMMENT '备注',
  apply_money         decimal(10,2) unsigned NOT NULL COMMENT '申请金额(元)',
  withdraw_money      decimal(10,2) unsigned NOT NULL COMMENT '到账金额(元)',
  apply_time          varchar(20) NULL COMMENT '申请时间,如:2016-08-18 12:53:30',
  withdraw_time       varchar(20) NULL COMMENT '确认时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (withdraw_id)
) ENGINE=InnoDB COMMENT='提现明细表';


DROP TABLE IF EXISTS re_score_exchange;
CREATE TABLE re_score_exchange(
  exchange_id                INT(10) UNSIGNED AUTO_INCREMENT COMMENT '金币兑换分类id',
  exchange_img               VARCHAR(256) NULL COMMENT '图标',
  exchange_name              VARCHAR(128) NULL COMMENT '名称',
  exchange_desc              longtext NULL COMMENT '说明',
  exchange_url               VARCHAR(256) NULL COMMENT '跳转链接',
  exchange_status            TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  exchange_order             TINYINT(3)   NOT NULL DEFAULT 0 COMMENT '排序,值较小者排在较前',
  PRIMARY KEY (exchange_id)
) ENGINE=INNODB COMMENT='金币兑换列表';


DROP TABLE IF EXISTS re_code_red;
CREATE TABLE re_code_red(
  code_id                   INT(10) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  red_code                  VARCHAR(20) NULL COMMENT '红包口令',
  customer_name             VARCHAR(64) NULL COMMENT '客户名称',
  customer_img              VARCHAR(128) NULL COMMENT '客户头像',
  customer_desc             VARCHAR(128) NULL COMMENT '客户描述',
  award_desc                VARCHAR(128) NULL COMMENT '奖励说明',
  customer_banner           VARCHAR(128) NULL COMMENT 'banner图',
  customer_banner_url       VARCHAR(128) NULL COMMENT 'banner链接',
  red_max                   VARCHAR(10) NULL COMMENT '最大红包(展示用)',
  red_desc                  longtext NULL COMMENT '红包领取规则',
  red_num_total             INT(10) NULL DEFAULT 0 COMMENT '红包总个数',
  red_num_left              INT(10) NULL DEFAULT 0 COMMENT '红包剩余个数',
  red_num_day_total         INT(10) NULL DEFAULT 0 COMMENT '当天红包总个数',
  red_num_day_left          INT(10) NULL DEFAULT 0 COMMENT '当天红包剩余个数',
  code_status               TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  create_time               BIGINT NOT NULL COMMENT '注册时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  update_time               BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (code_id)
) ENGINE=INNODB COMMENT='兑换码红包配置表';


DROP TABLE IF EXISTS re_code_red_detail;
CREATE TABLE re_code_red_detail(
  id                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  code_id               INT(10) NOT NULL COMMENT '红包id',
  code_money            decimal(10,2) unsigned NOT NULL COMMENT '红包金额(元)',
  code_status           TINYINT(1) NULL DEFAULT 0 COMMENT '红包状态 0-未领取,1-已领取',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='兑换码红包表';


DROP TABLE IF EXISTS re_rotate_detail;
CREATE TABLE re_rotate_detail(
  id                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id               int(10) unsigned NOT NULL COMMENT '用户ID',
  rotate_content        varchar(128) NULL COMMENT '内容',
  rotate_time           varchar(20) NULL COMMENT '时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='大转盘明细表';
ALTER TABLE re_rotate_detail ADD INDEX IDX_USER_ID (USER_ID);


DROP TABLE IF EXISTS re_exchange_detail;
CREATE TABLE re_exchange_detail(
  id                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id               int(10) unsigned NOT NULL COMMENT '用户ID',
  goods_num             BIGINT unsigned  NULL COMMENT '兑换商品编号',
  score                 INT(10) NULL COMMENT '金币',
  goods_name            varchar(256) NULL COMMENT '兑换商品的名字',
  card_id               VARCHAR(500) NULL COMMENT '虚拟商品的卡号',
  card_password         VARCHAR(500) NULL COMMENT '虚拟商品卡密',
  express_number        VARCHAR(200) NULL COMMENT '实物快递单号',
  invalid_time          VARCHAR(20) NULL COMMENT '过期时间',
  exchange_status       TINYINT(1) NULL DEFAULT 0 COMMENT '兑换状态 0-未发货,1-已发货',
  exchange_time         varchar(20) NULL COMMENT '申请兑换时间,如:2016-08-18 12:53:30',
  send_time             varchar(20) NULL COMMENT '发货时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='金币商城兑换记录表';


DROP TABLE IF EXISTS re_index_sort;
CREATE TABLE re_index_sort(
  sort_id               INT(10) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  sort_name             VARCHAR(128) NULL COMMENT '名称',
  sort_img              VARCHAR(256) NULL COMMENT '图标',
  sort_desc             VARCHAR(256) NULL COMMENT '分类详情(表名,表名,表名)',
  sort_status           TINYINT(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:Andriod开启,2:全部开启,3:全部关闭',
  sort_order            TINYINT(3) NOT NULL DEFAULT 0 COMMENT '排序,值较小者排在较前',
  limit_version         VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  limit_channel_name    LONGTEXT NULL COMMENT '最低显示版本的渠道名',
  limit_package_name    LONGTEXT NULL COMMENT '最低显示版本的包名',
  verify_version        VARCHAR(45) DEFAULT 0 NULL COMMENT '当前审核版本',
  verify_channel_package   LONGTEXT NULL COMMENT '审核通过的渠道包名',
  PRIMARY KEY (sort_id)
) ENGINE=INNODB COMMENT='首页列表分类';


DROP TABLE IF EXISTS re_umeng_push;
CREATE TABLE re_umeng_push(
  user_id               int(10) UNSIGNED NOT NULL COMMENT 're_user.user_id，主键',
  platform              TINYINT(1) NOT NULL COMMENT '平台 0-ios, 1-andriod',
  device_token          VARCHAR(128) NOT NULL COMMENT 'token',
  PRIMARY KEY (user_id)
) ENGINE=INNODB COMMENT='友盟推送token表';


DROP TABLE IF EXISTS re_user_portrait;
CREATE TABLE re_user_portrait(
  id                    INT(10) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_portrait         VARCHAR(256) NOT NULL COMMENT '头像链接',
  PRIMARY KEY (id)
) ENGINE=INNODB COMMENT='默认头像表';


DROP TABLE IF EXISTS re_address;
CREATE TABLE re_address(
  user_id         int(10) NOT NULL COMMENT '用户ID,主键',
  real_name       VARCHAR(32) NULL COMMENT '收货人姓名',
  mobile          VARCHAR(11) NULL COMMENT  '收货手机',
  province        VARCHAR(20) NULL COMMENT  '省',
  city            VARCHAR(20) NULL COMMENT  '市',
  detail_address  VARCHAR(200) NULL COMMENT  '详细地址',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='用户地址信息';


DROP TABLE IF EXISTS re_user_invitation;
CREATE TABLE re_user_invitation(
  id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id           int(10) unsigned NOT NULL COMMENT '邀请人ID',
  invited_user_id   int(10) unsigned NOT NULL COMMENT '被邀请人ID',
  invited_time      varchar(20) NULL COMMENT '邀请时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='用户邀请表';


DROP TABLE IF EXISTS re_user_commission;
CREATE TABLE re_user_commission(
  user_id           int(10) unsigned NOT NULL COMMENT '用户ID',
  current_money     decimal(10,2) unsigned NOT NULL COMMENT '当前佣金(元)',
  total_money       decimal(10,2) unsigned NOT NULL COMMENT '累计佣金(元)',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='用户佣金表';


DROP TABLE IF EXISTS re_user_commission_detail;
CREATE TABLE re_user_commission_detail(
  id                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id               int(10) unsigned NOT NULL COMMENT '用户ID',
  account_money         decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  detail_type           tinyint(1) NULL COMMENT '类型;0:支出,1:收入',
  detail_content        varchar(255) NULL COMMENT '详情',
  detail_time           varchar(20) NULL COMMENT '时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 COMMENT='用户佣金明细表';


DROP TABLE IF EXISTS re_user_commission_withdraw;
CREATE TABLE re_user_commission_withdraw(
  id                  BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  withdraw_status     tinyint(1) NOT NULL DEFAULT 0 COMMENT '提现状态; 0:未处理 ,1:已处理 ',
  apply_money         decimal(10,2) unsigned NOT NULL COMMENT '申请金额(元)',
  apply_time          varchar(20) NULL COMMENT '申请时间,如:2016-08-18 12:53:30',
  withdraw_time       varchar(20) NULL COMMENT '确认时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='用户佣金提现明细表';


DROP TABLE IF EXISTS re_user_feedback;
CREATE TABLE re_user_feedback(
  id                  BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  user_contact        varchar(255) NULL COMMENT '联系方式',
  feedback_detail     longtext NOT NULL COMMENT '内容',
  feedback_img        longtext NULL COMMENT '图片',
  feedback_time       varchar(20) NULL COMMENT '确认时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='意见反馈表';


DROP TABLE IF EXISTS re_dianjoy_notify;
CREATE TABLE re_dianjoy_notify(
  id                  BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  userId              int(10) unsigned NOT NULL COMMENT '用户ID',
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  app_id              VARCHAR(100) NULL COMMENT '在点乐的应用所对应的DIANLE_APP_ID值',
  currency            int(10) NULL COMMENT '积分，注意：不是钱',
  app_ratio           int(10) NULL COMMENT '汇率：1分钱=多少积分(>=1)',
  time_stamp          varchar(20) NULL COMMENT '时间戳',
  ad_name             varchar(255) NULL COMMENT '广告名',
  pack_name           varchar(255) NULL COMMENT '包名',
  task_id             varchar(32) NULL COMMENT '深度任务的唯一标识符',
  trade_type          varchar(3) NULL COMMENT '表示广告任务的类型 1-安装激活任务 4-次日打开深度任务',
  notify_time          varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='点乐回调记录';


DROP TABLE IF EXISTS re_mission;
CREATE TABLE re_mission(
  mission_id                INT(10) UNSIGNED AUTO_INCREMENT COMMENT '任务id',
  mission_name              VARCHAR(128) NULL COMMENT '任务名字',
  mission_img               VARCHAR(128) NULL COMMENT '任务图标',
  mission_reward            VARCHAR(128) NULL COMMENT '奖励,如:100元红包券,10元观影券,购物券等',
  gain_reward_num           INT(10) NOT NULL DEFAULT 0 COMMENT '目前获得奖励的人数',
  mission_ad_img            VARCHAR(128) NULL COMMENT '任务广告图片',
  mission_url               VARCHAR(128) NULL COMMENT '广告图片的链接,可以是网址,也可以是app下载地址',
  mission_hot               TINYINT(1) NULL DEFAULT 0 COMMENT '是否为热门任务,0:否,1:是',
  mission_sort              TINYINT(1) NULL DEFAULT 0 COMMENT '任务分类',
  mission_order             INT(4)  NOT NULL DEFAULT 0 COMMENT '任务排序,值较小者排在较前',
  participants_num          INT(10) NOT NULL DEFAULT 0 COMMENT '参加人数',
  start_time                INT(10) NOT NULL COMMENT '开始时间',
  end_time                  INT(10) NOT NULL COMMENT '结束时间',
  mission_status            TINYINT(1) NULL DEFAULT 1 COMMENT '任务状态,0:已结束,1:进行中',
  mission_step              longtext NULL COMMENT '任务步骤',
  mission_rule              longtext NULL COMMENT '任务规则',
  merchant_name             VARCHAR(128) NULL COMMENT '商家名字',
  mission_extra_reward      VARCHAR(128) NULL COMMENT '奖励,如:100元红包券,10元观影券,购物券等',
  merchant_detail           VARCHAR(128) NULL COMMENT '商家描述',
  show_or_not               TINYINT(1) NULL DEFAULT 2 COMMENT '任务开关, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  PRIMARY KEY (mission_id)
) ENGINE=INNODB COMMENT='优惠生活表';