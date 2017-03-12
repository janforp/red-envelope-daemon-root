DROP TABLE IF EXISTS re_red_banner;
CREATE TABLE re_red_banner(
  banner_id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'BannerID',
  banner_title          varchar(64)  NOT NULL COMMENT 'Banner标题',
  banner_img            varchar(256)  NOT NULL COMMENT 'Banner图片',
  banner_status         tinyint(1) DEFAULT 2 COMMENT 'Banner状态; 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  banner_url            varchar(256)  DEFAULT NULL COMMENT  'Banner链接',
  banner_weight         tinyint(3) unsigned DEFAULT 0 COMMENT '权重(0-100),大的排前面',
  min_version_code      int(10) NULL DEFAULT 0 COMMENT '最低显示版本号',
  max_version_code      int(10) NULL DEFAULT 0 COMMENT '最高显示版本号',
  show_channel_name     LONGTEXT NULL COMMENT '显示的渠道名',
  show_package_name     LONGTEXT NULL COMMENT '显示的包名',
  is_show               TINYINT(1) NULL DEFAULT 0 COMMENT '审核状态是否显示（默认为0） 0-不显示；1-显示',
  PRIMARY KEY (banner_id)
) ENGINE=InnoDB COMMENT='红包池banner';


DROP TABLE IF EXISTS re_user_red;
CREATE TABLE re_user_red(
  red_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  user_id           INT(10) NOT NULL COMMENT '发红包用户id',
  red_total_num     INT(10) NOT NULL COMMENT '红包总个数',
  red_left_num      INT(10) NOT NULL COMMENT '红包剩余个数',
  single_money      decimal(10,2) unsigned DEFAULT '0.00' COMMENT '单个红包金额(元)',
  total_money       decimal(10,2) unsigned DEFAULT '0.00' COMMENT '总金额(元)',
  red_content       VARCHAR(256) NULL COMMENT '红包内容',
  is_refund         tinyint(1) DEFAULT 0 COMMENT '如果未抢完,是否已经退回: 0-未退回 1-已退回',
  create_time       VARCHAR(20) NULL COMMENT '创建时间',
  create_time_ms    BIGINT NOT NULL COMMENT '创建时间',
  update_time       VARCHAR(20) NULL COMMENT '更新时间',
  refund_time       BIGINT NOT NULL COMMENT '退还时间',
  PRIMARY KEY (red_id)
) ENGINE=INNODB COMMENT='用户发红包表';


DROP TABLE IF EXISTS re_user_red_detail;
CREATE TABLE re_user_red_detail(
  detail_id         BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  red_id            BIGINT NOT NULL COMMENT '发红包用户id',
  user_id           INT(10) NOT NULL COMMENT '抢红包用户id',
  red_money         decimal(10,2) unsigned DEFAULT '0.00' COMMENT '红包金额(元)',
  create_time       VARCHAR(20) NULL COMMENT '创建时间',
  PRIMARY KEY (detail_id)
) ENGINE=INNODB COMMENT='用户抢红包表';
ALTER TABLE re_user_red_detail ADD INDEX IDX_RED_ID (RED_ID);