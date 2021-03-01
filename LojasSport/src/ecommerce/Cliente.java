package ecommerce;

public class Cliente {
	private String nome;
	private char genero;

	public String tratamento(char genero) {
		String apelido;

		if (genero == 'F') {
			apelido = "Sra.";
		} else if (genero == 'M') {
			apelido = "Sr.";
		} else if (genero == 'X'){
			apelido = "Srx.";
		} else {
			apelido = "";
		}
		return apelido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

}
