package fr.pizzeria.dao.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.IPerformanceDao;
import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Performance;

@Repository
@Lazy
public class PerformanceDaoJPARepoImpl implements IPerformanceDao {

	@Autowired
	IPerformanceRepository perfRepo;

	@Override
	public List<Performance> findAllPerformances() throws DaoException {
		return perfRepo.findAll();
	}

	@Override
	public void savePerformance(Performance newPerfomance) throws DaoException {
		perfRepo.save(newPerfomance);
	}

	@Override
	@Transactional
	public void updatePerformance(int idPerformance, Performance updatePerformance) throws DaoException {
		Performance oldPerformance = perfRepo.findOne(idPerformance);
		oldPerformance.setService(updatePerformance.getService());
		oldPerformance.setDateMesure(updatePerformance.getDateMesure());
		oldPerformance.setExecution(updatePerformance.getExecution());

	}

	@Override
	public void deletePerformance(int idPerformance) throws DaoException {
		Performance oldPerformance = perfRepo.findOne(idPerformance);
		perfRepo.delete(oldPerformance);

	}
}
