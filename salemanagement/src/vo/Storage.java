package vo;

public class Storage
{
	private String productname;
	private int productid;
	private String unit;
	private float price;
	private int downlimit;
	private int number;
	public Storage()
	{
		
	}
	public Storage(String productname, int productid, String unit, float price, int downlimit, int number)
	{
		super();
		this.productname = productname;
		this.productid = productid;
		this.unit = unit;
		this.price = price;
		this.downlimit = downlimit;
		this.number = number;
	}
	public String getProductname()
	{
		return productname;
	}
	public void setProductname(String productname)
	{
		this.productname = productname;
	}
	public int getProductid()
	{
		return productid;
	}
	public void setProductid(int productid)
	{
		this.productid = productid;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
	public int getDownlimit()
	{
		return downlimit;
	}
	public void setDownlimit(int downlimit)
	{
		this.downlimit = downlimit;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
}
