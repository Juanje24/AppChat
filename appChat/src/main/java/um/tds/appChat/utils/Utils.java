package um.tds.appChat.utils;
	
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;

import tds.BubbleText;
import um.tds.appChat.dominio.Mensaje;

public class Utils {

	public static String getRutaResourceFromFile(File archivoImagen) {
		// Define la ruta base del proyecto que debe apuntar a "src/main/resources"
		Path rutaBase = Paths.get("src/main/resources").toAbsolutePath();

		// Obtén la ruta absoluta del archivo
		Path rutaArchivo = archivoImagen.toPath().toAbsolutePath();

		// Calcula la ruta relativa desde "src/main/resources" hasta el archivo
		Path rutaRelativa = rutaBase.relativize(rutaArchivo);

		// Devuelve la ruta en formato compatible con getResource()
		return "/" + rutaRelativa.toString().replace("\\", "/");
	}

	public static String getRutaResourceFromString(String source) {
	    String basePath = "src/main/resources/";
	    if (source.contains(basePath)) {
	        // Obtén la parte relativa al classpath
	        String target = source.substring(source.indexOf(basePath) + basePath.length());
	        // Cambia los separadores del sistema por "/"
	        return "/" + target.replace("\\", "/"); // O usa replace(File.separator, "/") si prefieres
	    } else {
	        throw new IllegalArgumentException("La ruta no contiene el basePath esperado: " + basePath);
	    }
	}

	public static BubbleText getBubbleFromMensaje(Mensaje msj, JPanel panel) {
		if(msj.getTipo()==BubbleText.SENT) {
			if (!msj.getTexto().equals("") && msj.getEmoji() == -1) {
				return new BubbleText(panel,msj.getTexto(),Color.GREEN, msj.getNombreEmisor(), BubbleText.SENT);
			}
			else {
				return new BubbleText(panel, msj.getEmoji(), Color.GREEN, msj.getNombreEmisor(), BubbleText.SENT, 9);
			}
		}
		else {
			if (!msj.getTexto().equals("") && msj.getEmoji() == -1) {
				return new BubbleText(panel,msj.getTexto(),Color.LIGHT_GRAY, msj.getNombreEmisor(), BubbleText.RECEIVED);
			}
			else {
				return new BubbleText(panel, msj.getEmoji(), Color.LIGHT_GRAY, msj.getNombreEmisor(), BubbleText.RECEIVED, 9);
			}
        }
		
		
	}
}
