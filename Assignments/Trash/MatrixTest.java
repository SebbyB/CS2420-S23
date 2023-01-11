package Trash;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * 
 * @author CS 2420 staff and Sebastian Barney
 * @version August , 2022
 *
 */
class MatrixTest {
	
	
//Warning... don't set n too high. Not because the tests will fail, but because it'll make the tests take forever to complete.
	// n = 50 is a pretty good sample size, but feel free to put it at 100000 and kill your computer.
	int n = 50,m;

	/* ******equals tests****** */
	@Test
	void testModerateMatricesEqual() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});

		Matrix m2 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});

		assertEquals(m1, m2);
	}

	/**
	 * Shows that m1 should equal m2, even if both change the values of their entries to a set value.
	 * Also shows m1 never equals m2 if a row is out of place.
	 */
	@Test
	void testBigEmptyChangingMatrix() {
		
		Matrix m1 = new Matrix(n);
		Matrix m2 = new Matrix(n);
		assertEquals(m1, m2);
		for (int i = 0; i < n; i++) {
			m1.setMatrix(i);
			m2.setMatrix(i);
			assertEquals(m1, m2);
		}
		m2 = new Matrix(n);
		m2.setRowEntries(1,3);
		for (int i = 0; i < n; i++) {
			m1.setMatrix(i);
			assertNotEquals(m1, m2);
		}
	}

	/**
	 * Shows that the method returns false when just one entry is different.
	 */
	@Test
	void testIfSingleEntryChanged(){
		
		Matrix m1 = new Matrix(n);
		Matrix m2 = new Matrix(n);
		assertEquals(m1, m2);
		for (int i = 0; i < n; i++) {
			m1.setMatrix(i);
			m1.setEntry(0,0,n+1);
			m2.setMatrix(i);
			assertNotEquals(m1, m2);
		}
	}

	/**
	 * Shows the method returns false if the size of the matrices are different.
	 */
	@Test
	void testIfDifferentSize(){
		
		Matrix m1 = new Matrix(n);
		Matrix m2 = new Matrix(n+1);
		assertNotEquals(m1,m2);

	}
	/* ******end equals tests****** */


	/* ******toString tests****** */
	@Test
	void testModerateToString() {
		Matrix m = new Matrix(new int[][] {
			{1, 2},
			{3, 4}
		});

		assertEquals("1 2\n3 4\n", m.toString());
	}

	/**
	 * Tests that any nxn matrix can be turned into a string.
	 */
	@Test
	void testNToString(){
		

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(j !=n-1)builder.append("1 ");
				else builder.append("1\n");
			}
		}		Matrix m1 = new Matrix(n);
		m1.setMatrix(1);
		assertEquals(m1.toString(),builder.toString());
	}

	/**
	 * Tests that any nxm matrix can be turned into a string.
	 */
	@Test
	void testNbyMToString(){
		m = n+1;
		Matrix m1 = new Matrix(n,m);
		m1.setMatrix(1);
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if(j !=m)builder.append("1 ");
				else builder.append("1\n");
			}
		}
		assertEquals(m1.toString(),builder.toString());
	}


	/* ******end toString tests****** */



	/* ******times tests****** */
	@Test
	void testCompatibleTimes() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2, 3},
				{2, 5, 6}});

		Matrix m2 = new Matrix(new int[][]
				{{4, 5},
				{3, 2},
				{1, 1}});

		// the known correct result of multiplying m1 by m2
		int[][] expected = new int[][]
				{{13, 12},
				{29, 26}};
		
		// the result produced by the times method
		Matrix mulResult = m1.times(m2);
		int[][] resultArray = mulResult.getData();
		
		
		// compare the raw arrays
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}

	/**
	 * The two matrices are nxn and mxm matrices where n!=m
	 * This means they are incompatible and thus cannot be multiplied.
	 */
	@Test
	void testIncompatibleTimes(){

		Matrix m1 = new Matrix(5);
		Matrix m2 = new Matrix(3);
		assertThrows(IllegalArgumentException.class, () -> { m1.times(m2); });
	}

	/**
	 * Creates a 3x3 Identity matrix and shows that it, times some scaled version of itself, 
	 * will be equal to the scaled version.
	 */
	@Test
	void testIdentities3by3(){
		
		Matrix identity = new Matrix(new int[][]{
				{1,0,0},
				{0,1,0},
				{0,0,1}
		});
		for(int i = 0; i < n; i++){
			Matrix m1 = new Matrix(identity);
			Matrix m2 = new Matrix(identity);
			m2.multiply(n);
			assertEquals(m1.times(m2),m2);
		}
	}
	/**
	 * Creates a 4x4 Identity matrix and shows that it, times some scaled version of itself, 
	 * will be equal to the scaled version.
	 */
	@Test
	void testIdentities4by4(){
		
		Matrix identity = new Matrix(new int[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}
		});
		for(int i = 0; i < n; i++){
			Matrix m1 = new Matrix(identity);
			Matrix m2 = new Matrix(identity);
			m2.multiply(n);
			assertEquals(m1.times(m2),m2);
		}
	}


	/**
	 * Multiplies two matrices with all entries holding a value of one. 
	 * One should be nxm, the other should be mxn.
	 * The resulting matrix should be a nxn matrix with all entries holding a value of m.
	 *.
	 * Consider the dot product of two vectors r and c.
	 * In order to dot two vectors they must be the same size, meaning if all entries are the same
	 * r.c will equal the length of the vector * the value of the entry.
	 * Matrix multiplication dots column vectors with row vectors, both have to be of size m.
	 * this means in our case the entries need to be m.
	 */
	@Test
	void testNbyM(){
		 m = n+1;
		Matrix m1 = new Matrix(n,m), m2 = new Matrix(m,n),m3 = new Matrix(n);
		m1.setMatrix(1);
		m2.setMatrix(1);
		m3.setMatrix(m);

		assertEquals(m1.getNumColumns(),m);
		assertEquals(m1.getNumRows(),n);
		assertEquals(m2.getNumRows(),m);
		assertEquals(m2.getNumColumns(),n);
		assertEquals(m3.getNumRows(),n);
		assertEquals(m3.getNumColumns(),n);

		assertEquals(m1.times(m2).getNumColumns(),n);
		assertEquals(m1.times(m2).getNumRows(),n);
		assertEquals(m1.times(m2),m3);

	}

	/**
	 * Multiplies two nxn matrices with all entries holding a value of one.
	 * The resulting matrix should be a nxn matrix with all entries holding a value of n.
	 */
	@Test
	void dotN(){

		Matrix m1 = new Matrix(n), m2 = new Matrix(n), m3 = new Matrix(n);
		m1.setMatrix(1);
		m2.setMatrix(1);
		m3.setMatrix(n);
		assertEquals(m1.times(m2),m3);


	}

	/* ******end times tests****** */
	/* ******multiply tests****** */

	/**
	 * Shows that a matrix can be scaled.
	 */
	@Test
	void scaleByN(){
		
		Matrix ones = new Matrix(2);
		ones.setMatrix(1);

		for(int i = 1; i <= n; i++){
			Matrix m1 = new Matrix(2);
			m1.setMatrix(i);
			Matrix m2 = new Matrix(ones);
			assertEquals(m1,m2.multiply(i));
		}
	}

	/* ******end multiply tests****** */



	/* ******plus tests****** */
	@Test
	public void testIncompatiblePlus() {	
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});

		Matrix m2 = new Matrix(new int[][] {
			{2, 2},
			{2, 2}
		});
		
		// This is an example of how to test that an exception is thrown when needed.
		// The odd syntax below is an example of a lambda expression.
		// See Lab 1 for more info.
		assertThrows(IllegalArgumentException.class, () -> { m1.plus(m2); });  
	}

	/**
	 * Shows two matrices of whatever compatible size can be added together.
	 */
	@Test
	void addTwoMatricesSizeN(){
		
		Matrix m1 = new Matrix(n);
		Matrix m2 = new Matrix(n);
		Matrix m3 = new Matrix(n);
		m1.setMatrix(1);
		m2.setMatrix(1);
		m3.setMatrix(2);
		assertEquals(m1.plus(m2),m3);

	}

	/**
	 * Shows two matrices with negative entries can be added.
	 */
	@Test
	void addTwoMatricesSizeNNegatives(){
		
		Matrix m1 = new Matrix(n);
		Matrix m2 = new Matrix(n);
		Matrix m3 = new Matrix(n);
		m1.setMatrix(-1);
		m2.setMatrix(-1);
		m3.setMatrix(-2);
		assertEquals(m1.plus(m2),m3);

	}

	/**
	 * Shows combinations on positive and negative matrices can be added.
	 */
	@Test
	void addTwoMatricesSizeNPositivesAndNegatives(){
		
		Matrix m1 = new Matrix(n), m2 = new Matrix(n), m3 = new Matrix(n);
		m1.setMatrix(-1);
		m2.setMatrix(1);
		assertEquals(m1.plus(m2),m3);

	}

	/**
	 * sum from 1 to n.
	 * @param n - n
	 * @return sum
	 */
	int sumToN(int n){
		int sum = 0;
		for (int i = n; i > 0; i--){
			sum+= i;
		}
		return sum;
	}

	/**
	 * Shows that a bunch of matrices can be added together, both one by one or as a tensor.
	 */
	@Test
	void addNMatricesSizeN(){
		int	result = sumToN(n);
		Matrix resultMat = new Matrix(n),zeros1 = new Matrix(n),zeros2=new Matrix(n);
		Matrix[] matArr1 = new Matrix[n], matArr2 = new Matrix[n];

		resultMat.setMatrix(result);
		for (int i = 1; i <= n; i++){
			matArr1[i-1] = new Matrix(n);
			matArr2[i-1] = new Matrix(n);
			matArr1[i-1].setMatrix(i);
			matArr2[i-1].setMatrix(i);
		}
		zeros1 = zeros1.plus(matArr1);
		assertEquals(resultMat,zeros1);
		for(int i = 0; i < n; i++){
			zeros2 = zeros2.plus(matArr2[i]);
		}
		assertEquals(zeros2,resultMat);
		assertEquals(zeros2,zeros1);

	}

	/**
	 * Shows the same as above but with negative numbers.
	 */
	@Test
	void addNMatricesSizeNNegatives(){
		int result = sumToN(n)*-1;
		Matrix resultMat = new Matrix(n),zeros1 = new Matrix(n),zeros2=new Matrix(n);
		Matrix[] matArr1 = new Matrix[n], matArr2 = new Matrix[n];

		resultMat.setMatrix(result);
		for (int i = 1; i <= n; i++){
			matArr1[i-1] = new Matrix(n);
			matArr2[i-1] = new Matrix(n);
			matArr1[i-1].setMatrix(i*-1);
			matArr2[i-1].setMatrix(i*-1);
		}
		zeros1 = zeros1.plus(matArr1);
		assertEquals(resultMat,zeros1);
		for(int i = 0; i < n; i++){
			zeros2 = zeros2.plus(matArr2[i]);
		}
		assertEquals(zeros2,resultMat);
		assertEquals(zeros2,zeros1);

	}
	
	/* ******end plus tests****** */
	
	
	
	/* ******transpose tests****** */
	@Test
	public void testSmallTranspose() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});

		int expected[][] = {
				{1, 1},
				{1, 1},
				{1, 1}
		};
		
		Matrix t = m1.transpose();
		int resultArray[][] = t.getData();
		
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}

	/**
	 * Shows any matrix of any arbitrary size can be transposed.
	 */
	@Test
	void transposeNbyNMatrix(){
		 m = n+1;
		for (int i = 1; i <= n; i++) {
			Matrix m1 = new Matrix(n, m), m2 = new Matrix(m, n);
			m1.setMatrix(i);
			m2.setMatrix(i);
			assertNotEquals(m1,m2);
			assertEquals(m1,m2.transpose());
			assertEquals(m2,m1.transpose());
		}
	}
	
	/* ******end transpose tests****** */
	
	
	
	/* ****** Constructor tests****** */

	/**
	 * Creates a new matrix of all zeros using the provided constructor and the custom sizen constructor.
	 * It then compares the two matrices.
	 */
	@Test
	void sizeTest(){
		int[][] backingArr = new int[n][n];
		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				backingArr[i][j] = 0;
			}
		}
		Matrix m1 = new Matrix(n),m2 = new Matrix(backingArr);
		assertEquals(m1,m2);
		
	}
	/**
	 * Creates a new matrix of all zeros using the provided constructor and the custom size nxm constructor.
	 * It then compares the two matrices.
	 */
	@Test
	void testRowColumn(){
		m = n+1;
		int[][] backingArr = new int[n][m];
		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				backingArr[i][j] = 0;
			}
		}
		Matrix m1 = new Matrix(n,m),m2 = new Matrix(backingArr);
		assertEquals(m1,m2);
		
	}
	/**
	 * Creates a new matrix of all zeros using the provided constructor and the constructor that creates a new matrix from an old one..
	 * It then compares the two matrices.
	 */
	@Test
	void testMatrix(){
		m = n+1;
		int[][] backingArr = new int[n][m];
		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				backingArr[i][j] = 0;
			}
		}
		Matrix m1 = new Matrix(backingArr),m2 = new Matrix(m1);
		assertEquals(m1,m2);

	}
	/* ****** end Constructor tests****** */
	/* ****** set and get tests****** */

	/**
	 * Shows that the setEntry method will set an entry at some place in the matrix, and only that space.
	 */
	@Test
	void testSetEntry(){
		Matrix m1 = new Matrix(n);
		m1.setEntry(n-1,n-1,1);
		int[][] backingArr = m1.getData();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i == n - 1) && j == i) {
					assertEquals(backingArr[n - 1][n - 1], 1);
				} else {
					assertEquals(backingArr[n - 2][n - 1], 0);
				}
			}
		}
	}

	/**
	 * Shows that the getEntry method will return the proper value from the matrix.
	 */
	@Test
	void testGetEntry(){
		Matrix m1 = new Matrix(n);
		m1.setEntry(n-1,n-1,1);
		int[][] backingArr = m1.getData();
		assertEquals(backingArr[n-1][n-1],1);
		assertEquals(backingArr[n-1][n-1],m1.getEntry(n-1,n-1));
	}


	/**
	 * Shows that the getColumn and setColumn methods work for both arrays of entries and single entries.
	 * It does this by creating a matrix, m1, and an array of the appropriate size. It then populates the appropriate entries using each type of method.
	 * It then checks if the vector is equal to the entries in the array using each method.
	 */
	@Test
	void testGetSetColumn(){
		Matrix m1 = new Matrix(n);
		int[] column = new int[n];
		for (int i = 0; i < n; i++){
			column[i] = 1;
		}
		for(int j = 0; j < n; j++){
			m1.setEntry(j,n-3,1);
		}
		m1.setColumnEntries(n-2,1);
		m1.setColumn(n-1,column);
		assertEquals(n,m1.getColumn(n-3).length);
		assertEquals(n,m1.getColumn(n-2).length);
		assertEquals(n,m1.getColumn(n-1).length);
		int[][] backingArr = m1.getData();
		for (int i = 0; i < n; i++){
			assertEquals(backingArr[i][n-1],column[i]);
			assertEquals(backingArr[i][n-2],column[i]);
			assertEquals(backingArr[i][n-3],column[i]);
			assertEquals(backingArr[i][n-3],m1.getColumn(n-3)[i]);
			assertEquals(backingArr[i][n-2],m1.getColumn(n-2)[i]);
			assertEquals(backingArr[i][n-1],m1.getColumn(n-1)[i]);
		}



	}

	/**
	 * Shows that the getRow and setRow methods work for both arrays of entries and single entries.
	 */
	@Test
	void testGetSetRow(){
		Matrix m1 = new Matrix(n);
		int[] row = new int[n];
		for (int i = 0; i < n; i++){
			row[i] = 1;
		}
		for(int j = 0; j < n; j++){
			m1.setEntry(n-3,j,1);
		}
		m1.setRowEntries(n-2,1);
		m1.setRow(n-1,row);

		assertEquals(n,m1.getRow(n-3).length);
		assertEquals(n,m1.getRow(n-2).length);
		assertEquals(n,m1.getRow(n-1).length);
		int[][] backingArr = m1.getData();
		for (int i = 0; i < n; i++){
			assertEquals(backingArr[n-1][i],row[i]);
			assertEquals(backingArr[n-2][i],row[i]);
			assertEquals(backingArr[n-3][i],row[i]);
			assertEquals(backingArr[n-3][i],m1.getRow(n-2)[i]);
			assertEquals(backingArr[n-2][i],m1.getRow(n-2)[i]);
			assertEquals(backingArr[n-1][i],m1.getRow(n-1)[i]);
		}


	}
	@Test
	void testSetMatrix(){
		Matrix m1 = new Matrix(n);
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++){
			for(int j = 0; j < n; j++) {
				mat[i][j] = 1;
			}
		}
		m1.setMatrix(1);
		assertEquals(m1,new Matrix(mat));
	}


	/* ******end set and get tests****** */


}
