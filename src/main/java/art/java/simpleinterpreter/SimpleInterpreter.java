package art.java.simpleinterpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.TreeMap;

 /**
 *  Interpreter for Small Basic
 *	
 *	Murzabekov Almaz
 *	e-mail: muktaalmaz@yandex.ru
 */   
public class SimpleInterpreter {
	final int PROG_SIZE = 10000;
	
	// Type of tokens
	final int NONE = 0;
	final int DELIMITER = 1;
	final int VARIABLE = 2;
	final int NUMBER = 3;
	final int COMMAND = 4;
	final int QUOTEDSTR = 5;
	
	// Type of exceptions
	final int SYNTAX = 0;
	final int UNBALPARENS = 1;
	final int NOEXP = 2;
	final int DIVBYZERO = 3;
	final int EQUALEXPECTED = 4;
	final int NOTVAR = 5;
	final int LABELTABLEFULL = 6;
	final int DUPLABEL = 7;
	final int UNDEFLABLE = 8;
	final int THENEXPECTED = 9;
	final int TOEXPECTED = 10;
	final int NEXTWITHOUTFOR = 11;
	final int RETURNWITHOUTGOSUB = 12;
	final int MISSINGQUOTE = 13;
	final int FILENOTFOUND = 14;
	final int FILEIOERROR = 15;
	final int INPUTIOERROR = 16;
	
	// Keywords
	final int UNKNOW = 0;
	final int PRINT = 1;
	final int INPUT = 2;
	final int IF = 3;
	final int THEN = 4;
	final int FOR = 5;
	final int NEXT = 6;
	final int TO = 7;
	final int GOTO = 8;
	final int GOSUB = 9;
	final int RETURN = 10;
	final int END = 11;
	final int EOL = 12;
	// Keyword end of program
	final String EOP = "\0";
	
	final char LE = 1;
	final char GE = 2;
	final char NE = 3;
	
	private double[] vars;
	class Keyword{
		String keyword;
		int keywordTok;
		
		Keyword(String keyword, int keywordTok){
			this.keyword = keyword;
			this.keywordTok = keywordTok;
		}
	}
	
	Keyword[] keyWordTable = {
		new Keyword("����������", this.PRINT),
		new Keyword("������", this.INPUT),
		new Keyword("����", this.IF),
		new Keyword("�����", this.THEN),
		new Keyword("����", this.GOTO),
		new Keyword("��", this.FOR),
		new Keyword("������", this.NEXT),
		new Keyword("��", this.TO),
		new Keyword("������������", this.GOSUB),
		new Keyword("�������", this.RETURN),
		new Keyword("�����", this.END)
	};
	
	private char[] prog;
	private int progIndex;
	private String token;
	private int tokType;
	private int kwToken;
	
	class ForInfo{
		int var;
		double target;
		int loc;
	}
	private Stack<Object> fStack;
	
	class Label{
		String name;
		int loc;
		public Label(String name, int index){
			this.loc = index;
			this.name = name;
		}
	}
	private TreeMap<Object, Object> labelTable;
	private Stack<Object> gStack;
	
	char rops[] = {
		this.GE, this.NE, this.LE, '<', '>', '=', 0
	};
	String relops = new String(rops);
	
	public SimpleInterpreter(String progName) throws InterpreterException{
		char tempbuf[] = new char[this.PROG_SIZE];
		int size = loadProgram(tempbuf, progName);
		if(size != -1){
			this.prog = new char[size];
			System.arraycopy(tempbuf, 0, this.prog, 0, size);
		}
	}

