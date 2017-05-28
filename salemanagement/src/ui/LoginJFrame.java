package ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.factory.DAOFactory;
import vo.Account;

public class LoginJFrame extends JFrame implements MouseListener, FocusListener
{
	private JTextField account;
	private JPasswordField password;
	private JButton buttonLogin, buttonCancel, buttonRegister, buttonReset;

	public LoginJFrame()
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

		this.setSize(400, 325);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		ImageIcon icon = new ImageIcon("image/login.jpg");
		JLabel pic = new JLabel();
		icon = new ImageIcon(icon.getImage().getScaledInstance(400, 150, Image.SCALE_DEFAULT));
		pic.setIcon(icon);
		pic.setBounds(0, 0, 400, 150);
		this.getContentPane().add(pic);

		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 150, 400, 150);

		account = new JTextField("用户名/账号");
		account.setBounds(130, 20, 140, 25);
		account.setBorder(BorderFactory.createEtchedBorder());
		account.addFocusListener(this);

		password = new JPasswordField("密码");
		password.setBounds(130, 50, 140, 25);
		password.setBorder(BorderFactory.createEtchedBorder());
		password.addFocusListener(this);
		password.setEchoChar('\0');

		loginPanel.add(account);
		loginPanel.add(password);

		buttonLogin = new JButton("登录");
		buttonLogin.setBounds(100, 100, 80, 25);
		buttonLogin.setFont(new Font("黑体", Font.BOLD, 15));

		buttonCancel = new JButton("取消");
		buttonCancel.setBounds(220, 100, 80, 25);
		buttonCancel.setFont(new Font("黑体", Font.BOLD, 15));

		buttonRegister = new JButton("注册");
		buttonRegister.setFont(new Font("宋体", Font.PLAIN, 13));
		buttonRegister.setBounds(280, 20, 85, 24);
		buttonRegister.setContentAreaFilled(false);

		buttonReset = new JButton("重置");
		buttonReset.setFont(new Font("宋体", Font.PLAIN, 13));
		buttonReset.setBounds(280, 50, 85, 24);
		buttonReset.setContentAreaFilled(false);

		JLabel login = new JLabel("用户登录：");
		login.setBounds(50, 20, 80, 25);
		loginPanel.add(login);

		loginPanel.add(buttonLogin);
		loginPanel.add(buttonReset);
		loginPanel.add(buttonRegister);
		loginPanel.add(buttonCancel);
		buttonLogin.addMouseListener(this);
		buttonReset.addMouseListener(this);
		buttonRegister.addMouseListener(this);
		this.getContentPane().add(loginPanel);
		this.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == buttonLogin)
		{
			try
			{
				Account acc = DAOFactory.getAccount().findByAccountname(account.getText());
				if (acc == null)
				{
					JOptionPane.showMessageDialog(this, "用户不存在");
				} else
				{
					char[] ch = password.getPassword();
					StringBuffer pswd = new StringBuffer();
					for (char i : ch)
					{
						pswd.append(i);
					}
					if (acc.getAccountpassword().equals(pswd.toString()))
					{
						this.dispose();
						new MainMenu(acc); // 登录成功，以他的身份打开新窗口
					} else
					{
						JOptionPane.showMessageDialog(this, "密码错误");
					}
				}
			} catch (Exception ex)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "请检查数据库");
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonReset)
		{
			account.setText("用户名/账号");
			password.setText("密码");
			password.setEchoChar('\0');
		}
		if (e.getSource() == buttonRegister)
		{
			new LoginRegister(this);
		}
		if (e.getSource() == buttonCancel)
		{
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == account)
		{
			if (account.getText().equals("用户名/账号"))
			{
				account.setText("");
			}
		} else if (e.getSource() == password)
		{
			if (password.getText().equals("密码"))
			{
				password.setText("");
				password.setEchoChar('*');
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == account)
		{
			if (account.getText().equals(""))
			{
				account.setText("用户名/账号");
			}
		} else if (e.getSource() == password)
		{
			if (password.getText().equals(""))
			{
				password.setText("密码");
				password.setEchoChar('\0');
			}
		}
	}

}
