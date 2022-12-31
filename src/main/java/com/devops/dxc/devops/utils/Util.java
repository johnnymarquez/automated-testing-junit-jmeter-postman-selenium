package com.devops.dxc.devops.utils;

import com.devops.dxc.devops.model.Indicador;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");
    private final static String IND_UF = "uf";

    /**
     * Método para cacular el 10% del ahorro en la AFP. Las reglas de negocio se pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @return
     */
    public static int getDxc(int ahorro, double uf){

        if(((ahorro*0.1)/uf) > 150 ){
            return (int) (150*uf) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método para calcular el impuesto asociado al retiro del 10%. Las información de rangos y factores se puede
     * conocer en https://www.sii.cl/valores_y_fechas/renta/2020/personas_naturales.html
     *
     * @param sueldo
     * @return
     */
    public static int getImpuesto(int sueldo, int dxc){
        double impuesto = 0;
        int sueldoAnual = sueldo * 12;

        if(sueldoAnual>=8038926.01 && sueldoAnual<=17864280.00) {
            impuesto = dxc * 0.04;
        }else if(sueldoAnual>=17864280.01 && sueldoAnual<=29773800.00){
            impuesto = dxc * 0.08;
        }else if(sueldoAnual>=29773800.01 && sueldoAnual<=41683320.00) {
            impuesto = dxc * 0.135;
        }else if(sueldoAnual>=41683320.01 && sueldoAnual<=53592840.00) {
            impuesto = dxc * 0.23;
        }else if(sueldoAnual>=53592840.01 && sueldoAnual<=71457120.00) {
            impuesto = dxc * 0.304;
        }else if(sueldoAnual>=71457120.01) {
            impuesto = dxc * 0.35;
        }

        return (int)impuesto;
    }

    /**
     * Método que consulta el valor del día asociado al indicador
     * @param ind
     * @return
     */
    public static Indicador getIndicadorDiario(String ind){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = simpleDateFormat.format(Calendar.getInstance().getTime());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> call= restTemplate.getForEntity("https://mindicador.cl/api/" + ind + "/" + fecha, String.class);

        Gson gson = new Gson();
        Indicador indicador = gson.fromJson(call.getBody().toLowerCase(), Indicador.class);

        LOGGER.log(Level.INFO, "< Trabajo DevOps - DXC > <Consultado " + ind + " del día: " + indicador.getSerie().get(0).getValor() + " >");

        return indicador;
    }
    
}
