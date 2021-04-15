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

import it.objectmethod.model.SearchToDo;
import it.objectmethod.model.ToDoObj;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings({ "unchecked", "null"})
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSearchActive = true;
		String search = request.getParameter("searchInput");
		HttpSession session = request.getSession();
		List<SearchToDo> searchedElements = new ArrayList<>();
		List<SearchToDo> tidyList = new ArrayList<>();
		List<ToDoObj> toDoListObj = new ArrayList<>();
		toDoListObj = (List<ToDoObj>) session.getAttribute("list");
		SearchToDo searchObj = null;
		String error = "No articles found";

		if(search == null || search.isBlank() || toDoListObj.isEmpty()) {
			request.setAttribute("error", error);
		}else {
			for(ToDoObj i : toDoListObj) {
				String word = i.getNameTodo();
				int score = 0;
				searchObj = new SearchToDo();
				for(int j=0; j<search.length(); j++){
					try {
						char searchCharAt = search.charAt(j);
						char wordCharAt = word.charAt(j);
						if(wordCharAt == searchCharAt) {
							score++;
						}
						else {break;}
					} catch (Exception e) {
						break;
					}
				}
				searchObj.setName(word);
				searchObj.setScore(score);
				searchedElements.add(searchObj);
			}

			for(SearchToDo element : searchedElements) {
				if(element.getScore() == search.length()) {
					tidyList.add(element);
				}
			}
			if(tidyList.isEmpty()) {
				request.setAttribute("error", error);
			}else {
				request.setAttribute("array", tidyList);
			}
		}

		session.setAttribute("isSearchActive", isSearchActive);
		request.getRequestDispatcher("/pages/toDo.jsp").forward(request, response);
	}
}
