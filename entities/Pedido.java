package entities;
import enums.*;
import exceptions.DatoInvalidoException;
import interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private List<DetallePedido> detalles;

    public Pedido(Long id, FormaPago formaPago, Usuario usuario) {
        super(id);
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
    }

    public void addDetallePedido(Long idDetalle, int cantidad, Producto producto) {
        if (cantidad <= 0) throw new DatoInvalidoException("Cantidad debe ser mayor a 0.");
        if (producto.getStock() < cantidad) throw new DatoInvalidoException("Stock insuficiente.");
        
        Double subtotal = cantidad * producto.getPrecio();
        detalles.add(new DetallePedido(idDetalle, cantidad, subtotal, producto));
        producto.setStock(producto.getStock() - cantidad); // Resta stock
        calcularTotal();
    }

    @Override
    public void calcularTotal() {
        this.total = 0.0;
        for (DetallePedido dp : detalles) {
            if (!dp.isEliminado()) {
                this.total += dp.getSubtotal();
            }
        }
    }

    public void setEstado(Estado estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return "Pedido ID: " + getId() + " | Cliente: " + usuario.getMail() + " | Estado: " + estado + " | Total: $" + total;
    }
}