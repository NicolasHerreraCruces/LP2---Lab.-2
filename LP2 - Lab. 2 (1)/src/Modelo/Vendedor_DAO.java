package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class Vendedor_DAO {
    
    Conexion con;
    public Vendedor_DAO() {
        con = new Conexion();
    }
    
    public Vendedor getVendedorDAO() {
        
        Connection accesoBD = con.getConexion();
        Vendedor vendedor = null;
        try{
            String sql="SELECT * FROM vendedor";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            
            while ( resultados.next() ) {
                int id = resultados.getInt("id_vendedor");
                String nombre = resultados.getString("nombre");
                String apellido = resultados.getString("apellido");
                String rut = resultados.getString("rut");
                
                
            }
            accesoBD.close();
            return vendedor;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener vendedor");
            e.printStackTrace();
            return null;
        }
        
       
    }
    
    public ArrayList<Vendedor> getVendedores() {

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        Connection accesoBD = con.getConexion();
        
         try{

            String sql="SELECT * FROM vendedor";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            
            
            while ( resultados.next() ) {
                int id = resultados.getInt("id_vendedor");
                String nombre = resultados.getString("nombre");
                String apellido = resultados.getString("apellido");
                String rut = resultados.getString("rut");
                
                vendedores.add(new Vendedor(id, nombre, apellido, rut));
            }
                accesoBD.close();
                
                return vendedores;

        }catch (Exception e){

            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
            }
        }  
}
