package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/bai-viet"})
public class NewController extends HttpServlet {

	/**
	 * 
	 */
	
	
	@Inject
	private INewService newService;
	
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Long id =  Long.parseLong(req.getParameter("id"), 10);
		NewModel newModel = new NewModel();
		if (id == null) {
			res.sendRedirect(req.getContextPath()+"/home");
		}
		newModel= newService.findOne(id);
		SessionUtil.getInstance().putValue(req,"NEWMODEL",newModel);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/new.jsp");
		rd.forward(req, res);
	}
}
