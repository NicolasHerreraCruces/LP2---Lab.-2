/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VentanaInicio;
import Vista.VentanaRegistro;
import Vista.VentanaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nicolás
 */
public class ControladorVentanaInicio implements ActionListener {
    private VentanaInicio v1;
    private VentanaRegistro v2;
    private VentanaReportes v3;

    public ControladorVentanaInicio(VentanaInicio v1) {
        this.v1 = v1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        if (comando == "registrar") {
            VentanaRegistro v2 = new VentanaRegistro(this.v1, this.v3);
            v2.setVisible(true);
        }
        
        else if (comando == "verReporte") {  
            VentanaReportes v3 = new VentanaReportes(v1);
            v3.setVisible(true);
        }
    }   
}
