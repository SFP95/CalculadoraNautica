package Fichero;

import java.io.Serializable;

public class Paquete implements Serializable {
    private String textoEnviar;
    String resumen;

    public Paquete(){ }

    public Paquete(String textoEnviar, String resumen){
        this.textoEnviar = textoEnviar;
        this.resumen = resumen;
    }

    public String getTextoEnviar () {
        return textoEnviar;
    }

    public void setTextoEnviar (String textoEnviar) {
        this.textoEnviar = textoEnviar;
    }

    public String getResumen () {
        return resumen;
    }

    public void setResumen (String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString () {
        return "Paquete{" +
                "textoEnviar='" + textoEnviar + '\'' +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}
