package cn.mldn.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.house.dbc.DatabaseConnection;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/test")
public class TestServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Connection connection = new DatabaseConnection().getConnection();
			String sql = "insert into role (title,note)values(?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "abc");
			pStatement.setString(2, "def");
			pStatement.executeUpdate();
			ResultSet rSet = pStatement.getGeneratedKeys();
			while (rSet.next()) {
				System.out.println(rSet.getInt(1));
			}
//			pStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
