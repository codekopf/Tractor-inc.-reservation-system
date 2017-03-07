/**
 * 
 */
package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import cz.ucl.hatchery.carevidence.entity.Lending;
import cz.ucl.hatchery.carevidence.model.LendingFilter;

/**
 * @author User
 *
 */
public interface LendingDAO extends GenericDAO<Lending, Long> {

	public List<Lending> findByFilter(LendingFilter filter);

}
