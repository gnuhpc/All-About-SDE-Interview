package org.gnuhpc.bigdata.designpattern.interpreter;

import org.gnuhpc.bigdata.designpattern.interpreter.abstractexpression.Expression;
import org.gnuhpc.bigdata.designpattern.interpreter.nonterminalexpression.AndExpression;
import org.gnuhpc.bigdata.designpattern.interpreter.nonterminalexpression.OrExpression;
import org.gnuhpc.bigdata.designpattern.interpreter.terminalexpression.TerminalExpression;

/*
在一些需要解析表达式的地方使用，首先有个共用接口，然后分别实现表达式中的操作和参与操作的数字等。
可以使用java8中的IntBinaryOperator作为一个整数操作的Operator。
解释者模式，一般在解析表达式的时候使用，构造上
1. 一个抽象表达式， 一般定义一个interpreter抽象方法
2. 多个终止表达式，继承抽象表达式，对分解到最终的表达式如何解析提供具体实现（实现interpreter方法），
成员变量上一般为需要操作的数据，比如字符串，数字
3. 多个非终止表达式，继承抽象表达式，对分解中的表达式如何解析提供具体实现（实现interpreter方法，调用表达式成员变量的interpreter方法），
成员变量上一般为多个表达式（几个看需求）

在调用时，首先初始化中止表达式，然后用这些中止表达式构造非终止表达式，最后调用interpreter

* */
public class MainApp {

    static Expression buildInterpreterTree() {

        Expression terminal1 = new TerminalExpression("Lions");
        Expression terminal2 = new TerminalExpression("Tigers");
        Expression terminal3 = new TerminalExpression("Bears");

        // Tigers and Bears
        Expression alternation1 = new AndExpression(terminal2, terminal3);

        //Lions or (Tigers and Bears)
        Expression alternation2 = new OrExpression(terminal1, alternation1);

        return new AndExpression(terminal3, alternation2);
    }

    /**
     * main method - build the Interpreter and then interpret a specific
     * sequence
     *
     * @param args
     */
    public static void main(String[] args) {

        //String context = "Lions";
        //String context = "Tigers";
        //String context = "Bears";
        //String context = "Lions Tigers";
        //String context = "Lions Bears";
        String context = "Tigers Bears";

        Expression define = buildInterpreterTree();

        System.out.println(context + " is " + define.interpret(context));
    }
}
