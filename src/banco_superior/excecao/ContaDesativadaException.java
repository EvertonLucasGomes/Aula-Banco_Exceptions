package banco_superior.excecao;

public class ContaDesativadaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaDesativadaException(String msg)
	{
		super(msg);	
	}

}
