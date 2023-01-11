package Trash;

/**
 * This class represents a 2D matrix and simple operations on them.
 *
 * @author Daniel Kopta and Sebastian Barney
 * @version August 08, 2021
 *
 */

public class Matrix {


    // the dimensions of the matrix
    private int numRows;
    private int numColumns;
    private int[][] data;

    /**
     * Constructor for a matrix that creates a nxm matrix of all zeros.
     * @param numRows - number of rows the matrix has.
     * @param numColumns - number of columns the matrix has.
     */
    public Matrix(int numRows, int numColumns){
        //Initializes the backing array to have the correct number of rows and columns.
        this.data = new int[numRows][numColumns];
        //Initializes the internal row and column variables to the correct size.
        this.numRows = numRows;
        this.numColumns = numColumns;
        //Changes the values of every entry to 0.
        for (int i = 0; i < numRows; i++){
            for(int j = 0; j < numColumns; j++){
                this.setEntry(i,j,0);
            }
        }
    }

    /**
     * Constructor for a square matrix of a specific size of all zeros.
     * @param size - size of the matrix.
     */
    public Matrix(int size){
        //Initializes the backing array to have the correct number of rows and columns.
        //Initializes the internal row and column variables to the correct size.
        this.numRows = size;
        this.numColumns = size;
        //Changes the values of every entry to 0.
        this.data = new int[size][size];
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.setEntry(i,j,0);
            }
        }
    }

    /**
     * Constructor for a matrix that takes another instance of a matrix
     * @param matrix - the matrix you would like a new instance of.
     */
    public Matrix(Matrix matrix){
        this.data = matrix.getData();
        this.numRows = matrix.getNumRows();
        this.numColumns = matrix.getNumColumns();
    }

    /**
     * DO NOT MODIFY THIS METHOD.
     * Constructor for a new Matrix. Automatically determines dimensions.
     * This is implemented for you.
     * @param d - the raw 2D array containing the initial values for the Matrix.
     */
    public Matrix(int d[][])
    {
        // d.length is the number of 1D arrays in the 2D array
        this.numRows = d.length;
        if(numRows == 0)
            this.numColumns = 0;
        else
            this.numColumns = d[0].length; // d[0] is the first 1D array

        // create a new matrix to hold the data
        this.data = new int[numRows][numColumns];

        // copy the data over
        for(int i=0; i < numRows; i++)
            for(int j=0; j < numColumns; j++)
                data[i][j] = d[i][j];
    }

    /**
     * Private getter method for getting the backing array of a matrix.
     */
    public int[][] getData()
    {
        int[][] retVal = new int[data.length][];
        for(int i = 0; i < data.length; i++)
            retVal[i] = data[i].clone();
        return retVal;
    }


    /**
     * Getter Method for the number of rows in a matrix.
     * @return number of rows a matrix has
     */
    public int getNumRows(){
        return this.numRows;
    }
    /**
     * Getter Method for the number of columns in a matrix.
     * @return number of columns a matrix has
     */
    public int getNumColumns(){
        return this.numColumns;
    }

    /**
     * Sets a matrix to one specific entry.
     * @param entry - desired entry for the matrix
     */
    public void setMatrix(int entry){

        for (int i = 0; i < this.getNumRows(); i++){
            for (int j = 0; j < this.getNumColumns(); j++){
                this.setEntry(i,j,entry);
            }
        }
    }

    /**
     * Places an entry at a desired row and column within the matrix.
     * @param row - Desired row for placement.
     * @param column - Desired column for placement.
     * @param entry - Desired entry.
     * @throws IndexOutOfBoundsException if the desired row or column is not in the matrix.
     */
    public void setEntry(int row, int column, int entry)throws IndexOutOfBoundsException{
        //Checks if the rows and columns are valid.
        if(this.getNumColumns() < column || this.getNumRows() < row){
            //if not throws an exception.
            throw new IndexOutOfBoundsException();
        }
        //otherwise sets the backing array
        this.data[row][column] = entry;
    }

    /**
     * Gets the entry at a row and column.
     * @param row - Desired row.
     * @param column - Desired Column
     * @return desired Entry.
     */
    public int getEntry(int row, int column){
        //Checks if the rows and columns are valid.
        if(this.getNumColumns() < column || this.getNumRows() < row){
            //if not throws an exception.
            throw new IndexOutOfBoundsException();
        }
        else {
            //grabs the item from the backing array.
            return this.data[row][column];
        }
    }

    /**
     * Sets an entire row to be a specific entry.
     * @param row - Desired row for placement.
     * @param entry - Desired entry.
     */
    public void setRowEntries(int row, int entry){
        for(int i = 0; i < this.getNumColumns(); i++){
            setEntry(row,i,entry);
        }
    }

    /**
     * Sets an entire row to a list of desired entries.
     * @param row - Desired row for placement.
     * @param entries - list of desired entries.
     */
    public void setRow(int row, int [] entries) {
        //Checks for appropriate rows and columns sizes. If everything is correct it sets the row to the entries.
        int numColumns = entries.length;
        if (numColumns == this.getNumColumns()) {
            for (int i = 0; i < numColumns; i++) {
                setEntry(row, i, entries[i]);
            }
        } else if (row > this.getNumRows()) {
            throw new IndexOutOfBoundsException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets an entire column to a list of desired entries.
     * @param column - Desired column for placement.
     * @param entries - list of desired entries.
     */
    public void setColumn(int column, int [] entries) {
        int numRows = entries.length;
        if (numRows == this.getNumRows()) {
            for (int i = 0; i < numRows; i++) {
                setEntry(i, column, entries[i]);
            }
        } else if (column > this.getNumColumns()) {
            throw new IndexOutOfBoundsException();
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void setColumnEntries(int column, int entry){
        for(int i = 0; i < this.getNumRows(); i++){
            setEntry(i,column,entry);

        }
    }

    /**
     * Gets the row of a matrix.
     * @param row - Desired row index.
     * @return array representation of a matrix row.
     */
    public int[] getRow(int row) {
        int numColumns = this.getNumColumns();
        //Checks if the row is in the matrix.
        if (this.getNumRows() < row) {
            //if not throws exception
            throw new IndexOutOfBoundsException();
        }
        else {
            //otherwise returns the row.
            int[] matrixRow = new int[numColumns];
            for (int i = 0; i < numColumns; i++){
                matrixRow[i] = this.getEntry(row,i);
            }
            return matrixRow;
        }
    }

    /**
     * Gets the column of a matrix.
     * @param column - Desired column index.
     * @return array representation of a matrix column
     */
    public int[] getColumn(int column) {
        int numRows = this.getNumRows();
        //Checks if the rox is in the matrix.
        if (this.getNumColumns() < column) {
            //if not throws exception.
            throw new IndexOutOfBoundsException();
        }
        else {
            //otherwise returns the column.
            int[] matrixColumn = new int[numRows];
            for (int i = 0; i < numRows; i++){
                matrixColumn[i] = this.getEntry(i,column);
            }
            return matrixColumn;
        }
    }

    /**
     * Private helper method that adds the entries of two arrays.
     * @param arr1 - first array.
     * @param arr2 - second array.
     * @return vector sum.
     */
    private int[] addArray(int[] arr1, int[] arr2){
        //checks if the arrays are the same size.
        if(arr1.length == arr2.length){
            //if they are adds each element and return.
            int size = arr1.length;
            int[] retArr = new int[size];
        for (int i = 0; i < size; i++){
            retArr[i] = arr1[i] + arr2[i];
        }
        return retArr;
        }
        else {
            //otherwise throw exception.
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adds together a list of matrices.
     * @param matrices - list of matrices to be added.
     * @return - a matrix that is a sum of the list.
     */
    public Matrix plus(Matrix[] matrices){

        int numRows = this.getNumRows();
        int numColumns = this.getNumColumns();
        Matrix returnMatrix = new Matrix(this);
        for(Matrix matrix : matrices){
            if(matrix.getNumColumns() != numColumns || matrix.getNumRows() != numRows){
                throw new IllegalArgumentException();
            }else{
                for(int i = 0; i < numRows;i++){
                    returnMatrix.setRow(i,addArray(returnMatrix.getRow(i),matrix.getRow(i)));
                }
            }
        }
        return returnMatrix;
    }

    /**
     * Adds two matrices.
     * @param matrix - matrix to be added.
     * @return sum of two matrices.
     */
    public Matrix plus(Matrix matrix){
        int numRows = this.getNumRows();
        int numColumns = this.getNumColumns();
        Matrix returnMatrix = new Matrix(numRows,numColumns);
            if(matrix.getNumColumns() != numColumns || matrix.getNumRows() != numRows){
                throw new IllegalArgumentException();}
            else{
                for(int i = 0; i < numRows;i++){
                    returnMatrix.setRow(i,addArray(this.getRow(i),matrix.getRow(i)));
                }
            }
       return returnMatrix;

    }

    /**
     * Transposes this matrix.
     * @return Transposed matrix.
     */
    public Matrix transpose(){
        int numRows = this.getNumRows();
        int numColumns = this.getNumColumns();
        Matrix returnMatrix = new Matrix(numColumns,numRows);
            for(int i = 0; i < numColumns;i++){
                returnMatrix.setRow(i,this.getColumn(i));
            }
        return returnMatrix;
    }

    /**
     * Private helper method that Dots two vectors.
     * @param arr1 - vector 1.
     * @param arr2 - vector 2.
     * @return dot product of the two vectors.
     */
    private int dot(int [] arr1, int[] arr2){
        //running total
        int dotProduct = 0;
        //standard dot product.
        for(int i =0; i < arr1.length; i++){
            dotProduct+= arr1[i]*arr2[i];
        }
        return dotProduct;
    }

    /**
     * Multiplies two matrices.
     * @param matrix - matrix to be multiplied.
     * @return product of two matrices.
     */
    public Matrix times(Matrix matrix) {
        int numRows1 = this.getNumRows();
        int numColumns1 = this.getNumColumns();
        int numRows2 = matrix.getNumRows();
        int numColumns2 = matrix.getNumColumns();
        int numColumns = numRows1;
        int numRows = numColumns2;
        if (numColumns1 == numRows2) {
            Matrix returnMatrix = new Matrix(numRows, numColumns);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++){
                    int[] row = this.getRow(i);
                    int[] column = matrix.getColumn(j);
                    int entry = dot(row,column);
                    returnMatrix.setEntry(i,j,entry);
                }
            }
            return returnMatrix;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Private helper method.
     * Scales the vector by a factor n.
     * @param arr - vector but an array.
     * @param n - factor n.
     * @return - scaled vector.
     */
    private int[] multiplyArr(int[] arr,double n){
        for(int i = 0; i < arr.length; i++){
            arr[i] *= n;
        }
        return arr;
    }

    /**
     * Scales a matrix by a factor n.
     * @param n - factor n.
     * @return Scaled matrix.
     */
    public Matrix multiply(int n) {
        int numRows = this.getNumRows();
        int numColumns = this.getNumColumns();
        Matrix retMatrix = new Matrix(numRows,numColumns);
        for(int i = 0; i < numRows; i++){
            int[] newRow = this.getRow(i);
            retMatrix.setRow(i,multiplyArr(newRow,n));
        }
        return retMatrix;
    }




    /**
     * Prints the matrix in a cool way.
     */
    public void printMatrix() {
        String returnString;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < this.getNumRows(); i++) {
            builder.append("| ");
            for(int j = 0; j < this.getNumColumns(); j++){
                builder.append(this.getEntry(i,j));
                if (j != this.getNumColumns() - 1) {
                    builder.append(", ");
                }
            }
            builder.append(" |");
            builder.append("\n");
        }
        returnString = builder.toString();
        System.out.println( builder.toString());
    }

    /**
     * Determines whether this Matrix is equal to another object.
     * @param o - the other object to compare to, which may not be a Matrix
     */
    @Override // instruct the compiler that we intend for this method to override the superclass' (Object) version
    public boolean equals(Object o){
        if(o instanceof Matrix){
            Matrix mat = (Matrix) o;
            if(mat.getNumColumns() == this.getNumColumns() && mat.getNumRows() == this.getNumRows()){
                for(int i = 0; i < this.getNumRows(); i++){
                    for(int j = 0; j < this.getNumColumns(); j++){
                        if (this.getEntry(i,j) != mat.getEntry(i,j)){
                            return false;
                        }
                    }
                }
                return true;
            }
            else return false;

        }
        else return false;
    }
    /**
     * Returns a String representation of this Matrix.
     */
    @Override // instruct the compiler that we intend for this method to override the superclass' (Object) version
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i =0; i < this.getNumRows(); i++){
            for (int j = 0; j < this.getNumColumns(); j++) {
                if (j != this.getNumColumns() - 1) {
                    builder.append(this.getEntry(i, j));
                    builder.append(" ");
                } else {
                    builder.append(this.getEntry(i, j));
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }
}
