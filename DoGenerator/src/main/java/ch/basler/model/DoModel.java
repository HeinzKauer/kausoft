package ch.basler.model;

import java.util.ArrayList;

public class DoModel {
	ArrayList<RowData> data = new ArrayList<>();

	String doModelId = "";

	private DoModel() {
	}

	public DoModel(String doModelId) {
		this.doModelId = doModelId;
	}

	public String getDoModelId() {
		return doModelId;
	}

	public void add(RowData rowData) {
		data.add(rowData);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @return
	 */
	RowData getRowData(int index) {
		return data.get(index);
	}

	@Override
	public String toString() {
		return "DoModel [data=" + data + "]";
	}

	public void listDoModel() {
		System.out.println(this);
	}
}
