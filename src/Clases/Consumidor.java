/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Raul
 */
public class Consumidor {
    
    String run;
    String p_nombre;
    String s_nombre;
    String apellido_p;
    String apellido_m;
    String email;
    String clave;
    int puntaje;
    char activo;
    char suscrito;
    int comuna_id;

    public Consumidor() {
    }

    public Consumidor(String run, String p_nombre, String s_nombre, String apellido_p, String apellido_m, String email, String clave, int puntaje, char activo, char suscrito, int comuna_id) {
        this.run = run;
        this.p_nombre = p_nombre;
        this.s_nombre = s_nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.email = email;
        this.clave = clave;
        this.puntaje = puntaje;
        this.activo = activo;
        this.suscrito = suscrito;
        this.comuna_id = comuna_id;
    }

    public String getRun() {
        return run;
    }
    public String getP_nombre() {
        return p_nombre;
    }
    public String getS_nombre() {
        return s_nombre;
    }
    public String getApellido_p() {
        return apellido_p;
    }
    public String getApellido_m() {
        return apellido_m;
    }
    public String getEmail() {
        return email;
    }
    public String getClave() {
        return clave;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public char getActivo() {
        return activo;
    }
    public char getSuscrito() {
        return suscrito;
    }
    public int getComuna_id() {
        return comuna_id;
    }
    public void setRun(String run) {
        this.run = run;
    }
    public void setP_nombre(String p_nombre) {
        this.p_nombre = p_nombre;
    }
    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }
    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }
    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public void setActivo(char activo) {
        this.activo = activo;
    }
    public void setSuscrito(char suscrito) {
        this.suscrito = suscrito;
    }
    public void setComuna_id(int comuna_id) {
        this.comuna_id = comuna_id;
    }
    
    
    
    
    
}
