public class Main {
    public static void main(String[] args) throws WrongMatrixException {
        Matrix a = new Matrix(3,3);
        Matrix b = new Matrix(5,1);
        a.SetMatrixArray();
        a.InverseMatrix();
        System.out.print(a.Rank());
    }
}
