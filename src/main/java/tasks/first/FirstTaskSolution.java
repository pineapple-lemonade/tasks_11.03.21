package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayDeque<String> vertexDeque = new ArrayDeque<>();
        ArrayList<Vertex> vertexArrayList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            vertexArrayList.add(new Vertex(i));
        }
        vertexArrayList.get(startIndex).setPassed(true);
        vertexDeque.offerLast("" + startIndex);
        String result = "";
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if(adjacencyMatrix[startIndex][j] && !vertexArrayList.get(j).isPassed()){
                    vertexArrayList.get(j).setPassed(true);
                    vertexDeque.offerLast("" + j);
                }
            }
            result += vertexDeque.peekFirst() + ",";
            startIndex = Integer.parseInt(vertexDeque.pollFirst());
        }
        return result.substring(0,result.length()-1);
    }

    @Override
    public Boolean validateBrackets(String s) {
        String bracketsPattern = "(){}[]";
        String curly = "{}";
        String round = "()";
        String square = "[]";
        String openBrackets = "([{";
        String closeBrackets = "}])";
        char[] chars = s.toCharArray();
        ArrayDeque<String> brackets = new ArrayDeque<>();
        for (char i:chars) {
            String charAt = "" + i;
            if(bracketsPattern.contains(charAt)){
                brackets.offerLast(charAt);
            }
        }
        if(brackets.size() == 0){
            return true;
        }
        if(brackets.size() % 2 != 0){
            return false;
        }
        int bracketsSize = brackets.size();
        for (int i = 0; i < bracketsSize/2; i++) {
            if(!openBrackets.contains(brackets.getFirst()) || !closeBrackets.contains(brackets.getLast()) || openBrackets.contains(brackets.getLast()) || closeBrackets.contains(brackets.getFirst())){
                return false;
            }
            if(curly.contains(brackets.getFirst()) && curly.contains(brackets.getLast())
                    || round.contains(brackets.getFirst()) && round.contains(brackets.getLast())
                    || square.contains(brackets.getFirst()) && square.contains(brackets.getLast()) ){
            } else {
                return false;
            }
            brackets.pollFirst();
            brackets.pollLast();
        }
        return true;
    }

    @Override
    public Long polishCalculation(String s) {
        String possibleOperators = "-+*/";
        ArrayDeque<Long> numbers = new ArrayDeque<>();
        ArrayList<String> operators = new ArrayList<>();
        String[] splitString = s.split(" ");
        Long last;
        Long predLast;
        for (String elem:splitString) {
            if(possibleOperators.contains(elem)){
                operators.add(elem);
            }
            else{
                numbers.push(Long.parseLong(elem));
            }
        }

        if(operators.size() != numbers.size() - 1){
            throw new IllegalArgumentException("Invalid format of string");
        }

        for (int i = 0; i < operators.size(); i++) {
            last = numbers.pop();
            predLast = numbers.pop();
            switch (operators.get(i)){
                case "-":
                    numbers.push(predLast - last);
                    break;
                case "+":
                    numbers.push(predLast + last);
                    break;
                case "/":
                    numbers.push(predLast/last);
                    break;
                case "*":
                    numbers.push(predLast*last);
                    break;
            }
        }
        return numbers.pop();
    }
}
