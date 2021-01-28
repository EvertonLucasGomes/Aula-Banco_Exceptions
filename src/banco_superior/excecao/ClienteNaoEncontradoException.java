package banco_superior.excecao;

public class ClienteNaoEncontradoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String msg) 
	{
		super(msg);
	}
}
