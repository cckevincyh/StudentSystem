package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.bean.Student;
import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.util.Tools;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//选择年级，院系，专业，班级进行成绩分析
public class ScoreAnalyzeOption extends JDialog{
 	private JButton confirm_Button;	//"确认"按钮。
    private JButton cancel_Button;	//"取消"按钮。
    private JComboBox major_Box;	//"专业"选项。
    private JComboBox department_Box;	//"所属院系"选项。
    private JLabel classe_Label;	//"班级"标签。
    private JLabel grade_Label;	//"年级标签"。
    private JLabel major_Label;	//"专业"标签。
    private JLabel department_Label;	//"所属院系"标签。
    private JTextField student_IDText;	//"学号"文本域。
    private JTextField student_NameText;	//"姓名"文本域。
    private JComboBox classe_Box;	//"班级"文本域。
    private JComboBox grade_Box;	//"年级"文本域。
    private JDialog jd;	//当前窗口。
    private HashMap<String, String> departments;	//所有院系集合
    private HashMap<String, String> all_Major;	//所有专业集合
    private Vector<String> majors;	//专业名称集合
    private ManageHelper helper;	//数据库业务处理对象
    private Vector<String> classes;	//班级集合
/**
 * 
 * @param owner 它的父窗口
 * @param title 窗口名
 * @param modal 指定的模式窗口，还有非模式窗口
 */
public ScoreAnalyzeOption(JFrame owner, String title, boolean modal){
	super(owner, title, modal);
	helper = new ManageHelper();	//创建数据库业务处理对象
	departments = this.helper.getAllDepartment();	//获得所有院系
	all_Major = this.helper.getAllMajor();	//获得所有的专业
	this.jd = this;
	this.setSize(350,300);	//设置窗体大小。
	this.setLayout(null);	//设置空布局。
	
	grade_Label = new JLabel("年级:");	
	grade_Label.setBounds(78, 48, 30, 20);
	this.add(grade_Label);
	
	grade_Box = new JComboBox<String>(Tools.CreateGrade());	//需要获得获得年级选项
	grade_Box.setBounds(116, 48, 150, 20);
	//注册"年级选项"事件监听
	grade_Box.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			classe_Box.removeAllItems();//移除"班级选项"的内容
			String option = major_Box.getSelectedItem().toString();
			String major_id = all_Major.get(option);	//专业编号
			String grade = grade_Box.getSelectedItem().toString();
			if(!grade.equals("")){
				 classes = helper.getAllClasse(grade,major_id);	//获得班级
				for(String s : classes){
					classe_Box.addItem(s);
				}
			}
		}
	});
	this.add(grade_Box);
	
		
	department_Label = new JLabel("院系:");
	department_Label.setBounds(78, 88, 30, 20);
	
	this.add(department_Label);
	
	department_Box = new JComboBox(departments.keySet().toArray());//获得键的集合
	//注册"院系"选项事件监听
	department_Box.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			major_Box.removeAllItems();//移除"专业选项"的内容
			String option = department_Box.getSelectedItem().toString();//获得选项名称
			String department_ID = departments.get(option);	//获得院系编号
			if(!department_ID.equals("")){
				majors = helper.getMajor(department_ID);	//获得专业
				for(String s : majors){
					major_Box.addItem(s);
				}
			}
			
		}
	});
	department_Box.setBounds(116, 88, 150, 20);
	this.add(department_Box);

	major_Label = new JLabel("专业:");
	major_Label.setBounds(78, 136, 30, 20);	
	this.add(major_Label);
	
	major_Box = new JComboBox(new String[]{""});//防止空指针异常
	//注册"专业"选项事件监听
	major_Box.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(major_Box.getSelectedItem()!=null){	//防止空指针 
				if(!major_Box.getSelectedItem().toString().equals("")){
					if(major_Box.getSelectedItem().toString().equals("") || grade_Box.getSelectedItem()==null || grade_Box.getSelectedItem().toString().equals("")){
						JOptionPane.showMessageDialog(jd, "年级不能为空", "", JOptionPane.WARNING_MESSAGE);
						major_Box.setSelectedIndex(0);	//设置为空选项
						return ;
					}
					classe_Box.removeAllItems();//移除"班级选项"的内容
					String option = major_Box.getSelectedItem().toString();
					String major_id = all_Major.get(option);	//专业编号
					String grade = grade_Box.getSelectedItem().toString();
					if(!grade.equals("")){
						 classes = helper.getAllClasse(grade,major_id);	//获得班级
						for(String s : classes){
							classe_Box.addItem(s);
						}
					}
				}
			}
		}
	});
	major_Box.setBounds(116, 136, 150, 20);
	this.add(major_Box);
	
	
	
	classe_Label = new JLabel("班级:");	//需要获得班级选项
	classe_Label.setBounds(78, 184, 30, 20);	
	this.add(classe_Label);
	
	classe_Box = new JComboBox(new String[]{""});//防止空指针异常
	classe_Box.setBounds(116, 184, 150, 20);
	
	this.add(classe_Box);
	
	confirm_Button = new JButton("确定");
	//注册"确认"按钮事件监听
	confirm_Button.addActionListener(new ActionListener() {
				
		@Override 
		public void actionPerformed(ActionEvent e) {
			String classe = classe_Box.getSelectedItem().toString();
			String grade = grade_Box.getSelectedItem().toString();
			String major_ID = null;
			String major_Name = null;
			//数据校验部分
			if(grade.equals("")){
				JOptionPane.showMessageDialog(jd, "年级不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(department_Box.getSelectedItem()==null){	//先检查再用
				JOptionPane.showMessageDialog(jd, "院系不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(department_Box.getSelectedItem().toString().equals("")){
				JOptionPane.showMessageDialog(jd, "院系不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(major_Box.getSelectedItem()==null){	//先检查再用
				JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}else{
				major_Name = major_Box.getSelectedItem().toString();//获得专业名称
				major_ID = all_Major.get(major_Name);	//获得专业编号
			}
			if(major_ID.equals("")){
				JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(classe.equals("")){
				JOptionPane.showMessageDialog(jd, "班级不能为空！", "", JOptionPane.WARNING_MESSAGE);
				return ;
			}
				jd.dispose();//关闭当前窗口
				ScoreAnalyzeFrame analyzeFrame = new ScoreAnalyzeFrame(jd, major_Name+"——"+classe+"班的成绩分析", false, major_ID,major_Name, grade,classe);
			
			}
		});
	confirm_Button.setBounds(70, 232, 60, 25);
	
	
	this.add(confirm_Button);
	
	cancel_Button = new JButton("取消");
	cancel_Button.setBounds(230, 232, 60, 25);
	//注册"取消"按钮事件监听
	cancel_Button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			jd.dispose();
			
		}
	});
	this.add(cancel_Button);
	
	WindowUtil.setFrameCenter(this);
	this.setResizable(false);
	this.setVisible(true);
}
}
