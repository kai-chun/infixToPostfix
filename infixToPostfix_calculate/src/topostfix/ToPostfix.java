/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topostfix;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class ToPostfix {
    
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
    
    static String[] toPostfix(String s){
        Stack<String> st=new Stack<String>();
        StringTokenizer tokens = new StringTokenizer(s,"+|-|*|/|(|)|%",true);  
        String[] pos=new String[s.length()];
        int index=0;
        
        while(tokens.hasMoreTokens()){ 

            String c=tokens.nextToken();
            String test;
            boolean doit;
            
            if(isOperator(c.charAt(0))==false){
                pos[index]=c;
                index++;
            }else{
                if(c.charAt(0) == '('){
                    st.push(c);
                }else if(c.charAt(0) == ')'){
                    while(st.peek().charAt(0) != '('){
                        pos[index]=st.pop();
                        index++;
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
                                pos[index]=test;
                                index++;
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
            pos[index]=st.pop();
            index++;
        }
        return pos;
    }
    
    static int calculateOperator(int a,int b,char c){
        switch(c){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                if(b==0){
                    return a;
                }else{
                    return a/b;
                }
            case '%':
                return a%b;
            default:
                return 0;
        }
    }
    
    static int calculatePostfix(String[] s){
         
        Stack<Integer> st=new Stack<Integer>();
            
            for(int i=0;i<s.length;i++){
                if(s[i]!=null){
                    if(isOperator(s[i].charAt(0))==false){
                        st.push(Integer.parseInt(s[i]));
                    }else{
                        int b=st.pop();
                        int a=st.pop();
                        int temp=calculateOperator(a,b,s[i].charAt(0));
                        st.push(temp);
                    }
                }
            }
        return st.pop();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  
          
        String s=input.next();  
        String[] st=toPostfix(s);
        System.out.println(calculatePostfix(st));
    }
    
}
