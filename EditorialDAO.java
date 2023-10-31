/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Editorial;
import java.awt.BorderLayout;
import javax.persistence.NoResultException;

public class EditorialDAO extends DAO<Editorial>{
    
    @Override
    public void guardar(Editorial editorial){
        super.guardar(editorial);
    }
    
    public Editorial buscarPorId(int id){
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }
    
    public void eliminarPorId(int id){
        Editorial editorial = buscarPorId(id);
        conectar();
        editorial = em.merge(editorial);
        super.eliminar(editorial);
        desconectar();
    }
    
    public Editorial buscarPorNombre(String nombre){
        conectar();
        Editorial editorial = null;
        try {
            editorial = (Editorial) em.createQuery("select * from editorial where nombre like : nombre")
                    .setParameter("nombre", "%" + nombre + "%")
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Editorial no registrada: ");
        }finally{
            desconectar();
        }
        return editorial;
    }
    
}
