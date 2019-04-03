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
  is '�����';
-- Add comments to the columns 
comment on column SYS_AREA.id
  is '���';
comment on column SYS_AREA.parent_id
  is '�������';
comment on column SYS_AREA.parent_ids
  is '���и������';
comment on column SYS_AREA.name
  is '����';
comment on column SYS_AREA.sort
  is '����';
comment on column SYS_AREA.code
  is '�������';
comment on column SYS_AREA.type
  is '�������ͣ�1�����ң�2��ʡ�ݡ�ֱϽ�У�3�����У�4�����أ�';
comment on column SYS_AREA.create_by
  is '������';
comment on column SYS_AREA.create_date
  is '����ʱ��';
comment on column SYS_AREA.update_by
  is '������';
comment on column SYS_AREA.update_date
  is '����ʱ��';
comment on column SYS_AREA.remarks
  is '��ע��Ϣ';
comment on column SYS_AREA.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '�ֵ��';
-- Add comments to the columns 
comment on column SYS_DICT.id
  is '���';
comment on column SYS_DICT.value
  is '����ֵ';
comment on column SYS_DICT.label
  is '��ǩ��';
comment on column SYS_DICT.type
  is '����';
comment on column SYS_DICT.description
  is '����';
comment on column SYS_DICT.sort
  is '��������';
comment on column SYS_DICT.parent_id
  is '�������';
comment on column SYS_DICT.create_by
  is '������';
comment on column SYS_DICT.create_date
  is '����ʱ��';
comment on column SYS_DICT.update_by
  is '������';
comment on column SYS_DICT.update_date
  is '����ʱ��';
comment on column SYS_DICT.remarks
  is '��ע��Ϣ';
comment on column SYS_DICT.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '�˵���';
-- Add comments to the columns 
comment on column SYS_MENU.id
  is '���';
comment on column SYS_MENU.parent_id
  is '�������';
comment on column SYS_MENU.parent_ids
  is '���и������';
comment on column SYS_MENU.name
  is '����';
comment on column SYS_MENU.sort
  is '����';
comment on column SYS_MENU.href
  is '����';
comment on column SYS_MENU.target
  is 'Ŀ�꣨mainFrame�� _blank��_self��_parent��_top��';
comment on column SYS_MENU.icon
  is 'ͼ��';
comment on column SYS_MENU.is_show
  is '�Ƿ��ڲ˵�����ʾ��1����ʾ��0������ʾ��';
comment on column SYS_MENU.permission
  is 'Ȩ�ޱ�ʶ';
comment on column SYS_MENU.create_by
  is '������';
comment on column SYS_MENU.create_date
  is '����ʱ��';
comment on column SYS_MENU.update_by
  is '������';
comment on column SYS_MENU.update_date
  is '����ʱ��';
comment on column SYS_MENU.remarks
  is '��ע��Ϣ';
comment on column SYS_MENU.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '������';
-- Add comments to the columns 
comment on column SYS_OFFICE.id
  is '���';
comment on column SYS_OFFICE.parent_id
  is '�������';
comment on column SYS_OFFICE.parent_ids
  is '���и������';
comment on column SYS_OFFICE.name
  is '����';
comment on column SYS_OFFICE.sort
  is '����';
comment on column SYS_OFFICE.area_id
  is '��������';
comment on column SYS_OFFICE.code
  is '�������';
comment on column SYS_OFFICE.type
  is '�������ͣ�1����˾��2�����ţ�3��С�飩';
comment on column SYS_OFFICE.grade
  is '�����ȼ���1��һ����2��������3��������4���ļ���';
comment on column SYS_OFFICE.address
  is '��ϵ��ַ';
comment on column SYS_OFFICE.zip_code
  is '��������';
comment on column SYS_OFFICE.master
  is '������';
comment on column SYS_OFFICE.phone
  is '�绰';
comment on column SYS_OFFICE.fax
  is '����';
comment on column SYS_OFFICE.email
  is '����';
comment on column SYS_OFFICE.useable
  is '�Ƿ�����';
comment on column SYS_OFFICE.primary_person
  is '��������';
comment on column SYS_OFFICE.deputy_person
  is '��������';
comment on column SYS_OFFICE.create_by
  is '������';
comment on column SYS_OFFICE.create_date
  is '����ʱ��';
comment on column SYS_OFFICE.update_by
  is '������';
