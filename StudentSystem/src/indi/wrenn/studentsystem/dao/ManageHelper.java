package indi.wrenn.studentsystem.dao;

import indi.wrenn.studentsystem.bean.AnalyzeResult;
import indi.wrenn.studentsystem.bean.Student;
import indi.wrenn.studentsystem.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;


//数据库业务处理类
public class ManageHelper {
	private JdbcHelper helper;	//与数据库通信的对象
	
	
	
	/**
	 * 登陆业务处理
	 * @param user	用户对象
	 * @return 返回是否成功登陆
	 */
	public boolean Login(User user){
		boolean b = true;
		helper = new JdbcHelper();	//创建与数据库通信的对象
		User newUser = helper.getUser(user);	//获得用户数据
		if(!user.getPassword().equals(newUser.getPassword())){	//比对密码与数据库中的对应密码是否一致
			b = false;
		}
		helper.close();//关闭资源
		return b;
	}
	
	
	
	/**
	 * 注册业务处理
	 * @param user 用户对象
	 * @return	返回是否注册成功
	 */
	public boolean Register(User user){
		helper = new JdbcHelper();//创建与数据库通信的对象
		boolean b = helper.register(user);
		helper.close();//关闭资源
		return b;
	}
	
	
	
	/**
	 * 检查是否重复登陆的方法
	 * @param user 用户对象
	 * @return 是否重复登陆,登陆过的返回true,否则返回false
	 */
	public boolean Check_IsLogin(User user){
		boolean b = true;
		int isLogin;
		helper = new JdbcHelper();//创建与数据库通信的对象
		User newUser = helper.getUser(user);
		if(newUser.getIsLogin()==0){
			b = false;
		}
		helper.close();//关闭资源
		return b;
	}
	
	
	
	/**
	 * 返回成功修改登陆情况
	 * @param user 用户对象
	 */
	public boolean  Update_IsLogin(User user){
		helper = new JdbcHelper();//创建与数据库通信的对象
		boolean b = helper.update_IsLogin(user);
		helper.close();//关闭资源
		return b;
	}
	
	
	
	/**
	 * 修改密码业务处理
	 * @param user	用户对象
	 * @param new_Password	新密码
	 * @return 返回是否修改成功
	 */
	public boolean update_Password(User user,String new_Password){
		boolean b;
		helper = new JdbcHelper();//创建与数据库通信的对象
		b = helper.update_Password(user, new_Password);
		helper.close();//关闭资源
		return b;
	}
	
	
	
	/**
	 * 获得所有院系
	 * @return	返回院系HashMap集合
	 */	
	public HashMap<String, String>getAllDepartment(){
		helper = new JdbcHelper();
		HashMap<String, String> map;
		map = helper.getAllDepartment();
		helper.close();//关闭资源
		return map;
	}
	
	
	
	/**
	 * 获得所有专业
	 * @return	返回所有专业集合
	 */
	public HashMap<String, String>getAllMajor(){
		helper = new JdbcHelper();
		HashMap<String, String> map;
		map = helper.getAllMajor();
		helper.close();//关闭资源
		return map;
	}
	
	
	
	/**
	 * 根据department_ID返回对应的专业
	 * @param department_ID	院系编号
	 * @return 返回对应的专业集合
	 */
	public Vector<String> getMajor(String department_ID){
		helper = new JdbcHelper();
		Vector<String> vector;
		vector = helper.getMajor(department_ID);
		helper.close();
		return vector;
	}
	
	
	
