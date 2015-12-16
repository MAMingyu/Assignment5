package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		String attributeName;
		Type attributeType;
		String attributeValue;
		Tuple next = child.next();
		Tuple result;
		newAttributeList = child.getAttributeList();
		int num = newAttributeList.size();
		int index = 0;
		if (next != null){
			while(index<num){
				if (attributePredicate.equals(next.getAttributeName(index))){
					break;
				}
				index++;
			}
			attributeName = next.getAttributeName(index);
			attributeValue = (String) next.getAttributeValue(index);
			attributeType = next.getAttributeType(index);
			result = new Tuple(attributeName, attributeType.toString(), attributeValue);
			result.setAttributeName();
			result.attribute.attributeType = attributeType;
			result.setAttributeValue();
			return result;
		}
		else return null;
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}