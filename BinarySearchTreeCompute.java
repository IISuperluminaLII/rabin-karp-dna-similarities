import java.util.ArrayList;

public class BinarySearchTreeCompute
{
	// member fields and methods
	int k = 0;
	int ktimes = 0;
	String[] bst;
	BinaryST st;
	BinaryST stConcanted;
	String[] all2kCombinations;
	public BinarySearchTreeCompute(String[] s, int k)
	{
		this.bst = s;
		this.k = k;
		this.ktimes = 2*k;
		st = new BinaryST(s);
		//Complexity of O(klog(n))
		//stConcanted = new BinaryST(stingConcanted(s));
        all2kCombinations = stingConcanted(s);
	}

	//O(n^2 log n) or O(n^2*h)

	/**
	 * This Method computes all the possible valid combinations of k length string of size n and then checks each
	 * k length substring within 2k the length string and then returns the ArrayList containing the valid strings
	 * This uses a BST to search search a k length string which is O(k log n)
     *
	 * @return
	 */
	public ArrayList<String> compute2k()
	{
		String[] contantedToSearch = stingConcanted(bst);
		ArrayList<String> compute2k = new ArrayList<String>();
		//O(n) since inner loop is array length dependent
		for(int i = 0; i < all2kCombinations.length; i++){

			boolean containsSubString = true;

			for(int j = k; j < all2kCombinations[i].length(); j++){
				String substringOfString = all2kCombinations[i].substring(j-k,j);//stConcanted.findNode(contantedToSearch[i]).getData().substring(j-k, j);

				//Big-O of O(h) or O( log(n))
				if(st.search(substringOfString)){
					containsSubString = true;
				}else{
					containsSubString = false;
					break;
				}
			}
			if(containsSubString)
				compute2k.add(all2kCombinations[i]);
		}

		return compute2k;
	}

    /**
     * Pre computes all the possible combinations of a given set S
     * @param st
     * @return
     */
	public String[] stingConcanted(String[] st){
		ArrayList<String> toRet = new ArrayList<String>();
		for(int i = 0; i < st.length; i++){
			for(int j = 0; j < st.length; j++){
				//if(i != j)
					toRet.add(st[i] + st[j]);
			}
		}
		String[] stockArr = new String[toRet.size()];
		stockArr = toRet.toArray(stockArr);

		return stockArr;
	}


}

