DROP TABLE IF EXISTS re_withdraw_bind_zfb;
CREATE TABLE re_withdraw_bind_zfb(
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  alipay_account      VARCHAR(128) NOT NULL COMMENT '支付宝账户',
  full_name           VARCHAR(128) NOT NULL COMMENT '姓名',
  PRIMARY KEY (user_id)
) ENGINE=InnoDB COMMENT='提现支付宝绑定表';


DROP TABLE IF EXISTS re_recommend_mission;
CREATE TABLE re_recommend_mission (
  mission_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  mission_type          tinyint(1) DEFAULT '0' COMMENT '任务分类,0:高额任务, 1:关注任务',
  exchange_code         VARCHAR(16) NULL COMMENT '兑换码',
  mission_icon          varchar(256) NULL COMMENT '任务图标',
  mission_title         varchar(128) NULL COMMENT '任务名字',
  mission_label         varchar(256) NULL COMMENT '任务标签',
  min_money             DECIMAL(10,2) DEFAULT 0.00 COMMENT '最小金额',
  max_money             DECIMAL(10,2) DEFAULT 0.00 COMMENT '最大金额',
  start_time            varchar(20) NULL COMMENT '开始事件,如:2016-08-18 12:53:30',
  end_time              varchar(20) NULL COMMENT '结束事件,如:2016-08-18 12:53:30',
  total_num             INT(10) DEFAULT 0 COMMENT '总数量',
  left_num              INT(10) DEFAULT 0 COMMENT '剩余数量',
  mission_desc          longtext NULL COMMENT '任务简介',
  mission_status        tinyint(1) DEFAULT '2' COMMENT '显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  is_limit_time         tinyint(1) DEFAULT '0' COMMENT '是否限时, 0:限时, 1:不限时',
  limit_minute          INT(10) DEFAULT 0 COMMENT '分钟',
  is_verify             tinyint(1) DEFAULT '0' COMMENT '是否需要审核, 0:审核, 1:不审核',
  verify_text           varchar(256) NULL COMMENT '文字要求',
  verify_img            varchar(256) NULL COMMENT '图片要求',
  verify_require        LONGTEXT NULL COMMENT '审核要求',
  mission_imgs          longtext NULL COMMENT '需审核图片',
  mission_order         tinyint(3) NOT NULL DEFAULT '0' COMMENT '任务排序,值较小者排在较前',
  is_end                TINYINT(1) NULL DEFAULT 1 COMMENT '进行中 0-否  1-是',
  merchant_icon         VARCHAR(256) NULL DEFAULT NULL COMMENT '商家图标',
  merchant_name         VARCHAR(100) NULL DEFAULT NULL COMMENT '商家名称',
  mission_banner        VARCHAR(256) NULL DEFAULT NULL COMMENT '任务banner',
  mission_detail        longtext NULL DEFAULT NULL COMMENT '任务详情，用编辑器录入',
  mission_reward        VARCHAR(100) NULL DEFAULT NULL COMMENT '奖励,如:获得20元红包',
  participants_num      INT(10) NULL DEFAULT 0 COMMENT '参与人数,领取任务即可',
  type_id               TINYINT(3) NULL DEFAULT 0 COMMENT '分类: 0-未分类',
  sub_type_name         varchar(200) DEFAULT NULL COMMENT '子类型,用逗号分隔开，如:爱奇艺,优酷',
  img_num               tinyint(1) DEFAULT 0 COMMENT '需要提交图片的数量（为了兼容5.0之前的版本）',
  PRIMARY KEY (mission_id)
) ENGINE=InnoDB COMMENT='推荐任务';


DROP TABLE IF EXISTS re_recommend_mission_require;
CREATE TABLE re_recommend_mission_require(
  require_id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  require_name          varchar(128) NULL COMMENT '要求名字',
  PRIMARY KEY (require_id)
) ENGINE=InnoDB COMMENT='任务审核要求';


DROP TABLE IF EXISTS re_recommend_mission_step;
CREATE TABLE re_recommend_mission_step (
  step_id               BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  step_num              tinyint(3) NOT NULL DEFAULT '0' COMMENT '任务步骤号',
  step_content          longtext NULL COMMENT '步骤内容',
  step_imgs             longtext NULL COMMENT '步骤图片',
  step_btn              longtext NULL COMMENT '步骤按钮',
  step_status           tinyint(1) DEFAULT '2' COMMENT '显示平台, 0:ios开启,1:andriod开启,2:全部开启,3:全部关闭',
  PRIMARY KEY (step_id)
) ENGINE=InnoDB COMMENT='推荐任务步骤';


DROP TABLE IF EXISTS re_recommend_task;
CREATE TABLE re_recommend_task(
  task_id               BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id               int(10) unsigned NOT NULL COMMENT '用户ID',
  mission_id            BIGINT NOT NULL COMMENT '任务id',
  commit_text           longtext NULL COMMENT '提交文字',
  commit_img            longtext NULL COMMENT '提交图片',
  commit_require        longtext NULL COMMENT '提交要求',
  task_status           tinyint(1) DEFAULT 0 COMMENT '状态; 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期',
  verify_remarks        varchar(256) NULL COMMENT '审核备注',
  create_time           BIGINT NULL COMMENT '创建时间',
  update_time           BIGINT NULL COMMENT '更新时间',
  release_time          BIGINT NULL COMMENT '任务过期,释放时间',
  PRIMARY KEY (task_id)
) ENGINE=InnoDB COMMENT='用户推荐任务表';


DROP TABLE IF EXISTS callback_huaqiaobao;
CREATE TABLE callback_huaqiaobao(
  id                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
  user_id               int(10) unsigned NOT NULL COMMENT '用户ID',
  mobile                varchar(20) NOT NULL COMMENT '手机',
  call_type             tinyint(1) DEFAULT 0 COMMENT '状态; 0-app红包,1-微信红包',
  create_time           varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='华侨宝回调';


DROP TABLE IF EXISTS re_code_exchange_detail;
CREATE TABLE re_code_exchange_detail(
  mission_id          BIGINT unsigned NOT NULL COMMENT '任务ID',
  user_id             int(10) unsigned NOT NULL COMMENT '用户ID',
  exchange_code       varchar(10) NOT NULL COMMENT '兑换码',
  exchange_status     TINYINT(1) NULL DEFAULT 0 COMMENT '兑换状态 0-未兑换, 1-已兑换',
  money               decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  create_time         varchar(20) NULL COMMENT '生成时间,如:2016-08-18 12:53:30',
  exchange_time       varchar(20) NULL COMMENT '兑换时间,如:2016-08-18 12:53:30',
  KEY (mission_id, exchange_code),
  PRIMARY KEY (mission_id, user_id)
) ENGINE=InnoDB COMMENT='兑换任务明细表';