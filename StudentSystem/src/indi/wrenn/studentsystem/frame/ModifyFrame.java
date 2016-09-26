package indi.wrenn.studentsystem.frame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import indi.wrenn.studentsystem.bean.Student;
import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.model.StudentModel;
import indi.wrenn.studentsystem.util.Tools;
import indi.wrenn.studentsystem.util.WindowUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//修改学生信息界面
public class ModifyFrame extends JDialog{
 	private JButton modify_Button;	//"修改"按钮。
    private JButton cancel_Button;	//"取消"按钮。
    private JComboBox sex_Box;	//"性别"选项。
    private JComboBox major_Box;	//"专业"选项。
    private JComboBox department_Box;	//"所属院系"选项。
    private JLabel student_ID; //"学号"标签。
    private JLabel student_Name;	//"姓名"标签。
    private JLabel sex_Label;	//"性别"标签。
    private JLabel classe_Label;	//"班级"标签。
    private JLabel grade_Label;	//"年级标签"。
    private JLabel major_Label;	//"专业"标签。
    private JLabel department_Label;	//"所属院系"标签。
    private JTextField student_IDText;	//"学号"文本域。
    private JTextField student_NameText;	//"姓名"文本域。
    private JComboBox classe_Box;	//"班级"选项。
    private JComboBox grade_Box;	//"年级"选项。
    private JDialog jd;	//当前窗口。
    private HashMap<String, String> departments;	//所有院系集合
    private HashMap<String, String> all_Major;	//所有专业集合
    private Vector<String> majors;	//专业名称集合
    private ManageHelper helper;	//数据库业务处理对象
    private Vector<String> classes;	//班级集合
    private StudentModel sm;	//学生数据模型对象
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public ModifyFrame(JDialog owner, String title, boolean modal,int rowNum,StudentModel sm){
		super(owner, title, modal);
		helper = new ManageHelper();	//创建数据库业务处理对象
		
		departments = this.helper.getAllDepartment();	//获得所有院系
		all_Major = this.helper.getAllMajor();	//获得所有的专业
		this.jd = this;
		this.sm = sm;
		this.setSize(350,429);	//设置窗体大小。
		this.setLayout(null);	//设置空布局。
		//获取信息
		String student_id = sm.getValueAt(rowNum, 0).toString();
		String student_name = sm.getValueAt(rowNum, 1).toString();
		String student_sex = sm.getValueAt(rowNum, 2).toString();
		String student_grade = sm.getValueAt(rowNum, 3).toString();
		String student_department = sm.getValueAt(rowNum, 5).toString();
		String student_major = sm.getValueAt(rowNum,6).toString();
		String student_classe =  sm.getValueAt(rowNum, 4).toString();
		String classid = String.valueOf(student_id.charAt(student_id.length()-2))+String.valueOf(student_id.charAt(student_id.length()-1));
		student_ID = new JLabel("班号:");	
		student_ID.setBounds(78, 48, 30, 20);	
		this.add(student_ID);	
		
		student_IDText = new JTextField();
		student_IDText.setBounds(116, 48, 150, 20);
		student_IDText.setText(classid);	//获取班号并显示
		this.add(student_IDText);
		
		student_Name = new JLabel("姓名:");
		student_Name.setBounds(78, 88, 30, 20);
		this.add(student_Name);
		
		
		student_NameText = new JTextField();
		student_NameText.setBounds(116, 88, 150, 20);
		student_NameText.setText(student_name);	//设置学生姓名并显示
		this.add(student_NameText);
		
		sex_Label = new JLabel("性别:");
		sex_Label.setBounds(78, 128, 30, 20);
		this.add(sex_Label);
		
		sex_Box = new JComboBox(new String[]{"","男","女"});
		sex_Box.setSelectedItem(sm.getValueAt(rowNum, 2));
		sex_Box.setBounds(116, 128, 60, 20);
		this.add(sex_Box);
		
		
		grade_Label = new JLabel("年级:");
		grade_Label.setBounds(78, 168, 30, 20);
		this.add(grade_Label);
		
		
		grade_Box = new JComboBox(Tools.CreateGrade());
		grade_Box.setBounds(116, 168, 150, 20);
		//注册"年级选项"事件监听
				grade_Box.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(classe_Box!=null ){	//防止空指针异常
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
				});
		grade_Box.setSelectedItem(sm.getValueAt(rowNum, 3));	//设置年级并显示
		this.add(grade_Box);
		
		
			
		department_Label = new JLabel("院系:");
		department_Label.setBounds(78, 208, 30, 20);
		
		this.add(department_Label);
		
