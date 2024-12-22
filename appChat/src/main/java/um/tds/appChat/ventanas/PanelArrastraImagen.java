package um.tds.appChat.ventanas;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class PanelArrastraImagen extends JDialog {

	private static final long serialVersionUID = 1L;
	private  JPanel contentPane = new JPanel();
	private List<File> archivosSubidos = new ArrayList<File>();
	private JButton btnAceptar;
	private JButton btnCancelar;
	


	/**
	 * Create the dialog.
	 */
	public PanelArrastraImagen(JFrame owner) {
		super(owner, "Agregar fotos", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 638, 414);
		getContentPane().setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "\u00C1rea de arrastre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setBackground(Color.WHITE);
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");  
        editorPane.setText("Puedes arrastrar el fichero aquí.   ");
        contentPane.add(editorPane, BorderLayout.NORTH);
        
          
        
        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(imagenLabel, BorderLayout.CENTER);
		contentPane.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>) evt.getTransferable().
		            		getTransferData(DataFlavor.javaFileListFlavor);
		            
		            if (!droppedFiles.isEmpty()) {
		            	File file = droppedFiles.get(0);
		                archivosSubidos.add(file);
		            //lblArchivoSubido.setText(droppedFiles.get(0).getAbsolutePath());
		            //lblArchivoSubido.setVisible(true);
		            
		         // Cargar la imagen en el JLabel
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imagenLabel.setIcon(new ImageIcon(img));
                    editorPane.setText("Foto subida: " + file.getName());
                    //lblArchivoSubido.setText(file.getAbsolutePath());
                    //lblArchivoSubido.setVisible(true);
		          }
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		        // evt.dropComplete(true);
		    }
		});
		
		// Panel de botones Aceptar y Cancelar
        JPanel panelBotones = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(10);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Acción del botón Aceptar
        btnAceptar.addActionListener(ev -> dispose());

        // Acción del botón Cancelar
        btnCancelar.addActionListener(ev -> {
                archivosSubidos.clear(); // Limpia la lista si se cancela
                dispose();
        });        	
        JButton botonElegir = new JButton("Seleccionar de tu ordenador");      
     
        botonElegir.setForeground(Color.WHITE);
        botonElegir.setBackground(SystemColor.textHighlight);
        botonElegir.addActionListener(ev -> {
        	JFileChooser chooser = new JFileChooser();
        	if (chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
        		File currentFile = chooser.getSelectedFile();
                archivosSubidos.add(currentFile);
                ImageIcon icon = new ImageIcon(currentFile.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imagenLabel.setIcon(new ImageIcon(img));
        	}
        });
        panelBotones.add(botonElegir);
        panelBotones.add(Box.createRigidArea(new Dimension(150,10)));
        btnAceptar.setBackground(new Color(166,255,184));
        btnAceptar.setForeground(SystemColor.textHighlight);
        btnCancelar.setBackground(new Color(255,100,100));
        btnCancelar.setForeground(SystemColor.textHighlight);
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        JEditorPane editorPane_1 = new JEditorPane();
        editorPane_1.setEditable(false);
        editorPane_1.setContentType("text/html");  
        editorPane_1.setText("<h1>Agregar Foto</h1>");
        panel.add(editorPane_1);
        
        setLocationRelativeTo(owner); // Centra el diálogo en la ventana principal
    }

	
	public List<File> showDialog() {
		this.setVisible(true);
		return archivosSubidos;
	}
}
