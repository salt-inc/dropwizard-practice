package views;

import java.nio.charset.Charset;

import io.dropwizard.views.View;

public class JobOfferView extends View {

	public JobOfferView(String templateName) {
		super(templateName, Charset.forName("UTF-8"));
	}

}
