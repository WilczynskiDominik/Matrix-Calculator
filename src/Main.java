public class Main {
    public static void main(String[] args) {

        //Creating new matrix
        Matrix matrix = new Matrix();
        //Creating new menu
        Menu menu = new Menu();
/*
        matrix.createMatrix(3, 3);
        matrix.setMatrixValue(0, 0, 2);
        matrix.setMatrixValue(0, 1, 1);
        matrix.setMatrixValue(0, 2, 4);
        matrix.setMatrixValue(1, 0, 4);
        matrix.setMatrixValue(1, 1, 5);
        matrix.setMatrixValue(1, 2, 0);
        matrix.setMatrixValue(2, 0, 1);
        matrix.setMatrixValue(2, 2, 2);

*/
        do{
            menu.menu(matrix);
        }while(menu.isMenuRunning());
    }
}