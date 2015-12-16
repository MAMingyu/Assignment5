package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		String attributeName = null;
		String attributeType = null;
		String attributeValue = null;
		if (!getAttribute){
			try {
				attributeName = br.readLine();
				attributeType = br.readLine();
				attributeValue = br.readLine();
				tuple = new Tuple(attributeName, attributeType, attributeValue);
				if (attributeValue != null && attributeValue.length() != 0){
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					getAttribute = true;
					return tuple;
				}
				else{
					getAttribute = true;
					return null;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		else{
			try {
				attributeValue = br.readLine();
				if (attributeValue != null && attributeValue.length() != 0){
					tuple.col2 = attributeValue.split(",");
					tuple.attributeList = new ArrayList<Attribute>();
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					return tuple;
				}
				else return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}