package de.enwaffel.script;

import java.util.ArrayList;

public class ScriptSource {

    private final ArrayList<Expr> expressions;

    ScriptSource(ArrayList<Expr> expressions) {
        this.expressions = expressions;
    }

    protected boolean has_main() {
        for (Expr expr : expressions) {
            if (expr instanceof Expr.Func && expr.name.equals("main")) return true;
        }
        return false;
    }

    protected ArrayList<Expr> getExpressions() {
        return expressions;
    }

}
