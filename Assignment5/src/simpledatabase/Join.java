package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple next = null;
		int numRight = 0;
		int numLeft = 0;
		int indexLeft = 0;
		int indexRight = 0;
		int cntLeft = 0;
		int cntRight = 0;
		
		//store all the tuples selected by leftChild in tuples1 when first time call next function
		if(tuples1.isEmpty() && leftChild!=null){
			next = leftChild.next();
			newAttributeList = next.getAttributeList();
			numLeft = newAttributeList.size();
			int i = 0;
			while (i < numLeft){
				Tuple newNext = new Tuple("","","");
				newNext.col = next.col;
				newNext.col1 = next.col1;
				newNext.col2 = next.col2;
				newNext.attributeList = new ArrayList<Attribute>();
				newNext.setAttributeName();
				newNext.setAttributeType();
				newNext.setAttributeValue();
				tuples1.add(newNext);
				i++;
				next = leftChild.next();
			}
		}
		numLeft = tuples1.size();
		
		//find the foreign key and the connection relationship
		next = rightChild.next();
		if(next != null){
			newAttributeList = next.getAttributeList();
			numRight = newAttributeList.size();
		}
		//Use two loop to try to match every pair of keys in leftChild and rightChild
		outerloop:
			for (indexLeft = 0; indexLeft < numLeft; indexLeft++){
				for (indexRight = 0; indexRight < numRight; indexRight++){
					if (next.getAttributeName(indexRight).equals(tuples1.get(0).getAttributeName(indexLeft))){
						break outerloop;
					}
				}
			}
		//pick tuples from leftChild, compare, merge and store in tuples1
		while(cntRight < numRight){
			if (next == null){//This next is the first element in rightChild
				return null;
			}
			while(cntLeft < numLeft && next!=null){
				if (next.getAttributeValue(indexRight).equals(tuples1.get(cntLeft).getAttributeValue(indexLeft))){
					newAttributeList = next.getAttributeList();
					for (int j = 0; j < numLeft; j++){
						if (j != indexLeft)
							newAttributeList.add(tuples1.get(cntLeft).getAttributeList().get(j));
					}
					cntLeft=0;
					return new Tuple(newAttributeList);
				}
				cntLeft++;
			}
			cntLeft = 0;
			cntRight++;
			next = rightChild.next();
			}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}