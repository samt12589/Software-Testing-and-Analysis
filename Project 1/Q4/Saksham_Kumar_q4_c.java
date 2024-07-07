import org.junit.*;


public class Saksham_Kumar_q4_c {

    /* add your test code here */

	/* In these test cases we see that the test cases input overlap as there are limited number 
	 * of test paths to run but they satisfiy all the feasable paths for all the coverage types.
	 */
	
	/*
	 * Node Coverage
	 * 
	 All the nodes are covered in these 2 node coverage tests. 
	 */

	 // Test path [1,2,3,4,5,8,10,11]
	/*
	 * Here we enter the function with the least amount of requirements empty string and i = 0
	 */
	@Test 
	public void NodeCoverageTest1() {
		M item = new M();
		item.m("", 0);
		

	 }

	 /*
	  * Here we are trying to traverse through the other path that will cover the rest of the missing nodes.
	  */

	 // Test Path [1,2,3,6,7,8,9,11]
	@Test
	public void NodeCoverageTest2() {
		M item = new M();
		item.m("Aa", 0);
		
	 }

	 /*
	  * Edge Coverage
	  * Here we are covering all the edges of the CFG as drawn and try to cover all possible paths.
	  */
	 // Test path [1,2,3,5,8,9,11]
	 /*
	  * in test 1 we cover the first missed edge from 3 to 5 by giving input as a single character
	  */
	  @Test
	  public void EdgeCoverageTest1() { 
		M item = new M();
		item.m("A", 0);
	  }

	  /*
	   * Here we are covering the next missed edge of the graph 3 to 7 the default one
	   */
	  // Test path [1,2,3,7,8,9,11]
	  @Test
	  public void EdgeCoverageTest2() {
		M item = new M();
		item.m("AaBbCc",0);
	  }

	  /*
	   * Now we cover the left over edges to be covered for 1 to 3 and 3 to 6
	   */
	  //  Test Path    [1,3,6,7,8,9,11]
	  @Test
	  public void EdgeCoverageTest3() {
		M item = new M();
		item.m("Aa",1);
	  }

	  /*
	   * covering the missing edge 3 to 4 for empty input
	   */
	 // Test Path [1,3,4,5,8,10,11]
	  @Test
	  public void EdgeCoverageTest4() {
		M item = new M();
		item.m("",1);
	  }
	  /*
	   * Edge Pair Coverage
	   * 
	   * It isn't completely possible to satisfy just the edge pair coverages and not have prime path
	   * coverages thererfore i have written a few coverages and their test paths as follows
	   */
	  // Test Path [1,2,3,7,8,9,11]
	  // Pairs covered [1,2,3], [2,3,7], [3,7,8], [7,8,9], [8,9,11]
	  @Test
	  public void EdgePairCoverageTest1() {
			M item = new M();
			item.m("SAKSHAM",0);
	  }
	  // Test Path  [1,3,7,8,9,11]
	  // Pairs covered [1,3,7], [3,7,8], [7,8,9], [8,9,11]
	  @Test
	  public void EdgePairCoverageTest2() {
		M item = new M();
		item.m("SAKSHAM",1);
	  }
	  /*
	   * Now as we don't have any feasible test paths for edge-pair that are not in the prime paths 
	   * it isn't possible to make a test case that isn't a prime path.
	   * Prime paths subsumes the other coverages so test cases will overlap.
	   * Except the infeasable test paths for Edge pair every other pair should be covered in the prime paths tested.
	   *
	   */
	   
	  /* Test Paths [1,2,3,4,5,8,10,11] test 1
	   * 			[1,3,6,7,8,9,11]  test 2
	   * 
	   * These are for the prime path coverages and most of the prime paths are already covered in the 
	   * Test cases above so  have covered the longest ones here.
	   */
	  @Test
	  public void PrimePathCoverageTest1() {
		M item = new M();
		item.m("",0);
	  }

	  @Test
	  public void PrimePathCoverageTest2() {
		M item = new M();
		item.m("Ee",1);
	  }



	

    
}

class M {
	public static void main(String [] argv){
		M obj = new M();
		if (argv.length > 0)
			obj.m(argv[0], argv.length);
	}

	public void m(String arg, int i) {
		int q = 1;
		A o = null;
		Impossible none = new Impossible();
		if (i == 0)
			q = 4;
		q++;
		switch (arg.length()) {
			case 0: q /= 2;
			case 1: o = new A(); new B(); q *= 3; break;
			case 2: o = new A(); q = q * 100;
			default: o = new B(); break;
		}
		if (arg.length() > 0) {
			o.m();
		} else {
			System.out.println("zero");
		}
		none.happened();
	}
}

class A {
	public void m() {
		System.out.println("a");
	}
}

class B extends A {
	public void m() {
		System.out.println("b");
	}
}

class Impossible{
	public void happened() {
		// "2b||!2b?", whatever the answer nothing happens here
	}
}

