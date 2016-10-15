package el4j.framework.context.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * Wrap Request Info
 */
public class Request
{

	// 请求方法 Request Method. GET, POST, PUT, DELETE
	private String requestMethod;
	// 请求路径 Request Path
	private String requestPath;

	public Request(String requestMethod, String requestPath)
	{
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod()
	{
		return requestMethod;
	}

	public String getRequestPath()
	{
		return requestPath;
	}

	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj)
	{
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
