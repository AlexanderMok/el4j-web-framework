package el4j.framework.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

public class ProxyChain
{
    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;//from cglib
    private final Object[] methodParams;
    
    
    private List<Proxy> proxyList = new ArrayList<>();
    
    /**
     * proxy class counter
     */
    private int proxyIndex = 0;

	public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParams, List<Proxy> proxyList)
	{
		super();
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}

	public Class<?> getTargetClass()
	{
		return targetClass;
	}

	public Method getTargetMethod()
	{
		return targetMethod;
	}

	public Object[] getMethodParams()
	{
		return methodParams;
	}
    
    public Object doProxyChain() throws Throwable
    {
    	Object methodResult = null;
    	if (proxyIndex < proxyList.size())
		{
			methodResult = proxyList.get(proxyIndex++).doObject(this);
		}
		else
		{
			methodProxy.invokeSuper(targetObject, methodParams);

		}
    	return methodResult;
    }
}
