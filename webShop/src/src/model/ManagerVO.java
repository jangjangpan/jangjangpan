package model;

public class ManagerVO {

	int manager_id;
	String fullname;
	public ManagerVO(int manager_id, String fullname) {
		super();
		this.manager_id = manager_id;
		this.fullname = fullname;
	}
	public ManagerVO() {
		super();
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
}
