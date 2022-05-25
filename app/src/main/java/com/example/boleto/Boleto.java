package com.example.boleto;

public class Boleto {
    //variables
    private int numeroBoleto;
    private String nombreCliente;
    private String destino;
    private int tipoBoleto;
    private String fecha;
    private double descuento;
    private double precio;

    //Constructores
    public Boleto(){
        this.numeroBoleto = 0;
        this.nombreCliente = "";
        this.destino = "";
        this.tipoBoleto = 0;
        this.fecha = "";
        this.precio = 0.0;
    }

    public Boleto(int numeroBoleto, String nombreCliente, String destino, int tipoBoleto, String fecha, double precio){
        this.numeroBoleto = numeroBoleto;
        this.nombreCliente = nombreCliente;
        this.destino = destino;
        this.tipoBoleto = tipoBoleto;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Boleto(Boleto boleto){
        this.numeroBoleto = boleto.numeroBoleto;
        this.nombreCliente = boleto.nombreCliente;
        this.destino = boleto.destino;
        this.tipoBoleto = boleto.tipoBoleto;
        this.fecha = boleto.fecha;
        this.precio = boleto.precio;
    }



    //Encapsulamiento
    public int getNumeroBoleto() {
        return numeroBoleto;
    }

    /**
     * @param numeroBoleto the numeroBoleto to set
     */
    public void setNumeroBoleto(int numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the tipoBoleto
     */
    public int getTipoBoleto() {
        return tipoBoleto;
    }

    /**
     * @param tipoBoleto the tipoViaje to set
     */
    public void setTipoBoleto(int tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Funciones
    public double obtenerSubtotal(){
        double subtotal = 0.0;
        if(this.tipoBoleto==1) subtotal = this.precio;
        if(this.tipoBoleto==2) subtotal = this.precio*1.8;
        return subtotal;
    }

    public double obtenerDescuento(int edad){
        if (edad > 60){
            descuento = obtenerSubtotal()/2; //Entre 2 porque es el 50% de descuento
        }
        return descuento;
    }

    public double obtenerImpuesto(){
        return obtenerSubtotal() * .16;
    }

    public double obtenerTotal(){
        double total;
        total = obtenerSubtotal() + obtenerImpuesto() - descuento;
        return total;
    }


}

