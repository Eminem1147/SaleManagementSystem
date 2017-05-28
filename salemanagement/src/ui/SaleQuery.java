package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dbc.DatabaseConnection;

public class SaleQuery extends JDialog implements ActionListener
{
	private String dbnames[] = { "account", "product", "guest", "sale", "store", "storage" };
	private String dbnames_cn[] = { "�˻���Ϣ", "��Ʒ��Ϣ", "�˿���Ϣ", "������Ϣ", "�����Ϣ", "�����Ϣ" };
	String title[][] = { { "�˻����", "�˻�����", "�˻�����" }, { "��Ʒ���", "��Ʒ����", "��������", "������", "���ۼ�", "������", "��λ", "��������" },
			{ "�˿ͱ��", "�˿�����", "�˿͵�ַ", "�ֻ�����" }, { "���۱��", "��Ʒ���", "��Ʒ����", "�˿ͱ��", "����ʱ��", "���۵ص�", "����", "����" },
			{ "�������", "��Ʒ���", "��Ʒ����", "������", "����", "��λ" }, { "��Ʒ����", "��Ʒ���", "��λ", "����", "�������", "����" }, };
	boolean textQuote[][] = { { false, true, true }, { false, true, true, false, false, false, true, true },
			{ false, true, true, true }, { false, false, true, false, true, true, false, false },
			{ false, false, true, false, false, true }, { true, false, true, false, false, false } };
	private JButton buttonOk;
	private JTextField textSearch;
	private Connection con;
	private JComboBox boxDb, boxCol;
	private JTable table;
	private JScrollPane sp;
	DefaultTableModel tableModel;
	private JFrame f;

	public SaleQuery(JFrame f)
	{

		super(f);
		this.f = f;
		f.setEnabled(false);		
		this.setTitle("���۲�ѯ");
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setDropTarget(getDropTarget());
		this.setLayout(null);
		this.setResizable(false);

		Container cp = this.getContentPane();
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				// super.windowClosing(e);
				f.setEnabled(true);
				dispose();
				try
				{
					con.close();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.exit(0);
			}
		});
		try
		{
			con = new DatabaseConnection().getConnection();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boxDb = new JComboBox(dbnames_cn);
		boxDb.setBounds(100, 20, 100, 25);
		boxCol = new JComboBox(title[0]);
		boxCol.setBounds(250, 20, 100, 25);
		cp.add(boxDb);
		cp.add(boxCol);
		boxDb.addActionListener(this);

		textSearch = new JTextField();
		textSearch.setBounds(400, 20, 100, 25);
		cp.add(textSearch);

		buttonOk = new JButton("��ѯ");
		buttonOk.setBounds(550, 20, 100, 25);
		buttonOk.addActionListener(this);
		cp.add(buttonOk);

		table = new JTable()
		{// ���Ʊ�񲻿��Ա༭��������ѡ��
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		sp = new JScrollPane(table);
		sp.setBounds(0, 60, 800, 580);
		cp.add(sp);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == this.boxDb)
		{
			boxCol.removeAllItems();
			int k = boxDb.getSelectedIndex();
			for (String i : title[k])
			{
				boxCol.addItem(i);
			}
		}
		if (e.getSource() == this.buttonOk)
		{
			int db = boxDb.getSelectedIndex();
			int col = boxCol.getSelectedIndex();
			String sql = "SELECT * FROM " + dbnames[db] + ";";
			try
			{
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				ResultSetMetaData mtdt = rs.getMetaData();
				String columName = mtdt.getColumnName(col + 1);
				String text = textSearch.getText();
				if (text.equals(""))
				{
					sql = "SELECT * FROM " + dbnames[db] + ";";
				} else
				{
					if (textQuote[db][col])
					{
						text = "'%" + text + "%'";
					}
					sql = "SELECT * FROM " + dbnames[db] + " where " + columName + " like " + text + ";";
				}

				rs = stat.executeQuery(sql);
				mtdt = rs.getMetaData();
				tableModel = new DefaultTableModel();
				int colnum = mtdt.getColumnCount();
				for (int i = 0; i < colnum; i++)
				{
					tableModel.addColumn(title[db][i]);
				}
				String[] columns = new String[colnum];
				while (rs.next())
				{

					for (int i = 1; i <= colnum; i++)
					{
						columns[i - 1] = rs.getString(i);
					}
					tableModel.addRow(columns);
				}
				table.setModel(tableModel);

				// ����ĳһ�жԱ���������!!!!
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
				table.setRowSorter(sorter);

			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}