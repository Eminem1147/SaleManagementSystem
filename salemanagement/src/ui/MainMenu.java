package ui;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.factory.DAOFactory;
import vo.Account;

public class MainMenu extends JFrame implements ActionListener
{
	private JButton zhuxiao, userManagement, changePassword, commodityManagement;
	private JButton saleManagement, storeManagement, saleQuery;
	private JButton guestManagement;
	private Account acc;

	public MainMenu(Account acc)
	{
		// 设置图标
		Image image = null;
		try
		{
			image = ImageIO.read(new File("image/logo.jpg"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(image);
		
		this.acc = acc;
		this.setResizable(false);
		this.setBounds(100, 135, 200, 480);
		this.setLayout(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try
				{
					DAOFactory.getAccount().alter(acc);
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		try
		{
			DAOFactory.getAccount().alter(acc);
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel allPanel = new BgPanel();
		allPanel.setLayout(null);
		allPanel.setBounds(0, 0, 200, 500);

		commodityManagement = new JButton("商品管理");
		commodityManagement.setBounds(30, 20, 130, 40);
		allPanel.add(commodityManagement);
		commodityManagement.addActionListener(this);

		guestManagement = new JButton("顾客管理");
		guestManagement.setBounds(30, 70, 130, 40);
		allPanel.add(guestManagement);
		guestManagement.addActionListener(this);

		saleManagement = new JButton("销售管理");
		saleManagement.setBounds(30, 120, 130, 40);
		allPanel.add(saleManagement);
		saleManagement.addActionListener(this);

		storeManagement = new JButton("进货管理");
		storeManagement.setBounds(30, 170, 130, 40);
		allPanel.add(storeManagement);
		storeManagement.addActionListener(this);

		saleQuery = new JButton("销售查询");
		saleQuery.setBounds(30, 220, 130, 40);
		allPanel.add(saleQuery);
		saleQuery.addActionListener(this);

		userManagement = new JButton("用户管理");
		userManagement.setBounds(30, 270, 130, 40);
		allPanel.add(userManagement);
		userManagement.addActionListener(this);

		changePassword = new JButton("修改密码");
		changePassword.setBounds(30, 320, 130, 40);
		allPanel.add(changePassword);
		changePassword.addActionListener(this);

		zhuxiao = new JButton("用户注销");
		zhuxiao.setBounds(30, 370, 130, 40);
		allPanel.add(zhuxiao);
		zhuxiao.addActionListener(this);

		Container cp = this.getContentPane();
		cp.add(allPanel);
		allPanel.setOpaque(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == commodityManagement)
			new CommodityManagement(this);
		
		if (e.getSource() == storeManagement)
			new StoreManagement(this);
		
		if (e.getSource() == saleManagement)
			new SaleManagement(this);
		
		if (e.getSource() == guestManagement)
			new GuestManagement(this);
		
		if (e.getSource() == userManagement)
			new UserManagement(this);
		
		if (e.getSource() == changePassword)
			new ChangePassword(this, this.acc);
		
		if (e.getSource() == saleQuery)
			new SaleQuery(this);
		
		if (e.getSource() == zhuxiao)
		{
			this.dispose();
			new LoginJFrame();
			return;
		}
	}
}
