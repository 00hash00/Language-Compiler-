package Compiler;

import java.util.ArrayList;

public class Sementic {
    static class MainTable {
        String name;
        String type;
        String AM;
        String cat;
        String parent;
        ArrayList<BodyTable> link;

        public MainTable(String name, String type, String AM, String cat, String parent, ArrayList<BodyTable> link) {
            this.name = name;
            this.type = type;
            this.AM = AM;
            this.cat = cat;
            this.parent = parent;
            this.link = link;
        }
    }

    static class BodyTable {
        String name;
        String type;
        String AM;
        String TM;
        String SCOPE;

        public BodyTable(String name, String type, String AM, String TM, String SCOPE) {
            this.name = name;
            this.type = type;
            this.AM = AM;
            this.TM = TM;
            this.SCOPE = SCOPE;
        }
    }

    static class FunctionTable {
        String name;
        String type;
        String Scope;

        public FunctionTable(String name, String type, String Scope) {
            this.name = name;
            this.type = type;
            this.Scope = Scope;
        }
    }
}


