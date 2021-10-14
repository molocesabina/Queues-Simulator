package gui;
import manager.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;
import model.*;

public class SimulationFrame extends JFrame {
	private JTextField simulationTime = new JTextField(30);
  	private JTextField nbClients = new JTextField(30);
  	private JTextField nbQueues = new JTextField(30);
  	private JTextField minArrivalTime = new JTextField(30);
  	private JTextField maxArrivalTime = new JTextField(30);
  	private JTextField minServiceTime = new JTextField(30);
  	private JTextField maxServiceTime = new JTextField(30);
  	
	private JPanel content;
	private JPanel panelButon;
	private JPanel panel;
	private JPanel panelText;
	private JPanel panelLabels;
	
    private Image img = new ImageIcon(getClass().getClassLoader().getResource("back.jpg")).getImage().getScaledInstance(710, 425, Image.SCALE_DEFAULT); 
    private JLabel lblCurrentSim = new JLabel(); 
   
	public SimulationFrame() {
		content = new JPanel() {
            public void paintComponent(Graphics g) {
                 g.drawImage(img, 0, 0, this);
            }
        };
       
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS)); 
        content.setOpaque(true);
        
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setOpaque(false);
        
        JPanel panelUmplutura1 = new JPanel();
        panelUmplutura1.add( Box.createRigidArea(new Dimension(40,0)) );
        panelUmplutura1.setOpaque(false);
        panel.add(panelUmplutura1);
        
        panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels,BoxLayout.Y_AXIS));
        panelLabels.setOpaque(false);
        panelLabels.add( Box.createRigidArea(new Dimension(0,40)) );  
        JLabel lblSimTime = new JLabel("SIMULATION TIME");
        lblSimTime.setForeground(new Color(0, 0, 0));
        lblSimTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblSimTime);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblNbClients = new JLabel("NB. CLIENTS");
        lblNbClients.setForeground(new Color(0, 0, 0));
        lblNbClients.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblNbClients);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblNbQueues = new JLabel("NB. QUEUES");
        lblNbQueues.setForeground(new Color(0, 0, 0));
        lblNbQueues.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblNbQueues);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblMinArrivalTime = new JLabel("MIN ARRIVAL TIME ");
        lblMinArrivalTime.setForeground(new Color(0, 0, 0));
        lblMinArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblMinArrivalTime);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblMaxArrivalTime = new JLabel("MAX ARRIVAL TIME ");
        lblMaxArrivalTime.setForeground(new Color(0, 0, 0));
        lblMaxArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblMaxArrivalTime);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblMinProcessingTime = new JLabel("MIN SERVICE TIME ");
        lblMinProcessingTime.setForeground(new Color(0, 0, 0));
        lblMinProcessingTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblMinProcessingTime);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );
        JLabel lblMaxProcessingTime = new JLabel("MAX SERVICE TIME ");
        lblMaxProcessingTime.setForeground(new Color(0, 0, 0));
        lblMaxProcessingTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        panelLabels.add(lblMaxProcessingTime);
        panelLabels.add( Box.createRigidArea(new Dimension(0,25)) );   
        panel.add(panelLabels);
        
    	panel.add(lblCurrentSim);
    	lblCurrentSim.setFont(new Font("Segoe UI Emoji", Font.BOLD, 19));
    	lblCurrentSim.setVisible(false);
    	
        panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText,BoxLayout.Y_AXIS));
        panelText.setOpaque(false);
        panelText.add( Box.createRigidArea(new Dimension(0,40)) );  
        simulationTime.setBorder(null);
        simulationTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(simulationTime);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        nbClients.setBorder(null);
        nbClients.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(nbClients);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        nbQueues.setBorder(null);
        nbQueues.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(nbQueues);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        minArrivalTime.setBorder(null);
        minArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(minArrivalTime);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        maxArrivalTime.setBorder(null);
        maxArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(maxArrivalTime);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        minServiceTime.setBorder(null);
        minServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelText.add(minServiceTime);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        maxServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        maxServiceTime.setBorder(null);
        panelText.add(maxServiceTime);
        panelText.add( Box.createRigidArea(new Dimension(0,25)) );
        panel.add(panelText);
        
        JPanel panelUmplutura2 = new JPanel();
        panelUmplutura2.add( Box.createRigidArea(new Dimension(40,0)) );
        panelUmplutura2.setOpaque(false);
        panel.add(panelUmplutura2);
        
          
        panelButon = new JPanel();
        panelButon.setOpaque(false);
        JButton start = new JButton("Start");
        start.setFont(new Font("Tahoma", Font.BOLD, 17));
        
        panelButon.add(start);
        panelButon.add( Box.createRigidArea(new Dimension(0,50)) );
        start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				int simulationTimeI = Integer.valueOf(simulationTime.getText());
				int nbClientsI = Integer.valueOf(nbClients.getText());
				int nbQueuesI = Integer.valueOf(nbQueues.getText());
				int minArrivalTimeI = Integer.valueOf(minArrivalTime.getText());
				int maxArrivalTimeI = Integer.valueOf(maxArrivalTime.getText());
				if(nbQueuesI<1) {
					throw new Exception();
				}
				if(minArrivalTimeI>maxArrivalTimeI)
					throw new Exception();
			    int minProcessingTimeI = Integer.valueOf(minServiceTime.getText());
				int maxProcessingTimeI = Integer.valueOf(maxServiceTime.getText());
				if(minProcessingTimeI>maxProcessingTimeI)
					throw new Exception();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Nu ati introdus datele simularii corect!");
					return;
				}
				panelText.setVisible(false);
				panelLabels.setVisible(false);
				panelButon.setVisible(false);
				lblCurrentSim.setVisible(true);
				SimulationManager gen = new SimulationManager(SimulationFrame.this);
				Thread t = new Thread(gen);
				t.start();
			}
        	
        });
        
        content.add(panel);
        content.add(panelButon);
 
        this.setContentPane(content);
        this.pack();
        
        this.setTitle("Queue Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

	}
	
	
	public void updateSimulation(List<Queue> queues, int timeOfSimulation) {	
	    String str = "<html>Time "+timeOfSimulation+"<br/><br/>";
		for(Queue s: queues) {
			str += "\u2B55 : ";
			int ok = 0;
			for(Client c: s.getClients()) {
				ok = 1;
				str += "\u25B2<sub>"+c.getServiceTime()+"</sub>  ";
			}
			if(ok==0) {
				str += "";
			}
			str+="<br/><br/>";
		}
		str +="</html>";
		lblCurrentSim.setText(str);	
	}

	public void updateFinished(float averageWaitingTime, float averageServiceTime, int peakHour) {
		String str = "<html>Simulation ended<br/><br/>";
		str += "Average waiting time : "+String.format("%.2f",averageWaitingTime)+"<br/>";
	    str += "Average service time : "+String.format("%.2f",averageServiceTime)+"<br/>";
		str +="Peak Hour : "+peakHour;
		lblCurrentSim.setText(str);
	}
	public int getNbQueuesI() {
		return Integer.valueOf(nbQueues.getText());
	}

	public int getSimulationTimeI() {
		return Integer.valueOf(simulationTime.getText());
	}

	public int getNbClientsI() {
		return Integer.valueOf(nbClients.getText());
	}

	public int getMinArrivalTimeI() {
		return Integer.valueOf(minArrivalTime.getText());
	}

	public int getMaxArrivalTimeI() {
		return Integer.valueOf(maxArrivalTime.getText());
	}

	public int getMinServiceTimeI() {
		return Integer.valueOf(minServiceTime.getText());
	}

	public int getMaxServiceTimeI() {
		return Integer.valueOf(maxServiceTime.getText());
	}
	

}

