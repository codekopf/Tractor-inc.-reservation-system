/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import cz.ucl.hatchery.carevidence.model.ClientDTO;
import cz.ucl.hatchery.carevidence.model.ClientFilter;
import cz.ucl.hatchery.carevidence.model.ClientNewForm;
import cz.ucl.hatchery.carevidence.model.ClientOldForm;

/**
 * @author User
 *
 */
public interface ClientManagerService {

	public void createNewClient(ClientNewForm clientNewForm);

	public List<ClientDTO> getAllClients();

	public List<ClientDTO> findClientByFilter(ClientFilter filter);

	public void saveOrUpdate(ClientOldForm oldClientForm);
}
