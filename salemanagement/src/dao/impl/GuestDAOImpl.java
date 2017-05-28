package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IGuestDAO;
import vo.Guest;

public class GuestDAOImpl implements IGuestDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public GuestDAOImpl(Connection con)
	{
		this.con = con;
	}

	@Override
	public boolean create(Guest Guest) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO guest VALUES(?,?,?,?);";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Guest.getGuestid());
		stat.setString(2, Guest.getGuestname());
		stat.setString(3, Guest.getGuestaddress());
		stat.setString(4, Guest.getPhonenumber());
		int update = stat.executeUpdate();
		if (update > 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public List<Guest> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT guestid, guestname, guestaddress, phonenumber FROM guest;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Guest Guest = null;
		List<Guest> list = new ArrayList<Guest>();
		while (rs.next())
		{
			Guest = new Guest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			list.add(Guest);
		}
		return list;
	}

	@Override
	public Guest findByGuestname(String Guestname) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT guestid, guestname, guestaddress, phonenumber FROM guest WHERE guestname=?;";
		stat = con.prepareStatement(sql);
		stat.setString(1, Guestname);
		ResultSet rs = stat.executeQuery();
		Guest Guest = null;
		if (rs.next())
		{
			Guest = new Guest();
			Guest.setGuestid(rs.getInt(1));
			Guest.setGuestname(rs.getString(2));
			Guest.setGuestaddress(rs.getString(3));
			Guest.setPhonenumber(rs.getString(4));
		}
		return Guest;
	}

	@Override
	public boolean alter(Guest Guest) throws Exception
	{
		// TODO Auto-generated method stub
		Guest acc = findByGuestname(Guest.getGuestname());
		if (acc == null)
			return false;
		else
		{
			String sql = "update guest set guestname=?, guestaddress=?, phonenumber=? where guestid=?;";
			stat = con.prepareStatement(sql);
			stat.setString(1, Guest.getGuestname());
			stat.setString(2, Guest.getGuestaddress());
			stat.setString(3, Guest.getPhonenumber());
			stat.setInt(4, Guest.getGuestid());
			int update = stat.executeUpdate();
			if (update > 0)
			{
				return true;
			} else
			{
				return false;
			}

		}
	}

	@Override
	public boolean delete(Guest Guest) throws Exception
	{
		// TODO Auto-generated method stub
		Guest acc = findByGuestname(Guest.getGuestname());
		if (acc == null)
			return false;
		else
		{
			String sql = "delete from guest where guestid=?;";
			stat = con.prepareStatement(sql);
			stat.setInt(1, Guest.getGuestid());
			int update = stat.executeUpdate();
			System.out.println(update);
			if (update > 0)
			{
				return true;
			} else
			{
				return false;
			}

		}
	}

}
