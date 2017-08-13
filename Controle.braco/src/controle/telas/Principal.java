/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.telas;

import controle.braco.Arduino;
import controle.braco.ArduinoMessageListener;
import controle.braco.Command;
import controle.braco.Constants;
import controle.braco.SendQueue;
import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author ntbra
 */
public class Principal extends javax.swing.JFrame {
    Arduino arduino;
    private final JPanel panels[] = new JPanel[4];
    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private DefaultListModel logList = new DefaultListModel();
    private DefaultListModel<Command> comList = new DefaultListModel<>();
    private static final long DELAY = 200;
    public Principal(String COM) {
        initComponents();
        init(COM);
        jList1.setModel(comList);
        jList2.setModel(logList);
        comList.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                jButton1.setEnabled(comList.getSize() > 0);
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
            }
        });
        service.scheduleAtFixedRate(new SendQueue(arduino), 0, DELAY, TimeUnit.MILLISECONDS);
        arduino.addListener(new ArduinoMessageListener() {
            @Override
            public void messageReceived(String message) {
                if(message.startsWith("OK")){
                    logList.addElement(message);
                    jList2.ensureIndexIsVisible(logList.getSize()-1);
                    
                }
            }

            @Override
            public void onError(Throwable ex) {
                System.err.println(ex.getMessage());
            }
        });
    }
    
    private void init(String COM){
        this.setIconImage(Constants.getIcon());
        arduino = new Arduino(COM, 9600);
        this.setLocationRelativeTo(null);
        enableLessBt(basePanel, false);
        enableLessBt(antePanel, false);
        enableLessBt(bracoPanel, false);
        panels[0] = garraPanel;
        panels[1] = basePanel;
        panels[2] = antePanel;
        panels[3] = bracoPanel;
        KeyboardFocusManager manager =
        KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher());
    }
    
    private void enableLessBt(JPanel panel, boolean enable){
        for(Component c: panel.getComponents()){
            if(!(c instanceof JRadioButton)){
                c.setEnabled(enable);
                if(c instanceof JLabel){
                    if(c.getName() != null && c.getName().equals("check")){
                        JLabel jl = (JLabel)c;
                        if(enable){
                           jl.setIcon(Constants.getConfirmIcon(32));
                        }else{
                            jl.setIcon(Constants.getCancelIcon(32));
                        }
                    }
                }
            }else{
                c.setEnabled(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        garraPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        garraRadio = new javax.swing.JRadioButton();
        garraA = new javax.swing.JButton();
        garraS = new javax.swing.JButton();
        basePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        baseRadio = new javax.swing.JRadioButton();
        baseQ = new javax.swing.JButton();
        baseW = new javax.swing.JButton();
        antePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        anteRadio = new javax.swing.JRadioButton();
        anteQ = new javax.swing.JButton();
        anteW = new javax.swing.JButton();
        bracoPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bracoRadio = new javax.swing.JRadioButton();
        bracoQ = new javax.swing.JButton();
        bracoW = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/arm.png"))); // NOI18N

        garraPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Garra - F3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N
        garraPanel.setMaximumSize(new java.awt.Dimension(250, 250));
        garraPanel.setMinimumSize(new java.awt.Dimension(250, 250));
        garraPanel.setPreferredSize(new java.awt.Dimension(250, 250));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/32/checkmark-32.png"))); // NOI18N
        jLabel4.setName("check"); // NOI18N

        buttonGroup1.add(garraRadio);
        garraRadio.setSelected(true);
        garraRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        garraRadio.setMaximumSize(new java.awt.Dimension(32, 32));
        garraRadio.setMinimumSize(new java.awt.Dimension(32, 32));
        garraRadio.setPreferredSize(new java.awt.Dimension(32, 32));
        garraRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garraRadioActionPerformed(evt);
            }
        });

        garraA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/open.png"))); // NOI18N
        garraA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        garraA.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/open-selected.png"))); // NOI18N
        garraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garraAActionPerformed(evt);
            }
        });

        garraS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/close.png"))); // NOI18N
        garraS.setMargin(new java.awt.Insets(2, 2, 2, 2));
        garraS.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/close-selected.png"))); // NOI18N
        garraS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garraSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout garraPanelLayout = new javax.swing.GroupLayout(garraPanel);
        garraPanel.setLayout(garraPanelLayout);
        garraPanelLayout.setHorizontalGroup(
            garraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(garraPanelLayout.createSequentialGroup()
                .addGroup(garraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(garraPanelLayout.createSequentialGroup()
                        .addComponent(garraRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(garraPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(garraA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(garraS)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        garraPanelLayout.setVerticalGroup(
            garraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(garraPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(garraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(garraA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(garraS, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64)
                .addGroup(garraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(garraRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        basePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Base - F4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N
        basePanel.setMaximumSize(new java.awt.Dimension(250, 250));
        basePanel.setMinimumSize(new java.awt.Dimension(250, 250));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/32/cancel-32.png"))); // NOI18N
        jLabel5.setName("check"); // NOI18N

        buttonGroup1.add(baseRadio);
        baseRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        baseRadio.setMaximumSize(new java.awt.Dimension(32, 32));
        baseRadio.setMinimumSize(new java.awt.Dimension(32, 32));
        baseRadio.setPreferredSize(new java.awt.Dimension(32, 32));
        baseRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseRadioActionPerformed(evt);
            }
        });

        baseQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor.png"))); // NOI18N
        baseQ.setMargin(new java.awt.Insets(2, 2, 2, 2));
        baseQ.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor-selected.png"))); // NOI18N
        baseQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseQActionPerformed(evt);
            }
        });

        baseW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor.png"))); // NOI18N
        baseW.setMargin(new java.awt.Insets(2, 2, 2, 2));
        baseW.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor-selected.png"))); // NOI18N
        baseW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout basePanelLayout = new javax.swing.GroupLayout(basePanel);
        basePanel.setLayout(basePanelLayout);
        basePanelLayout.setHorizontalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basePanelLayout.createSequentialGroup()
                .addGroup(basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(basePanelLayout.createSequentialGroup()
                        .addComponent(baseRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(basePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(baseQ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(baseW)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        basePanelLayout.setVerticalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseQ, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(baseW, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64)
                .addGroup(basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        antePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Antebraço - F5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N
        antePanel.setMaximumSize(new java.awt.Dimension(250, 250));
        antePanel.setMinimumSize(new java.awt.Dimension(250, 250));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/32/cancel-32.png"))); // NOI18N
        jLabel6.setName("check"); // NOI18N

        buttonGroup1.add(anteRadio);
        anteRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anteRadio.setMaximumSize(new java.awt.Dimension(32, 32));
        anteRadio.setMinimumSize(new java.awt.Dimension(32, 32));
        anteRadio.setPreferredSize(new java.awt.Dimension(32, 32));
        anteRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteRadioActionPerformed(evt);
            }
        });

        anteQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor.png"))); // NOI18N
        anteQ.setMargin(new java.awt.Insets(2, 2, 2, 2));
        anteQ.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor-selected.png"))); // NOI18N
        anteQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteQActionPerformed(evt);
            }
        });

        anteW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor.png"))); // NOI18N
        anteW.setMargin(new java.awt.Insets(2, 2, 2, 2));
        anteW.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor-selected.png"))); // NOI18N
        anteW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout antePanelLayout = new javax.swing.GroupLayout(antePanel);
        antePanel.setLayout(antePanelLayout);
        antePanelLayout.setHorizontalGroup(
            antePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(antePanelLayout.createSequentialGroup()
                .addGroup(antePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(antePanelLayout.createSequentialGroup()
                        .addComponent(anteRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(antePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anteQ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anteW)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        antePanelLayout.setVerticalGroup(
            antePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(antePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(antePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anteQ, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(anteW, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64)
                .addGroup(antePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anteRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bracoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Braço - F6", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N
        bracoPanel.setMaximumSize(new java.awt.Dimension(250, 250));
        bracoPanel.setMinimumSize(new java.awt.Dimension(250, 250));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/32/cancel-32.png"))); // NOI18N
        jLabel8.setName("check"); // NOI18N

        buttonGroup1.add(bracoRadio);
        bracoRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bracoRadio.setMaximumSize(new java.awt.Dimension(32, 32));
        bracoRadio.setMinimumSize(new java.awt.Dimension(32, 32));
        bracoRadio.setPreferredSize(new java.awt.Dimension(32, 32));
        bracoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bracoRadioActionPerformed(evt);
            }
        });

        bracoQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor.png"))); // NOI18N
        bracoQ.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bracoQ.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/hor-selected.png"))); // NOI18N
        bracoQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bracoQActionPerformed(evt);
            }
        });

        bracoW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor.png"))); // NOI18N
        bracoW.setMargin(new java.awt.Insets(2, 2, 2, 2));
        bracoW.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconBt/anti-hor-selected.png"))); // NOI18N
        bracoW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bracoWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bracoPanelLayout = new javax.swing.GroupLayout(bracoPanel);
        bracoPanel.setLayout(bracoPanelLayout);
        bracoPanelLayout.setHorizontalGroup(
            bracoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bracoPanelLayout.createSequentialGroup()
                .addGroup(bracoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(bracoPanelLayout.createSequentialGroup()
                        .addComponent(bracoRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bracoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bracoQ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bracoW)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bracoPanelLayout.setVerticalGroup(
            bracoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bracoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bracoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bracoQ, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bracoW, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64)
                .addGroup(bracoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bracoRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.setViewportView(jList1);

        jScrollPane3.setViewportView(jList2);

        jCheckBox1.setText("Armazenar e executar");

        jButton1.setText("Executar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garraPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(antePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bracoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(garraPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(basePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(antePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bracoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void garraRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garraRadioActionPerformed
        radioEvent(evt);
    }//GEN-LAST:event_garraRadioActionPerformed

    private void baseRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseRadioActionPerformed
        radioEvent(evt);
    }//GEN-LAST:event_baseRadioActionPerformed

    private void anteRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteRadioActionPerformed
        radioEvent(evt);
    }//GEN-LAST:event_anteRadioActionPerformed

    private void bracoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bracoRadioActionPerformed
        radioEvent(evt);
    }//GEN-LAST:event_bracoRadioActionPerformed

    private void garraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garraAActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Garra - Abre", "8"));
      }else{
          SendQueue.addMessage("8");
      }
    }//GEN-LAST:event_garraAActionPerformed

    private void garraSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garraSActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Garra - Fecha", "7"));
      }else{
          SendQueue.addMessage("7");
      }
    }//GEN-LAST:event_garraSActionPerformed

    private void baseQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseQActionPerformed
      if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Base - Horário", "5"));
      }else{
          SendQueue.addMessage("5");
      }
    }//GEN-LAST:event_baseQActionPerformed

    private void baseWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseWActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Base - Antihorário", "6"));
        }else{
            SendQueue.addMessage("6");
        }
    }//GEN-LAST:event_baseWActionPerformed

    private void anteQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteQActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Antebraço - Horário", "1"));
        }else{
            SendQueue.addMessage("1");
        }
    }//GEN-LAST:event_anteQActionPerformed

    private void bracoQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bracoQActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Baco - Horário", "3"));
        }else{
            SendQueue.addMessage("3");
        }
    }//GEN-LAST:event_bracoQActionPerformed

    private void anteWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteWActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Antebraço - Antihorário", "2"));
        }else{
            SendQueue.addMessage("2");
        }
    }//GEN-LAST:event_anteWActionPerformed

    private void bracoWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bracoWActionPerformed
        if(jCheckBox1.isSelected()){
          comList.addElement(new Command("Braco - Antihorário", "4"));
        }else{
            SendQueue.addMessage("4");
        }
    }//GEN-LAST:event_bracoWActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        service.shutdown();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        executeList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void executeList(){
        if(!comList.isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    enableAll(antePanel, false);
                    enableAll(bracoPanel, false);
                    enableAll(garraPanel, false);
                    enableAll(basePanel, false);
                    jButton1.setEnabled(false);
                    while(!comList.isEmpty()){
                        Command c = comList.firstElement();
                        arduino.envia(c.getCommand());
                        comList.removeElement(c);
                        try {
                            Thread.sleep(DELAY);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    JRadioButton bt = null;
                    if(baseRadio.isSelected()) bt = baseRadio;
                    else if(anteRadio.isSelected()) bt = anteRadio;
                    else if(bracoRadio.isSelected()) bt = bracoRadio;
                    else if(garraRadio.isSelected()) bt = garraRadio;
                    
                    radioEvent(new ActionEvent(bt, 0, ""));
                }
            }).start();
        }
    }
    
    private void enableAll(JPanel panel, boolean enable){
        for(Component c: panel.getComponents()){
            c.setEnabled(enable);
        }
    }
    
    private void radioEvent(java.awt.event.ActionEvent evt){
        Component comp = (Component)evt.getSource();
        if(comp instanceof JRadioButton){
            JRadioButton bt = (JRadioButton)comp;
            if(bt.isSelected()){
                JPanel panel = (JPanel)bt.getParent();
                for(JPanel c: panels){
                    if(!c.equals(panel)){
                        enableLessBt((JPanel)c, false);
                    }else{
                        enableLessBt((JPanel)c, true);
                    }
                }
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel antePanel;
    private javax.swing.JButton anteQ;
    private javax.swing.JRadioButton anteRadio;
    private javax.swing.JButton anteW;
    private javax.swing.JPanel basePanel;
    private javax.swing.JButton baseQ;
    private javax.swing.JRadioButton baseRadio;
    private javax.swing.JButton baseW;
    private javax.swing.JPanel bracoPanel;
    private javax.swing.JButton bracoQ;
    private javax.swing.JRadioButton bracoRadio;
    private javax.swing.JButton bracoW;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton garraA;
    private javax.swing.JPanel garraPanel;
    private javax.swing.JRadioButton garraRadio;
    private javax.swing.JButton garraS;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<Command> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

class KeyDispatcher implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        switch(e.getID()){
            case KeyEvent.KEY_TYPED:{
                break;
            }
            
            case KeyEvent.KEY_PRESSED:{
                switch(e.getKeyCode()){
                    case 114:{
                        garraRadio.setSelected(true);
                        radioEvent(new ActionEvent(garraRadio, 0, ""));
                        break;
                    }
                    case 115:{
                        baseRadio.setSelected(true);
                        radioEvent(new ActionEvent(baseRadio, 0, ""));
                        break;
                    }
                    case 116:{
                        anteRadio.setSelected(true);
                        radioEvent(new ActionEvent(anteRadio, 0, ""));
                        break;
                    }
                    case 117:{
                        bracoRadio.setSelected(true);
                        radioEvent(new ActionEvent(bracoRadio, 0, ""));
                        break;
                    }
                    case 65:{
                        //a
                        if(garraRadio.isSelected()){
                            garraAActionPerformed(new ActionEvent(e, -1, null));
                            garraA.requestFocus();
                        }
                        break;
                    }
                    case 83:{
                        //s
                        if(garraRadio.isSelected()){
                            garraSActionPerformed(new ActionEvent(e, -1, null));
                            garraS.requestFocus();
                        }
                        break;
                    }
                    case 81:{
                        //q
                        if(baseRadio.isSelected()){
                            baseQActionPerformed(null);
                            baseQ.requestFocus();
                        }else if(anteRadio.isSelected()){
                            anteQActionPerformed(null);
                            anteQ.requestFocus();
                        }else if(bracoRadio.isSelected()){
                            bracoQActionPerformed(null);
                            bracoQ.requestFocus();
                        }
                        break;
                    }
                    case 87:{
                        //w
                        if(baseRadio.isSelected()){
                            baseWActionPerformed(null);
                            baseW.requestFocus();
                        }else if(anteRadio.isSelected()){
                            anteWActionPerformed(null);
                            anteW.requestFocus();
                        }else if(bracoRadio.isSelected()){
                            bracoWActionPerformed(null);
                            bracoW.requestFocus();
                        }
                        break;
                    }
                    default:{
                        System.out.println(e.getKeyCode()+" - "+e.getKeyChar());
                    }
                }
                break;
            }
            case KeyEvent.KEY_RELEASED:{
                break;
            }
            default: System.out.println("ERRRO: "+e.getID());
        }
        return false;
    }
    }

}