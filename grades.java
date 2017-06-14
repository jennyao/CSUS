//Jenny Yao
//Gradebook Project
//Due Dec 17, 2015
//work on rowdata array, table, deleteSelect, labScores
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class grades extends JFrame implements ActionListener {
   static JPanel bottom, sub, sub2, card1, card2, card3, card4, card5, card6, card7, card8;
   static JLabel label2b;
   static JTextField className, className2, className3, numStudents, Name, Age, labNum, scoreFor, SSN;
   static JTable table, abTable;
   static Container contentPane;
   static CardLayout contentPaneLayout = new CardLayout();
   static ActionListener AL = new grades();
   //static JComboBox cbxGender, cbxYear;
   static JScrollPane scrollPane = new JScrollPane();   
   static int studCount, labNumber, studentCurrent, currentS;
   static String CN;     //to get the input info from designated TextField
   static JRadioButton rb1 = new JRadioButton("By ID", true);     //put in true to initialize it
   static JRadioButton rb2 = new JRadioButton("By Name");
   static JButton brb4 = new JButton("Back to Top Menu");
   static JButton brb5 = new JButton("Back to Top Menu");
   static JButton brb6 = new JButton("Back to Top Menu");
   static JButton brb7 = new JButton("Back to Top Menu");
   static JButton newClass = new JButton ("0. Create a New Class");
   static JButton loadStud = new JButton ("1. Load Students from the File");
   static JButton addNew = new JButton ("2. Add New Students");
   static JButton enterScores = new JButton ("3. Enter Lab Scores");
   static JButton sortStud = new JButton ("4. Sort Students");
   static JButton viewDelete = new JButton ("5. View/Delete Students");
   static JButton Backup = new JButton ("6. Backup Students To the File");
   static JButton Exit = new JButton ("7. Exit");
   static JButton create = new JButton("Create");
   static JButton cancel = new JButton("Cancel");
   static JButton cancel2 = new JButton("Cancel");
   static JButton cancel3 = new JButton("Cancel");
   static JButton load = new JButton("Load");
   static JButton load2 = new JButton("Load");
   static JButton sort = new JButton("Sort");
   static JButton saveAdd = new JButton("Save & Add Student");
   static JButton deleteSelect = new JButton("Delete Selected");
   
   static Students[] studA = new Students[100];
   
   static String[][] rowData = new String[100][100];  //25, 15
   static String[] colName = new String[] 
      {"ID", "Name", "Level", "Gender", "Age", "Lab 1", "Lab 2", "Lab 3", "Lab 4", "Lab 5", "Lab 6", "Lab 7", "Lab 8", "Lab 9", "Lab 10"};   
   static String[] cbxYear = new String[] { "Freshman", "Sophomore", "Junior", "Senior", "Graduate" };
   static String[] cbxGender = new String[] { "Male", "Female" };
   static FileOutputStream fos;  static ObjectOutputStream oos;   static ObjectInputStream ois;
   
   public static void main(String[] args) throws IOException {
      //main title frame
      JFrame frm = new JFrame("Jen's CSC 20 Gradebook");
      contentPane = frm.getContentPane();
      contentPane.setLayout(contentPaneLayout);      
      //BEGINNING OF CARD1
      JLabel label = new JLabel("<html><font size = 5><b>Use The Buttons Below To Manage Students</b></html>", JLabel.CENTER);
      JLabel label1 = new JLabel("Class Name: ", JLabel.CENTER);
      className = new JTextField(15);
      className.setEditable(false);
      JLabel label2 = new JLabel("Number of students: ", JLabel.CENTER);
      numStudents = new JTextField(10);
      numStudents.setEditable(false);
      //create the outside panel
      card1 = new JPanel(new BorderLayout());
      card1.add(label, BorderLayout.NORTH);
      //create a panel sub
      JPanel sub = new JPanel(new FlowLayout());
      //add labels and text fields to panel sub
      sub.add(label1);
      sub.add(className);
      sub.add(label2);
      sub.add(numStudents);
      //add panel sub to panel mid
      card1.add(sub, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new GridLayout(2, 4));
      //add buttons to panel bottom
      bottom.add(newClass);
      bottom.add(loadStud);
      bottom.add(addNew);
      bottom.add(enterScores);
      bottom.add(sortStud);
      bottom.add(viewDelete);
      bottom.add(Backup);
      bottom.add(Exit);
      //add panel bottom to panel mid
      card1.add(bottom, BorderLayout.SOUTH);    
      //contentPane.add(card1, BorderLayout.CENTER);
      contentPane.add(card1, "Card 1");
      //action listeners of buttons
      newClass.addActionListener(AL);
      loadStud.addActionListener(AL);
      addNew.addActionListener(AL);
      enterScores.addActionListener(AL);
      sortStud.addActionListener(AL);
      viewDelete.addActionListener(AL);
      Backup.addActionListener(AL);
      Exit.addActionListener(AL);
      //END OF CARD 1
      
      //BEGINNING OF CARD2
      label = new JLabel("<html><font size = 5><b>Create a New Class</b></html>", JLabel.CENTER);
      label1 = new JLabel("Class Name: ", JLabel.CENTER);
      className2 = new JTextField(15);
      //outer panel
      card2 = new JPanel(new BorderLayout());
      card2.add(label, BorderLayout.NORTH);
      //create a panel sub
      sub = new JPanel(new FlowLayout());
      //add labels and text fields to panel sub
      sub.add(label1);
      sub.add(className2);
      //add panel sub to panel mid
      card2.add(sub, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      create.setBounds(2,2,50,25);
      cancel.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(create);
      bottom.add(cancel);
      //add panel bottom to panel mid
      card2.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card2, "Card 2");
      create.addActionListener(AL);
      cancel.addActionListener(AL);
      //END OF CARD 2
            
      //BEGINNING OF CARD3
      label = new JLabel("<html><font size = 5><b>Load Students From a File</b></html>", JLabel.CENTER);
      label1 = new JLabel("Class Name: ", JLabel.CENTER);
      className3 = new JTextField(15);
      //outer panel
      card3 = new JPanel(new BorderLayout());
      card3.add(label, BorderLayout.NORTH);
      //create a panel sub
      sub = new JPanel(new FlowLayout());
      //add labels and text fields to panel sub
      sub.add(label1);
      sub.add(className3);
      //add panel sub to panel mid
      card3.add(sub, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      load.setBounds(2,2,50,25);
      cancel2.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(load);
      bottom.add(cancel2);
      //add panel bottom to panel mid
      card3.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card3, "Card 3");
      cancel2.addActionListener(AL);
      load.addActionListener(AL);
      //END OF CARD 3
      
      //START OF CARD4
      label = new JLabel("<html><font size = 5><b>Enter Student Information</b></html>", JLabel.CENTER);
      label1 = new JLabel("SSN: ", JLabel.RIGHT);
      SSN = new JTextField(15);    
      label2 = new JLabel("Name: ", JLabel.RIGHT);
      Name = new JTextField(15);      
      JLabel label3 = new JLabel("Gender: ", JLabel.RIGHT);      
      JLabel label4 = new JLabel("Age: ", JLabel.RIGHT);
      Age = new JTextField(15);
      JLabel label5 = new JLabel("Academic Level: ", JLabel.RIGHT);
      //outer panel
      card4 = new JPanel(new BorderLayout());
      card4.add(label, BorderLayout.NORTH);
      //create a panel sub
      sub = new JPanel();
      //sub.setLayout(new BoxLayout(sub, BoxLayout.Y_AXIS));
      sub = new JPanel(new GridLayout(5, 2));
      //add labels and text fields to panel sub
      sub.add(label1);
      sub.add(SSN);
      SSN.setHorizontalAlignment(JTextField.LEFT);
      //insert name
      sub.add(label2);
      sub.add(Name);
      Name.setHorizontalAlignment(JTextField.LEFT);
      //insert gender
      sub.add(label3);
      sub.add(new JComboBox(cbxGender));
      //insert age
      sub.add(label4);
      sub.add(Age);
      Age.setHorizontalAlignment(JTextField.LEFT);
      //insert academic level
      sub.add(label5);
      sub.add(new JComboBox(cbxYear));
      //add panel sub to panel mid
      card4.add(new JComboBox(cbxGender));
      card4.add(new JComboBox(cbxYear));
      card4.add(sub, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      saveAdd.setBounds(2,2,50,25);
      brb4.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(saveAdd);
      bottom.add(brb4);
      //add panel bottom to panel mid
      card4.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card4, "Card 4");
      brb4.addActionListener(AL);
      saveAdd.addActionListener(AL);
      //END OF CARD4
      
      //BEGINNING OF CARD5
      label = new JLabel("<html><font size = 5><b>Enter Lab Scores</b></html>", JLabel.CENTER);
      JLabel label2a = new JLabel("Lab Number: ", JLabel.CENTER);
      labNum = new JTextField(5);
      label2b = new JLabel("Score for  :", JLabel.CENTER);
      scoreFor = new JTextField(10);
      //outer panel
      card5 = new JPanel(new GridLayout(0,1));
      card5.add(label);
      //create a panel sub
      sub = new JPanel(new FlowLayout());
      sub2 = new JPanel(new FlowLayout());
      //add labels and text fields to panel sub
      sub.add(label2a);
      sub.add(labNum);
      sub2.add(label2b);
      sub2.add(scoreFor);
      //add panel sub to panel mid
      card5.add(sub);
      card5.add(sub2);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      brb5.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(brb5);
      //add panel bottom to panel mid
      card5.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card5, "Card 5");
      brb5.addActionListener(AL);
      scoreFor.addActionListener(AL);
      labNum.addActionListener(AL);
      //END OF CARD 5
      
      //BEGINNING OF CARD 6
      label = new JLabel("<html><font size = 5><b>Sort Students</b></html>", JLabel.CENTER);
      ButtonGroup bg1 = new ButtonGroup();
      bg1.add(rb1);
      bg1.add(rb2);
      //create outer panels
      card6 = new JPanel(new BorderLayout());
      card6.add(label, BorderLayout.NORTH);
      //create a panel sub
      sub = new JPanel(new FlowLayout());
      //add radio button group to panel sub
      sub.add(rb1);
      sub.add(rb2);
      //add panel sub to panel mid
      card6.add(sub, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      sort.setBounds(2,2,50,25);
      brb6.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(sort);
      bottom.add(brb6);
      //add panel bottom to panel mid
      card6.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card6, "Card 6");
      brb6.addActionListener(AL);
      //END OF CARD 6
      
      //BEGINNING OF CARD7
      label = new JLabel("<html><font size = 4><b>Student List</b></html>", JLabel.CENTER);      
      card7 = new JPanel(new BorderLayout());
      card7.add(label, BorderLayout.NORTH);
      //create a panel sub
      sub = new JPanel(new FlowLayout());
      table = new JTable(rowData, colName);
      table.setPreferredScrollableViewportSize(new Dimension(800, 80));
      table.setFillsViewportHeight(true);
      card7.add(scrollPane, BorderLayout.CENTER);
      //create a panel bottom  
      bottom = new JPanel(new FlowLayout());
      //set size of buttons
      deleteSelect.setBounds(2,2,50,25);
      brb7.setBounds(2,2,50,25);
      //add buttons to panel bottom
      bottom.add(deleteSelect);
      bottom.add(brb7);
      //add panel bottom to panel mid
      card7.add(bottom, BorderLayout.SOUTH);    
      contentPane.add(card7, "Card 7");
      brb7.addActionListener(AL);
      deleteSelect.addActionListener(AL);
      //END OF CARD 7
      
      //DON'T NEED CARD8
      Backup.addActionListener(AL);
      
      frm.pack();
		frm.setSize(900,300);
     	frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
      
   }
   
      public void actionPerformed(ActionEvent e) {
         Object source = e.getSource();
         if (source == create) {
            CN = className2.getText();
            studCount = 0;
            className.setText(CN);
            numStudents.setText("" + studCount);
            contentPaneLayout.show(contentPane, "Card 1");
         }
         if (source == cancel) contentPaneLayout.show(contentPane, "Card 1");
         if (source == cancel2) contentPaneLayout.show(contentPane, "Card 1");
         if (source == cancel3) contentPaneLayout.show(contentPane, "Card 1");
         if (source == brb4) contentPaneLayout.show(contentPane, "Card 1");
         if (source == brb5) contentPaneLayout.show(contentPane, "Card 1");
         if (source == brb6) contentPaneLayout.show(contentPane, "Card 1");
         if (source == brb7) contentPaneLayout.show(contentPane, "Card 1");
         if (source == newClass) contentPaneLayout.show(contentPane, "Card 2");
         if (source == loadStud) contentPaneLayout.show(contentPane, "Card 3");
         if (source == load) {
            Students St;
            try {
               CN = className3.getText();
               System.out.println("className3 is " + CN);
               studCount = 0;
               //reading a file
               ois = new ObjectInputStream(new FileInputStream(CN));
               while (true) {
                  St = (Students)ois.readObject();
                  System.out.println("student name is " + St.NameS); 
                  studA[studCount] = St;
                  studCount++;
               }
            } catch (EOFException ey) {
            } catch (Exception ex) { 
               ex.printStackTrace();
            }
            contentPaneLayout.show(contentPane, "Card 3");
         }
         if (source == addNew) contentPaneLayout.show(contentPane, "Card 4");
         if (source == saveAdd) {
            Students temp = new Students();
            temp.SSN = Integer.valueOf(SSN.getText()).intValue();
            temp.NameS = Name.getText();
            temp.AgeC = Integer.valueOf(Age.getText()).intValue();
            studA[studCount] = temp;
            studCount++;
            SSN.setText("");
            Name.setText("");
            Age.setText("");
            numStudents.setText("" + studCount);
         }
         if (source == labNum) {        
            studentCurrent = 0;
            label2b.setText("Score for " + studA[studentCurrent].NameS + ":");
            labNumber = Integer.valueOf(labNum.getText()).intValue();
         }
         if (source == scoreFor) {   
            currentS = Integer.valueOf(scoreFor.getText()).intValue();
               switch (labNumber) {
                  case 1:
                     studA[studentCurrent].Lab1 = currentS; break;
                  case 2:
                     studA[studentCurrent].Lab2 = currentS; break;
                  case 3:
                     studA[studentCurrent].Lab3 = currentS; break;
                  case 4:
                     studA[studentCurrent].Lab4 = currentS; break;
                  case 5:
                     studA[studentCurrent].Lab5 = currentS; break;
                  case 6:
                     studA[studentCurrent].Lab6 = currentS; break;
                  case 7:
                     studA[studentCurrent].Lab7 = currentS; break;
                  case 8:
                     studA[studentCurrent].Lab8 = currentS; break;
                  case 9:
                     studA[studentCurrent].Lab9 = currentS; break;
                  case 10:
                     studA[studentCurrent].Lab10 = currentS; break;
               }
               studentCurrent++;
               label2b.setText("");
                  if (studentCurrent < studCount) {
                     labNumber = Integer.valueOf(labNum.getText()).intValue();
                     System.out.println("labNumber is " + labNumber);
                     label2b.setText("Score for " + studA[studentCurrent].NameS + ":");
                  } else {
                     contentPaneLayout.show(contentPane, "Card 1");
                  }
         }
         if (source == enterScores)  contentPaneLayout.show(contentPane, "Card 5");
         if (source == sortStud) contentPaneLayout.show(contentPane, "Card 6");
         if (source == viewDelete) {
            rowData = new String[studCount][15];
            for (int i = 0; i < studCount; i++) {
               rowData[i][0] = studA[i].SSN + "";
               rowData[i][1] = studA[i].NameS + "";
               rowData[i][2] = cbxYear[studA[i].Year];
               rowData[i][3] = cbxGender[studA[i].Gender];
               rowData[i][4] = studA[i].AgeC + "";
               rowData[i][5] = studA[i].Lab1 + "";
               rowData[i][6] = studA[i].Lab2 + "";
               rowData[i][7] = studA[i].Lab3 + "";
               rowData[i][8] = studA[i].Lab4 + "";
               rowData[i][9] = studA[i].Lab5 + "";
               rowData[i][10] = studA[i].Lab6 + "";
               rowData[i][11] = studA[i].Lab7 + "";
               rowData[i][12] = studA[i].Lab8 + "";
               rowData[i][13] = studA[i].Lab9 + "";
               rowData[i][14] = studA[i].Lab10 + "";
            }
            abTable = new JTable(rowData, colName);
            JScrollPane tmp = new JScrollPane(abTable);
            scrollPane.setViewport(tmp.getViewport());
            contentPaneLayout.show(contentPane, "Card 7");
         }
         if (source == deleteSelect) {
            int del = abTable.getSelectedRow();
            for (int k = del; k < studCount - 1; ++k) {
               studA[k] = studA[k+1];
            }
            studCount--;
            rowData = new String[studCount][15];
            for (int i = 0; i < studCount; i++) {
               rowData[i][0] = studA[i].SSN + "";
               rowData[i][1] = studA[i].NameS + "";
               rowData[i][2] = cbxYear[studA[i].Year];
               rowData[i][3] = cbxGender[studA[i].Gender];
               rowData[i][4] = studA[i].AgeC + "";
               rowData[i][5] = studA[i].Lab1 + "";
               rowData[i][6] = studA[i].Lab2 + "";
               rowData[i][7] = studA[i].Lab3 + "";
               rowData[i][8] = studA[i].Lab4 + "";
               rowData[i][9] = studA[i].Lab5 + "";
               rowData[i][10] = studA[i].Lab6 + "";
               rowData[i][11] = studA[i].Lab7 + "";
               rowData[i][12] = studA[i].Lab8 + "";
               rowData[i][13] = studA[i].Lab9 + "";
               rowData[i][14] = studA[i].Lab10 + "";
            }
            abTable = new JTable(rowData, colName);
            JScrollPane tmp = new JScrollPane(abTable);
            scrollPane.setViewport(tmp.getViewport());
            contentPaneLayout.show(contentPane, "Card 7");
         }
         if (source == Backup) { 
            //CN = className3.getText();
            System.out.println("className is " + CN);
            if (CN.equals("")) System.exit(0);
            else {
               try {
               //create new file
               studCount = rowData.length;
               //a file of objects can not be opened for append
               fos = new FileOutputStream(CN);
               oos = new ObjectOutputStream(fos);
               //write something in file
               for (int i = 0; i < studCount; i++) {
                  oos.writeObject(studA[i]);
               }
               oos.close();
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }
         }
         if (source == Exit) System.exit(0); 
      }
} //End class grades

class Students implements Serializable { int SSN; String NameS; int Year; int Gender; int AgeC; int Lab1; int Lab2; int Lab3; int Lab4; int Lab5; int Lab6; int Lab7; int Lab8; int Lab9; int Lab10; }