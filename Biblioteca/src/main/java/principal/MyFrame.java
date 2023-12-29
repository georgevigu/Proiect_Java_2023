package principal;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import principal.Book;

public class MyFrame extends JFrame implements ActionListener{

	JLabel label, label2, label3, label4, label5, label6;
	JButton button, button2;
	JTextField textField, textField2;
	JPanel panel1, panel2, panel3, panel4, panel5;
	JComboBox comboBox;
	JRadioButton ro, en, it;
	ButtonGroup radio;
	JCheckBox checkBox;

	Font BoldMonospaced = new Font("Monospaced", Font.BOLD, 15);

	public MyFrame() {

		label = new JLabel();
		label.setText("Titlu:  ");
		label.setPreferredSize(new Dimension(50,30));

		label2 = new JLabel();
		label2.setText("Autor: ");
		label2.setPreferredSize(new Dimension(50,30));

		label3 = new JLabel();
		label3.setText("Categorie: ");
		label3.setPreferredSize(new Dimension(70,30));

		label4 = new JLabel();
		label4.setText("Limba: ");
		label4.setPreferredSize(new Dimension(50,30));

		label5 = new JLabel();
		label5.setText("Audiobook? ");
		label5.setPreferredSize(new Dimension(70,30));
		
		label6 = new JLabel();
		label6.setText("Cartile au fost salvate!");
		label6.setBounds(10, 240, 300, 30);
		label6.setVisible(false);

		button = new JButton();
		button.addActionListener(this);
		button.setText("Adaugă");
		button.setFocusable(false);
		button.setBounds(260,200, 100,30);

		button2 = new JButton();
		button2.addActionListener(this);
		button2.setText("Salvează");
		button2.setFocusable(false);
		button2.setBounds(260,240,100,30);

		ro = new JRadioButton("Romana");
		ro.setSelected(true);
		en = new JRadioButton("Engleza");
		it = new JRadioButton("Italiana");
		radio = new ButtonGroup();
		radio.add(ro);
		radio.add(en);
		radio.add(it);


		textField = new JTextField();
		textField.setPreferredSize(new Dimension(300,30));
		textField.setFont(BoldMonospaced);

		textField2 = new JTextField();
		textField2.setPreferredSize(new Dimension(300,30));
		textField2.setFont(BoldMonospaced);

		String[] categorii = {"Poezie","Teatru","Beletristica","Manga","Dezvoltare personala","Stiinte"};
		comboBox = new JComboBox(categorii);
		comboBox.setFont(BoldMonospaced);

		checkBox = new JCheckBox();

		panel1 = new JPanel();
		panel1.setBounds(0, 0, 600, 40);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panel1.setBackground(Color.green);
		panel1.add(label);
		panel1.add(textField);

		panel2 = new JPanel();
		panel2.setBounds(0, 40, 600, 40);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panel2.setBackground(Color.cyan);
		panel2.add(label2);
		panel2.add(textField2);

		panel3 = new JPanel();
		panel3.setBounds(0, 80, 600, 40);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panel3.setBackground(Color.green);
		panel3.add(label3);
		panel3.add(comboBox);

		panel4 = new JPanel();
		panel4.setBounds(0, 120, 600, 40);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panel4.setBackground(Color.green);
		panel4.add(label4);
		panel4.add(ro);
		panel4.add(en);
		panel4.add(it);

		panel5 = new JPanel();
		panel5.setBounds(0, 160, 600, 40);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(label5);
		panel5.add(checkBox);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(400,400);
		this.setTitle("Proiect Java 2023");
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(button);
		this.add(button2);
		this.add(label6);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        
		    }
		});
		this.setVisible(true);


	}

	//Citire din fisier json
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) { //butonul de "Adauga"
			if(!(textField.getText().equals("")) && !(textField2.getText().equals(""))) {
				
				//Afiseaza mesaj de succes
				JOptionPane.showMessageDialog(this, "Carte adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE); 
				System.out.println("S-a adaguat cartea " + textField.getText()
				+ " scrisa de " + textField2.getText());

				//Luam datele inserate
				String titlu = textField.getText();
				String autor = textField2.getText();
				String categorie = (String) comboBox.getSelectedItem();
				String limba = "";
				boolean audio = false;
				if(ro.isSelected()) {limba = ro.getText();}
				else if(en.isSelected() ) {limba = en.getText();}
				else if(it.isSelected()) {limba = it.getText();}
				if(checkBox.isSelected()) {audio = true;}

				//Resetam campurile de text
				textField.setText("");
				textField2.setText("");
				
				//Adaugam carte in lista
				Book carteNoua = new Book(titlu,autor,categorie,limba,audio);
				Main.carti.add(carteNoua);
				
			}
			else {
				//Afiseaza eroare
				JOptionPane.showMessageDialog(this, "Nu s-a putut adăuga cartea!", "Eroare", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==button2) { //butonul de "Salveaza"
			//Afiseaza mesaj
			label6.setVisible(true);
			try {
				//Adaugam toate cartile noi in fisierul json
				ArrayList<Book> toateCartile = new ArrayList<Book>();
				toateCartile.addAll(Main.carti);
				Main.writeJson(toateCartile);
			} catch (StreamReadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DatabindException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
