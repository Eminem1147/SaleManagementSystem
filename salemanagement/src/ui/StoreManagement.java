package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.factory.DAOFactory;
import vo.Store;

public class StoreManagement extends JDialog implements ActionListener
{
	private JTable table;
	private Object body[][];
	private String title[] = { "入库编号", "商品编号", "商品名称", "单价", "数量", "单位" };
	List<Store> store;
	private JFrame f;
	private JButton buttonAdd, buttonDelete;
	private JTextField textUnit, textCommodityid, textNumber, textMoney;

	public StoreManagement(JFrame f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("进货管理");
		this.setSize(800, 400);
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

		// Label
		JLabel labelCommodityid = new JLabel("商品编号：");
		labelCommodityid.setBounds(20, 20, 80, 25);
		JLabel labelMoney = new JLabel("单价：");
		labelMoney.setBounds(210, 20, 60, 25);
		JLabel labelNumber = new JLabel("数量：");
		labelNumber.setBounds(380, 20, 60, 25);
		JLabel labelUnit = new JLabel("单位：");
		labelUnit.setBounds(570, 20, 60, 25);

		// Text
		textCommodityid = new JTextField();
		textCommodityid.setBounds(90, 20, 80, 25);
		textMoney = new JTextField();
		textMoney.setBounds(260, 20, 80, 25);
		textNumber = new JTextField();
		textNumber.setBounds(440, 20, 80, 25);
		textUnit = new JTextField();
		textUnit.setBounds(630, 20, 80, 25);

		try
		{
			store = DAOFactory.getStore().findAll();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		body = new Object[store.size()][title.length];
		Iterator<Store> it = store.iterator();
		int i = 0;
		while (it.hasNext())
		{
			Store store = it.next();
			body[i][0] = store.getStoreid();
			body[i][1] = store.getProductid();
			body[i][2] = store.getProductname();
			body[i][3] = store.getInprice();
			body[i][4] = store.getNumber();
			body[i][5] = store.getUnit();
			i++;
		}

		table = new JTable(body, title)
		{// 控制表格不可以编辑，但可以选中
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 100, 800, 320);
		cp.add(sp);

		buttonAdd = new JButton("添加");
		buttonAdd.setBounds(230, 60, 100, 24);
		buttonDelete = new JButton("删除");
		buttonDelete.setBounds(420, 60, 100, 24);
		buttonAdd.addActionListener(this);
		buttonDelete.addActionListener(this);
		cp.add(labelCommodityid);
		cp.add(labelNumber);
		cp.add(labelMoney);
		cp.add(labelUnit);
		cp.add(textCommodityid);
		cp.add(textNumber);
		cp.add(textMoney);
		cp.add(textUnit);
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
			int storeid = 0;
			try
			{
				List<Store> stores = DAOFactory.getStore().findAll();
				Iterator<Store> storeIt = stores.iterator();
				while (storeIt.hasNext())
				{
					int num = storeIt.next().getStoreid();
					if (storeid < num)
					{
						storeid = num;
					}
				}
				storeid++;

				if (null == DAOFactory.getProduct().findByProductid(Integer.parseInt(textCommodityid.getText())))
				{
					JOptionPane.showMessageDialog(this, "商品不存在");
					return;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				Store store = new Store(storeid, Integer.parseInt(textCommodityid.getText()),
						DAOFactory.getProduct().findByProductid(Integer.parseInt(textCommodityid.getText()))
								.getProductname(),
						Float.parseFloat(textMoney.getText()), Integer.parseInt(textNumber.getText()),
						textUnit.getText());
				DAOFactory.getStore().create(store);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new StoreManagement(f);
			this.dispose();

		}
		if (e.getSource() == buttonDelete)
		{
			int row = table.getSelectedRow();
			Store store = new Store();
			store.setStoreid((int) body[row][0]);
			try
			{
				if (DAOFactory.getStore().delete(store))
				{
					this.dispose();
					new StoreManagement(f);
					JOptionPane.showMessageDialog(this, "删除成功");
					return;
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "删除失败");
				e1.printStackTrace();
			}
		}
	}
}
