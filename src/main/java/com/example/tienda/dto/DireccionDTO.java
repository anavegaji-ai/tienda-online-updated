
package com.example.tienda.dto;

public class DireccionDTO {
    private String calle;
    private String ciudad;
    private String pais;
    private String zip;

    public String getCalle(){return calle;}
    public void setCalle(String calle){this.calle = calle;}
    public String getCiudad(){return ciudad;}
    public void setCiudad(String ciudad){this.ciudad = ciudad;}
    public String getPais(){return pais;}
    public void setPais(String pais){this.pais = pais;}
    public String getZip(){return zip;}
    public void setZip(String zip){this.zip = zip;}
}
