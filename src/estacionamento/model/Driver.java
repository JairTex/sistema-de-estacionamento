package estacionamento.model;

public class Driver {
	private String name;
	private String document;
	private String phone;
	
	public Driver(String name, String document, String phone) {
		this.name = name;
		this.document = document;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "-------- Informações do Motorista --------"
				+ "\nNome: " + this.name
				+ "\nCPF: " + this.document 
				+ "\nContato: " + this.phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
