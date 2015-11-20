package com.exigenservices.lectures.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Random;
import java.util.logging.SimpleFormatter;

/**
 * Created by Михаил on 06.11.2015.
 */
public class CheckTag extends SimpleTagSupport {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        try {
            out.print(Check(value));
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    public static boolean Check(String y) throws MyException {
        if(y == ""){
            throw new MyException("11");
        }
        Integer value1 = Integer.parseInt(y);
        int x = (new Random()).nextInt();
        if ((value1 >= 0) && (value1 <= 100)) {
            if (value1 == x) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
