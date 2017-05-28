package vo;

public class Product
{
	private int productid;
	private String productname;
	private String producedate;
	private float inprice;
	private float saleprice;
	private int storagetime;
	private String unit;
	private String indate;
	public Product()
	{
		
	}
	public Product(int productid, String productname, String producedate, float inprice, float saleprice,
			int storagetime, String unit, String indate)
	{
		super();
		this.productid = productid;
		this.productname = productname;
		this.producedate = producedate;
		this.inprice = inprice;
		this.saleprice = saleprice;
		this.storagetime = storagetime;
		this.unit = unit;
		this.indate = indate;
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
	public String getProducedate()
	{
		return producedate;
	}
	public void setProducedate(String producedate)
	{
		this.producedate = producedate;
	}
	public float getInprice()
	{
		return inprice;
	}
	public void setInprice(float inprice)
	{
		this.inprice = inprice;
	}
	public float getSaleprice()
	{
		return saleprice;
	}
	public void setSaleprice(float saleprice)
	{
		this.saleprice = saleprice;
	}
	public int getStoragetime()
	{
		return storagetime;
	}
	public void setStoragetime(int storagetime)
	{
		this.storagetime = storagetime;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public String getIndate()
	{
		return indate;
	}
	public void setIndate(String indate)
	{
		this.indate = indate;
	}
}
