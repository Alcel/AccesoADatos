package repaso.examenNoviembre;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreadorDeAlumnos {
	public static void main(String[] args) throws IOException {
		String rutaNombre= "./src/repaso/datos/NombresComas";
		String rutaGrupos= "./src/repaso/datos/GruposSaltosdeLinea";
		String rutaMedias= "./src/repaso/datos/MediasComas";
		String rutaNuevosAlumnos= "./src/repaso/datos/MediasComas";
		ArrayList<Alumno> alumno;
		File nuevosAlumnos = new File(rutaNuevosAlumnos);
		File nombre = new File(rutaNombre);
		File grupos = new File(rutaGrupos);
		File medias = new File(rutaMedias);
		nuevosAlumnos.createNewFile();
	
		
	}
}
