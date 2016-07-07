drop table if exists testuser;
drop table if exists upload;

create table testuser (
       userId           char(20) not null,
       name             varchar(20) null,
       age              int(5) default NULL,
       PRIMARY KEY  (userId)
);
INSERT INTO testuser VALUES ('1', 'tony','11');
INSERT INTO testuser VALUES ('2', 'sunny','12');
INSERT INTO testuser VALUES ('3', 'kevin','13');

CREATE TABLE upload (
  objectId            char(50) NOT NULL default '',
  name                varchar(50) default '',
  description         varchar(200) default '',
  datas               LONGBLOB,
  size                int(20) NOT NULL default '0',  
  messageId           varchar(20) NOT NULL default '0',
  creationDate        VARCHAR(25) NOT NULL,
  contentType         varchar(50) default '',
  PRIMARY KEY  (objectId),
);
CREATE INDEX messageId  ON upload(messageId);


