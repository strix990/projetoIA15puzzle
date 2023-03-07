import java.util.Scanner;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;

class Trab15 {
    public static void main(String[]args){
        Scanner imp = new Scanner(System.in);
        Tabuleiro game1 = new Tabuleiro(imp), game2 = new Tabuleiro(imp);
        /*ProfundidadeAlgori test= new ProfundidadeAlgori();
        Stack<Tabuleiro> solv= new Stack<Tabuleiro>();
        solv = test.runDfs(game1, game2, 2);
        System.out.println("foi1");
        game1 = solv.pop();
        game1.printers();*/
        game1.printers();
        game1 = game1.moveleft();
        game1.printers(); 
        //parte de teste, nao relevante
    }
}
class Tabuleiro {
    static int SIZE = 4;
    int[][] game = new int[SIZE][SIZE]; //tabuleiro do jogo 
    int[] blkrow = new int[2]; //posicao do quadrado vazio
    int inver; //valor do inver para checar se o jogo tem resolucao

    Tabuleiro(){}
    Tabuleiro(int[][] qualquer){
        inver = 0;
        blkrow[0] = 0;
        blkrow[1] = 0;
        game = qualquer;
    }
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
                if(listatemp[x] > listatemp[y] && (listatemp[y] != 0) && (listatemp[x] != 0)){}
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
    public Tabuleiro moveleft() {//move para esquerda, cria um novo tabuleiro ret e retorna ele com a posicao atualizada movida para a esquerda
        Tabuleiro ret = new Tabuleiro();
        ret.game = this.game;
        ret.blkrow = this.blkrow;
        if(blkrow[1] - 1 < 0 )
            return null;
        else {
            ret.game[blkrow[0]][blkrow[1]] = game[blkrow[0]][blkrow[1]-1];//move a posicao do espaco do lado esquerdo do vazio para direita
            ret.game[blkrow[0]][blkrow[1]-1] = 0; //coloca a posicao citada acima como 0
            ret.blkrow[1] = blkrow[1] - 1; //atualiza a coordenada da posicao vazia
            return ret;
        }
    }
    public Tabuleiro movedown() {//move para baixo, mesma coisa que o da esquerda
        Tabuleiro ret = new Tabuleiro();
        ret.game = this.game;
        ret.blkrow = this.blkrow;
        if(blkrow[0] + 1 >= SIZE )
            return null;
        else {
            ret.game[blkrow[0]][blkrow[1]] = game[blkrow[0]+1][blkrow[1]];//etc...
            ret.game[blkrow[0]+1][blkrow[1]] = 0;
            ret.blkrow[0] = blkrow[0] + 1;
            return ret;
        }
    }
    public Tabuleiro moveright() {//move para direita, etc...
        Tabuleiro ret = new Tabuleiro();
        ret.game = this.game;
        ret.blkrow = this.blkrow;
        if(blkrow[1] + 1 >= SIZE )
            return null;
        else {  
            ret.game[blkrow[0]][blkrow[1]] = game[blkrow[0]][blkrow[1]+1];//etc..
            ret.game[blkrow[0]][blkrow[1]+1] = 0;
            ret.blkrow[1] = blkrow[1] + 1;
            return ret;
        }
    }
    public Tabuleiro moveup() {//move para cima, etc...
        Tabuleiro ret = new Tabuleiro();
        ret.game = this.game;
        ret.blkrow = this.blkrow;
        ret.inver = 0;
        if(blkrow[0] - 1 < 0 )
            return null;
        else {
            ret.game[blkrow[0]][blkrow[1]] = game[blkrow[0]-1][blkrow[1]];//etc...
            ret.game[blkrow[0]-1][blkrow[1]] = 0;
            ret.blkrow[0] = blkrow[0] - 1;
            return ret;
        }
    }
    public void printers() {//imprime o tabuleiro
        for(int x = 0; x < SIZE;x++){
            for(int y = 0; y < SIZE;y++){
                System.out.print(this.game[x][y] + " ");
            }
            System.out.println("");
        }
    }
    public boolean newequals(Tabuleiro jogo2) {//compara se o tabuleiro atual eh o mesmo que o do parametro(jogo2)
        for(int x = 0; x < SIZE;x++){
            for(int y = 0; y < SIZE;y++){
                if(this.game[x][y] != jogo2.game[x][y])
                    return false;
            }
        }
        return true;
    }
    }


