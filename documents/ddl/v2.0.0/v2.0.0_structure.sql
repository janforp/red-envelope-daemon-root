USE red_envelope;

DROP TABLE IF EXISTS re_coin_item;
CREATE TABLE re_coin_item(
  item_id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT '商品id',
  item_title          VARCHAR(256) NULL COMMENT '标题',
  item_img            VARCHAR(256) NULL COMMENT '图片',
  item_coin           int(10) unsigned NOT NULL COMMENT '金币',
  item_desc           VARCHAR(600) NULL COMMENT '描述',
  item_type           TINYINT(3) NULL COMMENT '商品类型0:实物，1:虚拟商品，将来会扩展',
  PRIMARY KEY (item_id)
) ENGINE=InnoDB COMMENT='金币商城商品';


DROP TABLE IF EXISTS re_discover_banner;
CREATE TABLE re_discover_banner(
  banner_id               int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'BannerID',
  banner_title            varchar(64)  NOT NULL COMMENT 'Banner标题',
  banner_img              varchar(256)  NOT NULL COMMENT 'Banner图片',
  banner_status           tinyint(1) DEFAULT 2 COMMENT 'Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  banner_url              varchar(256)  DEFAULT NULL COMMENT  'Banner链接',
  banner_order            tinyint(3) unsigned DEFAULT 0 COMMENT 'Banner排序，值越小，越靠前',
  limit_version           VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  max_version             VARCHAR(45) NULL DEFAULT '9.9.9' COMMENT '最高版本',
  limit_channel_name      LONGTEXT NULL COMMENT '显示的渠道名',
  limit_package_name      LONGTEXT NULL COMMENT '显示的包名',
  is_show                 TINYINT(1) NULL DEFAULT 0 COMMENT '审核状态是否显示（默认为0） 0-不显示；1-显示',
  min_version_code        INT(10) NULL DEFAULT 0 COMMENT '最低显示版本号',
  max_version_code        INT(10) NULL DEFAULT 10000 COMMENT '最高显示版本号',
  PRIMARY KEY (banner_id)
) ENGINE=InnoDB COMMENT='发现banner';


DROP TABLE IF EXISTS re_integral_wall;
CREATE TABLE re_integral_wall(
  wall_id                 int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '积分墙ID',
  wall_title              varchar(64)  NOT NULL COMMENT '标题',
  wall_desc               VARCHAR(128) NULL COMMENT '描述',
  wall_img                varchar(256)  NOT NULL COMMENT '图片',
  wall_status             tinyint(1) DEFAULT 2 COMMENT '状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  wall_url                varchar(256)  DEFAULT NULL COMMENT  '链接',
  wall_order              tinyint(3) unsigned DEFAULT 0 COMMENT '排序，值越小，越靠前',
  limit_version           VARCHAR(45) DEFAULT 0 NULL COMMENT '最低显示版本',
  max_version             VARCHAR(45) NULL DEFAULT '9.9.9' COMMENT '最高版本',
  limit_channel_name      LONGTEXT NULL COMMENT '显示的渠道名',
  limit_package_name      LONGTEXT NULL COMMENT '显示的包名',
  PRIMARY KEY (wall_id)
) ENGINE=InnoDB COMMENT='积分墙';


