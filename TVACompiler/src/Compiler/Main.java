package Compiler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UI ui = new UI();
        //Lexical obj = new Lexical();
       // Syntax ob = new Syntax();
        //Sementic o = new Sementic();
        /*A b = new A();
        System.out.println(b.a);
        A bb = new B();
        System.out.println(bb.a);
        B bbb = new B();
        System.out.println(bbb.a);
        B bbbb = new A();
        System.out.println(bbbb.a);
        C c = new C();*/
    }
}
class A{
    int a=5;
}
class B extends A{
}
class C{

}
//class P implements K {

//}
//interface K{}
/*
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isWhitespace;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\NB\\IdeaProjects\\haha compiler\\input.txt");
            //FileInputStream fis = new FileInputStream("F:\\test1.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
            OutputStream out = new FileOutputStream("output.txt");
            String savedLine;    //constructs a string buffer with no characters
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<String> brokenword = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();
            ArrayList<String> Token = new ArrayList<>();
            ArrayList<String> classPart = new ArrayList<>();
            ArrayList<String> valuePart = new ArrayList<>();
            ArrayList<String> lineNumber = new ArrayList<>();
            int looper1 = 1;
            int h = 0;
            boolean comment = false;

            while (sc.hasNextLine()) {
                savedLine = "";
                savedLine += sc.nextLine();
                lines.add(savedLine);
                StringBuilder words = new StringBuilder(new String());
                int i = 0;
                for (i = 0; i < savedLine.length(); i++) {
                    if (isWhitespace(savedLine.charAt(i)) && !comment) {
                        if (words.length() == 0) {
                            // do nothing
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("+") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("+")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "++";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "++";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "+=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "+=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else if (Character.isDigit(savedLine.charAt(i + 1)) && !Character.isLetterOrDigit(savedLine.charAt(i - 1))) {
                            words.append(savedLine.charAt(i));
                            i++;
                            while (Character.isDigit(savedLine.charAt(i))) {
                                words.append(savedLine.charAt(i));
                                if (savedLine.length() - 1 == i) {
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("-") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("-")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "--";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "--";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "-=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "-=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else if (Character.isDigit(savedLine.charAt(i + 1)) && !Character.isLetterOrDigit(savedLine.charAt(i - 1))) {
                            words.append(savedLine.charAt(i));
                            i++;
                            while (Character.isDigit(savedLine.charAt(i))) {
                                words.append(savedLine.charAt(i));
                                i++;
                            }
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            i--;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("*") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "*=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "*=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("/") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "/=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "/=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("%") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "%=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "%=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("=") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "==";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "==";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }           // FLOAT
                    } else if (String.valueOf(savedLine.charAt(i)).equals(".") && !comment) {
                        boolean flag1 = false;
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else if (words.length() > 0) {
                            Pattern pattern1 = Pattern.compile("^[+|-]?[0-9]{1,10}$");
                            Matcher matcher1 = pattern1.matcher(words.toString());
                            boolean result1 = matcher1.find();
                            if (result1) {
                                words.append(savedLine.charAt(i));
                                int j = i;
                                while (Character.isDigit(savedLine.charAt(j + 1)) || Character.isLetter(savedLine.charAt(j + 1)) || savedLine.charAt(j + 1) == 'e' || savedLine.charAt(j + 1) == 'E' || savedLine.charAt(j + 1) == '+' || savedLine.charAt(j + 1) == '-' && !flag1) {
                                    words.append(savedLine.charAt(j + 1));
                                    if (savedLine.charAt(j + 1) == '+' || savedLine.charAt(j + 1) == '-') {
                                        flag1 = true;
                                        j++;
                                        break;
                                    }
                                    j++;
                                }
                                while (Character.isDigit(savedLine.charAt(j + 1)) || Character.isLetter(savedLine.charAt(j + 1)) || savedLine.charAt(j + 1) == '+' || savedLine.charAt(j + 1) == '-' && flag1) {
                                    if (Character.isDigit(savedLine.charAt(j + 1)) || Character.isLetter(savedLine.charAt(j + 1))) {
                                        words.append(savedLine.charAt(j + 1));
                                        j++;
                                    } else if (savedLine.charAt(j + 1) == '+' || savedLine.charAt(j + 1) == '-') {
                                        brokenword.add(words.toString());
                                        temp.add(words.toString());
                                        words = new StringBuilder();
                                        brokenword.add(String.valueOf(savedLine.charAt(j + 1)));
                                        temp.add(String.valueOf(savedLine.charAt(j + 1)));
                                        j++;
                                    }
                                }
                                i = j;
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals(";") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals(":") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("{") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("}") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("(") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals(")") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("[") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("]") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("#") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("/")) {
                            i++;
                            comment = true;
                        } else {
                            break;
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("#")) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("/")) {
                            i++;
                            comment = true;
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("/")) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("#")) {
                            i++;
                            comment = false;
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("'") && !comment) {
                        if (!words.isEmpty()) {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            brokenword.add(String.valueOf(savedLine.charAt(i)));
                            temp.add(String.valueOf(savedLine.charAt(i)));
                        } else if (words.isEmpty()) {
                            String CHAR = "";
                            CHAR += String.valueOf(savedLine.charAt(i));
                            int c = 0;
                            for (c = i + 1; c < savedLine.length(); c++) {
                                if (!String.valueOf(savedLine.charAt(c)).equals("'")) {
                                    CHAR += String.valueOf(savedLine.charAt(c));
                                } else if (String.valueOf(savedLine.charAt(c)).equals("'")) {
                                    CHAR += String.valueOf(savedLine.charAt(c));
                                    break;
                                }
                                i = c + 1;
                            }
                            brokenword.add(CHAR);
                            temp.add(CHAR);
                        }
                    } else if (savedLine.charAt(i) == '"' && !comment) {
                        if (!words.isEmpty()) {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            brokenword.add(String.valueOf(savedLine.charAt(i)));
                            temp.add(String.valueOf(savedLine.charAt(i)));
                        } else if (words.isEmpty()) {
                            String STIR = "";
                            STIR += String.valueOf(savedLine.charAt(i));
                            int c = 0;
                            for (c = i + 1; c < savedLine.length(); c++) {
                                if (savedLine.charAt(c) == '"') {
                                    STIR += String.valueOf(savedLine.charAt(c));
                                    i = c;
                                    break;

                                } else if (!String.valueOf(savedLine.charAt(c)).equals("\"")) {
                                    STIR += String.valueOf(savedLine.charAt(c));
                                }
                            }
                            brokenword.add(STIR);
                            temp.add(STIR);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("&") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("&")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "&&";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "&&";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("|") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("|")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "||";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "||";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("!") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "!=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "!=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("<") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += "<=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += "<=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals(">") && !comment) {
                        if (String.valueOf(savedLine.charAt(i + 1)).equals("=")) {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += ">=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += ">=";
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                            i++;
                        } else {
                            if (words.length() == 0) {
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            } else {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                                words = new StringBuilder();
                                String specialcharacter = "";
                                specialcharacter += (String.valueOf(savedLine.charAt(i)));
                                brokenword.add(specialcharacter);
                                temp.add(specialcharacter);
                            }
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals(",") && !comment) {
                        if (words.length() == 0) {
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        } else {
                            brokenword.add(words.toString());
                            temp.add(words.toString());
                            words = new StringBuilder();
                            String specialcharacter = "";
                            specialcharacter += (String.valueOf(savedLine.charAt(i)));
                            brokenword.add(specialcharacter);
                            temp.add(specialcharacter);
                        }
                    } else if (String.valueOf(savedLine.charAt(i)).equals("e") && !comment) {
                        Pattern pattern2 = Pattern.compile("^[+|-]?[0-9]{1,10}$");
                        Matcher matcher2 = pattern2.matcher(words.toString());
                        boolean result2 = matcher2.find();
                        if (result2) {
                            String intwala_e = "";
                            intwala_e = String.valueOf(savedLine.charAt(i));
                            temp.add(words.toString());
                            brokenword.add(words.toString());
                            words = new StringBuilder();
                            temp.add(intwala_e);
                            brokenword.add(intwala_e);
                        } else {
                            words.append(savedLine.charAt(i));
                            if (savedLine.length() - 1 == i && !words.isEmpty()) {
                                if (Character.isDigit(savedLine.charAt(i)) || Character.isLetter(savedLine.charAt(i))) {
                                    brokenword.add(words.toString());
                                    temp.add(words.toString());
                                }
                            }
                        }
                    } else if (!comment) {
                        words.append(savedLine.charAt(i));
                        if (savedLine.length() - 1 == i && !words.isEmpty()) {
                            if (Character.isDigit(savedLine.charAt(i)) || Character.isLetter(savedLine.charAt(i))) {
                                brokenword.add(words.toString());
                                temp.add(words.toString());
                            }
                        }
                    }
                }
                while (looper1 <= lines.size()) {
                    System.out.println("Line no: " + looper1);
                    System.out.println(lines.get(looper1 - 1));
                    System.out.println(temp);
                    if (!temp.isEmpty()) {
                        for (int x = 0; x < temp.size(); x++) {
                            int t = x + 1;
                            String tox = temp.get(x);
                            String token = "";
                            if (Character.isDigit(tox.charAt(0))) {
                                Pattern pattern3 = Pattern.compile("^[+|-]?[0-9]{1,10}$");
                                Pattern pattern4 = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
                                Matcher matcher3 = pattern3.matcher(tox);
                                Matcher matcher4 = pattern4.matcher(tox);
                                boolean result3 = matcher3.find();
                                boolean result4 = matcher4.find();
                                if (result3) {
                                    token = "TOKEN # " + t + " (   CP => Integer Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Integer Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else if (result4) {
                                    token = "TOKEN # " + t + " (   CP => Float/Double Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Float/Double Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else {
                                    token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Invalid Token");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                }
                            } else if (tox.equals("+") || tox.equals("-")) {
                                token = "TOKEN # " + t + " (   CP => Plus-Minus Operator  VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Plus-Minus Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.charAt(0) == '+' && tox.length() >= 2 && Character.isDigit(tox.charAt(1))) {
                                Pattern pattern3 = Pattern.compile("^[+|-]?[0-9]{1,10}$");
                                Pattern pattern4 = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
                                Matcher matcher3 = pattern3.matcher(tox);
                                Matcher matcher4 = pattern4.matcher(tox);
                                boolean result3 = matcher3.find();
                                boolean result4 = matcher4.find();
                                if (result3) {
                                    token = "TOKEN # " + t + " (   CP => Integer Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Integer Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else if (result4) {
                                    token = "TOKEN # " + t + " (   CP => Float/Double Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Float/Double Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else {
                                    token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Invalid Token");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                }
                            } else if (tox.charAt(0) == '-' && tox.length() >= 2 && Character.isDigit(tox.charAt(1))) {
                                Pattern pattern3 = Pattern.compile("^[+|-]?[0-9]{1,10}$");
                                Pattern pattern4 = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
                                Matcher matcher3 = pattern3.matcher(tox);
                                Matcher matcher4 = pattern4.matcher(tox);
                                boolean result3 = matcher3.find();
                                boolean result4 = matcher4.find();
                                if (result3) {
                                    token = "TOKEN # " + t + " (   CP => Integer Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Integer Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else if (result4) {
                                    token = "TOKEN # " + t + " (   CP => Float/Double Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Float/Double Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                } else {
                                    token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Invalid Token");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                }
                            } else if (String.valueOf(tox.charAt(0)).equals("'")) {
                                Pattern pattern5 = Pattern.compile("^.$");
                                if (tox.length() == 3) {
                                    Matcher matcher5 = pattern5.matcher(String.valueOf(tox.charAt(1)));
                                    boolean result5 = matcher5.find();
                                    if (result5) {
                                        token = "TOKEN # " + t + " (   CP => Character Constant   VP => " + tox.charAt(1) + "      Line # " + looper1 + "      )";
                                        classPart.add("Character Constant");
                                        valuePart.add(tox);
                                        lineNumber.add(String.valueOf(looper1));
                                        //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                        Token.add(token);
                                    }
                                } else if (tox.length() == 4) {
                                    if (tox.charAt(1) == '\\' && tox.charAt(2) == 'n') {
                                        token = "TOKEN # " + t + " (   CP => Newline Character   VP => " + tox.charAt(1) + tox.charAt(2) + "      Line # " + looper1 + "      )";
                                        classPart.add("Newline Character");
                                        valuePart.add(tox);
                                        lineNumber.add(String.valueOf(looper1));
                                        //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                        Token.add(token);
                                    } else if (tox.charAt(1) == '\\' && tox.charAt(2) == 't') {
                                        token = "TOKEN # " + t + " (   CP => Horizontal Tab   VP => " + tox.charAt(1) + tox.charAt(2) + "      Line # " + looper1 + "      )";
                                        classPart.add("Horizontal Tab");
                                        valuePart.add(tox);
                                        lineNumber.add(String.valueOf(looper1));
                                        //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                        Token.add(token);
                                    } else if (tox.charAt(1) == '\\' && tox.charAt(2) == 'v') {
                                        token = "TOKEN # " + t + " (   CP => Vertical Tab   VP => " + tox.charAt(1) + tox.charAt(2) + "      Line # " + looper1 + "      )";
                                        classPart.add("Vertical Tab");
                                        valuePart.add(tox);
                                        lineNumber.add(String.valueOf(looper1));
                                        //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                        Token.add(token);
                                    } else {
                                        token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                        classPart.add("Invalid Token");
                                        valuePart.add(tox);
                                        lineNumber.add(String.valueOf(looper1));
                                        //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                        Token.add(token);
                                    }
                                } else {
                                    token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Invalid Token");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                }
                            } else if (String.valueOf(tox.charAt(0)).equals("\"")) {
                                String Fox = "";
                                for (int n = 1; n < tox.length() - 1; n++) {
                                    Fox += tox.charAt(n);
                                }
                                Pattern pattern6 = Pattern.compile("^[a-zA-Z0-9]*(.)*$");
                                Matcher matcher6 = pattern6.matcher(Fox);
                                boolean result6 = matcher6.find();
                                if (result6) {
                                    token = "TOKEN # " + t + " (   CP => String Constant   VP => " + Fox + "      Line # " + looper1 + "      )";
                                    classPart.add("String Constant");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                } else {
                                    token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + Fox + "      Line # " + looper1 + "      )";
                                    classPart.add("Invalid Token");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                }
                                Token.add(token);
                            } else if (tox.equals("true") || tox.equals("false")) {
                                token = "TOKEN # " + t + " (   CP => Boolean Constant   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Boolean Constant");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("++")) {
                                token = "TOKEN # " + t + " (   CP => Increment Operator  VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Increment Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("--")) {
                                token = "TOKEN # " + t + " (   CP =>  Decrement Operator   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Decrement Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("*") || tox.equals("/") || tox.equals("%")) {
                                token = "TOKEN # " + t + " (   CP => Multiply-Divide-Modulus Operator   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Multiply-Divide-Modulus Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("=") || tox.equals("+=") || tox.equals("*=") || tox.equals("/=") || tox.equals("-=") || tox.equals("%=")) {
                                token = "TOKEN # " + t + " (   CP => Assignment Operator   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Assignment Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("!")) {
                                token = "TOKEN # " + t + " (   CP => Not   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Not");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("break") || tox.equals("continue")) {
                                token = "TOKEN # " + t + " (   CP => Break-Continue   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Break-Continue");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("public") || tox.equals("private") || tox.equals("protected")) {
                                token = "TOKEN # " + t + " (   CP => Access Modifier   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Access Modifier");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("main")) {
                                token = "TOKEN # " + t + " (   CP => Special Identifier   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Special Identifier");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("<") || tox.equals(">") || tox.equals("<=") || tox.equals(">=") || tox.equals("==") || tox.equals("!=")) {
                                token = "TOKEN # " + t + " (   CP => Relational Operator   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Relational Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("&&") || tox.equals("||")) {
                                token = "TOKEN # " + t + " (   CP => Logical Operator   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Logical Operator");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("short") || tox.equals("int") || tox.equals("long") || tox.equals("float") || tox.equals("double") || tox.equals("String") || tox.equals("char")) {
                                token = "TOKEN # " + t + " (   CP => DataType   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("DataType");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals(";") || tox.equals(":") || tox.equals(",") || tox.equals("(") || tox.equals(")") || tox.equals("{") || tox.equals("}") || tox.equals("[") || tox.equals("]")) {
                                token = "TOKEN # " + t + " (   CP => " + tox + "   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add(tox);
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals(".") || tox.equals("for") || tox.equals("void") || tox.equals("fn") || tox.equals("def") || tox.equals("extends") || tox.equals("while") || tox.equals("if") || tox.equals("else") || tox.equals("switch") || tox.equals("case") || tox.equals("default")) {
                                token = "TOKEN # " + t + " (   CP => " + tox + "   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add(tox);
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("return") || tox.equals("null") || tox.equals("print") || tox.equals("override") || tox.equals("Arraylist") || tox.equals("Linkedlist") || tox.equals("virtual") || tox.equals("class") || tox.equals("extend") || tox.equals("interface") || tox.equals("implement")) {
                                token = "TOKEN # " + t + " (   CP => " + tox + "   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add(tox);
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (tox.equals("static") || tox.equals("abstract") || tox.equals("implements") || tox.equals("final") || tox.equals("new") || tox.equals("this") || tox.equals("try") || tox.equals("catch") || tox.equals("finally")) {
                                token = "TOKEN # " + t + " (   CP => " + tox + "   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add(tox);
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            } else if (Character.isLetter(tox.charAt(0))) {
                                Pattern pattern7 = Pattern.compile("^([a-z]+[0-9]*){1,63}$", Pattern.CASE_INSENSITIVE);
                                Matcher matcher7 = pattern7.matcher(tox);
                                boolean result7 = matcher7.find();
                                if (result7) {
                                    token = "TOKEN # " + t + " (   CP => Identifier   VP => " + tox + "      Line # " + looper1 + "      )";
                                    classPart.add("Identifier");
                                    valuePart.add(tox);
                                    lineNumber.add(String.valueOf(looper1));
                                    //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                    Token.add(token);
                                }
                            } else {
                                token = "TOKEN # " + t + " (   CP => Invalid Token   VP => " + tox + "      Line # " + looper1 + "      )";
                                classPart.add("Invalid Token");
                                valuePart.add(tox);
                                lineNumber.add(String.valueOf(looper1));
                                //token = "TOKEN # " + t + " : " + classPart.get(x) + " " + valuePart.get(x) + " " + lineNumber.get(x);
                                Token.add(token);
                            }
                            System.out.println(Token.get(h));
                            h++;
                            byte[] dataBytes = token.getBytes(); // Converts the string into bytes
                            out.write(dataBytes);   // Writes data to the output stream
                        }
                    } else {
                        System.out.println("NO TOKEN FOR THIS LINE");
                    }
                    temp.clear();
                    looper1++;
                }
            }
            out.close();    // Closes the output stream
            sc.close();    //closes the stream and release the resources
            System.out.println("Contents of File: ");
            if (classPart.size() == valuePart.size()) {
                if (valuePart.size() == lineNumber.size()) {
                    if (classPart.size() == lineNumber.size()) {
                        System.out.println(classPart);
                        System.out.println(valuePart);
                        System.out.println(lineNumber);
                    }
                }
            }
            System.out.println(brokenword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
