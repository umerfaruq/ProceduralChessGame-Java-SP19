/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Dell7240
 */
public class Chess {
    
    public static boolean contains(char[] array, char key) {
        for (int i = 0 ; i<array.length; i++) {
            if (array[i] == key) {
                return true;
            }
        }
    return false;
    }
    
    public static boolean contains(int[][] array, int[] search) {
        for(int i = 0; i<array.length; i++) {
            if(Arrays.equals(array[i], search)) {
                return true;
            }
        }
        return false;
    }
    private static final char[][] blankBoard = new char[8][8];
    public static void blankFillRec(int i,int j) {
        if(i<blankBoard.length) {
            if(i%2==1) {
                if (j<blankBoard[i].length) {
                    if(j%2==1){
                        blankBoard[i][j] = '0';
                    }
                    else{
                        blankBoard[i][j] = '1';
                    }
                    blankFillRec(i,j+1);
                }
                else {
                    return;
                }
            }
            else {
                if(j<blankBoard[i].length) {
                    if(j%2==1){
                        blankBoard[i][j] = '1';
                    }
                    else{
                        blankBoard[i][j] = '0';
                    }
                    blankFillRec(i,j+1);
                }
                else {
                    return;
                }
            }
            blankFillRec(i+1,0);
        } 
    }
    
    
    public static char[][] newBoard() {
        char[][] board = new char[8][8];
        for(int i = 0;i<board.length;i++) {
            board[i] = Arrays.copyOf(blankBoard[i], 8);
        }
        char[] pieces = {'r','n','b','q','k','b','n','r'};
        char[] pawns = {'p','p','p','p','p','p','p','p'};
        board[0] = pieces;
        board[1] = pawns;
        board[7] = ((new String(pieces)).toUpperCase()).toCharArray();
        board[6] = ((new String(pawns)).toUpperCase()).toCharArray();
                
        return board;
        
}

