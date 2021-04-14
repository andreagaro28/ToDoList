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
		HttpSession session = request.getSession();
		int idDone = Integer.parseInt(request.getParameter("doneAt"));
		List<ToDoObj> toDoListObj = new ArrayList<>();
		toDoListObj = (List<ToDoObj>) session.getAttribute("list");
		
		boolean done = toDoListObj.get(idDone).getDone(); 
		toDoListObj.get(idDone).setDone(!done); 
		
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}
}
