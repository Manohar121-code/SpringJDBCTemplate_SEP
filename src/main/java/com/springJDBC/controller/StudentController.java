package com.springJDBC.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springJDBC.dao.StudentDao;
import com.springJDBC.model.Student;

@Controller
public class StudentController {

	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createStudent(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("course") String course, ModelAndView mv) {

		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setCourse(course);

		int counter = studentDao.create(student);

		if (counter > 0) {
			mv.addObject("msg", "Student registration successful.");
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("create");

		return mv;
	}
	
	@RequestMapping(value = "/read")
	public ModelAndView readStudent(ModelAndView model) throws IOException {

		List<Student> listStudent = studentDao.read();
		model.addObject("listStudent", listStudent);
		model.setViewName("read");

		return model;
	}
	
	@RequestMapping(value = "/update/{studentId}")
	public ModelAndView findStudentById(ModelAndView model, @PathVariable("studentId") int studentId)
			throws IOException {

		List<Student> listStudent = studentDao.findStudentById(studentId);
		model.addObject("listStudent", listStudent);
		model.setViewName("update");

		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("course") String course, ModelAndView mv) {

		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		student.setCourse(course);

		int counter = studentDao.update(student);

		if (counter > 0) {
			mv.addObject("msg", "Student records updated against student id: " + student.getId());
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("update");

		return mv;
	}
	
	@RequestMapping(value = "/delete/{studentId}")
	public ModelAndView deleteStudentById(ModelAndView mv, @PathVariable("studentId") int studentId)
			throws IOException {

		int counter = studentDao.delete(studentId);

		if (counter > 0) {
			mv.addObject("msg", "Student records deleted against student id: " + studentId);
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("delete");

		return mv;
	}
	
}
