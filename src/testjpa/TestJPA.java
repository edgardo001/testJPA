/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjpa;

import Controlador.PersonaJpaController;
import Modelo.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Datasoft
 */
public class TestJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testJPAPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PersonaJpaController personaJpaController = new PersonaJpaController(entityManagerFactory);
        //================================================
        //================================================
        //Insert
        entityManager.getTransaction().begin();
        Persona per = new Persona();
        per.setNombre("daniel");
        per.setApellido("Silva");
        per.setDireccion("Buin");
        personaJpaController.create(per);
        entityManager.getTransaction().commit();
        System.out.println("Persisted " + per);
        entityManager.close();
        entityManagerFactory.close();
        //================================================
        //================================================
        //Select
        Persona findPersona = personaJpaController.findPersona(1);        
        System.out.println(findPersona.getNombre());
        //================================================
        //================================================
        //Delete
        try {
            personaJpaController.destroy(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //================================================
        //================================================
        //Update
        try {
            Persona findPersona2 = personaJpaController.findPersona(2);
            findPersona2.setApellido("Garrido");
            personaJpaController.edit(findPersona2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
