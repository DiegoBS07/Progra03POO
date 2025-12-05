/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente;


import FrameManager.ImageFrameManagerUtil;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import static java.lang.Thread.sleep;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author diego
 */
public class FrameClient extends JFrame {

    private Client client;
    private static final int NUMBER_OF_CELLS = 400;
    private static final int NUMBER_OF_ROWS_AND_COLUMS = 20;
    private JPanel pnlImgFondo;
    private static final int CORRECT_EXIT = 0;
    /**
     * Creates new form FrameClient
     */
    public FrameClient() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initImages();
        String name = JOptionPane.showInputDialog(this, "Ingrese su nombre");
        this.setTitle(name);
        client =  new Client(this, name);
        
    }
    
    /*El método muestra una ventana con un error de nombre y solicita
          nuevamente el nombre al usuario, haciendo el request al servidor.
        */
    public void showNameError(){
        if (this.client == null){
            return;
        }
        String name = JOptionPane.showInputDialog(this, "Ingrese su nombre", "Error en el nombre", JOptionPane.ERROR_MESSAGE);
        this.setTitle(name);
        client.name = name;
        client.sendName();
        
    }
    public static int getNumberOfCells(){
        return NUMBER_OF_CELLS;
    }
    
    public static int getNumberOfRowsAndColumns(){
        return NUMBER_OF_ROWS_AND_COLUMS;
    }
    
    
    /*Cambia el fondo de la pantalla y también el icono de la ventana*/
    private void initImages(){
        ((JPanel)getContentPane()).setOpaque(false);
        Image imgFrameClient = new ImageIcon(getClass()
                .getResource("/ImagenesFondos/ImagenFondoFrameClient.png")).getImage()
                .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        this.setIconImage(imgFrameClient);
        pnlImgFondo = ImageFrameManagerUtil.createPanelWithImage("/ImagenesFondos/ImagenFondoFrameClient.png");
        pnlImgFondo.setBounds(0, 0, getWidth(), getHeight()); //Se ajusta al tamaño del frame
        pnlImgFondo.setOpaque(false); // Así no tapa el uso de otros componentes

        // Así se agrega el panel al layered pane en la capa más baja
        getLayeredPane().add(pnlImgFondo, Integer.valueOf(Integer.MIN_VALUE));
        
        //Así el tamaño de la imagen de fondo es reajustable siempre que el usuario lo desee
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (pnlImgFondo != null) {
                    pnlImgFondo.setBounds(0, 0, getWidth(), getHeight());
                    pnlImgFondo.repaint();
                }
            }
        });
    }

    /*El metodo revalida y repinta la ventana en su totalidad*/
    public void refreshFrame(){
        this.revalidate();
        this.repaint();
    }
    
    /*Método que carga todos los 400 labels del mapa en el panel correspondiente*/
    public void initOceanMap(){ 
        //Crear las celdas
        ArrayList<Cell> createdCells = new ArrayList<Cell>();
        ArrayList<Cell> addedCells = new ArrayList<Cell>();
        for (int i = 0; i < client.getFighterRegister().getFighters().size(); i++) {
            for (int j = 0; j < client.getFigtherAmountOfCells(i); j++) {
                Cell newCell = new Cell(client.getFighterRegister().getFighter(i));
                createdCells.add(newCell);
            }
        }
        //Añadir las celdas de forma aleatoria en el frame
        Random random = new Random();
        while (addedCells.size() < NUMBER_OF_CELLS){
            int index = random.nextInt(NUMBER_OF_CELLS);
            if (!addedCells.contains(createdCells.get(index))){
                addedCells.add(createdCells.get(index));
                pnlOceanMapGrid.add(createdCells.get(index).getRefLabel());
            }
        }
        //Añadir las celdas a la matriz del mapa
        int cellCounter = 0;
        for (int i = 0; i < NUMBER_OF_ROWS_AND_COLUMS; i++){
            for (int j = 0; j < NUMBER_OF_ROWS_AND_COLUMS; j++){
                client.getOceanMap()[i][j] = addedCells.get(cellCounter++);
            }
        }
        //Agregación del header para las rows
        for (int i = 1; i <= NUMBER_OF_ROWS_AND_COLUMS; i++){
            JLabel lblNumberOfRow = new JLabel("" + i, SwingConstants.CENTER);
            lblNumberOfRow.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            pnlRowsHeader.add(lblNumberOfRow);
        }
        //Agregación del header para las colums
        for (int i = 1; i <= NUMBER_OF_ROWS_AND_COLUMS; i++){
            JLabel lblNumberOfColumn = new JLabel("" + i, SwingConstants.CENTER);
            lblNumberOfColumn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            pnlColumnsHeader.add(lblNumberOfColumn);
        }
        client.calculateActualCellsPerFighter();
        client.generateActualFighterPercentage();
        insertActualHealth();
        pnlOceanMapGrid.revalidate();
        pnlOceanMapGrid.repaint();
        this.revalidate();
        this.repaint();


    }
    
    private void cleanCommandWritter(){
        this.txfCommand.setText("");
        this.repaint();
    }
  
    public void colocarPersonajes(ArrayList<Fighter> Fighters){
        switch (Fighters.size()) {
    case 1:
        mostrarFighterEnTextPane(txpFighterStats1, Fighters.get(0));
        break;
    case 2:
        mostrarFighterEnTextPane(txpFighterStats2, Fighters.get(1));
        break;
    case 3:
        mostrarFighterEnTextPane(txpFighterStats3, Fighters.get(2));
        break;
}
    }
    
    public void imageSelector(ArrayList<Fighter> Fighters){
        switch (Fighters.size()) {
    case 1:
         insertImageinFrame(lblImgFighter1, Fighters.get(0));
        break;
    case 2:
         insertImageinFrame(lblImgFighter2, Fighters.get(1));
        break;
    case 3:
         insertImageinFrame(lblImgFighter3, Fighters.get(2));
        break;
}
    }

    public void showCellInfo(Cell info){
            int health = info.getHealth();
            boolean dead = info.isDead();
            String fighterName = (info.getFighter() != null) ? info.getFighter().getName() : "Vacío";

            StringBuilder sb = new StringBuilder();
            sb.append("Información de la celda:\n");
            sb.append("Luchador: ").append(fighterName).append("\n");
            sb.append("Vida: ").append(health).append("\n");
            sb.append("Estado: ").append(dead ? "Muerto" : "Vivo").append("\n");

            txaRanking.setText(sb.toString());
        }

    public void insertActualHealth() {
        client.calculateActualCellsPerFighter();
        int fullHealth = client.calculateFullHealth(); // porcentaje global
        ArrayList<Fighter> fighters = client.getFighterRegister().getFighters();
        this.client.generateActualFighterPercentage(); //Genrea el porcentaje actual de vida de peleadores

        if (fighters.size() >= 3) {
            Fighter f1 = fighters.get(0);
            Fighter f2 = fighters.get(1);
            Fighter f3 = fighters.get(2);

            // Obtiene el porcentaje actual.
            double p1 = f1.getTotalHealthPercentage();
            double p2 = f2.getTotalHealthPercentage();;
            double p3 = f3.getTotalHealthPercentage();;

            // Inserta en los JTextPane
            txpFitgtherCells1.setText(f1.getName() + ": " + p1 + "%");
            txpFigtherCells2.setText(f2.getName() + ": " + p2 + "%");
            txpFigtherCells3.setText(f3.getName() + ": " + p3 + "%");

            // Salud global
            txpHealth.setText("Salud total: " + fullHealth + "%");
        }
    }

    
    public void insertImageinFrame(JLabel label, Fighter f){
        URL resource = getClass().getResource(f.getImagePath());
            if (resource == null) {
        System.out.println("Imagen no encontrada");
        return;
        }
        ImageIcon image = new ImageIcon(resource);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icon);
        label.revalidate();
        label.repaint();
        label.setOpaque(true);

    }

    public void showSummary(LogEvents Log){
        Log.generateSummary();
        txaRanking.setText(Log.getSummary());

    }

    public void showLog(LogEvents Log){
        this.txaLogPlayer.setText(Log.getEventsAsString());

    }
    
    public void mostrarFighterEnTextPane(JTextPane textPane, Fighter f){
    StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.remove(0, doc.getLength()); // Limpia contenido anterior
        } catch (BadLocationException ex) {
            System.getLogger(FrameClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    // Estilo: representation porcentage
    Style porcentage = textPane.addStyle("porcentage", null);
    StyleConstants.setFontSize(porcentage, 16);
    StyleConstants.setForeground(porcentage, Color.BLACK);
        try {
            doc.insertString(doc.getLength(), String.format("%d", f.getCivilizationpercent()) + "%\n", porcentage);
        } catch (BadLocationException ex) {
            System.getLogger(FrameClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    // Estilo: name
    Style name = textPane.addStyle("Name", null);
    StyleConstants.setFontSize(name, 14);
    StyleConstants.setBold(name, true);
    StyleConstants.setForeground(name, f.getRepresentativeColor());
        try {
            doc.insertString(doc.getLength(), f.getName() + "\n", name);
        } catch (BadLocationException ex) {
            System.getLogger(FrameClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    // Estilo: attackgroup
    Style attackgroup = textPane.addStyle("grupo", null);
    StyleConstants.setFontSize(attackgroup, 14);
    StyleConstants.setItalic(attackgroup, true);
        try {
            doc.insertString(doc.getLength(), "Attack Group: " + f.getAttackGroup().getName() + "\n\n", attackgroup);
        } catch (BadLocationException ex) {
            System.getLogger(FrameClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    // Estilo: stats
    Style stats = textPane.addStyle("stats", null);
    StyleConstants.setFontSize(stats, 12);
        try {
            doc.insertString(doc.getLength(),
                    String.format("Power:       %d%%\nResistance: %d%%\nHealing:     %d%%\n",
                            f.getPowerPercent(), f.getResistancePercent(), f.getHealingPercent()), stats);
        } catch (BadLocationException ex) {
            System.getLogger(FrameClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
}
    public void writeMessage(String msg){
        txaMessages.append(msg + "\n");
        this.refreshFrame();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaMessages = new javax.swing.JTextArea();
        txfCommand = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLogPlayer = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaRanking = new javax.swing.JTextArea();
        btnClose = new javax.swing.JButton();
        pnlOceanMap = new javax.swing.JPanel();
        pnlOceanMapGrid = new javax.swing.JPanel();
        pnlOceanMapGrid1 = new javax.swing.JPanel();
        pnlOceanMapGrid2 = new javax.swing.JPanel();
        pnlOceanMapGrid3 = new javax.swing.JPanel();
        pnlOceanMapGrid4 = new javax.swing.JPanel();
        pnlOceanMapGrid5 = new javax.swing.JPanel();
        pnlOceanMapGrid6 = new javax.swing.JPanel();
        pnlOceanMapGrid7 = new javax.swing.JPanel();
        pnlOceanMapGrid8 = new javax.swing.JPanel();
        pnlOceanMapGrid9 = new javax.swing.JPanel();
        pnlWarriors = new javax.swing.JPanel();
        lblWarriorImage1 = new javax.swing.JLabel();
        lblWarriorImage2 = new javax.swing.JLabel();
        lblWarriorImage3 = new javax.swing.JLabel();
        lblWarriorImage4 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txaResultadoAtaque1 = new javax.swing.JTextArea();
        pnlColumnsHeader = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        txaMessages.setColumns(20);
        txaMessages.setRows(5);
        jScrollPane1.setViewportView(txaMessages);

        txfCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfCommandActionPerformed(evt);
            }
        });
        txfCommand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txfCommandKeyReleased(evt);
            }
        });

        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txaLogPlayer.setColumns(20);
        txaLogPlayer.setRows(5);
        jScrollPane2.setViewportView(txaLogPlayer);

        txaRanking.setColumns(20);
        txaRanking.setRows(5);
        jScrollPane3.setViewportView(txaRanking);

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        pnlOceanMap.setBackground(new java.awt.Color(102, 255, 102));
        pnlOceanMap.setOpaque(false);
        pnlOceanMap.setPreferredSize(new java.awt.Dimension(600, 600));

        pnlOceanMapGrid.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid.setLayout(new java.awt.GridLayout(20, 20));

        pnlOceanMapGrid1.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid1.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid1.setLayout(new java.awt.GridLayout(20, 20));
        pnlOceanMapGrid.add(pnlOceanMapGrid1);

        pnlOceanMapGrid2.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid2.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid2.setLayout(new java.awt.GridLayout(20, 20));

        pnlOceanMapGrid3.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid3.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid3.setLayout(new java.awt.GridLayout(20, 20));
        pnlOceanMapGrid2.add(pnlOceanMapGrid3);

        pnlOceanMapGrid4.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid4.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid4.setLayout(new java.awt.GridLayout(20, 20));

        pnlOceanMapGrid5.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid5.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid5.setLayout(new java.awt.GridLayout(20, 20));
        pnlOceanMapGrid4.add(pnlOceanMapGrid5);

        pnlOceanMapGrid2.add(pnlOceanMapGrid4);

        pnlOceanMapGrid6.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid6.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid6.setLayout(new java.awt.GridLayout(20, 20));

        pnlOceanMapGrid7.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid7.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid7.setLayout(new java.awt.GridLayout(20, 20));
        pnlOceanMapGrid6.add(pnlOceanMapGrid7);

        pnlOceanMapGrid8.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid8.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid8.setLayout(new java.awt.GridLayout(20, 20));

        pnlOceanMapGrid9.setBackground(new java.awt.Color(0, 51, 255));
        pnlOceanMapGrid9.setPreferredSize(new java.awt.Dimension(515, 400));
        pnlOceanMapGrid9.setLayout(new java.awt.GridLayout(20, 20));
        pnlOceanMapGrid8.add(pnlOceanMapGrid9);

        pnlOceanMapGrid6.add(pnlOceanMapGrid8);

        pnlOceanMapGrid2.add(pnlOceanMapGrid6);

        javax.swing.GroupLayout pnlOceanMapLayout = new javax.swing.GroupLayout(pnlOceanMap);
        pnlOceanMap.setLayout(pnlOceanMapLayout);
        pnlOceanMapLayout.setHorizontalGroup(
            pnlOceanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOceanMapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOceanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOceanMapGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(pnlOceanMapGrid2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlOceanMapLayout.setVerticalGroup(
            pnlOceanMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOceanMapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOceanMapGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlOceanMapGrid2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlWarriors.setBackground(new java.awt.Color(51, 255, 51));
        pnlWarriors.setOpaque(false);

        javax.swing.GroupLayout pnlWarriorsLayout = new javax.swing.GroupLayout(pnlWarriors);
        pnlWarriors.setLayout(pnlWarriorsLayout);
        pnlWarriorsLayout.setHorizontalGroup(
            pnlWarriorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWarriorsLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(lblWarriorImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWarriorImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWarriorImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWarriorImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        pnlWarriorsLayout.setVerticalGroup(
            pnlWarriorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWarriorsLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(pnlWarriorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWarriorImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWarriorImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWarriorImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWarriorImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txaResultadoAtaque1.setColumns(20);
        txaResultadoAtaque1.setRows(5);
        jScrollPane9.setViewportView(txaResultadoAtaque1);

        pnlColumnsHeader.setPreferredSize(new java.awt.Dimension(515, 52));
        pnlColumnsHeader.setLayout(new java.awt.GridLayout(1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(pnlOceanMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlWarriors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlColumnsHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txfCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 1203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 251, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(pnlColumnsHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlWarriors, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlOceanMap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        //obnter string del txf y quitar espacio
        String msg =  txfCommand.getText().trim();
        if (msg.length()>0){
            String args[] = CommandUtil.tokenizerArgs(msg);
            if (args.length > 0){
                Command comando = CommandFactory.getCommand(args);
                if (comando != null){
                    try {
                        client.objectSender.writeObject(comando);
                    } catch (IOException ex) {
                        
                    }
                }else{
                    this.writeMessage("Error: comando desconocido");
                }
                
            }
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnCloseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(FrameClient.CORRECT_EXIT);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txfCommandActionPerformed(ActionEvent evt) {//GEN-FIRST:event_txfCommandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfCommandActionPerformed

    private void txfCommandKeyReleased(KeyEvent evt) {//GEN-FIRST:event_txfCommandKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (respondThreeNumbersCommand){
                this.commandRespondThreeNumbers = respondThreeNumbers();
                this.cleanCommandWritter();
                return;
            }
            //obnter string del txf y quitar espacio
            String msg =  txfCommand.getText().trim();
            if (msg.length()>0){
                String args[] = CommandUtil.tokenizerArgs(msg);
                if (args.length > 0){
                    Command comando = CommandFactory.getCommand(args);
                    if (!client.isDead() && !DisponibilityCheckerOfCommand.checkInicialCommandDisponibilty(comando, this.client.isIsReady(), client.getFighterRegister().getFighters())){
                        this.writeMessage("Error: comando no permitido");
                        this.cleanCommandWritter();
                        return;
                    }
                    if (client.isDead() && !DisponibilityCheckerOfCommand.checkDeadCommandDisponibility(comando)){
                        this.writeMessage("Error: comando no permitido");
                        this.cleanCommandWritter();
                        return;
                    }
                    if (comando != null){
                        try {
                            client.objectSender.writeObject(comando);
                            client.objectSender.flush();
                        } catch (IOException ex) {

                        }
                    }else{
                        this.writeMessage("Error: comando desconocido");
                    }

                }
            }
            this.cleanCommandWritter();
        }
    }//GEN-LAST:event_txfCommandKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblWarriorImage1;
    private javax.swing.JLabel lblWarriorImage2;
    private javax.swing.JLabel lblWarriorImage3;
    private javax.swing.JLabel lblWarriorImage4;
    private javax.swing.JPanel pnlColumnsHeader;
    private javax.swing.JPanel pnlOceanMap;
    private javax.swing.JPanel pnlOceanMapGrid;
    private javax.swing.JPanel pnlOceanMapGrid1;
    private javax.swing.JPanel pnlOceanMapGrid2;
    private javax.swing.JPanel pnlOceanMapGrid3;
    private javax.swing.JPanel pnlOceanMapGrid4;
    private javax.swing.JPanel pnlOceanMapGrid5;
    private javax.swing.JPanel pnlOceanMapGrid6;
    private javax.swing.JPanel pnlOceanMapGrid7;
    private javax.swing.JPanel pnlOceanMapGrid8;
    private javax.swing.JPanel pnlOceanMapGrid9;
    private javax.swing.JPanel pnlWarriors;
    private javax.swing.JTextArea txaLogPlayer;
    private javax.swing.JTextArea txaMessages;
    private javax.swing.JTextArea txaRanking;
    private javax.swing.JTextArea txaResultadoAtaque1;
    private javax.swing.JTextField txfCommand;
    // End of variables declaration//GEN-END:variables
}
