package br.com.fiap.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.jpa.entity.Motorista;
import br.com.fiap.jpa.entity.Veiculo;

public class MotoristaDAO implements DAO {

	private static MotoristaDAO instance;
	protected EntityManager entityManager;
	Motorista m = new Motorista();

	public static MotoristaDAO getInstance() {
		if (instance == null) {
			instance = new MotoristaDAO();
		}

		return instance;
	}

	public MotoristaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	@Override
	public void persist(Object o) {
		try {
			m = (Motorista) o;			
			entityManager.getTransaction().begin();
			entityManager.persist(m);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void merge(Object o) {
		try {
			m = (Motorista) o;
			entityManager.getTransaction().begin();
			entityManager.merge(m);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void remove(Object o) {
		try {
			m = (Motorista) o;
			entityManager.getTransaction().begin();
			m = entityManager.find(Motorista.class, m.getCarteira());
			entityManager.remove(m);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void removeById(int id) {
		try {
			Motorista m = getById(id);
			remove(m);
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll() {
		return entityManager.createQuery("FROM " + Motorista.class.getName()).getResultList();
	}

	@Override
	public Motorista getById(int id) {
		return entityManager.find(Motorista.class, id);
	}
	
	public static void consulta(EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("select * from t_motorista", Motorista.class);

		List<Motorista> lista = query.getResultList();

		lista.stream().forEach(m -> System.out.println("Nr Carteira: " + m.getCarteira() + ", Nm Motorista: " + m.getNome()));
	}

}
