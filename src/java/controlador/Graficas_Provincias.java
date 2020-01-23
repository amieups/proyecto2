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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.PieChartModel;
/**
 *
 * @author AnthOnY
 */
@ManagedBean
@ViewScoped
@RequestScoped
public class Graficas_Provincias {
     

     private static List<Educacion> lista=new ArrayList<>();
      private BarChartModel barras;
      Educacion educ= new Educacion();
   public String listar_tabla() throws SQLException{
       try{
          String sql ="Select  dp.provincia,sum(cast("+educ.getValor()+" as integer)) as cantidad from hechos_educacion as he inner join dim_provincia as dp on he.sk_provincia=dp.sk_provincia"
                  + "  where  dp.provincia= '"+educ.getProvincia1()+"' or dp.provincia='"+educ.getProvincia2()+"'"
         + " or dp.provincia='"+educ.getProvincia3()+"'or dp.provincia='"+educ.getProvincia4()+"' group by dp.provincia order by dp.provincia desc";;
        ResultSet rs =null;
        conexion con = new conexion();
        lista.clear();
        rs=con.Consulta(sql);
        while (rs.next()){
            Educacion ed = new Educacion();
            ed.setProvincia(rs.getString(1));
            ed.setValor1(rs.getInt("cantidad"));
            System.out.println(ed.getProvincia());
            lista.add(ed);
            
        }
        con.cerrarConexion();
        rs.close();
          barras=new BarChartModel();
           BarChartSeries prov=new BarChartSeries();
            
        for(Educacion edu : lista){
            prov.set(edu.getProvincia(), edu.getValor1());
            prov.setLabel("");
          
        }
        barras.addSeries(prov);
        barras.setTitle("Reportes por Provincias");
        barras.setLegendPosition("e");

        
       }catch(Exception e){
           e.printStackTrace();
       }
      
              return "archivoProvincia.xhtml"; 
}

    public BarChartModel getBarras() {
        return barras;
    }

    public void setBarras(BarChartModel barras) {
        this.barras = barras;
    }

  

    public Educacion getEduc() {
        return educ;
    }

    public void setEduc(Educacion educ) {
        this.educ = educ;
    }
   
   
}
