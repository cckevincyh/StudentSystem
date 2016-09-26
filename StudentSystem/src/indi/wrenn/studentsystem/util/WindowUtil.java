package indi.wrenn.studentsystem.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

public class WindowUtil {
	 //设置窗体居中
    public static void setFrameCenter(Container c) {
        //获取工具对象
        Toolkit tk = Toolkit.getDefaultToolkit();

        //获取屏幕的宽和高
        Dimension d = tk.getScreenSize();
        double srceenWidth = d.getWidth();
        double srceenHeigth = d.getHeight();

        //获取窗体的宽和高
        int frameWidth = c.getWidth();
        int frameHeight = c.getHeight();

        //获取新的宽和高
        int width = (int) (srceenWidth - frameWidth) / 2;
        int height = (int) (srceenHeigth - frameHeight) / 2;

        //设置窗体坐标
        c.setLocation(width, height);
    }
}
