package cz.ucl.hatchery.carevidence;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:car-evidence-context.xml")
public abstract class AbstractSpringTesting {

	public static final Logger LOG = LoggerFactory.getLogger(AbstractSpringTesting.class);

	public <T> T unwrapProxy(final Class<T> clazz, final T proxy) {
		if (AopUtils.isAopProxy(proxy) && proxy instanceof Advised) {
			try {
				final Object target = ((Advised) proxy).getTargetSource().getTarget();
				return clazz.cast(target);
			} catch (final Exception e) {
				LOG.error("", e);
			}
		}
		return proxy;
	}

}
