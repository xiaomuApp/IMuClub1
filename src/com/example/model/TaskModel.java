package com.example.model;

public class TaskModel {

	private String theme;
	private String deadline;
	private boolean Isdeclare;
	private boolean Iscomplete;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public boolean isIsdeclare() {
		return Isdeclare;
	}

	public void setIsdeclare(boolean isdeclare) {
		Isdeclare = isdeclare;
	}

	public boolean isIscomplete() {
		return Iscomplete;
	}

	public void setIscomplete(boolean iscomplete) {
		Iscomplete = iscomplete;
	}

}
