package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import dao.factory.DAOFactory;
import vo.Sale;

public class SaleManagement extends JDialog implements ActionListener
{
	private JTable table;
	private Object body[][];
	private String title[] = { "���۱��", "��Ʒ���", "��Ʒ����", "�˿�ID", "����ʱ��", "���۵ص�", "��������", "������" };
	List<Sale> sales;
	private JFrame f;
	private JButton buttonAdd, buttonDelete;
	private JTextField textCommodityid, textNumber, textMoney;
	private JTextField textCommodityname, textGuestid, textSellplace;
	JSpinner textSelltime;

	public SaleManagement(JFrame f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("���۹���");
		this.setSize(1100, 400);
		this.setLocationRelativeTo(null);
		this.setDropTarget(getDropTarget());
		this.setLayout(null);
		this.setResizable(false);
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
				// System.exit(0);
			}
		});

		JLabel labelCommodityid = new JLabel("��Ʒ���");
		labelCommodityid.setBounds(20, 20, 60, 25);
		JLabel labelCommodityname = new JLabel("��Ʒ����");
		labelCommodityname.setBounds(170, 20, 60, 25);
		JLabel labelGuestid = new JLabel("�˿�ID");
		labelGuestid.setBounds(320, 20, 60, 25);
		JLabel labelSelltime = new JLabel("����ʱ��");
		labelSelltime.setBounds(470, 20, 60, 25);
		JLabel labelSellplace = new JLabel("���۵ص�");
		labelSellplace.setBounds(620, 20, 60, 25);
		JLabel labelNumber = new JLabel("��������");
		labelNumber.setBounds(770, 20, 60, 25);
		JLabel labelMoney = new JLabel("������");
		labelMoney.setBounds(920, 20, 60, 25);

		textCommodityid = new JTextField();
		textCommodityid.setBounds(80, 20, 80, 25);
		textCommodityname = new JTextField();
		textCommodityname.setBounds(230, 20, 80, 25);
		textGuestid = new JTextField();
		textGuestid.setBounds(380, 20, 80, 25);

		SpinnerDateModel model1 = new SpinnerDateModel();
		model1.setCalendarField(Calendar.YEAR);
		textSelltime = new JSpinner(model1);
		textSelltime.setBounds(530, 20, 80, 25);
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(textSelltime, "yyyy-MM-dd");
		textSelltime.setEditor(editor1);

		textSellplace = new JTextField();
		textSellplace.setBounds(680, 20, 80, 25);
		textNumber = new JTextField();
		textNumber.setBounds(830, 20, 80, 25);
		textMoney = new JTextField();
		textMoney.setBounds(980, 20, 80, 25);

		try
		{
			sales = DAOFactory.getSale().findAll();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		body = new Object[sales.size()][title.length];
		Iterator<Sale> it = sales.iterator();
		int i = 0;
		while (it.hasNext())
		{
			Sale sales = it.next();
			body[i][0] = sales.getSaleid();
			body[i][1] = sales.getProductid();
			body[i][2] = sales.getProductname();
			body[i][3] = sales.getGuestid();
			body[i][4] = sales.getSelltime();
			body[i][5] = sales.getSellplace();
			body[i][6] = sales.getNumber();
			body[i][7] = sales.getMoney();
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
		sp.setBounds(0, 80, 1100, 320);
		cp.add(sp);

		buttonAdd = new JButton("���");
		buttonAdd.setBounds(320, 50, 100, 24);
		buttonDelete = new JButton("ɾ��");
		buttonDelete.setBounds(520, 50, 100, 24);
		buttonAdd.addActionListener(this);
		buttonDelete.addActionListener(this);

		cp.add(labelCommodityid);
		cp.add(labelSelltime);
		cp.add(labelNumber);
		cp.add(labelCommodityname);
		cp.add(labelGuestid);
		cp.add(labelSellplace);
		cp.add(labelMoney);
		cp.add(textCommodityid);
		cp.add(textCommodityname);
		cp.add(textSelltime);
		cp.add(textNumber);
		cp.add(textMoney);
		cp.add(textGuestid);
		cp.add(textSellplace);
		cp.add(buttonAdd);
		cp.add(buttonDelete);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == buttonAdd)
		{
			int saleid = 0;
			try
			{
				List<Sale> sales = DAOFactory.getSale().findAll();
				Iterator<Sale> saleIt = sales.iterator();
				while (saleIt.hasNext())
				{
					int num = saleIt.next().getSaleid();
					if (saleid < num)
					{
						saleid = num;
					}
				}
				saleid++;

				// ��Ʒ����������!!!!!
				if (null == DAOFactory.getProduct().findByProductid(Integer.parseInt(textCommodityid.getText())))
				{
					JOptionPane.showMessageDialog(this, "��Ʒ������");
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				Sale sale = new Sale(saleid, Integer.parseInt(textCommodityid.getText()), textCommodityname.getText(),
						Integer.parseInt(textGuestid.getText()), df.format(textSelltime.getValue()),
						textSellplace.getText(), Integer.parseInt(textNumber.getText()),
						Float.parseFloat(textMoney.getText()));
				DAOFactory.getSale().create(sale);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new SaleManagement(f);
			this.dispose();
		}
		if (e.getSource() == buttonDelete)
		{
			int row = table.getSelectedRow(); // �õ�ѡ�е���
			Sale sale = new Sale();
			sale.setSaleid((int) body[row][0]);
			try
			{
				if (DAOFactory.getSale().delete(sale))
				{
					this.dispose();
					new SaleManagement(f);
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
	}
	// public static void main(String a[]){
	// new SaleManagement(new JFrame());
	// }
}
