package cz.ucl.hatchery.carevidence.web;

import java.util.Locale;

public class CommonConstants {

	public static final String ENCODING_UTF8 = "UTF-8";
	public static final Locale CZECH_LOCALE = new Locale("cs");
	public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	// REGEXP
	public static final String DATE_MONTH_REGEXP_PATTERN = "^([0-9]{2})\\.([0-9]{2})\\.$";

	public static final Long VOLUME_0 = 0L;
	public static final Long VOLUME_1 = 1L;
	public static final Long VOLUME_2 = 2L;

	public static final long SERIAL_UID = 1L;

	public static final String SLASH = "/";
	public static final String BACK_SLASH = "\\";
	public static final String QUESTION_MARK = "?";
	public static final String AMPERSAND = "&";
	public static final String EMPTY_STRING = "";
	public static final String LEFT_BRACKET = "(";
	public static final String RIGHT_BRACKET = ")";
	public static final String LEFT_CURLY_BRACKET = "{";
	public static final String RIGHT_CURLY_BRACKET = "}";
	public static final String PIPE = "|";
	public static final String WHITESPACE = " ";
	public static final String COMMA = ",";
	public static final String DASH = "-";
	public static final String DOT = ".";
	public static final String COLON = ":";
	public static final String UNDERSCORE = "_";
	public static final String QUOTATION = "\"";
	public static final String SEMICOLON = ";";
	public static final String PLUS = "+";
	public static final String EQUAL = "=";
	public static final String ASTERISK = "*";
	public static final Integer RUFAK_ROW_SIZE = 72;

	public static final String COMMA_SEPARATOR = ", ";

	public static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String WINDOWS_LINE_SEPARATOR = "\r\n";

	public static final String OK_RESULT = "OK";

	// MIME TYPE
	public static final String MIME_TYPE_APPLICATION_JSON = "application/json";
	public static final String MIME_TYPE_EXCEL_XLS = "application/xls";
	public static final String MIME_TYPE_APPLICATION_PDF = "application/pdf";
	public static final String MIME_TYPE_PLAIN_TEXT = "text/plain";
	public static final String CSV_CONTENT_TYPE = "text/csv";

	public static final String SUFFIX_XLS = "xlsx";
	public static final String SUFFIX_TXT = "txt";
	public static final String SUFFIX_PDF = "pdf";
	public static final String SUFFIX_CSV = "csv";
	public static final String ATTACHMENT_DESCRIPTION_FORMAT = "%s '%s'";

	public static final String MSG_CAUGHT_EXCEPTION = "CAUGHT EXCEPTION ";

}