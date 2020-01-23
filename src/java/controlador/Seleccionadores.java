/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AnthOnY
 */
@ManagedBean (name="select")
@RequestScoped

public class Seleccionadores {

    private String canton;
    private String categoria;
    
    private List<String> categoria_lista=new ArrayList<>();

    public List<String> get_categoria_canton(){
        try {
            categoria_lista.clear();
            Connection con=null;
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://172.17.42.227:5432/DIMENSIONAL_AMIE","postgres","postgres2020");
            
            PreparedStatement ps=null;
            ps=con.prepareStatement("select canton from dim_canton");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                categoria_lista.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
            
        }
        return categoria_lista;
    }
    
    
    public Seleccionadores(){
        
    }
    
    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getCategoria_lista() {
        return categoria_lista;
    }

    public void setCategoria_lista(List<String> categoria_lista) {
        this.categoria_lista = categoria_lista;
    }
    
  
}

