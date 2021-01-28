package banco_superior.modelo;

import java.io.Serializable;

import banco_superior.excecao.ContaDesativadaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.SaldoInsuficienteException;
import banco_superior.excecao.ValorInvalidoException;

public class ContaSalario implements IConta, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String numeroConta;
	String agencia;
	private float saldo;
	boolean status;
	String dataAbertura;
	
	public ContaSalario(String numeroConta, String agencia, String dataAbertura) {
		
		this.numeroConta = numeroConta;
		this.dataAbertura = dataAbertura;
		this.saldo = 0f;
		this.status = true;
		this.agencia = agencia;
		
	}
	
	@Override
	public void depositar(float valorDepositado) throws ValorInvalidoException, ContaDesativadaException, ErroInesperadoException {
		if (valorDepositado>0 && this.status)
		{
			this.saldo += valorDepositado;
		}
		else if(valorDepositado <= 0)
		{
			throw new ValorInvalidoException("Operação inválida, valor insuficiente para deposito");
		}
		else if(this.status == false)
		{
			throw new ContaDesativadaException("Operação inválida, a conta está desativada");
		}
		else 
		{
			throw new ErroInesperadoException("Não foi possível concluira a operação");
		}
	}

		

	@Override
	public void desativarConta() 
	{
		this.status = false;
		
	}

	@Override
	public void ativarConta() 
	{
		this.status = true;
		
	}

	@Override
	public void sacar(float valorSacado) throws SaldoInsuficienteException {
		if(valorSacado > 0 && this.status && 
				this.saldo >= valorSacado) {
			this.saldo -= valorSacado;
		}
		else if(this.saldo < valorSacado ) {
			throw new SaldoInsuficienteException("Saldo insuficiente para saque");
		}
			
	}

	@Override
	public void transferencia(IConta contaDestino, float valorTransferido) throws ErroInesperadoException
	{
		throw new ErroInesperadoException("Esse tipo de conta não efetua transferência");	
	}

	@Override
	public String toString() {
		return "contaSalario [numeroConta=" + numeroConta + ", agencia=" + agencia + ", saldo=" + saldo + ", status="
				+ status + ", dataAbertura=" + dataAbertura + "]";
	}
	
}
