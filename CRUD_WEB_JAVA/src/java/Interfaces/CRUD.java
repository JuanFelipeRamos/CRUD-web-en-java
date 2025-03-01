package Interfaces;

import Modelo.Usuarios;
import java.util.List;

public interface CRUD {
    public List listar();
    public Usuarios list(int id);
    public boolean crear(Usuarios user);
    public boolean editar(Usuarios user);
    public boolean elimiar(int id);
}
