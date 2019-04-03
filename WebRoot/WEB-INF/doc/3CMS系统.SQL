


CREATE TABLE cms_article 
( 
  id varchar2(64) NOT NULL, 
  category_id varchar2(64) NOT NULL, 
  title varchar2(255) NOT NULL, 
  link varchar2(255), 
  color varchar2(50), 
  image varchar2(255), 
  keywords varchar2(255), 
  description varchar2(255), 
  weight number(10,0) DEFAULT 0, 
  weight_date timestamp, 
  hits number(10,0) DEFAULT 0, 
  posid varchar2(10), 
  custom_content_view varchar2(255), 
  view_config clob, 
  create_by varchar2(64), 
  create_date timestamp, 
  update_by varchar2(64), 
  update_date timestamp, 
  remarks varchar2(255), 
  del_flag char(1) DEFAULT '0' NOT NULL, 
  PRIMARY KEY (id) 
); 




CREATE TABLE cms_article_data 
( 
  id varchar2(64) NOT NULL, 
  content clob, 
  copyfrom varchar2(255), 
  relation varchar2(255), 
  allow_comment char(1), 
  PRIMARY KEY (id) 
); 




CREATE TABLE cms_category 
( 
  id varchar2(64) NOT NULL, 
  parent_id varchar2(64) NOT NULL, 
  parent_ids varchar2(2000) NOT NULL, 
  site_id varchar2(64) DEFAULT '1', 
  office_id varchar2(64), 
  module varchar2(20), 
  name varchar2(100) NOT NULL, 
  image varchar2(255), 
  href varchar2(255), 
  target varchar2(20), 
  description varchar2(255), 
  keywords varchar2(255), 
  sort number(10,0) DEFAULT 30, 
  in_menu char(1) DEFAULT '1', 
  in_list char(1) DEFAULT '1', 
  show_modes char(1) DEFAULT '0', 
  allow_comment char(1), 
  is_audit char(1), 
  custom_list_view varchar2(255), 
  custom_content_view varchar2(255), 
  view_config clob, 
  create_by varchar2(64), 
  create_date timestamp, 
  update_by varchar2(64), 
  update_date timestamp, 
  remarks varchar2(255), 
  del_flag char(1) DEFAULT '0' NOT NULL, 
  PRIMARY KEY (id) 
); 




