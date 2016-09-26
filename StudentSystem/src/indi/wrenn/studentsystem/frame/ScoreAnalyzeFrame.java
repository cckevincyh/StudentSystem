package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.model.ScoreAnalyzeModel;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class ScoreAnalyzeFrame extends JDialog{
	private ManageHelper helper;
	private Vector<String> courses;
	private JLabel average_Scores;	//平均成绩标签
	private JTextField average_ScoresText;	//平均成绩文本域
	private JLabel total_Score;	//总成绩标签
	private JTextField total_ScoreText;	//总成绩文本域
	private JButton scores_Ranking;	//成绩排名按钮
	private JDialog jd;

	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public ScoreAnalyzeFrame(JDialog owner, String title, boolean modal,String major_Id,String major_Name,String grade,String classe){
		super(owner, title, modal);
		this.jd = this;
		this.setLayout(null);
		helper = new ManageHelper();
		
		courses = helper.getCourse(major_Id,grade);//获得所有课程
		
		average_Scores = new JLabel("班级平均成绩:");
		average_Scores.setBounds(200, 15, 90, 20);//班级平均成绩文本域
		this.add(average_Scores);
		
		average_ScoresText = new JTextField();
		average_ScoresText.setEditable(false);
		//设置班级的平均成绩
		average_ScoresText.setText(helper.getClasseAvg(classe, grade, major_Name));
		average_ScoresText.setBounds(295, 15, 60, 20);
		this.add(average_ScoresText);
		
		total_Score = new JLabel("班级总成绩:");
		total_Score.setBounds(435, 15, 80, 20);
		this.add(total_Score);
		
		total_ScoreText = new JTextField();	//班级总成绩文本域
		total_ScoreText.setEditable(false);
		//设置班级总成绩
		total_ScoreText.setText(helper.getClasse_SumScore(classe, grade, major_Name));
		total_ScoreText.setBounds(520, 15, 60, 20);
		this.add(total_ScoreText);
		
	
		int vgap = 0;	//垂直间距
		for(int i=0;i<courses.size();i++){
			JTextField course = new JTextField(courses.get(i)+":");	//科目标签
			course.setEditable(false);
			course.setBounds(26, 48+vgap, 120, 20);	
			this.add(course);
			JLabel highestScore = new JLabel("最高成绩:");	//最高成绩标签
			highestScore.setBounds(156, 48+vgap, 60, 20);
			this.add(highestScore);
			
			JTextField highestScoreText = new JTextField();//最高成绩文本域
			highestScoreText.setBounds(221, 48+vgap, 30, 20);
			//设置最高成绩
			highestScoreText.setText(helper.getCourseHighestScore(courses.get(i), major_Name, classe, grade));
			highestScoreText.setEditable(false);
			this.add(highestScoreText);
			
			JLabel lowest_Score = new JLabel("最低成绩:");	//最低成绩标签
			lowest_Score.setBounds(261, 48+vgap, 60, 20);
			this.add(lowest_Score);
			
			JTextField lowest_ScoreText = new JTextField();//最低成绩文本域
			lowest_ScoreText.setBounds(326, 48+vgap, 30, 20);
			//设置最低成绩
			lowest_ScoreText.setText(helper.getCourseLowestScore(courses.get(i), major_Name, classe, grade));
			lowest_ScoreText.setEditable(false);
			this.add(lowest_ScoreText);
			
			
			JLabel avg_Score = new JLabel("平均成绩:");	//平均成绩标签
			avg_Score.setBounds(366, 48+vgap, 60, 20);
			this.add(avg_Score);
			
			JTextField avg_ScoreText = new JTextField();	//平均成绩文本域
			avg_ScoreText.setBounds(431, 48+vgap, 30, 20);
			avg_ScoreText.setEditable(false);
			//计算平均成绩
			String score = helper.getCourseAvg(courses.get(i), major_Name, classe, grade);
			if(score!=null && !score.equals("")){
				avg_ScoreText.setText(score.charAt(0)+""+score.charAt(1)+""+score.charAt(2)+""+score.charAt(3));
			}
			this.add(avg_ScoreText);
			
			
			JLabel j1 = new JLabel("优:");	//优人数标签
			j1.setBounds(476, 48+vgap, 20, 20);
			this.add(j1);
			
			JTextField t1 = new JTextField();//优文本域
			t1.setEditable(false);
			t1.setBounds(498, 48+vgap, 40, 20);
			//计算优秀人数
			t1.setText(helper.getCount_ScoreType(grade, classe, major_Name, "优", courses.get(i)));
			this.add(t1);
			
			JLabel j2 = new JLabel("良:");	//良人数标签
			j2.setBounds(548, 48+vgap, 20, 20);
			this.add(j2);
			
			JTextField t2 = new JTextField();//良文本域
			t2.setEditable(false);
			t2.setBounds(570, 48+vgap, 40, 20);
			//计算良好人数
			t2.setText(helper.getCount_ScoreType(grade, classe, major_Name, "良", courses.get(i)));
			this.add(t2);

			JLabel j3 = new JLabel("中:");	//中人数标签
			j3.setBounds(620, 48+vgap, 20, 20);
			this.add(j3);
			
			JTextField t3 = new JTextField();//中文本域
			t3.setEditable(false);
			//计算中人数
			t3.setText(helper.getCount_ScoreType(grade, classe, major_Name, "中", courses.get(i)));
			t3.setBounds(642, 48+vgap, 40, 20);
			this.add(t3);
			
			
			JLabel j4 = new JLabel("及格:");	//及格人数标签
			j4.setBounds(692, 48+vgap, 30, 20);
			this.add(j4);
			
			JTextField t4 = new JTextField();//及格文本域
			t4.setEditable(false);
			//计算及格人数
			t4.setText(helper.getCount_ScoreType(grade, classe, major_Name, "及格", courses.get(i)));
			t4.setBounds(724, 48+vgap, 40, 20);
			this.add(t4);
			
			
			JLabel j5 = new JLabel("不及格:");	//不及格人数标签
			j5.setBounds(774, 48+vgap, 50, 20);
			this.add(j5);
			
			JTextField t5 = new JTextField();//不及格文本域
			t5.setEditable(false);
			t5.setBounds(825, 48+vgap, 40, 20);
			//计算不及格人数
			t5.setText(helper.getCount_ScoreType(grade, classe, major_Name, "不及格", courses.get(i)));
			this.add(t5);
			
			vgap += 30;
		}
		scores_Ranking = new JButton("成绩排名");
		scores_Ranking.setBounds(420, 48+vgap+10, 100, 25);
		//注册"成绩排名"按钮事件监听
		scores_Ranking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ScoreAnalyzeModel model = new ScoreAnalyzeModel(major_Name, grade, classe);
				ScoresRankingFrame frame = new ScoresRankingFrame(jd, "成绩排名", true, model);
			}
		});
		this.add(scores_Ranking);
		
		
		this.setSize(900, 48+vgap+78);
		this.setResizable(false);
		WindowUtil.setFrameCenter(this);
		this.setVisible(true);
		
	}
}
