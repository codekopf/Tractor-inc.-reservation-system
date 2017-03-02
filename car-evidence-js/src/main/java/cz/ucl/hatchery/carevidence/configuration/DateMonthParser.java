package cz.ucl.hatchery.carevidence.configuration;

public interface DateMonthParser {

	public DateMonth parse(String date);

	public interface DateMonth {

		public int getDate();

		public int getMonth();

	}

}
