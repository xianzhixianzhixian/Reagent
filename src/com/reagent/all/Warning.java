package com.reagent.all;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Warning extends JFrame implements ActionListener{

	//����ؼ�
	JButton jb;
	JTextArea jta;
	JLabel jlb;
	
	public Warning(String message){
		
		jlb=new JLabel();
		jlb.setIcon(new ImageIcon("image/3.png"));
		
		jta=new JTextArea();
		jta.setEditable(false);
		jta.setText(message);
		
		jb=new JButton("ȷ��");
		jb.setBackground(Color.WHITE);
		jb.setFont(new Font("����", Font.PLAIN, 16));
		jb.addActionListener(this);
		jb.setActionCommand("ȷ��");
		
		MusicDemo music=new MusicDemo();
		
		this.add(jta,"Center");
		this.add(jlb, "West");
		this.add(jb,"South");
		
		this.setTitle("����");
		this.setLocation(600, 300);
		this.setSize(300,200);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("ȷ��")){
			this.dispose();
		}
	}
	
}