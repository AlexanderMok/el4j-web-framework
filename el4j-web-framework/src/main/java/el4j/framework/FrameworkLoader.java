package el4j.framework;

import el4j.framework.bean.BeanContainer;
import el4j.framework.core.helper.ClassHelper;
import el4j.framework.core.helper.ControllerHelper;
import el4j.framework.core.helper.IocHelper;
import el4j.framework.core.utils.ClassUtil;

/**
 * 初始化框架
 */
public class FrameworkLoader
{

	public static void init()
	{
		Class<?>[] cs = { ClassHelper.class, BeanContainer.class, IocHelper.class, ControllerHelper.class };
		for (Class<?> c : cs)
		{
			ClassUtil.loadClass(c.getName(), true);
		}
	}
}
