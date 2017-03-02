package cz.ucl.hatchery.carevidence;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration
@Transactional
public abstract class AbstractTransactionalSpringTesting extends AbstractSpringTesting {

}
