package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

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

public class LoginRegister extends JDialog implements ActionListener
{
	private JButton buttonOk, buttonCancel, buttonCheck;
	private JTextField textAccountname;
	private JPasswordField textAccountpassword, textAccountpassword2;
	private JFrame f;

	LoginRegister(JFrame f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false); //ʹԭ�������޷����
		this.setSize(320, 300);
		this.setLocationRelativeTo(null);
		this.setTitle(" ע���˻�");
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				// super.windowClosing(e);
				f.setEnabled(true);
				dispose();
			}
		});
		this.setLayout(null);
		this.setResizable(false);
		JLabel labelTitle = new JLabel("ע����Ϣ");
		labelTitle.setBounds(120, 0, 200, 40);
		Font font = new Font("����", Font.BOLD, 20);
		labelTitle.setFont(font);

		JLabel labelAccountname = new JLabel("�û�����");
		labelAccountname.setBounds(50, 40, 100, 40);
		JLabel labelAccountpassword = new JLabel("���룺");
		labelAccountpassword.setBounds(50, 90, 100, 40);
		JLabel labelAccountpassword2 = new JLabel("����һ�����룺");
		labelAccountpassword2.setBounds(50, 140, 100, 40);

		textAccountname = new JTextField();
		textAccountname.setBounds(150, 52, 100, 20);
		textAccountname.setBorder(BorderFactory.createEtchedBorder());
		textAccountpassword = new JPasswordField();
		textAccountpassword.setBounds(150, 102, 100, 20);
		textAccountpassword.setBorder(BorderFactory.createEtchedBorder());
		textAccountpassword2 = new JPasswordField();
		textAccountpassword2.setBounds(150, 152, 100, 20);
		textAccountpassword2.setBorder(BorderFactory.createEtchedBorder());

		buttonOk = new JButton("ȷ��");
		buttonOk.setBounds(70, 220, 80, 25);
		buttonCancel = new JButton("ȡ��");
		buttonCancel.setBounds(160, 220, 80, 25);
		buttonCheck = new JButton("���");
		buttonCheck.setBounds(250, 52, 60, 19);
		buttonCheck.setContentAreaFilled(false);

		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
		buttonCheck.addActionListener(this);

		Container cp = this.getContentPane();
		cp.add(labelAccountname);
		cp.add(labelAccountpassword);
		cp.add(labelAccountpassword2);
		cp.add(labelTitle);
		cp.add(textAccountname);
		cp.add(textAccountpassword);
		cp.add(textAccountpassword2);
		cp.add(buttonOk);
		cp.add(buttonCancel);
		cp.add(buttonCheck);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == buttonOk)
		{
			//System.out.println("buttonOk");
			StringBuffer pswd1 = new StringBuffer(), pswd2 = new StringBuffer();
			int accountnumber = 0;
			char[] ch1 = textAccountpassword.getPassword();
			for (char i : ch1)
			{
				pswd1.append(i);
			}
			char[] ch2 = textAccountpassword2.getPassword();
			for (char i : ch2)
			{
				pswd2.append(i);
			}
			if (textAccountname.getText().length() < 1)
			{
				JOptionPane.showMessageDialog(this, "����ȷ�����û���");
				return;
			}
			if (ch1.length < 6 || ch2.length < 6)
			{
				JOptionPane.showMessageDialog(this, "�����벻����6λ������");
				return;
			}
			try
			{
				Account acc = DAOFactory.getAccount().findByAccountname(textAccountname.getText());
				if (acc != null)
				{
					JOptionPane.showMessageDialog(this, "�û����ظ�");
					return;
				}
				if (!pswd1.toString().equals(pswd2.toString()))
				{
					JOptionPane.showMessageDialog(this, "��ȷ������������ͬ");
					return;
				}
				List<Account> setsAccount = DAOFactory.getAccount().findAll();
				Iterator<Account> itAccount = setsAccount.iterator();
				while (itAccount.hasNext())
				{
					int num = itAccount.next().getAccountid();
					if (accountnumber < num)
					{
						accountnumber = num;
					}
				}
				accountnumber++; //accountid����

				acc = new Account(accountnumber, textAccountname.getText(), pswd1.toString());
				DAOFactory.getAccount().create(acc);

				JOptionPane.showMessageDialog(this, "����ɹ�");
				f.setEnabled(true);
				this.dispose();
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "����δ֪���󣬲���ʧ��");
				e1.printStackTrace();
			}
		}
		if (e.getSource() == buttonCancel)
		{
			//System.out.println("buttonCancel");
			f.setEnabled(true);
			this.dispose();
		}
		if (e.getSource() == buttonCheck)
		{
			//System.out.println("buttonCheck");
			Account acc;
			try
			{
				acc = DAOFactory.getAccount().findByAccountname(textAccountname.getText());
				if (acc != null)
				{
					JOptionPane.showMessageDialog(this, "�û����ظ�");
					return;
				} else
				{
					JOptionPane.showMessageDialog(this, "����ʹ��");
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
