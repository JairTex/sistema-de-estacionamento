package estacionamento.main;

import java.util.Scanner;

import estacionamento.model.Parking;

public class Main {

	public static void main(String[] args) {
		Parking parking = new Parking(50.);
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		while(exit == false) {
			System.out.println(
					  " ---- Menu Principal ---- "
					+ "\n1 - Entrada"
					+ "\n2 - Saída"
					+ "\n3 - Relatórios"
					+ "\n4 - Buscar"
					+ "\n5 - Sair"
					+ "\nDigite uma opção(Ex.:1): ");
			
			String choice = sc.nextLine();
			
			switch (choice) {
			case "1": {
				if(parking.getOneEmptyParking() == "") {
					System.out.println("Estacionamento Cheio!");
				} else {
					System.out.println("\nVaga: " + parking.getOneEmptyParking());
					
					parking.enterParking(sc);
					
					System.out.println("Pressione ENTER...");
					sc.nextLine();
				}
				break;
			}
			case "2": {
				System.out.println("---- Carros Estacionados ----");
				
				parking.getBusyParkingList();
				
				parking.leaveParking(sc);
				
				System.out.println("Pressione ENTER...");
				sc.nextLine();
				
				break;
			}
			case "3": {
				System.out.println(
						"Selecione uma opcao:"
						+ "\n1 - Imprimir todas as vagas"
						+ "\n2 - Imprimir vagas ocupadas"
						+ "\n3 - Imprimir vagas desocupadas"
						+ "\n4 - Imprimir vagas em formato de matriz"
						+ "\n5 - Imprimir extrato operacional"
						+ "\nDigite uma opção(Ex.:1): ");
				String reportMenu = sc.nextLine();
				
				switch(reportMenu) {
				case "1":{
					System.out.println("\n---- Todas as Vagas ----\n");
					
					parking.getParkingListFormatted();
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					
					break;
				}
				case "2":{
					System.out.println("\n---- Vagas Ocupadas ----\n");
					
					parking.getBusyParkingList();
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					
					break;
				}
				case "3":{
					System.out.println("\n---- Vagas Desocupadas ----\n");
					
					parking.getEmptyParkingList();
					
					System.out.println("\nPress ENTER...");
					sc.nextLine();
					
					break;
				}
				case "4":{
					System.out.println("\n---- Vagas em formato de matriz ----");
					parking.getParkingListMatrix();
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					
					break;
				}
				case "5":{
					parking.printOperationStatement();
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					
					break;
				}
				default:
					System.out.println("Esta opção é Inválida: " + reportMenu);
				}
				
				break;
			}
			case "4": {
				System.out.println("\nSelecione uma opção: "
						+ "\n1 - Buscar por vaga"
						+ "\n2 - Buscar por placa");
				String searchMenu = sc.nextLine();
				
				switch(searchMenu){
				case "1":{
					System.out.println("\nEscreva o nome da vaga: ");
					String numberSpot = sc.nextLine().toUpperCase().trim();
					
					parking.searchBySpot(numberSpot);
					
					System.out.println("Pressione ENTER...");
					sc.nextLine();
					break;
				}
				case "2":{
					System.out.println("\nDigite a placa do veiculo que está estacionado: ");					
					String numberPlate = sc.nextLine().toUpperCase().trim();
					
					parking.searchByPlate(numberPlate);
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					break;
				}
				default:{
					System.out.println("\nEsta opção é Inválida: " + choice + "\n");
					}
				}
				
				break;
			}
			case "5": {
				System.out.println("\nDeseja imprimir o extrato operacional antes de sair?"
						+ "\n1 - Sim"
						+ "\n2 - Não"
						+ "\n3 - Cancelar");
				String exitMenu = sc.nextLine();
				
				switch(exitMenu) {
				case "1":{
					parking.printOperationStatement();
					
					System.out.println("\nPressione ENTER...");
					sc.nextLine();
					
					System.out.println("\nPrograma finalizado!");
					exit = true;
					break;
				}
				case "2":{
					System.out.println("\nPrograma finalizado!");
					exit = true;
					break;
				}
				case "3":{
					break;
				}				
				default:{
					System.out.println("\nEsta opção é Inválida: " + exitMenu + "\n");
				}
				}
				break;
			}
			default:{
				System.out.println("\nEsta opção é Inválida: " + choice + "\n");
				}
			}
			
		}
		sc.close();
	}

}