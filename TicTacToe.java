
/**
 * AP CS A - Unit 8 TicTacToe
 *
 * @Cindy Weng 
 * @Nov. 9, 2023
 */

import java.util.*;

public class TicTacToe
{
    public static String[][] board=new String[3][3];
    public static boolean player1=true;
    public static int roundCount=1;
    public static boolean player1winner=false;
    public static boolean player2winner=false; 

    public static void TicTacToe()
    {
        takeInput();
    }

    public static void takeInput(){
        Scanner sc=new Scanner(System.in);

        if(!player1winner&&!player2winner){
            System.out.println("Round "+roundCount+" :");

            print2DArray(board);

            if(player1){
                System.out.println("X(player1), make you move (row, col): ");
            }else{
                System.out.println("Y(player2), make you move (row, col): ");
            }

            String input=sc.nextLine();
            String[] inputTemp=input.split(",");

            newMove(inputTemp[0], inputTemp[1]);

        }else{
            String input=sc.nextLine();
            if(input.equals("Y")){
                player1winner=false;
                player2winner=false;
                roundCount=1;
                player1=true;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        board[i][j]=null;
                    }
                }
                takeInput();
            }else if(input.equals("N")){
                return;
            }else{
                System.out.println("Invalid Input: please enter Y or N");
                takeInput();
            }
        }

    }

    public static void newMove(String r, String c){
        int ri=Integer.valueOf(r);
        int ci=Integer.valueOf(c);

        if(ri>=3 || ci>=3){
            System.out.println("Invalid Input: Try Again(Out of Bound)!");
            takeInput();
            return;
        }else{
            if(board[ri][ci]!=null){
                System.out.println("Invalid Input: Try Again(Grid Occupied!)");
                takeInput();
                return;
            }else{
                if(player1){
                    board[ri][ci]="X";
                }else{
                    board[ri][ci]="Y";
                }
                roundCount++;
                player1=!player1;
                checkWinner(board);
                if(!player1winner&&!player2winner){
                    takeInput();
                }else{
                    if(player1winner){
                        System.out.println("Player 1 Won!");
                    }else if(player2winner){
                        System.out.println("Player 2 Won!");
                    }
                    print2DArray(board);
                    System.out.println("Play again? Y/N");
                    takeInput();
                }
            }
        }
    }

    public static void print2DArray(String[][] arr2d){
        for(int i=0; i<arr2d.length; i++){
            for(int j=0; j<arr2d[0].length; j++){
                if(board[i][j]==null){
                    System.out.print("[ ]"+" ");
                }else{
                    System.out.print("["+arr2d[i][j]+"]"+" ");
                }
            }
            System.out.println();
        }
    }

    public static void checkWinner(String[][] arr2d){
        for(int r=0; r<arr2d.length; r++){
            int countplayer1=0;
            int countplayer2=0;
            for(int c=0; c<arr2d[0].length; c++){
                if(arr2d[r][c]==null){
                    return;
                }
                if(arr2d[r][c].equals("X")){
                    countplayer1++;
                }else if(arr2d[r][c].equals("Y")){
                    countplayer2++;
                }
            }
            if(countplayer1==3){
                player1winner=true;
                return;
            }else if(countplayer2==3){
                player2winner=true;
                return;
            }
        }

        for(int c=0; c<arr2d[0].length; c++){
            int countplayer1=0;
            int countplayer2=0;
            for(int r=0; r<arr2d.length; r++){
                if(arr2d[r][c]==null){
                    return;
                }
                if(arr2d[r][c].equals("X")){
                    countplayer1++;
                }else if(arr2d[r][c].equals("Y")){
                    countplayer2++;
                }
            }
            if(countplayer1==3){
                player1winner=true;
                return;
            }else if(countplayer2==3){
                player2winner=true;
                return;
            }
        }

        int countplayer1=0;
        int countplayer2=0;
        for(int r=0; r<arr2d.length; r++){
            if(arr2d[r][r]==null){
                return;
            }
            if(arr2d[r][r].equals("X")){
                countplayer1++;
            }else if(arr2d[r][r].equals("Y")){
                countplayer2++;
            }
        }
        if(countplayer1==3){
            player1winner=true;
            return;
        }else if(countplayer2==3){
            player2winner=true;
            return;
        }

        countplayer1=0;
        countplayer2=0;
        for(int r=0; r<arr2d.length; r++){
            if(arr2d[r][3-r]==null){
                return;
            }
            if(arr2d[r][3-r].equals("X")){
                countplayer1++;
            }else if(arr2d[r][3-r].equals("Y")){
                countplayer2++;
            }
        }
        if(countplayer1==3){
            player1winner=true;
            return;
        }else if(countplayer2==3){
            player2winner=true;
            return;
        }
    }

}
