package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple next = null;
		if(tuplesResult.isEmpty()){
		//score all tuples from child to tuplesResult
			next=child.next();
			if(next==null) return null;
			newAttributeList = next.getAttributeList();
			int num = newAttributeList.size();
			int i = 0;
			while (i < num && next != null){
				newAttributeList = child.getAttributeList();
				num = newAttributeList.size();
				i = 0;
				tuplesResult.add(next);
				i++;
				next = child.next();
			}
		}
		
		//Find the sorting condition
		int index;
		for (index = 0; index < newAttributeList.size(); index++){
			if (orderPredicate.equals(tuplesResult.get(0).getAttributeName(index))){
				break;
			}
		}
		//Sorting
		boolean notFinishSorting = true;
		while(notFinishSorting){
			int cntSwap = 0;
			for (int i = 0; i < tuplesResult.size() - 1; i++){
				//if previous one is larger than next one, swap
				//Do sorting:
				switch(tuplesResult.get(i).getAttributeType(index).type){
				case STRING:
					if (((String) tuplesResult.get(i).getAttributeValue(index)).compareTo((String) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case INTEGER:
					if (((Integer) tuplesResult.get(i).getAttributeValue(index)).compareTo((Integer) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case BOOLEAN:
					if (((Boolean) tuplesResult.get(i).getAttributeValue(index)).compareTo((Boolean) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case BYTE:
					if (((Byte) tuplesResult.get(i).getAttributeValue(index)).compareTo((Byte) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case CHAR:
					if (((Character) tuplesResult.get(i).getAttributeValue(index)).compareTo((Character) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case DOUBLE:
					if (((Double) tuplesResult.get(i).getAttributeValue(index)).compareTo((Double) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case FLOAT:
					if (((Float) tuplesResult.get(i).getAttributeValue(index)).compareTo((Float) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case LONG:
					if (((Long) tuplesResult.get(i).getAttributeValue(index)).compareTo((Long) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				case SHORT:
					if (((Short) tuplesResult.get(i).getAttributeValue(index)).compareTo((Short) tuplesResult.get(i+1).getAttributeValue(index)) > 0){
						Tuple tentativeStore = tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(i+1));
						tuplesResult.set(i+1, tentativeStore);
						cntSwap++;
					}
					break;
				default:
					break;
				}
			}
			if (cntSwap == 0) notFinishSorting = false;
		}

				
		Tuple result;
		if (tuplesResult.size() == 0){
			result = null;
		}
		else{
			result = tuplesResult.get(0);
			tuplesResult.remove(0);
		}
		return result;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}