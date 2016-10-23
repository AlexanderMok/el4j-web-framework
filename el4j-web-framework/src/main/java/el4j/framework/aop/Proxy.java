package el4j.framework.aop;

public interface Proxy
{
	/**
	 * chain proxy ，implements 横切逻辑
	 * @param proxyChain
	 * @return
	 * @throws Throwable
	 */
    Object doObject(ProxyChain proxyChain) throws Throwable; 
}
