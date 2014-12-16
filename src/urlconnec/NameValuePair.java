package urlconnec;

public class NameValuePair {
	
	private String name;
	private String value;
	
	public NameValuePair (String newName, String newValue){
		name = newName;
		value = newValue;
	}

	public NameValuePair(){
		name = null;
		value = null;
	}
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
	}
}
