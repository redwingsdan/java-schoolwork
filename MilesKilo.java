
public class MilesKilo {
	public static void main(String[] args){
		int miles = 1;
		double kilometers = 1.609;
		int i;
		System.out.println("Miles" + "\t" + "Kilometers" + "\n");
		for(i = 0; i < 10; i++){
			System.out.println(miles + "\t\t" + kilometers + "\n");
			miles = miles + 1;
			kilometers = miles * 1.609;
			kilometers = (int)(kilometers * 1000) / 1000.000;
		}
	}
}