    public static void printBoard(char[][] board, int side) {
        int i,j; char a;
        Dictionary pieces = new Hashtable();
        pieces.put('P',"\u2659"); pieces.put('p',"\u265F"); pieces.put('K',"\u2654");
        pieces.put('k',"\u265A"); pieces.put('Q',"\u2655"); pieces.put('q',"\u265B");
        pieces.put('R',"\u2656"); pieces.put('r',"\u265C"); pieces.put('B',"\u2657");
        pieces.put('b',"\u265D"); pieces.put('N',"\u2658"); pieces.put('n',"\u265E");
        pieces.put('0',"\u2001"); pieces.put('1',"\u2001");
        String ANSI_RESET = "\u001B[0m"; String ANSI_BLACK_BACKGROUND = "\u001B[40m"; 
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        if(side==1) {
            System.out.print("   ");
            
            for(a='a';a<='h';a++){
                System.out.print(a+"\u2001");
            }
            System.out.print("\n  +");
            for(i=0;i<8;i++) {
                System.out.print("-\u2001");
            }
            System.out.print("\b +\n");
            for(i=board.length-1;i>=0;i--) {
                System.out.print((i+1)+" |");
                for(j=0;j<board[i].length;j++) {
                    if (blankBoard[i][j]=='0') {
                        System.out.print(ANSI_BLACK_BACKGROUND+pieces.get(board[i][j])+ANSI_RESET+" ");
                    }
                    else if (blankBoard[i][j]=='1') {
                        System.out.print(ANSI_WHITE_BACKGROUND+pieces.get(board[i][j])+ANSI_RESET+" ");
                    }
                    
                }
                System.out.print("\b| "+(i+1));
                System.out.println();
            }
            System.out.print("  +");
            for(i=0;i<8;i++) {
                System.out.print("-\u2001");
            }
            System.out.print("\b +\n");
            System.out.print("   ");
            for(a='a';a<='h';a++){
                System.out.print(a+"\u2001");
            }
            System.out.println();
        }
        else if(side==0) {
            System.out.print("   ");
            for(a='h';a>='a';a--){
                System.out.print(a+"\u2001");
            }
            System.out.print("\n  +");
            for(i=0;i<8;i++) {
                System.out.print("-\u2001");
            }
            System.out.print("\b +\n");
            for(i=0;i<board.length;i++) {
                System.out.print((i+1)+" |");
                for(j=board[i].length-1;j>=0;j--) {
                    if (blankBoard[i][j]=='0') {
                        System.out.print(ANSI_BLACK_BACKGROUND+pieces.get(board[i][j])+ANSI_RESET+" ");
                    }
                    else if (blankBoard[i][j]=='1') {
                        System.out.print(ANSI_WHITE_BACKGROUND+pieces.get(board[i][j])+ANSI_RESET+" ");
                    }
                }
                System.out.print("\b| "+(i+1));
                System.out.println();
                }
            System.out.print("  +");
            for(i=0;i<8;i++) {
                System.out.print("-\u2001");
            }
            System.out.print("\b +\n");
            System.out.print("   ");
            for(a='h';a>='a';a--){
                System.out.print(a+"\u2001");
            }
            System.out.println();
        }
    }
    
    
    public static void moveInput(char[] move, int select) {
        Scanner input = new Scanner(System.in);
        String str;
        if(select == 0) {
//            System.out.print("Enter location of piece that you want to move: ");
//            str = input.next().toLowerCase();
            while(true) {
                System.out.print("Enter location of piece that you want to move: ");
                str = input.next().toLowerCase();
                try {
                    move[0] = (char) ((int) str.charAt(0) - 49);
                    move[1] = (char) ((int) str.charAt(1) - 1) ;
                    break;
                } catch(Exception e) {
                    System.out.println("Wrong Input");
                    }
            }
            if(!(move[0]>='0'&&move[0]<='7')) {
                throw new ArrayIndexOutOfBoundsException("OutOfBounds");
            }
            else if(!(move[1]>='0'&&move[1]<='7')) {
                throw new ArrayIndexOutOfBoundsException("OutOfBounds");
            }
        }
        if(select == 1) {
//            System.out.print("Enter location where you want to move it: ");
//            str = input.next().toLowerCase();
            while(true) {
                System.out.print("Enter location where you want to move it: ");
                str = input.next().toLowerCase();
                try {
                    move[2] = (char) ((int) str.charAt(0) - 49);
                    move[3] = (char) ((int) str.charAt(1) - 1) ;
                    break;
                } catch(Exception e) {
                    System.out.println("Wrong Input");
                    }
            }
            if(!(move[2]>='0'&&move[2]<='7')) {
                throw new ArrayIndexOutOfBoundsException("OutOfBounds");
            }
            else if(!(move[3]>='0'&&move[3]<='7')) {
                throw new ArrayIndexOutOfBoundsException("OutOfBounds");
            }
        }

    }

    

    
    public static boolean checkRule(char[][] board, char[] move, int side) {
        int i=0,j=0,k=0;
        char[] piecesB = "rnbqkp".toCharArray();
        char[] piecesA = "RNBQKP".toCharArray();
        char[][] pieces = {piecesA,piecesB};
        int xNew = ((int) move[3])-48; int yNew = ((int) move[2])-48;
        int xOld = ((int) move[1])-48; int yOld = ((int) move[0])-48;
        int[] newXY = {xNew,yNew};
        if(xNew==xOld && yNew == yOld) {
            System.out.println("Same Place");
            return false;
        }
        if(board[xOld][yOld]=='0' || board[xOld][yOld]=='1') {
            System.out.println("No Piece");
            return false;
        }
        if(contains(pieces[(int) Math.pow(0, side)],board[xOld][yOld])) {
            System.out.println("Wrong Side");
            return false;
        }
        if(contains(pieces[side],board[xNew][yNew])) {
            System.out.println("Your Team");
            return false;
        }

        if(board[xOld][yOld]==pieces[side][0]) {                      //Rook
            int maxXUp=0,maxXDown=0,maxYRight=0,maxYLeft=0;
            for(i=xOld+1;i<=8;i++) {
                try{
                    maxXUp = i;
                    if(contains(pieces[side],board[i][yOld])) {
                        System.out.println(board[i][yOld]+" "+pieces[side][i]);
                        maxXUp = i-1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][yOld])) {
                        maxXUp = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=xOld-1;i>=-1;i--) {
                try{
                    maxXDown=i;
                    if(contains(pieces[side],board[i][yOld])) {
                        maxXDown = i+1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][yOld])) {
                        maxXDown = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=yOld+1;i<=8;i++) {
                try{
                    maxYRight=i;
                    if(contains(pieces[side],board[xOld][i])) {
                        maxYRight = i-1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[xOld][i])) {
                        maxYRight = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=yOld-1;i>=-1;i--) {
                try{
                    maxYLeft=i;
                    if(contains(pieces[side],board[xOld][i])) {
                        maxYLeft = i+1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[xOld][i])) {
                        maxYLeft = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            if(yOld==yNew){
                if((xNew<=maxXUp) && (xNew>=maxXDown)) {
                    return true;
                }
            }
            else if(xOld==xNew){
                if((yNew<=maxYRight) && (yNew>=maxYLeft)) {
                    return true;
                }
            }
            else{
                return false;
            }

        }
        else if(board[xOld][yOld]==pieces[side][1]) {        //Knight
            int[] upRight = {xOld+2,yOld+1};
            int[] upLeft = {xOld+2,yOld-1};
            int[] rightUp = {xOld+1,yOld+2};
            int[] rightDown = {xOld-1,yOld+2};
            int[] leftUp = {xOld+1,yOld-2};
            int[] leftDown = {xOld-1,yOld-2};
            int[] downRight = {xOld-2,yOld+1};
            int[] downLeft = {xOld-2,yOld-1};
            int[][] moves = {upRight,upLeft,rightUp,rightDown,leftUp,leftDown,downRight,downLeft};
            for(i=0;i<moves.length;i++) {
                if(xNew==moves[i][0] && yNew==moves[i][1]) {
                    return true;
                }
                
            }
            return false;
        }
        else if(board[xOld][yOld]==pieces[side][2]) {                   //Bishop
            int[][] maxUpRight = new int[8][2]; int[][] maxUpLeft = new int[8][2];
            int[][] maxDownRight = new int[8][2]; int[][] maxDownLeft = new int[8][2];
            
            for(i=xOld+1,j=yOld+1,k=0;i<=8;i++,j++,k++) {
                try{
                    maxUpRight[k][0] = i;
                    maxUpRight[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxUpRight[k][0] = (i-1);
                        maxUpRight[k][1] = (j-1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxUpRight[k][0] = i;
                        maxUpRight[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
                
            }
            for(i=xOld+1,j=yOld-1,k=0;i<=8;i++,j--,k++) {
                try{
                    maxUpLeft[k][0] = i;
                    maxUpLeft[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxUpLeft[k][0] = (i-1);
                        maxUpLeft[k][1] = (j+1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxUpLeft[k][0] = i;
                        maxUpLeft[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
            }
            for(i=xOld-1,j=yOld+1,k=0;i<=8;i--,j++,k++) {
                try{
                    maxDownRight[k][0] = i;
                    maxDownRight[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxDownRight[k][0] = (i+1);
                        maxDownRight[k][1] = (j-1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxDownRight[k][0] = i;
                        maxDownRight[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=xOld-1,j=yOld-1,k=0;i<8;i--,j--,k++) {
                try {
                    maxDownLeft[k][0] = i;
                    maxDownLeft[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxDownLeft[k][0] = (i+1);
                        maxDownLeft[k][1] = (j+1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxDownLeft[k][0] = i;
                        maxDownLeft[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
            }
            
            if(contains(maxUpRight,newXY)) {
                return true;
            }
            else if(contains(maxUpLeft,newXY)) {
                return true;
            }
            else if(contains(maxDownRight,newXY)) {
                return true;
            }
            else if(contains(maxDownLeft,newXY)) {
                return true;
            }
            else {
                return false;
            }
        }
        else if(board[xOld][yOld]==pieces[side][3]) {      //Queen
            int maxXUp=0,maxXDown=0,maxYRight=0,maxYLeft=0;
            int[][] maxUpRight = new int[8][2]; int[][] maxUpLeft = new int[8][2];
            int[][] maxDownRight = new int[8][2]; int[][] maxDownLeft = new int[8][2];
            for(i=xOld+1;i<=8;i++) {
                try{
                    maxXUp = i;
                    if(contains(pieces[side],board[i][yOld])) {
                        System.out.println(board[i][yOld]+" "+pieces[side][i]);
                        maxXUp = i-1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][yOld])) {
                        maxXUp = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=xOld-1;i>=-1;i--) {
                try{
                    maxXDown=i;
                    if(contains(pieces[side],board[i][yOld])) {
                        maxXDown = i+1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][yOld])) {
                        maxXDown = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=yOld+1;i<=8;i++) {
                try{
                    maxYRight=i;
                    if(contains(pieces[side],board[xOld][i])) {
                        maxYRight = i-1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[xOld][i])) {
                        maxYRight = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=yOld-1;i>=-1;i--) {
                try{
                    maxYLeft=i;
                    if(contains(pieces[side],board[xOld][i])) {
                        maxYLeft = i+1;
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[xOld][i])) {
                        maxYLeft = i;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=xOld+1,j=yOld+1,k=0;i<=8;i++,j++,k++) {
                try{
                    maxUpRight[k][0] = i;
                    maxUpRight[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxUpRight[k][0] = (i-1);
                        maxUpRight[k][1] = (j-1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxUpRight[k][0] = i;
                        maxUpRight[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
                
            }
            for(i=xOld+1,j=yOld-1,k=0;i<=8;i++,j--,k++) {
                try{
                    maxUpLeft[k][0] = i;
                    maxUpLeft[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxUpLeft[k][0] = (i-1);
                        maxUpLeft[k][1] = (j+1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxUpLeft[k][0] = i;
                        maxUpLeft[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
            }
            for(i=xOld-1,j=yOld+1,k=0;i<=8;i--,j++,k++) {
                try{
                    maxDownRight[k][0] = i;
                    maxDownRight[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxDownRight[k][0] = (i+1);
                        maxDownRight[k][1] = (j-1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxDownRight[k][0] = i;
                        maxDownRight[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
            }
            for(i=xOld-1,j=yOld-1,k=0;i<8;i--,j--,k++) {
                try {
                    maxDownLeft[k][0] = i;
                    maxDownLeft[k][1] = j;
                    if(contains(pieces[side],board[i][j])) {
                        maxDownLeft[k][0] = (i+1);
                        maxDownLeft[k][1] = (j+1);
                        break;
                    }
                    else if(contains(pieces[(int) Math.pow(0, side)],board[i][j])) {
                        maxDownLeft[k][0] = i;
                        maxDownLeft[k][1] = j;
                        break;
                    }
                } catch(Exception e) {
                    }
                
            }
            if(yOld==yNew){
                if((xNew<=maxXUp) && (xNew>=maxXDown)) {
                    return true;
                }
            }
            else if(xOld==xNew){
                if((yNew<=maxYRight) && (yNew>=maxYLeft)) {
                    return true;
                }
            }
            
            if(contains(maxUpRight,newXY)) {
                return true;
            }
            else if(contains(maxUpLeft,newXY)) {
                return true;
            }
            else if(contains(maxDownRight,newXY)) {
                return true;
            }
            else if(contains(maxDownLeft,newXY)) {
                return true;
            }
            else {
                return false;
            }
        }
        
        else if(board[xOld][yOld]==pieces[side][4]) {                //King
            int[][] moves = new int[8][2];
            k=0;
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld+1][yOld]) || board[xOld+1][yOld]=='0' || board[xOld+1][yOld] == '1') {
                    moves[k][0] = xOld+1;
                    moves[k][1] = yOld;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld-1][yOld]) || board[xOld-1][yOld]=='0' || board[xOld-1][yOld] == '1') {
                    moves[k][0] = xOld-1;
                    moves[k][1] = yOld;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld][yOld+1]) || board[xOld][yOld+1]=='0' || board[xOld][yOld+1] == '1') {
                    moves[k][0] = xOld;
                    moves[k][1] = yOld+1;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld][yOld-1]) || board[xOld][yOld-1]=='0' || board[xOld][yOld-1] == '1') {
                    moves[k][0] = xOld;
                    moves[k][1] = yOld-1;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld+1][yOld+1]) || board[xOld+1][yOld+1]=='0' || board[xOld+1][yOld+1] == '1') {
                    moves[k][0] = xOld+1;
                    moves[k][1] = yOld+1;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld+1][yOld-1]) || board[xOld+1][yOld-1]=='0' || board[xOld+1][yOld-1] == '1') {
                    moves[k][0] = xOld+1;
                    moves[k][1] = yOld-1;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld-1][yOld+1]) || board[xOld-1][yOld+1]=='0' || board[xOld-1][yOld+1] == '1') {
                    moves[k][0] = xOld-1;
                    moves[k][1] = yOld+1;
                    k++;
                }
            } catch(Exception e) {
                }
            try{
                if(contains(pieces[(int) Math.pow(0, side)],board[xOld-1][yOld-1]) || board[xOld-1][yOld-1]=='0' || board[xOld-1][yOld-1] == '1') {
                    moves[k][0] = xOld-1;
                    moves[k][1] = yOld-1;
                    k++;
                }
            } catch(Exception e) {
                }
            if(contains(moves,newXY)) {
                return true;
            }
            else {
                return false;
            }
            
        }
        else if(board[xOld][yOld]==pieces[side][5]) {                   //Pawn
            int[][] moves = new int[5][2];
            k=0;
            if(side==1) {
                if(xOld==1) {
                    for(j=1;j<=2;j++) {
                        if(board[xOld+j][yOld] == '0' || board[xOld+j][yOld] == '1') {
                            moves[k][0] = xOld+j;
                            moves[k][1] = yOld;
                            k++;
                        }
                    }
                }
                try{
                    if(board[xOld+1][yOld] == '0' || board[xOld+1][yOld] == '1') {
                        moves[k][0] = xOld+1;
                        moves[k][1] = yOld;
                        k++;
                    }
                } catch(Exception e) {
                    }
                try{
                    if(contains(pieces[(int) Math.pow(0, side)],board[xOld+1][yOld+1])) {
                        moves[k][0] = xOld+1;
                        moves[k][1] = yOld+1;
                        k++;
                    }
                } catch(Exception e) {
                    }
                try{
                    if(contains(pieces[(int) Math.pow(0, side)],board[xOld+1][yOld-1])) {
                        moves[k][0] = xOld+1;
                        moves[k][1] = yOld-1;
                        k++;
                    }
                } catch(Exception e) {
                    }
                
            }
            else if(side==0) {
                if(xOld==6) {
                    for(j=1;j<=2;j++) {
                        if(board[xOld-j][yOld] == '0' || board[xOld-j][yOld] == '1') {
                            moves[k][0] = xOld-j;
                            moves[k][1] = yOld;
                            k++;
                        }
                    }
                }
                try{
                    if(board[xOld-1][yOld] == '0' || board[xOld-1][yOld] == '1') {
                        moves[k][0] = xOld-1;
                        moves[k][1] = yOld;
                        k++;
                    }
                } catch(Exception e) {
                    }
                try{
                    if(contains(pieces[(int) Math.pow(0, side)],board[xOld-1][yOld+1])) {
                        moves[k][0] = xOld-1;
                        moves[k][1] = yOld+1;
                        k++;
                    }
                } catch(Exception e) {
                    }
                try{
                    if(contains(pieces[(int) Math.pow(0, side)],board[xOld-1][yOld-1])) {
                        moves[k][0] = xOld-1;
                        moves[k][1] = yOld-1;
                        k++;
                    }
                } catch(Exception e) {
                    }
            }
            
            if(contains(moves,newXY)) {
                return true;
            }
            else {
                return false;
            }  
        }
        return false;
    }
        
    
      

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        blankFillRec(0,0);
        Scanner input = new Scanner(System.in);
        char exit = 'n';
        char[][] chessBoard = newBoard();
        int select = 0; int i,j,l;
        char[] piecesB = "rnbqkp".toCharArray();
        char[] piecesA = "RNBQKP".toCharArray();
        char[][] pieces = {piecesA,piecesB};
        while(true) {
            System.out.println("\t\t\tCHESS");
            System.out.println("1. NEW GAME\n2. LOAD GAME\n3. CHECK PIECE\n4. EXIT");
            System.out.print("Select an option: ");
            select = input.nextInt();
            if(select==1) {
                break;
            }
            else if(select==2) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader("Save_Game.txt"));
                    String piece = in.readLine();
                    System.out.println(piece);
                    for(i=0,l=0;i<8;i++){
                        for(j=0;j<8;j++) {
                            chessBoard[i][j]=piece.charAt(l);
                            l++;
                        }
                    }
                    in.close();
                    
                } catch( IOException ioException ) {
                    }
                break;
            }
            else if(select==3) {
                for(i=0;i<blankBoard.length;i++) {
                    chessBoard[i] = Arrays.copyOf(blankBoard[i],8);
                }
                for(i=0;i<pieces.length;i++) {
                    System.out.println();
                    for(j=0;j<pieces[i].length;j++) {
                       System.out.print(pieces[i][j]+" "); 
                    }
                }
                
                System.out.print("\nEnter Piece: ");
                chessBoard[4][4] = input.next().charAt(0);
                break;
            }
            else if(select==4) {
                exit='y';
                break;
            }
            else if(select!=1 || select!= 2 || select!= 3 || select!=4) {
                System.out.println("Wrong Input");
            }
        }
        
        

        
        char[] move = new char[4];
        
        int endFlag = 0;
        char[][] fallen = new char[2][16];
        int side = 1; int[] k = new int[2]; 
        
        while(exit=='n') {
            printBoard(chessBoard,side);
            System.out.println("Player "+side);
            while(true) {
                try{
                    moveInput(move,0);
                    moveInput(move,1);
                    break;
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("WRONG INPUT! TRY AGAIN");
                }
            }
            int xNew = ((int) move[3])-48; int yNew = ((int) move[2])-48;
            int xOld = ((int) move[1])-48; int yOld = ((int) move[0])-48;
            System.out.println("Selected Piece: "+chessBoard[xOld][yOld]+"("+xOld+","+yOld+")");
            System.out.println("Piece at new location: "+chessBoard[xNew][yNew]+"("+xNew+","+yNew+")");
            if(checkRule(chessBoard,move,side)) {
                if(contains(pieces[(int) Math.pow(0, side)],chessBoard[xNew][yNew])) {
                    fallen[(int) Math.pow(0, side)][k[(int) Math.pow(0, side)]] = chessBoard[xNew][yNew];
                    k[(int) Math.pow(0, side)]++;
                }
                chessBoard[xNew][yNew]=chessBoard[xOld][yOld];
                chessBoard[xOld][yOld]=blankBoard[xOld][yOld];
            }
            else{
                System.out.println("Illegal");
                continue;
            }
            try{
                if(fallen[(int) Math.pow(0, side)][k[(int) Math.pow(0, side)-1]]==pieces[side][4]) {
                    System.out.println("CHECKMATE!\nPLAYER "+side+" HAS WON");
                    endFlag=1;
                    break;        
                }
            } catch(Exception e) {
                }
            if((xNew==7 || xNew==0) && chessBoard[xNew][yNew]==pieces[side][5]) {
                chessBoard[xNew][yNew] = pieces[side][3];
            }
            
            
            if(select!=3) {       
                side = (side == 0) ? 1:0;
            }
            System.out.print("SAVE AND EXIT? (Y/N): ");
            exit = input.next().charAt(0);
        }
        if(endFlag!=1 && select !=4){
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("Save_Game.txt"), "utf-8"));
                for(i=0;i<chessBoard.length;i++){
                    writer.write(chessBoard[i]);
                }
            } catch (IOException ex) {
            } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
            }
        }
        System.out.println("PROGRAM EXITED");
        
    }
    
}