class Node{ //essa parte da node acabei nao utilizando por conta de erros
    Tabuleiro game;
    int depthNode;
    Node(Tabuleiro game, int depthnode){
        this.game = game;
        this.depthNode = depthnode;
    }
}

class ProfundidadeAlgori { //mesma coisa com essas variaveis
    private Node objetivo;
    private boolean objachado;

   /*  ProfundidadeAlgori(Node objetivo) {
        this.objetivo = objetivo;
    }*/
    public Stack<Tabuleiro> runDfs(Tabuleiro start,Tabuleiro objectivo,int maxdepth){//Nessa parte se faz a chamada preliminar do DFS passa se dois tabuleiros o start e objectivo e a profundidade maxima desejada
        Stack<Tabuleiro> caminho = new Stack<Tabuleiro>();
        int depth = 0;//sempre comeca como 0
        caminho.push(start);//coloca o primeiro tabuleiro como primeiro elemento da stack
        return Dfs(start, objectivo, depth, caminho, maxdepth);//chama a funcao DFS principal com dois parametros adicionais depth e caminho(que eh a pilha)
    }
   /*  public void runIDS(Node start){ //essa parte de runIDS funcionaria se o codigo de DFS funcionasse tambem
        int depth = 0;

        while(!objachado){
            Dfs(start, objetivo, depth);
            depth++;
        }
    }*/
    public Stack<Tabuleiro> Dfs(Tabuleiro start, Tabuleiro objectivo, int depth, Stack<Tabuleiro> caminho, int maxdepth){//funcao de DFS o problema esta na parte de recursao en que ele nao consegue comparar o tabuleiro da chamada recursiva com o tabuleiro objetivo, dando um erro de execao de nullpoint
        //start.printers();
        //System.out.println(start.newequals(objectivo));
        if(start.newequals(objectivo)) { //compara se o start chegou no objetivo, se sim retorna a pilha com o caminho
            System.out.println(depth);
            return caminho;
        }
        else {
            if((start.moveleft() == null) /*&& (caminho.search(start.moveleft()) != -1)*/ && (maxdepth != depth)){ //primeiro se compara se o movimento eh valido logo depois teria que comparar se o movimento ja foi feito e esta na stack porem tambem estava dando erro de comparacao apos ve se nao chegou no maxdepth
                caminho.push(start.moveleft());//se for valido coloca o movimento na stack
                System.out.println("<-");//debug
                Dfs(start.moveleft(), objectivo, depth + 1 , caminho, maxdepth );//chamada recursiva
            }
            else if((start.movedown()!= null)/*&& (caminho.search(start.movedown()) != -1)*/ && maxdepth != depth){//mesma ideia
                caminho.push(start.movedown());
                System.out.println("down");
                Dfs(start.movedown(), objectivo, depth + 1 , caminho, maxdepth );
            }
            else if((start.moveright()!= null) /*&&(caminho.search(start.moveright()) != -1)*/ && maxdepth != depth){//mesma ideia
                caminho.push(start.moveright());
                System.out.println("->");
                start = start.moveright();
                Dfs(start, objectivo, depth + 1 , caminho, maxdepth );
            }
            else if((start.moveup()!= null) /*&&(caminho.search(start.moveup()) != -1)*/ && (maxdepth != depth)){//mesma ideia
                caminho.push(start.moveup());
                System.out.println("cima");
                Dfs(start.moveup(), objectivo, depth + 1 , caminho, maxdepth );
            }
            else if(maxdepth == depth) {//caso a profundidade maxima tenha sido alcancada dar pop nessa node da stack e retornar vazio
                caminho.pop();
                System.out.println(depth);
                return null;
            }
        }
        return null;
    }
}

