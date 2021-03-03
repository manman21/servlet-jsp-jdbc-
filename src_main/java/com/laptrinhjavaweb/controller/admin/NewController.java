package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet (urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	ResourceBundle bundle = ResourceBundle.getBundle("message");

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		NewModel newModel = FormUtil.toModel(NewModel.class, req);
		String view = null;
		if ( newModel.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(), 
					new Sorter(newModel.getSortName(), newModel.getSortBy()));
			newModel.setListResult(newService.findAll(pageble));
			newModel.setTotalItem(newService.getTotalItem());
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
			view = "/views/admin/new/list.jsp";
		} else if(newModel.getType().equals(SystemConstant.EDIT)) {
			if (newModel.getId() != null) {
				newModel = newService.findOne(newModel.getId());
			} 
			req.setAttribute("categories", categoryService.findAll());;
			view = "/views/admin/new/edit.jsp";
		} 
		MessageUtil.showMessage(req);
		req.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


	}
}
