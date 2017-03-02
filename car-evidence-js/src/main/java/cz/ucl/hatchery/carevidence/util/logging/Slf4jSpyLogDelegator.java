/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.logging;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.log4jdbc.Properties;
import net.sf.log4jdbc.sql.Spy;

/**
 * Delegates JDBC spy logging events to the the Simple Logging Facade for Java
 * (slf4j).
 * 
 *
 */
public class Slf4jSpyLogDelegator extends net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator {

	/**
	 * Logger that shows the SQL timing, post execution
	 */
	private final Logger sqlTimingLogger = LoggerFactory.getLogger("jdbc.sqltiming");

	private static String nl = System.getProperty("line.separator");

	/**
	 * Special call that is called only for JDBC method calls that contain SQL.
	 *
	 * @param spy
	 *            the Spy wrapping the class where the SQL occurred.
	 * @param execTime
	 *            how long it took the SQL to run, in milliseconds.
	 * @param methodCall
	 *            a description of the name and call parameters of the method
	 *            that generated the SQL.
	 * @param sql
	 *            SQL that occurred.
	 */
	@Override
	public void sqlTimingOccurred(final Spy spy, final long execTime, final String methodCall, final String sql) {
		if (sqlTimingLogger.isErrorEnabled() && (!Properties.isDumpSqlFilteringOn() || shouldSqlBeLogged(sql))) {
			if (Properties.isSqlTimingErrorThresholdEnabled()
					&& execTime >= Properties.getSqlTimingErrorThresholdMsec()) {
				sqlTimingLogger
						.error(buildSqlTimingDump(spy, execTime, methodCall, sql, sqlTimingLogger.isDebugEnabled()));
			} else if (sqlTimingLogger.isWarnEnabled()) {
				if (Properties.isSqlTimingWarnThresholdEnabled()
						&& execTime >= Properties.getSqlTimingWarnThresholdMsec()) {
					sqlTimingLogger
							.warn(buildSqlTimingDump(spy, execTime, methodCall, sql, sqlTimingLogger.isDebugEnabled()));
				} else if (sqlTimingLogger.isDebugEnabled()) {
					sqlTimingLogger.debug(buildSqlTimingDump(spy, execTime, methodCall, sql, true));
				} else if (sqlTimingLogger.isInfoEnabled()) {
					sqlTimingLogger.info(buildSqlTimingDump(spy, execTime, methodCall, sql, false));
				}
			}
		}
	}

	/**
	 * Determine if the given sql should be logged or not based on the various
	 * DumpSqlXXXXXX flags.
	 *
	 * @param sql
	 *            SQL to test.
	 * @return true if the SQL should be logged, false if not.
	 */
	private boolean shouldSqlBeLogged(String sql) {
		if (sql == null) {
			return false;
		}
		sql = sql.trim();

		if (sql.length() < 6) {
			return false;
		}
		sql = sql.substring(0, 6).toLowerCase();
		return (Properties.isDumpSqlSelect() && "select".equals(sql))
				|| (Properties.isDumpSqlInsert() && "insert".equals(sql))
				|| (Properties.isDumpSqlUpdate() && "update".equals(sql))
				|| (Properties.isDumpSqlDelete() && "delete".equals(sql))
				|| (Properties.isDumpSqlCreate() && "create".equals(sql));
	}

	/**
	 * Helper method to quickly build a SQL timing dump output String for
	 * logging.
	 *
	 * @param spy
	 *            the Spy wrapping the class where the SQL occurred.
	 * @param execTime
	 *            how long it took the SQL to run, in milliseconds.
	 * @param methodCall
	 *            a description of the name and call parameters of the method
	 *            that generated the SQL.
	 * @param sql
	 *            SQL that occurred.
	 * @param debugInfo
	 *            if true, include debug info at the front of the output.
	 *
	 * @return a SQL timing dump String for logging.
	 */

	private String buildSqlTimingDump(final Spy spy, final long execTime, final String methodCall, final String sql,
			final boolean debugInfo) {
		final StringBuffer out = new StringBuffer();

		out.append("[");
		out.append(spy.getConnectionNumber());
		out.append("] {duration:");
		out.append(execTime);
		out.append("ms} - ");
		out.append(processSql(sql));

		return out.toString();
	}

	/**
	 * Break an SQL statement up into multiple lines in an attempt to make it
	 * more readable
	 *
	 * @param sql
	 *            SQL to break up.
	 * @return SQL broken up into multiple lines
	 */
	private String processSql(String sql) {
		if (sql == null) {
			return null;
		}

		if (Properties.isSqlTrim()) {
			sql = sql.trim();
		}

		StringBuilder output = new StringBuilder();

		if (Properties.getDumpSqlMaxLineLength() <= 0) {
			output.append(sql);
		} else {
			// insert line breaks into sql to make it more readable
			final StringTokenizer st = new StringTokenizer(sql);
			String token;
			int linelength = 0;

			while (st.hasMoreElements()) {
				token = (String) st.nextElement();

				output.append(token);
				linelength += token.length();
				output.append(" ");
				linelength++;
				if (linelength > Properties.getDumpSqlMaxLineLength()) {
					output.append(nl);
					linelength = 0;
				}
			}
		}

		if (Properties.isDumpSqlAddSemicolon()) {
			output.append(";");
		}

		String stringOutput = output.toString();

		if (Properties.isTrimExtraBlankLinesInSql()) {
			final LineNumberReader lineReader = new LineNumberReader(new StringReader(stringOutput));

			output = new StringBuilder();

			int contiguousBlankLines = 0;
			try {
				while (true) {
					final String line = lineReader.readLine();
					if (line == null) {
						break;
					}

					// is this line blank?
					if (line.trim().length() == 0) {
						contiguousBlankLines++;
						// skip contiguous blank lines
						if (contiguousBlankLines > 1) {
							continue;
						}
					} else {
						contiguousBlankLines = 0;
						output.append(line);
					}
					output.append(nl);
				}
			} catch (final IOException e) {
				// since we are reading from a buffer, this isn't likely to
				// happen,
				// but if it does we just ignore it and treat it like its the
				// end of the stream
			}
			stringOutput = output.toString();
		}

		return stringOutput;
	}

}
