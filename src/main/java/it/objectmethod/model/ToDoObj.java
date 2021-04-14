package it.objectmethod.model;

public class ToDoObj {
	private Integer Id;
	private String nameTodo;
	private Boolean done;

	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNameTodo() {
		return nameTodo;
	}
	public void setNameTodo(String nameTodo) {
		this.nameTodo = nameTodo;
	}
}
