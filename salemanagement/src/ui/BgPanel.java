package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/*
 * 为了设置JPanel的背景图片
 */

public class BgPanel extends JPanel
{
	Image image;
	
	public BgPanel()
	{
		image = Toolkit.getDefaultToolkit().getImage("image/background.jpg");
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int imWidth = image.getWidth(this);
		int imHeight = image.getHeight(this);
		int FWidth = getWidth();
		int FHeight = getHeight();
		int x = (FWidth - imWidth) / 2;
		int y = (FHeight - imHeight) / 2;
		g.drawImage(image, x, y, null);
	}
}
