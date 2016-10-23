package el4j.framework.core.helper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import el4j.framework.context.annotation.RequestMapping;
import el4j.framework.context.application.Handler;
import el4j.framework.context.application.Request;
import el4j.framework.core.utils.ArrayUtil;
import el4j.framework.core.utils.CollectionUtil;

public class ControllerHelper
{

	// 请求request与处理请求handler映射关系
	private static final Map<Request, Handler> RequestMap = new HashMap<>();

	static
	{
		ArrayList<Class<?>> controllerClasses = ClassHelper.getControllerClasses();
		if (CollectionUtil.isNotEmpty(controllerClasses))
		{
			initRequestMap(controllerClasses);
		}
	}

	private static void initRequestMap(ArrayList<Class<?>> controllerClasses)
	{
		for (Class<?> controllerClass : controllerClasses)
		{
			Method[] methods = controllerClass.getDeclaredMethods();
			if (ArrayUtil.isNotEmpty(methods))
			{
				for (Method method : methods)
				{
					// 带有RequestMapping注解的方法
					if (method.isAnnotationPresent(RequestMapping.class))
					{
						RequestMapping rm = method.getAnnotation(RequestMapping.class);
						Request request = new Request(rm.method(), rm.path());
						Handler handler = new Handler(controllerClass, method);
						RequestMap.put(request, handler);
					}
				}
			}
		}
	}

	/**
	 * 根据HTTP请求方法获取相应的handler
	 *
	 * @param requestMethod
	 * @param requestPath
	 * @return get request handler according to request method
	 */
	public static Handler getHandler(String requestMethod, String requestPath)
	{
		Request request = new Request(requestMethod, requestPath);
		return RequestMap.get(request);
	}
}
