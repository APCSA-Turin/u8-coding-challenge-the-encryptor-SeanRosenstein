package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Encryptor {
   
    public static int determineColumns(int messageLen, int rows){
        int numCols = messageLen /rows;
        if(messageLen % rows != 0){
            numCols++;
        }
        if(messageLen ==0){
            return 1;
        }
        return numCols;
    }
   
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encryptedArray = new String[rows][determineColumns(message.length(), rows)];
        int count = 1;
        for (int row = 0; row < encryptedArray.length; row ++) {
            for (int col = 0; col < encryptedArray[0].length; col ++) {
                if (count != message.length() + 1) {
                    encryptedArray[row][col] = message.substring(count - 1, count);
                    count ++;
                }
                else {
                    encryptedArray[row][col] = "=";
                }
            }
        }
        return encryptedArray;
    }


    public static String encryptMessage(String message, int rows){
        String[][] encryptedArray = generateEncryptArray(message, rows);
        String encryptedMessage = "";
        for (int col = encryptedArray[0].length - 1; col >= 0; col --) {
            for (int row = 0; row < encryptedArray.length; row ++) {
                encryptedMessage += encryptedArray[row][col];
            }
        }
        return encryptedMessage;
    }
   
    public static String decryptMessage(String encryptedMessage, int rows) {
        int columns = determineColumns(encryptedMessage.length(), rows);
        String[][] decryptedArray = new String[rows][columns];
        int count = 0;
        for (int col = columns - 1; col >= 0; col--) {
            for (int row = 0; row < rows; row++) {
                if (count < encryptedMessage.length()) {
                    decryptedArray[row][col] = encryptedMessage.substring(count, count + 1);
                    count++;
                }
            }
        }
        String decryptedMessage = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (decryptedArray[row][col] != null && !decryptedArray[row][col].equals("=")) {
                    decryptedMessage += decryptedArray[row][col];
                }
            }
        }
        return decryptedMessage;
    }
    public static void main(String[] args) {
        System.out.println(encryptMessage("Well hello there people and things", 2));
        System.out.println(decryptMessage("aPiSr=c r a= ltkb=Ilacuu", 6));


    }
}
