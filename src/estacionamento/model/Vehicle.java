package estacionamento.model;

public class Vehicle {
	private String numberPlate;
	private String brand;
	private String model;
	private String color;
	private Driver driver;
	
	public Vehicle(String numberPlate, String brand, String model, String color, Driver driver) {
		this.numberPlate = numberPlate;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.driver = driver;
	}
	
	@Override
	public String toString() {
		return "-------- Informações do Veiculo --------"
				+ "\nPlaca: " + numberPlate 
				+ "\nMarca: " + brand 
				+ "\nModelo: " + model 
				+ "\nCor: " + color 
				+ "\nCondutor:" + driver.getName();
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
