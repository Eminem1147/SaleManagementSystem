package vo;

public class Account
{
	private int accountid;
	private String accountname;
	private String accountpassword;
	public Account()
	{
		
	}
	public Account(int accountid, String accountname, String accountpassword)
	{
		super();
		this.accountid = accountid;
		this.accountname = accountname;
		this.accountpassword = accountpassword;
	}
	public int getAccountid()
	{
		return accountid;
	}
	public void setAccountid(int accountid)
	{
		this.accountid = accountid;
	}
	public String getAccountname()
	{
		return accountname;
	}
	public void setAccountname(String accountname)
	{
		this.accountname = accountname;
	}
	public String getAccountpassword()
	{
		return accountpassword;
	}
	public void setAccountpassword(String accountpassword)
	{
		this.accountpassword = accountpassword;
	}
}
