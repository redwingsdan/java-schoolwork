public class KiloPound2 {
	public static void main(String[] args){
		int kilo = 1, pounds1 = 20;
		double pounds = 2.2, kilo1 = 9.09;
		int i;
		System.out.println("Kilograms" + "\t" + "Pounds" + "\tPounds\tKilograms" + "\n");
		for(i = 0; i < 100; i++){
			System.out.println(kilo + "\t\t" + pounds + "\t" + pounds1 + "\t" + kilo1 +"\n");
			kilo = kilo + 2;
			pounds = kilo * 2.2;
			pounds = (int)(pounds * 100) / 100.00;
			pounds1 = pounds1 + 5;
			kilo1 = pounds1 / 2.2;
			kilo1 = (int)(kilo1 * 100) / 100.00;
		}
	}
	
}
