//Daniel Peterson (109091561)
//This program receives a user determined integer and converts
//it from a celsius temperature value to a farenheit temperature
//value
import java.util.Scanner;
public class Celsius {
	public static void main(String[] args){
		double celsius, farenheit;
		System.out.println("Please enter a degree in Celsius: ");
		Scanner input = new Scanner(System.in);
		celsius = input.nextDouble();
		farenheit = (celsius * (9.0/5)) + 32;
		System.out.print(celsius + " degrees in celsius is = ");
		System.out.println(farenheit + " degrees in farenheit");
		
	}
}