CREATE TABLE cms_comment 
( 
  id varchar2(64) NOT NULL, 
  category_id varchar2(64) NOT NULL, 
  content_id varchar2(64) NOT NULL, 
  title varchar2(255), 
  content varchar2(255), 
  name varchar2(100), 
  ip varchar2(100), 
  create_date timestamp NOT NULL, 
  audit_user_id varchar2(64), 
  audit_date timestamp, 
  del_flag char(1) DEFAULT '0' NOT NULL, 
   PRIMARY KEY (id) 
 ); 
 

 

 CREATE TABLE cms_guestbook 
 ( 
   id varchar2(64) NOT NULL, 
   type char(1) NOT NULL, 
   content varchar2(255) NOT NULL, 
   name varchar2(100) NOT NULL, 
   email varchar2(100) NOT NULL, 
   phone varchar2(100) NOT NULL, 
   workunit varchar2(100) NOT NULL, 
   ip varchar2(100) NOT NULL, 
   create_date timestamp NOT NULL, 
   re_user_id varchar2(64), 
   re_date timestamp, 
   re_content varchar2(100), 
   del_flag char(1) DEFAULT '0' NOT NULL, 
   PRIMARY KEY (id) 
 ); 
 

 

 CREATE TABLE cms_link 
 ( 
   id varchar2(64) NOT NULL, 
   category_id varchar2(64) NOT NULL, 
   title varchar2(255) NOT NULL, 
   color varchar2(50), 
   image varchar2(255), 
   href varchar2(255), 
   weight number(10,0) DEFAULT 0, 
   weight_date timestamp, 
   create_by varchar2(64), 
   create_date timestamp, 
   update_by varchar2(64), 
   update_date timestamp, 
   remarks varchar2(255), 
   del_flag char(1) DEFAULT '0' NOT NULL, 
   PRIMARY KEY (id) 
 ); 
 

 

 CREATE TABLE cms_site 
 ( 
   id varchar2(64) NOT NULL, 
   name varchar2(100) NOT NULL, 
   title varchar2(100) NOT NULL, 
   logo varchar2(255), 
   domain varchar2(255), 
   description varchar2(255), 
   keywords varchar2(255), 
   theme varchar2(255) DEFAULT 'default', 
   copyright clob, 
   custom_index_view varchar2(255), 
   create_by varchar2(64), 
   create_date timestamp, 
   update_by varchar2(64), 
   update_date timestamp, 
   remarks varchar2(255), 
   del_flag char(1) DEFAULT '0' NOT NULL, 
   PRIMARY KEY (id) 
 ); 
 

 

 

 /* Create Indexes */ 
 

 CREATE INDEX cms_article_create_by ON cms_article (create_by); 
 CREATE INDEX cms_article_title ON cms_article (title); 
 CREATE INDEX cms_article_keywords ON cms_article (keywords); 
 CREATE INDEX cms_article_del_flag ON cms_article (del_flag); 
 CREATE INDEX cms_article_weight ON cms_article (weight); 
 CREATE INDEX cms_article_update_date ON cms_article (update_date); 
 CREATE INDEX cms_article_category_id ON cms_article (category_id); 
 CREATE INDEX cms_category_parent_id ON cms_category (parent_id); 
 CREATE INDEX cms_category_parent_ids ON cms_category (parent_ids); 
 CREATE INDEX cms_category_module ON cms_category (module); 
 CREATE INDEX cms_category_name ON cms_category (name); 
 CREATE INDEX cms_category_sort ON cms_category (sort); 
 CREATE INDEX cms_category_del_flag ON cms_category (del_flag); 
 CREATE INDEX cms_category_office_id ON cms_category (office_id); 
 CREATE INDEX cms_category_site_id ON cms_category (site_id); 
 CREATE INDEX cms_comment_category_id ON cms_comment (category_id); 
 CREATE INDEX cms_comment_content_id ON cms_comment (content_id); 
 CREATE INDEX cms_comment_status ON cms_comment (del_flag); 
 CREATE INDEX cms_guestbook_del_flag ON cms_guestbook (del_flag); 
 CREATE INDEX cms_link_category_id ON cms_link (category_id); 
 CREATE INDEX cms_link_title ON cms_link (title); 
 CREATE INDEX cms_link_del_flag ON cms_link (del_flag); 
 CREATE INDEX cms_link_weight ON cms_link (weight); 
 CREATE INDEX cms_link_create_by ON cms_link (create_by); 
 CREATE INDEX cms_link_update_date ON cms_link (update_date); 
 CREATE INDEX cms_site_del_flag ON cms_site (del_flag); 
 

 

 

 /* Comments */ 
 

 COMMENT ON TABLE cms_article IS '文章表'; 
 COMMENT ON COLUMN cms_article.id IS '编号'; 
 COMMENT ON COLUMN cms_article.category_id IS '栏目编号'; 
 COMMENT ON COLUMN cms_article.title IS '标题'; 
 COMMENT ON COLUMN cms_article.link IS '文章链接'; 
 COMMENT ON COLUMN cms_article.color IS '标题颜色'; 
 COMMENT ON COLUMN cms_article.image IS '文章图片'; 
 COMMENT ON COLUMN cms_article.keywords IS '关键字'; 
 COMMENT ON COLUMN cms_article.description IS '描述、摘要'; 
 COMMENT ON COLUMN cms_article.weight IS '权重，越大越靠前'; 
 COMMENT ON COLUMN cms_article.weight_date IS '权重期限'; 
 COMMENT ON COLUMN cms_article.hits IS '点击数'; 
 COMMENT ON COLUMN cms_article.posid IS '推荐位，多选'; 
 COMMENT ON COLUMN cms_article.custom_content_view IS '自定义内容视图'; 
 COMMENT ON COLUMN cms_article.view_config IS '视图配置'; 
 COMMENT ON COLUMN cms_article.create_by IS '创建者'; 
 COMMENT ON COLUMN cms_article.create_date IS '创建时间'; 
 COMMENT ON COLUMN cms_article.update_by IS '更新者'; 
 COMMENT ON COLUMN cms_article.update_date IS '更新时间'; 
 COMMENT ON COLUMN cms_article.remarks IS '备注信息'; 
 COMMENT ON COLUMN cms_article.del_flag IS '删除标记'; 
 COMMENT ON TABLE cms_article_data IS '文章详表'; 
 COMMENT ON COLUMN cms_article_data.id IS '编号'; 
 COMMENT ON COLUMN cms_article_data.content IS '文章内容'; 
 COMMENT ON COLUMN cms_article_data.copyfrom IS '文章来源'; 
 COMMENT ON COLUMN cms_article_data.relation IS '相关文章'; 
 COMMENT ON COLUMN cms_article_data.allow_comment IS '是否允许评论'; 
 COMMENT ON TABLE cms_category IS '栏目表'; 
 COMMENT ON COLUMN cms_category.id IS '编号'; 
 COMMENT ON COLUMN cms_category.parent_id IS '父级编号'; 
 COMMENT ON COLUMN cms_category.parent_ids IS '所有父级编号'; 
 COMMENT ON COLUMN cms_category.site_id IS '站点编号'; 
 COMMENT ON COLUMN cms_category.office_id IS '归属机构'; 
 COMMENT ON COLUMN cms_category.module IS '栏目模块'; 
 COMMENT ON COLUMN cms_category.name IS '栏目名称'; 
 COMMENT ON COLUMN cms_category.image IS '栏目图片'; 
 COMMENT ON COLUMN cms_category.href IS '链接'; 
 COMMENT ON COLUMN cms_category.target IS '目标'; 
 COMMENT ON COLUMN cms_category.description IS '描述'; 
 COMMENT ON COLUMN cms_category.keywords IS '关键字'; 
 COMMENT ON COLUMN cms_category.sort IS '排序（升序）'; 
 COMMENT ON COLUMN cms_category.in_menu IS '是否在导航中显示'; 
 COMMENT ON COLUMN cms_category.in_list IS '是否在分类页中显示列表'; 
 COMMENT ON COLUMN cms_category.show_modes IS '展现方式'; 
 COMMENT ON COLUMN cms_category.allow_comment IS '是否允许评论'; 
 COMMENT ON COLUMN cms_category.is_audit IS '是否需要审核'; 
 COMMENT ON COLUMN cms_category.custom_list_view IS '自定义列表视图'; 
 COMMENT ON COLUMN cms_category.custom_content_view IS '自定义内容视图'; 
 COMMENT ON COLUMN cms_category.view_config IS '视图配置'; 
 COMMENT ON COLUMN cms_category.create_by IS '创建者'; 
 COMMENT ON COLUMN cms_category.create_date IS '创建时间'; 
 COMMENT ON COLUMN cms_category.update_by IS '更新者'; 
 COMMENT ON COLUMN cms_category.update_date IS '更新时间'; 
 COMMENT ON COLUMN cms_category.remarks IS '备注信息'; 
 COMMENT ON COLUMN cms_category.del_flag IS '删除标记'; 
 COMMENT ON TABLE cms_comment IS '评论表'; 
 COMMENT ON COLUMN cms_comment.id IS '编号'; 
 COMMENT ON COLUMN cms_comment.category_id IS '栏目编号'; 
 COMMENT ON COLUMN cms_comment.content_id IS '栏目内容的编号'; 
 COMMENT ON COLUMN cms_comment.title IS '栏目内容的标题'; 
 COMMENT ON COLUMN cms_comment.content IS '评论内容'; 
 COMMENT ON COLUMN cms_comment.name IS '评论姓名'; 
 COMMENT ON COLUMN cms_comment.ip IS '评论IP'; 
 COMMENT ON COLUMN cms_comment.create_date IS '评论时间'; 
 COMMENT ON COLUMN cms_comment.audit_user_id IS '审核人'; 
 COMMENT ON COLUMN cms_comment.audit_date IS '审核时间'; 
 COMMENT ON COLUMN cms_comment.del_flag IS '删除标记'; 
 COMMENT ON TABLE cms_guestbook IS '留言板'; 
 COMMENT ON COLUMN cms_guestbook.id IS '编号'; 
 COMMENT ON COLUMN cms_guestbook.type IS '留言分类'; 
 COMMENT ON COLUMN cms_guestbook.content IS '留言内容'; 
 COMMENT ON COLUMN cms_guestbook.name IS '姓名'; 
 COMMENT ON COLUMN cms_guestbook.email IS '邮箱'; 
 COMMENT ON COLUMN cms_guestbook.phone IS '电话'; 
 COMMENT ON COLUMN cms_guestbook.workunit IS '单位'; 
 COMMENT ON COLUMN cms_guestbook.ip IS 'IP'; 
 COMMENT ON COLUMN cms_guestbook.create_date IS '留言时间'; 
 COMMENT ON COLUMN cms_guestbook.re_user_id IS '回复人'; 
 COMMENT ON COLUMN cms_guestbook.re_date IS '回复时间'; 
 COMMENT ON COLUMN cms_guestbook.re_content IS '回复内容'; 
 COMMENT ON COLUMN cms_guestbook.del_flag IS '删除标记'; 
 COMMENT ON TABLE cms_link IS '友情链接'; 
 COMMENT ON COLUMN cms_link.id IS '编号'; 
 COMMENT ON COLUMN cms_link.category_id IS '栏目编号'; 
 COMMENT ON COLUMN cms_link.title IS '链接名称'; 
 COMMENT ON COLUMN cms_link.color IS '标题颜色'; 
 COMMENT ON COLUMN cms_link.image IS '链接图片'; 
 COMMENT ON COLUMN cms_link.href IS '链接地址'; 
 COMMENT ON COLUMN cms_link.weight IS '权重，越大越靠前'; 
 COMMENT ON COLUMN cms_link.weight_date IS '权重期限'; 
 COMMENT ON COLUMN cms_link.create_by IS '创建者'; 
 COMMENT ON COLUMN cms_link.create_date IS '创建时间'; 
 COMMENT ON COLUMN cms_link.update_by IS '更新者'; 
 COMMENT ON COLUMN cms_link.update_date IS '更新时间'; 
 COMMENT ON COLUMN cms_link.remarks IS '备注信息'; 
 COMMENT ON COLUMN cms_link.del_flag IS '删除标记'; 
 COMMENT ON TABLE cms_site IS '站点表'; 
 COMMENT ON COLUMN cms_site.id IS '编号'; 
 COMMENT ON COLUMN cms_site.name IS '站点名称'; 
 COMMENT ON COLUMN cms_site.title IS '站点标题'; 
 COMMENT ON COLUMN cms_site.logo IS '站点Logo'; 
 COMMENT ON COLUMN cms_site.domain IS '站点域名'; 
 COMMENT ON COLUMN cms_site.description IS '描述'; 
 COMMENT ON COLUMN cms_site.keywords IS '关键字'; 
 COMMENT ON COLUMN cms_site.theme IS '主题'; 
 COMMENT ON COLUMN cms_site.copyright IS '版权信息'; 
 COMMENT ON COLUMN cms_site.custom_index_view IS '自定义站点首页视图'; 
 COMMENT ON COLUMN cms_site.create_by IS '创建者'; 
 COMMENT ON COLUMN cms_site.create_date IS '创建时间'; 
 COMMENT ON COLUMN cms_site.update_by IS '更新者'; 
 COMMENT ON COLUMN cms_site.update_date IS '更新时间'; 
 COMMENT ON COLUMN cms_site.remarks IS '备注信息'; 
 COMMENT ON COLUMN cms_site.del_flag IS '删除标记'; 




insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('1811f34d1c0a4a949ee8bf17abdaf8d8', '83c20e3945fe46c5adfe4f7a94092c05', '0,1,2,3b88d4604c634d679315884072e31b9b,83c20e3945fe46c5adfe4f7a94092c05,', '修改', 60, null, null, null, '0', 'cms:category:edit', '1', to_date('03-06-2016 14:31:17', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:31:17', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('79fddd2bb71341aba8266128a2cada69', 'dd885cab55514c38b0f189639b37d2b3', '0,1,2,3b88d4604c634d679315884072e31b9b,dd885cab55514c38b0f189639b37d2b3,', '修改', 60, null, null, null, '0', 'cms:site:edit', '1', to_date('03-06-2016 14:33:54', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:33:54', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('e808215d0baa4e15b6b26108abbbb645', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '文章模型', 90, '/cms/article/', null, null, '1', null, '1', to_date('03-06-2016 14:35:09', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:35:09', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('3b88d4604c634d679315884072e31b9b', '2', '0,1,2,', 'CMS管理', 1020, null, null, null, '1', null, '1', to_date('03-06-2016 14:26:36', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:26:36', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('83c20e3945fe46c5adfe4f7a94092c05', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '栏目管理', 30, '/cms/category/', null, null, '1', null, '1', to_date('03-06-2016 14:30:07', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:30:07', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('d70942e3f12c418489819adce22a5370', '83c20e3945fe46c5adfe4f7a94092c05', '0,1,2,3b88d4604c634d679315884072e31b9b,83c20e3945fe46c5adfe4f7a94092c05,', '查看', 30, null, null, null, '0', 'cms:category:view', '1', to_date('03-06-2016 14:30:29', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:31:26', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('dd885cab55514c38b0f189639b37d2b3', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '站点设置', 60, '/cms/site/', null, null, '1', null, '1', to_date('03-06-2016 14:32:48', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:32:48', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('43568ecf18524bdbb65e1323876c1d12', 'dd885cab55514c38b0f189639b37d2b3', '0,1,2,3b88d4604c634d679315884072e31b9b,dd885cab55514c38b0f189639b37d2b3,', '查看', 30, null, null, null, '0', 'cms:site:view', '1', to_date('03-06-2016 14:33:36', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:33:36', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('18a5b621d42549999ccfd5ff18abd299', 'e808215d0baa4e15b6b26108abbbb645', '0,1,2,3b88d4604c634d679315884072e31b9b,e808215d0baa4e15b6b26108abbbb645,', '审核', 90, null, null, null, '0', 'cms:article:audit', '1', to_date('03-06-2016 14:39:48', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:39:48', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('43b861d4a65a41c6acc74ba57b300c6e', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '链接模型', 120, '/cms/link/', null, null, '1', null, '1', to_date('03-06-2016 14:40:58', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:40:58', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('e614fd3468e24b47a8d9eafa9b377f61', '43b861d4a65a41c6acc74ba57b300c6e', '0,1,2,3b88d4604c634d679315884072e31b9b,43b861d4a65a41c6acc74ba57b300c6e,', '查看', 30, null, null, null, '0', 'cms:link:view', '1', to_date('03-06-2016 14:41:24', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:41:24', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('5add398ffeb8460ea89da64686259504', '43b861d4a65a41c6acc74ba57b300c6e', '0,1,2,3b88d4604c634d679315884072e31b9b,43b861d4a65a41c6acc74ba57b300c6e,', '审核', 90, null, null, null, '0', 'cms:link:audit', '1', to_date('03-06-2016 14:44:35', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:44:35', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('7042b8542bb24e418e5169d91ca6dba6', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '评论管理', 150, '/cms/comment/?status=2', null, null, '1', null, '1', to_date('03-06-2016 14:46:06', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:46:06', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('4cca5ba08b4e4ba78a888c25564d271e', '7042b8542bb24e418e5169d91ca6dba6', '0,1,2,3b88d4604c634d679315884072e31b9b,7042b8542bb24e418e5169d91ca6dba6,', '审核', 60, null, null, null, '0', 'cms:comment:edit', '1', to_date('03-06-2016 14:47:53', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:47:53', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('c7238496efa54a218a70e96889af9b2b', '8a33c87337324dfca7bfaaa4250a756b', '0,1,2,3b88d4604c634d679315884072e31b9b,8a33c87337324dfca7bfaaa4250a756b,', '审核', 30, null, null, null, '0', 'cms:guestbook:edit', '1', to_date('03-06-2016 14:49:50', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:51:48', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('d2b37cb6e6784f58afbe2b97cf3627d2', '8a33c87337324dfca7bfaaa4250a756b', '0,1,2,3b88d4604c634d679315884072e31b9b,8a33c87337324dfca7bfaaa4250a756b,', '查看', 60, null, null, null, '0', 'cms:guestbook:view', '1', to_date('03-06-2016 14:52:04', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:52:04', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('bdab5af394884489bfbfdb70a489aad5', 'e808215d0baa4e15b6b26108abbbb645', '0,1,2,3b88d4604c634d679315884072e31b9b,e808215d0baa4e15b6b26108abbbb645,', '查看', 30, null, null, null, '0', 'cms:article:view', '1', to_date('03-06-2016 14:35:32', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:35:32', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('696574b581394e2e9ecca90437a347e0', 'e808215d0baa4e15b6b26108abbbb645', '0,1,2,3b88d4604c634d679315884072e31b9b,e808215d0baa4e15b6b26108abbbb645,', '修改', 60, null, null, null, '0', 'cms:article:edit', '1', to_date('03-06-2016 14:35:59', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:35:59', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('9512b1ae7f434eb1a9d0008b58e62fce', '43b861d4a65a41c6acc74ba57b300c6e', '0,1,2,3b88d4604c634d679315884072e31b9b,43b861d4a65a41c6acc74ba57b300c6e,', '修改', 60, null, null, null, '0', 'cms:link:edit', '1', to_date('03-06-2016 14:42:10', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:42:10', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('216181d6abc5431abf5e817c025d94ec', '7042b8542bb24e418e5169d91ca6dba6', '0,1,2,3b88d4604c634d679315884072e31b9b,7042b8542bb24e418e5169d91ca6dba6,', '查看', 30, null, null, null, '0', 'cms:comment:view', '1', to_date('03-06-2016 14:47:23', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:47:33', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('8a33c87337324dfca7bfaaa4250a756b', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '公共留言', 180, '/cms/guestbook/?status=2', null, null, '1', null, '1', to_date('03-06-2016 14:48:53', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 14:48:53', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into SYS_MENU (id, parent_id, parent_ids, name, sort, href, target, icon, is_show, permission, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('1426281cdc514f75b86deb46c3451027', '3b88d4604c634d679315884072e31b9b', '0,1,2,3b88d4604c634d679315884072e31b9b,', '信息量统计', 210, '/cms/stats/article', null, null, '1', null, '1', to_date('03-06-2016 15:00:01', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('03-06-2016 15:00:01', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
commit;

insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('42', 'basic', '基础主题', 'cms_theme', '站点主题', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('43', 'blue', '蓝色主题', 'cms_theme', '站点主题', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('44', 'red', '红色主题', 'cms_theme', '站点主题', 30, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('45', 'article', '文章模型', 'cms_module', '栏目模型', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('46', 'picture', '图片模型', 'cms_module', '栏目模型', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('47', 'download', '下载模型', 'cms_module', '栏目模型', 30, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('48', 'link', '链接模型', 'cms_module', '栏目模型', 40, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('49', 'special', '专题模型', 'cms_module', '栏目模型', 50, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('50', '0', '默认展现方式', 'cms_show_modes', '展现方式', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('03-06-2016 16:57:01', 'dd-mm-yyyy hh24:mi:ss'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('51', '1', '首栏目内容列表', 'cms_show_modes', '展现方式', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('03-06-2016 16:57:43', 'dd-mm-yyyy hh24:mi:ss'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('52', '2', '栏目第一条内容', 'cms_show_modes', '展现方式', 30, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('03-06-2016 17:02:32', 'dd-mm-yyyy hh24:mi:ss'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('53', '0', '发布', 'cms_del_flag', '内容状态', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('54', '1', '删除', 'cms_del_flag', '内容状态', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('55', '2', '审核', 'cms_del_flag', '内容状态', 15, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('56', '1', '首页焦点图', 'cms_posid', '推荐位', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('57', '2', '栏目页文章推荐', 'cms_posid', '推荐位', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('58', '1', '咨询', 'cms_guestbook', '留言板分类', 10, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('59', '2', '建议', 'cms_guestbook', '留言板分类', 20, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('60', '3', '投诉', 'cms_guestbook', '留言板分类', 30, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
insert into SYS_DICT (id, value, label, type, description, sort, parent_id, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('61', '4', '其它', 'cms_guestbook', '留言板分类', 40, '0', '1', to_date('27-05-2013', 'dd-mm-yyyy'), '1', to_date('27-05-2013', 'dd-mm-yyyy'), '0', '0');
commit;
insert into CMS_SITE (id, name, title, logo, domain, description, keywords, theme, custom_index_view, create_by, create_date, update_by, update_date, remarks, del_flag)
values ('1', '默认站点', 'JeeSite Web', null, 'JeeSite', 'basic', null, 'default', null, null, null, null, null, null, '0');
commit;