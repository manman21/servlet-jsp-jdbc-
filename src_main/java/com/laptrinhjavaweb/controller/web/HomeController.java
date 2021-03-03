package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/home","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;
	
	@Inject
	private IUserService userService;
	
	ResourceBundle bundle = ResourceBundle.getBundle("message");
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", bundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, res);
		} else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			res.sendRedirect(req.getContextPath()+"/home");
		} else {
			NewModel newModel = new NewModel();
			Pageble pageble = new PageRequest(1, 6, new Sorter("createddate", "desc"));
			newModel.setListResult(newService.findAll(pageble));
			SessionUtil.getInstance().putValue(req,"NEWMODEL",newModel);
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if (model !=  null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) {
					res.sendRedirect(req.getContextPath()+ "/home");
				} else if (model.getRole().getCode().equals("ADMIN")){
					res.sendRedirect(req.getContextPath()+ "/admin-home");
				}
			} else {
				res.sendRedirect(req.getContextPath()+ "/dang-nhap?action=login&message=user_password_invalid&alert=danger");
			}
			
		} else if(action != null && action.equals("logout")) {
			
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, res);
		}

	}
	
}
