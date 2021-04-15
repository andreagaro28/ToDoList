<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
body {
	text-align: center;
}

.line {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.delete {
	margin-left: 20%;
}

.done-button {
	margin-right: 20%;
}

.text {
	font-size: 20px;
	font-family: cursive;
}

.row {
	width: 50%;
	user-select: none;
}

.done {
	text-decoration: line-through;
}

#add-form {
	background-color: white;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	position: sticky;
	z-index: 1;
	margin-top: 30px;
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.search {
	width: 40%;
}

#add {
	margin-left: 25%;
}
</style>
</head>
<body>
	<div id="add-form">
		<form action="/todolist/AddToDoServlet" id="add">
			<input type="text" name="toDoText" placeholder="Add to do..."
				class="alert alert-primary text"> <input type="submit"
				value="Add" class="btn btn-primary">
		</form>
		<form action="/todolist/SearchServlet">
			<input type="text" name="searchInput" placeholder="Search..."
				class="alert alert-primary text search"> <input
				type="submit" value="Search" class="btn btn-primary">
		</form>
	</div>
	<c:choose>
		<c:when test="${isSearchActive}">
			<p>
				<a href="/todolist/AddToDoServlet">Back to list</a>
			</p>
			<p>${error}</p>
			<c:forEach items="${array}" var="element">
				<div class="list-group-item list-group-item-warning line">
					<div class="text row">${element.name}</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="element">
				<div class="list-group-item list-group-item-warning line">
					<a href="/todolist/DoneServlet?doneAt=${element.id}"
						class="done-button btn btn-success">Done</a>
					<c:choose>
						<c:when test="${element.done}">
							<div class="done text row">${element.nameTodo}</div>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${element.change}">
									<div class="text row">
										<form action="/todolist/TextChangedServlet?okAt=${element.id}"
											method="POST">
											<input class="row" name="changeTextInput" type="text"
												value="${element.nameTodo}"> <input type="submit"
												value="ok" class="btn btn-info">
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="text row">
										<a href="/todolist/ChangeServlet?changeAt=${element.id}">${element.nameTodo}</a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<a href="/todolist/DeleteServlet?removeAt=${element.id}"
						class="btn btn-danger delete ">Delete</a>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>