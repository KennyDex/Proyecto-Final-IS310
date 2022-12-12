/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dex.kenny.proyecto;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 *
 * @author usuario
 */
class Principal extends JFrame implements ActionListener {
    JTextArea t; //Componente Text
    JFrame f; //Frame
    
    Principal(){
        f = new JFrame("Editor de Texto"); // Crea un JFrame
        try{
            //Pequeña personalizacion del JFrame
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }catch(Exception e){
            
        }
        t = new JTextArea();
        JMenuBar mb = new JMenuBar(); //crea un Menu Bar
        
        //---------------------MENU ARCHIVO-------------------------------------
        
        JMenu m1 = new JMenu("Archivo"); //Crea el amenu Archivo
        
        JMenuItem mi1 = new JMenuItem("Nuevo"); // se crean los items del amenu Archivo
        JMenuItem mi2 = new JMenuItem("Abrir");
        JMenuItem mi3 = new JMenuItem("Guardar");
        JMenuItem mi9 = new JMenuItem("Imprimir");
        
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
       
        //----------------------------------------------------------------------
        
        //--------------------MENU EDITAR---------------------------------------
        
        JMenu m2 = new JMenu("Editar"); // Crea el amenu Editar
        
        JMenuItem mi4 = new JMenuItem("Cortar"); //Se crean los items del amenu Editar
        JMenuItem mi5 = new JMenuItem("Copiar");
        JMenuItem mi6 = new JMenuItem("Pegar");
        
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        
        //----------------------------------------------------------------------
        
        //-------------------------------CERRAR---------------------------------
        
        JMenuItem mc = new JMenuItem("Cerrar"); //Se crea el amenu Cerrar
        
        mc.addActionListener(this);
        
        //----------------------------------------------------------------------
        
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
        
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500,500);
        f.show();
    }
    
    //Si un boton es presionado
    public void actionPerformed(ActionEvent e){
        
        String s = e.getActionCommand();
        if(s.equals("Cortar")){
            t.cut();
        }
        else if(s.equals("Copiar")){
            t.copy();
        }
        else if(s.equals("Pegar")){
            t.paste();
        }
        else if(s.equals("Guardar")){
            JFileChooser j = new JFileChooser("f:"); //Crea un objeto de la clase JFileChooser
            
            /*Invoca la funcion showSaveDialog para mostrar el dialogo guardado*/
            int r = j.showSaveDialog(null); 
            if(r == JFileChooser.APPROVE_OPTION){
                
                /*Establecer la etiqueta a la ruta del directorio seleccionado*/
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try{
                    FileWriter wr = new FileWriter(fi, false); // crea un FileWriter
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(t.getText());
                    w.flush();
                    w.close();
                }catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                } 
            }
            else{ // si el usuario cancela la operacion
                   JOptionPane.showMessageDialog(f, "Se cancelo la operacion");
            }
        }
        else if(s.equals("Imprimir")){
            try{
                
                t.print(); //imprime el archivo
                
            }catch(Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if(s.equals("Abrir")){
            
            JFileChooser j = new JFileChooser("f:"); //Crea un objeto FileChooser
            
            /*Invoca la funcion showSaveDialog para mostrar el dialogo guardado*/
            int r = j.showOpenDialog(null);
            
            //Si el usuario selecciona un archivo
            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try{
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    sl = br.readLine();
                    
                    //Toma la entrada del archivo
                    while((s1 = br.readLine()) != null){
                        sl = sl + "\n" + s1;
                    }
                    t.setText(sl); //Pone el texto
                }catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }   
            }
            else{
                JOptionPane.showMessageDialog(f, "El usuario cancelo la operacion");
            }
        }
        else if(s.equals("Nuevo")){
            t.setText("");
        }
        else if(s.equals("Cerrar")){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        Principal e = new Principal();
    }
}
