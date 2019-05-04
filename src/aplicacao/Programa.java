package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pessoa pessoa = new Pessoa(null, "Dávila Oliveira", "oliveiradavila49@gmail.com");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManger = entityManagerFactory.createEntityManager();

		entityManger.getTransaction().begin();
		entityManger.persist(pessoa);
		entityManger.getTransaction().commit();
		entityManger.close();
		entityManagerFactory.close();

	}

}

