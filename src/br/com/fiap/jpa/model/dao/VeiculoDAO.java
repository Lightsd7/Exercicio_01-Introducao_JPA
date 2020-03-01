package br.com.fiap.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.jpa.entity.Veiculo;

public class VeiculoDAO implements DAO {

	private static VeiculoDAO instance;
	protected EntityManager entityManager;
	Veiculo v = new Veiculo();

	public static VeiculoDAO getInstance() {
		if (instance == null) {
			instance = new VeiculoDAO();
		}

		return instance;
	}

	public VeiculoDAO() {
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
			v = (Veiculo) o;
			entityManager.getTransaction().begin();
			entityManager.persist(v);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void merge(Object o) {
		try {
			v = (Veiculo) o;
			entityManager.getTransaction().begin();
			entityManager.merge(v);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void remove(Object o) {
		try {
			v = (Veiculo) o;
			entityManager.getTransaction().begin();
			v = entityManager.find(Veiculo.class, v.getCodigo());
			entityManager.remove(v);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void removeById(int id) {
		try {
			Veiculo v = getById(id);
			remove(v);
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll() {
		return entityManager.createQuery("FROM " + Veiculo.class.getName()).getResultList();
	}

	@Override
	public Veiculo getById(int id) {
		return entityManager.find(Veiculo.class, id);
	}

	public static void consulta(EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("select * from t_veiculo", Veiculo.class);

		List<Veiculo> lista = query.getResultList();

		lista.stream().forEach(v -> System.out.println("Id Veículo: " + v.getCodigo() + " Ano do Veículo: " + v.getAno()));
	}

}
