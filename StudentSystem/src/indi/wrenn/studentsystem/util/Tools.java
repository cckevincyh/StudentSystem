package indi.wrenn.studentsystem.util;

import java.util.Calendar;
import java.util.Vector;

public class Tools {

	//获取年级
		public static Vector<String> CreateGrade(){
			Vector<String> vector = new Vector<String>();
			vector.add("");	//添加一个空选项 
			Calendar c = Calendar.getInstance();
			int Year = c.get(Calendar.YEAR);
			for(int i=0;i<4;i++){
				vector.add(String.valueOf(Year--));
			}
			return vector;
			
		}
		
		
		//生成学生学号的方法(学号：department+major+grade+classe+num)
		public static String CreateID(String grade,String classe,String major,String department,String num){
			String id = department+major+grade.charAt(2)+""+grade.charAt(3)+classe+num;
			return id;
		}
}
