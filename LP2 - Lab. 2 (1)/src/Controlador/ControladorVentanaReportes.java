/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Vendedor;
import Modelo.Vendedor_DAO;
import Modelo.Venta;
import Modelo.Venta_DAO;
import Vista.VentanaInicio;
import Vista.VentanaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 *
 * @author Nicolás
 */
public class ControladorVentanaReportes implements ActionListener {

    private VentanaInicio v1;
    private VentanaReportes v3;

    public ControladorVentanaReportes(VentanaInicio v1, VentanaReportes v3) {
        this.v1 = v1;
        this.v3 = v3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando == "obtener") {
            String fecha = null;
            String mes = (String) this.v3.cbMes.getSelectedItem();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
            if (mes == "Enero") {
                fecha = "01";
            } else if (mes == "Febrero") {
                fecha = "02";
            } else if (mes == "Marzo") {
                fecha = "03";
            } else if (mes == "Abril") {
                fecha = "04";
            } else if (mes == "Mayo") {
                fecha = "05";
            } else if (mes == "Junio") {
                fecha = "06";
            } else if (mes == "Julio") {
                fecha = "07";
            } else if (mes == "Agosto") {
                fecha = "08";
            } else if (mes == "Septiembre") {
                fecha = "09";
            } else if (mes == "Octubre") {
                fecha = "10";
            } else if (mes == "Noviembre") {
                fecha = "11";
            } else if (mes == "Diciembre") {
                fecha = "12";
            }  

            String mejorVendedor;
            int totalVentas;

            Vendedor_DAO a = new Vendedor_DAO();
            ArrayList<Vendedor> vendedores = a.getVendedores();
            Venta_DAO b = new Venta_DAO();
            ArrayList<Venta> ventas = b.getVentas();
            

            ArrayList<Venta> ventasMes = new ArrayList<>();

            //transformar fecha    
            for (int i = 0; i < ventas.size(); i++) {
                String[] parts = ventas.get(i).getFecha().split("-");
                Arrays.toString(parts);
                if (parts[1].equals(fecha)) {
                    ventasMes.add(ventas.get(i));
                }    
            }

            // Mejor vendedor + total de ventas.
            // Mayor venta + vendedor.
            int id_vendedorMayorVenta = 0;
            int mayorVenta = ventasMes.get(0).getMonto();
            for (int i = 1; i < ventasMes.size(); i++) {
                if (mayorVenta < ventasMes.get(i).getMonto()) {
                    mayorVenta = ventasMes.get(i).getMonto();
                    id_vendedorMayorVenta = ventasMes.get(i).getId_vendedor();
                }
            
            }

            String mayorV = String.valueOf(mayorVenta);
            String idmayorV = String.valueOf(id_vendedorMayorVenta);
            this.v3.tfMayorVenta.setText(mayorV);

            
            
            //Mejor vendedor 
            
            int[] tVentas = new int[100];
            for (int i = 0; i < ventasMes.size(); i++) {
                 tVentas[ventasMes.get(i).getId_vendedor()]=tVentas[ventasMes.get(i).getId_vendedor()]+ventasMes.get(i).getMonto();
                 
            }
            int cont=0;
            int pos=0;
            
            for (int i = 0; i < tVentas.length; i++) {
                if(tVentas[i]>cont){
                    cont=tVentas[i];
                    pos=i;
                }
            }
            String tV = String.valueOf(cont);
            this.v3.tfTotalVentas.setText(tV);
            
            
            
            
            String nombreVendedorMayorVenta = "";
            for (int i = 0; i < vendedores.size(); i++) {
                if (vendedores.get(i).getId_vendedor() == id_vendedorMayorVenta) {
                    nombreVendedorMayorVenta = vendedores.get(i).getNombre();
                }
            }
            String nombreVendedorMasVentas = "";
           for (int i = 0; i < vendedores.size(); i++) {
                if (vendedores.get(i).getId_vendedor() == pos) {
                    nombreVendedorMasVentas = vendedores.get(i).getNombre();
                }
           }
      

            this.v3.tfVendedorMayorVenta.setText(nombreVendedorMayorVenta);
            this.v3.tfMejorVendedor.setText(nombreVendedorMasVentas);
            
            
            
            // Promedio ventas.
            int sumaMonto = 0;
            double promedio = 0;
            for (int i = 0; i < ventasMes.size(); i++) {
                sumaMonto += ventasMes.get(i).getMonto();
            }
            promedio = (sumaMonto / ventasMes.size());
            String promedioVentas = String.valueOf(promedio);
            this.v3.tfPromedioVentas.setText(promedioVentas);
        }
        if (comando == "imprimir") {
        }
        if (comando == "cerrar") {
            v3.dispose();
        }
    }


}
