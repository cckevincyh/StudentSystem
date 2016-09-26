package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.model.ScoreAnalyzeModel;
import indi.wrenn.studentsystem.util.WindowUtil;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
//成绩排名界面
import javax.swing.JTable;
public class ScoresRankingFrame extends JDialog{

	private JTable jt;
	private JScrollPane jsp;
	
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public ScoresRankingFrame(JDialog owner, String title, boolean modal,ScoreAnalyzeModel model){
		super(owner, title, modal);
		jt = new JTable();
		jsp = new JScrollPane(jt);
		jt.setModel(model);
		jsp.setBounds(20, 20, 860, 460);
		this.add(jsp);
		
		this.setSize(1000,500);
		WindowUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setVisible(true);
	}
}