	private int loadProgram(char[] tempbuf, String progName) throws InterpreterException {
		int size = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader(progName);
			br = new BufferedReader(fr);
			
			size = br.read(tempbuf, 0, this.PROG_SIZE);
		} catch(FileNotFoundException ex){
			handleErr(this.FILENOTFOUND);
		}  catch(IOException ex){
			handleErr(this.FILEIOERROR);
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
		if(tempbuf[size -1] == (char)26)
			size--;
		return size;
	}
	public void run() throws InterpreterException{
		this.vars = new double[26];
		this.fStack = new Stack<>();
		this.labelTable = new TreeMap<>();
		this.gStack = new Stack<>();
		this.progIndex = 0;
		this.scanLabels();
		this.sbInterp();
	}
	private void scanLabels() throws InterpreterException {
		Object result;
		
		getToken();
		if(this.tokType == NUMBER)
			this.labelTable.put(token, new Integer(this.progIndex));
		findEOL();
		do{
			getToken();
			if(this.tokType == NUMBER){
				result = this.labelTable.put(token, new Integer(this.progIndex));
				if(result != null)
					handleErr(this.DUPLABEL);
			}
			
			if(kwToken != EOL)
				findEOL();
		}while(!token.equals(EOP));
		progIndex = 0;
	}
	private void findEOL() throws InterpreterException {
		while(progIndex < prog.length && 
				prog[progIndex] != '\n')
			++progIndex;
		if(progIndex < prog.length)
			progIndex++;
	}
	private void sbInterp() throws InterpreterException {
		do{
			getToken();
			if(this.tokType == VARIABLE){
				putBack();
				assignment();
			} else{
				switch(this.kwToken){
				case PRINT: print(); break;
				case GOTO: execGoto(); break;
				case IF: execIf(); break;
				case FOR: execFor(); break;
				case NEXT: next(); break;
				case INPUT: input(); break;
				case GOSUB: goSub(); break;
				case RETURN: gReturn(); break;
				case END: return;
				}
			}
		} while(!this.token.equals(this.EOP));
	}
	private void assignment() throws InterpreterException {
		int var;
		double value;
		char vname;
		
		getToken();
		vname = token.charAt(0);
		if(!Character.isLetter(vname)){
			handleErr(NOTVAR);
			return;
		}
		
		var = (int) Character.toUpperCase(vname) - 'A';
		getToken();
		if(!token.equals("=")){
			handleErr(EQUALEXPECTED);
			return;
		}
		
		value = evaluate();
		vars[var] = value;
	}
	
