package um.tds.appChat.utils;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;


public class RoundButtonUI extends BasicButtonUI {

    private Color textColor = null;
    private Color backgroundColor = null;

    public RoundButtonUI() {
        super();
    }

    public RoundButtonUI(Color backgroundColor) {
        super();
        this.backgroundColor = backgroundColor;
    }

    public RoundButtonUI(Color textColor, Color backgroundColor) {
        super();
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JButton button = (JButton) c;
        button.setOpaque(false);  // Evitar el fondo predeterminado
        button.setBorderPainted(false); // Desactivar el borde predeterminado
        button.setFocusPainted(false);  // Evitar el enfoque predeterminado
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        AbstractButton b = (AbstractButton) c;

        // Activar suavizado para bordes redondeados
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (backgroundColor == null) {
            backgroundColor = new Color(210, 210, 210);
        }
        if (b.getModel().isPressed()) {
            g2.setColor(backgroundColor.darker()); // Color al presionar
        } else if (b.getModel().isRollover()) {
            g2.setColor(backgroundColor.brighter()); // Color al pasar el mouse
        } else {
            g2.setColor(backgroundColor); // Color normal
        }
        g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 30, 30);

        // Borde redondeado
        g2.setColor(new Color(10, 10, 10)); // Color del borde
        g2.drawRoundRect(0, 0, b.getWidth() - 1, b.getHeight() - 1, 30, 30);

        // Dibujar el icono
        Icon icon = b.getIcon();
        int iconWidth = 0;
        int iconHeight = 0;
        int iconX = 0;
        int iconY = 0;
        if (icon != null) {
            iconWidth = icon.getIconWidth();
            iconHeight = icon.getIconHeight() / 4;
            iconX = (b.getWidth() - iconWidth) / 2;
            iconY = (b.getHeight() - iconHeight - b.getFontMetrics(b.getFont()).getHeight()) / 2;
            icon.paintIcon(b, g2, iconX, iconY);
        }

        // Dibujar el texto
        if (textColor == null) {
            textColor = new Color(40, 40, 40);
        }
        g2.setFont(b.getFont());
        FontMetrics fm = g2.getFontMetrics();
        int textX = (b.getWidth() - fm.stringWidth(b.getText())) / 2;
        int textY = iconY + iconHeight + fm.getAscent();
        g2.setColor(textColor);
        g2.drawString(b.getText(), textX, textY);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        AbstractButton b = (AbstractButton) c;
        FontMetrics fm = b.getFontMetrics(b.getFont());
        int width = fm.stringWidth(b.getText()) + 20; // Margen horizontal
        int height = fm.getHeight() + 10;            // Margen vertical
        Icon icon = b.getIcon();
        if (icon != null) {
            width = Math.max(width, icon.getIconWidth() + 20);
            height += icon.getIconHeight();
        }
        return new Dimension(width, height);
    }
}