package ecommerce;

public class Produto {
	
	private int codigo;
	private String descricao;
	private int estoque;
	private double preco;
	
	public Produto() {
		
	}
	
	public Produto(int codigo, String descricao, int estoque, double preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.estoque = estoque;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void atualizarEstoque(int quantidade) {
		this.estoque -= quantidade;
	}
	
	
	
}
