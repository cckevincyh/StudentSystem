package indi.wrenn.studentsystem.model;

import indi.wrenn.studentsystem.bean.Student;
import indi.wrenn.studentsystem.dao.ManageHelper;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ScoreModel extends AbstractTableModel{
	private ManageHelper helper;
	private Vector<Student> students;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public ScoreModel(String sql,String major_Name,String grade,JDialog jd) {
		helper = new ManageHelper();
		students = helper.getStudent(sql);
		Vector<String> courses = helper.getCourse(helper.getAllMajor().get(major_Name),grade);//获得课程
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年级");
		columnNames.add("班级");
		columnNames.add("院系");
		columnNames.add("专业");
		for(int i=0;i<courses.size();i++){
			columnNames.add(courses.get(i));
		}
		for(Student student : students){
			Vector<String> hang = new Vector<String>();
			hang.add(student.getStudent_ID());
			hang.add(student.getStudent_Name());
			hang.add(student.getSex());
			hang.add(student.getGrade());
			hang.add(student.getClasse());
			hang.add(student.getDepartment_Name());
			hang.add(student.getMajor_Name());
			HashMap<String, String> scores = helper.getStudentScore(student.getStudent_ID());	//得到所有课程
			for(int i=0;i<courses.size();i++){
				if(scores.size()!=0){
					String score = scores.get(courses.get(i));	//得到成绩
					hang.add(score);
				}else{
					hang.add("");
				}
			}
			rowData.add(hang);
		}
		if(getRowCount()!=0){
			JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
			return ;
		}else{
			JOptionPane.showMessageDialog(jd, "没有任何记录！");
			return ;
		}
	}
	
	//得到共有多少行
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.rowData.size();
		}
		//得到共有多少列
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.columnNames.size();
		}
		//得到某行某列的数据
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
		}
		
		//重写方法 getColumnName
		@Override  
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return (String)this.columnNames.get(column);
		}

}
