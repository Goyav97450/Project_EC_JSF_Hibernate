package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

/**
 * @author Thibault
 * Classe impl�mentant l'interface IClientService
 * L'annotation @Service permet au conteneur SpringIoC d'identifier cette classe comme un bean
 * L'annotation @Transactional sert � dire que cette classe n'est pas un singleton
 */
@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService{

	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @Autowired permet de r�aliser l'injection automatique des
	 * d�pendances.
	 */
	@Autowired
	private IClientDao clDao;
	
	@Override
	public int saveClient(Client cl) {
		
		return clDao.saveClient(cl);
	}

	@Override
	public Client getClientByMail(String rech) {
		
		return clDao.getClientByMail(rech);
	}

	@Override
	public Client getClientById(Client cl) {
		
		return clDao.getClientById(cl);
	}

	@Override
	public List<Client> getAllClient() {
		return clDao.getAllClient();
	}

	@Override
	public List<String> getAllClIdService() {
		return clDao.getAllClId();
	}

}
