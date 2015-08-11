package net.toster;

import java.io.IOException;

import net.toster.prog.SiteParser;

public class Application {

	String link = "https://toster.ru/q/";

	public void run() throws IOException {

		SiteParser siteParser = new SiteParser();
		siteParser.setLink(link);
		siteParser.parseSite();

	}
}
