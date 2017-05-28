package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.factory.DAOFactory;
import vo.Account;

public class UserManagement extends JDialog implements ActionListener
{
	private JFrame f;
	private JTable table;
	private Object body[][];
	private String title[] = { "��¼��", "��¼��", "��¼����" };
	private List<Account> accs;
	private JButton buttonPswdinit, buttonDelete, buttonCancel;

	public UserManagement(JFrame f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("�û�����");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDropTarget(getDropTarget());
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel allPanel = new BgPanel();
		allPanel.setLayout(null);
		allPanel.setBounds(0, 0, 800, 400);
		
		Container cp = this.getContentPane();
		// this.setModalityType(Dialog.ModalityType.MODELESS);
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
		try
		{
			accs = DAOFactory.getAccount().findAll();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		body = new Object[accs.size()][title.length];
		Iterator<Account> it = accs.iterator();
		int i = 0;
		while (it.hasNext())
		{
			Account acc = it.next();
			body[i][0] = acc.getAccountid();
			body[i][1] = acc.getAccountname();
			body[i][2] = acc.getAccountpassword();
			i++;
		}

		table = new JTable(body, title)
		{// ���Ʊ�񲻿��Ա༭��������ѡ��
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 600, 320);
		cp.add(sp);

		buttonPswdinit = new JButton("��������");
		buttonPswdinit.setBounds(100, 330, 100, 30);
		buttonDelete = new JButton("ɾ���û�");
		buttonDelete.setBounds(250, 330, 100, 30);
		buttonCancel = new JButton("ȡ��");
		buttonCancel.setBounds(400, 330, 100, 30);
		buttonPswdinit.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonCancel.addActionListener(this);
		cp.add(buttonPswdinit);
		cp.add(buttonDelete);
		cp.add(buttonCancel);
		
		cp.add(allPanel);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

		if (e.getSource() == buttonPswdinit)
		{
			int row = table.getSelectedRow(); //�õ�ѡ�е���

			Account acc = new Account((int) body[row][0], (String) body[row][1], "");
			try
			{
				if (DAOFactory.getAccount().alter(acc))
				{
					body[row][2] = "";
					table.repaint();
					JOptionPane.showMessageDialog(this, "��ʼ���ɹ�");
					return;
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "��ʼ��ʧ��");
				e1.printStackTrace();
			}

		}
		if (e.getSource() == buttonDelete)
		{
			int row = table.getSelectedRow(); //�õ�ѡ�е���

			Account acc = new Account((int) body[row][0], (String) body[row][1], (String) body[row][2]);
			try
			{
				if (DAOFactory.getAccount().delete(acc))
				{
					this.dispose();
					new UserManagement(f);
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
					return;
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "ɾ��ʧ��");
				e1.printStackTrace();
			}

		}
		if (e.getSource() == buttonCancel)
		{
			f.setEnabled(true);
			this.dispose();
			return;
		}
	}

}
