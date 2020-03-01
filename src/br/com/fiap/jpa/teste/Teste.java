package br.com.fiap.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.jpa.entity.GeneroMotorista;
import br.com.fiap.jpa.entity.Motorista;
import br.com.fiap.jpa.entity.Veiculo;
import br.com.fiap.jpa.model.dao.MotoristaDAO;
import br.com.fiap.jpa.model.dao.VeiculoDAO;

public class Teste {

	public static void main(String[] args) {

		Veiculo v = new Veiculo("Celta","Preto",2019);
		VeiculoDAO vdao = new VeiculoDAO();
		EntityManagerFactory f = Persistence.createEntityManagerFactory("oracle");
		EntityManager em = f.createEntityManager();
		
//		vdao.persist(v);
		
//		vdao.findAll();
//		System.out.println(vdao.getById(2));
		

//		vdao.consulta(em);
		
		Motorista m = new Motorista(10920, "Rogério Fernandez", null, null, GeneroMotorista.MASCULINO);
		MotoristaDAO mdao = new MotoristaDAO();
//		mdao.persist(m);
//		mdao.findAll();
		mdao.consulta(em);
		
		f.close();
		em.close();
	}

}
