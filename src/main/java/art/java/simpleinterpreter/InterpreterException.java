package art.java.simpleinterpreter;

public class InterpreterException extends Exception{
	private static final long serialVersionUID = -8131141884035243804L;
	private String message;
	
	public InterpreterException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return this.message;
	}
}
