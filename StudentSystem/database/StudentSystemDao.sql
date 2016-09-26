create database StudentSystemDao;


use StudentSystemDao;
create table tb_Department( #所属院系表
Department_ID varchar(30) primary key,	#院系编号
Department_Name nvarchar(50) unique not null 	#院系名
);


use StudentSystemDao;
create table tb_Major(  #专业表
Major_ID varchar(30) primary key,	#专业编号
Major_Name nvarchar(50) unique not null,	#专业名称
Department_ID varchar(30) not null,	#所属院系ID
foreign key(Department_ID) references tb_Department(Department_ID)	#所属院系设置为院系表的外键
);





use StudentSystemDao;
create table tb_Classe(		#班级表
Classe int not null,	#班级
Grade int not null,	#年级
Major_ID varchar(30) not null,	#专业编号
primary key(Classe,Grade,Major_ID)	#班级，年级和专业编号共为主键
);




use StudentSystemDao;
create table tb_Student(		#学生表
Student_Id varchar(30) primary key,	#学生学号
Student_Name nvarchar(50) not null,		#学生姓名
Student_Sex nchar(1) default '男' not null check (StudentSex in('男','女')),	#学生性别
Grade int not null,	#年级
Classe int not null,	#班级
Major_ID varchar(30) not null,	#专业编号
Major_Name nvarchar(50)  not null,	#专业名称
Department_ID varchar(30) not null,	#所属院系ID
Department_Name nvarchar(50)  not null,	#院系名称
foreign key(Department_ID) references tb_Department(Department_ID),	#所属院系编号设置为外键
foreign key(Major_ID) references tb_Major(Major_ID),	#专业编号设置为外键
foreign key(Department_Name) references tb_Department(Department_Name),	#所属院系名称设置为外键
foreign key(Major_Name) references tb_Major(Major_Name)	#专业名称设置为外键
);




use StudentSystemDao;
create table tb_User(					#用户表
User_name nvarchar(30) primary key,		#用户名				
Password_ varchar(30) not null,			#密码
IsLogin bit not null DEFAULT 0 	#是否重复登陆

);



use StudentSystemDao;
create table tb_Course(								#课程表
Course_Name nvarchar(50) not null,			#课程名称
Major_ID varchar(30) not null,						#所属专业编号
foreign key(Major_ID) references tb_Major(Major_ID),#专业编号设置为专业表的外键
Grade int not null,	#年级
primary key(Course_Name,Major_ID,Grade)	#课程名称，专业，年级共为主键
);







use StudentSystemDao;
create table tb_Score( 									  #成绩表
Student_Id varchar(30) not null,						  #学生ID
Student_Name nvarchar(50) not null,		#学生姓名
Course_Name varchar(30) not null,							#课程名称
primary key(Course_Name,Student_Id),		  #学生ID和课程名称共同组成主键
foreign key(Student_Id) references tb_Student(Student_Id), #学生ID设置为学生表的外键
foreign key(Course_Name) references tb_Course(Course_Name),
Score dec(4,1) default null  check(Score between 0.0 and 100.0) #成绩在0到100之间
);





/*插入院系*/
insert into tb_Department(Department_ID ,Department_Name) values('01','艺术系');
insert into tb_Department(Department_ID ,Department_Name) values('02','中国语言文学系');
insert into tb_Department(Department_ID ,Department_Name) values('03','电子信息系');
insert into tb_Department(Department_ID ,Department_Name) values('04','计算机科学与技术系');
insert into tb_Department(Department_ID ,Department_Name) values('05','物流与信息管理系');
insert into tb_Department(Department_ID ,Department_Name) values('06','旅游管理系');
insert into tb_Department(Department_ID ,Department_Name) values('07','机械与汽车工程系');
insert into tb_Department(Department_ID ,Department_Name) values('08','国际贸易与金融系');
insert into tb_Department(Department_ID ,Department_Name) values('09','工商管理系');
insert into tb_Department(Department_ID ,Department_Name) values('10','公共管理系');
insert into tb_Department(Department_ID ,Department_Name) values('11','建筑学系');
insert into tb_Department(Department_ID ,Department_Name) values('12','化学与药学系');
insert into tb_Department(Department_ID ,Department_Name) values('13','外语系');
insert into tb_Department(Department_ID ,Department_Name) values('14','音乐舞蹈学院');



/*插入专业*/
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('01','视觉传达设计专业','01');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('02','动画专业','01');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('03','汉语言文学专业','02');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('04','广告学专业','02');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('05','汉语国际教育专业','02');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('06','电子信息科学与技术专业','03');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('07','微电子科学与工程专业','03');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('08','自动化专业','03');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('09','通信工程专业','03');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('10','测控技术与仪器专业','03');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('11','计算机科学与技术专业','04');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('12','软件工程专业','04');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('13','网络工程专业','04');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('14','物流管理专业','05');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('15','信息管理与信息系统专业','05');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('16','电子商务专业','05');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('17','旅游管理专业','06');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('18','机械设计制造及其自动化专业','07');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('19','汽车服务工程专业','07');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('20','车辆工程专业','07');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('21','工业工程专业','07');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('22','国际经济与贸易专业','08');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('23','金融学专业','08');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('24','工商管理专业','09');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('25','市场营销专业','09');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('26','人力资源管理专业','09');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('27','会计学专业','09');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('28','行政管理专业','10');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('29','劳动与社会保障专业','10');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('30','社会工作专业','10');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('31','建筑学专业','11');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('32','城乡规划专业','11');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('33','应用化学专业','12');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('34','制药工程(生物制药)专业','12');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('35','药物制剂专业','12');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('36','中药学专业','12');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('37','化学工程与工艺专业','12');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('38','英语专业','13');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('39','日语专业','13');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('40','朝鲜语（韩国语）专业','13');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('41','音乐表演专业','14');
insert into tb_Major(Major_ID,Major_Name,Department_ID) values('42','舞蹈编导专业','14');



/*添加班级*/
insert into tb_Classe(Classe,Grade,Major_ID) values(1,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(2,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(3,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(4,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(5,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(6,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(7,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(8,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(9,2014,'11');
insert into tb_Classe(Classe,Grade,Major_ID) values(1,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(2,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(3,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(4,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(5,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(6,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(7,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(8,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(9,2014,'12');
insert into tb_Classe(Classe,Grade,Major_ID) values(1,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(2,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(3,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(4,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(5,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(6,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(7,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(8,2014,'13');
insert into tb_Classe(Classe,Grade,Major_ID) values(9,2014,'13');



/*添加课程*/
insert into tb_Course(Course_Name,Major_ID,Grade) values ('高等数学','11',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('英语','11',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('数据结构','11',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('计算机组成原理','11',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('面向对象程序设计','11',2014);

insert into tb_Course(Course_Name,Major_ID,Grade) values ('高等数学','12',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('英语','12',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('数据结构','12',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('软件工程导论','12',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('面向对象程序设计','12',2014);

insert into tb_Course(Course_Name,Major_ID,Grade) values ('高等数学','13',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('英语','13',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('数据结构','13',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('计算机网络','13',2014);
insert into tb_Course(Course_Name,Major_ID,Grade) values ('面向对象程序设计','13',2014);
