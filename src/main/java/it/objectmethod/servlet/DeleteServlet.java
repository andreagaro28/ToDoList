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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int id = 0;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idRemover = request.getParameter("removeAt");
		if(idRemover != null) {
			HttpSession session = request.getSession();
			List<ToDoObj> toDoListObj = new ArrayList<>();
			toDoListObj = (List<ToDoObj>) session.getAttribute("list");
			toDoListObj.remove(Integer.parseInt(idRemover));
			
			int id = 0;
			for(ToDoObj i : toDoListObj) {
				i.setId(id++);
				i.setChange(false);
			}
		}
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}
}
