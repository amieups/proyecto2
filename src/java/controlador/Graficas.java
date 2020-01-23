/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import modelo.Educacion;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ACER
 */

@ManagedBean
@ViewScoped
@RequestScoped
public class Graficas {
    

     private static List<Educacion> lista=new ArrayList<>();
      private PieChartModel pastel;
      Educacion educ= new Educacion();
   public String listar_tabla() throws SQLException{
       try{
          String sql ="Select  dc.canton,sum(cast("+educ.getValor()+" as integer)) as cantidad from hechos_educacion as he inner join dim_canton as dc on he.sk_canton=dc.sk_canton"
                  + "  where  dc.canton= '"+educ.getCanton1()+"' or dc.canton='"+educ.getCanton2()+"'"
         + " or dc.canton='"+educ.getCanton3()+"'or dc.canton='"+educ.getCanton4()+"' group by dc.canton order by dc.canton desc";;
        ResultSet rs =null;
        conexion con = new conexion();
        lista.clear();
        rs=con.Consulta(sql);
        while (rs.next()){
            Educacion ed = new Educacion();
            ed.setCanton(rs.getString(1));
            ed.setValor1(rs.getInt("cantidad"));
            lista.add(ed);
            
        }
        con.cerrarConexion();
        rs.close();
          pastel=new PieChartModel();
        for(Educacion edu : lista){
            pastel.set(edu.getCanton(), edu.getValor1());
        }
        pastel.setTitle("Reportes por Canton");
        pastel.setLegendPosition("e");
        pastel.setFill(true);
        pastel.setShowDataLabels(true);
        pastel.setDiameter(150);
        
       }catch(Exception e){
           e.printStackTrace();
       }
      
              return "archivoCanton.xhtml"; 
}

    public PieChartModel getPastel() {
        return pastel;
    }

    public void setPastel(PieChartModel pastel) {
        this.pastel = pastel;
    }

    public Educacion getEduc() {
        return educ;
    }

    public void setEduc(Educacion educ) {
        this.educ = educ;
    }
   
   
    
}