	/**
	 * 添加学生业务
	 * @param student 学生对象
	 * @return 返回是否添加成功
	 */
	public boolean addStudent(Student student){
		boolean b = true;
		helper = new JdbcHelper();
		b = helper.addStudent(student);
		helper.close();//关闭资源
		return b;
	}
	
	
	/**
	 * 修改学生业务
	 * @param newStudent	新学生对象
	 * @param oldStudentID	旧学生信息
	 * @return	返回是否添加成功
	 */
	public boolean updateStudent(Student newStudent,String oldStudentID){
		boolean b = true;
		helper = new JdbcHelper();
		b = helper.updateStudent(newStudent, oldStudentID);
		helper.close();//关闭资源
		return b;
	}
	
	
	/**
	 * 删除学生业务
	 * @param studentID	学生学号
	 * @return	返回是否删除成功
	 */
	public boolean deleteStudent(String studentID){
		boolean b = true;
		helper = new JdbcHelper();
		b = helper.deleteStudent(studentID);
		helper.close();
		return b;
	}
	
	
	/**
	 * 根据sql语句返回特定的学生对象集合
	 * @param sql	sql语句
	 * @return	返回学生集合
	 */
	public Vector<Student> getStudent(String sql){
		Vector<Student> students;
		helper = new JdbcHelper();
		students = helper.getStudent(sql);
		helper.close();
		return students;
	}
	
	
	/**
	 * 根据年级和专业编号编号返回对应的班级
	 * @param 
	 * @return
	 */
	public Vector<String> getAllClasse(String grade,String major_ID){
		Vector<String> vector;
		helper = new JdbcHelper();
		vector = helper.getAllClasse(grade,major_ID);
		helper.close();
		return vector;
		
	}
	
	
	
	
	/**
	 *  根据专业编号返回对应的所有课程
	 * @param major_Id
	 * @return 返回课程集合
	 */
	public Vector<String> getCourse(String major_Id,String grade){
		Vector<String> vector;
		helper = new JdbcHelper();
		vector = helper.getCourse(major_Id,grade);
		helper.close();
		return vector;
	}
	
	
	/**
	 * 添加学生成绩
	 */
	public boolean addStudentScore(Student student,Vector<String> courses){
		boolean b;
		helper = new JdbcHelper();
		b = helper.addStudentScore(student,courses);
		helper.close();
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
		boolean b;
		helper = new JdbcHelper();
		b = helper.updateStudentScore(student_Id, course_Name, score);
		helper.close();
		return b;
	}
	
	/**
	 * 查询学生成绩
	 * @param student_Id	学生学号
	 * @param course_Name	课程名称
	 * @return	返回成绩集合
	 */
	public HashMap<String, String> getStudentScore(String student_Id){
		HashMap<String, String> map;
		helper = new  JdbcHelper();
		map = helper.getStudentScore(student_Id);
		helper.close();
		return map;
		
	}
	
//	/**
//	 * 根据学号和课程名查询指定的成绩
//	 * @param student_Id 学号
//	 * @param course_Name	课程名
//	 * @return	返回成绩
//	 */
//	public String getStudentScore(String student_Id,String course_Name){
//		String score = null;
//		helper = new JdbcHelper();
//		score = helper.getStudentScore(student_Id, course_Name);
//		helper.close();
//		return score;
//	}
//	

	

	
	/**
	 * 根据特定的sql语句返回学生集合
	 * @param sql
	 * @return
	 */
	public Vector<Student> getScores(String sql){
		Vector<Student> students;
		helper = new JdbcHelper();
		students = helper.getScores(sql);
		helper.close();
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
		helper = new JdbcHelper();
		sum = helper.getClasse_SumScore(classe, grade, major);
		helper.close();
		return sum;
	}
	
	
	/**
	 * 根据学生id返回该学生的平均成绩
	 * @param student_Id
	 * @return
	 */
	public String getStudentAvgScore(String student_Id){
		String avg = null;
		helper = new JdbcHelper();
		avg = helper.getStudentAvgScore(student_Id);
		helper.close();
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
		helper = new JdbcHelper();
		count = helper.getCount_ScoreType(grade, classe, major, type, course_Name);
		helper.close();
		return count;
	}
	
	/**
	 * 获得课程的平均分
	 * @param course 课程名
	 * @param major	专业
	 * @return	平均分
	 */
	public String getCourseAvg(String course,String major,String classe,String grade){
		String avg = null;
		helper = new JdbcHelper();
		avg = helper.getCourseAvg(course, major, classe, grade);
		helper.close();
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
		helper = new JdbcHelper();
		highest = helper.getCourseHighestScore(course, major, classe, grade);
		helper.close();
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
		helper = new JdbcHelper();
		lowest = helper.getCourseLowestScore(course, major, classe, grade);
		helper.close();
		return lowest;
	}
	
	/**
	 * 获得班级的平均成绩
	 * @param classe
	 * @param grade
	 * @param major
	 * @return
	 */
	public String getClasseAvg(String classe,String grade,String major){
		String sum = getClasse_SumScore(classe,grade,major);
		helper = new JdbcHelper();
		String num = helper.getHaveScoreCount(major, classe, grade);
		if(sum!=null && num!=null){
			return String.valueOf((Double.parseDouble(sum)/Double.parseDouble(num)));
		}else{
			return "";
		}
	}
	

	/**
	 * 获得成绩分析的结果
	 * @param major	专业
	 * @param classe	班级
	 * @param grade	年级
	 * @return	返回分析集合
	 */
	public Vector<AnalyzeResult> analyzeScore(String major,String classe,String grade ){
		Vector<AnalyzeResult> vector ;
		helper = new JdbcHelper();
		vector = helper.analyzeScore(major, classe, grade);
		helper.close();
		return vector;
	}
	
}
