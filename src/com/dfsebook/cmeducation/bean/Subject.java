package com.dfsebook.cmeducation.bean;


public class Subject {

	private String stem;
	
	private String[] options;
	
	private int[] answers;

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public int[] getAnswers() {
		return answers;
	}

	public void setAnswers(int[] answers) {
		this.answers = answers;
	}
	
	public void addOption(String option, int index) {
		if (options == null)
			return;
		options[index] = option;
	}
	
	public void addAnswer(int index) {
		if (answers == null)
			return;
		answers[index] = 1;
	}
}
