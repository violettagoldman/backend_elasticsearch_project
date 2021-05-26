package other;

import logic.request.wTree.WNode;

import java.util.Stack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Stack;

class Arbin {
    /*
     * Information portée par le noeud
     */
    private int contenu;

    /*
     * Coordonnées du noeud
     */
    private int x, y;

    /*
     * Descendants directs du noeud
     */private Arbin filsGauche, filsDroit;

    /*
     * Accès au contenu
     */
    public int contenu() {
        return contenu;
    }

    public void fixerContenu(int c) {
        contenu = c;
    }

    /*
     * Accès aux fils
     */
    public boolean existeFilsGauche() {
        return filsGauche != null;
    }

    public boolean existeFilsDroit() {
        return filsDroit != null;
    }

    public Arbin filsGauche() {
        return filsGauche;
    }

    public Arbin filsDroit() {
        return filsDroit;
    }

    public void fixerFilsGauche(Arbin g) {
        filsGauche = g;
    }

    public void fixerFilsDroit(Arbin d) {
        filsDroit = d;
    }

    /*
     * Un noeud externe, ou feuille, est un noeud sans fils
     */
    public boolean externe() {
        return !existeFilsGauche() && !existeFilsDroit();
    }

    /*
     * Nombre de niveaux de l'arbre
     */
    int hauteur() {
        int g = existeFilsGauche() ? filsGauche.hauteur() : 0;
        int d = existeFilsDroit() ? filsDroit.hauteur() : 0;
        return Math.max(g, d) + 1;
    }

    /*
     * Accès à la position dans le plan
     */
    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public void fixerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     * Calcul de la position d'un noeud dans le plan
     */
    public int calculerPositions(int xCourant, int yCourant) {
        /* on augmente xCourant afin d'accueillir toute la largeur du fils gauche */
        if (existeFilsGauche())
            xCourant = filsGauche().calculerPositions(xCourant, yCourant + 1);

        /* la valeur de xCourant est maintenant l'abscisse de notre noeud */
        fixerPosition(xCourant, yCourant);
        xCourant = xCourant + 1;

        /* on deplace xCourant afin d'accueillir toute la largeur du fils droit */
        if (existeFilsDroit())
            xCourant = filsDroit().calculerPositions(xCourant, yCourant + 1);

        /* la valeur de xCourant comprend maintenant la largeur de notre arbre */
        return xCourant;
    }

    /*
     * Expression textuelle (la plus simple)
     */
    public String toString() {
        return "(" + contenu + "," + filsGauche + "," + filsDroit + ")";
    }

    /*
     * Parcours en pré-ordre
     */
    public void preOrdre(Visiteur traitement, Stack chemin) {
        chemin.push(this);
        traitement.visiter(chemin);
        if (existeFilsGauche())
            filsGauche().preOrdre(traitement, chemin);
        if (existeFilsDroit())
            filsDroit().preOrdre(traitement, chemin);
        chemin.pop();
    }

    /*
     * Parcours en in-ordre
     */
    public void inOrdre(Visiteur traitement, Stack chemin) {
        chemin.push(this);
        if (existeFilsGauche())
            filsGauche().inOrdre(traitement, chemin);
        traitement.visiter(chemin);
        if (existeFilsDroit())
            filsDroit().inOrdre(traitement, chemin);
        chemin.pop();
    }

    /*
     * Parcours en post-ordre
     */
    public void postOrdre(Visiteur traitement, Stack chemin) {
        chemin.push(this);
        if (existeFilsGauche())
            filsGauche().postOrdre(traitement, chemin);
        if (existeFilsDroit())
            filsDroit().postOrdre(traitement, chemin);
        traitement.visiter(chemin);
        chemin.pop();
    }

    /*
     * Constructeur d'un arbre binaire qui
     * est une feuille portant l'information donnée
     */
    public Arbin(int contenu) {
        this.contenu = contenu;
        filsGauche = filsDroit = null;
    }

    /*
     * Construction d'un arbre binaire équilibré qui
     * porte tous les nombres compris entre min et max
     */
    public Arbin(int min, int max) {
        int mil = (min + max) / 2;
        contenu = mil;
        if (min < mil)
            filsGauche = new Arbin(min, mil - 1);
        if (mil < max)
            filsDroit = new Arbin(mil + 1, max);
    }

    /*
     * Construction d'un arbre binaire qui
     * porte n nombres aléatoires compris entre min et max
     */
    public Arbin(int n, int min, int max) {
        contenu = entierAleatoire(min, max);
        int aGauche = entierAleatoire(0, n - 1);
        if (aGauche > 0)
            filsGauche = new Arbin(aGauche, min, max);
        if (aGauche < n - 1)
            filsDroit = new Arbin(n - aGauche - 1, min, max);
    }

    /*
     * Test de tout cela
     */
    public static void main(String[] args) {
        /* On construit un arbre de 50 noeuds */
        Arbin a = new Arbin(0, 50);
        // Arbin a = new Arbin(50, 0, 1000);

        /* On l'affiche simplement */
        System.out.println("haut: " + a.hauteur() + ", a: " + a);

        /* On crée un visiteur qui affiche chaque noeud sur une ligne
         * avec une marge proportionnelle à la distance de la racine */
        Visiteur afficheur = new Visiteur() {
            public void visiter(Stack chemin) {
                int n = chemin.size();
                while (n-- > 0)
                    System.out.print("   ");
                System.out.println(((Arbin) chemin.peek()).contenu);
            }
        };

        /* On parcourt l'arbre, par exemple en in-ordre */
        a.inOrdre(afficheur, new Stack());
    }

    /*
     * Auxiliaire
     */
    public static int entierAleatoire(int min, int max) {
        return (int) Math.round(min + Math.random() * (max - min));
    }


    public interface Visiteur {
        void visiter(Stack chemin);
    }

    public static class Panneau extends JPanel {
        /*
         * Taille de l'arbre pris pour exemple
         */
        public static final int NOMBRE = 40;

        /*
         * L'arbre en question
         */
        private Arbin arbre;

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
        public Panneau(Arbin arbre) {
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
        private void dessiner(Arbin a, Graphics g) {
            final int LARG = 10;
            final int HAUT = 8;

            int x1 = (int) (dx * a.X() + mx);
            int y1 = (int) (dy * a.Y() + my);

            if (a.existeFilsGauche()) {
                Arbin f = a.filsGauche();
                int x2 = (int) (dx * f.X() + mx);
                int y2 = (int) (dy * f.Y() + my);
                g.drawLine(x1, y1, x2, y2);
                dessiner(f, g);
            }
            if (a.existeFilsDroit()) {
                Arbin f = a.filsDroit();
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

            String s = "" + a.contenu();
            if (s.length() < 2)
                s = " " + s;
            g.drawString(s, x1 - LARG + 3, y1 + HAUT - 2);
        }

        /*
         * Programme principal: on crée un cadre et on y place
         * un panneau réprsentant un arbre
         */
        public static void main(String[] args) {
            Arbin arbre = new Arbin(NOMBRE, 0, 99);
            Panneau panneau = new Panneau(arbre);

            JFrame cadre = new JFrame("Arbre binaire aléatoire - n = " + NOMBRE);
            cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cadre.setBounds(100, 100, 600, 400);
            cadre.setContentPane(panneau);
            cadre.setVisible(true);
        }
    }
}
