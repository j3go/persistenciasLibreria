package persistencia;


import com.sun.source.tree.BreakTree;
import entidades.Autor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;


public class AutorDAO extends DAO<Autor>{

    
    @Override
    public void guardar(Autor autor){
        super.guardar(autor);
    }
    
    public Autor buscarPorId(int id){
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
       public void eliminarPorId(int id){
        Autor autor = null;
        try {
            autor = buscarPorId(id);
        } catch (Exception ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conectar();
        autor = em.merge(autor);
        super.eliminar(autor);
        desconectar();
    }
       
    public void editarNombre(int id,String nombre){
        Autor autor = null;
        try {
            autor = buscarPorId(id);
        } catch (Exception ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        autor.setNombre(nombre);
        super.editar(autor);
    }
    
    public Autor buscarPorNombre(String nombre) {
        conectar();
     Autor autor = null;
        try {
            autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                               .setParameter("nombre", "%" + nombre + "%")
                                .getSingleResult();   
         } catch (NoResultException e) {
            System.out.println("El Autor no esta registrado");
    } finally {
        desconectar();
    }
    return autor;
}
}