		department_Box = new JComboBox(departments.keySet().toArray());//获得键的集合
		department_Box.setSelectedItem(sm.getValueAt(rowNum, 5));
		//注册"院系"选项事件监听
		department_Box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				addMajorOption();//增加专业的选项
			}
		});
		department_Box.setBounds(116, 208, 150, 20);
		this.add(department_Box);
	
		major_Label = new JLabel("专业:");
		major_Label.setBounds(78, 248, 30, 20);	
		this.add(major_Label);
		
		major_Box = new JComboBox(new String[]{""});//防止空指针异常
		addMajorOption();	//初始化专业的选项
		major_Box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addClasseOption();	//增加班级选项
			}
		});
		major_Box.setSelectedItem(sm.getValueAt(rowNum,6));
		
		major_Box.setBounds(116, 248, 150, 20);
		this.add(major_Box);
		
		classe_Label = new JLabel("班级:");
		classe_Label.setBounds(78, 288, 30, 20);
		this.add(classe_Label);
		
		classe_Box = new JComboBox(new String[]{""});//防止空指针异常
		addClasseOption();//初始化班级选项
		//注册"班级选项"事件监听
				classe_Box.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if(classe_Box.getSelectedItem()!=null){	//防止空指针 
							if(!classe_Box.getSelectedItem().toString().equals("")){	
								String classe = classe_Box.getSelectedItem().toString();
								String grade = grade_Box.getSelectedItem().toString();
								String major_Name = major_Box.getSelectedItem().toString();//获得专业名称
								 String major_ID = all_Major.get(major_Name);	//获得专业编号
							}
						}
					}
				});
		classe_Box.setBounds(116, 288, 150, 20);
		classe_Box.setSelectedItem(sm.getValueAt(rowNum, 4));
		this.add(classe_Box);
		
		
		modify_Button = new JButton("修改");
		modify_Button.setBounds(70, 330, 60, 25);
		
		
		//注册"修改"按钮事件监听
		modify_Button.addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				Student newStudent = new Student();
				String id = student_IDText.getText().trim();
				String name = student_NameText.getText().trim();
				String sex = sex_Box.getSelectedItem().toString();
				String classe = classe_Box.getSelectedItem().toString();
				String grade = grade_Box.getSelectedItem().toString();
				String department_ID = null;
				String major_ID = null;
				String department_Name = null;
				String major_Name = null;
				//数据校验部分
				if(id.equals("")){
					JOptionPane.showMessageDialog(jd, "班号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(id.length()!=2){
					JOptionPane.showMessageDialog(jd, "班号必须是两位数！", "", JOptionPane.WARNING_MESSAGE);
					student_IDText.setText("");
					return ;
				}
				if(name.equals("")){
					JOptionPane.showMessageDialog(jd, "姓名不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(sex.equals("")){
					JOptionPane.showMessageDialog(jd, "性别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(grade.equals("")){
					JOptionPane.showMessageDialog(jd, "年级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(classe.equals("")){
					JOptionPane.showMessageDialog(jd, "班级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(department_Box.getSelectedItem()==null){	//先检查再用
					JOptionPane.showMessageDialog(jd, "院系不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}else{
					department_Name = department_Box.getSelectedItem().toString();	//获得院系名称
					department_ID = departments.get(department_Name);	//获得院系编号	
				}
				if(department_ID.equals("")){
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

				String ID = Tools.CreateID(grade, classe, major_ID, department_ID, id);//生成学号
				student_IDText.setText(ID);	//设置学号文本域
				JOptionPane.showMessageDialog(jd, "该学生的id为:"+ID);
					
				newStudent.setStudent_ID(ID);
				newStudent.setStudent_Name(name);
				newStudent.setSex(sex);
				newStudent.setGrade(grade);
				newStudent.setClasse(classe);
				newStudent.setMajor_ID(major_ID);
				newStudent.setDepartment_ID(department_ID);
				newStudent.setDepartment_Name(department_Name);
				newStudent.setMajor_Name(major_Name);
				if(helper.updateStudent(newStudent, student_id)){
					JOptionPane.showMessageDialog(jd, "修改成功！");
					jd.dispose();	//关闭当前窗口
					return ;
				}else{
					JOptionPane.showMessageDialog(jd, "修改失败！", "", JOptionPane.WARNING_MESSAGE);
					jd.dispose();	//关闭当前窗口
					return ;
				}
			}
			
		});
		this.add(modify_Button);
		
		cancel_Button = new JButton("取消");
		cancel_Button.setBounds(230, 330, 60, 25);
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
	
	
	//增加专业的选项
	public void addMajorOption(){
		major_Box.removeAllItems();//移除"专业选项"的内容（会触发事件监听）
		String option = department_Box.getSelectedItem().toString();//获得选项名称
		String department_ID = departments.get(option);	//获得院系编号
		if(!department_ID.equals("")){
			majors = helper.getMajor(department_ID);	//获得专业
			for(String s : majors){
				major_Box.addItem(s);
			}
		}
	}
	
	
	//增加班级选项
	public void addClasseOption(){
		if(major_Box.getSelectedItem()!=null){	//防止空指针 
			if(!major_Box.getSelectedItem().toString().equals("")){
				if(major_Box.getSelectedItem().toString().equals("") || grade_Box.getSelectedItem()==null || grade_Box.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(jd, "年级不能为空", "", JOptionPane.WARNING_MESSAGE);
					major_Box.setSelectedIndex(0);	//设置为空选项
					return ;
				}
				if(classe_Box!=null ){//防止空指针异常
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
	}

}
