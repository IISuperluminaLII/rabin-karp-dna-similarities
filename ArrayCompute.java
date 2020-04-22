import java.util.ArrayList;


public class ArrayCompute
{
	// member fields and methods

	int k = 0;
	int ktimes = 0;
	ArrayList<String> all2kCombinations;
	String[] copyOfArray;

	/**
	 *
	 * Main method that starts by filling values for s and k. Then creates all2kcombnations
	 * that represents all the possible combinations derived from s
	 *
	 * String[] copyOfArray = copy of strings in s
	 *
	 * int ktimes = 2k(length of 2k combination)
	 *
	 * ArrayList<> all2kCombinations = ArrayList of every possible combination in s
	 *
	 * @param s Input set S
	 * @param k Size of the Array K
	 */
	public ArrayCompute(String[] s, int k)
	{
		if(s[0].length() != k)
			throw new IllegalArgumentException("Element 0 in set S is not a valid length");
		this.copyOfArray = s;
		this.k = k;
		this.ktimes = 2*k;
		all2kCombinations = new ArrayList<String>();
		populateCombinations(s);

	}
	/**
	 * compute2k finds all correct values in all2kCombination. Compares all possible k length substrings
	 * from k length set S in all2kCombinations to the values contained in set S in copyOfArray. If the strings
	 * match then the checkCouter is incremented by the length of the substring. If each substring
	 * of the current all2kCombinations string has a value in copyOfArray the string is add to compute2k
	 *
	 * ArrayList<> compute2k = T(collection of 2k strings)
	 *
	 * int checkCouter = number of times the compared substring is equal to a value in s
	 *
	 * String substringOfString  = substring of a string from all2kcombinations
	 *
	 * String test = current string from all2kCombinations
	 *
	 * @return Returns the valid 2k length string who's k length substring is cointained within the set S
	 */
	public ArrayList<String> compute2k()
	{
		ArrayList<String> compute2k = new ArrayList<String>();
		//O(n) since inner loop is array length dependent
		for(int i = 0; i < all2kCombinations.size(); i++){
			int checkCounter = 0;
			boolean containsSubString = true;
			//O(k) runtime since we are iterating over the 2k length string
			for(int j = k; j < ktimes; j++){
				//O(k * n) since we are checking all the possible substrings and weather it exists within the original set S
				String substringOfString = all2kCombinations.get(i).substring(j-k, j);
				if(arrContains(substringOfString)){
					containsSubString = true;
				}else{
					containsSubString = false;
					break;
				}
			}
			if(containsSubString){
				compute2k.add(all2kCombinations.get(i));
			}
		}
		return compute2k;
	}

	/**
	 * This helper method traverses the entire set S and checks the substring s is contained within it
	 *
	 * @param s
	 * @return If the substring s is within the orginal set S then this returns True else returns False
	 */
	public boolean arrContains(String s){

		for(int i = 0; i < copyOfArray.length; i++){

			if(s.compareTo(copyOfArray[i]) == 0){
				return true;
			}

		}
		return false;

	}

	public void populateCombinations(String[] s){

		for(int i = 0; i < s.length; i++){
			for(int j = 0; j < s.length; j++){
				all2kCombinations.add(s[i]+s[j]);
			}
		}
	}



}

