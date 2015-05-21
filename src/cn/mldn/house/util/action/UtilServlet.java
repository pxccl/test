package cn.mldn.house.util.action;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/utilServlet/*")
public class UtilServlet extends DispatcherServlet {
	
	public String getRand(){
		try {
			String code = (String) super.getSession().getAttribute("rand");
			super.getResponse().setCharacterEncoding("utf-8");
			super.getResponse().getWriter().print(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "json";
	}
	@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}

}
