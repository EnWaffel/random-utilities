package de.enwaffel.script;

import java.util.ArrayList;

public class Expr {

    public String name = "";
    public Expr value;

    public static class BExpr extends Expr {

        public ArrayList<Expr> body = new ArrayList<>();

    }

    public static class Func extends BExpr {

        public ArrayList<Expr> params = new ArrayList<>();

    }

    public static class Var extends Expr {
    }

    @Override
    public String toString() {
        return "Expr(" + getClass().getSimpleName() + "){" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