comment on column SYS_OFFICE.update_date
  is '����ʱ��';
comment on column SYS_OFFICE.remarks
  is '��ע��Ϣ';
comment on column SYS_OFFICE.del_flag
  is 'ɾ�����';
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
  is '�û���';
-- Add comments to the columns 
comment on column SYS_USER.id
  is '���';
comment on column SYS_USER.company_id
  is '������˾';
comment on column SYS_USER.office_id
  is '��������';
comment on column SYS_USER.login_name
  is '��¼��';
comment on column SYS_USER.password
  is '����';
comment on column SYS_USER.no
  is '����';
comment on column SYS_USER.name
  is '����';
comment on column SYS_USER.email
  is '����';
comment on column SYS_USER.phone
  is '�绰';
comment on column SYS_USER.mobile
  is '�ֻ�';
comment on column SYS_USER.user_type
  is '�û�����';
comment on column SYS_USER.photo
  is '�û�ͷ��';
comment on column SYS_USER.login_ip
  is '����½IP';
comment on column SYS_USER.login_date
  is '����½ʱ��';
comment on column SYS_USER.login_flag
  is '�Ƿ�ɵ�¼';
comment on column SYS_USER.create_by
  is '������';
comment on column SYS_USER.create_date
  is '����ʱ��';
comment on column SYS_USER.update_by
  is '������';
comment on column SYS_USER.update_date
  is '����ʱ��';
comment on column SYS_USER.remarks
  is '��ע��Ϣ';
comment on column SYS_USER.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '��ɫ��';
-- Add comments to the columns 
comment on column SYS_ROLE.id
  is '���';
comment on column SYS_ROLE.office_id
  is '��������';
comment on column SYS_ROLE.name
  is '��ɫ����';
comment on column SYS_ROLE.enname
  is 'Ӣ������';
comment on column SYS_ROLE.role_type
  is '��ɫ����';
comment on column SYS_ROLE.data_scope
  is '���ݷ�Χ��0���������ݣ�1�����ڹ�˾���������ݣ�2�����ڹ�˾���ݣ�3�����ڲ��ż��������ݣ�4�����ڲ������ݣ�8�����������ݣ�9������ϸ���ã�';
comment on column SYS_ROLE.is_sys
  is '�Ƿ�ϵͳ����';
comment on column SYS_ROLE.useable
  is '�Ƿ����';
comment on column SYS_ROLE.create_by
  is '������';
comment on column SYS_ROLE.create_date
  is '����ʱ��';
comment on column SYS_ROLE.update_by
  is '������';
comment on column SYS_ROLE.update_date
  is '����ʱ��';
comment on column SYS_ROLE.remarks
  is '��ע��Ϣ';
comment on column SYS_ROLE.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '��ɫ-�˵�';
-- Add comments to the columns 
comment on column SYS_ROLE_MENU.role_id
  is '��ɫ���';
comment on column SYS_ROLE_MENU.menu_id
  is '�˵����';
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
  is '��ɫ-����';
-- Add comments to the columns 
comment on column SYS_ROLE_OFFICE.role_id
  is '��ɫ���';
comment on column SYS_ROLE_OFFICE.office_id
  is '�������';
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
  is '�û�-��ɫ';
-- Add comments to the columns 
comment on column SYS_USER_ROLE.user_id
  is '�û����';
comment on column SYS_USER_ROLE.role_id
  is '��ɫ���';
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
  is '��־��';
-- Add comments to the columns 
comment on column SYS_LOG.id
  is '���';
comment on column SYS_LOG.type
  is '��־���ͣ�1��������־��2���쳣��־��';
comment on column SYS_LOG.title
  is '�������ܱ���';
comment on column SYS_LOG.create_by
  is '������';
comment on column SYS_LOG.create_date
  is '����ʱ��';
comment on column SYS_LOG.remote_addr
  is '����IP��ַ';
comment on column SYS_LOG.user_agent
  is '�û�����';
comment on column SYS_LOG.request_uri
  is '����URI';
comment on column SYS_LOG.method
  is '������ʽ';
comment on column SYS_LOG.params
  is '�����ύ������';
comment on column SYS_LOG.exception
  is '�쳣��Ϣ';
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
  is '���ɷ���';
-- Add comments to the columns 
comment on column GEN_SCHEME.id
  is '���';
comment on column GEN_SCHEME.name
  is '����';
comment on column GEN_SCHEME.category
  is '����';
