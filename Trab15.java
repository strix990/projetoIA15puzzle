import java.util.Scanner;

class Trab15 {
    public static void main(String[]args){
        Scanner imp = new Scanner(System.in);
        Tabuleiro game1 = new Tabuleiro(imp), game2 = new Tabuleiro(imp);
        System.out.println(game1.solv(game2));
        System.out.println(game2.blkrow[0] + " " + game2.blkrow[1]);
        System.out.println(game2.moveup());
        System.out.println(game2.blkrow[0] + " " + game2.blkrow[1]);
        System.out.println(game2.moves);
    }
}
class Tabuleiro {
    static int SIZE = 4;
    int[][] game = new int[SIZE][SIZE]; //tabuleiro do jogo 
    int[] blkrow = new int[2]; //posicao do quadrado vazio
    int inver, moves = 0; //valor do inver para checar se o jogo tem resolucao / numero de movimentos feitos no jogo

    Tabuleiro(Scanner imp) { //construtor que devolve o inver do jogo e inicializa o tabuleiro
    int listatemp[] = new int[16]; //lista para checar o inver
    int x1 = 0;
    inver = 0;
    for(int x = 0; x< SIZE;x++){ //inicializa a lista e jogo
        for(int y = 0; y < SIZE;y++){
            int currenum = imp.nextInt();
            this.game[x][y] = currenum;
            listatemp[x1] = currenum;
            if(currenum == 0){
                blkrow[0] = x;
                blkrow[1] = y;
            }
            x1++;
            }
        }
        for(int x = 0;x < 16;x++){ //checa o inver
            for(int y = x+1;y < 16;y++)
                if(listatemp[x] > listatemp[y] && (listatemp[y] != 0) && (listatemp[x] != 0))
                    inver += 1;
        }
    }
    public boolean solv(Tabuleiro game2){//devolve se um jogo comparado com um outro jogo qualquer eh solucionavel

        if(((this.inver%2==0)==(blkrow[1]%2==1))==((game2.inver%2==0)==(blkrow[1]%2==1)))
            return true;
        else 
            return false;
    }
    //movimento do espaco vazio comecando no sentido anti-horario, retorna true se possivel e false caso contrario
    public boolean moveleft() {//move para esquerda
        if(blkrow[1] - 1 <= 0 )
            return false;
        else {
            game[blkrow[0]][blkrow[1]] = game[blkrow[0]][blkrow[1]-1];
            game[blkrow[0]][blkrow[1]-1] = 0;
            moves++;
            blkrow[1] = blkrow[1] - 1;
            return true;
        }
    }
    public boolean movedown() {//move para baixo
        if(blkrow[0] + 1 >= SIZE )
            return false;
        else {
            game[blkrow[0]][blkrow[1]] = game[blkrow[0]+1][blkrow[1]];
            game[blkrow[0]+1][blkrow[1]] = 0;
            moves++;
            blkrow[0] = blkrow[0] + 1;
            return true;
        }
    }
    public boolean moveright() {//move para direita
        if(blkrow[1] + 1 >= SIZE )
            return false;
        else {  
            game[blkrow[0]][blkrow[1]] = game[blkrow[0]][blkrow[1]+1];
            game[blkrow[0]][blkrow[1]+1] = 0;
            moves++;
            blkrow[1] = blkrow[1] + 1;
            return true;
        }
    }
    public boolean moveup() {//move para cima
        if(blkrow[0] - 1 <= 0 )
            return false;
        else {
            game[blkrow[0]][blkrow[1]] = game[blkrow[0]-1][blkrow[1]];
            game[blkrow[0]-1][blkrow[1]] = 0;
            moves++;
            blkrow[0] = blkrow[0] - 1;
            return true;
        }
    }
}
