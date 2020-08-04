
public class KiloPounds {
	public static void main(String[] args){
		int kilo = 1;
		double pounds = 2.2;
		int i;
		System.out.println("Kilograms" + "\t" + "Pounds" + "\n");
		for(i = 0; i < 100; i++){
			System.out.println(kilo + "\t\t" + pounds + "\n");
			kilo = kilo + 2;
			pounds = kilo * 2.2;
			pounds = (int)(pounds * 100) / 100.00;
		}
	}
	
}
