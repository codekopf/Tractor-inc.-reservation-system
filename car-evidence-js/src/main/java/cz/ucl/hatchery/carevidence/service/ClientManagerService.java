/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import cz.ucl.hatchery.carevidence.model.ClientDTO;
import cz.ucl.hatchery.carevidence.model.ClientFilter;
import cz.ucl.hatchery.carevidence.model.ClientNewForm;

/**
 * @author User
 *
 */
public interface ClientManagerService {

	public void createNewClient(ClientNewForm carNewForm);

	public List<ClientDTO> getAllClients();

	public List<ClientDTO> findClientByFilter(ClientFilter filter);

}
