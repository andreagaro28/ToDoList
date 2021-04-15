package it.objectmethod.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.model.ToDoObj;

@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int id = 0;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idChange = request.getParameter("changeAt");
		if(idChange != null) {
			HttpSession session = request.getSession();
			List<ToDoObj> toDoListObj = new ArrayList<>();
			toDoListObj = (List<ToDoObj>) session.getAttribute("list");
			
			for(ToDoObj i : toDoListObj) {
				i.setChange(false);
			}
			int id=Integer.parseInt(idChange);
			boolean done = toDoListObj.get(id).getChange(); 
			toDoListObj.get(id).setChange(!done); 
			
			session.setAttribute("idElement", id);
		}
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);

	}
}
