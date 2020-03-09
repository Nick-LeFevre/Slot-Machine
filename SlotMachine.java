import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SlotMachine extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new SlotMachine();
	}
	
	private JLabel spinner1;	// Left digit display
	private JLabel spinner2;	// Middle digit display
	private JLabel spinner3;	// Right digit display
	
	private int[] values;		// Digit values
	
	private JButton startStop;	// Button to start or stop spinner(s)
	
	private int count = 0;
	spinnerThread1 one = new spinnerThread1();
	spinnerThread2 two = new spinnerThread2();
	spinnerThread3 three = new spinnerThread3();
	// Constructor
	public SlotMachine() {
		super("Slots!");
		values = new int[3];
		makeFrame();
	}

	// Makes all three digits start spinning.
	public void startSpinning() {
		one.start();
		two.start();
		three.start();

	}
	// Makes one digit stop spinning.
	// If all digits stop, displays a message if all three digits are the same.
	public void stopSpinning() {
		count += 1;
		if(count ==3) {
			int check = values[0];
			boolean flag = true;
			for(int i = 0; i < 3 && flag; i++) {
				if(values[i] != check) {
					flag = false;
				}
			}
			if(flag) {
				startStop.setText("You Win! :)");
			}
			else {
				startStop.setText("You Lose!");
			}
		}
		if(count == 4) {
			startStop.setText("Stop!");
			count = 0;
			spinnerThread1 one = new spinnerThread1();
			spinnerThread2 two = new spinnerThread2();
			spinnerThread3 three = new spinnerThread3();
			one.start();
			two.start();
			three.start();
		}
	}
	
	private class spinnerThread1 extends Thread {
		public void run() {
			while(count <= 0) {
			try {
				Thread.sleep(10);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			values[0]++;
			if(values[0] == 10) {
				values[0] = 0;
			}
			spinner1.setText(Integer.toString(values[0]));
		}
	}
}
	private class spinnerThread2 extends Thread {
		public void run() {
			while(count <= 1) {
				try {
					Thread.sleep(20);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				values[1]++;
				if(values[1] == 10) {
					values[1] = 0;
				}
				spinner2.setText(Integer.toString(values[1]));
			}
		}
	}
	
	private class spinnerThread3 extends Thread {
		public void run() {
			while(count <= 2) {
				try {

					Thread.sleep(30);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				values[2]++;
				if(values[2] == 10) {
					values[2] = 0;
				}
				spinner3.setText(Integer.toString(values[2]));
			}
		}
	}
	
	// This method is called when Start/Stop button is clicked.
	public void actionPerformed(ActionEvent e) {
		if(startStop.getText().equals("START")) {
			startStop.setText("STOP");
			startSpinning();
		} else {
			stopSpinning();
		}
	}
	
	// Builds the window and makes it appear!
	private void makeFrame() {
		setLayout(new BorderLayout(5, 5));
		
		JPanel spinnerPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		spinner1 = new JLabel("0", JLabel.CENTER);
		spinner1.setFont(new Font(null, Font.BOLD, 40));
		spinnerPanel.add(spinner1);
		spinner2 = new JLabel("0", JLabel.CENTER);
		spinner2.setFont(new Font(null, Font.BOLD, 40));
		spinnerPanel.add(spinner2);
		spinner3 = new JLabel("0", JLabel.CENTER);
		spinner3.setFont(new Font(null, Font.BOLD, 40));
		spinnerPanel.add(spinner3);
		add(spinnerPanel, BorderLayout.CENTER);
		
		startStop = new JButton("START");
		startStop.setFont(new Font(null, Font.ITALIC, 20));
		startStop.addActionListener(this);
		add(startStop, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 150);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}