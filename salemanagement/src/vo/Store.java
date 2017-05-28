package vo;

public class Store
{
	private int storeid;
	private int productid;
	private String productname;
	private float inprice;
	private int number;
	private String unit;
	public Store()
	{
		
	}
	public Store(int storeid, int productid, String productname, float inprice, int number, String unit)
	{
		super();
		this.storeid = storeid;
		this.productid = productid;
		this.productname = productname;
		this.inprice = inprice;
		this.number = number;
		this.unit = unit;
	}
	public int getStoreid()
	{
		return storeid;
	}
	public void setStoreid(int storeid)
	{
		this.storeid = storeid;
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
	public float getInprice()
	{
		return inprice;
	}
	public void setInprice(float inprice)
	{
		this.inprice = inprice;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
}
