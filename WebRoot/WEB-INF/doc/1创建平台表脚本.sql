-- Create table
create table SYS_AREA
(
  id          VARCHAR2(64) not null,
  parent_id   VARCHAR2(64) not null,
  parent_ids  VARCHAR2(2000) not null,
  name        NVARCHAR2(100) not null,
  sort        NUMBER(10) not null,
  code        VARCHAR2(100),
  type        CHAR(1),
  create_by   VARCHAR2(64),
  create_date DATE,
  update_by   VARCHAR2(64),
  update_date DATE,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_AREA
  is '区域表';
-- Add comments to the columns 
comment on column SYS_AREA.id
  is '编号';
comment on column SYS_AREA.parent_id
  is '父级编号';
comment on column SYS_AREA.parent_ids
  is '所有父级编号';
comment on column SYS_AREA.name
  is '名称';
comment on column SYS_AREA.sort
  is '排序';
comment on column SYS_AREA.code
  is '区域编码';
comment on column SYS_AREA.type
  is '区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）';
comment on column SYS_AREA.create_by
  is '创建者';
comment on column SYS_AREA.create_date
  is '创建时间';
comment on column SYS_AREA.update_by
  is '更新者';
comment on column SYS_AREA.update_date
  is '更新时间';
comment on column SYS_AREA.remarks
  is '备注信息';
comment on column SYS_AREA.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index SYS_AREA_DEL_FLAG on SYS_AREA (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_AREA_PARENT_ID on SYS_AREA (PARENT_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_AREA_PARENT_IDS on SYS_AREA (PARENT_IDS)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_AREA
  add constraint PK_SYS_AREA primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_DICT
(
  id          VARCHAR2(64) not null,
  value       VARCHAR2(100) not null,
  label       VARCHAR2(200) not null,
  type        VARCHAR2(100) not null,
  description NVARCHAR2(100) not null,
  sort        NUMBER(10) not null,
  parent_id   VARCHAR2(64) default '0',
  create_by   VARCHAR2(64),
  create_date DATE,
  update_by   VARCHAR2(64),
  update_date DATE,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_DICT
  is '字典表';
-- Add comments to the columns 
comment on column SYS_DICT.id
  is '编号';
comment on column SYS_DICT.value
  is '数据值';
comment on column SYS_DICT.label
  is '标签名';
comment on column SYS_DICT.type
  is '类型';
comment on column SYS_DICT.description
  is '描述';
comment on column SYS_DICT.sort
  is '排序（升序）';
comment on column SYS_DICT.parent_id
  is '父级编号';
comment on column SYS_DICT.create_by
  is '创建者';
comment on column SYS_DICT.create_date
  is '创建时间';
comment on column SYS_DICT.update_by
  is '更新者';
comment on column SYS_DICT.update_date
  is '更新时间';
comment on column SYS_DICT.remarks
  is '备注信息';
comment on column SYS_DICT.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index SYS_DICT_DEL_FLAG on SYS_DICT (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_DICT_LABEL on SYS_DICT (LABEL)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_DICT_VALUE on SYS_DICT (VALUE)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_DICT
  add constraint PK_SYS_DICT primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_MENU
(
  id          VARCHAR2(64) not null,
  parent_id   VARCHAR2(64) not null,
  parent_ids  VARCHAR2(2000) not null,
  name        NVARCHAR2(100) not null,
  sort        NUMBER(10) not null,
  href        VARCHAR2(2000),
  target      VARCHAR2(20),
  icon        VARCHAR2(100),
  is_show     CHAR(1) not null,
  permission  VARCHAR2(200),
  create_by   VARCHAR2(64) not null,
  create_date DATE,
  update_by   VARCHAR2(64) not null,
  update_date DATE,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_MENU
  is '菜单表';
-- Add comments to the columns 
comment on column SYS_MENU.id
  is '编号';
comment on column SYS_MENU.parent_id
  is '父级编号';
comment on column SYS_MENU.parent_ids
  is '所有父级编号';
comment on column SYS_MENU.name
  is '名称';
comment on column SYS_MENU.sort
  is '排序';
comment on column SYS_MENU.href
  is '链接';
comment on column SYS_MENU.target
  is '目标（mainFrame、 _blank、_self、_parent、_top）';
comment on column SYS_MENU.icon
  is '图标';
comment on column SYS_MENU.is_show
  is '是否在菜单中显示（1：显示；0：不显示）';
comment on column SYS_MENU.permission
  is '权限标识';
comment on column SYS_MENU.create_by
  is '创建者';
comment on column SYS_MENU.create_date
  is '创建时间';
comment on column SYS_MENU.update_by
  is '更新者';
comment on column SYS_MENU.update_date
  is '更新时间';
comment on column SYS_MENU.remarks
  is '备注信息';
comment on column SYS_MENU.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index SYS_MENU_DEL_FLAG on SYS_MENU (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_MENU_PARENT_ID on SYS_MENU (PARENT_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_MENU_PARENT_IDS on SYS_MENU (PARENT_IDS)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_OFFICE
(
  id             VARCHAR2(64) not null,
  parent_id      VARCHAR2(64) not null,
  parent_ids     VARCHAR2(2000) not null,
  name           NVARCHAR2(100) not null,
  sort           NUMBER(10) not null,
  area_id        VARCHAR2(64) not null,
  code           VARCHAR2(100),
  type           CHAR(1) not null,
  grade          CHAR(1) not null,
  address        NVARCHAR2(255),
  zip_code       VARCHAR2(100),
  master         NVARCHAR2(100),
  phone          NVARCHAR2(200),
  fax            NVARCHAR2(200),
  email          NVARCHAR2(200),
  useable        VARCHAR2(64),
  primary_person VARCHAR2(64),
  deputy_person  VARCHAR2(64),
  create_by      VARCHAR2(64) not null,
  create_date    DATE not null,
  update_by      VARCHAR2(64) not null,
  update_date    DATE not null,
  remarks        NVARCHAR2(255),
  del_flag       CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_OFFICE
  is '机构表';
-- Add comments to the columns 
comment on column SYS_OFFICE.id
  is '编号';
comment on column SYS_OFFICE.parent_id
  is '父级编号';
comment on column SYS_OFFICE.parent_ids
  is '所有父级编号';
comment on column SYS_OFFICE.name
  is '名称';
comment on column SYS_OFFICE.sort
  is '排序';
comment on column SYS_OFFICE.area_id
  is '归属区域';
comment on column SYS_OFFICE.code
  is '区域编码';
comment on column SYS_OFFICE.type
  is '机构类型（1：公司；2：部门；3：小组）';
comment on column SYS_OFFICE.grade
  is '机构等级（1：一级；2：二级；3：三级；4：四级）';
comment on column SYS_OFFICE.address
  is '联系地址';
comment on column SYS_OFFICE.zip_code
  is '邮政编码';
comment on column SYS_OFFICE.master
  is '负责人';
comment on column SYS_OFFICE.phone
  is '电话';
comment on column SYS_OFFICE.fax
  is '传真';
comment on column SYS_OFFICE.email
  is '邮箱';
comment on column SYS_OFFICE.useable
  is '是否启用';
comment on column SYS_OFFICE.primary_person
  is '主负责人';
comment on column SYS_OFFICE.deputy_person
  is '副负责人';
comment on column SYS_OFFICE.create_by
  is '创建者';
comment on column SYS_OFFICE.create_date
  is '创建时间';
comment on column SYS_OFFICE.update_by
  is '更新者';
comment on column SYS_OFFICE.update_date
  is '更新时间';
comment on column SYS_OFFICE.remarks
  is '备注信息';
comment on column SYS_OFFICE.del_flag
  is '删除标记';
-- Create/Recreate indexes 
create index SYS_OFFICE_DEL_FLAG on SYS_OFFICE (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_OFFICE_PARENT_ID on SYS_OFFICE (PARENT_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_OFFICE_PARENT_IDS on SYS_OFFICE (PARENT_IDS)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_OFFICE_TYPE on SYS_OFFICE (TYPE)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_OFFICE
  add constraint PK_SYS_OFFICE primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  -- Create table
create table SYS_USER
(
  id          VARCHAR2(64) not null,
  company_id  VARCHAR2(64) not null,
  office_id   VARCHAR2(64) not null,
  login_name  VARCHAR2(100) not null,
  password    VARCHAR2(100) not null,
  no          VARCHAR2(100),
  name        NVARCHAR2(100) not null,
  email       NVARCHAR2(200),
  phone       VARCHAR2(200),
  mobile      VARCHAR2(200),
  user_type   CHAR(1),
  photo       VARCHAR2(1000),
  login_ip    VARCHAR2(100),
  login_date  TIMESTAMP(6),
  login_flag  VARCHAR2(64),
  create_by   VARCHAR2(64),
  create_date DATE,
  update_by   VARCHAR2(64),
  update_date DATE,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_USER
  is '用户表';
-- Add comments to the columns 
comment on column SYS_USER.id
  is '编号';
comment on column SYS_USER.company_id
  is '归属公司';
comment on column SYS_USER.office_id
  is '归属部门';
comment on column SYS_USER.login_name
  is '登录名';
comment on column SYS_USER.password
  is '密码';
comment on column SYS_USER.no
  is '工号';
comment on column SYS_USER.name
  is '姓名';
comment on column SYS_USER.email
  is '邮箱';
comment on column SYS_USER.phone
  is '电话';
comment on column SYS_USER.mobile
  is '手机';
comment on column SYS_USER.user_type
  is '用户类型';
comment on column SYS_USER.photo
  is '用户头像';
comment on column SYS_USER.login_ip
  is '最后登陆IP';
comment on column SYS_USER.login_date
  is '最后登陆时间';
comment on column SYS_USER.login_flag
  is '是否可登录';
comment on column SYS_USER.create_by
  is '创建者';
comment on column SYS_USER.create_date
  is '创建时间';
comment on column SYS_USER.update_by
  is '更新者';
comment on column SYS_USER.update_date
  is '更新时间';
comment on column SYS_USER.remarks
  is '备注信息';
comment on column SYS_USER.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index SYS_USER_COMPANY_ID on SYS_USER (COMPANY_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_USER_DEL_FLAG on SYS_USER (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_USER_LOGIN_NAME on SYS_USER (LOGIN_NAME)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_USER_OFFICE_ID on SYS_USER (OFFICE_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_USER_UPDATE_DATE on SYS_USER (UPDATE_DATE)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_USER
  add constraint PK_SYS_USER primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Create table
create table SYS_ROLE
(
  id          VARCHAR2(64) not null,
  office_id   VARCHAR2(64),
  name        NVARCHAR2(100) not null,
  enname      VARCHAR2(255),
  role_type   VARCHAR2(255),
  data_scope  CHAR(1),
  is_sys      VARCHAR2(64),
  useable     VARCHAR2(64),
  create_by   VARCHAR2(64) not null,
  create_date DATE,
  update_by   VARCHAR2(64) not null,
  update_date DATE,
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_ROLE
  is '角色表';
-- Add comments to the columns 
comment on column SYS_ROLE.id
  is '编号';
comment on column SYS_ROLE.office_id
  is '归属机构';
comment on column SYS_ROLE.name
  is '角色名称';
comment on column SYS_ROLE.enname
  is '英文名称';
comment on column SYS_ROLE.role_type
  is '角色类型';
comment on column SYS_ROLE.data_scope
  is '数据范围（0：所有数据；1：所在公司及以下数据；2：所在公司数据；3：所在部门及以下数据；4：所在部门数据；8：仅本人数据；9：按明细设置）';
comment on column SYS_ROLE.is_sys
  is '是否系统数据';
comment on column SYS_ROLE.useable
  is '是否可用';
comment on column SYS_ROLE.create_by
  is '创建者';
comment on column SYS_ROLE.create_date
  is '创建时间';
comment on column SYS_ROLE.update_by
  is '更新者';
comment on column SYS_ROLE.update_date
  is '更新时间';
comment on column SYS_ROLE.remarks
  is '备注信息';
comment on column SYS_ROLE.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index SYS_ROLE_DEL_FLAG on SYS_ROLE (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_ROLE_ENNAME on SYS_ROLE (ENNAME)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_ROLE_MENU
(
  role_id VARCHAR2(64) not null,
  menu_id VARCHAR2(64) not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_ROLE_MENU
  is '角色-菜单';
-- Add comments to the columns 
comment on column SYS_ROLE_MENU.role_id
  is '角色编号';
comment on column SYS_ROLE_MENU.menu_id
  is '菜单编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE_MENU
  add constraint PK_SYS_ROLE_MENU primary key (ROLE_ID, MENU_ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_ROLE_OFFICE
(
  role_id   VARCHAR2(64) not null,
  office_id VARCHAR2(64) not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table SYS_ROLE_OFFICE
  is '角色-机构';
-- Add comments to the columns 
comment on column SYS_ROLE_OFFICE.role_id
  is '角色编号';
comment on column SYS_ROLE_OFFICE.office_id
  is '机构编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE_OFFICE
  add constraint PK_SYS_ROLE_OFFICE primary key (ROLE_ID, OFFICE_ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255;
  -- Create table
create table SYS_USER_ROLE
(
  user_id VARCHAR2(64) not null,
  role_id VARCHAR2(64) not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_USER_ROLE
  is '用户-角色';
-- Add comments to the columns 
comment on column SYS_USER_ROLE.user_id
  is '用户编号';
comment on column SYS_USER_ROLE.role_id
  is '角色编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (USER_ID, ROLE_ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table SYS_LOG
(
  id          VARCHAR2(64) not null,
  type        CHAR(1) default '1',
  title       NVARCHAR2(500),
  create_by   VARCHAR2(64),
  create_date TIMESTAMP(6),
  remote_addr VARCHAR2(255),
  user_agent  VARCHAR2(255),
  request_uri VARCHAR2(255),
  method      VARCHAR2(5),
  params      CLOB,
  exception   CLOB
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table SYS_LOG
  is '日志表';
-- Add comments to the columns 
comment on column SYS_LOG.id
  is '编号';
comment on column SYS_LOG.type
  is '日志类型（1：接入日志；2：异常日志）';
comment on column SYS_LOG.title
  is '操作功能标题';
comment on column SYS_LOG.create_by
  is '创建者';
comment on column SYS_LOG.create_date
  is '创建时间';
comment on column SYS_LOG.remote_addr
  is '操作IP地址';
comment on column SYS_LOG.user_agent
  is '用户代理';
comment on column SYS_LOG.request_uri
  is '请求URI';
comment on column SYS_LOG.method
  is '操作方式';
comment on column SYS_LOG.params
  is '操作提交的数据';
comment on column SYS_LOG.exception
  is '异常信息';
-- Create/Recreate indexes 
create index SYS_LOG_CREATE_BY on SYS_LOG (CREATE_BY)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_LOG_CREATE_DATE on SYS_LOG (CREATE_DATE)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_LOG_REQUEST_URI on SYS_LOG (REQUEST_URI)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index SYS_LOG_TYPE on SYS_LOG (TYPE)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_LOG
  add constraint PK_SYS_LOG primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table GEN_SCHEME
(
  id                   VARCHAR2(64) not null,
  name                 NVARCHAR2(200),
  category             VARCHAR2(2000),
  package_name         VARCHAR2(500),
  module_name          VARCHAR2(30),
  sub_module_name      VARCHAR2(30),
  function_name        NVARCHAR2(500),
  function_name_simple NVARCHAR2(100),
  function_author      NVARCHAR2(100),
  gen_table_id         VARCHAR2(200),
  create_by            VARCHAR2(64),
  create_date          TIMESTAMP(6),
  update_by            VARCHAR2(64),
  update_date          TIMESTAMP(6),
  remarks              NVARCHAR2(255),
  del_flag             CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GEN_SCHEME
  is '生成方案';
-- Add comments to the columns 
comment on column GEN_SCHEME.id
  is '编号';
comment on column GEN_SCHEME.name
  is '名称';
comment on column GEN_SCHEME.category
  is '分类';
comment on column GEN_SCHEME.package_name
  is '生成包路径';
comment on column GEN_SCHEME.module_name
  is '生成模块名';
comment on column GEN_SCHEME.sub_module_name
  is '生成子模块名';
comment on column GEN_SCHEME.function_name
  is '生成功能名';
comment on column GEN_SCHEME.function_name_simple
  is '生成功能名（简写）';
comment on column GEN_SCHEME.function_author
  is '生成功能作者';
comment on column GEN_SCHEME.gen_table_id
  is '生成表编号';
comment on column GEN_SCHEME.create_by
  is '创建者';
comment on column GEN_SCHEME.create_date
  is '创建时间';
comment on column GEN_SCHEME.update_by
  is '更新者';
comment on column GEN_SCHEME.update_date
  is '更新时间';
comment on column GEN_SCHEME.remarks
  is '备注信息';
comment on column GEN_SCHEME.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index GEN_SCHEME_DEL_FLAG on GEN_SCHEME (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table GEN_SCHEME
  add primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table GEN_TABLE
(
  id              VARCHAR2(64) not null,
  name            NVARCHAR2(200),
  comments        NVARCHAR2(500),
  class_name      VARCHAR2(100),
  parent_table    VARCHAR2(200),
  parent_table_fk VARCHAR2(100),
  create_by       VARCHAR2(64),
  create_date     TIMESTAMP(6),
  update_by       VARCHAR2(64),
  update_date     TIMESTAMP(6),
  remarks         NVARCHAR2(255),
  del_flag        CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GEN_TABLE
  is '业务表';
-- Add comments to the columns 
comment on column GEN_TABLE.id
  is '编号';
comment on column GEN_TABLE.name
  is '名称';
comment on column GEN_TABLE.comments
  is '描述';
comment on column GEN_TABLE.class_name
  is '实体类名称';
comment on column GEN_TABLE.parent_table
  is '关联父表';
comment on column GEN_TABLE.parent_table_fk
  is '关联父表外键';
comment on column GEN_TABLE.create_by
  is '创建者';
comment on column GEN_TABLE.create_date
  is '创建时间';
comment on column GEN_TABLE.update_by
  is '更新者';
comment on column GEN_TABLE.update_date
  is '更新时间';
comment on column GEN_TABLE.remarks
  is '备注信息';
comment on column GEN_TABLE.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index GEN_TABLE_DEL_FLAG on GEN_TABLE (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index GEN_TABLE_NAME on GEN_TABLE (NAME)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table GEN_TABLE
  add primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table GEN_TABLE_COLUMN
(
  id           VARCHAR2(64) not null,
  gen_table_id VARCHAR2(64),
  name         NVARCHAR2(200),
  comments     NVARCHAR2(500),
  jdbc_type    VARCHAR2(100),
  java_type    VARCHAR2(500),
  java_field   VARCHAR2(200),
  is_pk        CHAR(1),
  is_null      CHAR(1),
  is_insert    CHAR(1),
  is_edit      CHAR(1),
  is_list      CHAR(1),
  is_query     CHAR(1),
  query_type   VARCHAR2(200),
  show_type    VARCHAR2(200),
  dict_type    VARCHAR2(200),
  settings     NVARCHAR2(2000),
  sort         NUMBER,
  create_by    VARCHAR2(64),
  create_date  TIMESTAMP(6),
  update_by    VARCHAR2(64),
  update_date  TIMESTAMP(6),
  remarks      NVARCHAR2(255),
  del_flag     CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table GEN_TABLE_COLUMN
  is '业务表字段';
-- Add comments to the columns 
comment on column GEN_TABLE_COLUMN.id
  is '编号';
comment on column GEN_TABLE_COLUMN.gen_table_id
  is '归属表编号';
comment on column GEN_TABLE_COLUMN.name
  is '名称';
comment on column GEN_TABLE_COLUMN.comments
  is '描述';
comment on column GEN_TABLE_COLUMN.jdbc_type
  is '列的数据类型的字节长度';
comment on column GEN_TABLE_COLUMN.java_type
  is 'JAVA类型';
comment on column GEN_TABLE_COLUMN.java_field
  is 'JAVA字段名';
comment on column GEN_TABLE_COLUMN.is_pk
  is '是否主键';
comment on column GEN_TABLE_COLUMN.is_null
  is '是否可为空';
comment on column GEN_TABLE_COLUMN.is_insert
  is '是否为插入字段';
comment on column GEN_TABLE_COLUMN.is_edit
  is '是否编辑字段';
comment on column GEN_TABLE_COLUMN.is_list
  is '是否列表字段';
comment on column GEN_TABLE_COLUMN.is_query
  is '是否查询字段';
comment on column GEN_TABLE_COLUMN.query_type
  is '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）';
comment on column GEN_TABLE_COLUMN.show_type
  is '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）';
comment on column GEN_TABLE_COLUMN.dict_type
  is '字典类型';
comment on column GEN_TABLE_COLUMN.settings
  is '其它设置（扩展字段JSON）';
comment on column GEN_TABLE_COLUMN.sort
  is '排序（升序）';
comment on column GEN_TABLE_COLUMN.create_by
  is '创建者';
comment on column GEN_TABLE_COLUMN.create_date
  is '创建时间';
comment on column GEN_TABLE_COLUMN.update_by
  is '更新者';
comment on column GEN_TABLE_COLUMN.update_date
  is '更新时间';
comment on column GEN_TABLE_COLUMN.remarks
  is '备注信息';
comment on column GEN_TABLE_COLUMN.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index GEN_TABLE_COLUMN_DEL_FLAG on GEN_TABLE_COLUMN (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index GEN_TABLE_COLUMN_NAME on GEN_TABLE_COLUMN (NAME)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index GEN_TABLE_COLUMN_SORT on GEN_TABLE_COLUMN (SORT)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index GEN_TABLE_COLUMN_TABLE_ID on GEN_TABLE_COLUMN (GEN_TABLE_ID)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table GEN_TABLE_COLUMN
  add primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table GEN_TEMPLATE
(
  id          VARCHAR2(64) not null,
  name        NVARCHAR2(200),
  category    VARCHAR2(2000),
  file_path   VARCHAR2(500),
  file_name   VARCHAR2(200),
  content     CLOB,
  create_by   VARCHAR2(64),
  create_date TIMESTAMP(6),
  update_by   VARCHAR2(64),
  update_date TIMESTAMP(6),
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1) default '0' not null
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table GEN_TEMPLATE
  is '代码模板表';
-- Add comments to the columns 
comment on column GEN_TEMPLATE.id
  is '编号';
comment on column GEN_TEMPLATE.name
  is '名称';
comment on column GEN_TEMPLATE.category
  is '分类';
comment on column GEN_TEMPLATE.file_path
  is '生成文件路径';
comment on column GEN_TEMPLATE.file_name
  is '生成文件名';
comment on column GEN_TEMPLATE.content
  is '内容';
comment on column GEN_TEMPLATE.create_by
  is '创建者';
comment on column GEN_TEMPLATE.create_date
  is '创建时间';
comment on column GEN_TEMPLATE.update_by
  is '更新者';
comment on column GEN_TEMPLATE.update_date
  is '更新时间';
comment on column GEN_TEMPLATE.remarks
  is '备注信息';
comment on column GEN_TEMPLATE.del_flag
  is '删除标记（0：正常；1：删除）';
-- Create/Recreate indexes 
create index GEN_TEMPLATE_DEL_FALG on GEN_TEMPLATE (DEL_FLAG)
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints 
alter table GEN_TEMPLATE
  add primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table T_BA_ATT
(
  id          VARCHAR2(32) not null,
  create_by   VARCHAR2(32),
  create_date DATE,
  update_by   VARCHAR2(32),
  update_date DATE,
  remarks     VARCHAR2(1000),
  del_flag    CHAR(1) default 0,
  sort        NUMBER(10),
  use_state   CHAR(1) default 0,
  isrequired  CHAR(1),
  type        VARCHAR2(32),
  office_id   VARCHAR2(32),
  file_id     VARCHAR2(32),
  name        VARCHAR2(400),
  max_size    VARCHAR2(40),
  is_sort     CHAR(1)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_ATT
  is '基础模板配置表';
-- Add comments to the columns 
comment on column T_BA_ATT.id
  is '主键
';
comment on column T_BA_ATT.create_by
  is '创建人
';
comment on column T_BA_ATT.create_date
  is '创建时间
';
comment on column T_BA_ATT.update_by
  is '修改人
';
comment on column T_BA_ATT.update_date
  is '修改时间
';
comment on column T_BA_ATT.remarks
  is '备注';
comment on column T_BA_ATT.del_flag
  is '0正常1删除
';
comment on column T_BA_ATT.sort
  is '顺序
';
comment on column T_BA_ATT.use_state
  is '使用状态
';
comment on column T_BA_ATT.isrequired
  is '是否必填0否1必填
';
comment on column T_BA_ATT.type
  is '文件类型
';
comment on column T_BA_ATT.office_id
  is '机构ID';
comment on column T_BA_ATT.file_id
  is '文件ID';
comment on column T_BA_ATT.name
  is '名称';
comment on column T_BA_ATT.max_size
  is '文件大小限制';
comment on column T_BA_ATT.is_sort
  is '需要排序重新定向，0否1是';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_ATT
  add constraint PK_T_BA_INIT_ATT primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_PARAMETER
(
  id          VARCHAR2(32) not null,
  create_by   VARCHAR2(32),
  create_date DATE,
  update_by   VARCHAR2(32),
  update_date DATE,
  remarks     VARCHAR2(400),
  del_flag    VARCHAR2(2),
  sort        NUMBER(10),
  use_state   CHAR(1),
  para_value  VARCHAR2(100),
  para_key    VARCHAR2(100),
  temp_id     VARCHAR2(32),
  para_name   VARCHAR2(32),
  para_type   VARCHAR2(32)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_BA_PARAMETER.id
  is '主键
';
comment on column T_BA_PARAMETER.create_by
  is '创建人
';
comment on column T_BA_PARAMETER.create_date
  is '创建时间
';
comment on column T_BA_PARAMETER.update_by
  is '修改人
';
comment on column T_BA_PARAMETER.update_date
  is '修改时间
';
comment on column T_BA_PARAMETER.remarks
  is '备注
';
comment on column T_BA_PARAMETER.del_flag
  is '0正常1删除
';
comment on column T_BA_PARAMETER.sort
  is '顺序
';
comment on column T_BA_PARAMETER.use_state
  is '使用状态
';
comment on column T_BA_PARAMETER.para_value
  is '参数值
';
comment on column T_BA_PARAMETER.para_key
  is '参数键
';
comment on column T_BA_PARAMETER.temp_id
  is '模板ID';
comment on column T_BA_PARAMETER.para_name
  is '参数名称
';
comment on column T_BA_PARAMETER.para_type
  is '参数类型
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_PARAMETER
  add constraint PK_T_BA_PARAMETER primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_FILE
(
  id          VARCHAR2(32) not null,
  create_by   VARCHAR2(64),
  create_date TIMESTAMP(6),
  update_by   VARCHAR2(64),
  update_date TIMESTAMP(6),
  remarks     NVARCHAR2(255),
  del_flag    CHAR(1),
  file_name   VARCHAR2(600),
  file_size   VARCHAR2(32),
  file_type   VARCHAR2(200)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_FILE
  is '基础附件表';
-- Add comments to the columns 
comment on column T_BA_FILE.id
  is '主键
';
comment on column T_BA_FILE.create_by
  is '创建人
';
comment on column T_BA_FILE.create_date
  is '创建时间
';
comment on column T_BA_FILE.update_by
  is '操作人
';
comment on column T_BA_FILE.update_date
  is '操作时间
';
comment on column T_BA_FILE.remarks
  is '附件描述
';
comment on column T_BA_FILE.del_flag
  is '0 正常 1删除
';
comment on column T_BA_FILE.file_name
  is '文件标题
';
comment on column T_BA_FILE.file_size
  is '附件大小
';
comment on column T_BA_FILE.file_type
  is '附件类型
';
-- Create table
create table T_BA_REGISTERED
(
  id            VARCHAR2(32) not null,
  name          VARCHAR2(200),
  org_code      VARCHAR2(50),
  user_id       VARCHAR2(32),
  phone         VARCHAR2(50),
  create_by     VARCHAR2(64),
  create_date   TIMESTAMP(6),
  update_by     VARCHAR2(64),
  update_date   TIMESTAMP(6),
  remarks       NVARCHAR2(400),
  del_flag      VARCHAR2(2) default 0,
  approval_code VARCHAR2(100),
  address_code  CHAR(6),
  address       VARCHAR2(200),
  head_code     VARCHAR2(50),
  head_name     VARCHAR2(50),
  head_natrue   VARCHAR2(50),
  org_fixedtel  VARCHAR2(50),
  org_tel       VARCHAR2(50),
  contact_name  VARCHAR2(50),
  contact_phone VARCHAR2(50),
  e_mail        VARCHAR2(200),
  capital       VARCHAR2(20)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_REGISTERED
  is '基础注册表';
-- Add comments to the columns 
comment on column T_BA_REGISTERED.id
  is '主键';
comment on column T_BA_REGISTERED.name
  is '机构名称';
comment on column T_BA_REGISTERED.org_code
  is '组织机构代码';
comment on column T_BA_REGISTERED.user_id
  is '系统用户ID';
comment on column T_BA_REGISTERED.phone
  is '注册人手机号';
comment on column T_BA_REGISTERED.create_by
  is '创建者';
comment on column T_BA_REGISTERED.create_date
  is '创建时间';
comment on column T_BA_REGISTERED.update_by
  is '更新者';
comment on column T_BA_REGISTERED.update_date
  is '更新时间';
comment on column T_BA_REGISTERED.remarks
  is '备注
';
comment on column T_BA_REGISTERED.del_flag
  is '0正常1删除
';
comment on column T_BA_REGISTERED.approval_code
  is '认证机构批准号';
comment on column T_BA_REGISTERED.address_code
  is '机构地址编码';
comment on column T_BA_REGISTERED.address
  is '机构地址详情';
comment on column T_BA_REGISTERED.head_code
  is '法人注册号';
comment on column T_BA_REGISTERED.head_name
  is '法定代表人';
comment on column T_BA_REGISTERED.head_natrue
  is '法人性质';
comment on column T_BA_REGISTERED.org_fixedtel
  is '固定电话';
comment on column T_BA_REGISTERED.org_tel
  is '传真号';
comment on column T_BA_REGISTERED.contact_name
  is '联系人';
comment on column T_BA_REGISTERED.contact_phone
  is '联系人电话';
comment on column T_BA_REGISTERED.e_mail
  is 'Email';
comment on column T_BA_REGISTERED.capital
  is '注册资本';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_REGISTERED
  add constraint PK_T_BA_REGISTERED primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_ACTIVITY
(
  id               VARCHAR2(40) not null,
  temp_activity_id VARCHAR2(40),
  apply_code       VARCHAR2(40),
  apply_state      CHAR(2),
  apply_id         VARCHAR2(40),
  status           CHAR(1),
  review_advice    CHAR(2),
  company_id       VARCHAR2(40),
  org_name         VARCHAR2(200),
  user_id          VARCHAR2(40),
  create_by        VARCHAR2(40),
  create_date      DATE,
  update_by        VARCHAR2(40),
  update_date      DATE,
  del_flag         CHAR(1),
  remarks          VARCHAR2(255),
  activity_name    VARCHAR2(200)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_ACTIVITY
  is '流程实例表';
-- Add comments to the columns 
comment on column T_BA_ACTIVITY.id
  is '主键';
comment on column T_BA_ACTIVITY.temp_activity_id
  is '流程定义ID';
comment on column T_BA_ACTIVITY.apply_code
  is '申请编号';
comment on column T_BA_ACTIVITY.apply_state
  is '申请状态';
comment on column T_BA_ACTIVITY.apply_id
  is '申请ID';
comment on column T_BA_ACTIVITY.status
  is '流程实例状态:1运行中2结束3终止4暂停';
comment on column T_BA_ACTIVITY.review_advice
  is '审批结果';
comment on column T_BA_ACTIVITY.company_id
  is '组织机构ID';
comment on column T_BA_ACTIVITY.org_name
  is '机构名称';
comment on column T_BA_ACTIVITY.user_id
  is '机构ID';
comment on column T_BA_ACTIVITY.create_by
  is '创建人';
comment on column T_BA_ACTIVITY.create_date
  is '创建时间';
comment on column T_BA_ACTIVITY.update_by
  is '修改人';
comment on column T_BA_ACTIVITY.update_date
  is '修改日期';
comment on column T_BA_ACTIVITY.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_ACTIVITY.remarks
  is '备注';
comment on column T_BA_ACTIVITY.activity_name
  is '流程定义名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_ACTIVITY
  add constraint PK_T_BA_ACTIVITY primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_NOTE
(
  id           VARCHAR2(40) not null,
  temp_note_id VARCHAR2(40),
  activity_id  VARCHAR2(40),
  name         VARCHAR2(40),
  code         CHAR(1),
  status       CHAR(1),
  create_by    VARCHAR2(40),
  create_date  DATE,
  update_by    VARCHAR2(40),
  update_date  DATE,
  del_flag     CHAR(1),
  remarks      VARCHAR2(255)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_NOTE
  is '流程节点表';
-- Add comments to the columns 
comment on column T_BA_NOTE.id
  is '主键';
comment on column T_BA_NOTE.temp_note_id
  is '流程节点模板ID';
comment on column T_BA_NOTE.activity_id
  is '流程实例ID';
comment on column T_BA_NOTE.name
  is '节点名称';
comment on column T_BA_NOTE.code
  is '节点编码';
comment on column T_BA_NOTE.status
  is '节点状态';
comment on column T_BA_NOTE.create_by
  is '创建人';
comment on column T_BA_NOTE.create_date
  is '创建时间';
comment on column T_BA_NOTE.update_by
  is '修改人';
comment on column T_BA_NOTE.update_date
  is '修改日期';
comment on column T_BA_NOTE.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_NOTE.remarks
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_NOTE
  add constraint PK_T_BA_NOTE primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_NOTE_ROLE
(
  id          VARCHAR2(40) not null,
  role_id     VARCHAR2(40),
  note_id     VARCHAR2(40),
  create_by   VARCHAR2(40),
  create_date DATE,
  update_by   VARCHAR2(40),
  update_date DATE,
  del_flag    CHAR(1),
  remarks     VARCHAR2(255)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_NOTE_ROLE
  is '流程节点角色表';
-- Add comments to the columns 
comment on column T_BA_NOTE_ROLE.id
  is '主键';
comment on column T_BA_NOTE_ROLE.role_id
  is '角色ID';
comment on column T_BA_NOTE_ROLE.note_id
  is '节点ID';
comment on column T_BA_NOTE_ROLE.create_by
  is '创建人';
comment on column T_BA_NOTE_ROLE.create_date
  is '创建时间';
comment on column T_BA_NOTE_ROLE.update_by
  is '修改人';
comment on column T_BA_NOTE_ROLE.update_date
  is '修改日期';
comment on column T_BA_NOTE_ROLE.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_NOTE_ROLE.remarks
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_NOTE_ROLE
  add constraint PK_T_BA_NOTE_ROLE primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_TASK
(
  id              VARCHAR2(40) not null,
  note_id         VARCHAR2(40),
  activity_id     VARCHAR2(40),
  note_name       VARCHAR2(40),
  dual_type       CHAR(2),
  dual_opinion    CHAR(2),
  opinion_content VARCHAR2(1000),
  update_name     VARCHAR2(200),
  create_name     VARCHAR2(200),
  att_ids         VARCHAR2(200),
  create_by       VARCHAR2(40),
  create_date     DATE,
  update_by       VARCHAR2(40),
  update_date     DATE,
  del_flag        CHAR(1),
  remarks         VARCHAR2(255),
  status          CHAR(1),
  last_task_id    VARCHAR2(40),
  role_id         VARCHAR2(40),
  standby         VARCHAR2(40),
  standby1        VARCHAR2(800),
  days            VARCHAR2(10)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_TASK
  is '流程任务表';
-- Add comments to the columns 
comment on column T_BA_TASK.id
  is '主键';
comment on column T_BA_TASK.note_id
  is '节点ID';
comment on column T_BA_TASK.activity_id
  is '流程ID';
comment on column T_BA_TASK.note_name
  is '节点名称';
comment on column T_BA_TASK.dual_type
  is '处理类型;01岗位内流转02下一节点03退回';
comment on column T_BA_TASK.dual_opinion
  is '处理意见';
comment on column T_BA_TASK.opinion_content
  is '意见说明';
comment on column T_BA_TASK.update_name
  is '处理人姓名';
comment on column T_BA_TASK.create_name
  is '上一步处理人姓名';
comment on column T_BA_TASK.att_ids
  is '附件ID';
comment on column T_BA_TASK.create_by
  is '创建人（上一步处理人ID）';
comment on column T_BA_TASK.create_date
  is '创建时间（上一步处理时间）';
comment on column T_BA_TASK.update_by
  is '修改人（处理人ID）';
comment on column T_BA_TASK.update_date
  is '修改日期（处理时间）';
comment on column T_BA_TASK.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_TASK.remarks
  is '备注';
comment on column T_BA_TASK.status
  is '流程任务状态:1运行中2结束3终止4暂停';
comment on column T_BA_TASK.last_task_id
  is '上一处理任务TD';
comment on column T_BA_TASK.role_id
  is '处理角色ID';
comment on column T_BA_TASK.standby
  is '备用';
comment on column T_BA_TASK.standby1
  is '备用';
comment on column T_BA_TASK.days
  is '超期时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_TASK
  add constraint PK_T_BA_TASK primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_TEMP_ACTIVITY
(
  id            VARCHAR2(40),
  activity_name VARCHAR2(200),
  logo          VARCHAR2(40),
  status        CHAR(1),
  create_by     VARCHAR2(40),
  create_date   DATE,
  update_by     VARCHAR2(40),
  update_date   DATE,
  del_flag      CHAR(1),
  remarks       VARCHAR2(255),
  company_id    VARCHAR2(64)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_TEMP_ACTIVITY
  is '流程实例定义表';
-- Add comments to the columns 
comment on column T_BA_TEMP_ACTIVITY.id
  is '主键';
comment on column T_BA_TEMP_ACTIVITY.activity_name
  is '流程定义名称';
comment on column T_BA_TEMP_ACTIVITY.logo
  is '流程标识';
comment on column T_BA_TEMP_ACTIVITY.status
  is '状态:1运行中2终止3暂停';
comment on column T_BA_TEMP_ACTIVITY.create_by
  is '创建人';
comment on column T_BA_TEMP_ACTIVITY.create_date
  is '创建时间';
comment on column T_BA_TEMP_ACTIVITY.update_by
  is '修改人';
comment on column T_BA_TEMP_ACTIVITY.update_date
  is '修改日期';
comment on column T_BA_TEMP_ACTIVITY.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_TEMP_ACTIVITY.remarks
  is '备注';
comment on column T_BA_TEMP_ACTIVITY.company_id
  is '公司';
-- Create table
create table T_BA_TEMP_NOTE
(
  id          VARCHAR2(40) not null,
  name        VARCHAR2(40),
  activity_id VARCHAR2(40),
  status      CHAR(1),
  send_sms    CHAR(1),
  sms_content VARCHAR2(4000),
  priority    NUMBER,
  create_by   VARCHAR2(40),
  create_date DATE,
  update_by   VARCHAR2(40),
  update_date DATE,
  del_flag    CHAR(1),
  remarks     VARCHAR2(250)
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_TEMP_NOTE
  is '流程节点定义表';
-- Add comments to the columns 
comment on column T_BA_TEMP_NOTE.id
  is '主键';
comment on column T_BA_TEMP_NOTE.name
  is '流程节点名称';
comment on column T_BA_TEMP_NOTE.activity_id
  is '流程实例模板ID';
comment on column T_BA_TEMP_NOTE.status
  is '状态:1运行中2终止3暂停';
comment on column T_BA_TEMP_NOTE.send_sms
  is '发送短信:1发送2不发送';
comment on column T_BA_TEMP_NOTE.sms_content
  is '发送短信内容';
comment on column T_BA_TEMP_NOTE.priority
  is '优先级';
comment on column T_BA_TEMP_NOTE.create_by
  is '创建人';
comment on column T_BA_TEMP_NOTE.create_date
  is '创建时间';
comment on column T_BA_TEMP_NOTE.update_by
  is '修改人';
comment on column T_BA_TEMP_NOTE.update_date
  is '修改日期';
comment on column T_BA_TEMP_NOTE.del_flag
  is '是否有效:0 正常 1删除 2无效';
comment on column T_BA_TEMP_NOTE.remarks
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BA_TEMP_NOTE
  add constraint PK_T_BA_TEMP_NOTE primary key (ID)
  using index 
  tablespace Lwbase
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create table
create table T_BA_CALENDAR
(
  id          VARCHAR2(40),
  year        CHAR(4),
  create_by   VARCHAR2(64) not null,
  create_date DATE not null,
  update_by   VARCHAR2(64) not null,
  update_date DATE not null,
  del_flag    CHAR(1) default '0' not null,
  work_date   DATE
)
tablespace Lwbase
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_BA_CALENDAR
  is '日历设置';
-- Add comments to the columns 
comment on column T_BA_CALENDAR.id
  is '主键';
comment on column T_BA_CALENDAR.year
  is '年份';
comment on column T_BA_CALENDAR.create_by
  is '创建人';
comment on column T_BA_CALENDAR.create_date
  is '创建时间';
comment on column T_BA_CALENDAR.update_by
  is '修改人';
comment on column T_BA_CALENDAR.update_date
  is '修改日期';
comment on column T_BA_CALENDAR.del_flag
  is '0正常 1删除';
comment on column T_BA_CALENDAR.work_date
  is '工作日';