DROP TABLE IF EXISTS re_zy_notify;
CREATE TABLE re_zy_notify(
  order_id            VARCHAR(100) NOT NULL COMMENT '订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  app_id              VARCHAR(100) NULL COMMENT '开发者应用ID',
  ad_id               int(10) NULL COMMENT '广告id',
  ad_name             VARCHAR(255) NULL COMMENT '广告标题',
  integral            int(10) NULL COMMENT '积分',
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  package_name        varchar(255) NULL COMMENT '包名',
  price               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  day_num             int(10) NULL COMMENT '回调的任务为第几天任务',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB COMMENT='中亿回调记录';


DROP TABLE IF EXISTS re_ym_andriod;
CREATE TABLE re_ym_andriod(
  order_id            VARCHAR(100) NOT NULL COMMENT '订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。',
  app_id              VARCHAR(100) NULL COMMENT '开发者应用ID',
  ad_name             VARCHAR(255) NULL COMMENT '广告标题',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  channel_name        VARCHAR(100) NULL COMMENT '渠道号',
  points              decimal(10,2) unsigned DEFAULT '0.00' COMMENT '用户可以赚取的积分',
  ad_id               int(10) NULL COMMENT '广告id',
  package_name        varchar(255) NULL COMMENT '应用包名',
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  price               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  trade_type          VARCHAR(3) NULL COMMENT '回调的任务类型。1=>主任务；2=>附加任务(附加任务可能会有多个)；3=>分享主任务；4=>深度分享任务',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB COMMENT='有米andriod回调记录';


DROP TABLE IF EXISTS re_ym_ios;
CREATE TABLE re_ym_ios(
  order_id            VARCHAR(100) NOT NULL COMMENT '订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。',
  app_id              VARCHAR(100) NULL COMMENT '开发者应用ID',
  ad_name             VARCHAR(255) NULL COMMENT '广告标题',
  ad_id               int(10) NULL COMMENT '广告id',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  channel_name        VARCHAR(100) NULL COMMENT '渠道号',
  price               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  points              decimal(10,2) unsigned DEFAULT '0.00' COMMENT '用户可以赚取的积分',
  store_id            VARCHAR(100) NULL COMMENT '应用商店的 Id，该值在某些任务类型可能为空',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB COMMENT='有米ios回调记录';


DROP TABLE IF EXISTS re_bd_notify;
CREATE TABLE re_bd_notify(
  id                  BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  device_id           VARCHAR(100) NULL COMMENT '设备Id',
  app_id              VARCHAR(100) NULL COMMENT '开发者应用ID',
  currency            decimal(10,2) unsigned DEFAULT '0.00' COMMENT '虚拟货币',
  ratio               decimal(10,2) unsigned DEFAULT '100.00' COMMENT '汇率：1元钱=多少积分(>=1)   注:默认值100',
  time_stamp          BIGINT unsigned NOT NULL COMMENT '时间戳',
  ad_name             VARCHAR(255) NULL COMMENT '广告的名称',
  ad_packname         varchar(255) NULL COMMENT '广告的包名',
  trade_type          TINYINT(3) NULL COMMENT '广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='贝多回调记录';


DROP TABLE IF EXISTS re_dc_notify;
CREATE TABLE re_dc_notify(
  order_id            VARCHAR(100) NOT NULL COMMENT '订单号，每个订单号都具有唯一性；用于排重',
  device_id           VARCHAR(100) NULL COMMENT '设备号，手机唯一，即手机的IMEI或 MAC地址',
  app_id              VARCHAR(100) NULL COMMENT '在点财的应用所对应的APPID值',
  score               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '虚拟货币',
  rate                decimal(10,2) unsigned DEFAULT '100.00' COMMENT '汇率：1元钱=多少积分(>=1)   注:默认值100',
  trade_type          TINYINT(3) NULL COMMENT '广告任务的类型  0-安装激活任务； 1(或大于1)-签到任务',
  ad_name             VARCHAR(255) NULL COMMENT '广告的名称',
  ad_packname         varchar(255) NULL COMMENT '广告的包名',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB COMMENT='点财回调记录';


DROP TABLE IF EXISTS re_dtn_notify;
CREATE TABLE re_dtn_notify(
  order_id            VARCHAR(100) NOT NULL COMMENT '订单号，每个订单号都具有唯一性；用于排重',
  app_id              VARCHAR(32) NULL COMMENT '在点财的应用所对应的APPID值',
  udid                VARCHAR(50) NULL COMMENT '设备识别码(IMEI)',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  currency            decimal(10,2) unsigned DEFAULT '0.00' COMMENT '充值的虚拟币金额',
  ad_name             VARCHAR(255) NULL COMMENT '激活产品名称',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB COMMENT='大头鸟回调记录';


DROP TABLE IF EXISTS re_dianru_notify;
CREATE TABLE re_dianru_notify(
  hash_id             VARCHAR(100) NOT NULL COMMENT '唯一标识 ID',
  app_id              VARCHAR(32) NULL COMMENT '开发者应用 ID',
  ad_id               VARCHAR(32) NULL COMMENT '广告 ID',
  ad_name             VARCHAR(255) NULL COMMENT '广告名称',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  mac_add             VARCHAR(100) NULL COMMENT 'mac 地址',
  device_id           VARCHAR(100) NULL COMMENT '设备唯一标识(IMEI)',
  source              VARCHAR(100) NULL COMMENT '渠道来源',
  point               int(10) NULL COMMENT '积分',
  notify_time         varchar(20) NULL COMMENT '回调时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (hash_id)
) ENGINE=InnoDB COMMENT='点入回调记录';


DROP TABLE IF EXISTS re_white_list;
CREATE TABLE re_white_list(
  mobile             VARCHAR(11) NOT NULL COMMENT '手机号',
  PRIMARY KEY (mobile)
) ENGINE=InnoDB COMMENT='登录白名单';