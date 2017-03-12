USE red_envelope;

DROP TABLE IF EXISTS re_user_andriod_app;
CREATE TABLE re_user_andriod_app(
  id                  BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id             INT(10) NOT NULL COMMENT '用户id',
  app_package         VARCHAR(256) NULL COMMENT 'app包名',
  app_name            VARCHAR(256) NULL COMMENT 'app名称',
  record_time         varchar(20) NULL COMMENT '记录时间,如:2016-10-14 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='用户andriod app实时表';
ALTER TABLE re_user_andriod_app ADD INDEX IDX_USER_ID (USER_ID);


DROP TABLE IF EXISTS re_user_andriod_app_history;
CREATE TABLE re_user_andriod_app_history(
  id                  BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id             INT(10) NOT NULL COMMENT '用户id',
  app_package         VARCHAR(256) NULL COMMENT 'app包名',
  app_name            VARCHAR(256) NULL COMMENT 'app名称',
  record_time         varchar(20) NULL COMMENT '记录时间,如:2016-10-14 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='用户andriod app历史表';
ALTER TABLE re_user_andriod_app_history ADD INDEX IDX_USER_ID_APP_PACKAGE (USER_ID,APP_PACKAGE);


DROP TABLE IF EXISTS re_app_market;
CREATE TABLE re_app_market(
  market_id                INT(4) UNSIGNED AUTO_INCREMENT COMMENT 'id',
  market_name              VARCHAR(50) NULL COMMENT '市场名称',
  market_package           VARCHAR(100) NULL COMMENT '包名',
  market_url               VARCHAR(256) NULL COMMENT '市场下载地址',
  market_icon              VARCHAR(256) NULL COMMENT '应用市场图标',
  market_title_icon        VARCHAR(256) NULL COMMENT '市场名图',
  market_order             TINYINT(3) NOT NULL DEFAULT 0 COMMENT '排序,值较小者排在较前',
  PRIMARY KEY (market_id)
) ENGINE=INNODB COMMENT='应用市场表';


DROP TABLE IF EXISTS re_app;
CREATE TABLE re_app(
  app_id                  BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  app_name                VARCHAR(128) NULL COMMENT 'app名称',
  app_size                VARCHAR(20) NULL COMMENT 'app大小',
  market_id               INT(4) NULL COMMENT '市场id',
  app_package             VARCHAR(100) NULL COMMENT '包名',
  app_icon                VARCHAR(256) NULL COMMENT 'app图片',
  app_desc                VARCHAR(256) NULL COMMENT 'app描述',
  create_time             varchar(20) NULL COMMENT '创建时间,如:2016-08-18',
  update_time             VARCHAR(20) NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (app_id),
  UNIQUE KEY `app_key` (market_id, app_package)
) ENGINE=INNODB COMMENT='app表';


DROP TABLE IF EXISTS re_task_label;
CREATE TABLE re_task_label(
  label_id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  task_label              VARCHAR(256) NULL COMMENT '关键词',
  PRIMARY KEY (label_id)
) ENGINE=INNODB COMMENT='任务标签表';


DROP TABLE IF EXISTS re_app_keywords;
CREATE TABLE re_app_keywords(
  keyword_id                BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  app_id                    BIGINT NOT NULL COMMENT '此关键词对应的appID',
  keyword                   VARCHAR(256) NULL COMMENT '关键词',
  money                     decimal(10,2) unsigned DEFAULT '1.00' COMMENT '金额',
  total_num                 INT(10) NULL COMMENT '总次数',
  left_num                  INT(10) NULL COMMENT '剩余次数',
  task_label                VARCHAR(256) NULL COMMENT '标签',
  keyword_weight            TINYINT(3) NULL DEFAULT 0 COMMENT '权重大的靠前',
  release_time              varchar(20) NULL COMMENT '投放时间,如:2016-08-18 12:53:30',
  end_time                  varchar(20) NULL COMMENT '结束时间,如:2016-08-18 12:53:30',
  create_time               varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  update_time               VARCHAR(20) NULL COMMENT '修改时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (keyword_id)
) ENGINE=INNODB COMMENT='关键词表';


DROP TABLE IF EXISTS re_app_task;
CREATE TABLE re_app_task(
  task_id                   BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id                   int(10) unsigned NOT NULL COMMENT '用户ID',
  device_id                 VARCHAR(100) NULL COMMENT '设备Id',
  keyword_id                BIGINT NOT NULL COMMENT '关键词id',
  app_id                    BIGINT NOT NULL COMMENT '此关键词对应的appID',
  task_step                 tinyint(1) DEFAULT 1 COMMENT '当前执行步骤',
  task_status               tinyint(1) DEFAULT 0 COMMENT '状态; 0:进行中,1:已完成,2:已放弃',
  create_time               BIGINT NULL COMMENT '创建时间',
  update_time               BIGINT NULL COMMENT '更新时间',
  PRIMARY KEY (task_id)
) ENGINE=INNODB COMMENT='用户任务表';