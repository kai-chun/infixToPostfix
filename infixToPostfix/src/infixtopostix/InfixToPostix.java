/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixtopostix;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author mac
 */
public class InfixToPostix { 

    static int priority(char c){  
        if(c=='*'||c=='/'||c=='%'){  
            return 2;  
        }else if(c=='+'||c=='-'){  
            return 1;  
        }else{  
            return 0;  
        }  
    }  
      
    static boolean isOperator(char c){  
        switch(c){  
            case '(': case ')': case '+': case '-': case '*': case '/' : case '%':  
                return true;  
            default:  
                return false;  
        }  
    }  
      
    public static void main(String[] args) {  
        Scanner input = new Scanner(System.in);    
            
        String s=input.next();    
        Stack<String> st=new Stack<String>();  
        StringTokenizer tokens = new StringTokenizer(s,"+|-|*|/|(|)|%",true);    
            
        while(tokens.hasMoreTokens()){   
  
            String c=tokens.nextToken();  
            String test;  
            boolean doit;  
              
            if(isOperator(c.charAt(0))==false){  
                System.out.print(c+" ");  
            }else{  
                if(c.charAt(0) == '('){  
                    st.push(c);  
                }else if(c.charAt(0) == ')'){  
                    while(st.peek().charAt(0) != '('){  
                        System.out.print(st.pop()+" ");  
                    }  
                    st.pop();  
                }else{  
                    if(st.empty()){  
                        st.push(c);               
                    }else{                        
                        doit=true;  
                        while(!st.empty() && doit){  
                            test=st.pop();  
                            if(priority(test.charAt(0))>=priority(c.charAt(0))){  
                                System.out.print(test+" ");  
                            }else{  
                                st.push(test);  
                                doit = false;  
                            }  
                        }  
                        st.push(c);  
                    }  
                }  
            }  
        }  
        while(!st.empty()){  
            System.out.print(st.pop()+" ");  
        }  
        System.out.println();  
    }   
}  
    
