import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class nonRollingHashCompute
{
	// member fields and methods
	String[] s = null;
	int k = 0;
	int ktimes = 0;

	HashMap<String, Integer> hMap;
	HashSet<String> hMapConcanted;
	String entireString;
    ArrayList<String> all2kCombinations;

	public nonRollingHashCompute(String[] s, int k)
	{
		// implementation
		this.s = s;
		this.k = k;
		this.ktimes = 2*k;
        StringBuilder sb = new StringBuilder();
        all2kCombinations = new ArrayList<String>();
        entireString = sb.toString();
		hMap = new HashMap<String, Integer>();
		populateCombinations();


	}

	public ArrayList<String> compute2k()
	{

        ArrayList<String> compute2k = new ArrayList<String>();
        //for(int i = 0; i < hMapConcanted.size(); i++){
        for(int i = 0; i < all2kCombinations.size(); i++){
            int checkCounter = 0;
            boolean containsSubString = true;
            for(int j = k; j < ktimes; j++){
                //String substringOfString = all2kCombinations.get(i).substring(counter, j);
                String substringOfString = all2kCombinations.get(i).substring(j-k, j);
                //Contains key is O(1) time since it is computing the hash of the substringOfString and returns if value and/or key exists in the given
                if(hMap.containsKey(substringOfString)){
                    containsSubString = true;
                }else{
                    containsSubString = false;
                    break;
                }
            }
            if(containsSubString)
                compute2k.add(all2kCombinations.get(i));

        }
        //ABCDEF
		return compute2k;
	}


	public void populateCombinations(){
        int counter = 0;
        for(int i = 0; i < s.length; i++){
            hMap.put(s[i], i);
            for(int j = 0; j < s.length; j++){
                //if(i != j) {
                all2kCombinations.add(s[i]+s[j]);
            }
        }
    }

    public void hash(String k){
        int hash = 7;
        for (int i = 0; i < k.length(); i++) {
            hash = hash*31 + k.charAt(i);
        }

    }
}

