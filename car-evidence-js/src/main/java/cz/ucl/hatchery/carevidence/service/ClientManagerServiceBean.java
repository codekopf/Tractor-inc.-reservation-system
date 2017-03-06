/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ucl.hatchery.carevidence.dao.ClientDAO;
import cz.ucl.hatchery.carevidence.entity.Client;
import cz.ucl.hatchery.carevidence.model.ClientDTO;
import cz.ucl.hatchery.carevidence.model.ClientFilter;
import cz.ucl.hatchery.carevidence.model.ClientNewForm;
import cz.ucl.hatchery.carevidence.model.ClientOldForm;
import cz.ucl.hatchery.carevidence.util.CoreDateUtil;
import cz.ucl.hatchery.carevidence.util.DTOConverter;

/**
 * @author User
 *
 */
@Service
@Transactional
public class ClientManagerServiceBean implements ClientManagerService {

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public void createNewClient(final ClientNewForm clientNewForm) {

		final Client client = createGeneralClient();

		// set parametrs from dto
		setFieldFromDto(clientNewForm, client);

		save(client);
	}

	@Override
	public void saveOrUpdate(final ClientOldForm oldClientForm) {

		final Long oldClientID = oldClientForm.getId();
		final Client client = clientDAO.findById(oldClientForm.getId());

		client.setClientName(oldClientForm.getClientName());
		client.setClientSurname(oldClientForm.getClientSurname());
		client.setClientICO(oldClientForm.getClientICO());
		client.setClientEmail(oldClientForm.getClientEmail());
		client.setClientPhone(oldClientForm.getClientPhone());

		save(client);

		// final Client client = createGeneralClient();
		//
		// // set parametrs from dto
		// setFieldFromDto(clientNewForm, client);
		//
		// save(client);
	}

	/**
	 * @param clientNewForm
	 * @param client
	 */
	private void setFieldFromDto(final ClientNewForm clientNewForm, final Client client) {
		// TODO - NOTE : NONONO
		// client.setId(clientNewForm.getClientId());
		// uvidime
		client.setClientName(clientNewForm.getClientName());
		client.setClientSurname(clientNewForm.getClientSurname());
		client.setClientICO(clientNewForm.getClientICO());
		client.setClientEmail(clientNewForm.getClientEmail());
		// TODO - NOTE : USED IN DEFAULT LOAD
		// client.setClientDateOfRegistraion(carNewForm.getClientDateOfRegistraion());
		client.setClientPhone(clientNewForm.getClientPhone());
	}

	/**
	 * @param client
	 */
	private void save(final Client client) {
		clientDAO.save(client);
	}

	/**
	 * 
	 */
	private Client createGeneralClient() {
		final Client client = new Client();

		client.setClientDateOfRegistraion(CoreDateUtil.getNow());

		return client;
	}

	/** {@inheritDoc} */
	@Override
	public List<ClientDTO> getAllClients() {

		return DTOConverter.convertClient(clientDAO.findAll());
	}

	/** {@inheritDoc} */
	@Override
	public List<ClientDTO> findClientByFilter(final ClientFilter filter) {

		return DTOConverter.convertClient(clientDAO.findByFilter(filter));
	}

	/**
	 * @param car
	 */
	// @Override
	// private void update(final ClientFilter filter) {
	//
	// final List<ClientDTO> ShouldBeOne = findClientByFilter(filter);
	//
	// if (ShouldBeOne.size() > 1) {
	// System.out.println("ERROR");
	// } else {
	// System.out.println("SUCCESSS");
	// }
	//
	// // set parametrs from dto
	// setFieldFromDto(clientNewForm, client);
	//
	// save(client);
	//
	// }

}