package cn.mldn.house.util.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.mldn.house.util.BeanOperate;
import cn.mldn.house.util.BeanValidate;
import cn.mldn.house.util.StringUtils;

import com.jspsmart.upload.SmartUpload;
 
/**
 * 定义一个负责处理所有用户请求的公共Servlet 这个Servlet负责操作分发、负责数据验证、分页实现 这个类必须有具体的子类操作
 */
@SuppressWarnings("serial")
public abstract class DispatcherServlet extends HttpServlet {
	private static final String PAGESBASENAME = "Pages";
	private static final String MESSAGESBASENAME = "Messages";
	public static final String DRIECTORY = "upload";
	// 准备进行Messages.properties文件读取的资源类
	private ResourceBundle messagesResource;
	// 准备进行Pages.properties文件读取的资源类
	private ResourceBundle pagesResource;
	private HttpServletRequest request; // 为随后进行其它操作准备
	private HttpServletResponse response;
	private HttpSession session;
	private SmartUpload smart; // 留给上传操作使用

	// 定义分页所需要的几个重要参数，这几个参数的设置由handleSplitPage()方法完成
	private Integer currentPage = 1;
	private Integer lineSize = 10 ;
	private String keyWord;
	private String column;

	/**
	 * 此方法留给一些程序初始化使用，因为在整个程序里面存在有资源文件， 将通过此部分读取资源文件
	 */
	@Override
	public void init() throws ServletException {
		// 所有的资源文件都会在初始化的时候准备好
		// 代码必须考虑到日后扩充的国际化应用，应该利用Locale类取得本地语言编码
		Locale loc = Locale.getDefault(); // 根据当前语言环境取得语言编码
		this.pagesResource = ResourceBundle.getBundle(PAGESBASENAME, loc);
		this.messagesResource = ResourceBundle.getBundle(MESSAGESBASENAME, loc);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request; // 将request对象保存
		this.response = response;
		this.session = request.getSession();
		String path = "errors.page"; // 默认的跳转路径
		this.handleParameter(); // 处理所有的请求参数

		// 得到路径：/MyProject/pages/back/admin/emp/EmpServlet/list
		String uri = request.getRequestURI(); // 取得用户的请求相对路径
		// 处理后路径：list
		String methodName = uri.substring(uri.lastIndexOf("/") + 1);
		String validateField = uri.substring(uri.lastIndexOf("/") + 1)
				+ "Validate";
		boolean flag = true;
		this.handleSplitPage(); // 处理分页
		try {
			try {
				Field field = this.getClass().getDeclaredField(validateField);
				field.setAccessible(true); // 取消封装
				flag = this.validate(field.get(this).toString()); // 保存验证结果
				if (flag) {
					// 根据指定的方法名称反射调用指定的方法
					Method met = this.getClass().getMethod(methodName);
					path = (String) met.invoke(this); // 反射调用方法
				}
			} catch (Exception e) {
				Method met = this.getClass().getMethod(methodName);
				path = (String) met.invoke(this); // 反射调用方法
				if ("json".equals(path)) {
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == false) {
			String backUrl = request.getHeader("referer");
			if (backUrl != null) {
				backUrl = backUrl.substring(backUrl.indexOf(request
						.getContextPath()) + request.getContextPath().length());
				request.setAttribute("path", backUrl);
				request.setAttribute("msg",
						this.messagesResource.getObject("data.error"));
				path = "forward.page";
			}
		}
		request.getRequestDispatcher(this.pagesResource.getString(path))
				.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * 得到保存的文件夹名称
	 * 
	 * @return
	 */
	public String getDirectory() {
		return DRIECTORY;
	}

	/**
	 * 得到保存的路径
	 * 
	 * @return
	 */
	public String getPath() {
		String path = super.getServletContext().getRealPath("/")
				+ File.separator + this.getDirectory() + File.separator;
		File file = new File(path);
		if (!file.exists()) { // 不存在
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 保存单个文件
	 * 
	 * @param fileName
	 */
	public void saveFile(String fileName) throws Exception {
		if (fileName == null) {
			return;
		}
		String filePath = this.getPath() + fileName;
		if (this.smart == null || this.smart.getFiles().getSize() == 0) {
			return;
		}
		if (this.smart.getFiles().getFile(0).getContentType().contains("image")) {
			this.smart.getFiles().getFile(0).saveAs(filePath);
		}
	}

	/**
	 * 保存多个文件
	 * 
	 * @param fileNames
	 *            生成的文件名称
	 * @throws Exception
	 */
	public void saveFile(List<String> fileNames) throws Exception {
		if (fileNames == null) {
			return;
		}
		Iterator<String> iter = fileNames.iterator();
		int foot = 0;
		while (iter.hasNext()) {
			String fileName = iter.next();
			String filePath = this.getPath() + fileName;
			if (this.smart.getFiles().getFile(foot).getContentType()
					.contains("image")) {
				this.smart.getFiles().getFile(foot).saveAs(filePath);
			}
			foot++; // 修改脚标
		}
	}

	/**
	 * 根据参数名称取得指定的参数内容，本方法区分了是封装表单还是不封装表单的操作
	 * 
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public String getParamValue(String paramName) {
		String contentType = this.request.getContentType();
		if (contentType == null) {
			return null;
		}
		if (contentType.contains("multipart/form-data")) { // 表单被封装了
			return this.smart.getRequest().getParameter(paramName);
		} else {
			return this.request.getParameter(paramName);
		}
	}

	/**
	 * 取得数组数据
	 * 
	 * @param paramName
	 * @return
	 */
	public String[] getParamValues(String paramName) {
		String contentType = this.request.getContentType();
		if (contentType == null) {
			return null;
		}
		if (contentType.contains("multipart/form-data")) { // 表单被封装了
			return this.smart.getRequest().getParameterValues(paramName);
		} else {
			return this.request.getParameterValues(paramName);
		}
	}

	/**
	 * 此处进行数据验证，这个验证是在数据接收完成之后进行的
	 * 
	 * @param property
	 * @return
	 */
	public boolean validate(String property) {
		BeanValidate bv = new BeanValidate();
		String result[] = property.split("\\|");
		for (int x = 0; x < result.length; x++) {
			String voName = result[x].substring(0, result[x].indexOf("."));
			try {
				Method voGetMet = this.getClass().getMethod(
						"get" + StringUtils.initcap(voName));
				bv.setObj(voGetMet.invoke(this));
				bv.setProperty(result[x]);
				bv.handleString(); // 验证
			} catch (Exception e) {
			}
		}
		// 现在有错误信息，应该将错误信息保存到页面输出，这应该使用request属性范围
		if (bv.getErrorMsg().size() > 0) {
			this.request.setAttribute("errMsg", bv.getErrorMsg());
		}
		return bv.getErrorMsg().size() == 0; // 没有错误记录
	}

	/**
	 * 自动为子类的VO对象设置请求内容，前提在子类之中必须提供有对应的getter方法
	 */
	@SuppressWarnings("unchecked")
	public void handleParameter() { // 处理所有的参数
		// application/x-www-form-urlencoded
		String contentType = this.request.getContentType();
		Enumeration<String> enu = null; // 取得对象的方式有两种
		if (contentType != null) {
			if (contentType.contains("multipart/form-data")) { // 表单被封装了
				try {
					this.smart = new SmartUpload();
					this.smart.initialize(super.getServletConfig(), request,
							response);
					this.smart.upload();
					enu = this.smart.getRequest().getParameterNames();
				} catch (Exception e) {
				}
			} else {
				enu = this.request.getParameterNames();
			}
			while (enu.hasMoreElements()) {
				String paramName = enu.nextElement();
				if (paramName.contains(".")) { // 现在是需要用户自动设置内容
					String voName = paramName.substring(0,
							paramName.indexOf("."));
					// 取得每一个子类Servlet之中存在的getVo对象()操作方法，目的是为了驱动BeanOperate操作
					try {
						Method voGetMet = this.getClass().getMethod(
								"get" + StringUtils.initcap(voName));
						BeanOperate bo = new BeanOperate(voGetMet.invoke(this),
								paramName);
						bo.handleString(); // 处理完成之后可以知道操作当前属性的对象以及属性的Field类
						// 区分是使用getParameter()还是使用getParameterValues()方法
						if (bo.getField().getType().getSimpleName()
								.contains("[]")) { // 表示数组
							bo.setValue(this.getParamValues(paramName));
						} else {
							bo.setValue(this.getParamValue(paramName));
						}
						bo.setFieldValue();// 处理内容的设置
					} catch (Exception e) {
					}
					// System.out.println("*** paramName = " + paramName +
					// "、paramValue = " + request.getParameter(paramName));
				}
			}
		}
	}

	/**
	 * 此方法负责处理所有的分页操作
	 */
	public void handleSplitPage() {
		try {
			currentPage = Integer.parseInt(request.getParameter("cp"));
		} catch (Exception e) {
		}
		try {
			lineSize = Integer.parseInt(request.getParameter("ls"));
		} catch (Exception e) {
		}
		column = request.getParameter("col");
		if (column == null) {
			column = this.getDefaultColumn();
		}
		keyWord = request.getParameter("kw");
		if (keyWord == null) {
			keyWord = "";
		} else { // 现在有内容
			if ("GET".equalsIgnoreCase(request.getMethod())) { // 为GET请求处理
				try {
					keyWord = URLDecoder.decode(keyWord, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("columnData", this.getColumnData());
		request.setAttribute("column", column);
		request.setAttribute("defaultColumn", this.getDefaultColumn());
		try {
			request.setAttribute("keyWord", URLEncoder.encode(keyWord, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置跳转到forward.jsp页面时的两个属性内容
	 * 
	 * @param pathKey
	 *            要跳转的路径的key内容
	 * @param msgKey
	 *            要提示的用户信息
	 */
	public void setPathAndMsg(String pathKey, String msgKey) {
		String path = this.pagesResource.getString(pathKey);
		String msg = MessageFormat.format(
				this.messagesResource.getString(msgKey), this.getObjectName());
		this.request.setAttribute("msg", msg);
		this.request.setAttribute("path", path);
	}

	/**
	 * 本方法的主要功能是生成图片文件的名称
	 * 
	 * @return
	 */
	public String createSingleFileName() {
		try {
			if (this.smart == null || this.smart.getFiles().getSize() == 0) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileName = UUID.randomUUID() + "."
				+ this.smart.getFiles().getFile(0).getFileExt();
		return fileName;
	}

	/**
	 * 同时生成多个图片的名字
	 * 
	 * @return
	 */
	public List<String> createMultiFileName() {
		try {
			if (this.smart == null || this.smart.getFiles().getSize() == 0) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> names = new ArrayList<String>();
		for (int x = 0; x < this.smart.getFiles().getCount(); x++) {
			names.add(UUID.randomUUID() + "."
					+ this.smart.getFiles().getFile(x).getFileExt());
		}
		return names;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpSession getSession() {
		return session;
	}

	public SmartUpload getSmart() {
		return smart;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getLineSize() {
		return lineSize;
	}

	public String getColumn() {
		return column;
	}

	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * 本方法的主要目的是为了Messages.properties里面的VO操作提供名称的
	 * 
	 * @return 由各个子类设置自己要进行提示的名称
	 */
	public abstract String getObjectName();

	/**
	 * 取得默认的分页列
	 * 
	 * @return
	 */
	public abstract String getDefaultColumn();

	/**
	 * 是取得模糊检索的搜索列的范围
	 * 
	 * @return
	 */
	public abstract String getColumnData();
}
