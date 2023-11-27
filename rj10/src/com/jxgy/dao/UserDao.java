package com.jxgy.dao;

import java.util.List;
import java.util.Map;

import com.jxgy.po.User;


public interface UserDao {
	public User login(User u);
	public List<User> getAll(Map map);
	public boolean addUser(User user);
	public boolean delUserById(int id);
	

}
