/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author vina
 */
public class Oferta {
    
    private int id;              
    private String titulo;         
    private String descripcion;     
    private Date fecha_inicio;
    private Date fecha_termino;
    private int precio_normal;
    private int precio_oferta;
    private int compra_min;
    private int compra_max;
    private boolean activa;

    public Oferta() {
    }

    public Oferta(int id, String titulo, String descripcion, Date fecha_inicio, Date fecha_termino, int precio_normal, int precio_oferta, int compra_min, int compra_max, boolean activa) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.precio_normal = precio_normal;
        this.precio_oferta = precio_oferta;
        this.compra_min = compra_min;
        this.compra_max = compra_max;
        this.activa = activa;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_termino() {
        return fecha_termino;
    }

    public int getPrecio_normal() {
        return precio_normal;
    }

    public int getPrecio_oferta() {
        return precio_oferta;
    }

    public int getCompra_min() {
        return compra_min;
    }

    public int getCompra_max() {
        return compra_max;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_termino(Date fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public void setPrecio_normal(int precio_normal) {
        this.precio_normal = precio_normal;
    }

    public void setPrecio_oferta(int precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public void setCompra_min(int compra_min) {
        this.compra_min = compra_min;
    }

    public void setCompra_max(int compra_max) {
        this.compra_max = compra_max;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Oferta{" + "id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha_inicio=" + fecha_inicio + ", fecha_termino=" + fecha_termino + ", precio_normal=" + precio_normal + ", precio_oferta=" + precio_oferta + ", compra_min=" + compra_min + ", compra_max=" + compra_max + ", activa=" + activa + '}';
    }
    
    
    
}
