//Dávila Oliveira Gomes
package aplicacao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pessoa pessoa;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Scanner entrada = new Scanner(System.in);
		int opcao;

		while (true) {
			System.out.println("Digite 1: Para listar pessoas cadastradas");
			System.out.println("Digite 2: Para buscar pessoas pelo ID");
			System.out.println("Digite 3: Para cadastrar uma pessoa");
			System.out.println("Digite 4: Para atualizar uma pessoa");
			System.out.println("Digite 5: Para remover uma pessoa");
			System.out.println("Digite 0: Para sair");

			while (true) {
				try {
					opcao = Integer.parseInt(entrada.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Tente novamente!");
				}
			}

			switch (opcao) {

			case 1:
				String jpql = "SELECT p FROM Pessoa p";
				List<Pessoa> pessoas = entityManager.createQuery(jpql, Pessoa.class).getResultList();

				for (int i = 0; i < pessoas.size(); i++) {
					System.out.println("Pessoa " + (i + 1) + ":");
					System.out.println(pessoas.get(i) + "\n");
				}
				System.out.println();
				break;

			case 2:
				int id;
				System.out.println("Digite o ID: ");
				while (true) {
					try {
						id = Integer.parseInt(entrada.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Tente novamente!");
					}
				}
				Pessoa pessoaFound = entityManager.find(Pessoa.class, id);
				if (pessoaFound == null) {
					System.out.println("Não existe uma pessoa com o ID digitado.");
				} else {
					System.out.println(pessoaFound);
				}

				break;

			case 3:
				System.out.println("Digite o nome: ");
				String nome = entrada.nextLine();
				System.out.println("Digite o email: ");
				String email = entrada.nextLine();
				pessoa = new Pessoa(null, nome, email);
				entityManager.getTransaction().begin();
				entityManager.persist(pessoa);
				entityManager.getTransaction().commit();
				break;

			case 4:
				System.out.println("Digite o ID: ");
				int id1;
				while (true) {
					try {
						id1 = Integer.parseInt(entrada.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Tente novamente!");
					}
				}

				Pessoa pessoaFound1 = entityManager.find(Pessoa.class, id1);

				if (pessoaFound1 != null) {
//					System.out.println(pessoaFound1);
					System.out.println("Digite o novo nome: ");
					String nome1 = entrada.nextLine();
					System.out.println("Digite o novo email: ");
					String email1 = entrada.nextLine();
					pessoaFound1.setEmail(email1);
					pessoaFound1.setNome(nome1);
					entityManager.getTransaction().begin();
					entityManager.persist(pessoaFound1);
					entityManager.getTransaction().commit();
				} else {
					System.out.println("Não é possível a atualização pois o id digitado não existe no Banco de Dados.");
				}

				break;
			case 5:
				System.out.println("Digite o ID que deseja remover: ");
				int id2;
				while (true) {
					try {
						id2 = Integer.parseInt(entrada.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Tente novamente!");
					}
				}
				Pessoa pessoaFound2 = entityManager.find(Pessoa.class, id2);
				if (pessoaFound2 == null) {
					System.out.println("Não é possível apagar o ID desejado, pois não existe.");
				} else {
					System.out.println(pessoaFound2);
				}
				entityManager.getTransaction().begin();
				entityManager.remove(pessoaFound2);
				entityManager.getTransaction().commit();
				break;

			case 0:
				System.out.println("Fechando programa...Até breve!");
				entityManager.close();
				entityManagerFactory.close();
				System.exit(0);
				break;

			default:
				System.out.println("Certifique-se que o número que você digitou condiz com as opcões disponíveis!");
				break;
			}

//		entityManger.getTransaction().commit();
//		System.out.println(pessoa);

		}
	}

}
