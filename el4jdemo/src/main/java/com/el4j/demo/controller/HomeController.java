package com.el4j.demo.controller;

import java.util.Map;

import com.el4j.demo.service.PersonService;

import el4j.framework.context.annotation.Autowired;
import el4j.framework.context.annotation.Controller;
import el4j.framework.context.annotation.RequestMapping;
import el4j.framework.context.application.ModelAndView;
import el4j.framework.context.application.Param;
import el4j.framework.context.application.RequestMethod;

@Controller
public class HomeController
{

	@Autowired
	private PersonService ps;

	// 主界面
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView home()
	{
		return new ModelAndView("home.jsp").addmData("message", "Welcome");
	}

	// 获取person信息
	@RequestMapping(path = "/persons", method = RequestMethod.GET)
	public ModelAndView getPersons()
	{
		if (ps == null)
		{
			System.out.println("++++++++++++");
			return new ModelAndView("home.jsp").addmData("message", "Welcome Daaa");
		}
		return new ModelAndView("persons.jsp").addmData("persons", ps.getPersons());
	}

	// 处理用户登录
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView login(Param param)
	{
		Map<String, Object> user = param.getFieldMap();
		System.out.println(user.get("username"));
		System.out.println(user.get("password"));
		return new ModelAndView("home.jsp").addmData("message", "Welcome YYY");
	}
}
