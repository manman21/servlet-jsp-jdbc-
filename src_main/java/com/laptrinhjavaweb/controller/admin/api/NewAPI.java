package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{

	/**
	 * 
	 */
	
	@Inject
	private INewService newService;
	
	private static final long serialVersionUID = -915988021506484384L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("api-admin-new: POST");
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy( ((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName() );
		newModel = newService.save(newModel);
		mapper.writeValue(res.getOutputStream(), newModel);
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("api-admin-new: PUT");
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		NewModel updateNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy( ((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName() );
		updateNew = newService.update(updateNew);
		mapper.writeValue(res.getOutputStream(), updateNew);
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("api-admin-new: DELETE");
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		NewModel deleteNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.delete(deleteNew.getIds());
		mapper.writeValue(res.getOutputStream(), "{}");
	}
}