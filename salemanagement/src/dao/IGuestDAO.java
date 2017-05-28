package dao;

import java.util.List;

import vo.Guest;

public interface IGuestDAO
{
	public boolean create(Guest Guest) throws Exception;

	public List<Guest> findAll() throws Exception;
	
	public Guest findByGuestname(String Guestname) throws Exception;
	
	public boolean alter(Guest Guest) throws Exception;
	
	public boolean delete(Guest Guest) throws Exception;
}
