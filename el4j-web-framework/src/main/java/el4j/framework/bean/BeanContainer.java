package el4j.framework.bean;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import el4j.framework.context.annotation.Controller;
import el4j.framework.context.annotation.Service;
import el4j.framework.core.helper.ClassHelper;

/**
 * Bean容器
 */
public class BeanContainer
{

	/**
	 * 存放Bean类和Bean实例的映射关系
	 * Store Mapping relation between a Bean and its instance
	 */
	private static final ConcurrentHashMap<String, Object> beanContainer = new ConcurrentHashMap<>();

	static
	{
		ArrayList<Class<?>> beanClasses = ClassHelper.getBeanClasses();
		for (Class<?> beanClass : beanClasses)
		{
			if (beanClass.isAnnotationPresent(Controller.class) || beanClass.isAnnotationPresent(Service.class))
			{
				Object obj = BeanFactory.newInstance(beanClass);
				beanContainer.put(beanClass.getName(), obj);
			}

		}
	}

	/**
	 * 获取Bean映射
	 *
	 * @return beanContainer
	 */
	public static Map<String, Object> getBeanContainer()
	{
		return beanContainer;
	}

	/**
	 * 获取Bean实例，但这里没有处理scope属性问题，对象只实例化一次
	 * Scope is not handled here, Objects are initialized once.
	 * @param className
	 * @return Bean Instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className)
	{
		if (!beanContainer.containsKey(className))
		{
			/**
			 * 
			 */
			throw new RuntimeException("can not get bean by className: " + className);
		}
		return (T) beanContainer.get(className);
	}

	/**
	 * 设置Bean实例
	 * @param className
	 * @param obj
	 */
	public static void setBean(String className, Object obj)
	{
		beanContainer.put(className, obj);
	}
}
