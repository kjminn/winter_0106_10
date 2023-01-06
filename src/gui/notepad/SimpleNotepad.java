package gui.notepad;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class SimpleNotepad extends JFrame implements ActionListener{
	JMenuItem itemNew, itemOpen, itemSave, itemExit;
	JTextArea area=new JTextArea();
	
	public SimpleNotepad() {
		super("Simple Notepad");
		makeMenu();
		add(area);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 600);
		setVisible(true);
	}

	public void makeMenu(){
		JMenuBar menuBar=new JMenuBar();//메뉴표시줄
		JMenu menuFile=new JMenu("File");//상위메뉴
		//JMenuItem: 상위메뉴 또는 하위메뉴를 클릭했을 때 선택할 수 있는 항목
		itemNew=new JMenuItem("New");
		itemOpen=new JMenuItem("Open");
		itemSave=new JMenuItem("Save");
		itemExit=new JMenuItem("Exit");
		itemNew.addActionListener(this);
		itemOpen.addActionListener(this);
		itemSave.addActionListener(this);
		itemExit.addActionListener(this);
//		File 상위메뉴에 메뉴항목들을 추가
		menuFile.add(itemNew);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.add(itemExit);
//		메뉴바에 File 상위메뉴 추가
		menuBar.add(menuFile);
//		프레임에 메뉴바 설정
		setJMenuBar(menuBar);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem eItem=(JMenuItem)e.getSource();
		if(eItem==itemNew){
			area.setText("");
		}else if(eItem==itemOpen){
			readFile();
		}else if(eItem==itemSave){
			writeFile();
		}else if(eItem==itemExit){
			System.exit(0);
		}
		
	}
	
	public void readFile(){
		area.setText("");
		FileDialog fileDlg=null;
		fileDlg=new FileDialog(this, "File Open", FileDialog.LOAD);
		fileDlg.setVisible(true);
		System.out.println(fileDlg.getDirectory());
		System.out.println(fileDlg.getFile());
		FileReader fReader = null;
		BufferedReader bufReader=null;
		try {
			fReader = new FileReader(new File(fileDlg.getDirectory()+"/"+fileDlg.getFile()));
			bufReader=new BufferedReader(fReader);
			String line="";
			while ((line=bufReader.readLine())!=null) {
				area.append(line+"\n");
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeFile(){
		FileDialog fileDlg=null;
		fileDlg=new FileDialog(this, "File Save", FileDialog.SAVE);
		fileDlg.setVisible(true);
		System.out.println(fileDlg.getDirectory());
		System.out.println(fileDlg.getFile());
		FileWriter fWriter = null;
		BufferedWriter bufWriter=null;
		try {
			fWriter = new FileWriter(new File(fileDlg.getDirectory()+"/"+fileDlg.getFile()));
			bufWriter=new BufferedWriter(fWriter);
			bufWriter.write(area.getText());
			bufWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new SimpleNotepad();
	}
}
