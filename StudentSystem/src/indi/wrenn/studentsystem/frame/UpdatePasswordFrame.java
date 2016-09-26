package indi.wrenn.studentsystem.frame;

import indi.wrenn.studentsystem.bean.User;
import indi.wrenn.studentsystem.dao.ManageHelper;
import indi.wrenn.studentsystem.util.WindowUtil;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//修改密码界面
public class UpdatePasswordFrame extends JDialog{

	private JLabel password_Label;	//原密码标签。
	private JLabel newpassword_Label;	//新密码标签。
	private JLabel repassword_Label;	//确认密码标签。
	private JPasswordField password_Text;	//原密码文本域。
	private JPasswordField newpassword_Text;	//新密码文本域。
	private JPasswordField repassword_Text;	//确认密码文本域。
	private JButton confirm_Button;	//确认按钮。
	private JButton cancel_Button;	//取消按钮。
	private JDialog jd;	//当前窗口 。
	private User user;
	/**
	 * 
	 * @param owner 它的父窗口
	 * @param title 窗口名
	 * @param modal 指定的模式窗口，还有非模式窗口
	 */
	public UpdatePasswordFrame(JFrame owner, String title, boolean modal,User user){
		super(owner, title, modal);
		this.user = user;
		this.jd = this;
		this.setLayout(null);//设置为空布局。
		this.setSize(400,300);//设置大小。
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);//设置背景颜色。
		password_Label = new JLabel("原密码:");	//创建"原密码"标签。
		password_Label.setBounds(100, 60, 50, 20);	//设置"原密码"标签位置。
		c.add(password_Label);	//添加"密码"标签。
		
		password_Text = new JPasswordField();	//创建"原密码"文本域。
		password_Text.setBounds(160, 60, 120, 20);	//设置"原密码"文本域位置。
		password_Text.grabFocus();//获得光标。
		c.add(password_Text);	//添加"密码"文本域。
		
		newpassword_Label = new JLabel("新密码:");	//创建"新密码"标签。
		newpassword_Label.setBounds(100, 110, 50, 20);
		c.add(newpassword_Label);	//添加"新密码"标签。
		
		newpassword_Text = new JPasswordField();	//创建"新密码"文本域。
		newpassword_Text.setBounds(160, 110, 120, 20);	//设置"新密码"文本域位置。
		c.add(newpassword_Text);	//添加"新密码"文本域。
		
		repassword_Label = new JLabel("确认密码");	//创建"确认密码"标签。
		repassword_Label.setBounds(100, 162, 70, 20);	//设置"确认密码"标签位置。
		c.add(repassword_Label);	//添加"确认密码"标签。
		
		repassword_Text = new JPasswordField();	//创建"确认密码"文本域。
		repassword_Text.setBounds(160, 162, 120, 20);	//设置"确认密码"文本域位置。
		c.add(repassword_Text); //添加"确认密码"文本域。
		
		confirm_Button = new JButton("确认");	//创建"确认"按钮。
		confirm_Button.setBounds(90, 210, 60, 20);	//设置"确认"按钮位置。
		//注册"确认"按钮事件监听。
		confirm_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(password_Text.getPassword());
				String repassword = new String(repassword_Text.getPassword());
				String newpassword = new String(newpassword_Text.getPassword());
				if(password.equals("")){
					JOptionPane.showMessageDialog(jd, "原密码不能为空！","",JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(newpassword.equals("")){
					JOptionPane.showMessageDialog(jd, "新密码不能为空！","",JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(repassword.equals("")){
					JOptionPane.showMessageDialog(jd, "确认密码不能为空！","",JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(repassword.equals(newpassword)){
					//检查原密码是否正确
					ManageHelper helper = new ManageHelper();
					user.setPassword(password);
					if(helper.Login(user)){//检查原密码是否正确
						helper.update_Password(user, newpassword);	//修改密码
						JOptionPane.showMessageDialog(jd, "修改密码成功！");
						jd.dispose();//关闭当前窗口
						return ;
					}else{
						JOptionPane.showMessageDialog(jd,"原密码不正确！","",JOptionPane.WARNING_MESSAGE);
						Reset();	//重置
						return ;
					}
				}else{
					JOptionPane.showMessageDialog(jd,"两次密码不一致","",JOptionPane.WARNING_MESSAGE);
					Reset();	//重置
					return ;
				}
				
			}
		});
		c.add(confirm_Button);	//添加"确认"按钮。
		
		
		cancel_Button = new JButton("取消");	//创建"取消"按钮。
		cancel_Button.setBounds(250, 210, 60, 20);	//设置"取消"按钮位置。
		//注册"取消"按钮事件监听。
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.dispose();//关闭当前页面。
			
			}
		});
		c.add(cancel_Button);	//添加"取消"按钮。
		

		this.setResizable(false);	//设置大小不可改变。
		WindowUtil.setFrameCenter(this);//设置窗口居中。
		this.setVisible(true);	//设置窗体可见。
	}
	
	//重置
	public void Reset(){
		password_Text.setText("");
		repassword_Text.setText("");
		newpassword_Text.setText("");
	}
}
