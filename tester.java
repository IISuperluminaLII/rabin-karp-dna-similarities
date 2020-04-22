import java.util.ArrayList;

public class tester {

	public static void main(String[] args){

        
		//String[] sts = new String[]{"BC","CA","AG","AF","FR","FR","FR","FR","AF","CA","GG","AR","AR","ZZ"};
        String combinations = "ACAAGATGCCATTGTCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCCCCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCC"; //AGGCCAGTGCCGGGCCCCTCATAGGAGAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGG;//TGGTTTGAGTGGACCTCCCAGGCCAGTGCCGGGCCCCTCATAGGAGAGGCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCCCCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCCAGGCCAG";
        ArrayList<String> store = new ArrayList<String>();
        int subLength = 5;
        for(int i = subLength; i < combinations.length(); i++){

            store.add(combinations.substring(i-subLength, i));

        }
        String[] stb = new String[store.size()]; stb = store.toArray(stb);

		BinaryST st = new BinaryST(sts);

		System.out.println(st.toString());
		System.out.println("Distince: "+st.distinctSize());
		System.out.println("totalSize: "+st.size());
		System.out.println("Height: "+st.height());
		System.out.println("Recurrences: "+st.frequency("ZX"));

		//Remove testing
		System.out.print(st.remove("FR"));
		System.out.print("\n Recurrence After Remove: "+st.frequency("FR"));
		System.out.print("\n"+st.toString());


		//System.out.println(st.findNode("BC").count);

		String[] inOrder = st.preOrder();

		for(String s : inOrder){
			System.out.print(s+" ");
		}
		System.out.println("\nFreq:"+st.frequency("AF"));

		System.out.println("Rank:"+st.rankOf("BC"));
		System.out.println("Distince Size"+ st.distinctSize());
		System.out.println("Total Size: "+st.size());

		System.out.println("Freq: "+st.frequency("FR"));
//


        //testWars();



		//43510
		//82511
		//154264
		//33541
		//38769
	}

	public static void testWars(){

        String combinations = "ACAAGATGCCATTGTCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCCCCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCC"; //AGGCCAGTGCCGGGCCCCTCATAGGAGAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGG;//TGGTTTGAGTGGACCTCCCAGGCCAGTGCCGGGCCCCTCATAGGAGAGGCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCACGGCCACCGCTGCCCTGCCCCTGGAGGGTGGCCCCACCGGCCGAGACAGCGAGCATATGCAGGAAGCGGCAGGAATAAGGAAAAGCAGCCTCCTGACTTTCCTCGCTTGGTGGTTTGAGTGGACCTCCCAGGCCAG";
        ArrayList<String> store = new ArrayList<String>();
        int subLength = 30;
        for(int i = subLength; i < combinations.length(); i++){

            store.add(combinations.substring(i-subLength, i));

        }

        System.out.println("K-Length array size: "+store.size()+"\n\n");

        //String[] stb = new String[]{"AA", "AC", "CC"};
        //String[] stb = new String[]{"ABC", "CDE","BCD", "EFG","DEF", "GHI","FGH","AAG","AGC","AGA","AGB"};
        String[] stb = new String[store.size()]; stb = store.toArray(stb);
        //String[] stb = new String[]{"A","B", "C", "D"};
        //ABCD
        //ABCDEFGH
        //String[] stb = new String[]{"AB","BC","CD","EF","FG","GH"};
        StringBuilder sb = new StringBuilder();


        int length = stb[0].length();
        ArrayCompute arr = new ArrayCompute(stb,length);
        BinarySearchTreeCompute bst = new BinarySearchTreeCompute(stb,length);
        nonRollingHashCompute hsh = new nonRollingHashCompute(stb,length);
        RollHashCompute rlhash = new RollHashCompute(stb,length);

        //ABCD
        //WarWithArray arrays = new WarWithArray(stb, 2);
        //WarWithBST arrays = new WarWithBST(stb, stb[0].length());
        //WarWithRollHash arrays = new WarWithRollHash(stb,3);

        //for(toArrayInterface inf : algos) {
            long averageTime = 0;
            ArrayList<String> timeTest = new ArrayList<String>();
            //for(int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            timeTest = rlhash.compute2k();
            long endTime = System.nanoTime();
            long totalTime = (endTime - startTime)/3600;

            averageTime += totalTime;
            //System.out.println(timeTest);
            //System.out.println();
            //}
            //System.out.println(bst.algorithm());
            System.out.println("Average Time: "+averageTime/10);
            System.out.println("2kLength Array: "+timeTest);
            System.out.println("\n\nArraySize: " +timeTest.size());
            System.out.println("___________________________________________");
        //}
    }




}
