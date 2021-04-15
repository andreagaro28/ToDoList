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

@WebServlet("/AddToDoServlet")
public class AddToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int id = 0;
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String toDoElement = request.getParameter("toDoText");
		List<ToDoObj> toDoListObj = new ArrayList<>();
		if(session.getAttribute("list")!=null) {
			toDoListObj = (List<ToDoObj>) session.getAttribute("list");
		}
		if(toDoElement==null || toDoElement.isBlank()) {
			
		}
		else {
			ToDoObj toDoObj = new ToDoObj();
			toDoObj.setNameTodo(toDoElement);
			toDoObj.setDone(false);
			toDoObj.setChange(false);
			toDoListObj.add(toDoObj);	
			int id = 0;
			for(ToDoObj i : toDoListObj) {
				i.setId(id++);
				i.setChange(false);
			}
			session.setAttribute("list", toDoListObj);
		}

		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}


}