comment on column GEN_SCHEME.package_name
  is '���ɰ�·��';
comment on column GEN_SCHEME.module_name
  is '����ģ����';
comment on column GEN_SCHEME.sub_module_name
  is '������ģ����';
comment on column GEN_SCHEME.function_name
  is '���ɹ�����';
comment on column GEN_SCHEME.function_name_simple
  is '���ɹ���������д��';
comment on column GEN_SCHEME.function_author
  is '���ɹ�������';
comment on column GEN_SCHEME.gen_table_id
  is '���ɱ���';
comment on column GEN_SCHEME.create_by
  is '������';
comment on column GEN_SCHEME.create_date
  is '����ʱ��';
comment on column GEN_SCHEME.update_by
  is '������';
comment on column GEN_SCHEME.update_date
  is '����ʱ��';
comment on column GEN_SCHEME.remarks
  is '��ע��Ϣ';
comment on column GEN_SCHEME.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is 'ҵ���';
-- Add comments to the columns 
comment on column GEN_TABLE.id
  is '���';
comment on column GEN_TABLE.name
  is '����';
comment on column GEN_TABLE.comments
  is '����';
comment on column GEN_TABLE.class_name
  is 'ʵ��������';
comment on column GEN_TABLE.parent_table
  is '��������';
comment on column GEN_TABLE.parent_table_fk
  is '�����������';
comment on column GEN_TABLE.create_by
  is '������';
comment on column GEN_TABLE.create_date
  is '����ʱ��';
comment on column GEN_TABLE.update_by
  is '������';
comment on column GEN_TABLE.update_date
  is '����ʱ��';
comment on column GEN_TABLE.remarks
  is '��ע��Ϣ';
comment on column GEN_TABLE.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is 'ҵ����ֶ�';
-- Add comments to the columns 
comment on column GEN_TABLE_COLUMN.id
  is '���';
comment on column GEN_TABLE_COLUMN.gen_table_id
  is '��������';
comment on column GEN_TABLE_COLUMN.name
  is '����';
comment on column GEN_TABLE_COLUMN.comments
  is '����';
comment on column GEN_TABLE_COLUMN.jdbc_type
  is '�е��������͵��ֽڳ���';
comment on column GEN_TABLE_COLUMN.java_type
  is 'JAVA����';
comment on column GEN_TABLE_COLUMN.java_field
  is 'JAVA�ֶ���';
comment on column GEN_TABLE_COLUMN.is_pk
  is '�Ƿ�����';
comment on column GEN_TABLE_COLUMN.is_null
  is '�Ƿ��Ϊ��';
comment on column GEN_TABLE_COLUMN.is_insert
  is '�Ƿ�Ϊ�����ֶ�';
comment on column GEN_TABLE_COLUMN.is_edit
  is '�Ƿ�༭�ֶ�';
comment on column GEN_TABLE_COLUMN.is_list
  is '�Ƿ��б��ֶ�';
comment on column GEN_TABLE_COLUMN.is_query
  is '�Ƿ��ѯ�ֶ�';
comment on column GEN_TABLE_COLUMN.query_type
  is '��ѯ��ʽ�����ڡ������ڡ����ڡ�С�ڡ���Χ����LIKE����LIKE������LIKE��';
comment on column GEN_TABLE_COLUMN.show_type
  is '�ֶ����ɷ������ı����ı��������򡢸�ѡ�򡢵�ѡ���ֵ�ѡ����Աѡ�񡢲���ѡ������ѡ��';
comment on column GEN_TABLE_COLUMN.dict_type
  is '�ֵ�����';
comment on column GEN_TABLE_COLUMN.settings
  is '�������ã���չ�ֶ�JSON��';
comment on column GEN_TABLE_COLUMN.sort
  is '��������';
comment on column GEN_TABLE_COLUMN.create_by
  is '������';
comment on column GEN_TABLE_COLUMN.create_date
  is '����ʱ��';
comment on column GEN_TABLE_COLUMN.update_by
  is '������';
comment on column GEN_TABLE_COLUMN.update_date
  is '����ʱ��';
comment on column GEN_TABLE_COLUMN.remarks
  is '��ע��Ϣ';
comment on column GEN_TABLE_COLUMN.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '����ģ���';
-- Add comments to the columns 
comment on column GEN_TEMPLATE.id
  is '���';
