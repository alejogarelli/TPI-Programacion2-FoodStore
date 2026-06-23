package entities;

public class Producto extends Base {
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    public Producto(Long id, String nombre, Double precio, String descripcion, int stock, boolean disponible, Categoria categoria) {
        super(id);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = "img.png"; // Simplificado
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + nombre + " | $" + precio + " | Stock: " + stock + " | Cat: " + categoria.getNombre();
    }
}