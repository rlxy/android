package com.jxgy.contrl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.jxgy.dao.UserDao;
import com.jxgy.dao.impl.UserDaoImpl;
import com.jxgy.po.User;

public class UserServlet extends HttpServlet {

	private static final Map Map = null;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if ("login".equals(method)) {
			login(request, response);
		} else if ("addUser".equals(method)) {
			addUser(request, response);
		} else if ("getAll".equals(method)) {
			getAll(request, response);
		} else if ("delUserById".equals(method)) {
			delUserById(request, response);
		} else if ("addUser1".equals(method)) {
			addUser1(request, response);
		}

	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		UserDao dao = new UserDaoImpl();
		User user = dao.login(u);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void delUserById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		boolean result = dao.delUserById(id);
		if (result) {
			request.getRequestDispatcher("user?method=getAll").forward(request, response);
		}
	}

	protected void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String real_name = request.getParameter("real_name");
		Map map = new HashMap();
		map.put("real_name", real_name);

		UserDao dao = new UserDaoImpl();
		List<User> users = dao.getAll(map);
		request.setAttribute("users", users);
		request.setAttribute("real_name", real_name);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
		;
	}

	protected void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		String photo = request.getParameter("photo");
		user.setUsername(username);
		user.setPassword(password);
		user.setRealname(realname);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setTel(tel);
		user.setAddress(address);
		user.setType(type);
		user.setPhoto(photo);
		UserDao dao = new UserDaoImpl();
		boolean result = dao.addUser(user);
		if (result) {
			request.getRequestDispatcher("user?method=getAll").forward(request, response);
		}
	}

	protected void addUser1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		DiskFileItemFactory df = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(df);
		try {
			List<FileItem> items = upload.parseRequest(null);
			for (FileItem its : items) {
				if (its.isFormField()) {
					String name = its.getFieldName();
					if ("username".equals(name)) {
						user.setUsername(its.getString("utf-8"));
					} else if ("realname".equals(name)) {
						user.setRealname(its.getString("utf-8"));
					} else if ("password".equals(name)) {
						user.setPassword(its.getString("utf-8"));
					} else if ("sex".equals(name)) {
						user.setSex(its.getString("utf-8"));
					}

				} else {
					String fileName = its.getName();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					long currentTime = System.currentTimeMillis();
					String path = request.getRealPath("/fileupload");
					System.out.println(path);
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					File file = new File(path, currentTime + fileType);
					try {
						its.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
					user.setPhoto("fileupload/" + currentTime + fileType);
				}
			}
			UserDao dao = new UserDaoImpl();
			boolean res = dao.addUser(user);
			if (res) {
				response.sendRedirect("user?method=getAll");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
