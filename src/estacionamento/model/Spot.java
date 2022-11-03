package estacionamento.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Spot {
	private String number;
	private Calendar checkInTime;
	private Calendar checkOutTime;
	private Double amount;
	private Vehicle vehicle;
	private Driver driver;
	
	
	public Spot(String number, Vehicle vehicle) {
		this.number = number;
		this.checkInTime = Calendar.getInstance();
		this.vehicle = vehicle;
		this.driver = vehicle.getDriver();
	}

	@Override
	public String toString() {
		return "-------- Informações da vaga --------"
				+ "\nVaga: " + number 
				+ "\nEntrada: " + getCurrentDateFormatted(checkInTime)
				+ "\nSaida: " + getCurrentDateFormatted(checkOutTime)
				+ "\nVeiculo: " + vehicle.getNumberPlate()
				+ "\nCondutor: " + driver.getName();
	}
	
	public String getCurrentDateFormatted(Calendar date) {	
		if (date == null) {
			return "Indefinido";
		}
		
		SimpleDateFormat formatted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return formatted.format(date.getTime());
	}
	
	public String getAmountFormatted () {
		return new DecimalFormat("#,##0.00").format(this.amount);
	}
	
	public Double getAmountFormattedToDouble () {
		return Double.parseDouble(this.getAmountFormatted().replace("," , "."));
	}
	
	public void checkOut() {
		this.setCheckOutTime(Calendar.getInstance());
	}
	
	public void calcAmout(Parking parking , double hourValue) {
		if(this.checkOutTime.get(this.checkOutTime.MINUTE) < this.checkInTime.get(this.checkInTime.MINUTE)) {
			int totalMinutes = 
					//calculating the total minutes
					((this.getCheckOutTime().get(this.getCheckOutTime().MINUTE) + 60) 
							- this.getCheckInTime().get(this.getCheckInTime().MINUTE))
					//Adding minutes from hours
					+ (60 * ((this.getCheckOutTime().get(this.getCheckOutTime().HOUR) -1) 
							- this.getCheckInTime().get(this.getCheckInTime().HOUR))); 
					
			//double valueMinutes = parking.getHourValue() / 60;
			
			this.setAmount(totalMinutes * (parking.getHourValue() / 60));
			
		}else{
			int totalMinutes = 
					//calculating the total minutes
					((this.getCheckOutTime().get(this.getCheckOutTime().MINUTE)) 
							- this.getCheckInTime().get(this.getCheckInTime().MINUTE))
					//Adding minutes from hours
					+ (60 * ((this.getCheckOutTime().get(this.getCheckOutTime().HOUR)) 
							- this.getCheckInTime().get(this.getCheckInTime().HOUR))); 
			
			//double valueMinutes = parking.getHourValue() / 60;
			
			this.setAmount(totalMinutes * (parking.getHourValue() / 60));
		}
	}
	
	/*ATRIBUTOS : GETTERS AND SETTERS*/
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Calendar getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Calendar checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Calendar getCheckOutTime() {
		return checkOutTime;
	}

	private void setCheckOutTime(Calendar checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	private void setAmount(Double amount) {
		this.amount = amount;
	}
}
