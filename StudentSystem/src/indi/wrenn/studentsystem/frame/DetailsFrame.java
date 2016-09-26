package indi.wrenn.studentsystem.frame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import indi.wrenn.studentsystem.model.StudentModel;
import indi.wrenn.studentsystem.util.WindowUtil;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//详细信息界面
public class DetailsFrame extends JDialog{
	private JButton confirm_Button;	//"确认"按钮。
    private JTextField sex_Text;	//"性别"选项。
    private JTextField major_Text;	//"专业"选项。
    private JTextField department_Text;	//"所属院系"选项。
    private JLabel student_ID;  //"学号"标签。
    private JLabel student_Name;	//"姓名"标签。
    private JLabel sex_Label;	//"性别"标签。
    private JLabel classe_Label;	//"班级"标签。
    private JLabel grade_Label;	//"年级标签"。
    private JLabel major_Label;	//"专业"标签。
    private JLabel department_Label;	//"所属院系"标签。
    private JTextField student_IDText;	//"学号"文本域。
    private JTextField student_NameText;	//"姓名"文本域。
    private JTextField classe_Text;	//"班级"文本域。
    private JTextField grade_Text;	//"年级"文本域。
    private StudentModel sm;	//传入的学生数据模型
    private JDialog jd;	//当前窗口。
/**
 * 
 * @param owner 它的父窗口
 * @param title 窗口名
 * @param modal 指定的模式窗口，还有非模式窗口
 */
public DetailsFrame(JDialog owner, String title, boolean modal, int rowNum,StudentModel sm){
	super(owner, title, modal);
	this.sm = sm;	//传入学生数据模型
	this.jd = this;
	this.setSize(350,429);	//设置窗体大小。
	this.setLayout(null);	//设置空布局。
	
	student_ID = new JLabel("学号:");	
	student_ID.setBounds(78, 48, 30, 20);	
	this.add(student_ID);	
	
	student_IDText = new JTextField();
	student_IDText.setEditable(false);	//不可编辑
	student_IDText.setText(sm.getValueAt(rowNum, 0).toString());	//获取学号并显示
	student_IDText.setBounds(116, 48, 150, 20);
	this.add(student_IDText);
	
	student_Name = new JLabel("姓名:");
	student_Name.setBounds(78, 88, 30, 20);
	this.add(student_Name);
	
	
	student_NameText = new JTextField();
	student_NameText.setBounds(116, 88, 150, 20);
	student_NameText.setEditable(false);//不可编辑
	student_NameText.setText(sm.getValueAt(rowNum, 1).toString());	//设置学生姓名并显示
	this.add(student_NameText);
	
	sex_Label = new JLabel("性别:");
	sex_Label.setBounds(78, 128, 30, 20);
	this.add(sex_Label);
	
	sex_Text = new JTextField();
	sex_Text.setBounds(116, 128, 60, 20);
	sex_Text.setEditable(false);//不可编辑
	sex_Text.setText(sm.getValueAt(rowNum, 2).toString());	//设置学生性别并显示
	this.add(sex_Text);
	
	grade_Label = new JLabel("年级:");
	grade_Label.setBounds(78, 168, 30, 20);
	this.add(grade_Label);
	
	grade_Text = new JTextField();
	grade_Text.setBounds(116, 168, 150, 20);
	grade_Text.setEditable(false);
	grade_Text.setText(sm.getValueAt(rowNum, 3).toString());	//设置年级并显示
	this.add(grade_Text);
	
	classe_Label = new JLabel("班级:");
	classe_Label.setBounds(78, 208, 30, 20);
	this.add(classe_Label);
	
	classe_Text = new JTextField();
	classe_Text.setBounds(116, 208, 150, 20);
	classe_Text.setEditable(false);
	classe_Text.setText(sm.getValueAt(rowNum, 4).toString());
	this.add(classe_Text);
		
	department_Label = new JLabel("院系:");
	department_Label.setBounds(78, 248, 30, 20);
	
	this.add(department_Label);
	
	department_Text = new JTextField();
	department_Text.setEditable(false);
	department_Text.setText(sm.getValueAt(rowNum, 5).toString());
	department_Text.setBounds(116, 248, 150, 20);
	this.add(department_Text);

	major_Label = new JLabel("专业:");
	major_Label.setBounds(78, 288, 30, 20);
	this.add(major_Label);
	
	major_Text = new JTextField();
	major_Text.setEditable(false);
	major_Text.setBounds(116, 288, 150, 20);
	major_Text.setText(sm.getValueAt(rowNum, 6).toString());
	this.add(major_Text);
	
	confirm_Button = new JButton("确认");
	//注册"确定"按钮事件监听
	confirm_Button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jd.dispose();//关闭当前窗口
		}
	});
	confirm_Button.setBounds(150, 330, 60, 25);
	
	this.add(confirm_Button);

	
	WindowUtil.setFrameCenter(this);
	this.setResizable(false);
	this.setVisible(true);
	}
}
