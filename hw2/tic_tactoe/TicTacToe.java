package hw2.tic_tactoe;
import java.util.Scanner;

public class TicTacToe {
    enum block{
        EMPTY,
        X,
        O
    };
    private static block[][] flag = new block[3][3];
    private static block player1;
    private static block player2;
    private static int player;//0 = player1 | 1 = player2
    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        System.out.print("P1 is X and P2 is O\n");
        System.out.println("Play with the number keys:");
        System.out.printf("1 2 3\n4 5 6\n7 8 9\n");
        System.out.println("== Game start! ==");
        Scanner sc = new Scanner(System.in);
        player1 = block.X;
        player2 = block.O;
        player = 0;//player first      
        // start the game
        boolean is_first = true;
        while(true){
            if((player == 0 && is_first == true) || (player == 1 && is_first == false)){
                //player1
                game.show_game();
                int place_i, place_j;
                while(true){
                    System.out.print("place:");
                    int temp = sc.nextInt();
                    if(game.check(temp)==-1){
                        System.out.println("Wrong Place, input again");
                        game.show_game();
                        continue;
                    }
                    place_i = (temp - 1)/3;
                    place_j = (temp - 1)%3;
                    if(flag[place_i][place_j] == block.EMPTY) break;
                    else{
                        System.out.println("Wrong Place, input again");
                        game.show_game();
                        continue;
                    }
                }
                flag[place_i][place_j] = player1;
            }
            else{
                //player2
                game.show_game();
                int place_i, place_j;
                while(true){
                    System.out.print("place:");
                    int temp = sc.nextInt();
                    if(game.check(temp)==-1){
                        System.out.println("Wrong Place, input again");
                        game.show_game();
                        continue;
                    }
                    place_i = (temp - 1)/3;
                    place_j = (temp - 1)%3;
                    if(flag[place_i][place_j] == block.EMPTY) break;
                    else{
                        System.out.println("Wrong Place, input again");
                        game.show_game();
                        continue;
                    }
                }
                flag[place_i][place_j] = player2;
            }

            if(game.judge() == -1) is_first = !is_first;
            else break;
        }
        sc.close();

        // output result
        System.out.print("\nresult : ");
        game.show_game();
        int result = game.judge();
        if(result == 0) System.out.println("Tie");
        else if(result == 1){
            if(player == 0) System.out.println("Player1 win");
            else System.out.println("Player2 win");
        }
        else{
            if(player == 0) System.out.println("Player2 win");
            else System.out.println("Player1 win");
        }
    }

    // constructor
    public TicTacToe(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                flag[i][j] = block.EMPTY;
            }
        }
    }

    // to determine whether the game is completed
    public int judge(){
        // check line
        for(int i = 0; i < 3; i++){
            if(flag[i][0] == flag[i][1] && flag[i][1] == flag[i][2]){
                if(flag[i][0] == block.X) return 1;
                else if(flag[i][0] == block.O) return 2;
            }
            if(flag[0][i] == flag[1][i] && flag[1][i] == flag[2][i]){
                if(flag[0][i] == block.X) return 1;
                else if(flag[0][i] == block.O) return 2;
            }
        }
        if((flag[0][0] == flag[1][1] && flag[1][1] == flag[2][2])
        || (flag[2][0] == flag[1][1] && flag[1][1] == flag[0][2])){
            if(flag[1][1] == block.X) return 1;
            else if(flag[1][1] == block.O) return 2;
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(flag[i][j] == block.EMPTY) return -1;
            }
        }
        return 0;
    }

    public void show_game(){
        System.out.println("");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (flag[i][j] == block.EMPTY){
                    int temp = i*3+j+1;
                    System.out.print(" "+temp+" ");
                }
                else if (flag[i][j] == block.O)
                    System.out.print(" O ");
                else
                    System.out.print(" X ");
                System.out.print((j != 2) ? "|" : "\n");
            }
            if (i != 2)
                System.out.println("---+---+---");
        }
        System.out.println("");
    }
    public int check(int place){
        if((place >= 1) && (place <=9)){
            return 1;
        }
        return -1;
    }
}
