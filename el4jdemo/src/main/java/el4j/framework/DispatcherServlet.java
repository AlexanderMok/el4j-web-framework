package el4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import el4j.framework.bean.BeanContainer;
import el4j.framework.bean.BeanFactory;
import el4j.framework.bean.Data;
import el4j.framework.context.application.Handler;
import el4j.framework.context.application.ModelAndView;
import el4j.framework.context.application.Param;
import el4j.framework.core.helper.ConfigHelper;
import el4j.framework.core.helper.ControllerHelper;
import el4j.framework.core.utils.JsonUtil;
import el4j.framework.core.utils.ParameterUtil;
import el4j.framework.core.utils.StringUtil;

/**
 * 请求转发器
 * a Servlet that dispatchers requests to 
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		// 初始化框架&应用
		// framework and application initialization
		FrameworkLoader.init();
		ServletContext sc = config.getServletContext();
		// 注册JSP的Servlet
		ServletRegistration jspServlet = sc.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册处理静态资源的Servlet
		ServletRegistration defaultServlet = sc.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 获取请求方法
		String requestMethod = req.getMethod().toLowerCase();
		// 请求路径url
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String requestPath = null;
		if (contextPath != null && contextPath.length() > 0)
		{
			requestPath = url.substring(contextPath.length());
		}
		// 获取处理处理这个请求的handler
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		// System.out.println(requestMethod + " " + requestPath);
		if (handler != null)
		{
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanContainer.getBean(controllerClass.getName());
			// 解析请求参数
			Param param = ParameterUtil.createParam(req);
			Object result;// 请求返回对象
			Method method = handler.getMethod();// 处理请求的方法
			if (param.isEmpty())
			{
				System.out.println("-----------------");
				result = BeanFactory.invokeMethod(controllerBean, method);
			}
			else
			{
				result = BeanFactory.invokeMethod(controllerBean, method, param);
			}
			if (result instanceof ModelAndView)
			{
				handleViewResult((ModelAndView) result, req, resp);
			}
			else
			{
				handleDataResult((Data<?>) result, resp);
			}
		}
	}

	/**
	 * 返回为JSP页面 
	 * @param view
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private static void handleViewResult(ModelAndView view, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException
	{
		String path = view.getPath();
		if (StringUtil.isNotEmpty(path))
		{
			if (path.startsWith("/"))
			{
				resp.sendRedirect(req.getContextPath() + path);
			}
			else
			{
				Map<String, Object> data = view.getmData();
				for (Map.Entry<String, Object> entry : data.entrySet())
				{
					req.setAttribute(entry.getKey(), entry.getValue());
				}
				// forward将页面响应转发到ConfigHelper.getAppJspPath() + path
				req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
			}
		}
	}

	/**
	 * 返回JSON数据 
	 * @param data
	 * @param resp
	 * @throws IOException
	 */
	private static void handleDataResult(Data<?> data, HttpServletResponse resp) throws IOException
	{
		Object model = data.getData();
		if (model != null)
		{
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			String json = JsonUtil.toJSON(model);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}

}
