package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import cz.ucl.hatchery.carevidence.entity.Client;
import cz.ucl.hatchery.carevidence.model.ClientFilter;

public interface ClientDAO extends GenericDAO<Client, Long> {

	public List<Client> findByFilter(ClientFilter filter);

}
