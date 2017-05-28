package vo;

public class Guest
{
	private int guestid;
	private String guestname;
	private String guestaddress;
	private String phonenumber;
	public Guest()
	{
		
	}
	public Guest(int guestid, String guestname, String guestaddress, String phonenumber)
	{
		super();
		this.guestid = guestid;
		this.guestname = guestname;
		this.guestaddress = guestaddress;
		this.phonenumber = phonenumber;
	}
	public int getGuestid()
	{
		return guestid;
	}
	public void setGuestid(int guestid)
	{
		this.guestid = guestid;
	}
	public String getGuestname()
	{
		return guestname;
	}
	public void setGuestname(String guestname)
	{
		this.guestname = guestname;
	}
	public String getGuestaddress()
	{
		return guestaddress;
	}
	public void setGuestaddress(String guestaddress)
	{
		this.guestaddress = guestaddress;
	}
	public String getPhonenumber()
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber = phonenumber;
	}
}
