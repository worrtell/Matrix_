import java.util.Scanner;
public class Matrix {
    Scanner in = new Scanner(System.in);
    int rowsCount;
    int colsCount;
    Fraction[][] array;
    public Matrix(int rowsCount, int colsCount) {
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        Fraction[][] array = new Fraction[this.rowsCount][this.colsCount];
        Fraction emptiness = new Fraction("0");
        for (int i = 0; i < this.rowsCount; i++){
            for (int j = 0; j < this.colsCount; j++){
                array[i][j] = emptiness;
            }
        }
        this.array = array;
    }
    public void SetMatrixArray() {
        System.out.println("if you want to enter Matrix element by element press 1 or if you want to enter Matrix line by line press 2");
        int HowToEnter = in.nextInt();
        String p = in.nextLine();
        if (HowToEnter == 1) {
            for (int i = 0; i < rowsCount; i++) {
                for (int j = 0; j < colsCount; j++) {
                    System.out.println("element " + (i+1) + ":" + (j+1));
                    String input = in.nextLine();
                    Fraction a = new Fraction(input);
                    this.array[i][j] = a;
                }
            }
        }
        else {
            for (int i = 0; i < rowsCount; i ++){
                System.out.println("elements on row " + i);
                String input = in.nextLine();
                String[] input_array = input.split(" ");
                for (int j = 0; j < input_array.length; j ++) {
                    Fraction a = new Fraction(input_array[j]);
                    this.array[i][j] = a;
                }
            }
        }
    }
    public void PrintMatrix(){
        System.out.println("\nMatrix " + this.rowsCount + "×" + this.rowsCount);
        for (int i = 0; i < this.rowsCount; i++) {
            for (int j = 0; j < this.colsCount; j++) {
                this.array[i][j].PrintFraction();
            }
            System.out.println();
        }
    }
    public Fraction[][] MatrixAddition(Matrix B){
        if ((this.rowsCount == B.rowsCount) & (this.colsCount == B.colsCount)){
            Matrix C = new Matrix(this.rowsCount,this.colsCount);
            for (int i = 0; i < this.rowsCount; i++){
                for (int j = 0; j < this.colsCount; j++){
                    C.array[i][j] = C.array[i][j].FractionAddidion(this.array[i][j],B.array[i][j]);
                }
            }
            return C.array;
        }
        else{
            System.out.println("Addition is impossible");
            return null;
        }
    }
    public Fraction[][] MatrixMultiplication(Matrix B){
        if (this.colsCount == B.rowsCount){
            Matrix C = new Matrix(this.rowsCount, B.colsCount);
            for (int i = 0; i < this.rowsCount; i++){
                for (int j = 0; j < B.colsCount; j++){
                    Fraction sum = new Fraction("0");
                    for (int n = 0; n < B.rowsCount; n++){
                        Fraction mul = this.array[i][n].FractionMultiplication(this.array[i][n],B.array[n][j]);
                        sum = sum.FractionAddidion(sum, mul);
                    }
                    C.array[i][j] = sum;
                    System.out.print(" ");
                    //
                    C.array[i][j].PrintFraction();

                    //
                }
                System.out.println();
            }
            return C.array;
        }
        else if (B.colsCount == this.rowsCount){
            System.out.println("Multiplication is possible, but in reverse order. If you want to continue press (да,yes,д,y)\\n");
            String ans = in.nextLine();
            String right_ans = "да,yes,д,y";
            if (right_ans.contains(ans)){
                Matrix C = new Matrix(B.rowsCount, this.colsCount);
                for (int i = 0; i < B.rowsCount; i++){
                    for (int j = 0; j < this.colsCount; j++){
                        Fraction sum = new Fraction("0");
                        for (int n = 0; n < this.rowsCount; n++){
                            Fraction mul = B.array[i][n].FractionMultiplication(B.array[i][n],this.array[n][j]);
                            sum.FractionAddidion(sum, mul);
                        }
                        C.array[i][j] = sum;
                    }
                }
                return C.array;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
    public void SwapMatrixRows(int a, int b){
        Fraction[] temp;
        temp = this.array[a];
        this.array[a] = array[b];
        this.array[b] = temp;
    }
    public void SwapMatrixCols(int a, int b){
        for (int i = 0; i < this.rowsCount; i++){
            Fraction temp;
            temp = this.array[i][a];
            this.array[i][a] = this.array[i][b];
            this.array[i][b] =temp;
        }
    }
    public Matrix MatrixToSquare(){
        if (this.rowsCount < this.colsCount){
            Matrix NewMatrix = new Matrix(this.colsCount, this.colsCount);
            for (int i = 0; i < this.rowsCount; i++){
                for (int j = 0; j < this.colsCount; j++){
                    NewMatrix.array[i][j] = this.array[i][j];
                }
            }
            return NewMatrix;
        }
        else {
            Matrix NewMatrix = new Matrix(this.rowsCount, this.rowsCount);
            for (int i = 0; i < this.rowsCount; i++){
                for (int j = 0; j < this.colsCount; j++){
                    NewMatrix.array[i][j] = this.array[i][j];
                }
            }
            return NewMatrix;
        }
    }
    public int BubbleSort(int start, int col){
        Fraction a = new Fraction("0");
        int k = 1;
        for (int j = start; j < this.rowsCount-1; j++){
            for (int i = start; i < this.rowsCount-1; i++){
                if (a.FractionGreaterThan(this.array[i+1][col],this.array[i][col])){
                    this.SwapMatrixRows((i+1),i);
                    k *= -1;
                }
            }
        }
        return k;
    }
    public Fraction GaussMethod() {
        Matrix C = new Matrix(this.rowsCount, this.colsCount);
        C = this;
        C = C.MatrixToSquare();
        int c = 1;
        for (int j = 0; j < C.colsCount; j++) {
            c *= C.BubbleSort(j, j);
            if (C.array[j][j].num != 0) {
                Fraction k = new Fraction("0");
                Fraction minus = new Fraction("-1");
                for (int m = j + 1; m < C.rowsCount; m++) {
                    k = k.FractionDivision(C.array[m][j], C.array[j][j]);
                    k = k.FractionMultiplication(minus, k);
                    k.FractionReduce();
                    for (int i = 0; i < C.colsCount; i++) {
                        Fraction mult;
                        mult = C.array[j][i].FractionMultiplication(C.array[j][i], k);
                        C.array[m][i] = C.array[m][i].FractionAddidion(C.array[m][i], mult);
                    }
                }
                if (j > 0) {
                    for (int m = 0; m < j; m++) {
                        k = k.FractionDivision(C.array[m][j], C.array[j][j]);
                        k = k.FractionMultiplication(minus, k);
                        k.FractionReduce();
                        for (int i = 0; i < C.colsCount; i++) {
                            Fraction mult;
                            mult = C.array[j][i].FractionMultiplication(C.array[j][i], k);
                            C.array[m][i] = C.array[m][i].FractionAddidion(C.array[m][i], mult);
                        }
                    }
                }
            }
        }
        Fraction det = new Fraction(Integer.toString(c));
        for (int i = 0; i < C.colsCount; i++){
            det = det.FractionMultiplication(det,C.array[i][i]);
        }
        return det;
    }
    public Fraction GaussMethod2() {
        this.MatrixToSquare();
        int c = 1;
        for (int j = 0; j < this.colsCount; j++) {
            c *= this.BubbleSort(j, j);
            if (this.array[j][j].num != 0) {
                Fraction k = new Fraction("0");
                Fraction minus = new Fraction("-1");
                for (int m = j + 1; m < this.rowsCount; m++) {
                    k = k.FractionDivision(this.array[m][j], this.array[j][j]);
                    k = k.FractionMultiplication(minus, k);
                    k.FractionReduce();
                    for (int i = 0; i < this.colsCount; i++) {
                        Fraction mult;
                        mult = this.array[j][i].FractionMultiplication(this.array[j][i], k);
                        this.array[m][i] = this.array[m][i].FractionAddidion(this.array[m][i], mult);
                    }
                }
                if (j > 0) {
                    for (int m = 0; m < j; m++) {
                        k = k.FractionDivision(this.array[m][j], this.array[j][j]);
                        k = k.FractionMultiplication(minus, k);
                        k.FractionReduce();
                        for (int i = 0; i < this.colsCount; i++) {
                            Fraction mult;
                            mult = this.array[j][i].FractionMultiplication(this.array[j][i], k);
                            this.array[m][i] = this.array[m][i].FractionAddidion(this.array[m][i], mult);
                        }
                    }
                }
            }
        }
        Fraction det = new Fraction(Integer.toString(c));
        for (int i = 0; i < this.colsCount; i++){
            det = det.FractionMultiplication(det,this.array[i][i]);
        }
        return det;
    }
    public int GaussMatrixRank(){
        this.GaussMethod2();
        int rank = this.colsCount;
        Fraction zero = new Fraction("0");
        for (int i = 0; i < this.colsCount; i++){
            if (this.array[i][i].Eqal(zero)){
                rank--;
            }
        }
        return rank;
    }
    public void MatrixTransposition(){
        Matrix C = new Matrix(this.colsCount, this.rowsCount);
        for (int i = 0; i < this.rowsCount; i++) {
            for (int j = 0; j < this.colsCount; j++) {
                C.array[i][j] = this.array[j][i];
            }
        }
        this.colsCount = C.colsCount;
        this.rowsCount = C.rowsCount;
        this.array = C.array;
    }

    // Определитель минора + знак
    public Fraction MinorDet(int n, int m){
        int s = ((n+m)%2 == 0) ? 1: -1;
        Fraction sign = new Fraction(Integer.toString(s));
        Matrix C = new Matrix(this.rowsCount-1, this.colsCount-1);
        int i1 = 0;
        int j1 = 0;
        for (int i = 0; i < C.rowsCount; i++){
            if (i1 == n){
                i1 ++;
            }
            for (int j = 0; j < C.colsCount; j++){
                if (j1 == m){
                    j1 ++;
                }
                if (i1 < this.rowsCount & j1 < this.colsCount){
                    C.array[i][j] = this.array[i1][j1];
                }
                j1 ++;
            }
            j1 = 0;
            i1 ++;
        }
        return sign.FractionMultiplication(C.GaussMethod2(),sign);
    }
    public void InverseMatrix() throws WrongMatrixException{
        Fraction zero = new Fraction("0");
        Fraction det = this.GaussMethod();
        if (zero.Eqal(det)){
            throw new WrongMatrixException();
        }
        else{
            this.MatrixTransposition();
            Matrix C = new Matrix(this.colsCount, this.rowsCount);
            for (int i = 0; i < this.rowsCount; i++) {
                for (int j = 0; j < this.colsCount; j++) {
                    C.array[i][j] = det.FractionDivision(this.MinorDet(i,j),det);
                }
            }
            this.rowsCount = C.rowsCount;
            this.colsCount = C.colsCount;
            this.array = C.array;
        }
    }
    public Matrix Remove(int f){
        Matrix C = new Matrix(f,f);
        for (int i = 0; i < f; i++){
            for (int j = 0; j < f; j++){
                C.array[i][j] = this.array[i][j];
            }
        }
        return C;
    }

    // ранг методом окаймляющих миноров
    public int Rank(){
        int rank = 0;
        for (int k = 0; k < this.colsCount; k++){
            for (int i = k; i < this.rowsCount; i++){
                for (int j = k; j < this.colsCount; j++){
                    Matrix C = this.Remove(k+1);
                    Fraction zero = new Fraction("0");
                    // свап
                    if (zero.Eqal(C.GaussMethod())){
                        if (i+1 < this.rowsCount) {
                            this.SwapMatrixRows((i + 1), k);
                        }
                        if (j+1 < this.colsCount) {
                            this.SwapMatrixCols((j + 1), k);
                        }
                    }
                    else{
                        rank = k;
                        break;
                    }
                }
                break;
            }
        }

        return rank+1;
    }

    // расширенная матрица
    public Fraction[][] ExtendedMatrix(Matrix B){
        Matrix C = new Matrix(this.colsCount, this.rowsCount+1);
        for (int i = 0; i < this.rowsCount; i++){
            for (int j = 0; j < this.colsCount; j++){
                C.array[i][j] = this.array[i][j];
                if (j == this.colsCount-1){
                    C.array[i][j+1] = B.array[i][0];
                }
            }
        }
        return C.array;
    }
    public Matrix SLAE(Matrix B) throws WrongMatrixException{
        int rankA = this.Rank();
        Matrix AB = new Matrix(this.rowsCount, (this.colsCount+B.colsCount));
        AB.array = this.ExtendedMatrix(B);
        AB.PrintMatrix();
        int rankAB = AB.Rank();
        if ((rankA == rankAB) && (B.rowsCount == this.rowsCount)){
            if (rankA == B.rowsCount){
                System.out.println("SLAE has one solution");
                this.InverseMatrix();
                this.MatrixMultiplication(B);
                return this;
            }
            else {
                System.out.println("SLAE has eternity of solutions");
                return null;
            }
        }
        else {
            System.out.println("SLAE has no solutions");
            return null;
        }
    }
}
