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

@WebServlet("/TextChangedServlet")
public class TextChangedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int id = 0;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idOk = (int) session.getAttribute("idElement");
		String textChanged = request.getParameter("changeTextInput");
		List<ToDoObj> toDoListObj = new ArrayList<>();
		toDoListObj = (List<ToDoObj>) session.getAttribute("list");
		System.out.println(textChanged);
		toDoListObj.get(idOk).setNameTodo(textChanged);
		boolean done = toDoListObj.get(idOk).getChange(); 
		toDoListObj.get(idOk).setChange(!done); 
		
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}
}
