/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixtopostfix;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author mac
 */
public class InfixToPostfix {

    public static void main(String[] args) {          
        Scanner input=new Scanner(System.in);          
        String a=input.next();          
        StringTokenizer nums = new StringTokenizer(a, "+|-|*|/|(|)|%",true);      
        int times=0;      
        while (nums.hasMoreTokens()) {      
            if(times !=0){      
                System.out.print(" ");      
             }      
            System.out.print(nums.nextToken());      
            times++;      
         }      
            System.out.println();  
    }          
              
}  

