package entities;

public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(Long id, int cantidad, Double subtotal, Producto producto) {
        super(id);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public Double getSubtotal() { return subtotal; }
    @Override
    public String toString() { return cantidad + "x " + producto.getNombre() + " = $" + subtotal; }
}