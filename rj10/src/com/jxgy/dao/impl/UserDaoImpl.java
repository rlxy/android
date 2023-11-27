package com.jxgy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jxgy.dao.UserDao;
import com.jxgy.po.User;
import com.jxgy.util.DbUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(User u) {	
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			StringBuffer sql=new StringBuffer();
			User user=null;
			conn=DbUtil.getConnection();
			sql.append("select*from t10_user where username=? and password=?");
			try {
			pstm=conn.prepareStatement(sql.toString());
			pstm.setString(1,u.getUsername());
			pstm.setString(2, u.getPassword());
			rs = pstm.executeQuery();
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("address"));
				user.setType(rs.getString("type"));
				user.setIf_valid(rs.getString("if_valid"));	
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return user;
			}
	@Override
	public List<User> getAll(Map map) {	
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			StringBuffer sql=new StringBuffer();
			List<User> users=new ArrayList<User>();
			User user=null;
			
			try {
				conn=DbUtil.getConnection();
				sql.append("select*from t10_user  where 1=1 ");
				if(map.get("real_name")!=null && !"".equals(map.get("real_name"))) {
					sql.append("and realname like ?");
				}
				int i=1;
			pstm=conn.prepareStatement(sql.toString());
			if(map.get("real_name")!=null && !"".equals(map.get("real_name"))) {
				pstm.setString(i++, "%"+map.get("real_name").toString()+"%");
			}
			rs = pstm.executeQuery();
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setBirthday(rs.getString("birthday"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("address"));
				user.setType(rs.getString("type"));
				user.setIf_valid(rs.getString("if_valid"));	
				user.setPhoto(rs.getString("photo"));
				users.add(user);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return  users;
			}

	@Override
	public boolean addUser(User user) {
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer();
		boolean result=true;
		conn=DbUtil.getConnection();
		sql.append("insert into t10_user(username,realname,password,sex,birthday,tel,photo)values");
		sql.append("(?,?,?,?,?,?,?)");
		try {
		pstm=conn.prepareStatement(sql.toString());
		
		pstm.setString(1,user.getUsername());
		pstm.setString(2,user.getRealname());
		pstm.setString(3,user.getPassword());
		pstm.setString(4,user.getSex());
		pstm.setString(5,user.getBirthday());
		pstm.setString(6,user.getRealname());
		pstm.setString(7,user.getPhoto());
		
		pstm.executeUpdate();
		
		}catch (Exception e) {
			result =false;
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public boolean delUserById(int id) {
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer();
		boolean result=true;
		try {
			conn=DbUtil.getConnection();
			sql.append("delete  from t10_user where id=?");
			pstm=conn.prepareStatement(sql.toString());
			pstm.setInt(1,id);
			pstm.executeUpdate();
			
			}catch (Exception e) {
				result =false;
				e.printStackTrace();
			}
			return result;
	}
			

}
