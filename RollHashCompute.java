import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class RollHashCompute
{
	int k = 0;
	int ktimes = 0;
	ArrayList<String> all2kCombinations;
	String[] copyOfArray;
	// a large prime for modulo purposes
	long Q = BigInteger.probablePrime(31, new Random()).longValue();
	// radix
	int R = 256;

	public RollHashCompute(String[] s, int k)
	{
		this.copyOfArray = s;
		this.k = k;
		this.ktimes = 2*k;
		all2kCombinations = new ArrayList<String>();

		populateCobinations(s);
	}
	//ABCDEFGH
	public ArrayList<String> compute2k()
	{
		ArrayList<String> compute2k = new ArrayList<String>();
		//RabinKarp karp = new RabinKarp();
		for(int i = 0; i < all2kCombinations.size();i++) {
			int subCounter = 0;
			boolean containsSubString = true;
			for(int j = 0; j < copyOfArray.length; j++){
				//int toMul = karp.calcRabinKarp(all2kCombinations.get(i),toCheck);
				//int toMul = patternSearch(all2kCombinations.get(i).toCharArray(),toCheck.toCharArray());
				int recurrences = recurrences(all2kCombinations.get(i),copyOfArray[j]);
				if(recurrences > 0){
					//Multiplies by the number of occurences
					subCounter = (subCounter + recurrences);

				}
			}
			if(subCounter >= (k))
				compute2k.add(all2kCombinations.get(i));
		}
		return compute2k;
	}


	public void populateCobinations(String[] s){
		for(int i = 0; i < s.length; i++){
			for(int j = 0; j < s.length; j++){
				this.all2kCombinations.add(s[i]+s[j]);
			}
		}
	}

	/**
	 * This method runs the Rabin-Karp algorithm using a Randomized hash / Fingerprit hash function
	 * References used to build this recurrence calculating roll hash algorithm: Web: https://algs4.cs.princeton.edu/53substring/
	 * This is a modified search algorithm as explained by Princeton, here I am changing it to find the total number of recurrences
	 * Detailed explaination on documentation
	 *
	 * @param string: String to search given a patter
	 * @param pattern: Pattern to search the given string
	 * @return: returns the number of times the pattern occurs in the given string
	 */
	public int recurrences( String string, String pattern){
		int M = pattern.length();
		//set the bucket size

		// precompute R^(M-1) % Q for use in removing leading digit
		long RM = 1;

		for (int i = 1; i <= M-1; i++)
			RM = (R * RM) % Q;

		long patHash = hash(pattern, M);
		int N = string.length();

		//precompute the first pass hash of the length of the pattern
		long txtHash = hash(string, M);

		//Counter to check for multiple matches
		int counter = 0;
		for (int i = M; i < N; i++) {

			// Remove leading digit
			//Also doing a modulo reduction so as to keep the result small
			//eg. if we are rolling over pete
			// ASCII  e = 97, t = 98, p = 114.
			//first we hash("pet") =  [ ( [ ( [  (97 % 101) × 256   + 98 ] % prime ) × R ] %  prime ) + 114 ]   % 101   =  4
			//then we remove the leading digit and add in the traling digit like so
			//hash(abr) = hash(abr) - hash(a) = hash(br)
			//then hash(br) = hash(br) + hash(a) yielding us
            //hash("ete") =     [ ( 4 + prime - 97 * [(R % 101) * R] % 101 ) * R + 97 ] % 101 = 30
			txtHash = ((txtHash + Q) - (RM*string.charAt(i - M)) % Q) % Q;
			//Add the trailing digit
			txtHash = ((txtHash * R) + (string.charAt(i))) % Q;

			//offset to check if the Value of the removed leading digit matches
			int offset = i - M + 1;
			//Check to make sure the strings are correct incase there is a collision
			if ((patHash == txtHash) && check(string,offset,pattern,M))
				counter++;

		}
		return counter;
	}
	private boolean check(String txt, int i,String pattern, int M)
	{
		for (int j = 0; j < M; j++)
			if (pattern.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	/**
	 * Used for computing the first pass k length hash and to hash the pattern
	 *
	 * @param key String to hash
	 * @param M Length of the given string to hash
	 * @return Returns the hashed string as a Long
	 */
	private long hash(String key, int M)
	{
		long h = 0;
		for (int j = 0; j < M; j++)
			//Computes the hash of a given string and mods it a probable prime of length 31 to avoid overflow
			h = (R * h + key.charAt(j)) % Q;
		return h;
	}


}
