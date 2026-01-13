public class Screen {
    public static void main(String[] args) {
        clearScreen();
        liniaVertical(0,65,12);
        int colJug=40;
        int colJug2= 65+colJug;
        //jugador1
        printGraphics(Graphics.ogre,0,1);
        print("Nom: John",1,colJug);
        print("Vida:50",2,colJug);
        print("Atac:50",3,colJug);
        print("Defensa:50",4,colJug);

        //jugador2
        printGraphics(Graphics.dragon,0,70);

        print("Nom: John",1,colJug2);
        print("Vida:50",2,colJug2);
        print("Atac:50",3,colJug2);
        print("Defensa:50",4,colJug2);
        show();
    }

    static final int SCREEN_WIDTH=120;
    static final int SCREEN_HEIGHT=20;
    static String[][] pixels = new String[SCREEN_HEIGHT][SCREEN_WIDTH];

    static void printGraphics(String graph, int fila, int col){

        String[] linies=graph.split("\n");
        for (int i = 0; i < linies.length; i++) {
            print(linies[i],fila,col);
            fila++;
        }
    }
    static void liniaVertical(int fila, int col, int longitut){
        for (int i = 0; i < longitut; i++) {
            print("|",fila,col);
            fila++;
        }
    }
    static void clearScreen(){
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                pixels[i][j]=" ";
            }

        }
    }

    static void print(String str, int fila, int col){
        for (int i = 0; i < str.length(); i++) {
            pixels[fila][col]=""+str.charAt(i);
            col++;
        }
    }

    static void show(){
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                System.out.print(pixels[i][j]);
            }
            System.out.println();
        }
    }
}