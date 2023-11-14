
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
    public static boolean boardFull=false;

    public static void TicTacToe()
    {
        takeInput();
    }

    public static void takeInput(){
        Scanner sc=new Scanner(System.in);
        
    
        if(!player1winner&&!player2winner&&!boardFull){ //make new moves
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

        }else{ //has a winner or the board is full
            String input=sc.nextLine();
            if(input.equals("Y")){ //reset everything to restart
                player1winner=false;
                player2winner=false;
                roundCount=1;
                boardFull=false;
                player1=true;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        board[i][j]=null;
                    }
                }
                takeInput();
            }else if(input.equals("N")){ //don't restart
                return;
            }else{ //invalid input (not Y or N)
                System.out.println("Invalid Input: please enter Y or N");
                takeInput();
            }
        }

    }

    public static void newMove(String r, String c){//make new moves 
        //turn string into int
        int ri=Integer.valueOf(r);
        int ci=Integer.valueOf(c);

        //check if the coordinate is out of bound
        if(ri>=3 || ci>=3){
            System.out.println("Invalid Input: Try Again(Out of Bound)!");
            takeInput();
            return;
        }else{
            if(board[ri][ci]!=null){ //duplicate input
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
                if(!player1winner&&!player2winner&&!boardFull){
                    takeInput();
                }else{ //if there's a winner or the board is full -> end the game
                    if(player1winner){
                        System.out.println("Player 1 Won!");
                    }else if(player2winner){
                        System.out.println("Player 2 Won!");
                    }else if(boardFull){
                        System.out.println("Board Full! It's a tie!");
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
        int countplayer1=0;
        int countplayer2=0;
        
        //check1: row then column
        for(int r=0; r<arr2d.length; r++){
            countplayer1=0;
            countplayer2=0;
            for(int c=0; c<arr2d[0].length; c++){
                if(arr2d[r][c]==null){
                    break;
                }
                if(arr2d[r][c].equals("X")){
                    countplayer1++;
                }else if(arr2d[r][c].equals("Y")){
                    countplayer2++;
                }
            }
            if(has3Points(countplayer1, countplayer2)){
                return;
            }
        }
        
        //check2: column then row
        for(int c=0; c<arr2d[0].length; c++){
            countplayer1=0;
            countplayer2=0;
            for(int r=0; r<arr2d.length; r++){
                if(arr2d[r][c]==null){
                    break;
                }
                if(arr2d[r][c].equals("X")){
                    countplayer1++;
                }else if(arr2d[r][c].equals("Y")){
                    countplayer2++;
                }
            }
            if(has3Points(countplayer1, countplayer2)){
                return;
            }
        }

        //check3: left to right diagonal
        countplayer1=0;
        countplayer2=0;
        for(int r=0; r<arr2d.length; r++){
            if(arr2d[r][r]==null){
                break;
            }
            if(arr2d[r][r].equals("X")){
                countplayer1++;
            }else if(arr2d[r][r].equals("Y")){
                countplayer2++;
            }
        }
        if(has3Points(countplayer1, countplayer2)){
            return;
        }


        //check4: right to left diagonal
        countplayer1=0;
        countplayer2=0;
        for(int r=0; r<arr2d.length; r++){
            if(arr2d[r][2-r]==null){
                break;
            }
            if(arr2d[r][2-r].equals("X")){
                countplayer1++;
            }else if(arr2d[r][2-r].equals("Y")){
                countplayer2++;
            }
        }
        if(has3Points(countplayer1, countplayer2)){
            return;
        }
        
        //check if the board is full
        boardFull=true;
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(board[r][c]==null){
                    boardFull=false;
                    return;
                }
            }
        }
    }

    public static boolean has3Points(int p1w, int p2w){
        if(p1w==3){
            player1winner=true;
        }else if(p2w==3){
            player2winner=true;
        }
        return false;
    }

}
