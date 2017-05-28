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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import dao.factory.DAOFactory;
import vo.Product;

public class CommodityAdd extends JDialog implements ActionListener
{
	private JDialog f;
	private JButton buttonAdd, buttonCancel;
	private JTextField textCommodityname, textUnit, textInprice, textSaleprice, textStoragedate;
	JSpinner textProducedate, textIndate;

	public CommodityAdd(JDialog f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("商品入库管理");
		this.setSize(310, 310);
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
			}
		});
		JLabel labelCommodityname = new JLabel("商品名称：");
		labelCommodityname.setBounds(10, 10, 100, 25);
		JLabel labelUnit = new JLabel("单位：");
		labelUnit.setBounds(10, 40, 100, 25);
		JLabel labelInprice = new JLabel("进货价：");
		labelInprice.setBounds(10, 70, 100, 25);
		JLabel labelSaleprice = new JLabel("销售价：");
		labelSaleprice.setBounds(10, 100, 100, 25);
		JLabel labelStoragedate = new JLabel("有效期（天数）：");
		labelStoragedate.setBounds(10, 130, 100, 25);
		JLabel labelProducedate = new JLabel("生产时间：");
		labelProducedate.setBounds(10, 160, 100, 25);
		JLabel labelIndate = new JLabel("入库时间：");
		labelIndate.setBounds(10, 190, 100, 25);

		textCommodityname = new JTextField();
		textCommodityname.setBounds(100, 10, 190, 25);
		textUnit = new JTextField();
		textUnit.setBounds(100, 40, 190, 25);
		textInprice = new JTextField();
		textInprice.setBounds(100, 70, 190, 25);
		textSaleprice = new JTextField();
		textSaleprice.setBounds(100, 100, 190, 25);
		textStoragedate = new JTextField();
		textStoragedate.setBounds(100, 130, 190, 25);
		SpinnerDateModel model1 = new SpinnerDateModel();
		model1.setCalendarField(Calendar.YEAR);
		SpinnerDateModel model2 = new SpinnerDateModel();
		model2.setCalendarField(Calendar.YEAR);
		textProducedate = new JSpinner(model1);
		textProducedate.setBounds(100, 160, 190, 25);
		textIndate = new JSpinner(model2);
		textIndate.setBounds(100, 190, 190, 25);
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(textProducedate, "yyyy-MM-dd HH:mm:ss");
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(textIndate, "yyyy-MM-dd");
		textProducedate.setEditor(editor1);
		textIndate.setEditor(editor2);

		buttonAdd = new JButton("添加");
		buttonAdd.setBounds(30, 230, 100, 30);
		buttonCancel = new JButton("取消");
		buttonCancel.setBounds(180, 230, 100, 30);

		buttonAdd.addActionListener(this);
		buttonCancel.addActionListener(this);

		cp.add(labelCommodityname);
		cp.add(labelIndate);
		cp.add(labelProducedate);
		cp.add(labelStoragedate);
		cp.add(labelSaleprice);
		cp.add(labelInprice);
		cp.add(labelUnit);

		cp.add(textCommodityname);
		cp.add(textIndate);
		cp.add(textProducedate);
		cp.add(textStoragedate);
		cp.add(textSaleprice);
		cp.add(textInprice);
		cp.add(textUnit);
		cp.add(buttonAdd);
		cp.add(buttonCancel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == buttonAdd)
		{
			try
			{
				int commodityid = 0;
				List<Product> coms = DAOFactory.getProduct().findAll();
				Iterator<Product> comIt = coms.iterator();
				while (comIt.hasNext())
				{
					int num = comIt.next().getProductid();
					if (commodityid < num)
					{
						commodityid = num;
					}
				}
				commodityid++;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Product com = new Product(commodityid, textCommodityname.getText(),
						df2.format(textProducedate.getValue()), Float.parseFloat(textInprice.getText()),
						Float.parseFloat(textSaleprice.getText()), Integer.parseInt(textStoragedate.getText()),
						textUnit.getText(), df.format(textIndate.getValue()));
				DAOFactory.getProduct().create(com);
				JOptionPane.showMessageDialog(this, "添加成功");
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "异常");
				e1.printStackTrace();
			}
		}
		if (e.getSource() == buttonCancel)
		{
			f.setEnabled(true);
			this.dispose();
		}
	}
}
