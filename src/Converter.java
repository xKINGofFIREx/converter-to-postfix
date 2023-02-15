import java.util.Stack;

public class Converter {

    public static void main(String[] args) {
        convertToPostfixForm("(a + b) * (c + d) - e");
        convertToPostfixForm("(a * b) / (c + d - e)");
        convertToPostfixForm("(e + (a - b)) / (c + d)");
        convertToPostfixForm("e - a * b");
    }

    public static void convertToPostfixForm(String exp) {
        //убираем все пробелы в выражении (если они есть)
        exp = exp.replace(" ", "");

        //создаем стэк для операторов и строку, которая будет постфиксной формой
        String postfixForm = "";
        Stack<Character> stack = new Stack<>();
        String ops = "+-/*";

        //создаем цикл для обхода начального выражения
        for (int i = 0; i < exp.length(); i++) {
            //сохраняем символ на каждой итерации цикла
            char c = exp.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                //операторы выталкиваются из цикла до тех пор, пока символ в стэке не станет равным - (
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfixForm += stack.pop();
                }

                //удаление скобки из стэка
                stack.pop();
            } else if (ops.contains(c + "")) {
                //проверка, что с соответствует одному из знаков в переменной - ops и добавление в стэк
                while (!stack.isEmpty() && priorityCheck(c) <= priorityCheck(stack.peek())) {
                    postfixForm += stack.pop();
                }
                stack.push(c);
            } else {
                postfixForm += c;
            }
        }

        //добавление оставшихся операторов из стэка
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                break;
            postfixForm += stack.peek();
            stack.pop();
        }

        //вывод постфиксной формы
        System.out.println(postfixForm);
    }


    //функция для выставления приоритета операторам
    public static int priorityCheck(char opp)
    {
        switch (opp) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

}
