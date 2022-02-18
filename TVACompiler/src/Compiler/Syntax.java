package Compiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Syntax {

    ArrayList<String> classpart = Lexical.classPart;
    ArrayList<String> valuepart = Lexical.valuePart;
    ArrayList<String> linenumber = Lexical.lineNumber;
    ArrayList<Sementic.MainTable> MT = new ArrayList<Sementic.MainTable>();
    ArrayList<Sementic.BodyTable> BT;/*= new ArrayList<Sementic.BodyTable>();*/
    ArrayList<Sementic.FunctionTable> FT = new ArrayList<Sementic.FunctionTable>();
    Stack<Integer> scope = new Stack<>();
    int Scope = 0;
    String extra = "";
    int i = 0;
    static boolean SYN = false;

    String Mname = "";
    String Mtype = "";
    String MAM = "Default";
    String Mcat = "General";
    String Mparent = "None";

    String Bname = "";
    String Btype = "";
    String BAM = "Default";
    String BTM = "none";
    String SCOPE = "";
    int index = 0;
    ArrayList<ArrayList<Sementic.BodyTable>> table = new ArrayList<ArrayList<Sementic.BodyTable>>();

    String Fname = "";
    String Ftype = "";
    String FScope = "";

    boolean Glorious_Purpose = true;
    boolean Multiverse = false;
    String TYPE = "";
    boolean Check = false;
    String FUN = "";

    String t1 = "";
    String t2 = "";
    String t3 = "";
    String t4 = "";
    String op = "";
    String ope = "";
    String eType = "";
    String eType1 = "";
    String eType2 = "";

    FileWriter Maintable = new FileWriter("Maintable.txt");
    FileWriter Bodytable = new FileWriter("Bodytable.txt");
    FileWriter Functiontable = new FileWriter("Functiontable.txt");
    FileWriter Errortable = new FileWriter("Errortable.txt");
    FileWriter tableset = new FileWriter("table.txt");


    void insertMain(String Mname, String Mtype, String MAM, String Mcat, String Mparent, ArrayList link) {

        this.MT.add(new Sementic.MainTable(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>()));
    }

    void insertBody(String Bname, String Btype, String BAM, String BTM, String SCOPE) {
        this.MT.get(index).link.add(new Sementic.BodyTable(Bname, Btype, BAM, BTM, SCOPE));
    }

    void insertFunction(String Fname, String Ftype, String FScope) {
        this.FT.add(new Sementic.FunctionTable(Fname, Ftype, FScope));
    }

    public Syntax() throws IOException {
        classpart.add("$");
        System.out.println(classpart);

        insertMain("Name", "Type", "A.M", "Category", "Parent", new ArrayList<Sementic.BodyTable>());
        insertFunction("Name", "Type", "Scope");
        if (STRTNT()) {
            System.out.println("Valid Syntax");
            Errortable.write("Valid Syntax" + System.lineSeparator());
            SYN = true;
            tableset.write( " Valid Syntax");
            System.out.println(MT.get(0).name + " " + MT.get(0).type + " " + MT.get(0).AM + " " + MT.get(0).cat + " " + MT.get(0).parent);
            for (int x = 1; x < MT.size(); x++) {
                System.out.println(MT.get(x).name + " " + MT.get(x).type + " " + MT.get(x).AM + " " + MT.get(x).cat + " " + MT.get(x).parent);
                Maintable.write(MT.get(x).name + " \t " + MT.get(x).type + " \t " + MT.get(x).AM + " \t " + MT.get(x).cat + " \t " + MT.get(x).parent + System.lineSeparator());
                for (int k = 0; k < MT.get(x).link.size(); k++) {
                    System.out.println(MT.get(x).link.get(k).name + " " + MT.get(x).link.get(k).type + " " + MT.get(x).link.get(k).AM + " " + MT.get(x).link.get(k).TM + " " + MT.get(x).link.get(k).SCOPE);
                    Bodytable.write(MT.get(x).link.get(k).name + " \t " + MT.get(x).link.get(k).type + " \t " + MT.get(x).link.get(k).AM + " \t " + MT.get(x).link.get(k).TM + " \t " + MT.get(x).link.get(k).SCOPE + System.lineSeparator());
                }
            }
            for (int f = 0; f < FT.size(); f++) {
                System.out.println(FT.get(f).name + " " + FT.get(f).type + " " + FT.get(f).Scope);
                Functiontable.write(FT.get(f).name + " \t " + FT.get(f).type + " \t " + FT.get(f).Scope + System.lineSeparator());
            }
            //for (int f=0;f<)
            Maintable.close();
            Bodytable.close();
            Functiontable.close();
            Errortable.close();
        } else {
            System.out.println("Invalid Syntax");
            Errortable.write("Invalid Syntax" + System.lineSeparator());
            System.out.println("Error at Line # " + linenumber.get(i));
            Errortable.write("Error at Line # " + linenumber.get(i) + System.lineSeparator());
            System.out.println(classpart.get(i));
            Errortable.write(classpart.get(i) + System.lineSeparator());
            System.out.println(valuepart.get(i));
            Errortable.write(valuepart.get(i) + System.lineSeparator());
            tableset.write( " Invalid Syntax");
            tableset.write( "\nError at Line # " + linenumber.get(i));
            tableset.write( "\nClass-Part: "+classpart.get(i));
            tableset.write( "\nValue-Part: "+valuepart.get(i));
            Maintable.close();
            Bodytable.close();
            Functiontable.close();
            Errortable.close();
        }
        tableset.close();
    }

    public boolean DEC() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType")) {
            if (ACC_MOD()) {
                if (String.valueOf(classpart.get(i)).equals("DataType")) {
                    Btype = "";
                    Btype = valuepart.get(i);
                    t1 = valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        Bname = valuepart.get(i);
                        SCOPE = String.valueOf(scope.peek());
                        if (lookupBTredeclaration(Bname, scope.peek())) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else if (Multiverse) {
                            if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            } else {
                                insertFunction(Bname, Btype, SCOPE);
                                Bname = "";
                                Btype = "";
                                BAM = "Default";
                                BTM = "none";
                                SCOPE = "";
                            }
                        } else {
                            insertBody(Bname, Btype, BAM, BTM, SCOPE);
                            Bname = "";
                            Btype = "";
                            BAM = "Default";
                            BTM = "none";
                            SCOPE = "";
                        }
                        i++;
                        if (INIT()) {
                            if (LIST()) {
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ACC_MOD() {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier")) {
            MAM = "";
            MAM += valuepart.get(i);
            BAM = "";
            BAM += valuepart.get(i);
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("class") || classpart.get(i).equals("Array") || classpart.get(i).equals("Arraylist") || classpart.get(i).equals("abstract") || classpart.get(i).equals("interface")) {
            MAM = "";
            MAM += "Default";
            BAM = "";
            BAM += "Default";
            return true;
        }
        return false;
    }

    public boolean LIST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(";")) {
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals(",")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (INIT()) {
                    if (LIST()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean INIT() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("=")) {
            op = valuepart.get(i);
            i++;
            /*if (classpart.get(i).equals("Identifier")) {
                if (lookupBTundeclaration(valuepart.get(i))) {
                    if (!(Objects.equals(lookupBTtype(valuepart.get(i)), TYPE))) {
                        System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                        TYPE = "";
                    } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), TYPE))) {
                        System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                        TYPE = "";
                    }
                    if (OE()) {
                        System.out.println("here");
                        System.out.println(t1);
                        System.out.println(t2);
                        System.out.println(op);
                        if (Comp(t1,t2,op)==""){
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                        }
                        //i++;
                        return true;
                    }
                } else {
                    //System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    if (Objects.equals(classpart.get(i), "Integer Constant")) {
                        if (!Objects.equals(TYPE, "int")) {
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                            TYPE = "";
                        }
                    } else if (Objects.equals(classpart.get(i), "Float/Double Constant")) {
                        if (!Objects.equals(TYPE, "float")) {
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                            TYPE = "";
                        }
                    } else if (Objects.equals(classpart.get(i), "Character Constant")) {
                        if (!Objects.equals(TYPE, "char")) {
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                            TYPE = "";
                        }
                    } else if (Objects.equals(classpart.get(i), "String Constant")) {
                        if (!Objects.equals(TYPE, "String")) {
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                            TYPE = "";
                        }
                    } else if (Objects.equals(classpart.get(i), "Boolean Constant")) {
                        if (!Objects.equals(TYPE, "bool")) {
                            System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                            TYPE = "";
                        }
                    }
                    if (OE()) {
                        //i++;
                        return true;
                    }
                }
            }*/ /*else {

            }*/
            if (OE()) {
                if (Comp(t1,t2,op)==""){
                    /*System.out.println("here");
                    System.out.println(t1);
                    System.out.println(t2);
                    System.out.println(op);*/
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    /*t1 = "";
                    t1 = "";
                    t1 = "";*/
                }
                //i++;
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean INIT1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("=")) {
            i++;
            if (INIT2()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean INIT2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("{")) {
            scope.push(++Scope);
            System.out.println(scope.peek());
            i++;
            if (OE()) {
                if (OE2()) {
                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } /*else if (String.valueOf(classpart.get(i)).equals("new")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("DataType")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("[")) {
                    i++;
                    if (OE()) {
                        if (String.valueOf(classpart.get(i)).equals("]")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        }*/ else {
            return false;
        }
    }

    public boolean WHILE_ST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("while")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (OE()) {
                    if (String.valueOf(classpart.get(i)).equals(")")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (BODY5()) {
                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean BODY() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || String.valueOf(classpart.get(i)).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("defcall") || classpart.get(i).equals("print") || classpart.get(i).equals("Arraylist") || classpart.get(i).equals("DataType")) {
            if (SST()) {
                if (BODY()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("Break-Continue") || String.valueOf(classpart.get(i)).equals("return")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean BODY5() throws IOException {
        if (classpart.get(i).equals("def")) {
            return false;
        } else if (BODY()) {
            return true;
        }
        return false;
    }

    public boolean SST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def")) {
            if (ACC_MOD()) {
                if (ACC_MOD_SST()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Do")) {
            if (DO_WHILE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("cnstr")) {
            if (CONSTR()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("try")) {
            if (TST_CTH()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("while")) {
            if (WHILE_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("for")) {
            if (FOR_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("switch")) {
            if (SWT_CASE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Increment Operator") || String.valueOf(classpart.get(i)).equals("Decrement Operator")) {
            op = valuepart.get(i);

            eType = Comp1(t1, op);
            if (eType.equals("")) {
                System.out.println("Type Mis-matched at Line No: " + linenumber.get(i));
                Errortable.write("Type Mis-matched Error at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(Objects.equals(lookupBTtype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                }
                if (lookupBTundeclaration(valuepart.get(i))) {
                    i++;
                    if (X()) {
                        return true;
                    }
                } else {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall")) {
            if (FUNC_CALL()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (lookupMTname(valuepart.get(i))){
                if (lookupMTparent(valuepart.get(i)).equals("None")){
                    Check = true;
                }
            }
            if (!(lookupMTname(valuepart.get(i))) && valuepart.get(i+3).equals("new")){
                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            else if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }

            i++;
            if (ID_SST()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("print")) {
            if (PRT()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("Arraylist")) {
            if (ARRLST()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("DataType")) {
            if (ARR_DEC()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean PRT() {
        if (classpart.get(i).equals("print")) {
            i++;
            if (classpart.get(i).equals("(")) {
                i++;
                if (CONST()) {
                    if (classpart.get(i).equals(")")) {
                        i++;
                        if (classpart.get(i).equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ID_SST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            if (String.valueOf(classpart.get(i)).equals("=")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("new")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        if (Check){
                            if (!Objects.equals(valuepart.get(i), valuepart.get(i - 4))){
                                System.out.println("Invalid Reference for object at line # " + linenumber.get(i));
                                Errortable.write("Invalid Reference for object at Line # " + linenumber.get(i) + System.lineSeparator());
                            }
                        }
                        else if (!(lookupMTname(valuepart.get(i)))) {
                            System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                        }
                        else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                            System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                        }
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("(")) {
                            i++;
                            if (PL()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals(";")) {
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        if (String.valueOf(classpart.get(i)).equals(".") || String.valueOf(classpart.get(i)).equals("[") || String.valueOf(classpart.get(i)).equals("=") || String.valueOf(classpart.get(i)).equals("Assignment Operator") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            if (X()) {
                if (X_SST()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean X_SST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("=") || String.valueOf(classpart.get(i)).equals("Assignment Operator")) {
            if (ASSIGN_OP()) {
                if (OE()) {
                    if (String.valueOf(classpart.get(i)).equals(";")) {
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals(";")) {
                i++;
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("this")) {
            return true;
        }
        return false;
    }

    public boolean ACC_MOD_SST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (SST1()) {
                return true;
            }
        } else if (classpart.get(i).equals("static")) {
            i++;
            if (classpart.get(i).equals("DataType")) {
                Btype = "";
                Btype = valuepart.get(i);
                t1 = valuepart.get(i);
                i++;
                if (SST1()) {
                    return true;
                }
            } else if (classpart.get(i).equals("void")) {
                Btype = "";
                Btype = valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("def")) {
                    Multiverse = true;
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        Bname = valuepart.get(i);
                        SCOPE = String.valueOf(scope.peek());
                        if (lookupBTredeclaration(Bname, scope.peek())) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else {
                            insertBody(Bname, Btype, BAM, BTM, SCOPE);
                            Bname = "";
                            Btype = "";
                            BAM = "Default";
                            BTM = "none";
                            SCOPE = "";
                        }
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("(")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (P_DT()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals("{")) {
                                        i++;
                                        if (BODY()) {
                                            if (String.valueOf(classpart.get(i)).equals("}")) {
                                                Multiverse = false;
                                                scope.pop();
                                                --Scope;
                                                i++;
                                                return true;
                                            }
                                        }
                                    } else if (classpart.get(i).equals(";")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }/*else if (String.valueOf(classpart.get(i)).equals("def")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                i++;
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }*/ else if (classpart.get(i).equals("void")) {
            Btype = "";
            Btype = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                Multiverse = true;
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    if (lookupBTredeclaration(Bname, scope.peek())) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertBody(Bname, Btype, BAM, BTM, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (String.valueOf(classpart.get(i)).equals("{")) {
                                    i++;
                                    if (BODY()) {
                                        if (String.valueOf(classpart.get(i)).equals("}")) {
                                            Multiverse = false;
                                            scope.pop();
                                            --Scope;
                                            i++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ACC_MOD_SST1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (SST11()) {
                return true;
            }
        } /*else if (String.valueOf(classpart.get(i)).equals("def")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                i++;
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }*/ else if (classpart.get(i).equals("void")) {
            Btype = "";
            Btype = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                Multiverse = true;
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    if (lookupBTredeclaration(Bname, scope.peek())) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertBody(Bname, Btype, BAM, BTM, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (classpart.get(i).equals(";")) {
                                    Multiverse = false;
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean SST1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    TYPE = Btype;
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                TYPE = Btype;
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            if (INIT()) {
                if (LIST()) {
                    return true;
                }
            }
            return false;
        }
        if (String.valueOf(classpart.get(i)).equals("def")) {
            Multiverse = true;
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                String DUP1 = "";
                DUP1 = Bname;
                String DUP2 = "";
                DUP2 = Btype;
                String DUP3 = "";
                DUP3 = BAM;
                String DUP4 = "";
                DUP4 = BTM;
                String DUP5 = "";
                DUP5 = SCOPE;
                /*if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                i++;
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("return")) {
                                        i++;
                                        if (OE()) {
                                            extra += " --> " + DUP2;
                                            if (classpart.get(i - 1).equals("Integer Constant")) {
                                                DUP2 += "--> int";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Float/Double Constant")) {
                                                DUP2 += "--> float";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Character Constant")) {
                                                DUP2 += "--> char";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("String Constant")) {
                                                DUP2 += "--> string";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Boolean Constant")) {
                                                DUP2 += "--> bool";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            }
                                            Bname = "";
                                            Btype = "";
                                            BAM = "Default";
                                            BTM = "none";
                                            SCOPE = "";
                                            extra = "";
                                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                                i++;
                                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                                    Multiverse = false;
                                                    scope.pop();
                                                    --Scope;
                                                    i++;
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (classpart.get(i).equals(";")) {
                                scope.pop();
                                --Scope;
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean SST11() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    TYPE = Btype;
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                TYPE = Btype;
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            if (INIT()) {
                if (LIST()) {
                    return true;
                }
            }
            return false;
        }
        if (String.valueOf(classpart.get(i)).equals("def") && classpart.get(i - 2).equals("abstract")) {
            Multiverse = true;
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (classpart.get(i).equals(";")) {
                                Multiverse = false;
                                scope.pop();
                                --Scope;
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean SST2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (Multiverse) {
                    if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertFunction(Bname, Btype, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("[")) {
                    i++;

                    if (String.valueOf(classpart.get(i)).equals("]")) {
                        i++;

                        if (INIT1()) {

                            if (LIST()) {

                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("[")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("]")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("def")) {
                    Multiverse = true;
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        Bname = valuepart.get(i);
                        SCOPE = String.valueOf(scope.peek());
                        String DUP1 = "";
                        DUP1 = Bname;
                        String DUP2 = "";
                        DUP2 = Btype;
                        String DUP3 = "";
                        DUP3 = BAM;
                        String DUP4 = "";
                        DUP4 = BTM;
                        String DUP5 = "";
                        DUP5 = SCOPE;
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("(")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (P_DT()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals("{")) {
                                        i++;
                                        if (BODY()) {
                                            if (String.valueOf(classpart.get(i)).equals("return")) {
                                                i++;
                                                if (OE()) {
                                                    extra += " --> " + DUP2;
                                                    if (classpart.get(i - 1).equals("Integer Constant")) {
                                                        DUP2 += "--> int";
                                                        insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                    } else if (classpart.get(i - 1).equals("Float/Double Constant")) {
                                                        DUP2 += "--> float";
                                                        insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                    } else if (classpart.get(i - 1).equals("Character Constant")) {
                                                        DUP2 += "--> char";
                                                        insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                    } else if (classpart.get(i - 1).equals("String Constant")) {
                                                        DUP2 += "--> string";
                                                        insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                    } else if (classpart.get(i - 1).equals("Boolean Constant")) {
                                                        DUP2 += "--> bool";
                                                        insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                    }
                                                    Bname = "";
                                                    Btype = "";
                                                    BAM = "Default";
                                                    BTM = "none";
                                                    SCOPE = "";
                                                    extra = "";
                                                    if (String.valueOf(classpart.get(i)).equals(";")) {
                                                        i++;
                                                        if (String.valueOf(classpart.get(i)).equals("}")) {
                                                            Multiverse = false;
                                                            scope.pop();
                                                            --Scope;
                                                            i++;
                                                            return true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean SST3() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("def")) {
            Multiverse = true;
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                String DUP1 = "";
                DUP1 = Bname;
                String DUP2 = "";
                DUP2 = Btype;
                String DUP3 = "";
                DUP3 = BAM;
                String DUP4 = "";
                DUP4 = BTM;
                String DUP5 = "";
                DUP5 = SCOPE;
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                i++;
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("return")) {
                                        i++;
                                        if (OE()) {
                                            extra += " --> " + DUP2;
                                            if (classpart.get(i - 1).equals("Integer Constant")) {
                                                DUP2 += "--> int";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Float/Double Constant")) {
                                                DUP2 += "--> float";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Character Constant")) {
                                                DUP2 += "--> char";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("String Constant")) {
                                                DUP2 += "--> string";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            } else if (classpart.get(i - 1).equals("Boolean Constant")) {
                                                DUP2 += "--> bool";
                                                insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                            }
                                            Bname = "";
                                            Btype = "";
                                            BAM = "Default";
                                            BTM = "none";
                                            SCOPE = "";
                                            extra = "";
                                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                                i++;
                                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                                    Multiverse = false;
                                                    scope.pop();
                                                    --Scope;
                                                    i++;
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Relational Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("DataType")) {
                Btype = "";
                Btype = valuepart.get(i);
                t1 = valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("Relational Operator")) {
                    op = valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        Bname = valuepart.get(i);
                        SCOPE = String.valueOf(scope.peek());
                        if (lookupBTredeclaration(Bname, scope.peek())) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else if (Multiverse) {
                            if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            } else {
                                insertFunction(Bname, Btype, SCOPE);
                                Bname = "";
                                Btype = "";
                                BAM = "Default";
                                BTM = "none";
                                SCOPE = "";
                            }
                        } else {
                            insertBody(Bname, Btype, BAM, BTM, SCOPE);
                            Bname = "";
                            Btype = "";
                            BAM = "Default";
                            BTM = "none";
                            SCOPE = "";
                        }
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("=")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("new")) {
                                i++;
                                if (String.valueOf(classpart.get(i)).equals("Relational Operator")) {
                                    op = valuepart.get(i);
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals("DataType")) {
                                        Btype = "";
                                        Btype = valuepart.get(i);
                                        t1 = valuepart.get(i);
                                        i++;
                                        if (String.valueOf(classpart.get(i)).equals("Relational Operator")) {
                                            op = valuepart.get(i);
                                            i++;
                                            if (String.valueOf(classpart.get(i)).equals("(")) {
                                                i++;
                                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                                    i++;
                                                    if (String.valueOf(classpart.get(i)).equals(";")) {
                                                        i++;
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean ARR_DEC() throws IOException {
        if (classpart.get(i).equals("DataType")) {
            if (ARR_ST()) {
                if (ARR_TYP()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ARR_ST() {
        if (classpart.get(i).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (classpart.get(i).equals("[")) {
                i++;
                if (classpart.get(i).equals("]")) {
                    i++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ARR_TYP() throws IOException {
        if (classpart.get(i).equals("Identifier")) {
            if (ARR_1D()) {
                return true;
            }
        }
        if (classpart.get(i).equals("[")) {
            if (ARR_2D()) {
                return true;
            }
        }
        return false;
    }

    public boolean ARR_1D() throws IOException {
        if (classpart.get(i).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            if (ARR_1D_()) {
                return true;
            }
        }
        return false;
    }

    public boolean ARR_1D_() {
        if (classpart.get(i).equals("=")) {
            i++;
            if (classpart.get(i).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (ARR_VAL()) {
                    if (classpart.get(i).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        if (classpart.get(i).equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        } else if (classpart.get(i).equals(";")) {
            i++;
            return true;
        }
        return false;
    }

    public boolean ARR_2D() throws IOException {
        if (classpart.get(i).equals("[")) {
            i++;
            if (classpart.get(i).equals("]")) {
                i++;
                if (classpart.get(i).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    if (lookupBTredeclaration(Bname, scope.peek())) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else if (Multiverse) {
                        if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else {
                            insertFunction(Bname, Btype, SCOPE);
                            Bname = "";
                            Btype = "";
                            BAM = "Default";
                            BTM = "none";
                            SCOPE = "";
                        }
                    } else {
                        insertBody(Bname, Btype, BAM, BTM, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                    i++;
                    if (ARR_2D_()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean ARR_2D_() {
        if (classpart.get(i).equals("=")) {
            i++;
            if (classpart.get(i).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (ARR_VAL1()) {
                    if (classpart.get(i).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        if (classpart.get(i).equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        } else if (classpart.get(i).equals(";")) {
            i++;
            return true;
        }
        return false;
    }

    public boolean ARR_VAL() {
        if (classpart.get(i).equals("Integer Constant") || classpart.get(i).equals("Float/Double Constant") || classpart.get(i).equals("Character Constant") || classpart.get(i).equals("String Constant") || classpart.get(i).equals("Boolean Constant")) {
            if (CONST()) {
                if (ARR_VAL2()) {
                    return true;
                }
            }
        } /*else if (classpart.get(i).equals("{")) {
            if (ARR_VAL1()) {
                return true;
            }
        }*/
        return false;
    }

    public boolean ARR_VAL1() {
        if (classpart.get(i).equals("{")) {
            scope.push(++Scope);
            System.out.println(scope.peek());
            i++;
            if (ARR_VAL()) {
                if (classpart.get(i).equals("}")) {
                    scope.pop();
                    --Scope;
                    i++;
                    if (classpart.get(i).equals(",")) {
                        i++;
                        if (classpart.get(i).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (ARR_VAL()) {
                                if (classpart.get(i).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (classpart.get(i).equals("}")) {
            return true;
        }
        return false;
    }

    public boolean ARR_VAL2() {
        if (classpart.get(i).equals(",")) {
            i++;
            if (ARR_VAL()) {
                return true;
            }
        } else if (classpart.get(i).equals("}")) {
            return true;
        }
        return false;
    }

    public boolean ARRLST() throws IOException {
        if (classpart.get(i).equals("Arraylist")) {
            if (ARRLST_ST()) {
                if (classpart.get(i).equals("=")) {
                    i++;
                    if (classpart.get(i).equals("new")) {
                        i++;
                        if (ARRLST_ST1()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ARRLST_ST1() {
        if (classpart.get(i).equals("Arraylist")) {
            i++;
            if (valuepart.get(i).equals("<")) {
                i++;
                if (valuepart.get(i).equals(">")) {
                    i++;
                    if (classpart.get(i).equals("(")) {
                        i++;
                        if (classpart.get(i).equals(")")) {
                            i++;
                            if (classpart.get(i).equals(";")) {
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean ARRLST_ST() throws IOException {
        if (classpart.get(i).equals("Arraylist")) {
            i++;
            if (ARRLST_ST2()) {
                return true;
            }
        }
        return false;
    }

    public boolean ARRLST_ST2() throws IOException {
        if (valuepart.get(i).equals("<")) {
            i++;
            if (NP()) {
                if (valuepart.get(i).equals(">")) {
                    i++;
                    if (classpart.get(i).equals("Identifier")) {
                        Bname = valuepart.get(i);
                        SCOPE = String.valueOf(scope.peek());
                        if (lookupBTredeclaration(Bname, scope.peek())) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else if (Multiverse) {
                            if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            } else {
                                insertFunction(Bname, Btype, SCOPE);
                                Bname = "";
                                Btype = "";
                                BAM = "Default";
                                BTM = "none";
                                SCOPE = "";
                            }
                        } else {
                            insertBody(Bname, Btype, BAM, BTM, SCOPE);
                            Bname = "";
                            Btype = "";
                            BAM = "Default";
                            BTM = "none";
                            SCOPE = "";
                        }
                        i++;
                        return true;
                    }
                }
            }
        } else if (classpart.get(i).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            return true;
        }
        return false;
    }

    public boolean NP() throws IOException {
        if (classpart.get(i).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            return true;
        } else if (classpart.get(i).equals("Object")) {
            i++;
            return true;
        } else if (classpart.get(i).equals("Arraylist")) {
            if (ARRLST_ST()) {
                return true;
            }
        } else if (valuepart.get(i).equals(">")) {
            return true;
        }
        return false;
    }

    public boolean IF_ELSE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("if")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (OE()) {
                    if (String.valueOf(classpart.get(i)).equals(")")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (BODY()) {
                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    if (ELIF()) {
                                        if (ELSE()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean ELIF() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("elif")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (OE()) {
                    if (String.valueOf(classpart.get(i)).equals(")")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (BODY()) {
                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    if (ELIF()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("else") || String.valueOf(classpart.get(i)).equals("$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ELSE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("else")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (BODY()) {
                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DO_WHILE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Do")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (BODY5()) {
                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("while")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("(")) {
                                i++;
                                if (OE()) {
                                    if (String.valueOf(classpart.get(i)).equals(")")) {
                                        i++;
                                        if (String.valueOf(classpart.get(i)).equals(";")) {
                                            i++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean CONSTR() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("cnstr")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                scope.push(++Scope);
                                System.out.println(scope.peek());
                                i++;
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean TST_CTH() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("try")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (BODY()) {

                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        if (CTH_ST()) {

                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean CTH_ST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("catch")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("Exception")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals(")")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (BODY()) {
                                if (String.valueOf(classpart.get(i)).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    if (FINALLY()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    // }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean FINALLY() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("finally")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("{")) {
                scope.push(++Scope);
                System.out.println(scope.peek());
                i++;
                if (BODY()) {
                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        scope.pop();
                        --Scope;
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean FOR_ST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("for")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (C1()) {
                    if (C2()) {
                        if (String.valueOf(classpart.get(i)).equals(";")) {
                            i++;
                            if (C3()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals("{")) {
                                        scope.push(++Scope);
                                        System.out.println(scope.peek());
                                        i++;
                                        if (BODY5()) {
                                            if (String.valueOf(classpart.get(i)).equals("}")) {
                                                scope.pop();
                                                --Scope;
                                                i++;
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean C1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType")) {
            if (DEC()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (ASSIGN_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            i++;
            return true;
        } else {
            return false;
        }
    }

    public boolean C2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (OE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean C3() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (X()) {
                if (C3_()) {
                    return true;
                }
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(Objects.equals(lookupBTtype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                }
                i++;
                if (X()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean C3_() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("=") || String.valueOf(classpart.get(i)).equals("Assignment Operator")) {
            if (ASSIGN_OP()) {
                if (OE()) {
                    return true;
                }
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            return true;
        } else {
            return false;
        }
    }

    public boolean ASSIGN_ST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            TYPE = "";
            if (Multiverse) {
                TYPE = lookupfunctiontabletype(valuepart.get(i));
            } else {
                TYPE = lookupBTtype(valuepart.get(i));
            }
            /*else if (!(Objects.equals(lookupbodytabletype(valuepart.get(i)), TYPE))){
                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                TYPE = "";
            }
            else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), TYPE))){
                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                TYPE = "";
            }*/

            i++;
            if (X()) {
                if (ASSIGN_OP()) {
                    if (classpart.get(i).equals("Identifier")) {
                        if (lookupBTundeclaration(valuepart.get(i))) {
                            if (!(Objects.equals(lookupBTtype(valuepart.get(i)), TYPE))) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), TYPE))) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        }
                    } else {
                        if (Objects.equals(classpart.get(i), "Integer Constant")) {
                            if (!Objects.equals(TYPE, "int")) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        } else if (Objects.equals(classpart.get(i), "Float/Double Constant")) {
                            if (!Objects.equals(TYPE, "float")) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        } else if (Objects.equals(classpart.get(i), "Character Constant")) {
                            if (!Objects.equals(TYPE, "char")) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        } else if (Objects.equals(classpart.get(i), "String Constant")) {
                            if (!Objects.equals(TYPE, "String")) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        } else if (Objects.equals(classpart.get(i), "Boolean Constant")) {
                            if (!Objects.equals(TYPE, "bool")) {
                                System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                                Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                TYPE = "";
                            }
                        }
                    }
                    if (OE()) {
                        if (String.valueOf(classpart.get(i)).equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean ASSIGN_OP() {
        if (String.valueOf(classpart.get(i)).equals("=")) {
            op = valuepart.get(i);
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("Assignment Operator")) {
            op = valuepart.get(i);
            i++;
            return true;
        } else {
            return false;
        }
    }

    public boolean INC_DEC() throws IOException {
        if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (Multiverse) {
                    if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertFunction(Bname, Btype, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (X()) {
                    if (String.valueOf(classpart.get(i)).equals(";")) {
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupBTredeclaration(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (Multiverse) {
                if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }
            i++;
            if (X()) {
                if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
                    op = valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals(";")) {
                        i++;
                        return true;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean X() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (OBJ_DOT2()) {
                    return true;
                } else if (X2()) {
                    if (X()) {
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("[")) {
            i++;
            if (OE()) {
                if (String.valueOf(classpart.get(i)).equals("]")) {
                    i++;
                    if (X()) {
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("$") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("=") || String.valueOf(classpart.get(i)).equals("Assignment Operator") || String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("Logical Operator") || String.valueOf(classpart.get(i)).equals("Relational Operator") || String.valueOf(classpart.get(i)).equals("Plus-Minus Operator") || String.valueOf(classpart.get(i)).equals("Multiply-Divide-Modulus Operator")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean X2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("(")) {
            i++;
            if (PL()) {
                if (String.valueOf(classpart.get(i)).equals(")")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals(".")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                            }
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals(".") || String.valueOf(classpart.get(i)).equals("[") || String.valueOf(classpart.get(i)).equals("$") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("=") || String.valueOf(classpart.get(i)).equals("Assignment Operator") || String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("Logical Operator") || String.valueOf(classpart.get(i)).equals("Relational Operator") || String.valueOf(classpart.get(i)).equals("Plus-Minus Operator") || String.valueOf(classpart.get(i)).equals("Multiply-Divide-Modulus Operator")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean FUNC_DEC() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def")) {
            if (ACC_MOD()) {
                if (FUNC_DEC1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean FUNC_DEC1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("def")) {
            Multiverse = true;
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                if (BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        Multiverse = false;
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                Multiverse = true;
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    String DUP1 = "";
                    DUP1 = Bname;
                    String DUP2 = "";
                    DUP2 = Btype;
                    String DUP3 = "";
                    DUP3 = BAM;
                    String DUP4 = "";
                    DUP4 = BTM;
                    String DUP5 = "";
                    DUP5 = SCOPE;
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (String.valueOf(classpart.get(i)).equals("{")) {
                                    if (BODY()) {
                                        if (String.valueOf(classpart.get(i)).equals("return")) {
                                            i++;
                                            if (OE()) {
                                                extra += " --> " + DUP2;
                                                if (classpart.get(i - 1).equals("Integer Constant")) {
                                                    DUP2 += "--> int";
                                                    insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                } else if (classpart.get(i - 1).equals("Float/Double Constant")) {
                                                    DUP2 += "--> float";
                                                    insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                } else if (classpart.get(i - 1).equals("Character Constant")) {
                                                    DUP2 += "--> char";
                                                    insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                } else if (classpart.get(i - 1).equals("String Constant")) {
                                                    DUP2 += "--> string";
                                                    insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                } else if (classpart.get(i - 1).equals("Boolean Constant")) {
                                                    DUP2 += "--> bool";
                                                    insertBody(DUP1, extra, DUP3, DUP4, DUP5);
                                                }
                                                Bname = "";
                                                Btype = "";
                                                BAM = "Default";
                                                BTM = "none";
                                                SCOPE = "";
                                                extra = "";
                                                if (String.valueOf(classpart.get(i)).equals(";")) {
                                                    i++;
                                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                                        Multiverse = false;
                                                        scope.pop();
                                                        --Scope;
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } /*else if (String.valueOf(classpart.get(i)).equals("arr")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("[")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("]")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("def")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("(")) {
                                i++;
                                if (P_DT()) {
                                    if (String.valueOf(classpart.get(i)).equals(")")) {
                                        i++;
                                        if (String.valueOf(classpart.get(i)).equals("{")) {
                                            if (BODY()) {
                                                if (String.valueOf(classpart.get(i)).equals("return")) {
                                                    i++;
                                                    if (OE()) {
                                                        System.out.println(valuepart.get(i-1).getClass().getName());
                                                        if (String.valueOf(classpart.get(i)).equals(";")) {
                                                            i++;
                                                            if (String.valueOf(classpart.get(i)).equals("}")) {
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }*/ /*else if (String.valueOf(classpart.get(i)).equals("arrlist")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (String.valueOf(classpart.get(i)).equals("{")) {
                                    if (BODY()) {
                                        if (String.valueOf(classpart.get(i)).equals("return")) {
                                            i++;
                                            if (OE()) {
                                                System.out.println(valuepart.get(i-1).getClass().getName());
                                                if (String.valueOf(classpart.get(i)).equals(";")) {
                                                    i++;
                                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }*/ else {
            return false;
        }
    }

    public boolean FUNC_DEC2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("def")) {
            Multiverse = true;
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (P_DT()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                Multiverse = false;
                                scope.pop();
                                --Scope;
                                i++;
                                if (FUNC_DEC2()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean P_DT() {
        if (String.valueOf(classpart.get(i)).equals("DataType")) {
            Btype = "";
            Btype = valuepart.get(i);
            extra = valuepart.get(i);
            t1 = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    //System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                } else if (Multiverse) {
                    insertFunction(Bname, Btype, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (P_DT2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean P_DT2() {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("DataType")) {
                Btype = "";
                Btype = valuepart.get(i);
                extra += "," + valuepart.get(i);
                t1 = valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    if (lookupBTredeclaration(Bname, scope.peek())) {
                        //System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    } else if (Multiverse) {
                        insertFunction(Bname, Btype, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    } else {
                        insertBody(Bname, Btype, BAM, BTM, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                    i++;
                    if (P_DT2()) {
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean FUNC_CALL() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                /*String yo = valuepart.get(i);
                String yoyo =
                if (lookupBTfunctype(yo).equals("")) {
                }*/
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared function at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared function at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared function at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared function at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (Multiverse) {
                    TYPE = "";
                    TYPE = lookupfunctiontabletype(valuepart.get(i));
                }
                else {
                    TYPE = "";
                    TYPE = lookupBTfunctype(valuepart.get(i));
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("(")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier") && (classpart.get(i + 1).equals(".") || classpart.get(i + 1).equals("[") || classpart.get(i + 1).equals("("))) {
                        if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                            System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else if (!(lookupBTundeclaration(valuepart.get(i)))) {
                            System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
                        }
                        i++;
                        if (OR_A()) {
                            if (Y()) {
                                System.out.println(FUN);
                                FUN += " --> " + TYPE;
                                System.out.println("here1");
                                System.out.println(FUN);
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (String.valueOf(classpart.get(i)).equals(";")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    } else if (F_VAL1()) {
                        if (String.valueOf(classpart.get(i)).equals(")")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                scope.pop();
                                --Scope;
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean F_VAL1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i))) && (classpart.get(i)).equals("Identifier")) {
                System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse && (classpart.get(i)).equals("Identifier")) {
                System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (F_VAL2()) {
                return true;
            }
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        }
        return false;
    }

    public boolean F_VAL2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i))) && (classpart.get(i)).equals("Identifier")) {
                    System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse && (classpart.get(i)).equals("Identifier")) {
                    System.out.println("Undeclared argument at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared argument at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (F_VAL2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OR_A() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("[")) {
            i++;
            if (OE()) {
                if (String.valueOf(classpart.get(i)).equals("]")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals(".")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                            }
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(".") || String.valueOf(classpart.get(i)).equals("(")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Y() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (OR_A()) {
                    if (Y3()) {
                        if (String.valueOf(classpart.get(i)).equals("(")) {
                            i++;
                            if (PL()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (Y2()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("(")) {
            i++;
            if (PL()) {
                System.out.println("imhere");
                System.out.println(FUN);
                if (String.valueOf(classpart.get(i)).equals(")")) {
                    i++;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean Y2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (OR_A()) {
                    if (Y3()) {
                        if (String.valueOf(classpart.get(i)).equals("(")) {
                            i++;
                            if (PL()) {
                                if (String.valueOf(classpart.get(i)).equals(")")) {
                                    i++;
                                    if (Y4()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Y3() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (OR_A()) {
                    if (Y3()) {
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("(")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Y4() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (Y5()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Y5() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".") || String.valueOf(classpart.get(i)).equals(";")) {
            if (Y2()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("(")) {
            i++;
            if (PL()) {
                if (String.valueOf(classpart.get(i)).equals(")")) {
                    i++;
                    if (Y2()) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean PL() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (OE()) {
                FUN += t2;
                if (PL_()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        }
        return false;
    }

    public boolean PL_() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            FUN += ",";
            i++;
            if (OE()) {
                FUN += t2;
                if (PL_()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean PSVM() throws IOException {
        if (classpart.get(i).equals("PSVM")) {
            i++;
            if (classpart.get(i).equals("(")) {
                i++;
                if (valuepart.get(i).equals("String")) {
                    i++;
                    if (classpart.get(i).equals("[")) {
                        i++;
                        if (classpart.get(i).equals("]")) {
                            i++;
                            if (classpart.get(i).equals("args")) {
                                i++;
                                if (classpart.get(i).equals(")")) {
                                    i++;
                                    if (classpart.get(i).equals("{")) {
                                        scope.push(++Scope);
                                        System.out.println(scope.peek());
                                        i++;
                                        if (M_BODY()) {
                                            if (classpart.get(i).equals("}")) {
                                                scope.pop();
                                                --Scope;
                                                i++;
                                                if (CLASS_EXP() || M_BODY()) {
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean STRTNT() throws IOException {
        if (PKG()) {
            if (IMPT_BODY()) {
                if (CLASS_EXP()) {
                    return true;
                }
            } else if (CLASS_EXP()) {
                return true;
            }
        } else if (IMPT_BODY()) {
            if (CLASS_EXP()) {
                return true;
            }
        } else if (CLASS_EXP()) {
            return true;
        }
        return false;
    }

    public boolean PKG() {
        if (classpart.get(i).equals("pack")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                /*Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (classpart.get(i).equals(";")) {
                    i++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean OBJ_CALL() throws IOException {
        if (classpart.get(i).equals(".")) {
            i++;
            if (OBJ_DOT1()) {
                return true;
            } else if (classpart.get(i).equals("defcall")) {
                if (FUNC_CALL()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean OBJ_DOT1() throws IOException {
        if (classpart.get(i).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (OBJ_DOT2()) {
                return true;
            }
        }
        return false;
    }

    public boolean OBJ_DOT2() throws IOException {
        if (classpart.get(i).equals(".")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (OBJ_DOT2()) {
                    return true;
                }
            }
        } else if (classpart.get(i).equals("(")) {
            i++;
            if (classpart.get(i).equals(")")) {
                i++;
                if (classpart.get(i).equals(";")) {
                    i++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean IMPT_BODY() {
        if (classpart.get(i).equals("import")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                if (J_DOT1()) {
                    if (classpart.get(i).equals(";")) {
                        i++;
                        return true;
                    }
                } else {
                    if (FRM()) {
                        if (classpart.get(i).equals(";")) {
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean FRM() {
        if (classpart.get(i).equals("Identifier")) {
            i++;
            if (classpart.get(i).equals("from")) {
                i++;
                if (classpart.get(i).equals("Identifier")) {
                    i++;
                    return true;
                }
            }
        } else if (classpart.get(i).equals(";")) {
            return true;
        }
        return false;
    }

    public boolean J_DOT1() {
        if (classpart.get(i).equals("Identifier")) {
            i++;
            if (J_DOT2()) {
                return true;
            }
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            return true;
        }
        return false;
    }

    public boolean J_DOT2() {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                i++;
                if (J_DOT2()) {
                    return true;
                }
            } else if (valuepart.get(i).equals("*")) {
                i++;
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            return true;
        }
        return false;
    }

    public boolean THIS_DOT1() throws IOException {
        if (classpart.get(i).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (THIS_DOT2()) {
                return true;
            }
        } else if (String.valueOf(classpart.get(i)).equals(";")) {
            return true;
        }
        return false;
    }

    public boolean THIS_DOT2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable/object at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable/object at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (THIS_DOT2()) {
                    return true;
                }
            }
        } else if (valuepart.get(i).equals("=")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (classpart.get(i).equals(";")) {
                    i++;
                    return true;
                }
            }
        } else if (classpart.get(i).equals(";")) {
            i++;
            return true;
        }

        return false;
    }

    public boolean CLASS_EXP() throws IOException {
        if (classpart.get(i).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("class") || classpart.get(i).equals("abstract") || classpart.get(i).equals("interface")) {
            if (valuepart.get(i).equals("public")) {
                MAM = "";
                MAM += valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("class")) {
                    Mtype = "";
                    Mtype += valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            Glorious_Purpose = false;
                        } else {
                            Mname = "";
                            Mname += valuepart.get(i);
                        }
                        i++;
                        if (CLASS_ST1() || CLASS_ST2()) {
                            if (Glorious_Purpose) {
                                insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                index++;
                                insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                MAM = "Default";
                                Mcat = "General";
                                Mparent = "None";
                            } else if (!(Glorious_Purpose)) {
                                Glorious_Purpose = true;
                            }
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                scope.push(++Scope);
                                System.out.println(scope.peek());
                                i++;
                                if (PSVM()) {
                                    return true;
                                } else {
                                    if (M_BODY()) {
                                        if (String.valueOf(classpart.get(i)).equals("}")) {
                                            scope.pop();
                                            --Scope;
                                            i++;
                                            if (CLASS_EXP()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (classpart.get(i).equals("abstract")) {
                    Mcat = "";
                    Mcat += valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("class")) {
                        Mtype = "";
                        Mtype += valuepart.get(i);
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                            if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                                Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                                Glorious_Purpose = false;
                            } else {
                                Mname = "";
                                Mname += valuepart.get(i);
                            }
                            i++;
                            if (CLASS_ST1() || CLASS_ST2()) {
                                if (Glorious_Purpose) {
                                    insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                    index++;
                                    insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                    MAM = "Default";
                                    Mcat = "General";
                                    Mparent = "None";
                                } else if (!(Glorious_Purpose)) {
                                    Glorious_Purpose = true;
                                }
                                if (String.valueOf(classpart.get(i)).equals("{")) {
                                    scope.push(++Scope);
                                    System.out.println(scope.peek());
                                    i++;
                                    if (M_BODY1()) {
                                        if (String.valueOf(classpart.get(i)).equals("}")) {
                                            scope.pop();
                                            --Scope;
                                            i++;
                                            if (CLASS_EXP()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (classpart.get(i).equals("interface")) {
                    Mtype = "";
                    Mtype += valuepart.get(i);
                    i++;
                    if (classpart.get(i).equals("Identifier")) {
                        if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            Glorious_Purpose = false;
                        } else {
                            Mname = "";
                            Mname += valuepart.get(i);
                        }
                        i++;
                        if (CLASS_ST1()) {
                            if (IMPCHCK1()) {
                                if (Glorious_Purpose) {
                                    insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                    index++;
                                    insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                    MAM = "Default";
                                    Mcat = "General";
                                    Mparent = "None";
                                } else if (!(Glorious_Purpose)) {
                                    Glorious_Purpose = true;
                                }
                                if (classpart.get(i).equals("{")) {
                                    scope.push(++Scope);
                                    System.out.println(scope.peek());
                                    i++;
                                    if (M_BODY1()) {
                                        if (classpart.get(i).equals("}")) {
                                            scope.pop();
                                            --Scope;
                                            i++;
                                            if (CLASS_EXP()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            } else if (classpart.get(i).equals("{")) {
                                scope.push(++Scope);
                                System.out.println(scope.peek());
                                if (Glorious_Purpose) {
                                    insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                    index++;
                                    insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                    MAM = "Default";
                                    Mcat = "General";
                                    Mparent = "None";
                                } else if (!(Glorious_Purpose)) {
                                    Glorious_Purpose = true;
                                }
                                i++;
                                if (M_BODY1()) {
                                    if (classpart.get(i).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        if (CLASS_EXP()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        } else if (classpart.get(i).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            if (Glorious_Purpose) {
                                insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                index++;
                                insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                MAM = "Default";
                                Mcat = "General";
                                Mparent = "None";
                            } else if (!(Glorious_Purpose)) {
                                Glorious_Purpose = true;
                            }
                            i++;
                            if (M_BODY1()) {
                                if (classpart.get(i).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    if (CLASS_EXP()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (String.valueOf(classpart.get(i)).equals("class")) {
                MAM = "";
                MAM += "Default";
                Mtype = "";
                Mtype += valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        Glorious_Purpose = false;
                    } else {
                        Mname = "";
                        Mname += valuepart.get(i);
                    }
                    i++;
                    if (CLASS_ST1() || CLASS_ST2()) {
                        if (Glorious_Purpose) {
                            insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                            index++;
                            insertBody("Name", "Type", "A.M", "T.M", "Scope");
                            MAM = "Default";
                            Mcat = "General";
                            Mparent = "None";
                        } else if (!(Glorious_Purpose)) {
                            Glorious_Purpose = true;
                        }
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (PSVM()) {
                                return true;
                            } else {
                                if (M_BODY()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        if (CLASS_EXP()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (classpart.get(i).equals("abstract")) {
                MAM = "";
                MAM += "Default";
                Mcat = "";
                Mcat += valuepart.get(i);
                i++;
                if (String.valueOf(classpart.get(i)).equals("class")) {
                    Mtype = "";
                    Mtype += valuepart.get(i);
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                            System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                            Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                            Glorious_Purpose = false;
                        } else {
                            Mname = "";
                            Mname += valuepart.get(i);
                        }
                        i++;
                        if (CLASS_ST1() || CLASS_ST2()) {
                            if (Glorious_Purpose) {
                                insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                index++;
                                insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                MAM = "Default";
                                Mcat = "General";
                                Mparent = "None";
                            } else if (!(Glorious_Purpose)) {
                                Glorious_Purpose = true;
                            }
                            if (String.valueOf(classpart.get(i)).equals("{")) {
                                scope.push(++Scope);
                                System.out.println(scope.peek());
                                i++;
                                if (M_BODY1()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        if (CLASS_EXP()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (classpart.get(i).equals("interface")) {
                MAM = "";
                MAM += "Default";
                Mtype = "";
                Mtype += valuepart.get(i);
                i++;
                if (classpart.get(i).equals("Identifier")) {
                    if (lookUpClass(valuepart.get(i)).equals(valuepart.get(i))) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                        Glorious_Purpose = false;
                    } else {
                        Mname = "";
                        Mname += valuepart.get(i);
                    }
                    i++;
                    if (CLASS_ST1()) {
                        if (IMPCHCK1()) {
                            if (Glorious_Purpose) {
                                insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                index++;
                                insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                MAM = "Default";
                                Mcat = "General";
                                Mparent = "None";
                            } else if (!(Glorious_Purpose)) {
                                Glorious_Purpose = true;
                            }
                            if (classpart.get(i).equals("{")) {
                                scope.push(++Scope);
                                System.out.println(scope.peek());
                                i++;
                                if (M_BODY1()) {
                                    if (classpart.get(i).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        if (CLASS_EXP()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        } else if (classpart.get(i).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            if (Glorious_Purpose) {
                                insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                                index++;
                                insertBody("Name", "Type", "A.M", "T.M", "Scope");
                                MAM = "Default";
                                Mcat = "General";
                                Mparent = "None";
                            } else if (!(Glorious_Purpose)) {
                                Glorious_Purpose = true;
                            }
                            i++;
                            if (M_BODY1()) {
                                if (classpart.get(i).equals("}")) {
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    if (CLASS_EXP()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    } else if (classpart.get(i).equals("{")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        if (Glorious_Purpose) {
                            insertMain(Mname, Mtype, MAM, Mcat, Mparent, new ArrayList<Sementic.BodyTable>());
                            index++;
                            insertBody("Name", "Type", "A.M", "T.M", "Scope");
                            MAM = "Default";
                            Mcat = "General";
                            Mparent = "None";
                        } else if (!(Glorious_Purpose)) {
                            Glorious_Purpose = true;
                        }
                        i++;
                        if (M_BODY1()) {
                            if (classpart.get(i).equals("}")) {
                                scope.pop();
                                --Scope;
                                i++;
                                if (CLASS_EXP()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (classpart.get(i).equals("$")) {
            return true;
        }
        return false;
    }

    public boolean M_BODY() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("class") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("try") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("abstract") || String.valueOf(classpart.get(i)).equals("interface") || classpart.get(i).equals("Array") || classpart.get(i).equals("Arraylist") || classpart.get(i).equals("this")) {
            if (S_BODY()) {
                if (M_BODY()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else if (classpart.get(i).equals("DataType")) {
            if (ARR_DEC()) {
                return true;
            }
        } else if (classpart.get(i).equals("PSVM")) {
            if (PSVM()) {
                return true;
            }
        }
        /*else if (classpart.get(i).equals("PSVM")){
            if (PSVM()) {
                return true;
            } else {
                if (M_BODY()) {
                    if (String.valueOf(classpart.get(i)).equals("}")) {
                        i++;
                        if (CLASS_EXP()) {
                            return true;
                        }
                    }
                }
            }
        }*/
        return false;
    }

    public boolean M_BODY1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("class") || String.valueOf(classpart.get(i)).equals("if") || String.valueOf(classpart.get(i)).equals("Do") || String.valueOf(classpart.get(i)).equals("cnstr") || String.valueOf(classpart.get(i)).equals("try") || String.valueOf(classpart.get(i)).equals("while") || String.valueOf(classpart.get(i)).equals("for") || String.valueOf(classpart.get(i)).equals("switch") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier") || String.valueOf(classpart.get(i)).equals("abstract") || String.valueOf(classpart.get(i)).equals("interface") || classpart.get(i).equals("Array") || classpart.get(i).equals("Arraylist") || classpart.get(i).equals("this")) {
            if (S_BODY1()) {
                if (M_BODY1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else if (classpart.get(i).equals("DataType")) {
            if (ARR_DEC()) {
                return true;
            }
        }
        return false;
    }

    public boolean S_BODY1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("class") || classpart.get(i).equals("Array") || classpart.get(i).equals("Arraylist")) {
            if (ACC_MOD()) {
                if (BODY2_()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Do")) {
            if (DO_WHILE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("cnstr")) {
            if (CONSTR()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("try")) {
            if (TST_CTH()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("while")) {
            if (WHILE_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("for")) {
            if (FOR_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("switch")) {
            if (SWT_CASE()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(Objects.equals(lookupBTtype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                }
                i++;
                if (X()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall")) {
            if (FUNC_CALL()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupMTname(valuepart.get(i)))) {
                System.out.println("Undeclared interface at Line # " + linenumber.get(i));
                Errortable.write("Undeclared interface at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared interface at Line # " + linenumber.get(i));
                Errortable.write("Undeclared interface at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (ID_SST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("abstract")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                Multiverse = true;
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    Bname = valuepart.get(i);
                    SCOPE = String.valueOf(scope.peek());
                    if (lookupBTredeclaration(Bname, scope.peek())) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertBody(Bname, Btype, BAM, BTM, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (classpart.get(i).equals(";")) {
                                    Multiverse = false;
                                    scope.pop();
                                    --Scope;
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }

                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("interface")) {
            if (INTF_ST()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("this")) {
            i++;
            if (classpart.get(i).equals(".")) {
                i++;
                if (classpart.get(i).equals("Identifier")) {
                    if (THIS_DOT1()) {
                        return true;
                    }
                } else if (classpart.get(i).equals("defcall")) {
                    if (FUNC_CALL()) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean S_BODY() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("DataType") || String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("class") || classpart.get(i).equals("Array") || classpart.get(i).equals("Arraylist")) {
            if (ACC_MOD()) {
                if (BODY2()) {
                    return true;
                }
            }
            return false;
        } else if (classpart.get(i).equals("this")) {
            i++;
            if (classpart.get(i).equals(".")) {
                i++;
                if (classpart.get(i).equals("Identifier")) {
                    if (THIS_DOT1()) {
                        return true;
                    }
                } else if (classpart.get(i).equals("defcall")) {
                    if (FUNC_CALL()) {
                        return true;
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("if")) {
            if (IF_ELSE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Do")) {
            if (DO_WHILE()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("cnstr")) {
            if (CONSTR()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("try")) {
            if (TST_CTH()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("while")) {
            if (WHILE_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("for")) {
            if (FOR_ST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("switch")) {
            if (SWT_CASE()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(Objects.equals(lookupBTtype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                }
                i++;
                if (X()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall")) {
            if (FUNC_CALL()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            /*if (!(lookupbodytable2(valuepart.get(i)))){
                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
            }*/
            i++;
            if (ID_SST()) {
                return true;
            } /*else if (OBJ_CALL()) {
                return true;
            }*/
            return false;
        } /*else if (String.valueOf(classpart.get(i)).equals("abstract")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("def")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("(")) {
                        i++;
                        if (P_DT()) {
                            if (String.valueOf(classpart.get(i)).equals(")")) {
                                i++;
                                if (classpart.get(i).equals(";")) {
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }

                }
            }
            return false;
        }*/ else if (String.valueOf(classpart.get(i)).equals("interface")) {
            if (INTF_ST()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean BODY2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("DataType") || classpart.get(i).equals("void") || String.valueOf(classpart.get(i)).equals("def") || classpart.get(i).equals("static")) {
            if (ACC_MOD_SST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("class")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                /*Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (BODY3()) {
                    return true;
                }
            }
            return false;
        } else if (classpart.get(i).equals("Array")) {
            i++;
            if (ARR_DEC()) {
                return true;
            }
        } else if (classpart.get(i).equals("Arraylist")) {
            if (ARRLST()) {
                return true;
            }
        }
        return false;
    }

    public boolean BODY2_() throws IOException {
        if (classpart.get(i).equals("abstract")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("DataType") || classpart.get(i).equals("void") || String.valueOf(classpart.get(i)).equals("def")) {
                if (ACC_MOD_SST1()) {
                    return true;
                }
                return false;
            }
        } else if (String.valueOf(classpart.get(i)).equals("DataType")) {
            if (ACC_MOD_SST1()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("class")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                /*Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (BODY3()) {
                    return true;
                }
            }
            return false;
        } else if (classpart.get(i).equals("Array")) {
            i++;
            if (ARR_DEC()) {
                return true;
            }
        } else if (classpart.get(i).equals("Arraylist")) {
            if (ARRLST()) {
                return true;
            }
        }
        return false;
    }

    public boolean BODY3() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("{")) {
            scope.push(++Scope);
            System.out.println(scope.peek());
            i++;
            if (M_BODY()) {
                if (String.valueOf(classpart.get(i)).equals("}")) {
                    scope.pop();
                    --Scope;
                    i++;
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("extends")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                /*Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (String.valueOf(classpart.get(i)).equals("{")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (M_BODY()) {
                        if (String.valueOf(classpart.get(i)).equals("}")) {
                            scope.pop();
                            --Scope;
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("implement")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                /*Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupbodytable(Bname,scope.peek())){
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                }
                else {
                    insertBody(Bname,Btype,BAM,BTM,SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "";
                }*/
                i++;
                if (ID2()) {
                    if (String.valueOf(classpart.get(i)).equals("{")) {
                        scope.push(++Scope);
                        System.out.println(scope.peek());
                        i++;
                        if (M_BODY()) {
                            if (String.valueOf(classpart.get(i)).equals("}")) {
                                scope.pop();
                                --Scope;
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean CLASS_ST1() throws IOException {
        if (classpart.get(i).equals("extend")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                String[] received = lookUpClass7(valuepart.get(i));
                if (classpart.get(i - 3).equals("class")) {
                    if (received[0].equals(valuepart.get(i)) && received[1].equals(Mtype)) {
                        Mparent = "";
                        Mparent += valuepart.get(i);
                        i++;
                    } else {
                        System.out.println("Class Not Found Error");
                        Errortable.write("Class Not Found Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    }
                } else if (classpart.get(i - 3).equals("interface")) {
                    if (received[0].equals(valuepart.get(i)) && received[1].equals(Mtype)) {
                        Mparent = "";
                        Mparent += valuepart.get(i);
                        i++;
                    } else {
                        System.out.println("Class Not Found Error");
                        Errortable.write("Class Not Found Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    }
                    return true;
                }
            }
        } else if (classpart.get(i).equals("{")) {
            return true;
        }
        return false;
    }

    public boolean CLASS_ST2() throws IOException {
        if (classpart.get(i).equals("implements")) {
            i++;
            if (classpart.get(i).equals("Identifier")) {
                String[] received = lookUpClass7(valuepart.get(i));
                if (classpart.get(i - 3).equals("class")) {
                    if (received[0].equals(valuepart.get(i)) && received[1].equals("interface")) {
                        Mparent = "";
                        Mparent += valuepart.get(i);
                        i++;
                    } else {
                        System.out.println("Interface Not Found Error");
                        Errortable.write("Interface Not Found Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    }
                }
                if (IMPCHCK1()) {
                    return true;
                }
            }
        } else if (classpart.get(i).equals("{")) {
            return true;
        }
        return false;
    }

    public boolean IMPCHCK1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            Mparent += valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                String[] received = lookUpClass7(valuepart.get(i));
                if (received[0].equals(valuepart.get(i)) && received[1].equals("interface")) {
                    Mparent += valuepart.get(i);
                    i++;
                } else {
                    System.out.println("Interface Not Found Error");
                    Errortable.write("Interface Not Found Error at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                if (IMPCHCK2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("{")) {
            return true;
        }
        return false;
    }

    public boolean IMPCHCK2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            Mparent += valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                String[] received = lookUpClass7(valuepart.get(i));
                if (received[0].equals(valuepart.get(i)) && received[1].equals("interface")) {
                    Mparent += valuepart.get(i);
                    i++;
                } else {
                    System.out.println("Class Not Found Error");
                    Errortable.write("Class Not Found Error at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                if (IMPCHCK2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("{")) {
            return true;
        }
        return false;
    }

    public boolean INTF_ST() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("interface")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("{")) {
                    scope.push(++Scope);
                    System.out.println(scope.peek());
                    i++;
                    if (FUNC_DEC2()) {
                        if (String.valueOf(classpart.get(i)).equals("}")) {
                            scope.pop();
                            --Scope;
                            i++;
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean INTF_IMP() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Access Modifier") || String.valueOf(classpart.get(i)).equals("class")) {
            if (ACC_MOD()) {
                if (String.valueOf(classpart.get(i)).equals("class")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                        if (!(lookupMTname(valuepart.get(i)))) {
                            System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                        } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                            System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                            Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                        }
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("implement")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                                if (!(lookupMTname(valuepart.get(i)))) {
                                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                                }
                                i++;
                                if (ID2()) {
                                    if (String.valueOf(classpart.get(i)).equals("{")) {
                                        scope.push(++Scope);
                                        System.out.println(scope.peek());
                                        i++;
                                        if (M_BODY()) {
                                            if (String.valueOf(classpart.get(i)).equals("}")) {
                                                scope.pop();
                                                --Scope;
                                                i++;
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean ID2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupMTname(valuepart.get(i)))) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                }
                i++;
                if (ID2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("{")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OBJ_DEC() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupMTname(valuepart.get(i)))) {
                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
            } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                Bname = valuepart.get(i);
                SCOPE = String.valueOf(scope.peek());
                if (lookupBTredeclaration(Bname, scope.peek())) {
                    System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                    Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (Multiverse) {
                    if ((lookupfunctiontable(valuepart.get(i), scope.peek()))) {
                        System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
                        Errortable.write("Redeclaration Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else {
                        insertFunction(Bname, Btype, SCOPE);
                        Bname = "";
                        Btype = "";
                        BAM = "Default";
                        BTM = "none";
                        SCOPE = "";
                    }
                } else {
                    insertBody(Bname, Btype, BAM, BTM, SCOPE);
                    Bname = "";
                    Btype = "";
                    BAM = "Default";
                    BTM = "none";
                    SCOPE = "";
                }
                i++;
                if (String.valueOf(classpart.get(i)).equals("=")) {
                    i++;
                    if (String.valueOf(classpart.get(i)).equals("new")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                            if (Check){
                                if (!Objects.equals(valuepart.get(i), valuepart.get(i - 4))){
                                    System.out.println("Invalid Reference for object at line # " + linenumber.get(i));
                                    Errortable.write("Invalid Reference for object at Line # " + linenumber.get(i) + System.lineSeparator());
                                }
                            }
                            else if (!(lookupMTname(valuepart.get(i)))) {
                                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                            }
                            else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                                System.out.println("Undeclared Class at Line # " + linenumber.get(i));
                                Errortable.write("Undeclared Class at Line # " + linenumber.get(i) + System.lineSeparator());
                            }
                            i++;
                            if (String.valueOf(classpart.get(i)).equals("(")) {
                                i++;
                                if (PL()) {
                                    if (String.valueOf(classpart.get(i)).equals(")")) {
                                        i++;
                                        if (String.valueOf(classpart.get(i)).equals(";")) {
                                            i++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean SWT_CASE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("switch")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals("(")) {
                i++;
                if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                    if (!(lookupBTundeclaration(valuepart.get(i)))) {
                        System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                        Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                    } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                        System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                        Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                    }
                    i++;
                    if (String.valueOf(classpart.get(i)).equals(")")) {
                        i++;
                        if (String.valueOf(classpart.get(i)).equals("{")) {
                            scope.push(++Scope);
                            System.out.println(scope.peek());
                            i++;
                            if (CASE()) {
                                if (DEFAULT()) {
                                    if (String.valueOf(classpart.get(i)).equals("}")) {
                                        scope.pop();
                                        --Scope;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean CASE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("case")) {
            i++;
            if (OE()) {
                if (String.valueOf(classpart.get(i)).equals(":")) {
                    i++;
                    if (BODY()) {
                        if (String.valueOf(classpart.get(i)).equals("Break-Continue")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                i++;
                                if (CASE1()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean CASE1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("case")) {
            i++;
            if (OE()) {
                if (String.valueOf(classpart.get(i)).equals(":")) {
                    i++;
                    if (BODY()) {
                        if (String.valueOf(classpart.get(i)).equals("Break-Continue")) {
                            i++;
                            if (String.valueOf(classpart.get(i)).equals(";")) {
                                i++;
                                if (CASE1()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("def") || String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DEFAULT() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("default")) {
            i++;
            if (String.valueOf(classpart.get(i)).equals(":")) {
                i++;
                if (BODY()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (AE()) {
                if (OE1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean AE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (RE()) {
                if (AE1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean OE1() throws IOException {
        if (String.valueOf(valuepart.get(i)).equals("||")) {
            op = valuepart.get(i);
            i++;
            if (AE()) {
                if (OE1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OE2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(",")) {
            i++;
            if (OE()) {
                if (OE2()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean RE() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (E()) {
                if (RE1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean AE1() throws IOException {
        if (String.valueOf(valuepart.get(i)).equals("&&")) {
            op = valuepart.get(i);
            i++;
            if (RE()) {
                if (AE1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals(")") || String.valueOf(valuepart.get(i)).equals("||")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean RE1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Relational Operator")) {
            op = valuepart.get(i);
            i++;
            if (E()) {
                if (RE1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals(")") || String.valueOf(classpart.get(i)).equals("Logical Operator")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean E() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (T()) {
                if (E1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean E1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Plus-Minus Operator")) {
            op = valuepart.get(i);
            i++;
            if (T()) {
                if (E1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals(")") || String.valueOf(classpart.get(i)).equals("Logical Operator") || String.valueOf(classpart.get(i)).equals("Relational Operator")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean T() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("defcall") || String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant") || String.valueOf(classpart.get(i)).equals("(") || String.valueOf(classpart.get(i)).equals("!") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator") || String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (F()) {
                if (T1()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean T1() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Multiply-Divide-Modulus Operator")) {
            op = valuepart.get(i);
            i++;
            if (F()) {
                if (T1()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals(")") || String.valueOf(classpart.get(i)).equals("Logical Operator") || String.valueOf(classpart.get(i)).equals("Relational Operator") || String.valueOf(classpart.get(i)).equals("Plus-Minus Operator")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CONST() {
        if (String.valueOf(classpart.get(i)).equals("Integer Constant")) {
            t2 = "int";
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("Float/Double Constant")) {
            t2 = "float";
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("Character Constant")) {
            t2 = "char";
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("String Constant")) {
            t2 = "String";
            i++;
            return true;
        } else if (String.valueOf(classpart.get(i)).equals("Boolean Constant")) {
            t2 = "bool";
            i++;
            return true;
        } else {
            return false;
        }
    }

    public boolean F() throws IOException {
        if (String.valueOf(classpart.get(i)).equals("Integer Constant") || String.valueOf(classpart.get(i)).equals("Float/Double Constant") || String.valueOf(classpart.get(i)).equals("Character Constant") || String.valueOf(classpart.get(i)).equals("String Constant") || String.valueOf(classpart.get(i)).equals("Boolean Constant")) {
            if (CONST()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("(")) {
            i++;
            if (E()) {
                if (String.valueOf(classpart.get(i)).equals(")")) {
                    i++;
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("!")) {
            i++;
            if (F()) {
                return true;
            }
            return false;
        } else if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            op = valuepart.get(i);
            i++;
            if (String.valueOf(classpart.get(i)).equals("Identifier")) {
                if (!(lookupBTundeclaration(valuepart.get(i)))) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                    System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                    Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
                } else if (!(Objects.equals(lookupBTtype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                } else if (Multiverse && !(Objects.equals(lookupfunctiontabletype(valuepart.get(i)), "int"))) {
                    System.out.println("Type Mismatch Error at line # " + linenumber.get(i));
                    Errortable.write("Type Mismatch Error at Line # " + linenumber.get(i) + System.lineSeparator());
                    TYPE = "";
                }
                i++;
                if (X()) {
                    return true;
                }
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("Identifier")) {
            if (!(lookupBTundeclaration(valuepart.get(i)))) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            else if (lookupBTundeclaration(valuepart.get(i))){
                t2 = lookupBTtype(valuepart.get(i));
            }
            if (!(lookupfunctiontable1(valuepart.get(i))) && Multiverse) {
                System.out.println("Undeclared Variable at Line # " + linenumber.get(i));
                Errortable.write("Undeclared Variable at Line # " + linenumber.get(i) + System.lineSeparator());
            }
            else if (lookupfunctiontable1(valuepart.get(i))){
                t2 = lookupfunctiontabletype(valuepart.get(i));
            }

            /*Bname = valuepart.get(i);
            SCOPE = String.valueOf(scope.peek());
            if (lookupbodytable(Bname, scope.peek())) {
                System.out.println("Redeclaration Error at Line # " + linenumber.get(i));
            } else if (Multiverse) {
                insertFunction(Bname, Btype, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            } else {
                insertBody(Bname, Btype, BAM, BTM, SCOPE);
                Bname = "";
                Btype = "";
                BAM = "Default";
                BTM = "none";
                SCOPE = "";
            }*/
            i++;
            if (F2()) {
                return true;
            }
            return false;
        } else if (String.valueOf(classpart.get(i)).equals("defcall")) {
            if (FUNC_CALL()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean F2() throws IOException {
        if (String.valueOf(classpart.get(i)).equals(".") || String.valueOf(classpart.get(i)).equals("[") || classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
            if (X()) {
                if (classpart.get(i).equals("Increment Operator") || classpart.get(i).equals("Decrement Operator")) {
                    op = valuepart.get(i);
                    i++;
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } else if (String.valueOf(classpart.get(i)).equals(";") || String.valueOf(classpart.get(i)).equals(",") || String.valueOf(classpart.get(i)).equals("}") || String.valueOf(classpart.get(i)).equals("]") || String.valueOf(classpart.get(i)).equals(")") || String.valueOf(classpart.get(i)).equals(":") || String.valueOf(classpart.get(i)).equals("Logical Operator") || String.valueOf(classpart.get(i)).equals("Relational Operator") || String.valueOf(classpart.get(i)).equals("Plus-Minus Operator") || String.valueOf(classpart.get(i)).equals("Multiply-Divide-Modulus Operator")) {
            return true;
        } else {
            return false;
        }
    }

    String lookUpClass(String Name) {

        for (int i = 0; i < this.MT.size(); i++) {

            if (this.MT.get(i).name.equals(Name)) {

                return Name;
            }

        }

        return Mtype;
    }

    // for redeclaration
    boolean lookupBTredeclaration(String ID, int scoope) {
        for (int x = 1; x < MT.size(); x++) {
            //System.out.println(MT.get(x).name + " " + MT.get(x).type + " " + MT.get(x).AM + " " + MT.get(x).cat + " " + MT.get(x).parent);
            for (int k = 0; k < MT.get(x).link.size(); k++) {
                //System.out.println(MT.get(x).link.get(k).name + " " + MT.get(x).link.get(k).type + " " + MT.get(x).link.get(k).AM + " " + MT.get(x).link.get(k).TM + " " + MT.get(x).link.get(k).SCOPE);
                if (ID.equals(MT.get(x).link.get(k).name) && String.valueOf(scoope).equals(MT.get(x).link.get(k).SCOPE)) {
                    return true;
                }
            }
        }
        return false;
    }

    // for undeclared type
    boolean lookupBTundeclaration(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            //System.out.println(MT.get(x).name + " " + MT.get(x).type + " " + MT.get(x).AM + " " + MT.get(x).cat + " " + MT.get(x).parent);
            for (int k = 0; k < MT.get(x).link.size(); k++) {
                //System.out.println(MT.get(x).link.get(k).name + " " + MT.get(x).link.get(k).type + " " + MT.get(x).link.get(k).AM + " " + MT.get(x).link.get(k).TM + " " + MT.get(x).link.get(k).SCOPE);
                if (ID.equals(MT.get(x).link.get(k).name)) {
                    return true;
                }
            }
        }
        return false;
    }

    String lookupBTtype(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            //System.out.println(MT.get(x).name + " " + MT.get(x).type + " " + MT.get(x).AM + " " + MT.get(x).cat + " " + MT.get(x).parent);
            for (int k = 0; k < MT.get(x).link.size(); k++) {
                //System.out.println(MT.get(x).link.get(k).name + " " + MT.get(x).link.get(k).type + " " + MT.get(x).link.get(k).AM + " " + MT.get(x).link.get(k).TM + " " + MT.get(x).link.get(k).SCOPE);
                if (ID.equals(MT.get(x).link.get(k).name)) {
                    return String.valueOf(MT.get(x).link.get(k).type);
                }
            }
        }
        return "not found";
    }

    String lookupBTfunctype(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            //System.out.println(MT.get(x).name + " " + MT.get(x).type + " " + MT.get(x).AM + " " + MT.get(x).cat + " " + MT.get(x).parent);
            for (int k = 0; k < MT.get(x).link.size(); k++) {
                //System.out.println(MT.get(x).link.get(k).name + " " + MT.get(x).link.get(k).type + " " + MT.get(x).link.get(k).AM + " " + MT.get(x).link.get(k).TM + " " + MT.get(x).link.get(k).SCOPE);
                if (ID.equals(MT.get(x).link.get(k).name)) {
                    return String.valueOf(MT.get(x).link.get(k).type.split("-->"));
                }
            }
        }
        return "not found";
    }

    // OBJ DEC
    boolean lookupMTname(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            if (ID.equals(MT.get(x).name)) {
                return true;
            }
        }
        return false;
    }

    String lookupMTparent(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            if (ID.equals(MT.get(x).name)) {
                return MT.get(x).parent;
            }
        }
        return "";
    }

    String lookupmaintabletype(String ID) {
        for (int x = 1; x < MT.size(); x++) {
            if (ID.equals(MT.get(x).name)) {
                return MT.get(x).type;
            }
        }
        return "not found";
    }

    boolean lookupfunctiontable(String ID, int scoope) {
        for (int f = 0; f < FT.size(); f++) {
            if (ID.equals(FT.get(f).name) && String.valueOf(scoope).equals(FT.get(f).Scope)) {
                return true;
            }
        }
        return false;
    }

    boolean lookupfunctiontable1(String ID) {
        for (int f = 0; f < FT.size(); f++) {
            if (ID.equals(FT.get(f).name)) {
                return true;
            }
        }
        return false;
    }

    String lookupfunctiontabletype(String ID) {
        for (int f = 0; f < FT.size(); f++) {
            if (ID.equals(FT.get(f).name)) {
                return FT.get(f).type;
            }
        }
        return "not found";
    }




    /*public String lookUpClass11(String Name,String Type) {

        for (int i = 0; i < this.MT.size(); i++) {

            if (this.MT.get(i).name.equals(Name) && this.MT.get(i).type.equals("interface")) {

                return true;
            }

        }

        return false;
    }
    public boolean lookUpClass12(String Name,String Type) {

        for (int i = 0; i < this.MT.size(); i++) {

            if (this.MT.get(i).name.equals(Name) && this.MT.get(i).type.equals("class")) {

                return true;
            }

        }

        return false;
    }*/

    String[] lookUpClass7(String Name) {

        String[] answer = new String[2];
        answer[0] = "";
        answer[1] = "";

        for (int i = 0; i < this.MT.size(); i++) {

            if (this.MT.get(i).name.equals(Name)) {

                answer[0] = this.MT.get(i).name;
                answer[1] = this.MT.get(i).type;
                return answer;

            }

        }

        return answer;
    }

    String Comp(String T1, String T2, String Opr) {
        if (Opr.equals("&&") || Opr.equals("||")) {
            if (T1.equals("boolean") && T2.equals("boolean")) {
                return "boolean";
            }
        }
        if (Opr.equals("<") || Opr.equals(">") || Opr.equals("<=") || Opr.equals(">=")) {
            if (T1.equals("num") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{") || T2.equals("num{"))) {
                return "boolean";
            }
            if (T1.equals("letter") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{") || T2.equals("num{"))) {
                return "boolean";
            }
            if (T1.equals("num{") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{") || T2.equals("num{"))) {
                return "boolean";
            }
            if (T1.equals("char{") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{"))) {
                return "boolean";
            }
            if ((T1.equals("deci")) && (T2.equals("deci") || T2.equals("letter") || T2.equals("num") || T2.equals("deci{") || T2.equals("letter{") || T2.equals("num{"))) {
                return "boolean";
            }
            if ((T1.equals("deci{")) && (T2.equals("letter") || T2.equals("num") || T2.equals("letter{") || T2.equals("num{") || T2.equals("deci{") || T2.equals("deci"))) {
                return "boolean";
            }
        }
        switch (Opr) {
            case "=":
                if (T1.equals("num") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{") || T2.equals("num{"))) {
                    return "num";
                }
                if (T1.equals("letter") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{"))) {
                    return "num";
                }
                if (T1.equals("num{") && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                if (T1.equals("letter{") && (T2.equals("num") || T2.equals("letter") || T2.equals("letter{"))) {
                    return "num";
                }
                if ((T1.equals("deci")) && (T2.equals("deci") || T2.equals("letter") || T2.equals("num") || T2.equals("deci{") || T2.equals("letter{") || T2.equals("num{"))) {
                    return "deci";
                }
                if ((T1.equals("deci{")) && (T2.equals("letter") || T2.equals("num") || T2.equals("letter{") || T2.equals("num{") || T2.equals("deci{") || T2.equals("deci"))) {
                    return "deci";
                }
                if ((T1.equals("word") || T1.equals("word{")) && (T2.equals("word") || T2.equals("word{"))) {
                    return "word";
                }
                if (T1.equals("boolean") && T2.equals("boolean")) {
                    return "boolean";
                }
                break;
            case "+":
                if ((T1.equals("num") || T1.equals("letter") || T1.equals("num{") || T1.equals("letter{")) && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                if ((T1.equals("deci")) && (T2.equals("deci") || T2.equals("num") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter") || T2.equals("letter{"))) {
                    return "deci";
                }
                if ((T1.equals("deci") || T1.equals("num") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter") || T1.equals("letter")) && (T2.equals("deci{") || T2.equals("deci"))) {
                    return "deci";
                }
                if ((T1.equals("word") || T1.equals("word{")) && (T2.equals("word") || T2.equals("word{") || T2.equals("deci") || T2.equals("num") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter") || T2.equals("letter"))) {
                    return "word";
                }
                if ((T1.equals("word") || T1.equals("word{") || T1.equals("deci") || T1.equals("num") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter") || T1.equals("letter{")) && (T2.equals("word") || T2.equalsIgnoreCase("word{"))) {
                    return "word";
                }
                break;
            case "-":
                if ((T1.equals("deci") || T1.equals("deci{")) && (T2.equals("num") || T2.equals("deci") || T2.equals("letter") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter{"))) {
                    return "deci";
                }
                if ((T1.equals("num") || T1.equals("deci") || T1.equals("letter") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter{")) && (T2.equals("deci") || T2.equals("deci{"))) {
                    return "deci";
                }
                if ((T1.equals("num") || T1.equals("letter") || T1.equals("num{") || T1.equals("letter{")) && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                break;
            case "*":
                if ((T1.equals("num") || T1.equals("letter") || T1.equals("num{") || T1.equals("letter{")) && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                if ((T1.equals("deci") || T1.equals("deci{")) && (T2.equals("num") || T2.equals("deci") || T2.equals("letter") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter{"))) {
                    return "deci";
                }
                if ((T1.equals("num") || T1.equals("deci") || T1.equals("letter") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter{")) && (T2.equals("deci") || T2.equals("deci{"))) {
                    return "deci";
                }
                break;
            case "/":
                if ((T1.equals("num") || T1.equals("letter") || T1.equals("num{") || T1.equals("letter{")) && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                if ((T1.equals("deci") || T1.equals("deci{")) && (T2.equals("num") || T2.equals("deci") || T2.equals("letter") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter{"))) {
                    return "deci";
                }
                if ((T1.equals("num") || T1.equals("deci") || T1.equals("letter") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter{")) && (T2.equals("deci") || T2.equals("deci{"))) {
                    return "deci";
                }
                break;
            case "%":
                if ((T1.equals("num") || T1.equals("letter") || T1.equals("num{") || T1.equals("letter{")) && (T2.equals("num") || T2.equals("letter") || T2.equals("num{") || T2.equals("letter{"))) {
                    return "num";
                }
                if ((T1.equals("deci") || T1.equals("deci{")) && (T2.equals("num") || T2.equals("deci") || T2.equals("letter") || T2.equals("num{") || T2.equals("deci{") || T2.equals("letter{"))) {
                    return "deci";
                }
                if ((T1.equals("num") || T1.equals("deci") || T1.equals("letter") || T1.equals("num{") || T1.equals("deci{") || T1.equals("letter{")) && (T2.equals("deci") || T2.equals("deci{"))) {
                    return "deci";
                }
                break;
            case "==":
                if ((T1.equals("num") || T1.equals("num{") || T1.equals("letter") || T1.equals("letter{") || T1.equals("deci") || T1.equals("deci{")) && (T1.equals("num") || T1.equals("num{") || T1.equals("letter") || T1.equals("letter{") || T1.equals("deci") || T1.equals("deci{"))) {
                    return "boolean";
                }
                if ((T1.equals("word") || T1.equals("word{")) && (T2.equals("word") || T2.equals("word"))) {
                    return "boolean";
                }
        }

        return "";
    }

    String Comp1(String T1, String Opr) {

        String rType = "";

        if (T1.equals("num") && (Opr.equals("++") || Opr.equals("--"))) {
            rType = "num";
        } else if (T1.equals("deci") && (Opr.equals("--")) || Opr.equals("++")) {
            rType = "deci";
        } else if (T1.equals("letter") && (Opr.equals("--")) || Opr.equals("++")) {
            rType = "letter";
        } else {
            rType = "";
        }
        return rType;
    }
/*

    String lookUpFunc(String name, String pl, String rtype, String className) {

        String rType = "";
        pl = rtype + "->" + pl;
        boolean flag = false;

        for (int i = 0; i < this.classTable.size(); i++) {

            if (this.classTable.get(i).className.equals(className)) {

                ArrayList<MemberTable> memTable = this.classTable.get(i).memberTable;

                for (int j = 0; j < memTable.size(); j++) {

                    if (name.equals(memTable.get(j).name) && pl.equals(memTable.get(j).type)) {
                        flag = true;

                        pl = memTable.get(j).type;

                        for (String ret : pl.split("->")) {
                            rType = ret;
                            pl = pl;
                            //  System.out.println(pl);
                            break;
                        }
                    }

                }

            }
        }
        if (flag == true) {
            if (rType.equals("")) {
                return pl;
            }
            return rType;
        } else {
            return rType;
        }

    }

    void insertFunc(String name, String pl, String rtype, String am, String tm, int scope, String classname) {

        for (int i = 0; i < this.classTable.size(); i++) {

            if (this.classTable.get(i).className.equals(className)) {

                // System.out.println(name+","+rtype+"->"+pl+","+am+","+tm+","+scope);
                this.classTable.get(i).memberTable.add(new MemberTable(name, rtype + "->" + pl, am, tm, scope));
            }

        }

    }

    String lookUpFunc1(String name, String pl, String rtype, String className) {

        String rType = "";
        String pl1 = "";
        boolean flag = false;

        for (String ret : pl.split("->")) {
            pl1 = ret;
            //  System.out.println(pl);
            break;
        }

        rtype = pl1 + "->" + rtype;

        for (int i = 0; i < this.classTable.size(); i++) {

            if (this.classTable.get(i).className.equals(className)) {

                ArrayList<MemberTable> memTable = this.classTable.get(i).memberTable;

                for (int j = 0; j < memTable.size(); j++) {

                    if (name.equals(memTable.get(j).name) && rtype.equals(memTable.get(j).type)) {
                        flag = true;

                        pl = memTable.get(j).type;

                        for (String ret : pl.split("->")) {
                            rType = ret;
                            pl = pl;
                            break;
                        }
                    }

                }

            }
        }
        if (flag == true) {
            if (rType.equals("")) {
                return pl;
            }
            return rType;
        } else {
            return rType;
        }

    }

    String lookUpVariable(String variableName, int variableScope, String className) {

        String type = "";

        for (int i = 0; i < this.classTable.size(); i++) {

            if (this.classTable.get(i).className.equals(className)) {

                ArrayList<MemberTable> memTable = this.classTable.get(i).memberTable;

                for (int j = 0; j < memTable.size(); j++) {

                    if (memTable.get(j).name.equals(variableName) && variableScope == memTable.get(j).scope) {

                        //  for (int k = 0; k < this.currentScopeStack.size(); k++) {
                        //    if (this.currentScopeStack.get(k).equals(memTable.get(j).scope)) {
                        type = memTable.get(j).type;
                        return type;

                        //   }
                        //}
                    } else if (memTable.get(j).name.equals(variableName)) {
                        for (int k = 0; k < this.currentScopeStack.size(); k++) {
                            if (this.currentScopeStack.get(k).equals(memTable.get(j).scope) && memTable.get(j).scope != classScope) {

                                type = memTable.get(j).type;
                                return type;

                            }
                        }
                    }

                }

            }

        }
        return type;
    }

    void insertVariable(String variableName, String variableType, String AM, String TM, int variableScope, String className) {

        for (int i = 0; i < this.classTable.size(); i++) {

            if (this.classTable.get(i).className.equals(className)) {

                this.classTable.get(i).memberTable.add(new MemberTable(variableName, variableType, AM, TM, variableScope));
            }

        }

    }

    void insertClass(String className, String AM) {

        this.classTable.add(new ClassTable(className, AM));
    }

    void popCurrentScopeStack() {

        if (this.currentScopeStack.size() > 0) {

            this.currentScopeStack.remove(this.currentScopeStack.size() - 1);

        }

    }

    void pushCurrentScopeStack() {

        this.currentScopeStack.add(this.currentScope);

    }
*/
}