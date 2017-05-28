package vo;

public class Sale
{
	private int saleid;
	private int productid;
	private String productname;
	private int guestid;
	private String selltime;
	private String sellplace;
	private int number;
	private float money;
	public Sale()
	{
		
	}
	public Sale(int saleid, int productid, String productname, int guestid, String selltime, String sellplace,
			int number, float money)
	{
		super();
		this.saleid = saleid;
		this.productid = productid;
		this.productname = productname;
		this.guestid = guestid;
		this.selltime = selltime;
		this.sellplace = sellplace;
		this.number = number;
		this.money = money;
	}
	public int getSaleid()
	{
		return saleid;
	}
	public void setSaleid(int saleid)
	{
		this.saleid = saleid;
	}
	public int getProductid()
	{
		return productid;
	}
	public void setProductid(int productid)
	{
		this.productid = productid;
	}
	public String getProductname()
	{
		return productname;
	}
	public void setProductname(String productname)
	{
		this.productname = productname;
	}
	public int getGuestid()
	{
		return guestid;
	}
	public void setGuestid(int guestid)
	{
		this.guestid = guestid;
	}
	public String getSelltime()
	{
		return selltime;
	}
	public void setSelltime(String selltime)
	{
		this.selltime = selltime;
	}
	public String getSellplace()
	{
		return sellplace;
	}
	public void setSellplace(String sellplace)
	{
		this.sellplace = sellplace;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public float getMoney()
	{
		return money;
	}
	public void setMoney(float money)
	{
		this.money = money;
	}
	
}
