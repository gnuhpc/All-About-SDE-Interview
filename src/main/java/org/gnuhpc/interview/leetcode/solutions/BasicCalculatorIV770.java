package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/1/24
 */
public class BasicCalculatorIV770 {
    public static List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        class Item implements Comparable<Item> {
            int coeff;
            private final ArrayList<String> factors;

            private Item(String factor, int coeff) {
                this.factors = new ArrayList<>();
                this.factors.add(factor);
                this.coeff = coeff;
            }

            private Item(int coeff) {
                this.factors = new ArrayList<>();
                this.coeff = coeff;
            }

            private Item() {
                this.factors = new ArrayList<>();
                this.coeff = 0;
            }

            @Override
            public int compareTo(Item item) {
                if (this.factors.size() == item.factors.size()) {
                    int index = 0;
                    while (index < factors.size() && this.factors.get(index).equals(item.factors.get(index))) {
                        index += 1;
                    }
                    return (index == factors.size()) ? 0 : this.factors.get(index).compareTo(item.factors.get(index));
                } else {
                    return item.factors.size() - this.factors.size();
                }
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(coeff);
                for (String factor : factors) {
                    stringBuilder.append("*").append(factor);
                }
                return stringBuilder.toString();
            }

            Item mul(Item item) {
                Item result = new Item();
                result.coeff = this.coeff * item.coeff;
                result.factors.addAll(this.factors);
                result.factors.addAll(item.factors);
                result.factors.sort(String::compareTo);
                return result;
            }
        }

        class Expr {
            private ArrayList<Item> items;

            private Expr(Item item) {
                this.items = new ArrayList<>();
                this.items.add(item);
            }

            private void add(Expr expr) {
                items.addAll(expr.items);
                items.sort(Item::compareTo);
                clean();
            }

            private void mul(Expr expr) {
                ArrayList<Item> result = new ArrayList<>();
                for (Item item1 : items) {
                    for (Item item2 : expr.items) {
                        result.add(item1.mul(item2));
                    }
                }
                items = result;
                items.sort(Item::compareTo);
                clean();
            }

            private void clean() {
                for (int i = 0; i < items.size(); i++) {
                    while (i + 1 < items.size() && items.get(i).compareTo(items.get(i + 1)) == 0) {
                        items.get(i).coeff += items.get(i + 1).coeff;
                        items.remove(i + 1);
                    }
                    if (i < items.size() && items.get(i).coeff == 0) {
                        items.remove(i--);
                    }
                }
            }

            private Expr operate(Expr expr, String op) {
                switch (op) {
                    case "+":
                        add(expr);
                        break;
                    case "-":
                        for (Item item : expr.items) {
                            item.coeff *= -1;
                        }
                        add(expr);
                        break;
                    case "*":
                        mul(expr);
                        break;
                }
                return this;
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }

        LinkedList<Expr> mainStack = new LinkedList<>();
        LinkedList<String> symStack = new LinkedList<>();
        int index = 0;
        while (index < expression.length()) {
            if (expression.charAt(index) == ' ') {
                index += 1;
            } else if (expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
                int x = 0;
                while (index < expression.length() && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
                    x = x * 10 + expression.charAt(index++) - '0';
                }
                mainStack.push(new Expr(new Item(x)));
            } else if (expression.charAt(index) >= 'a' && expression.charAt(index) <= 'z') {
                StringBuilder stringBuilder = new StringBuilder();
                while (index < expression.length() && expression.charAt(index) >= 'a' && expression.charAt(index) <= 'z') {
                    stringBuilder.append(expression.charAt(index++));
                }
                String factor = stringBuilder.toString();
                if (map.containsKey(factor)) {
                    mainStack.push(new Expr(new Item(map.get(factor))));
                } else {
                    mainStack.push(new Expr(new Item(stringBuilder.toString(), 1)));
                }
            } else if (expression.charAt(index) == '(') {
                symStack.push("(");
                index += 1;
            } else if (expression.charAt(index) == ')') {
                while (!symStack.isEmpty() && !symStack.peek().equals("(")) {
                    Expr expr2 = mainStack.pop();
                    Expr expr1 = mainStack.pop();
                    mainStack.push(expr1.operate(expr2, symStack.pop()));

                }
                symStack.pop();
                index += 1;
            } else if (expression.charAt(index) == '*') {
                while (!symStack.isEmpty() && symStack.peek().equals("*")) {
                    Expr expr2 = mainStack.pop();
                    Expr expr1 = mainStack.pop();
                    mainStack.push(expr1.operate(expr2, symStack.pop()));
                }
                symStack.push("*");
                index += 1;
            } else {
                while (!symStack.isEmpty() && (symStack.peek().equals("+") || symStack.peek().equals("-") || symStack.peek().equals("*"))) {
                    Expr expr2 = mainStack.pop();
                    Expr expr1 = mainStack.pop();
                    mainStack.push(expr1.operate(expr2, symStack.pop()));
                }
                symStack.push((expression.charAt(index) == '+') ? "+" : "-");
                index += 1;
            }
        }
        while (!symStack.isEmpty()) {
            Expr expr2 = mainStack.pop();
            Expr expr1 = mainStack.pop();
            mainStack.push(expr1.operate(expr2, symStack.pop()));
        }

        ArrayList<String> result = new ArrayList<>();
        Expr expr = mainStack.pop();
        expr.clean();
        for (Item item : expr.items) {
            result.add(item.toString());
        }
        return result;
    }
}
