package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.bean.User;
import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
//主界面
public class StudentSystemMainFrame extends JFrame {
	private JMenuBar menuBar;	//应用菜单条。
	private JMenu student_Management;	//"学生管理"菜单。
	private JMenu score_Management;	//"成绩管理"菜单。
	private JMenu personal_Management;	//"个人管理"菜单。
	private JMenuItem add_Student;	//"添加学生"菜单项。
	private JMenuItem query_Student;	//"查询学生信息"菜单项。
	private JMenuItem modify_Student;	//"修改学生信息"菜单项。
	private JMenuItem delete_Student;	//"删除学生"菜单项。
	private JMenuItem add_Score;	//"添加学生成绩"菜单项。
	private JMenuItem modify_Score;	//"修改学生成绩"菜单项。
	private JMenuItem query_Score;	//"成绩查询"菜单项。
	private JMenuItem score_Statistics;	//"成绩统计"菜单项。
	private JMenuItem change_Password;	//"修改密码"菜单项。
	private JMenuItem logout;	//"退出登录"菜单项。
	private JFrame jf;	//当前窗口。
	private User user;//当前用户。
	public StudentSystemMainFrame(User user){
		super("学生成绩管理系统,欢迎你"+user.getUsername());
		this.user = user;
		this.jf = this;
		menuBar = new JMenuBar();	//创建菜单条。
		this.setJMenuBar(menuBar);	//添加菜单条。
		
		student_Management = new JMenu("学生管理");	//创建"学生管理"菜单。
		menuBar.add(student_Management);	//添加"学生管理"菜单。
		
		add_Student = new JMenuItem("添加学生");	//创建"添加学生"菜单项。
		//注册"添加学生"菜单项事件监听
		add_Student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStudentFrame addStudentFrame = new AddStudentFrame(jf,"添加学生",true);
			}
		});
		student_Management.add(add_Student);	//添加"添加学生"菜单项。
		
		query_Student = new JMenuItem("查询学生信息");	//创建"查询学生"菜单项。
		//注册"查询学生"菜单项事件监听。
		query_Student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryStudentFrame queryStudentFrame = new QueryStudentFrame(jf, "查询学生信息", true);
				
			}
		});
		student_Management.add(query_Student);	//添加"查询学生信息"菜单项。
		
		modify_Student = new JMenuItem("修改学生信息");	//创建"修改学生信息"菜单项。
		//注册"修改学生信息"菜单项事件监听
		modify_Student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyStudentFrame modifyStudentFrame = new ModifyStudentFrame(jf, "修改学生信息", true);
				
			}
		});
		student_Management.add(modify_Student);	//添加"修改学生"菜单项。
		
		delete_Student = new JMenuItem("删除学生");	//创建"删除学生"菜单项。
		//注册"删除学生"按钮事件监听
		delete_Student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteStudentFrame deleteStudentFrame = new DeleteStudentFrame(jf, "删除学生", true);
				
			}
		});
		student_Management.add(delete_Student);	//添加"删除学生"菜单项.
		
		score_Management = new JMenu("成绩管理");	//创建"成绩管理"菜单。
		menuBar.add(score_Management);	//添加"成绩管理"菜单。
		
		add_Score = new JMenuItem("添加学生成绩");	//创建"添加学生成绩"菜单项。
		//注册"添加学生成绩"菜单项事件监听
		add_Score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddStudentScoreFrame frame = new AddStudentScoreFrame(jf, "添加学生成绩", true);
				
			}
		});
		score_Management.add(add_Score);	//添加"添加学生成绩"菜单项。
		
		modify_Score = new JMenuItem("修改学生成绩");	//创建"修改学生成绩"菜单项。
		//注册"修改学生成绩"菜单项事件监听
		modify_Score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateStudentScoreFrame frame = new UpdateStudentScoreFrame(jf, "修改学生成绩", true);
				
			}
		});
		
		score_Management.add(modify_Score);	//添加"修改学生成绩"菜单项。
		
		query_Score = new JMenuItem("成绩查询");	//创建"成绩查询"菜单项。
		//注册"成绩查询"菜单项事件监听
		query_Score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				QueryStudentScoreFrame frame = new  QueryStudentScoreFrame(jf, "查询学生成绩", true);
			}
		});
		score_Management.add(query_Score);	//添加"成绩查询"菜单项。
		
		score_Statistics = new JMenuItem("成绩统计");	//创建"成绩统计"菜单项。
		score_Statistics.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ScoreAnalyzeOption analyzeOption = new ScoreAnalyzeOption(jf, "成绩分析选项", true);
			}
		});
		score_Management.add(score_Statistics);	//添加"成绩统计"菜单项。
		
		personal_Management = new JMenu("个人管理");	//创建"个人管理"菜单。
		menuBar.add(personal_Management);	//添加"个人管理"菜单。
		
		change_Password = new JMenuItem("修改密码");	//创建"修改密码"菜单项。
		//注册"修改密码"菜单项事件监听
		change_Password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordFrame frame = new UpdatePasswordFrame(jf, "修改密码", true,user);
			}
		});
		personal_Management.add(change_Password);	//添加"修改密码"菜单项。
		
		logout = new JMenuItem("退出登录");	//创建"退出登录"菜单项。
		//注册"退出登录"菜单项时间监听
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				jf.dispose();	//关闭当前窗口
				//修改登陆状态
				ManageHelper helper = new ManageHelper();
				user.setIsLogin(0);//设置登陆状态为未登录。
				helper.Update_IsLogin(user);
				StudentSystemLoginFrame frame = new StudentSystemLoginFrame();	//打开登陆界面
				
			}
		});
		personal_Management.add(logout);	//添加"退出登录"菜单项。
		
		this.setSize(578, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		WindowUtil.setFrameCenter(this);//设置窗体居中。
		ImagePanel imagePanel = new ImagePanel();
		setContentPane(imagePanel);
		
		try {
			Image img = ImageIO.read(this.getClass().getResource("/2.png"));
			this.setIconImage(img);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	     
		this.setVisible(true);//设置窗体可见。
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//修改登陆状态
				ManageHelper helper = new ManageHelper();
				user.setIsLogin(0);//设置登陆状态为未登录。
				helper.Update_IsLogin(user);
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
}