comment on column GEN_TEMPLATE.name
  is '����';
comment on column GEN_TEMPLATE.category
  is '����';
comment on column GEN_TEMPLATE.file_path
  is '�����ļ�·��';
comment on column GEN_TEMPLATE.file_name
  is '�����ļ���';
comment on column GEN_TEMPLATE.content
  is '����';
comment on column GEN_TEMPLATE.create_by
  is '������';
comment on column GEN_TEMPLATE.create_date
  is '����ʱ��';
comment on column GEN_TEMPLATE.update_by
  is '������';
comment on column GEN_TEMPLATE.update_date
  is '����ʱ��';
comment on column GEN_TEMPLATE.remarks
  is '��ע��Ϣ';
comment on column GEN_TEMPLATE.del_flag
  is 'ɾ����ǣ�0��������1��ɾ����';
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
  is '����ģ�����ñ�';
-- Add comments to the columns 
comment on column T_BA_ATT.id
  is '����
';
comment on column T_BA_ATT.create_by
  is '������
';
comment on column T_BA_ATT.create_date
  is '����ʱ��
';
comment on column T_BA_ATT.update_by
  is '�޸���
';
comment on column T_BA_ATT.update_date
  is '�޸�ʱ��
';
comment on column T_BA_ATT.remarks
  is '��ע';
comment on column T_BA_ATT.del_flag
  is '0����1ɾ��
';
comment on column T_BA_ATT.sort
  is '˳��
';
comment on column T_BA_ATT.use_state
  is 'ʹ��״̬
';
comment on column T_BA_ATT.isrequired
  is '�Ƿ����0��1����
';
comment on column T_BA_ATT.type
  is '�ļ�����
';
comment on column T_BA_ATT.office_id
  is '����ID';
comment on column T_BA_ATT.file_id
  is '�ļ�ID';
comment on column T_BA_ATT.name
  is '����';
comment on column T_BA_ATT.max_size
  is '�ļ���С����';
comment on column T_BA_ATT.is_sort
  is '��Ҫ�������¶���0��1��';
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
  is '����
';
comment on column T_BA_PARAMETER.create_by
  is '������
';
comment on column T_BA_PARAMETER.create_date
  is '����ʱ��
';
comment on column T_BA_PARAMETER.update_by
  is '�޸���
';
comment on column T_BA_PARAMETER.update_date
  is '�޸�ʱ��
';
comment on column T_BA_PARAMETER.remarks
  is '��ע
';
comment on column T_BA_PARAMETER.del_flag
  is '0����1ɾ��
';
comment on column T_BA_PARAMETER.sort
  is '˳��
';
comment on column T_BA_PARAMETER.use_state
  is 'ʹ��״̬
';
comment on column T_BA_PARAMETER.para_value
  is '����ֵ
';
comment on column T_BA_PARAMETER.para_key
  is '������
';
comment on column T_BA_PARAMETER.temp_id
  is 'ģ��ID';
comment on column T_BA_PARAMETER.para_name
  is '��������
';
comment on column T_BA_PARAMETER.para_type
  is '��������
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
  is '����������';
-- Add comments to the columns 
comment on column T_BA_FILE.id
  is '����
';
comment on column T_BA_FILE.create_by
  is '������
';
comment on column T_BA_FILE.create_date
  is '����ʱ��
';
comment on column T_BA_FILE.update_by
  is '������
';
comment on column T_BA_FILE.update_date
  is '����ʱ��
';
comment on column T_BA_FILE.remarks
  is '��������
';
comment on column T_BA_FILE.del_flag
  is '0 ���� 1ɾ��
';
comment on column T_BA_FILE.file_name
  is '�ļ�����
';
comment on column T_BA_FILE.file_size
  is '������С
';
comment on column T_BA_FILE.file_type
  is '��������
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
  is '����ע���';
-- Add comments to the columns 
comment on column T_BA_REGISTERED.id
  is '����';
comment on column T_BA_REGISTERED.name
  is '��������';
comment on column T_BA_REGISTERED.org_code
  is '��֯��������';
comment on column T_BA_REGISTERED.user_id
  is 'ϵͳ�û�ID';
comment on column T_BA_REGISTERED.phone
  is 'ע�����ֻ���';
comment on column T_BA_REGISTERED.create_by
  is '������';
comment on column T_BA_REGISTERED.create_date
  is '����ʱ��';
comment on column T_BA_REGISTERED.update_by
  is '������';
