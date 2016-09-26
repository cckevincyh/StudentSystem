package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.model.ScoreModel;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;



//查询成绩界面
public class QueryScoreFrame extends JDialog{
	private ManageHelper helper;
	private ScoreModel sm;
	private Vector<String> courses;
	private JDialog jd;
	private HashMap<String, JTextField> jtextFieldHashMap;//管理文本域的集合
	private JButton confirm_button;	//"确定按钮"
	private HashMap<String, String> scores;	//学生所有成绩
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public QueryScoreFrame(JDialog owner, String title, boolean modal,ScoreModel sm,int rowNum){
		super(owner, title, modal);
		this.jd = this;
		this.setLayout(null);
		
		helper = new ManageHelper();
		this.sm = sm;
		jtextFieldHashMap = new HashMap<String, JTextField>();
		courses = helper.getCourse(helper.getAllMajor().get(sm.getValueAt(rowNum, 6)),sm.getValueAt(rowNum, 3).toString());//获得所有课程
		scores = helper.getStudentScore(sm.getValueAt(rowNum, 0).toString());//根据学号获得该学生的所有科目成绩
		
	
		int vgap = 0;	//垂直间距
		for(int i=0;i<courses.size();i++){
			JLabel jLabel = new JLabel(courses.get(i)+":");
			jLabel.setBounds(78, 48+vgap, 120, 20);
			JTextField field = new JTextField();
			field.setEditable(false);
			field.setText(scores.get(courses.get(i)));//给文本域添加成绩
			field.setBounds(206, 48+vgap, 150, 20);
			jtextFieldHashMap.put(courses.get(i),field);	//添加入管理文本域的集合
			this.add(jLabel);
			this.add(field);
			vgap += 30;
		}
		confirm_button = new JButton("确定");
		confirm_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.dispose();
			}
		});
		confirm_button.setBounds(215, 48+vgap+5, 60, 20);
		this.add(confirm_button);
		this.setSize(450, 48+vgap+78);
		WindowUtil.setFrameCenter(this);
		this.setVisible(true);
		
	}
}
