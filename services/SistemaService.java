package services;
import entities.*;
import enums.*;
import exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaService {
    private List<Categoria> categorias = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    
    private Long idCat = 1L, idProd = 1L, idUsr = 1L, idPed = 1L, idDet = 1L;

    // --- CATEGORIAS ---
    public void crearCategoria(String nombre, String descripcion) {
        if (nombre.isBlank()) throw new DatoInvalidoException("Nombre vacío.");
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado()) 
                throw new DatoInvalidoException("La categoría ya existe.");
        }
        categorias.add(new Categoria(idCat++, nombre, descripcion));
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> activas = new ArrayList<>();
        for (Categoria c : categorias) if (!c.isEliminado()) activas.add(c);
        return activas;
    }

    public Categoria buscarCategoria(Long id) {
        for (Categoria c : categorias) if (c.getId().equals(id) && !c.isEliminado()) return c;
        throw new EntidadNoEncontradaException("Categoría no encontrada.");
    }

    public void eliminarCategoria(Long id) { buscarCategoria(id).setEliminado(true); }

    // --- PRODUCTOS ---
    public void crearProducto(String nombre, Double precio, String desc, int stock, Long idCategoria) {
        if (precio < 0 || stock < 0) throw new DatoInvalidoException("Precio/Stock inválido.");
        Categoria cat = buscarCategoria(idCategoria);
        productos.add(new Producto(idProd++, nombre, precio, desc, stock, true, cat));
    }

    public List<Producto> listarProductos() {
        List<Producto> activos = new ArrayList<>();
        for (Producto p : productos) if (!p.isEliminado()) activos.add(p);
        return activos;
    }

    public Producto buscarProducto(Long id) {
        for (Producto p : productos) if (p.getId().equals(id) && !p.isEliminado()) return p;
        throw new EntidadNoEncontradaException("Producto no encontrado.");
    }

    public void eliminarProducto(Long id) { buscarProducto(id).setEliminado(true); }

    // --- USUARIOS ---
    public void crearUsuario(String nom, String ape, String mail, String cel, Rol rol, String calle, String num) {
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(mail) && !u.isEliminado())
                throw new DatoInvalidoException("El mail ya está en uso.");
        }
        usuarios.add(new Usuario(idUsr++, nom, ape, mail, cel, rol, new Direccion(calle, num)));
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios) if (!u.isEliminado()) activos.add(u);
        return activos;
    }

    public Usuario buscarUsuario(Long id) {
        for (Usuario u : usuarios) if (u.getId().equals(id) && !u.isEliminado()) return u;
        throw new EntidadNoEncontradaException("Usuario no encontrado.");
    }

    public void eliminarUsuario(Long id) { buscarUsuario(id).setEliminado(true); }

    // --- PEDIDOS ---
    public Pedido iniciarPedido(Long idUsuario) {
        Pedido p = new Pedido(idPed++, FormaPago.EFECTIVO, buscarUsuario(idUsuario));
        pedidos.add(p);
        return p;
    }

    public void agregarDetalleAPedido(Pedido pedido, Long idProducto, int cantidad) {
        pedido.addDetallePedido(idDet++, cantidad, buscarProducto(idProducto));
    }

    public List<Pedido> listarPedidos() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) if (!p.isEliminado()) activos.add(p);
        return activos;
    }
}