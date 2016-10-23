package el4j.framework.context.application;

import java.lang.reflect.Method;

/**
 * 封装RequestMapping信息
 */
public class Handler
{

	private Class<?> controllerClass;

	private Method method;

	public Handler(Class<?> controllerClass, Method method)
	{
		this.controllerClass = controllerClass;
		this.method = method;
	}

	public synchronized Class<?> getControllerClass()
	{
		return controllerClass;
	}

	public synchronized Method getMethod()
	{
		return method;
	}
}
