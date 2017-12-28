/**
 * ��ȡexcel�ĳ���
 */

package com.reagent.all;

import javax.swing.*;
import javax.swing.table.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame implements ActionListener{

	//������Ҫ�����
	JLabel jlb1,jlb2,jlb3;
	JTextField jtf;
	JButton jb;
	JTable jtb;
	JPanel jp;
	JScrollPane jsp;
	
	//����jtable�Ĳ���
	DefaultTableModel dtl;
	Vector data,colName,all;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
	}

	//���캯��
	public Main(){
		
		//��������
		colName=new Vector();
		colName.add("����");
		colName.add("����");
		dtl=new DefaultTableModel(data,colName);
		
		//����ؼ�
		jp=new JPanel();
		jp.setLayout(null);
		
		jlb1=new JLabel("���ֵ��");
		jlb1.setFont(new Font("����", Font.PLAIN, 16));
		jlb2=new JLabel("�������");
		jlb2.setFont(new Font("����", Font.PLAIN, 16));
		jlb3=new JLabel();
		jtf=new JTextField(10);
		
		jb=new JButton("ִ��");
		jb.setFont(new Font("����", Font.PLAIN, 16));
		jb.setBackground(Color.WHITE);
		jb.addActionListener(this);
		jb.setActionCommand("ִ��");
		
		jtb=new JTable(dtl);
		jsp=new JScrollPane(jtb);
		
		jlb1.setBounds(45, 15, 80, 35);
		jtf.setBounds(115, 15, 125, 30);
		jb.setBounds(300, 15, 130, 30);
		jlb2.setBounds(10, 90, 100 ,25);
		jlb3.setBounds(100, 85, 280, 30);
		jsp.setBounds(10, 122, 450, 315);
		
		
		//��ӿؼ�
		jp.add(jlb1);
		jp.add(jtf);
		jp.add(jb);
		jp.add(jlb2);
		jp.add(jlb3);
		jp.add(jsp);
		jp.setBackground(Color.WHITE);
		
		readExcel();
		
		this.add(jp);
		this.setTitle("�Լ����");
		this.setSize(500,500);
		this.setLocation(480, 180);
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//��ȡexcel���
	public void readExcel(){
		
		String fileName = "C:\\Users\\KevinSmith\\Desktop\\�Լ���ȡ����\\�Լ�����������.xlsx"; 
		all=new Vector();
		XSSFWorkbook xssfWorkbook;
		try {
			xssfWorkbook = new XSSFWorkbook( fileName);
			// ѭ��������Sheet  
			for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){  
				  XSSFSheet xssfSheet = xssfWorkbook.getSheetAt( numSheet);  
				  if(xssfSheet == null){  
				    continue;  
				  }
				  // ѭ����Row   
				  for(int rowNum = 4; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){  
					    XSSFRow xssfRow = xssfSheet.getRow( rowNum);  
					    if(xssfRow == null){  
					      continue;  
					    }
					    Vector temp=new Vector();
						// ѭ����Cell     
						for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
							  XSSFCell xssfCell = xssfRow.getCell(cellNum);  
							  if(xssfCell == null){  
							    continue;  
							  }
							  if(cellNum==0 || cellNum==xssfRow.getLastCellNum()-2){
								  temp.add(getValue(xssfCell).toString());
								  
							  } 
						}
						all.add(temp);
				 }
				 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//��ȡÿ��Ԫ�ص�ֵ
	private String getValue(XSSFCell xssfCell){  
		if(xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN){  
		  return String.valueOf( xssfCell.getBooleanCellValue());  
		}else if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){  
		  return String.valueOf( xssfCell.getNumericCellValue());  
		}else{  
		  return String.valueOf( xssfCell.getStringCellValue());  
		}  
	}
	
	//��ȡ����ܴ���
	private double getAddTotal(double total){
		double all=0;
		
		if(total%7==0){
			all=total;
		}else{
			total=(int)(total/7)+1;
			all=total*7;
		}
		return all;
	}
	
	//�¼���Ӧ����
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("ִ��")){

			data=new Vector();
			jtb.removeAll();
			
			double alluse,use;
			for(int i=0;i<all.size();i++){
				Vector temp=(Vector) all.get(i);
				alluse=getAddTotal(Double.parseDouble((temp.get(1).toString().trim())));
				use=Double.parseDouble(temp.get(1).toString().trim());
				
				
				if(jtf.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "��������ֵ");
					return;
				}
				double fundation=Double.parseDouble(jtf.getText());
				
				if(alluse-use<=fundation)
				{
					data.add(temp);
				}
			}
			if(data.size()==0){
				jlb3.setFont(new Font("����", Font.PLAIN, 16));
				jlb3.setForeground(Color.RED);
				jlb3.setText("û���Լ���Ҫ��ӣ�");
			}else{
				jlb3.setFont(new Font("����", Font.PLAIN, 16));
				jlb3.setForeground(Color.RED);
				jlb3.setText("���ã������Լ���Ҫ���첹��");
			}
			dtl=new DefaultTableModel(data,colName);
			jtb.setModel(dtl);
			
			String message = "";
			for(int i=0;i<data.size();i++){
				
				Vector temp=(Vector)data.get(i);
				message=message+temp.get(0)+"��";
				if(i%5==0)
				{
					message=message+"\r\n";
				}
			}
			message="�����Լ���Ҫ���"+"\r\n"+message;
			Warning w=new Warning(message);
		}
	}
}
