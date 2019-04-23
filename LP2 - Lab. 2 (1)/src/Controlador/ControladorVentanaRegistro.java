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
import Vista.VentanaRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class ControladorVentanaRegistro implements ActionListener{
    private VentanaInicio v1;
    private VentanaRegistro v2;

    public ControladorVentanaRegistro(VentanaInicio v1, VentanaRegistro v2) {
        this.v1 = v1;
        this.v2 = v2;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        if (comando == "registrar") {
            String nombre = (String) this.v2.cbVendedor.getSelectedItem();
            String sucursal = (String) this.v2.cbSucursal.getSelectedItem();
            
            /*
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateInString = this.v2.tfFecha.getText();
            try {
                fecha = sdf.parse(dateInString);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorVentanaRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
            String fecha = this.v2.tfFecha.getText();
            int monto = Integer.parseInt(this.v2.tfMonto.getText());
            
            Vendedor_DAO v1 = new Vendedor_DAO();
            ArrayList<Vendedor> vendedores = v1.getVendedores();
            
            int id_vendedor = 0;
            for (int i = 0; i < vendedores.size(); i++) {
                if (nombre.equals(vendedores.get(i).getNombre())) {
                    id_vendedor = vendedores.get(i).getId_vendedor();
                }
            }
            
            Venta venta = new Venta(sucursal, monto, fecha, id_vendedor);
            
            Venta_DAO v2 = new Venta_DAO();
            v2.addVenta(venta);
        }
    }
}
