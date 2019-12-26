package com.zc.publics;

public class OrderBy {
	public String column;
	private String by;
	
	public OrderBy(){
		
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public OrderBy(String column, String by) {
		super();
		this.column = column;
		this.by = by;
	}
	
}

