package net.toster.prog;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import net.toster.prog.model.Question;
import net.toster.prog.util.FileUtils;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SiteParser {

	private String link;
	private List<Question> list = new LinkedList<Question>();

	public void parseSite() throws IOException {
		int index = 0;
		label: for (int k = index; k < 250_000; k++) {
			try {
				Document document = Jsoup.connect(link + String.valueOf(k)).timeout(10000).get();
				Elements questionPage = document.select(".question__title");
				System.out.println(k + ") " + questionPage.get(0).text());
				
				Question question = new Question();
				question.setName(questionPage.get(0).text());
				question.setLink(link + String.valueOf(k));
				list.add(question);
				
				if((list.size() % 100) == 0) {
					System.out.println(" === SAVE TO FILE ===");
					FileUtils.writeToFile(list);
					list.clear();
				}
			} catch (HttpStatusException e) {
				System.out.println(e.getMessage());
			} catch(SocketTimeoutException e) {
				System.out.println(e.getMessage());
				index += k;
				continue label;
			}
		}
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
