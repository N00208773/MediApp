package com.example.mediapp.ui.Productos;

public class Productos {
    private int id;
    private String nombre;
    private String descripcion;
    private String proveedor;
    private double precioVenta;
    private double precioCompra;
    private String stock;

    public Productos(int id, String nombre, String descripcion, String proveedor,
                     double precioVenta, double precioCompra, String stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getProveedor() { return proveedor; }
    public double getPrecioVenta() { return precioVenta; }
    public double getPrecioCompra() { return precioCompra; }
    public String getStock() { return stock; }
}