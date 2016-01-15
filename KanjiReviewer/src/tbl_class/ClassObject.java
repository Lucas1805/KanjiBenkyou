package tbl_class;

public class ClassObject {
	private String id;
	private String level;
	
	public ClassObject() {
		id = null;
		level = null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.id.equals( ((ClassObject) obj).id )) {
			return true;
		}
		else 
			return false;
	}
	
	//Combo box show value of toString function of object. Override toString to get the value we want to show in combo box
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.level;
	}
	
}
