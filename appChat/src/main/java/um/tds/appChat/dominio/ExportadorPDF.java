package um.tds.appChat.dominio;


import java.sql.BatchUpdateException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import tds.BubbleText;
public class ExportadorPDF {

	public void exportarPDF(List<Mensaje> mensajes, String nombreContacto, String nombreUsuario, String pdfPath) {
	    try {
	        // Crear un escritor de PDF
	        PdfWriter writer = new PdfWriter(pdfPath);
	        PdfDocument pdfDocument = new PdfDocument(writer);
	        Document document = new Document(pdfDocument);

	        // Cargar fuentes
	        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	        PdfFont normalFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        PdfFont italicFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);

	        // Agregar título al PDF
	        document.add(new Paragraph("Conversación entre " + nombreUsuario + " y " + nombreContacto)
	                .setFont(boldFont)
	                .setFontSize(20));

	        // Agregar un espacio
	        document.add(new Paragraph("\n"));

	        // Agregar mensajes al PDF
	        String nombreEmisor;
	        for (Mensaje mensaje : mensajes) {
	        	nombreEmisor = mensaje.getNombreEmisor();
	            String texto = mensaje.getTexto();
	            String fecha = mensaje.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	            if(texto.equals("") && mensaje.getEmoji()!=-1) {
	            	// Obtener la imagen del emoji
	                ImageIcon emoji = BubbleText.getEmoji(mensaje.getEmoji());
	                Image imgEmoji = new Image(ImageDataFactory.create(emoji.getImage(), null));
	                imgEmoji.setWidth(20); // Ajustar el tamaño de la imagen
	                imgEmoji.setHeight(20);

	                // Crear un párrafo con el nombre del emisor, la imagen del emoji y la fecha
	                Paragraph parrafoEmoji = new Paragraph()
	                        .add(new Text(nombreEmisor + ": ").setFont(boldFont)) // Nombre del remitente
	                        .add(imgEmoji) // Imagen del emoji
	                        .add(new Text(" (" + fecha + ")").setFont(italicFont)); // Fecha en cursiva

	                // Agregar al documento
	                document.add(parrafoEmoji);
                	
}
	            else {
	            	 // Formatear el mensaje
		            Paragraph parrafoMensaje = new Paragraph()
		                    .add(new Text(nombreEmisor + ": ").setFont(boldFont)) // Nombre del remitente en negrita
		                    .add(new Text(texto).setFont(normalFont)) // Contenido del mensaje
		                    .add(new Text(" (" + fecha + ")").setFont(italicFont)); // Fecha en cursiva
		            
		            // Agregar el mensaje al documento
		            document.add(parrafoMensaje);
	            }
	           
	        }

	        // Cerrar el documento
	        document.close();

	        System.out.println("Archivo PDF creado exitosamente en: " + pdfPath);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("Error al crear el archivo PDF: " + e.getMessage());
	    }
	}
}
