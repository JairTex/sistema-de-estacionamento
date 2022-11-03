package estacionamento.model;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parking {
	private double hourValue = 50;
	private int totalVehicleOperation = 0;
	private Double totalAmountOperation = 0.;
	private String operationStatement = "";
	private LinkedHashMap<String, Spot> parkingList = generateParking();
	
	public Parking(double hourValue) {
		this.hourValue = hourValue;
	}

	public LinkedHashMap<String, Spot> generateParking() {
		LinkedHashMap<String, Spot> parking = new LinkedHashMap<String, Spot>();
	
		for(int i = 1; i <= 50; i++) {
			if(i < 10) {
				String key = "A0" + i;
				parking.put(key, null);
			}else if(i == 10) {
				String key = "A" + i;
				parking.put(key, null);
			}else if(i > 10 && i<=20) {
				String key = "B" + i;
				parking.put(key, null);
			}else if(i > 20 && i<=30) {
				String key = "C" + i;
				parking.put(key, null);
			}else if(i > 30 && i<=40) {
				String key = "D" + i;
				parking.put(key, null);
			}else if(i > 40 && i<=50) {
				String key = "E" + i;
				parking.put(key, null);
			}
		}
		
		return parking;
	}
	
	public void enterParking(Scanner sc) {		
		System.out.println("---- FORMULARIO DE ENTRADA ----");
		
		String numberPlate = "";
		String numberPlateRegex = "^([A-Z]{3}[0-9]{4})$|^([A-Z]{3}[0-9][A-Z][0-9]{3})$";
		Pattern numberPlatePattern = Pattern.compile(numberPlateRegex);
		
		boolean numberPlateAccepted = false;
		while(numberPlateAccepted == false) {
			System.out.println("Número da placa (Ex: ABC1234): ");
			numberPlate = sc.nextLine().toUpperCase().trim(); 
			Matcher match = numberPlatePattern.matcher(numberPlate);
			if(match.find() == true) {
				numberPlateAccepted = true;
			}else {
				System.out.println("Placa inválida!");
			}
		}
		
		System.out.println("Marca: ");
		String brand = sc.nextLine().toUpperCase().trim(); 
		
		System.out.println("Modelo: ");
		String model = sc.nextLine().toUpperCase().trim(); 
		
		System.out.println("Cor do veículo: ");
		String color = sc.nextLine().toUpperCase().trim();
		
		String driverName = "";
		String driverNameRegex = "^[A-Z\\s]+$";
		Pattern driverNamePattern = Pattern.compile(driverNameRegex);
		
		boolean driverNameAccepted = false;
		while(driverNameAccepted == false) {
			System.out.println("Nome do condutor: ");
			driverName = sc.nextLine().toUpperCase().trim(); 
			Matcher match = driverNamePattern.matcher(driverName);
			if(match.find() == true) {
				driverNameAccepted = true;
			}else {
				System.out.println("Nome inválido!");
			}
		}
		
		String document = "";
		String documentRegex = "\\b([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})";
		Pattern documentPattern = Pattern.compile(documentRegex);
		
		boolean documentAccepted = false;
		while(documentAccepted == false) {
			System.out.println("CPF (Ex:123.456.789-10): ");
			document = sc.nextLine();
			Matcher match = documentPattern.matcher(document);
			if(match.find() == true) {
				documentAccepted = true;
			}else {
				System.out.println("CPF inválido!");
			}
		}
		
		String phone = "";
		String phoneRegex = "(^[0-9]{2}[ ][0-9]{5}\\-[0-9]{4})$|^([0-9]{2}[ ][0-9]{4}\\-[0-9]{4})$";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		
		boolean phoneAccepted = false;
		while(phoneAccepted == false) {
			System.out.println("Telefone (Ex: 99 99999-9999): ");
			phone = sc.nextLine();
			Matcher match = phonePattern.matcher(phone);
			if(match.find() == true) {
				phoneAccepted = true;
			}else {
				System.out.println("Número inválido!");
			}
		}
		
		System.out.print("\n");
	
		Spot spot = new Spot(this.getOneEmptyParking(), new Vehicle(numberPlate, brand, model, color, new Driver(driverName, document, phone)));
		this.parkingList.replace(spot.getNumber(), spot);
		System.out.println(spot.toString() + "\nValor da hora: " + this.getHourValueFormatted() + "\n");
	}
	

	
	public void leaveParking(Scanner sc) {
		System.out.println("Digite a placa do veiculo que está saindo: ");
		
		String numberPlate = sc.nextLine().toUpperCase().trim();
		
		int counter = 0;
		for (String key : this.parkingList.keySet()) {

            Spot spot = this.parkingList.get(key);
            
            if(spot != null && spot.getVehicle().getNumberPlate().equalsIgnoreCase(numberPlate.toUpperCase().trim())) {
            	spot.checkOut();
            	spot.calcAmout(this ,this.getHourValue());
            	
            	Spot tiket = spot;
            	
            	this.setTotalVehicleOperation(getTotalVehicleOperation() + 1);
            	this.setTotalAmountOperation(getTotalAmountOperation() + spot.getAmountFormattedToDouble());
            	this.addToOperationStatement(spot);
            	this.cleanSpot(spot);
            	
            	System.out.println("\n" + tiket.toString() + "\nValor total: " + spot.getAmountFormatted() + "\n");
            	
            	counter += 1;
            	break;
            }
            
		}
		if(counter == 0) {
			System.out.println("\nEsse veículo não existe!");
		}
	}
	
	public void searchByPlate(String numberPlate) {
		int counter = 0;
		for (String key : this.parkingList.keySet()) {

            Spot spot = this.parkingList.get(key);
            
            if(spot != null && spot.getVehicle().getNumberPlate().equalsIgnoreCase(numberPlate)) {         	
            	System.out.println("\nVeiculo estacionado: \n" + spot.toString() + "");
            	
            	counter += 1;
            	break;
            }
            
		}
		if(counter == 0) {
			System.out.println("\nEsse veículo não está estacionado!");
		}
	}	
	
	public void searchBySpot(String numberSpot){
		if(this.getParkingList().containsKey(numberSpot)) {
			try {
				System.out.println( "O veiculo " + this.getParkingList().get(numberSpot).getVehicle().getNumberPlate() + " está nessa vaga.\n");
			}catch(NullPointerException e) {
				System.out.println("A vaga " + numberSpot + " está vazia.\n");
			}
		}else {
			System.out.println("A vaga " + numberSpot + " não existe no estacionamento.\n");
		}
	}
	
	public void cleanSpot(Spot spot) {
		this.parkingList.replace(spot.getNumber(), null);
	}
	
	public void addToOperationStatement(Spot spot) {
		this.setOperationStatement(
				getOperationStatement() + "\n"
				+ spot.toString() + "\nValor total: " + spot.getAmountFormatted());
	}
	
	public void printOperationStatement() {
		System.out.println("\n--------- RELATORIO OPERACIONAL ---------\n" + getOperationStatement()
		+ "\n\nTotal de veiculos: " + this.getTotalVehicleOperation()
		+ "\nFaturamento Total: " + this.getTotalAmountOperationFormatted());
	}
	
	
	
	public String getTotalAmountOperationFormatted () {
		return new DecimalFormat("#,##0.00").format(this.getTotalAmountOperation());
	}
	
	public void getParkingListFormatted() {
		
		for (String key : this.parkingList.keySet()) {
            if(this.parkingList.get(key) == null) {
            	System.out.println(key + " ---> " + "desocupada");
            } else {
            	System.out.println(key + " ---> " + this.parkingList.get(key).getVehicle().getNumberPlate());
            }
            
		}
	}
	
	public void getParkingListMatrix() {
		int counter = 0;
		
		for (String key : this.parkingList.keySet()) {

            if(counter%10 != 0) {
            	if(this.parkingList.get(key) == null) {
                	System.out.print(key + " --> " + "desocupada" + "    ");
                } else {
                	System.out.print(key + " --> " + this.parkingList.get(key).getVehicle().getNumberPlate() + "    ");
                	
                }
            } else {
            	System.out.print("\n");
            	
            	if(this.parkingList.get(key) == null) {
                	System.out.print(key + " --> " + "desocupada" + "    ");
                } else {
                	System.out.print(key + " --> " + this.parkingList.get(key).getVehicle().getNumberPlate() + "    ");
                	
                }
            }
            counter++;
		}
		System.out.print("\n");
	}
	
	public void getEmptyParkingList() {
		for (String key : this.parkingList.keySet()) {
            if(this.parkingList.get(key) == null) {
            	System.out.println(key + " ---> " + "desocupada");
            }
		}
	}

	public void getBusyParkingList() {
		for (String key : this.parkingList.keySet()) {
	        if(this.parkingList.get(key) != null) {
	        	System.out.println(key + " ---> " + this.parkingList.get(key).getVehicle().getNumberPlate());
	        }
	}
	}
	
	public String getOneEmptyParking() {
		String numberPlate = ""; 
		for (String key : this.parkingList.keySet()) {
            if(this.parkingList.get(key) == null) {
            	numberPlate = key;
            	return numberPlate;
            }           
		}
		return numberPlate;
	}
	
	public String getHourValueFormatted () {
		return new DecimalFormat("#,##0.00").format(this.hourValue);
	}

	private int getTotalVehicleOperation() {
		return totalVehicleOperation;
	}

	private void setTotalVehicleOperation(int totalVehicleOperation) {
		this.totalVehicleOperation = totalVehicleOperation;
	}

	private Double getTotalAmountOperation() {
		return totalAmountOperation;
	}

	private void setTotalAmountOperation(Double totalAmountOperation) {
		this.totalAmountOperation = totalAmountOperation;
	}

	private String getOperationStatement() {
		return operationStatement;
	}

	private void setOperationStatement(String operationStatement) {
		this.operationStatement = operationStatement;
	}

	public LinkedHashMap<String, Spot> getParkingList() {
		return parkingList;
	}

	public double getHourValue() {
		return hourValue;
	}
	
}
