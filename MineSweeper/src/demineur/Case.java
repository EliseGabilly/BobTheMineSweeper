package demineur;

public class Case {
	
	String state; //flag F push value neutre -
	int value; //-1 bombe else nombre de bombes autour
	
	public Case() {
		state=" ";
		value=0;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state=state;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value=value;
	}
	
	public void set(int value, String state) {
		this.value=value;
		this.state=state;
	}
	
}