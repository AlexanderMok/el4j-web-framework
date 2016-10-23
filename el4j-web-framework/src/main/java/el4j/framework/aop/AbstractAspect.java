package el4j.framework.aop;

import java.lang.reflect.Method;



public abstract class AbstractAspect implements Proxy
{
	@Override
	public Object doObject(ProxyChain proxyChain) throws Throwable
	{

		Object result = null;

		Class<?> cls = proxyChain.getTargetClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();

		begin();

		try
		{

			if (intercept(cls, method, params))
			{
				beforeAdvise(cls, method, params);

				result = proxyChain.doProxyChain();

				afterAdvise(cls, method, params);
			}
			else
			{
				result = proxyChain.doProxyChain();

			}

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		finally
		{
			end();
		}

		return result;
	}

	public void begin()
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param cls
	 * @param method
	 * @param params
	 * @return
	 */
	public boolean intercept(Class<?> cls, Method method, Object[] params)
	{
		return false;
	}
	
	
    /**
     * 
     * @param cls
     * @param method
     * @param params
     */
	public void afterAdvise(Class<?> cls, Method method, Object[] params)
	{
		// TODO Auto-generated method stub

	}
    /**
     * 
     * @param cls
     * @param method
     * @param params
     */
	public void beforeAdvise(Class<?> cls, Method method, Object[] params)
	{
		// TODO Auto-generated method stub

	}
	
    /**
     * 
     */
	public void end()
	{
		// TODO Auto-generated method stub

	}
}
