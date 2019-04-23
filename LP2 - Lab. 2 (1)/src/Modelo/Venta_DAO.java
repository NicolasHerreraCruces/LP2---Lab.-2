package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Venta_DAO {

    Conexion con;

    public Venta_DAO() {
        con = new Conexion();
    }

    public Venta getVentaDAO() {

        Connection accesoBD = con.getConexion();
        Venta venta = null;
        try {
            String sql = "SELECT * FROM venta";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_venta = resultados.getInt("id_venta");
                String sucursal = resultados.getString("sucursal");
                int monto = resultados.getInt("monto");
                Date fecha = resultados.getDate("fecha");
                int id_vendedor = resultados.getInt("id_vendedor");

            }
            accesoBD.close();
            return venta;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener venta");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Venta> getVentas() {

        ArrayList<Venta> ventas = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {

            String sql = "SELECT * FROM venta";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_venta = resultados.getInt("id_venta");
                String sucursal = resultados.getString("sucursal");
                int monto = resultados.getInt("monto");
                String fecha = resultados.getString("fecha");
                int id_vendedor = resultados.getInt("id_vendedor");

                ventas.add(new Venta(id_venta, sucursal, monto, fecha, id_vendedor));
            }
            accesoBD.close();

            return ventas;

        } catch (Exception e) {

            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

    public boolean addVenta(Venta v) {
        Connection accesoBD = this.con.getConexion();

        String sql = "INSERT INTO venta (sucursal,monto,fecha,id_vendedor) VALUES (?,?,?,?)";

        try {
            PreparedStatement rs = accesoBD.prepareStatement(sql);

            rs.setString(1, v.getSucursal());
            rs.setInt(2, v.getMonto());
            rs.setString(3, v.getFecha());
            rs.setInt(4, v.getId_vendedor());

            rs.executeUpdate();
            System.out.println("Nueva venta registrada");
            accesoBD.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
