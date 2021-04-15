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
</style>
</head>
<body>
	<form action="/todolist/AddToDoServlet">
		<input type="text" name="toDoText" placeholder="Add to do..."
			class="alert alert-primary text"> <input type="submit"
			value="Add" class="btn btn-primary">
	</form>
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
								<form action="/todolist/TextChangedServlet?okAt=${element.id}">
									<input class="row" name="changeTextInput" type="text"
										value="${element.nameTodo}"> 
										<input type="submit" value="ok" class="btn btn-info">
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
</body>
</html>