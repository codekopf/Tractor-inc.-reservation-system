package cz.ucl.hatchery.carevidence.configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.ucl.hatchery.carevidence.web.CommonConstants;

public class DateMonthParserBean implements DateMonthParser {

	@Override
	public DateMonth parse(final String dateFragment) {

		final Pattern pattern = Pattern.compile(CommonConstants.DATE_MONTH_REGEXP_PATTERN);
		final Matcher matcher = pattern.matcher(dateFragment);

		if (matcher.find()) {
			final int day = Integer.parseInt(matcher.group(1));
			final int month = Integer.parseInt(matcher.group(2)) - 1;

			return new DateMonthValue(day, month);
		}

		return null;

	}

	public class DateMonthValue implements DateMonth {

		private int date;
		private int month;

		private DateMonthValue(final int date, final int month) {
			this.date = date;
			this.month = month;
		}

		@Override
		public int getDate() {
			return date;
		}

		@Override
		public int getMonth() {
			return month;
		}

		@Override
		public String toString() {
			final StringBuilder builder = new StringBuilder();
			builder.append("DateMonthValue [date=");
			builder.append(date);
			builder.append(", month=");
			builder.append(month);
			builder.append("]");
			return builder.toString();
		}

	}

}
