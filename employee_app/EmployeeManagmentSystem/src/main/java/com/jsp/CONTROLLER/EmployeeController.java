package com.jsp.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.DAO.EmployeeDao;
import com.jsp.DTO.Employee;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao dao;
	
	//API to insert employee object to DB
	@RequestMapping("/insert") //maps the web requests to spring controller methods
	public ModelAndView getEmployee()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("employee",new Employee());
		mv.setViewName("create.jsp");
		return mv;
	}
	
	@RequestMapping("/save")
	public ModelAndView saveEmployee(Employee employee)
	{
		dao.saveEmployee(employee);
		ModelAndView v=new ModelAndView();
		v.setViewName("index.jsp");
		return v;
	}
	
	//API for searching an object inDB based on ID
	@RequestMapping("/search")
	public ModelAndView searchEmployee()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("employee",new Employee());
		mv.setViewName("get.jsp");
		return mv;
	}
	
	@RequestMapping("/display")
	public ModelAndView findEmployee(@ModelAttribute Employee employee)
	{
		ModelAndView mv=new ModelAndView();
		Employee emp=dao.getEmployeeById(employee.getId());
		mv.addObject("employee",emp);
		mv.setViewName("display.jsp");
		return mv;
	}
	
	//API for deleting an object based on Id
	@RequestMapping("/delete")
	public ModelAndView deleteEmployee()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("employee",new Employee());
		mv.setViewName("delete.jsp");
		return mv;
	}
	
	@RequestMapping("/remove")
	public String removeEmployee(@ModelAttribute Employee employee)
	{
		dao.deleteEmployee(employee.getId());
		return "employee details removed successfully";
	}
	
	@RequestMapping("/displayall")
	public ModelAndView getAllEmployee()
	{
		ModelAndView mv=new ModelAndView();
		List<Employee> employees=dao.getAllEmployee();
		mv.addObject("employeelist",employees);
		mv.setViewName("displayAll.jsp");
		return mv;
	}
	
	//API to update employee details in DB
	@RequestMapping("/update")
	public ModelAndView updateEmployee()
	{

		ModelAndView mv=new ModelAndView();
		mv.addObject("employee",new Employee());
		mv.setViewName("update.jsp");
		return mv;
	}
	
	@RequestMapping("/modify")
	public String modifyEmployeeDetails(@ModelAttribute Employee employee)
	{
		dao.updateEmployee(employee);
		return "EMPLOYEE DETAILS UPDATED SUCCESSFULLY";
	}
}
