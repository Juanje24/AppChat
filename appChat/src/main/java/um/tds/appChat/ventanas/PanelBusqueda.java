package um.tds.appChat.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import um.tds.appChat.singletons.AppChat;
import um.tds.appChat.utils.RoundButtonUI;
import um.tds.appChat.dominio.Mensaje;
import java.util.List;

public class PanelBusqueda extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtTexto;
    private JTextField txtNumero;
    private JTextField txtNombreContacto;
    private JDateChooser dateChooser;
    private JTable table;
    private DefaultTableModel tableModel;

    public PanelBusqueda() {
        setTitle("Búsqueda de Mensajes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con campos de texto y selector de fecha
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        topPanel.add(new JLabel("Texto:"));
        txtTexto = new JTextField();
        topPanel.add(txtTexto);

        topPanel.add(new JLabel("Número de Teléfono:"));
        txtNumero = new JTextField();
        topPanel.add(txtNumero);

        topPanel.add(new JLabel("Nombre del Contacto:"));
        txtNombreContacto = new JTextField();
        topPanel.add(txtNombreContacto);

        topPanel.add(new JLabel("Fecha:"));
        dateChooser = new JDateChooser();
        topPanel.add(dateChooser);

        add(topPanel, BorderLayout.NORTH);

        // Tabla con scroll para mostrar los resultados
        tableModel = new DefaultTableModel(new String[]{"Emisor", "Receptor", "Texto"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de búsqueda
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
                buscarYMostrarMensajes();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBuscar);
        JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight,new Color(255,100,100)));
		btnCancelar.addActionListener(e->{
			SwingUtilities.getWindowAncestor(this).setVisible(true);
			dispose();
		});
		bottomPanel.add(btnCancelar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void buscarYMostrarMensajes() {
        String texto = txtTexto.getText().trim();
        String numero = txtNumero.getText().trim();
        String nombreContacto = txtNombreContacto.getText().trim();
        LocalDate fecha = null;

        if (dateChooser.getDate() != null) {
            fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        // Llamada al método buscarMensaje (debes implementarlo en tu modelo)
        List<Mensaje> mensajes = AppChat.INSTANCE.buscarMensaje(texto, numero, nombreContacto, fecha);

        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Agregar los mensajes a la tabla
        for (Mensaje mensaje : mensajes) {
            tableModel.addRow(new Object[]{mensaje.getEmisor(), mensaje.getReceptor(), mensaje.getTexto()});
        }
    }


}