comment on column T_BA_REGISTERED.update_date
  is '����ʱ��';
comment on column T_BA_REGISTERED.remarks
  is '��ע
';
comment on column T_BA_REGISTERED.del_flag
  is '0����1ɾ��
';
comment on column T_BA_REGISTERED.approval_code
  is '��֤������׼��';
comment on column T_BA_REGISTERED.address_code
  is '������ַ����';
comment on column T_BA_REGISTERED.address
  is '������ַ����';
comment on column T_BA_REGISTERED.head_code
  is '����ע���';
comment on column T_BA_REGISTERED.head_name
  is '����������';
comment on column T_BA_REGISTERED.head_natrue
  is '��������';
comment on column T_BA_REGISTERED.org_fixedtel
  is '�̶��绰';
comment on column T_BA_REGISTERED.org_tel
  is '�����';
comment on column T_BA_REGISTERED.contact_name
  is '��ϵ��';
comment on column T_BA_REGISTERED.contact_phone
  is '��ϵ�˵绰';
comment on column T_BA_REGISTERED.e_mail
  is 'Email';
comment on column T_BA_REGISTERED.capital
  is 'ע���ʱ�';
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
  is '����ʵ����';
-- Add comments to the columns 
comment on column T_BA_ACTIVITY.id
  is '����';
comment on column T_BA_ACTIVITY.temp_activity_id
  is '���̶���ID';
comment on column T_BA_ACTIVITY.apply_code
  is '������';
comment on column T_BA_ACTIVITY.apply_state
  is '����״̬';
comment on column T_BA_ACTIVITY.apply_id
  is '����ID';
comment on column T_BA_ACTIVITY.status
  is '����ʵ��״̬:1������2����3��ֹ4��ͣ';
comment on column T_BA_ACTIVITY.review_advice
  is '�������';
comment on column T_BA_ACTIVITY.company_id
  is '��֯����ID';
comment on column T_BA_ACTIVITY.org_name
  is '��������';
comment on column T_BA_ACTIVITY.user_id
  is '����ID';
comment on column T_BA_ACTIVITY.create_by
  is '������';
comment on column T_BA_ACTIVITY.create_date
  is '����ʱ��';
comment on column T_BA_ACTIVITY.update_by
  is '�޸���';
comment on column T_BA_ACTIVITY.update_date
  is '�޸�����';
comment on column T_BA_ACTIVITY.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_ACTIVITY.remarks
  is '��ע';
comment on column T_BA_ACTIVITY.activity_name
  is '���̶�������';
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
  is '���̽ڵ��';
-- Add comments to the columns 
comment on column T_BA_NOTE.id
  is '����';
comment on column T_BA_NOTE.temp_note_id
  is '���̽ڵ�ģ��ID';
comment on column T_BA_NOTE.activity_id
  is '����ʵ��ID';
comment on column T_BA_NOTE.name
  is '�ڵ�����';
comment on column T_BA_NOTE.code
  is '�ڵ����';
comment on column T_BA_NOTE.status
  is '�ڵ�״̬';
comment on column T_BA_NOTE.create_by
  is '������';
comment on column T_BA_NOTE.create_date
  is '����ʱ��';
comment on column T_BA_NOTE.update_by
  is '�޸���';
comment on column T_BA_NOTE.update_date
  is '�޸�����';
comment on column T_BA_NOTE.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_NOTE.remarks
  is '��ע';
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
  is '���̽ڵ��ɫ��';
-- Add comments to the columns 
comment on column T_BA_NOTE_ROLE.id
  is '����';
comment on column T_BA_NOTE_ROLE.role_id
  is '��ɫID';
comment on column T_BA_NOTE_ROLE.note_id
  is '�ڵ�ID';
comment on column T_BA_NOTE_ROLE.create_by
  is '������';
comment on column T_BA_NOTE_ROLE.create_date
  is '����ʱ��';
comment on column T_BA_NOTE_ROLE.update_by
  is '�޸���';
comment on column T_BA_NOTE_ROLE.update_date
  is '�޸�����';
comment on column T_BA_NOTE_ROLE.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_NOTE_ROLE.remarks
  is '��ע';
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
  is '���������';
-- Add comments to the columns 
comment on column T_BA_TASK.id
  is '����';
comment on column T_BA_TASK.note_id
  is '�ڵ�ID';
