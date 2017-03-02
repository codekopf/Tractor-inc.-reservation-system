package cz.ucl.hatchery.carevidence.tiles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cz.ucl.hatchery.carevidence.i18n.CarEvidenceLocaleProvider;

public class LanguageMenuProvider {

	public List<LanguageMenuItem> getMenuItems(final HttpServletRequest request) {

		final List<LanguageMenuItem> items = new ArrayList<LanguageMenuProvider.LanguageMenuItem>();
		for (final String language : CarEvidenceLocaleProvider.getSupportedLanguages()) {

			final LanguageMenuItem item = new LanguageMenuItem();
			item.setTitle(language.toUpperCase());
			item.setUri("?" + CarEvidenceLocaleProvider.PARAM_LANG + "=" + language);

			items.add(item);

		}

		return items;
	}

	public class LanguageMenuItem {

		private String title;
		private String uri;

		public String getTitle() {
			return title;
		}

		public void setTitle(final String title) {
			this.title = title;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(final String uri) {
			this.uri = uri;
		}

		@Override
		public String toString() {
			return "LanguageMenuItem [title=" + title + ", uri=" + uri + "]";
		}

	}

}
