package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.model.StudentModel;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//修改成绩界面
public class UpdateScoreFrame extends JDialog{
	private ManageHelper helper;
	private StudentModel sm;
	private Vector<String> courses;
	private JDialog jd;
	private JButton update_button;	//修改按钮
	private JButton cancel_button;	//取消按钮
	private HashMap<String, JTextField> jtextFieldHashMap;//管理文本域的集合
	private HashMap<String, String> scores;	//学生所有成绩
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public UpdateScoreFrame(JDialog owner, String title, boolean modal,StudentModel sm,int rowNum){
		super(owner, title, modal);
		this.jd = this;
		this.setLayout(null);
		helper = new ManageHelper();
		this.sm = sm;
		jtextFieldHashMap = new HashMap<String, JTextField>();
		courses = helper.getCourse(helper.getAllMajor().get(sm.getValueAt(rowNum, 6)),sm.getValueAt(rowNum, 3).toString());//获得所有课程
		int vgap = 0;	//垂直间距
		scores = helper.getStudentScore(sm.getValueAt(rowNum, 0).toString());//根据学号获得该学生的所有科目成绩
		if(scores.isEmpty()){
			JOptionPane.showMessageDialog(jd, "该同学还没有成绩！", "", JOptionPane.WARNING_MESSAGE);
			jd.dispose();
			return ;
		}
		for(int i=0;i<courses.size();i++){
			JLabel jLabel = new JLabel(courses.get(i)+":");
			jLabel.setBounds(78, 48+vgap, 120, 20);
			JTextField field = new JTextField();
			field.setText(scores.get(courses.get(i)));//给文本域添加成绩
			field.setBounds(206, 48+vgap, 150, 20);
			jtextFieldHashMap.put(courses.get(i),field);	//添加入管理文本域的集合
			this.add(jLabel);
			this.add(field);
			vgap += 30;
		}
		
		update_button = new JButton("修改");
		update_button.setBounds(120, 48+vgap+5, 60, 20);
		//注册"修改"按钮事件监听
		update_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<courses.size();i++){
					JTextField field = jtextFieldHashMap.get(courses.get(i));
					String score = field.getText().trim();
					if(score.equals("")){
						JOptionPane.showMessageDialog(jd, "成绩不能为空！","",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					if(Double.parseDouble(score)<0 || Double.parseDouble(score)>100){
						JOptionPane.showMessageDialog(jd, "请输入0~100之间的成绩", "", JOptionPane.WARNING_MESSAGE);
						return ;
					}
				}
				int i;
				for(i=0;i<courses.size();i++){
					JTextField field = jtextFieldHashMap.get(courses.get(i));
					String score = field.getText().trim();
					boolean b = helper.updateStudentScore(sm.getValueAt(rowNum, 0).toString(),courses.get(i),score);
					if(!b){
						break;
					}
				}
				if(i<courses.size()){
					JOptionPane.showMessageDialog(jd, "修改失败！","",JOptionPane.WARNING_MESSAGE);
					return ;
				}else{
					JOptionPane.showMessageDialog(jd, "修改成功！","",JOptionPane.WARNING_MESSAGE);
					jd.dispose();
					return ;
				}
				
			}
		});
		this.add(update_button);
		cancel_button = new JButton("取消");
		cancel_button.setBounds(280, 48+vgap+5, 60, 20);
		cancel_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.dispose(); 
			}
		});
		this.add(cancel_button);
		this.setSize(450, 48+vgap+78);
		WindowUtil.setFrameCenter(this);
		this.setVisible(true);
		
	}
}
