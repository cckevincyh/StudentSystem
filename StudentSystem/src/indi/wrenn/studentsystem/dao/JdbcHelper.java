package indi.wrenn.studentsystem.dao;

import indi.wrenn.studentsystem.bean.AnalyzeResult;
import indi.wrenn.studentsystem.bean.Student;
import indi.wrenn.studentsystem.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//与数据库通信的类
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class JdbcHelper implements JdbcConfig{
	//定义连接数据库所需要的对象
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection ct = null;
	

	//获得数据库的连接
	private  void init(){
		try {
			Class.forName(DRIVER);
			ct = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获得数据库连接
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//无参构造函数
	public JdbcHelper(){
		this.init();
	}
	

	/**
	 * 获取用户对象
	 * 根据传入的用户名，获取对应的用户，并返回用户对象
	 * @return 用户对象
	 */
	public User getUser(User user){
		User newUser = new User();
		try {
			ps = ct.prepareStatement("select * from tb_User where User_name=?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if(rs.next()){
				newUser.setUsername(rs.getString(1));	//设置用户名
				newUser.setPassword(rs.getString(2));	//设置密码
				newUser.setIsLogin(rs.getInt(3));	//设置是否登陆
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return newUser;
	}
	
	/**
	 * 注册处理
	 * @param user	用户对象
	 * @return	返回是否注册成功
	 */
	public boolean register(User user){
		boolean b = true;
		try {
			ps = ct.prepareStatement("insert into tb_User(User_name,Password_) values(?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			if(ps.executeUpdate()!=1){	//执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 修改用户"是否"登陆状态
	 * @param user
	 * @return
	 */
	public boolean update_IsLogin(User user){
		boolean b = true;
		try {
			ps = ct.prepareStatement("update tb_User set IsLogin=? where User_name=?");
			ps.setInt(1, user.getIsLogin());
			ps.setString(2, user.getUsername());
			if(ps.executeUpdate()!=1){
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	
	
	
	
		/**
		 * 修改密码
		 * @param user  用户对象
		 * @param new_Password 新密码
		 * @return	返回是否修改成功
		 */
		public boolean update_Password(User user,String new_Password){
			boolean b = true;
			try {
				ps = ct.prepareStatement("update tb_User set Password_=? where User_name=?");
				ps.setString(1, new_Password);
				ps.setString(2, user.getUsername());
				if(ps.executeUpdate()!=1){	//执行sql语句
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		
		
		/**
		 * 获得所有院系
		 * @return 返回所有院系的HashMap集合
		 */
		public HashMap<String, String> getAllDepartment(){
			HashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("", "");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Department order by Department_ID");
				rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString(2),rs.getString(1)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map;
		}
	
		
		/**
		 * 获得对应院系的专业
		 * @return 返回Vector<String>对象
		 */
		public Vector<String> getMajor(String department_ID){
			Vector<String> vector = new Vector<String>();
			vector.add("");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Major where Department_ID=? order by Major_ID");
				ps.setString(1, department_ID);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(2));	//获得专业名称
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vector;
		}
		
		/**
		 * 获得所有专业 
		 * @return 返回所有专业
		 */
		public HashMap<String, String>getAllMajor(){
			HashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("", "");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Major order by Major_ID");
				rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString(2),rs.getString(1)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map;
		}
		
		/**
		 * 添加学生
		 * @param student	学生对象
		 * @return	返回是否添加成功
		 */
		public boolean addStudent(Student student){
			boolean b = true;
			try {
				ps = ct.prepareStatement("insert into tb_Student(Student_Id,Student_Name,Student_Sex,Classe,Grade,Major_ID,Department_ID,Major_Name,Department_Name) values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, student.getStudent_ID());
				ps.setString(2, student.getStudent_Name());
				ps.setString(3, student.getSex());
				ps.setString(4, student.getClasse());
				ps.setString(5, student.getGrade());
				ps.setString(6, student.getMajor_ID());
				ps.setString(7, student.getDepartment_ID());
				ps.setString(8, student.getMajor_Name());
				ps.setString(9, student.getDepartment_Name());
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		
		
		/**
		 * 修改学生信息
		 * @param newStudent	新学生对象
		 * @param oldStudentID	旧学生信息
		 * @return	是否修改成功
		 */
		public boolean updateStudent(Student newStudent,String oldStudentID){
			boolean b = true;
			try {
				//update
				ps = ct.prepareStatement("update tb_Student set Student_Id=?, Student_Name=?, Student_Sex=? ,Classe=? ,Grade=?  ,Major_ID=? ,Department_ID=? ,Major_Name=? ,Department_Name=? where Student_Id=?");
				ps.setString(1, newStudent.getStudent_ID());
				ps.setString(2, newStudent.getStudent_Name());
				ps.setString(3, newStudent.getSex());
				ps.setString(4, newStudent.getClasse());
				ps.setString(5, newStudent.getGrade());
				ps.setString(6, newStudent.getMajor_ID());
				ps.setString(7, newStudent.getDepartment_ID());
				ps.setString(8, newStudent.getMajor_Name());
				ps.setString(9, newStudent.getDepartment_Name());
				ps.setString(10, oldStudentID);
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		
		/**
		 * 根据学生学号从数据库移除该学生
		 * @param studentID	学生学号
		 * @return	返回是否删除成功
		 */
		public boolean deleteStudent(String studentID){
			boolean b = true;
			try {
				ps = ct.prepareStatement("delete from tb_Student where Student_Id=?");
				ps.setString(1, studentID);
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		

		/**
		 * 根据sql语句返回特定的学生集合
		 * @param sql	sql语句
		 * @return	返回Vector<Student>对象
		 */
		public Vector<Student> getStudent(String sql){
			Vector<Student> students = new Vector<Student>();
			try {
				ps = ct.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					Student student = new Student();
					student.setStudent_ID(rs.getString(1));
					student.setStudent_Name(rs.getString(2));
					student.setSex(rs.getString(3));
					student.setGrade(rs.getString(4));
					student.setClasse(rs.getString(5));
					student.setMajor_ID(rs.getString(6));
					student.setMajor_Name(rs.getString(7));
					student.setDepartment_ID(rs.getString(8));
					student.setDepartment_Name(rs.getString(9));
					students.add(student);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return students;
		}
		
		/**
		 * 根据学生(年级和编号)返回对应的班级
		 * @param student
		 * @return
		 */
		public Vector<String> getAllClasse(String grade,String major_ID){
			Vector<String> vector = new Vector<String>();
			vector.add("");	//添加一个空选项
			try {
				ps = ct.prepareStatement("select Classe from tb_Classe where Grade=? and Major_ID=?");
				ps.setString(1, grade);
				ps.setString(2, major_ID);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vector;
		}
		
		
		
		
		
		
		/**
		 *  根据专业编号返回对应的所有课程
		 * @param major_Id
		 * @return 返回课程集合
		 */
		public Vector<String> getCourse(String major_Id,String grade){
			Vector<String> vector = new Vector<String>();
			try {
				ps = ct.prepareStatement("select Course_Name from tb_Course where Major_ID=? and Grade=?");
				ps.setString(1, major_Id);
				ps.setString(2, grade);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vector;
		}
		
		
		/**
		 * 添加学生成绩
		 * @param student 学生对象
		 * @return 返回是否添加成功
		 */
		public boolean addStudentScore(Student student,Vector<String> courses){
			boolean b = true;
			HashMap<String, String> map = student.getScores();
			try {
				int i;
				for(i=0;i<map.size();i++){
					ps = ct.prepareStatement("insert into tb_Score(Course_Name,Student_Id,Student_Name,Score) values (?,?,?,?)");
					ps.setString(1, courses.get(i));
					ps.setString(2, student.getStudent_ID());
					ps.setString(3, student.getStudent_Name());
					ps.setString(4, map.get(courses.get(i)));
					if(ps.executeUpdate()!=1){
						break;
					}
				}
				if(i<map.size()){
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		
		/**
		 * 修改学生成绩
		 * @param student_Id	学生学号
		 * @param course_Name	课程名字
		 * @param score 成绩
		 * @return	返回是否修改成功
		 */
		public boolean updateStudentScore(String student_Id,String course_Name,String score){
			boolean b = true;
			try {
				ps = ct.prepareStatement("update tb_Score set Score=? where Course_Name=? and Student_Id=? ");
				ps.setString(1, score);
				ps.setString(2, course_Name);
				ps.setString(3, student_Id);
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		
		
		/**
		 * 查询学生所有成绩
		 * @param student_Id	学生学号
		 * @return	返回成绩集合
		 */
		public HashMap<String, String> getStudentScore(String student_Id){
			HashMap<String, String> scores = new HashMap<String, String>();
			try {
				ps = ct.prepareStatement("select Course_Name,Score from tb_Score where Student_Id=? order by Student_Id asc");
				ps.setString(1, student_Id);
				rs = ps.executeQuery();
				while(rs.next()){
					scores.put(rs.getString(1), rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return scores;
		}
		
//		/**
//		 * 根据学号和课程名查询指定的成绩
//		 * @param student_Id 学号
//		 * @param course_Name	课程名
//		 * @return	返回成绩
//		 */
//		public String getStudentScore(String student_Id,String course_Name){
//			String score = null;
//			try {
//				ps = ct.prepareStatement("select Score from tb_Score where Student_Id=? and Course_Name=?");
//				ps.setString(1, student_Id);
//				ps.setString(2, course_Name);
//				rs = ps.executeQuery();
//				rs.next();
//				score = rs.getString(1);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return score;
//		}
//	
//		
		
		/**
		 * 根据特定的sql语句返回学生集合
		 * @param sql
		 * @return
		 */
		public Vector<Student> getScores(String sql){
			Vector<Student> students = new Vector<Student>();
			try {
				ps = ct.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					Student student = new Student();
					HashMap<String, String>scores = new HashMap<String, String>();
					scores.put(rs.getString(3), rs.getString(4));
					student.setScores(scores);
					student.setStudent_ID(rs.getString(1));
					student.setStudent_Name(rs.getString(2));
					students.add(student);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			return students;
		}
		
		
		/**
		 * 获得班级总成绩
		 * @param classe	班级
		 * @param grade		年级
		 * @param major		专业
		 * @return 总成绩
		 */
		public String getClasse_SumScore(String classe,String grade,String major){
			String sum = null;
			try {
				ps = ct.prepareStatement("select SUM(Score) from tb_Score where Student_Id in(Select Student_Id from tb_Student where Classe=? and Grade=? and Major_Name=?)");
				ps.setString(1, classe);
				ps.setString(2,grade);
				ps.setString(3, major);
				rs = ps.executeQuery();
				if(rs.next()){
					sum = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sum;
		}
		
		
		
		
		
		
		
		/**
		 * 根据学生id返回该学生的平均成绩
		 * @param student_Id
		 * @return
		 */
		public String getStudentAvgScore(String student_Id){
			String avg = null;
			try {
				ps = ct.prepareStatement("select  AVG(Score) from tb_Score where Student_Id=? ");
				ps.setString(1, student_Id);
				rs = ps.executeQuery();
				if(rs.next()){
					avg = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return avg;
		}
		
		/**
		 * 根据学生专业，年级，班级，科目，考试成绩类型(优，良，中，及格，不及格)返回该类型的学生人数
		 * @param grade
		 * @param classe
		 * @param major
		 * @param type
		 * @param course_Name
		 * @return
		 */
		public String getCount_ScoreType(String grade,String classe,String major,String type,String course_Name){
			String count = null;
			String sql = null;
			if(type.equals("优")){
				sql = "select  Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? and Score>=90 and Score<=100";
			}else if(type.equals("良")){
				sql = sql = "select  Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? and Score>=80 and Score<90";
			}else if(type.equals("中")){
				sql = sql = "select  Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? and Score>=70 and Score<80";
			}else if(type.equals("及格")){
				sql = sql = "select  Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? and Score>=60 and Score<70";
			}else if(type.equals("不及格")){
				sql = sql = "select  Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? and Score>=0 and Score<60";
			}
			try {
				ps = ct.prepareStatement(sql);
				ps.setString(1, grade);
				ps.setString(2, classe);
				ps.setString(3, major);
				ps.setString(4, course_Name);
				rs = ps.executeQuery();
				if(rs.next()){
					count = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return count+"人";
		}
		
		/**
		 * 获得课程的平均分
		 * @param course 课程名
		 * @param major	专业
		 * @return	平均分
		 */
		public String getCourseAvg(String course,String major,String classe,String grade){
			String avg = null;
			try {
				ps = ct.prepareStatement("select  AVG(Score) from tb_Score  where Course_Name=? and  Student_Id in (select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) ");
				ps.setString(1, course);
				ps.setString(2, grade);
				ps.setString(3,classe);
				ps.setString(4, major);
				rs = ps.executeQuery();
				if(rs.next()){
					avg = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return avg;
		}
		
		
		/**
		 * 获得单科成绩的最高分
		 * @param course
		 * @param major
		 * @param classe
		 * @param grade
		 * @return
		 */
		public String getCourseHighestScore(String course,String major,String classe,String grade){
			String highest = null;
			try {
				ps = ct.prepareStatement("select MAX(Score) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? " );
				ps.setString(1, grade);
				ps.setString(2, classe);
				ps.setString(3, major);
				ps.setString(4, course);
				rs = ps.executeQuery();
				if(rs.next()){
					highest = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return highest;
		}
		
		
		/**
		 * 获得单科成绩的最低分
		 * @param course
		 * @param major
		 * @param classe
		 * @param grade
		 * @return
		 */
		public String getCourseLowestScore(String course,String major,String classe,String grade){
			String lowest = null;
			try {
				ps = ct.prepareStatement("select MIN(Score) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) and Course_Name=? " );
				ps.setString(1, grade);
				ps.setString(2, classe);
				ps.setString(3, major);
				ps.setString(4, course);
				rs = ps.executeQuery();
				if(rs.next()){
					lowest = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lowest;
		}
		
		
		/**
		 * 获得该班级有成绩的人数
		 * @param major
		 * @param classe
		 * @param grade
		 * @return
		 */
		public String getHaveScoreCount(String major,String classe,String grade){
			String count = null;
			try {
				ps = ct.prepareStatement("select Count(*) from tb_Score where Student_Id in(select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=?) GROUP BY  Course_Name");
				ps.setString(1, grade);
				ps.setString(2, classe);
				ps.setString(3, major);
				rs = ps.executeQuery();
				if(rs.next()){
					count = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		
		/**
		 * 获得成绩分析的结果
		 * @param major	专业
		 * @param classe	班级
		 * @param grade	年级
		 * @return	返回分析集合
		 */
		public Vector<AnalyzeResult> analyzeScore(String major,String classe,String grade ){
			Vector<AnalyzeResult> vector = new Vector<AnalyzeResult>();
			try {
				ps = ct.prepareStatement("select  Student_Id,Student_Name,SUM(Score),Avg(Score) as score from tb_Score where Student_Id in (Select Student_Id from tb_Student where Grade=? and Classe=? and Major_Name=? )GROUP BY Student_Id order by score desc");
				ps.setString(1, grade);
				ps.setString(2, classe);
				ps.setString(3, major);
				rs = ps.executeQuery();
				while(rs.next()){
					AnalyzeResult result = new AnalyzeResult();
					result.setStudentId(rs.getString(1));
					result.setStudentName(rs.getString(2));
					result.setSumScore(rs.getString(3));
					result.setAvgScore(rs.getString(4));
					vector.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vector;
		}
		
		
		//关闭数据库资源
		public void close()	{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
}