 	private void print()throws InterpreterException {
		double result;
		int len = 0;
		int spaces;
		String lastDelim = "";
		do{
			getToken();
			if(kwToken == EOL || token.equals(EOP))
				break;
			if(tokType == QUOTEDSTR){
				System.out.print(token);
				len += token.length();
				getToken();
			} else{
				putBack();
				result = evaluate();
				getToken();
				System.out.print(result);
				
				Double t = new Double(result);
				len += t.toString().length();
			}
			lastDelim = token;
			
			if(lastDelim.equals(",")){
				spaces = 8 - (len % 8);
				len += spaces;
				while(spaces != 0){
					System.out.print(" ");
					spaces--;
				}
			} else if (token.equals(";")){
				System.out.print(" ");
				len++;
			} else if(kwToken != EOL && !token.equals(EOP))
				handleErr(SYNTAX);
		} while(lastDelim.equals(";") || lastDelim.equals(","));
		if(kwToken == EOL || token.equals(EOP)){
			if(!lastDelim.equals(";") && !lastDelim.equals(","))
				System.out.println();
		} else
			handleErr(SYNTAX);
	}
	private void execGoto()throws InterpreterException { 
		Integer loc;
		getToken();
		
		loc = (Integer) labelTable.get(token);
		if(loc == null)
			handleErr(UNDEFLABLE);
		else
			progIndex = loc.intValue();
	}
	private void execIf() throws InterpreterException {
		double result = evaluate();
		if(result != 0.0){
			getToken();
			if(kwToken != THEN){
				handleErr(THENEXPECTED);
				return;
			}
		} else findEOL();
	}
	private void execFor() throws InterpreterException {
		ForInfo stckvar = new ForInfo();
		double value;
		char vname;
		getToken();
		vname = token.charAt(0);
		if(!Character.isLetter(vname)){
			handleErr(NOTVAR);
			return;
		}
		stckvar.var = Character.toUpperCase(vname) - 'A';
		getToken();
		if(token.charAt(0) != '='){
			handleErr(EQUALEXPECTED);
			return;
		}
		value = evaluate();
		vars[stckvar.var] = value;
		getToken();
		if(kwToken != TO)
			handleErr(TOEXPECTED);
		stckvar.target = evaluate();
		if(value >= vars[stckvar.var]){
			stckvar.loc = progIndex;
			fStack.push(stckvar);
		} else
			while(kwToken != NEXT) 
				getToken();
 	}
	private void next() throws InterpreterException { 
		ForInfo stckvar;
		try{
			stckvar = (ForInfo)fStack.pop();
			vars[stckvar.var]++;
			
			if(vars[stckvar.var] > stckvar.target)
				return;
			fStack.push(stckvar);
			progIndex = stckvar.loc;
		} catch(EmptyStackException ex){
			handleErr(NEXTWITHOUTFOR);
		}
	}
	private void input() throws InterpreterException {
		int var;
		double val = 0.0;
		String str;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		getToken();
		if(tokType == QUOTEDSTR){
			System.out.print(token);
			getToken();
			if(!token.equals(","))
				handleErr(SYNTAX);
			getToken();
		} else 
			System.out.print("? ");
		var = Character.toUpperCase(token.charAt(0)) - 'A';
		try{
			str = br.readLine();
			val = Double.parseDouble(str); 
		} catch(NumberFormatException e){
			System.err.println("INVALID INPUT ERROR");
		} catch(IOException e){
			handleErr(INPUTIOERROR);
		}
		vars[var] = val;
	}
	private void goSub() throws InterpreterException {
		Integer loc;
		getToken();
		loc = (Integer) labelTable.get(token);
		if(loc == null)
			handleErr(UNDEFLABLE);
		else{
			gStack.push(new Integer(progIndex));
			progIndex = loc.intValue();
		}
	}
	private void gReturn() throws InterpreterException {
		Integer t;
		try{
			t = (Integer) gStack.pop();
			progIndex = t.intValue();		
		} catch(EmptyStackException e){
			handleErr(RETURNWITHOUTGOSUB);
		}
	}
	private double evaluate() throws InterpreterException {
		double result = 0.0;
		getToken();
		if(token.equals(EOP))
			handleErr(NOEXP);
		result = evalExp1();
		putBack();
		return result;
	}
	private double evalExp1() throws InterpreterException {
		double l_temp, r_temp, result;
		char op;
		result = evalExp2();
		if(token.equals(EOP)) 
			return result;
		op = token.charAt(0);
		if(isRelop(op)){
			l_temp = result;
			getToken();
			r_temp = evalExp1();
			switch(op){
			case '<': 
				result = (l_temp < r_temp ? 1.0 : 0.0); break;
			case LE:
				result = (l_temp <= r_temp ? 1.0 : 0.0); break;
			case '>':
				result = (l_temp > r_temp ? 1.0 : 0.0); break;
			case GE:
				result = (l_temp >= r_temp ? 1.0 : 0.0); break;
			case '=':
				result = (l_temp == r_temp ? 1.0 : 0.0); break;
			case NE:
				result = (l_temp != r_temp ? 1.0 : 0.0); break;
			}
		}
		return result;
	}
	private double evalExp2() throws InterpreterException {
		char op;
		double result, partialResult;
		result = evalExp3();
		while((op = token.charAt(0)) == '+' ||
				op == '-'){
			getToken();
			partialResult = evalExp3();
			switch(op){
			case '-':
				result -= partialResult; break;
			case '+':
				result += partialResult; break;
			}
		}
		
		return result;
	}
	private double evalExp3() throws InterpreterException {
		char op;
		double result, partialResult;
		result = evalExp4();
		while((op = token.charAt(0)) == '*' ||
				op == '/' || op == '%'){
			getToken();
			partialResult = evalExp4();
			switch(op){
			case '*':
				result *= partialResult; break;
			case '/':
				if(partialResult == 0.0)
					handleErr(DIVBYZERO);
				result /= partialResult; break;
			case '%':
				if(partialResult == 0.0)
					handleErr(DIVBYZERO);
				result %= partialResult; break;
			}
		}
		return result;
	}
	private double evalExp4() throws InterpreterException { 
		double result;
		double partialResult;
		double exp;
		int t;
		result = evalExp5();
		if(token.equals("^")){
			getToken();
			partialResult = evalExp4();
			exp = result;
			if(partialResult == 0.0){
				result = 1.0;
			} else{
				for (t = (int)partialResult -1; t > 0; t--)
					result *= exp;
			}
		}
		return result;
	}
	private double evalExp5() throws InterpreterException {
		double result;
		String op;
		op = "";
		if((tokType == DELIMITER) && 
				token.equals("+") ||
				token.equals("-")){
			op = token;
			getToken();
		}
		result = evalExp6();
		if(op.equals("-")) 
			result = -result;
		return result;
	}
	private double evalExp6() throws InterpreterException {
		double result;
		if(token.equals("(")){
			getToken();
			result = evalExp2();
			if(!token.equals(")"))
				handleErr(UNBALPARENS);
			getToken();
		} else
			result = atom();
		return result;
	}
	
