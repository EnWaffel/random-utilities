package de.enwaffel.script;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public final class Script {

    private static char[] buff;
    private static int cur;
    private static ArrayList<Expr> expressions;

    public static ScriptSource load(String str) {
        buff = str.toCharArray();
        expressions = new ArrayList<>();
        cur = 0;

        Expr.Func func_print = new Expr.Func();
        func_print.name = "print";
        Expr.Var var_param_print = new Expr.Var();
        var_param_print.name = "any";
        func_print.params.add(var_param_print);
        expressions.add(func_print);

        char lastC = ' ';
        int i = 0;
        StringBuilder last = new StringBuilder();
        String t = "";
        Expr b = null;
        StringBuilder paramBuff = new StringBuilder();
        int _i = 0;
        int sI = -1;
        char skipTo = ' ';
        for (int __i = 0;__i < buff.length;__i++) {
            char c = buff[__i];
            if (skipTo != ' ') if (buff[__i - 1] != skipTo) continue; else skipTo = ' ';
            if (c == '\n') continue;
            //System.out.println("char: " + c + " | index: " + i + " | last: " + last + " | type: " + t + " | expression: " + b);
            if (t.equals("")) {
                if (last.toString().contains("func")) {
                    t = "f";
                } else if (last.toString().equals("var")) {
                    t = "v";
                }
            } else {
                switch (t) {
                    case "f": {
                        if (b == null) b = new Expr.Func();
                        if (has_next() && next() == ' ' && i == -1) { continue; }
                        if (c == '(') { _i = 1; continue; }
                        if (_i == 0) b.name += c;
                        b.name = StringUtils.remove(b.name, " ");
                        if (_i == 1) {
                            if (sI == -1) sI = i;
                            if (!emptyUntil(sI, ')') && c != ')') {
                                paramBuff.append(c);
                            } else {
                                if (paramBuff.length() > 0) {
                                    for (String s : paramBuff.toString().split(",")) {
                                        String _s = StringUtils.remove(s, " ");
                                        Expr.Var var = new Expr.Var();
                                        var.name = _s;
                                        ((Expr.Func) b).params.add(var);
                                    }
                                }
                                _i = 2;
                            }
                        }
                        if (_i == 2) {
                            ((Expr.Func) b).body.addAll(parse_body());
                            expressions.add(b);
                            b = null;
                            t = "";
                            _i = 0;
                            paramBuff = new StringBuilder();
                            last = new StringBuilder();
                            skipTo = '}';
                        }
                        break;
                    }
                    case "v": {

                    }
                    default: {
                        System.out.println("error: invalid expr type");
                        break;
                    }
                }
            }
            last.append(c);
            lastC = c;
            i++;
            cur = i;
        }

        return new ScriptSource(expressions);
    }

    private static ArrayList<Expr> parse_body() {
        ArrayList body = new ArrayList();
        StringBuilder last = new StringBuilder();
        for (int i = cur;i < buff.length;i++) {
            char c = buff[i];
            if (c == '\n') continue;
            if (containsFuncName(last.toString())) {

            }
            last.append(c);
        }
        return body;
    }

    private static boolean has_next() {
        return buff.length > cur;
    }

    private static boolean containsFuncName(String str) {
        for (Expr expr : expressions) {
            if (expr instanceof Expr.Func && str.contains(expr.name)) return true;
        }
        return false;
    }

    private static char next() {
        return buff[cur + 1];
    }

    private static boolean emptyUntil(int sI, char c) {
        boolean result = true;
        for (int i = sI;i < buff.length;i++) {
            if (buff[i] != ' ') {
                result = false;
                break;
            }
            if (buff[i] == c) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void run(ScriptSource src) {
        for (Expr expr : src.getExpressions()) {
            System.out.println(expr);
            if (expr instanceof Expr.Func) {
                for (Expr expr1 : ((Expr.Func) expr).params) {
                    System.out.println(expr1);
                }
            }
        }
    }

}
