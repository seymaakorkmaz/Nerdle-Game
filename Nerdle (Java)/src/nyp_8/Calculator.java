package nyp_8;

import java.util.Stack;

public class Calculator { //stack ile denklem sonucunu bulma

    public boolean isDigit(char c) { // char rakam mý diye kontrol ediliyor.
        return (c >= '0' && c <= '9');
    }

    public boolean isOperator(char c) { // islem onceliklerini atýyor.
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    public int getPrecedence(char c) {
        switch(c) {

            case '+' :
            case '-' : return 1;

            case '*' :
            case '/' : return 2;

            default  : return -1;

        }
    }

    public int operate(int val1, int val2, char op) {  //dört islem 
        int result = 0;
        if(op == '+')
            result =  (val1 + val2);
        if(op == '-')
            result = (val1 - val2);
        if(op == '*')
            result = (val1 * val2);
        if(op == '/')
            if(val2 != 0)
                 result = (val1 / val2);

        return result;

    }

    public int calculate (String s) {  //denklemin eþittire kadar olan kýsmýný s ile alýyoruz
        Stack<Integer> values = new Stack<Integer>(); //rakamlar icin stack
        Stack<Character> operators = new Stack<Character>(); //operatorler icin stack

        int val = 0; 
        int i = 0;

        while(i < s.length()) {

            char step = s.charAt(i); 

            if (isDigit(step)) {

                val = (val * 10) + (int) (step - '0'); // art arda gelen basamaklarý sayýya çeviriyoruz.

            }else if (isOperator(step)){

                if(values.isEmpty()) {

                    values.push(val);  
                    operators.push(step);
                    val = 0;

                }else {

                    char prev = operators.peek();  //operator stackinin en üstüne bakýyoruz.

                    if(getPrecedence(step) > getPrecedence(prev)) { 
                    	//eklenecek olan ve stackteki islemin önceligi karsilastirilir.
                        values.push(val);   //yeni gelenin önceligi yüksekse pushlanir.
                        operators.push(step);
                        val = 0;

                    }else {

                        int prevVal = values.pop();  //yeni gelenin önceliði düþükse poplanir.
                        char prevOp = operators.pop();
                        prevVal = operate( prevVal, val, prevOp);  //islem yapilir
                        values.push(prevVal); // deðerler stackine pushlanir.
                        operators.push(step);
                        val = 0;

                    }
                }

            }

            i++;

        }

        while (!operators.isEmpty()) {

            int prev = values.pop();  //operator stacki bosalana kadar poplanir.
            char step = operators.pop();
            val = operate (prev, val, step); //islemler yapilir.

        }

        return val;
    }

}