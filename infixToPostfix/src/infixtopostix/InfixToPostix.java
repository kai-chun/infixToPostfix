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

    static int priority(char c){  // 運算的優先順序
        if (c == '*' || c == '/' || c == '%'){  
            return 2;  
        }else if (c == '+' || c == '-'){  
            return 1;  
        }else{  
            return 0;  
        }  
    }  
      
    static boolean isOperator (char c){  // 判斷是否為運算子
        switch (c){  
            case '(': case ')': case '+': case '-': case '*': case '/' : case '%':  
                return true;  
            default:  
                return false;  
        }  
    }  
      
    public static void main(String[] args) {  
        Scanner input = new Scanner(System.in);    
            
        String s = input.next();    
        Stack<String> st = new Stack<String>();  
        StringTokenizer tokens = new StringTokenizer(s, "+|-|*|/|(|)|%", true);  // 拆解運算式  
            
        while (tokens.hasMoreTokens()){   
  
            String c = tokens.nextToken();  
            String test;  
            boolean doit;  
              
            if (isOperator(c.charAt(0)) == false){ // 若不是運算子，直接印出運算元  
                System.out.print(c + " ");  
            }else{  
                if (c.charAt(0) == '('){  // 看到左括弧，直接 push 
                    st.push(c);  
                }else if (c.charAt(0) == ')'){  // pop 直到遇到左括弧
                    while (st.peek().charAt(0) != '('){  
                        System.out.print(st.pop() + " ");  
                    }  
                    st.pop();  
                }else{  
                    if (st.empty()){  // 若 stack 為空，push next token
                        st.push(c);               
                    }else{                        
                        doit = true;  
                        while (!st.empty() && doit){  
                            test = st.pop();  
                            if (priority(test.charAt(0)) >= priority(c.charAt(0))){  // 若 stack 最上層的運算子優先序大於現在讀入的則印出
                                System.out.print(test + " ");  
                            }else{  // 不是的話，把 pop 出來的放回去
                                st.push(test);  
                                doit = false;  
                            }  
                        }  
                        st.push(c);  
                    }  
                }  
            }  
        }  
        while (!st.empty()){  // 把 stack 剩下的 token 全部印出來
            System.out.print(st.pop() + " ");  
        }  
        System.out.println();  
    }   
}  
    
