package ua.nure.moskovchenko.web.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static ua.nure.moskovchenko.exception.Messages.ERR_CANNOT_FIND_I18N_WORD;

public class i18nTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(i18nTag.class);
    private String value;

    @Override
    public int doStartTag() throws JspException {

        String lang = (String) pageContext.getSession().getAttribute("lang");

        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(lang));
        JspWriter out = pageContext.getOut();
        try {
            out.println(rb.getString(value));
        } catch (IOException e) {
            LOG.error(ERR_CANNOT_FIND_I18N_WORD, e);
        }

        return SKIP_BODY;
    }

    public void setValue(String value){
        this.value= value;
    }
}
