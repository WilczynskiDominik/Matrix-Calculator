import java.text.DecimalFormat;

public class Matrix {
    private int columnsValue;
    private int rowsValue;
    private float[][] matrix;
    private boolean isMatrixCreated = false;

    //Getters
    public int getColumnsValue() {
        return columnsValue;
    }
    public int getRowsValue() {
        return rowsValue;
    }
    public float[][] getMatrix() {
        return matrix;
    }

    //Setters
    public void setColumnsValue(int columnsValue) {
        this.columnsValue = columnsValue;
    }
    public void setRowsValue(int rowsValue) {
        this.rowsValue = rowsValue;
    }
    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    //Boolean
    public boolean isMatrixCreated() {
        return isMatrixCreated;
    }
    public void setMatrixCreated(boolean matrixCreated) {
        isMatrixCreated = matrixCreated;
    }

    //Methods
    public void createMatrix(){
        this.matrix = new float[this.columnsValue][this.rowsValue];
        this.isMatrixCreated = true;
    }
    public void createMatrix(int columns, int rows){
        this.columnsValue = columns;
        this.rowsValue = rows;
        this.matrix = new float[columns][rows];
        this.isMatrixCreated = true;
    }
    public void deleteMatrix(){
        this.matrix = null;
        this.columnsValue = 0;
        this.rowsValue = 0;
        this.isMatrixCreated = false;
    }
    public void printMatrix(){
        //Used to format printed values
        DecimalFormat decimalFormat = new DecimalFormat("#0.000");

        if(!this.isMatrixCreated){
            return;
        }
        //Print columns numbers
        System.out.printf("%-10s","");
        for(int i = 0; i < this.columnsValue; i++){
            System.out.printf("%-8s", (i + 1));
        }
        System.out.println();
        //

        //Print matrix
        for(int i = 0; i < this.rowsValue; i++){
            System.out.print((i + 1) + "\t|\t");
            for(int j = 0; j < this.columnsValue; j++){
                System.out.printf("%-8s" ,decimalFormat.format(matrix[j][i]));
            }
            System.out.println("|");
        }
        //
    }
    public void setMatrixValue(int column, int row, float value){
        this.matrix[column][row] = value;
    }
    public void add(float value){
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                this.matrix[j][i] += value;
            }
        }
    }
    public void subtract(float value){
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                this.matrix[j][i] -= value;
            }
        }
    }
    public void multiply(float value){
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                this.matrix[j][i] *= value;
            }
        }
    }
    public void divide(float value){
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                this.matrix[j][i] /= value;
            }
        }
    }
    private float det(float[][] array, int range){
        float A = 0f;
        int columnPointer = 0;

        if(range == 1){
            A = (array[0][0] * array[1][1]) - (array[1][0] * array[0][1]);
            return A;
        }

        float[][] tempArray;

        while(columnPointer <= range) {
            tempArray = new float[range][range];
            for (int i = 1; i < range + 1; i++) {
                for (int j = 0, k = 0; j < range; j++) {
                    if (j == columnPointer)
                        k++;
                    tempArray[i - 1][j] = array[i][k];
                    k++;
                }
            }

            A += array[0][columnPointer] * ((float)Math.pow(-1, columnPointer)) * det(tempArray, range - 1);
            columnPointer++;
        }
        return A;
    }
    public float matrixDeterminant(){
        float detA;

        detA = det(this.matrix, this.columnsValue - 1);

        return detA;
    }
    public void transpode(){
        if(!isMatrixCreated){
            return;
        }
        int tempColumns = this.rowsValue;
        int tempRows = this.columnsValue;
        float[][] tempMatrix = new float[tempColumns][tempRows];

        //Saving values to the new matrix
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                tempMatrix[i][j] = this.matrix[j][i];
            }
        }

        //deleting old matrix
        this.matrix = null;
        this.columnsValue = tempColumns;
        this.rowsValue = tempRows;

        //creating new matrix
        createMatrix();

        //Filling up new matrix
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                setMatrixValue(j, i, tempMatrix[j][i]);
            }
        }
        //Did not need to delete temp variables, but I will do it
        tempMatrix = null;
    }
    public void complement(){
        //To solve inverse matrix there is need to be the same value of rows and columns
        if(!(this.rowsValue == this.columnsValue)){
            return;
        }
        //Pointers to point on current matrix place
        int columnPointer = 0;
        int rowPointer = 0;
        //Variable used in "Creating detMatrix" loop
        int column;
        int row;

        //Values of columns and rows for determinant matrix
        int detColumns = this.columnsValue - 1;
        int detRows = this.rowsValue - 1;

        //determinant
        float detA;

        //Complement matrix
        float[][] complementMatrix = new float[this.columnsValue][this.rowsValue];

        do{
            do{
                //Matrix used to get determinant values
                float[][] detMatrix = new float[detColumns][detRows];

                column = 0;
                row = 0;
                //Creating detMatrix
                for(int i = 0; i < detRows; i++, row++){
                    //If value of "i" is equal to row pointer go to the nex row
                    if(i == rowPointer){
                        row++;
                    }
                    for(int j = 0; j < detColumns; j++, column++){
                        //If value of "j" is equal to column pointer go to the next column
                        if(j == columnPointer){
                            column++;
                        }
                        detMatrix[j][i] = this.matrix[column][row];
                    }
                    column = 0;
                }
                //Solving determinant value
                detA = det(detMatrix, detColumns - 1);

                //Saving value to the right place
                complementMatrix[columnPointer][rowPointer] = detA * (float)Math.pow(-1, (columnPointer + 1) + (rowPointer + 1));

                //Deleting determinant matrix to create next
                detMatrix = null;
                columnPointer++;
            }while(columnPointer < this.columnsValue);
            columnPointer = 0;
            rowPointer++;
        }while(rowPointer < this.rowsValue);

        //Reset matrix
        this.matrix = null;
        createMatrix();

        //Setting matrix and deleting complement matrix (temp)
        for(int i = 0; i < this.rowsValue; i++){
            for(int j = 0; j < this.columnsValue; j++){
                setMatrixValue(j, i, complementMatrix[j][i]);
            }
        }

        complementMatrix = null;
    }
    public void inverse(){
        //To solve inverse matrix there is need to be the same value of rows and columns
        if(!(this.rowsValue == this.columnsValue)){
            return;
        }
        float detA = matrixDeterminant();
        //To solve inverse matrix there is need to detA be different from 0
        if(detA == 0){
            return;
        }

        complement();
        transpode();
        divide(detA);
    }
}
