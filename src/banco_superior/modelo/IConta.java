package banco_superior.modelo;

import banco_superior.excecao.ContaDesativadaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.SaldoInsuficienteException;
import banco_superior.excecao.ValorInvalidoException;

public interface IConta {

	static final float TAXA_SACAR_CONTA_CORRENTE = 0.01f;
	static final float TAXA_SACAR_CONTA_POUPANCA = 0.02f;
	static final float TAXA_SACAR_CONTA_INVESTIOMENTO = 0.03f;
	static final float RENDIMENTO_CONTA_POUPANCA = 0.03f;
	static final float TAXA_ADM_CONTA_INVESTIMENTO = 0.05f;
	static final float TAXA_ADM_CONTA_POUPANCA = 0.04f;
	static final float TAXA_ADM_CONTA_CORRENTE = 0.035f;
	static final float TAXA_ADM_CONTA_SALARIO = 0.015f;
	

	public void depositar(float valorDepositado)throws ValorInvalidoException, ContaDesativadaException, ErroInesperadoException ;
	
	public void desativarConta();
	
	public void ativarConta();
	
	public void sacar(float valorSacado) throws SaldoInsuficienteException;
	
	public void transferencia(IConta contaDestino, float valorTransferido)throws SaldoInsuficienteException, ValorInvalidoException, ContaDesativadaException, ErroInesperadoException;
	
	public String toString();
	
	
	
	
}
