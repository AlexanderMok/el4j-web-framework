package el4j.framework.core.helper;

import java.util.Properties;

import el4j.framework.context.constant.Constant;
import el4j.framework.core.utils.PropsUtil;

/**
 * 获取配置文件属性
 */
public class ConfigHelper
{

	private static final Properties CONFIG_PROPS = PropsUtil.loadProps("config.properties");

	/**
	 * 获取应用基础包名
	 *
	 * @return 
	 */
	public static String getAppBasePackage()
	{
		return PropsUtil.getString(CONFIG_PROPS, Constant.APP_BASE_PACKAGE);
	}

	/**
	 * 获取JSP路径
	 *
	 * @return
	 */
	public static String getAppJspPath()
	{
		return PropsUtil.getStringByDefault(CONFIG_PROPS, Constant.APP_JSP_PATH, "/WEB-INF/jsp/");
	}

	/**
	 * 获取应用静态资源路径
	 *
	 * @return
	 */
	public static String getAppAssetPath()
	{
		return PropsUtil.getStringByDefault(CONFIG_PROPS, Constant.APP_ASSET_PATH, "/asset/");
	}

}
