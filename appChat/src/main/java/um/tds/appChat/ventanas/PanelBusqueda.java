package um.tds.appChat.ventanas;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import tds.BubbleText;
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

    public PanelBusqueda(JFrame parent) {
        setTitle("Búsqueda de Mensajes");
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });
        
        
        
        
        // Panel superior con campos de texto y selector de fecha
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
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
        tableModel = new DefaultTableModel(new String[]{"Mensaje"}, 0);
        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new MensajeRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de búsqueda
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setUI(new RoundButtonUI());
        btnBuscar.addActionListener(e -> {
                buscarYMostrarMensajes();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBuscar);
        JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.setUI(new RoundButtonUI(SystemColor.textHighlight,new Color(255,100,100)));
		btnCancelar.addActionListener(e->{
			parent.setVisible(true);
			dispose();
		});
		bottomPanel.add(btnCancelar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void buscarYMostrarMensajes() {
        String texto = txtTexto.getText().trim();
        String numero = txtNumero.getText().trim();
        String nombreContacto = txtNombreContacto.getText().trim();
        LocalDateTime fecha = null;

        if (dateChooser.getDate() != null) {
            fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
		
        // Llamada al método buscarMensaje (debes implementarlo en tu modelo)
        List<Mensaje> mensajes = AppChat.INSTANCE.buscarMensaje(texto, numero, nombreContacto, fecha);

        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Agregar los mensajes a la tabla
        for (Mensaje mensaje : mensajes) {
            tableModel.addRow(new Object[]{mensaje});
        }
    }
    private static class MensajeRenderer implements TableCellRenderer {
        private final JPanel panel;
        private final JPanel panelNorte;
        private final JLabel lblEmisor;
        private final JLabel lblReceptor;
        private final JLabel txtMensaje;

        public MensajeRenderer() {
            panel = new JPanel(new BorderLayout(5, 5));
            panelNorte = new JPanel(new BorderLayout());
            lblEmisor = new JLabel();
            lblReceptor = new JLabel();
            txtMensaje = new JLabel();
            txtMensaje.setFont(new Font("Arial", Font.PLAIN, 16));
            
            panelNorte.add(lblReceptor, BorderLayout.WEST);
            panelNorte.add(lblEmisor, BorderLayout.EAST);
            panel.add(panelNorte, BorderLayout.NORTH);
            panel.add(txtMensaje, BorderLayout.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Mensaje mensaje) {
                lblEmisor.setText("Emisor: " + mensaje.getNombreEmisor() + " (" + mensaje.getEmisor() + ")");
                lblReceptor.setText("Receptor: " + mensaje.getNombreReceptor() + " (" + mensaje.getReceptor() + ")");
                String texto = mensaje.getTexto();
				if (texto.equals("") && mensaje.getEmoji() != -1) {
					txtMensaje.setIcon(BubbleText.getEmoji(mensaje.getEmoji()));
					txtMensaje.setText("");
				}
				else {
					txtMensaje.setIcon(null);
					txtMensaje.setText(mensaje.getTexto());					
				}
				txtMensaje.setFocusable(false);
                
            }

            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
                lblEmisor.setForeground(table.getSelectionForeground());
                lblReceptor.setForeground(table.getSelectionForeground());
                txtMensaje.setForeground(table.getSelectionForeground());
            } else {
                panel.setBackground(table.getBackground());
                lblEmisor.setForeground(table.getForeground());
                lblReceptor.setForeground(table.getForeground());
                txtMensaje.setForeground(table.getForeground());
            }
         // Ajusta dinámicamente la altura de la fila
            int preferredHeight = panel.getPreferredSize().height;
            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }

            return panel;
        }
    }


}
