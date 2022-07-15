package nyp_8;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    Calculator calculator = new Calculator();
    public ArrayList<String> GenerateEquation(int randomLength) {

        ArrayList<String> equation = new ArrayList<>();
        calculator = new Calculator();
        String[] opList = new String[5];
        String a;

        int flag = 0;
        boolean divFlag = false;
        int operatorCounter = 0;
        int lastVal = 0;

        opList[0] = "+";
        opList[1] = "-";
        opList[2] = "*";
        opList[3] = "/";
        opList[4] = "=";

        Random rand = new Random();
        int choice, operator, operandRepeat = 0;
        int operand = rand.nextInt(9) + 1;
        int digit , divCounter = 0;
        int val = operand;
        equation.add(String.valueOf(operand));
        operandRepeat++;

        do {
            Random random = new Random();

            if (operandRepeat == 0) //rakam secilmemisse iki islem art arda yazmamak icin rakakm secilir.
                choice = 0;

            else {
                if (operandRepeat < 3)
                    choice = random.nextInt(2);                            // 0 ise operand 1 ise operator üretilir.
                else
                    choice = 1;
            }

            //operand -> sayi
            // operator -> islem

            switch (choice) {

                case 0:

                    if(divFlag){ //bir onceki islem bolme ise ona uygun sayi uretilir.
                        operand = validNumber(lastVal);
                        operandRepeat = 30;         //choice 'u 1 yapmak için 
                        int number = operand;
                        int i = countDigit(number);
                        int x;
                        while(i > 0){
                            x= number / (int) Math.pow(10,i-1);
                            number = number % (int) Math.pow(10,i-1);
                            equation.add(String.valueOf(x));
                            i--;
                        }

                    }
                    else{
                        if (operandRepeat == 0) //0 ile baslayan sayi üretmemek icin kontrol yapar.
                            operand = random.nextInt(9) + 1;
                        else
                            operand = random.nextInt(10);

                        val = (val * 10) + operand; //rakamlari sayiya donusturur.

                        operandRepeat++;
                        equation.add(String.valueOf(operand));
                    }



                   break;

                case 1:
                    divFlag = false;
                    if(divCounter == 1) 
                        operator = random.nextInt(3);
                    else
                        operator = random.nextInt(4);

                    equation.add(opList[operator]);

                    if (operator == 3){ //bolme islemi geldiyse divFlag 1 olur.
                        divFlag = true;
                        divCounter = 1;
                    }

                    operandRepeat = 0;
                    lastVal = val;
                    val = 0;
                    operatorCounter++;

            }

            a = equation.toString();
            digit = countDigit(calculator.calculate(a));
            	//denklem kurallari sagliyor mu diye kontrol edilir
            if (operandRepeat != 0 && (equation.size() + 1 + digit) == randomLength && operatorCounter != 0 && calculator.calculate(a) >= 0 )
                flag = 1;
            //denklem kurallari saglamiyorsa yeni denklem olusturmak icin her sey sifirlanir.
            else if((equation.size() + 1 + digit) > randomLength){
                operandRepeat = 0;
                operatorCounter = 0;
                equation.clear();
                operand = rand.nextInt(9) + 1;
                equation.add(String.valueOf(operand));
                operandRepeat++;
                val = operand;
                divCounter = 0;
            }
         /*
            System.out.println("equation :" + a);
            System.out.println(calculator.calculate(a));*/
            
        } while (flag == 0);

        return equation;

    }


    public int countDigit(int number) { //basamak sayisi sayilir.
        int digit = 0;
        while (number > 0) {
            number /= 10;
            digit++;
        }
        return digit;
    }

    public boolean isInteger(int a, int b) { //Integer mi kontrolü
        if ((a > b) && (a % b) == 0) {
            return true;
        } else
            return false;
    }


    public int validNumber(int lastVal) { //bolme isleminden sonra uygun bir sayi dondurur.

        int number;
        Random random = new Random();
        do {
            number = random.nextInt(lastVal) + 1;
        } while (lastVal % number != 0);

        return number;
    }

    public String print(ArrayList<String> equation){ //denklemi birlestirir.
        String a = "";
        for(String s : equation){
           a += s;
        }
        a += "=";
        a += calculator.calculate(a);
        return a;
    }
    public String convertEquation(ArrayList<String> eq){
        String equation = "";
        for(String s : eq){
            equation += s;
        }
        return equation;
    }

    public String concatEquation(ArrayList<String> equation) {


        String finalEq = convertEquation(equation);
        finalEq += "=";
        finalEq += String.valueOf(calculator.calculate(convertEquation(equation)));


        return finalEq;
    }
}