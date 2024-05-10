package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		// DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("=====Entre com os dados da locação=====");
		System.out.print("Modelo do veiculo: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		// LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Devolução (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		// LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		// BrazilTaxService taxService = new BrazilTaxService();
		// System.out.println(taxService.tax(390.0));

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

		rentalService.processInvoice(cr);

		System.out.println();
		System.out.println("=====FATURA=====");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

		sc.close();
	}
}
