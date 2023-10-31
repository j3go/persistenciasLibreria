
package persistencia;

import entidades.Libro;
import java.util.List;

public class LibroDAO extends DAO<Libro>{
    
   @Override
   public void guardar(Libro libro){
       super.guardar(libro);
   }
   
   public Libro buscarPorIsbn(long isbn){
       conectar();
       Libro libro = em.find(Libro.class, isbn);
       desconectar();
       return libro;
   }
   
   public void eliminarPorIsbn(long isbn){
       Libro libro = buscarPorIsbn(isbn);
       conectar();
       libro = em.merge(libro);
       super.eliminar(libro);
       desconectar();
   }
   
   public List<Libro> librosPorEditorial(String nombreEdit){
       conectar();
       List<Libro> lista =  em.createQuery("select * from libro where editorial like :nombreEdit")
               .setParameter("nombreEdit","%" + nombreEdit + "%" ).getResultList();
       desconectar();
       return lista;
   }
    
}
