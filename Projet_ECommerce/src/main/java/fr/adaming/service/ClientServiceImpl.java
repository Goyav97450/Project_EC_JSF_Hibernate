package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Client;

/**
 * @author Thibault
 * Classe implémentant l'interface IClientService
 * L'annotation @Service permet au conteneur SpringIoC d'identifier cette classe comme un bean
 * L'annotation @Transactional sert à dire que cette classe n'est pas un singleton
 */
@Service
@Transactional
public class ClientServiceImpl implements IClientService{

	/**
	 * Transformation de l'association entre service et dao
	 * L'annotation @Autowired permet de réaliser l'injection automatique des
	 * dépendances.
	 */
	@Autowired
	private IClientService clService;
	
	@Override
	public int saveClient(Client cl) {
		
		return clService.saveClient(cl);
	}

	@Override
	public Client getClientByMail(Client cl) {
		
		return clService.getClientByMail(cl);
	}

	@Override
	public Client getClientById(Client cl) {
		
		return clService.getClientById(cl);
	}

}
