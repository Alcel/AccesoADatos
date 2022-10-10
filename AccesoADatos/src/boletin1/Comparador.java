package boletin1;

public class Comparador {
	public static void main(String[] args) {
		String sistemaOperativo = System.getProperty("os.name");
		String fichero1;
		String fichero2;
		if(sistemaOperativo.startsWith("Win")) {
			fichero1=".\\datos\\"+args[0]+".txt";
			fichero2=".\\datos\\"+args[1]+".txt";
			System.out.println("Hola");
		}
		else {
			fichero1="./datos/"+args[0]+".txt";
			fichero2="./datos/"+args[1]+".txt";
		}
	}
}