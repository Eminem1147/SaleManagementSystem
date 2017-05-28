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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.factory.DAOFactory;
import vo.Guest;

public class GuestAdd extends JDialog implements ActionListener
{
	private JDialog f;
	private JButton buttonAdd, buttonCancel;
	private JTextField textGuestid, textGuestname, textGuestaddress, textPhonenumber;

	public GuestAdd(JDialog f)
	{
		super(f);
		this.f = f;
		f.setEnabled(false);
		this.setTitle("顾客添加");
		this.setSize(310, 210);
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
		JLabel labelGuestid = new JLabel("顾客ID：");
		labelGuestid.setBounds(10, 10, 100, 25);
		JLabel labelGuestname = new JLabel("顾客姓名：");
		labelGuestname.setBounds(10, 40, 100, 25);
		JLabel labelGuestaddress = new JLabel("顾客地址：");
		labelGuestaddress.setBounds(10, 70, 100, 25);
		JLabel labelPhonenumber = new JLabel("手机号码：");
		labelPhonenumber.setBounds(10, 100, 100, 25);

		textGuestid = new JTextField();
		textGuestid.setBounds(100, 10, 190, 25);
		textGuestname = new JTextField();
		textGuestname.setBounds(100, 40, 190, 25);
		textGuestaddress = new JTextField();
		textGuestaddress.setBounds(100, 70, 190, 25);
		textPhonenumber = new JTextField();
		textPhonenumber.setBounds(100, 100, 190, 25);

		buttonAdd = new JButton("添加");
		buttonAdd.setBounds(30, 140, 100, 30);
		buttonCancel = new JButton("取消");
		buttonCancel.setBounds(180, 140, 100, 30);

		buttonAdd.addActionListener(this);
		buttonCancel.addActionListener(this);

		cp.add(labelGuestid);
		cp.add(labelGuestname);
		cp.add(labelGuestaddress);
		cp.add(labelPhonenumber);

		cp.add(textGuestid);
		cp.add(textGuestname);
		cp.add(textGuestaddress);
		cp.add(textPhonenumber);
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
				List<Guest> coms = DAOFactory.getGuest().findAll();
				Iterator<Guest> comIt = coms.iterator();
				while (comIt.hasNext())
				{
					int num = comIt.next().getGuestid();
					if (commodityid < num)
					{
						commodityid = num;
					}
				}
				commodityid++;
				Guest com = new Guest(Integer.parseInt(textGuestid.getText()), textGuestname.getText(),
						textGuestaddress.getText(), textPhonenumber.getText());
				DAOFactory.getGuest().create(com);
				JOptionPane.showMessageDialog(this, "添加成功");
				 f.setEnabled(true);
				 this.dispose();
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
