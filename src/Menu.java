import java.util.Scanner;

public class Menu {
    private boolean isRunning = true;

    public boolean isMenuRunning(){
        return this.isRunning;
    }
    public void setMenuRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void menu(Matrix matrix){

        short choiceMenu;
        int column;
        int row;
        float equationValue;
        Scanner input = new Scanner(System.in);

        System.out.println("---MATRIX CALCULATOR---");
        System.out.println("\tWilczynski 2024");
        if(matrix.isMatrixCreated()){
            System.out.println();
            System.out.println("Your matrix:");
            matrix.printMatrix();
        }
        System.out.println();
        System.out.println( "1 - Create matrix\n" +
                "2 - Delete matrix\n" +
                "3 - Set value\n" +
                "4 - Add value\n" +
                "5 - Subtract value\n" +
                "6 - Multiply value\n" +
                "7 - Divide value\n" +
                "8 - Matrix determinant\n" +
                "9 - Transpose matrix\n" +
                "10 - Complement matrix\n" +
                "11 - Inverse matrix\n" +
                "0 - End program\n");
        choiceMenu = input.nextShort();
        switch(choiceMenu){
            //Create matrix
            case 1:
                System.out.print("Set column numbers: ");
                matrix.setColumnsValue(input.nextInt());
                System.out.print("Set row numbers: ");
                matrix.setRowsValue(input.nextInt());
                matrix.createMatrix();
                break;
            //Delete matrix
            case 2:
                matrix.deleteMatrix();
                break;
            //Set value
            case 3:
                System.out.print("Choose column number: ");
                column = input.nextInt() - 1;
                System.out.print("Choose row number: ");
                row = input.nextInt() - 1;
                System.out.print("Set value: ");
                equationValue = input.nextFloat();
                matrix.setMatrixValue(column, row, equationValue);
                break;
            //Add value
            case 4:
                System.out.print("Set value to add: ");
                equationValue = input.nextFloat();
                matrix.add(equationValue);
                break;
            //Subtract value
            case 5:
                System.out.print("Set value to subtract: ");
                equationValue = input.nextFloat();
                matrix.subtract(equationValue);
                break;
            //Multiply value
            case 6:
                System.out.print("Set value to multiply: ");
                equationValue = input.nextFloat();
                matrix.multiply(equationValue);
                break;
            //Divide value
            case 7:
                System.out.print("Set value to divide: ");
                equationValue = input.nextFloat();
                matrix.divide(equationValue);
                break;
            //Matrix determinant
            case 8:
                if(matrix.getColumnsValue() == matrix.getRowsValue()){
                    System.out.println("Matrix determinant is equal: " + matrix.matrixDeterminant());
                    break;
                }else{
                    System.out.println("Numbers of columns and rows must be the same!");
                }
                break;
            //Transpose matrix
            case 9:
                matrix.transpode();
                break;
            //Complement matrix;
            case 10:
                if(matrix.getColumnsValue() == matrix.getRowsValue()){
                    matrix.complement();
                    break;
                }else{
                    System.out.println("Numbers of columns and rows must be the same!");
                }
                break;
            //Inverse matrix
            case 11:
                if(matrix.getColumnsValue() == matrix.getRowsValue()){
                    matrix.inverse();
                    break;
                }else{
                    System.out.println("Numbers of columns and rows must be the same!");
                }
                break;
            //End program
            case 0:
                this.isRunning = false;
                break;
        }
    }
}