	private double atom() throws InterpreterException{
		double result = 0.0;
		switch(tokType){
		case NUMBER:
			try{
				result = Double.parseDouble(token);
			} catch(NumberFormatException e){
				handleErr(SYNTAX);
			}
			getToken();
			break;
		case VARIABLE:
			result = findVar(token);
			getToken();
			break;
		default:
				handleErr(SYNTAX);
		}
		return result;
	}
	private double findVar(String vname) throws InterpreterException {
		if(!Character.isLetter(vname.charAt(0))){
			handleErr(SYNTAX);
			return 0.0;
		}
		int index = Character.toUpperCase(vname.charAt(0)) - 'A';
		return vars[index];
	}
	private void putBack(){
		if(token == EOP)
			return;
		for (int i = 0; i < token.length(); i++) {
			progIndex--;
		}
	}
	private void handleErr(int errorCode) throws InterpreterException{
		String[] err = {
				"�������������� ������",
				"�� ����������� ����������� � ����������� ������",
				"No expression present",
				"������� �� ����",
				"��� ����� ���������",
				"��������� ����������",
				"Label table full",
				"Duplicate label",
				"Undefined label",
				"THEN expected",
				"TO expected",
				"NEXT expected",
				"RETURN without GOSUB",
				"Closing quotes needed",
				"���� �� ������", 
				"IO - ������ ��� ���������� ������ �� �����",
				"IO - ������ ��� INPUT �����"
		};
		throw new InterpreterException(err[errorCode]);
	}
	public void getToken() throws InterpreterException {
		char ch;
		tokType = NONE;
		token = "";
		kwToken = UNKNOW;
		
		if(progIndex == prog.length){
			token = EOP;
			return;
		}
		
		while(progIndex < prog.length &&
				isSpaceOrTab(prog[progIndex]))
			progIndex++;
		if(progIndex == prog.length){
			token = EOP;
			tokType = DELIMITER;
			return;
		}
		if(prog[progIndex] =='\r'){
			progIndex += 2;
			kwToken = EOL;
			token = "\r\n";
			return;
		}
		
		ch = prog[progIndex];
		if(ch == '<' || ch == '>'){
			if(progIndex + 1 == prog.length)
				handleErr(SYNTAX);
			switch(ch){
			case '<':
				if(prog[progIndex + 1] == '>'){
					progIndex += 2;
					token = String.valueOf(NE);
				} else if(prog[progIndex + 1] == '='){
					progIndex += 2;
					token = String.valueOf(LE);
				} else {
					progIndex++;
					token = "<";
				} break;
			case '>':
				if(prog[progIndex +1] == '='){
					progIndex += 2;
					token = String.valueOf(GE);
				} else {
					progIndex++;
					token = ">";
				} break;
			}
			tokType = DELIMITER;
			return;
		}
		if(isDelim(prog[progIndex])){
			token += prog[progIndex];
			progIndex++;
			tokType = DELIMITER;
		} else if(Character.isLetter(prog[progIndex])){
			while(!isDelim(prog[progIndex])){
				token += prog[progIndex];
				progIndex++;
				if(progIndex >= prog.length)
					break;
			}
			kwToken = lookUp(token);
			if(kwToken == UNKNOW)
				tokType = VARIABLE;
			else 
				tokType = COMMAND;
		} else if(Character.isDigit(prog[progIndex])){
			while(!isDelim(prog[progIndex])){
				token += prog[progIndex];
				progIndex++;
				if(progIndex >= prog.length)
					break;
			}
			tokType = NUMBER;
		} else if(prog[progIndex] == '"'){
			progIndex++;
			ch = prog[progIndex];
			while(ch != '"' && ch != '\r'){
				token += ch;
				progIndex++;
				ch = prog[progIndex];
			}
			if(ch == '\r')
				handleErr(MISSINGQUOTE);
			progIndex++;
			tokType = QUOTEDSTR;
		} else {
			token = EOP;
			return;
		}	
	}

	private int lookUp(String token) {
		token = token.toLowerCase();
		
		for (int j = 0; j < keyWordTable.length; j++) {
			if(keyWordTable[j].keyword.equals(token))
				return keyWordTable[j].keywordTok;
		}
		return UNKNOW;
	}

	private boolean isSpaceOrTab(char c) {
		return c == ' ' || c == '\t';
	}
	private boolean isDelim(char c) {
		return " \r,;<>+-/*%^=()".indexOf(c) != -1;
	}
	private boolean isRelop(char c){
		return relops.indexOf(c) != -1;
	}	
}