comment on column T_BA_TASK.activity_id
  is '����ID';
comment on column T_BA_TASK.note_name
  is '�ڵ�����';
comment on column T_BA_TASK.dual_type
  is '��������;01��λ����ת02��һ�ڵ�03�˻�';
comment on column T_BA_TASK.dual_opinion
  is '�������';
comment on column T_BA_TASK.opinion_content
  is '���˵��';
comment on column T_BA_TASK.update_name
  is '����������';
comment on column T_BA_TASK.create_name
  is '��һ������������';
comment on column T_BA_TASK.att_ids
  is '����ID';
comment on column T_BA_TASK.create_by
  is '�����ˣ���һ��������ID��';
comment on column T_BA_TASK.create_date
  is '����ʱ�䣨��һ������ʱ�䣩';
comment on column T_BA_TASK.update_by
  is '�޸��ˣ�������ID��';
comment on column T_BA_TASK.update_date
  is '�޸����ڣ�����ʱ�䣩';
comment on column T_BA_TASK.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_TASK.remarks
  is '��ע';
comment on column T_BA_TASK.status
  is '��������״̬:1������2����3��ֹ4��ͣ';
comment on column T_BA_TASK.last_task_id
  is '��һ��������TD';
comment on column T_BA_TASK.role_id
  is '�����ɫID';
comment on column T_BA_TASK.standby
  is '����';
comment on column T_BA_TASK.standby1
  is '����';
comment on column T_BA_TASK.days
  is '����ʱ��';
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
  is '����ʵ�������';
-- Add comments to the columns 
comment on column T_BA_TEMP_ACTIVITY.id
  is '����';
comment on column T_BA_TEMP_ACTIVITY.activity_name
  is '���̶�������';
comment on column T_BA_TEMP_ACTIVITY.logo
  is '���̱�ʶ';
comment on column T_BA_TEMP_ACTIVITY.status
  is '״̬:1������2��ֹ3��ͣ';
comment on column T_BA_TEMP_ACTIVITY.create_by
  is '������';
comment on column T_BA_TEMP_ACTIVITY.create_date
  is '����ʱ��';
comment on column T_BA_TEMP_ACTIVITY.update_by
  is '�޸���';
comment on column T_BA_TEMP_ACTIVITY.update_date
  is '�޸�����';
comment on column T_BA_TEMP_ACTIVITY.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_TEMP_ACTIVITY.remarks
  is '��ע';
comment on column T_BA_TEMP_ACTIVITY.company_id
  is '��˾';
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
  is '���̽ڵ㶨���';
-- Add comments to the columns 
comment on column T_BA_TEMP_NOTE.id
  is '����';
comment on column T_BA_TEMP_NOTE.name
  is '���̽ڵ�����';
comment on column T_BA_TEMP_NOTE.activity_id
  is '����ʵ��ģ��ID';
comment on column T_BA_TEMP_NOTE.status
  is '״̬:1������2��ֹ3��ͣ';
comment on column T_BA_TEMP_NOTE.send_sms
  is '���Ͷ���:1����2������';
comment on column T_BA_TEMP_NOTE.sms_content
  is '���Ͷ�������';
comment on column T_BA_TEMP_NOTE.priority
  is '���ȼ�';
comment on column T_BA_TEMP_NOTE.create_by
  is '������';
comment on column T_BA_TEMP_NOTE.create_date
  is '����ʱ��';
comment on column T_BA_TEMP_NOTE.update_by
  is '�޸���';
comment on column T_BA_TEMP_NOTE.update_date
  is '�޸�����';
comment on column T_BA_TEMP_NOTE.del_flag
  is '�Ƿ���Ч:0 ���� 1ɾ�� 2��Ч';
comment on column T_BA_TEMP_NOTE.remarks
  is '��ע';
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
  is '��������';
-- Add comments to the columns 
comment on column T_BA_CALENDAR.id
  is '����';
comment on column T_BA_CALENDAR.year
  is '���';
comment on column T_BA_CALENDAR.create_by
  is '������';
comment on column T_BA_CALENDAR.create_date
  is '����ʱ��';
comment on column T_BA_CALENDAR.update_by
  is '�޸���';
comment on column T_BA_CALENDAR.update_date
  is '�޸�����';
comment on column T_BA_CALENDAR.del_flag
  is '0���� 1ɾ��';
comment on column T_BA_CALENDAR.work_date
  is '������';

