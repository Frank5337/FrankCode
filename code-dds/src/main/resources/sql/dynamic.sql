/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26-log : Database - code-shiro
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `test`;

/*Table structure for table `s_user` */

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `salt` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '盐',
  `perms` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限',
  `json` json DEFAULT NULL,
  `is_system` bit(1) NOT NULL DEFAULT b'0',
  `dateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_name` (`name`,`pwd`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='user';

/*Data for the table `s_user` */

insert  into `s_user`(`id`,`name`,`pwd`,`salt`,`perms`,`json`,`is_system`,`dateTime`,`timeStamp`) values (1,'root','1','xxx','user:add',NULL,'','2020-08-05 17:15:17','2020-08-05 17:17:04'),(2,'zbl','1','qwqw','user:update','{\"fileName\": \"客户端安装-视频录制.pptx\", \"secDomainName\": \"研发中心\", \"secDomainIndex\": \"1\"}','\0','2020-08-05 17:16:59','2020-08-05 17:17:06'),(3,'test','1','FsAiEJA4RytmpH1JlMeXd5xyA6AeIxMg','user:update',NULL,'\0','2020-08-05 17:17:21','2020-08-05 17:17:09');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
