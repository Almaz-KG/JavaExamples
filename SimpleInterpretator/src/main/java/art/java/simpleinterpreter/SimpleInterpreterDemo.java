package art.java.simpleinterpreter;

public class SimpleInterpreterDemo {
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Oooooops! There are no arguments");
			return;
		}
		
		try{
			SimpleInterpretator interpreter = 
					new SimpleInterpretator(args[0]);
			interpreter.run();
		} catch(InterpreterException e){
			System.err.println(e.getMessage());
		}
	}
}
