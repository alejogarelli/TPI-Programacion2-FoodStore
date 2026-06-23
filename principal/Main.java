package principal;
import ui.AppMenu;

public class Main {
    public static void main(String[] args) {
        // Inicializar la aplicación aislada del Main 
        AppMenu menu = new AppMenu();
        menu.iniciar();
    }
}