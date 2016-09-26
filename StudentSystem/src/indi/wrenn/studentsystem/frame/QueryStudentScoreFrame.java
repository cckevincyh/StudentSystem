package indi.wrenn.studentsystem.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.model.ScoreModel;
import indi.wrenn.studentsystem.model.StudentModel;
import indi.wrenn.studentsystem.util.CreateSql;
import indi.wrenn.studentsystem.util.Tools;
import indi.wrenn.studentsystem.util.WindowUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class QueryStudentScoreFrame extends JDialog{
    private JComboBox major_Box;	//"专业"选项。
    private JComboBox department_Box;	//"所属院系"选项。
    private JLabel classe_Label;	//"班级"标签。
    private JLabel grade_Label;	//"年级标签"。
    private JLabel major_Label;	//"专业"标签。
    private JLabel department_Label;	//"所属院系"标签。
    private JComboBox classe_Box;	//"班级"文本域。
    private JComboBox grade_Box;	//"年级"文本域。
	private JButton query_Button;	//"查询"按钮。
	private JButton conditions_button;	//多条件查询按钮 
	private JComboBox query_List;	//"查询"选项。
	private JLabel query_Label;	//标签。
	private JTextField query_Text;	//"查询"文本域。
	private JButton details_Button;	//"详细信息"按钮
	private JTable jt;	//表格。
	private JScrollPane jsp;	//滚动条。
    private JDialog jd;	//当前窗口。
    private HashMap<String, String> departments;	//所有院系集合
    private HashMap<String, String> all_Major;	//所有专业集合
    private Vector<String> majors;	//专业名称集合
    private ManageHelper helper;	//数据库业务处理对象
    private ScoreModel scoreModel;
    private static Vector<String> query_Option;
	
	static {
		query_Option = new Vector<String>();
		query_Option.add("全部");
		query_Option.add("学号");
		query_Option.add("姓名");
		query_Option.add("性别");
		query_Option.add("班级");
	}
    
    /**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public QueryStudentScoreFrame(JFrame owner, String title, boolean modal){
		super(owner, title, modal);
		this.setLayout(null);
		
		this.jd = this;
		helper = new ManageHelper();	//创建数据库业务处理对象
		departments = this.helper.getAllDepartment();	//获得所有院系
		all_Major = this.helper.getAllMajor();	//获得所有的专业
		
		grade_Label = new JLabel("年级:");
		grade_Label.setBounds(25, 19, 30, 20);
		this.add(grade_Label);
		
		grade_Box = new JComboBox(Tools.CreateGrade());
		grade_Box.setBounds(60, 19, 60, 20);
		this.add(grade_Box);
		
		department_Label = new JLabel("院系:");
		department_Label.setBounds(130, 19, 30, 20);
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
		department_Box.setBounds(165, 19, 150, 20);
		this.add(department_Box);
		
		major_Label = new JLabel("专业:");
		major_Label.setBounds(325, 19, 30, 20);
		this.add(major_Label);
		
		major_Box = new JComboBox(new String[]{""});//防止空指针异常
		major_Box.setBounds(360, 19, 150, 20);
		this.add(major_Box);
		
		query_Label = new JLabel("查询信息:");
		query_Label.setBounds(520, 19, 60, 20);
		this.add(query_Label);
		
		
		query_Text = new JTextField();
		query_Text.setBounds(585, 19, 100, 20);
		this.add(query_Text);
		
		query_List = new JComboBox(query_Option);
		query_List.setBounds(695, 19, 65, 20);
		this.add(query_List);
		
	
		
		
		query_Button = new JButton("查询");
		query_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				String grade = grade_Box.getSelectedItem().toString();
				String major =	null;
				String str = query_Text.getText().trim();
				String option = query_List.getSelectedItem().toString();
				if(grade.equals("")){
					JOptionPane.showMessageDialog(jd, "年级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}if(department_Box.getSelectedItem()==null){	//先检查再用
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
					major = major_Box.getSelectedItem().toString();//获得专业名称
				}
				if(major.equals("")){
					JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				//创建查询的sql语句
				String sql = CreateSql.getStudent_Sql(grade, major, str, option);
				scoreModel = new ScoreModel(sql, major, grade, jd);
				jt.setModel(scoreModel);
			}
		});
		query_Button.setBounds(770, 19, 65, 20);
		this.add(query_Button);
		
		conditions_button = new JButton("多条件查询");
		conditions_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String grade = grade_Box.getSelectedItem().toString();
				String major =	null;
				if(grade.equals("")){
					JOptionPane.showMessageDialog(jd, "年级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}if(department_Box.getSelectedItem()==null){	//先检查再用
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
					major = major_Box.getSelectedItem().toString();//获得专业名称
				}
				if(major.equals("")){
					JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				ScoreConditionsQueryFrame frame = new ScoreConditionsQueryFrame(jd, "多条件查询", true, jt, department_Box.getSelectedItem().toString(), major,grade);
			}
		});
		
		conditions_button.setBounds(845, 19, 100, 20);
		this.add(conditions_button);
		
		jt = new JTable();
		jsp = new JScrollPane(jt);
		jsp.setBounds(26, 45, 925, 480);
		this.add(jsp);
		
		details_Button = new JButton("详细成绩");
		//注册"详细信息"按钮事件监听
		details_Button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowNum = jt.getSelectedRow();
				if(rowNum==-1){
					JOptionPane.showMessageDialog(jd, "请选择一行！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}				
				QueryScoreFrame frame = new QueryScoreFrame(jd, "学生成绩", true, scoreModel, rowNum);
			}
		});
		
		details_Button.setBounds(450, 530, 100, 20);
		this.add(details_Button);
		
		this.setSize(1000,600);
		WindowUtil.setFrameCenter(this);
		this.setVisible(true);
	}
}
