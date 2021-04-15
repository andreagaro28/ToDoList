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

@WebServlet("/DoneServlet")
public class DoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id = 0;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idDone = request.getParameter("doneAt");
		if(idDone != null) {
			HttpSession session = request.getSession();
			List<ToDoObj> toDoListObj = new ArrayList<>();
			toDoListObj = (List<ToDoObj>) session.getAttribute("list");

			boolean done = toDoListObj.get(Integer.parseInt(idDone)).getDone(); 
			toDoListObj.get(Integer.parseInt(idDone)).setDone(!done); 
			for(ToDoObj i : toDoListObj) {
				i.setChange(false);
			}
		}
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}
}
