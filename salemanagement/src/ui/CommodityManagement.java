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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.factory.DAOFactory;
import vo.Product;

public class CommodityManagement extends JDialog implements ActionListener
{
	private JFrame f;
	private JButton buttonRefresh, buttonAdd, buttonCancel;
	private JTable table;
	private Object body[][];
	private String title[] = { "商品编号", "商品名称", "生产日期", "进货价格", "销售价格", "保质期", "单位", "进货日期" };
	List<Product> comms;

	CommodityManagement(JFrame f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("商品信息");
		this.setBounds(400, 100, 800, 400);
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
			comms = DAOFactory.getProduct().findAll();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		body = new Object[comms.size()][title.length];
		Iterator<Product> it = comms.iterator();
		int i = 0;
		while (it.hasNext())
		{
			Product comms = it.next();
			body[i][0] = comms.getProductid();
			body[i][1] = comms.getProductname();
			body[i][2] = comms.getProducedate();
			body[i][3] = comms.getInprice();
			body[i][4] = comms.getSaleprice();
			body[i][5] = comms.getStoragetime();
			body[i][6] = comms.getUnit();
			body[i][7] = comms.getIndate();
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
		sp.setBounds(0, 0, 800, 320);
		cp.add(sp);

		buttonRefresh = new JButton("刷新");
		buttonRefresh.setBounds(250, 330, 80, 30);
		buttonAdd = new JButton("添加");
		buttonAdd.setBounds(360, 330, 80, 30);
		buttonCancel = new JButton("取消");
		buttonCancel.setBounds(470, 330, 80, 30);
		buttonRefresh.addActionListener(this);
		buttonAdd.addActionListener(this);
		buttonCancel.addActionListener(this);

		cp.add(buttonRefresh);
		cp.add(buttonAdd);
		cp.add(buttonCancel);
		cp.add(allPanel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		if (arg0.getSource() == buttonRefresh)
		{
			this.dispose();
			new CommodityManagement(f);
		}
		if (arg0.getSource() == buttonAdd)
		{
			 new CommodityAdd(this);
		}
		if (arg0.getSource() == buttonCancel)
		{
			f.setEnabled(true);
			this.dispose();
		}
	}
}
