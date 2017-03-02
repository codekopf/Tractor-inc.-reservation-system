package cz.ucl.hatchery.carevidence.web;

import java.util.ArrayList;
import java.util.List;

public class TimeDurationFormatter {

	private int seconds;

	private boolean withSeconds = true;

	private static final int[] interval = { 86400, 3600, 60, 0 };
	private static final String[] maskToken = { "%dd", "%dh", "%dmin", "%ds" };

	private String duration = "";

	public TimeDurationFormatter(final int seconds) {
		initialize(seconds);
	}

	public TimeDurationFormatter(final int seconds, final boolean withSeconds) {
		this.withSeconds = withSeconds;
		initialize(seconds);
	}

	private void initialize(final int seconds) {
		this.seconds = seconds;
		if (this.seconds > 0) {
			prepareFormatAndArgs();
		} else if (withSeconds) {
			format(maskToken[3], new Integer[] { 0 });
		}
	}

	public String getDuration() {
		return duration;
	}

	private void prepareFormatAndArgs() {

		final StringBuffer mask = new StringBuffer();
		final List<Integer> args = new ArrayList<Integer>();
		final int n = interval.length - (withSeconds ? 0 : 1);
		for (int i = 0; i < n; i++) {

			if (seconds >= interval[i] && seconds > 0) {

				int volume;
				mask.append(maskToken[i]).append(" ");
				switch (i) {
				case 0:
					volume = seconds / interval[i];
					args.add(volume);
					seconds -= volume * interval[i];
					break;
				case 1:
					volume = (seconds % interval[i - 1]) / interval[i];
					args.add(volume);
					seconds -= interval[i];
					break;
				case 2:
					args.add((seconds % interval[i - 1]) / interval[i]);
					seconds -= interval[i];
					break;
				case 3:
					args.add(seconds % interval[i - 1]);
					seconds -= interval[i];
					break;
				}

			}

		}

		format(mask.toString(), args.toArray());

	}

	private void format(final String mask, final Object[] args) {
		duration = String.format(mask, args).trim();
	}

}
