package logic.request.wTree;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public interface Visiteur {
    void visiter(Stack chemin);


    public class Panneau extends JPanel {
        /*
         * Taille de l'arbre pris pour exemple
         */
        public static final int NOMBRE = 7;

        /*
         * L'arbre en question
         */
        private WNode arbre;

        /*
         * dimensions de l'arbre (exprimées en nombres de noeuds)
         */
        private int largeur, hauteur;

        /*
         * Coefficients des transformations pour convertir les coordonnées
         * données en nombre de noeuds en coordonnées données en pixels
         */
        private double dx, dy, mx, my;

        /*
         * On consruit un panneau à partir d'un arbre dont on mémorise
         * une fois pour toutes les dimensions.
         */
        public Panneau(WNode arbre) {
            this.arbre = arbre;
            largeur = arbre.calculerPositions(0, 0);
            hauteur = arbre.hauteur();
            setBackground(Color.WHITE);
        }

        /*
         * Dessin de l'arbre.
         * La méthode paint est appelée notamment à l'occasion des changements
         * de taille de la fenêtre.
         * C'est donc le bon moment pour calculer les coefficients dx, dy, mx, my
         */
        public void paint(Graphics g) {
            super.paint(g);
            if (arbre != null) {
                Dimension d = getSize();
                dx = ((double) d.width) / largeur;
                dy = ((double) d.height) / hauteur;
                mx = dx / 2;
                my = dy / 2;
                dessiner(arbre, g);
            }
        }

        /*
         * Dessin effectif
         */
        private void dessiner(WNode a, Graphics g) {
            final int LARG = 10;
            final int HAUT = 8;

            int x1 = (int) (dx * a.X() + mx);
            int y1 = (int) (dy * a.Y() + my);

            if (a.existLeft()) {
                WNode f = a.left();
                int x2 = (int) (dx * f.X() + mx);
                int y2 = (int) (dy * f.Y() + my);
                g.drawLine(x1, y1, x2, y2);
                dessiner(f, g);
            }
            if (a.existRight()) {
                WNode f = a.right();
                int x2 = (int) (dx * f.X() + mx);
                int y2 = (int) (dy * f.Y() + my);
                g.drawLine(x1, y1, x2, y2);
                dessiner(f, g);
            }

            Color c = g.getColor();
            g.setColor(a.externe() ? Color.yellow : Color.pink);
            g.fillRect(x1 - LARG, y1 - HAUT, 2 * LARG, 2 * HAUT);
            g.setColor(c);
            g.drawRect(x1 - LARG, y1 - HAUT, 2 * LARG, 2 * HAUT);

            String s = "" + 1;
            if (s.length() < 2)
                s = " " + s;
            g.drawString(s, x1 - LARG + 3, y1 + HAUT - 2);
        }
    }
}
