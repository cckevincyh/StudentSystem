package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.model.StudentModel;
import indi.wrenn.studentsystem.util.CreateSql;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AddStudentScoreFrame extends JDialog{
	private JPanel jp1,jp2,jp3;	//面板。
	private JLabel query_Label;	//标签。
	private JButton query_Button;	//"查询"按钮。
	private JComboBox query_List;	//"查询"选项。
	private JButton preciseQuery_Button;	//"精确查询"按钮。
	private JButton addScore_button;	//"添加成绩"按钮。
	private JTextField query_Text;	//"查询"文本域。
	private JTable jt;	//表格。
	private JScrollPane jsp;	//滚动条。
	private JDialog jd;	//当前窗口。
	private StudentModel studentModel;//学生数据模型类
	
	private static Vector<String> query_Option;
	
	static {
		query_Option = new Vector<String>();
		query_Option.add("全部");
		query_Option.add("学号");
		query_Option.add("姓名");
		query_Option.add("性别");
		query_Option.add("班级");
		query_Option.add("年级");
		query_Option.add("专业");
		query_Option.add("院系");
	}
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public AddStudentScoreFrame(JFrame owner, String title, boolean modal){
		super(owner, title, modal);
		this.jd = this;

		Container c = this.getContentPane();
		jp1 = new JPanel();
		query_Label = new JLabel("请输入查询信息:");
		jp1.add(query_Label);
		
		query_Text = new JTextField(10);
		jp1.add(query_Text);
		
		query_List = new JComboBox<String>(query_Option);
		jp1.add(query_List);
		
		query_Button = new JButton("查询");
		//注册"查询"按钮事件监听
		query_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = query_Text.getText().trim();	//查询内容
				String option = query_List.getSelectedItem().toString();	//查询选项
				String sql = CreateSql.getStudent_Sql(str, option);	//获得sql语句
				studentModel = new StudentModel(sql,jd);//构建新的数据模型类，并更新
				jt.setModel(studentModel);//更新Jtable
			}
		});
		jp1.add(query_Button);
		
		preciseQuery_Button = new JButton("多条件查询");
		//注册"多条件查询"按钮事件监听
		preciseQuery_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConditionsQueryFrame frame = new ConditionsQueryFrame(jd, "多条件查询", true, jt);
				
			}
		});
		jp1.add(preciseQuery_Button);
		c.add(jp1,BorderLayout.NORTH);	//添加面板
	
		jp2 = new JPanel();
		jt = new JTable();
		String sql = CreateSql.getStudent_Sql(null, "全部");//查询全部内容
		studentModel = new StudentModel(sql,jd);//构建新的数据模型类，并更新
		jt.setModel(studentModel);
		
	
		
		jsp = new JScrollPane(jt);
		jp2.add(jsp);
		c.add(jp2,BorderLayout.CENTER);	//添加面板
		
		jp3 = new JPanel();
		addScore_button = new JButton("添加成绩");
		//注册"详细信息"按钮事件监听
		addScore_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowNum = jt.getSelectedRow();
				if(rowNum==-1){
					JOptionPane.showMessageDialog(jd, "请选择一行！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				AddScoreFrame frame = new AddScoreFrame(jd, "添加成绩", true, studentModel, rowNum);
			}
		});
		jp3.add(addScore_button);
		c.add(jp3,BorderLayout.SOUTH);
	
		this.setSize(600,540);
		this.setResizable(false);
		WindowUtil.setFrameCenter(this);//设置窗体居中。
		this.setVisible(true);
		
	}
}
