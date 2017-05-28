package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.factory.DAOFactory;
import vo.Account;

public class ChangePassword extends JDialog implements ActionListener
{

	private JFrame f;
	private JButton buttonOk, buttonCancel;
	private JTextField textName;
	private JPasswordField pswdold, pswdnew1, pswdnew2;
	Account acc;

	public ChangePassword(JFrame f, Account acc)
	{
		this.acc = acc;
		this.f = f;
		f.setEnabled(false);
		this.setTitle("修改密码");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDropTarget(getDropTarget());
		this.setLayout(null);
		this.setResizable(false);
		Container cp = this.getContentPane();
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				f.setEnabled(true);
				dispose();
			}
		});

		JLabel labelName = new JLabel("当前用户名：");
		labelName.setBounds(100, 10, 100, 30);
		JLabel labelPswdOld = new JLabel("原密码：");
		labelPswdOld.setBounds(100, 60, 100, 30);
		JLabel labelPswdNew1 = new JLabel("新密码：");
		labelPswdNew1.setBounds(100, 110, 100, 30);
		JLabel labelPswdNew2 = new JLabel("再输一次密码：");
		labelPswdNew2.setBounds(100, 160, 100, 30);

		textName = new JTextField(acc.getAccountname()); // 设置成当前用户
		textName.setBounds(200, 13, 100, 28);
		textName.setEditable(false);
		textName.setBorder(BorderFactory.createEtchedBorder());
		pswdold = new JPasswordField();
		pswdold.setBounds(200, 63, 100, 28);
		pswdold.setBorder(BorderFactory.createEtchedBorder());
		pswdnew1 = new JPasswordField();
		pswdnew1.setBounds(200, 113, 100, 28);
		pswdnew1.setBorder(BorderFactory.createEtchedBorder());
		pswdnew2 = new JPasswordField();
		pswdnew2.setBounds(200, 163, 100, 28);
		pswdnew2.setBorder(BorderFactory.createEtchedBorder());

		buttonOk = new JButton("修改");
		buttonOk.setBounds(100, 220, 80, 25);
		buttonOk.setFont(new Font("宋体", Font.BOLD, 15));

		buttonCancel = new JButton("取消");
		buttonCancel.setBounds(220, 220, 80, 25);
		buttonCancel.setFont(new Font("宋体", Font.BOLD, 15));

		cp.add(labelName);
		cp.add(labelPswdOld);
		cp.add(labelPswdNew1);
		cp.add(labelPswdNew2);
		cp.add(textName);
		cp.add(pswdold);
		cp.add(pswdnew1);
		cp.add(pswdnew2);

		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
		cp.add(buttonOk);
		cp.add(buttonCancel);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == buttonOk)
		{
			char[] chOld = this.pswdold.getPassword();
			StringBuffer pswdold = new StringBuffer();
			for (char i : chOld)
			{
				pswdold.append(i);
			}
			if (acc.getAccountpassword().equals(pswdold.toString()))
			{
				StringBuffer pswdnew1 = new StringBuffer(), pswdnew2 = new StringBuffer();
				char[] chNew1 = this.pswdnew1.getPassword();
				for (char i : chNew1)
				{
					pswdnew1.append(i);
				}
				char[] chNew2 = this.pswdnew2.getPassword();
				for (char i : chNew2)
				{
					pswdnew2.append(i);
				}
				if (chNew1.length < 6 || chNew2.length < 6)
				{
					JOptionPane.showMessageDialog(this, "请输入不少于6位的密码");
					return;
				}
				if (!pswdnew1.toString().equals(pswdnew2.toString()))
				{
					JOptionPane.showMessageDialog(this, "请确认两次密码相同");
					return;
				}
				if (pswdold.toString().equals(pswdnew2.toString()))
				{
					JOptionPane.showMessageDialog(this, "请不要使用旧密码");
					return;
				}
				acc.setAccountpassword(pswdnew1.toString());
				try
				{
					DAOFactory.getAccount().alter(acc);
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(this, "修改失败");
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(this, "修改成功");
				f.setEnabled(true);
				this.dispose();
			} else
			{
				JOptionPane.showMessageDialog(this, "原密码错误");
				return;
			}
		}
		if (arg0.getSource() == buttonCancel)
		{
			f.setEnabled(true);
			this.dispose();
		}
	}

}
