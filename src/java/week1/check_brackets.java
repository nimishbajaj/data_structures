package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    char type;
    int position;

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }
}

class check_brackets {
    public static void main(String[] args) throws IOException {

//        while (true) {
            InputStreamReader input_stream = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input_stream);
            String text = reader.readLine();
            String out = "";

            Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
            for (int position = 0; position < text.length(); ++position) {
                char next = text.charAt(position);

                if (next == '(' || next == '[' || next == '{') {
                    opening_brackets_stack.push(new Bracket(next, position + 1));
                }

                if (next == ')' || next == ']' || next == '}') {
                    if (!opening_brackets_stack.empty() && opening_brackets_stack.peek().Match(next)) {
                        opening_brackets_stack.pop();
                    } else {
                        out = "" + (position + 1);
                        break;
                    }
                }
            }

            if (out.length() > 0) System.out.println(out);
            else if (opening_brackets_stack.size() == 0) System.out.println("Success");
            else System.out.println(opening_brackets_stack.get(0).position);
//        }


        // Printing answer, write your code here
    }
}
