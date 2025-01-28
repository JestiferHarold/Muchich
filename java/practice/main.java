import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class main {
	public static void main(String[] args) throws IOException{
		doc = Jsoup.connect("https://www.google.com/search?q=html+scrapper+for+java&client=firefox-b-d&sca_esv=ecb9c3497f6c7642&sxsrf=ADLYWIK8Rc2qQhPcjYfVvTYG_HHZQeTjDA%3A1737241384207&ei=KDOMZ_CzDJ-PseMPhq_8kAk&ved=0ahUKEwjw5-C8sICLAxWfR2wGHYYXH5IQ4dUDCBA&uact=5&oq=html+scrapper+for+java&gs_lp=Egxnd3Mtd2l6LXNlcnAiFmh0bWwgc2NyYXBwZXIgZm9yIGphdmEyBhAAGBYYHjIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHkjKLlCJA1jyLXACeAGQAQGYAbYCoAGAIqoBCDAuMTAuOS4xuAEDyAEA-AEBmAIVoAL5IKgCFMICChAAGLADGNYEGEfCAgcQIxgnGOoCwgIUEAAYgAQYkQIYtAIYigUY6gLYAQHCAhQQABiABBjjBBi0AhjpBBjqAtgBAcICFBAAGOMEGLQCGIkFGOkEGOoC2AEBwgIEECMYJ8ICChAjGIAEGCcYigXCAgsQABiABBiRAhiKBcICCxAAGIAEGLEDGIMBwgIREC4YgAQYsQMY0QMYgwEYxwHCAggQABiABBixA8ICBRAuGIAEwgIOEAAYgAQYkQIYsQMYigXCAgoQABiABBhDGIoFwgIFEAAYgATCAgcQABiABBgKwgIIEAAYgAQYogTCAggQABiiBBiJBcICCxAAGIAEGIYDGIoFwgIHEAAYgAQYDcICBhAAGA0YHsICCBAAGBYYChgemAMQ4gMFEgExICnxBR4REM4v-hw9iAYBkAYGugYGCAEQARgBkgcGMi42LjEzoAfamQE&sclient=gws-wiz-serp").get();
		String f = doc.select("h1").attr("title");
		System.out.println(f);
	}
}
