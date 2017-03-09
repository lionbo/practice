package org.lionbo.practice.drools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

    public static void main(String[] args) {
        Pattern personRecoginer = Pattern.compile("(\\d+)号客服(.+)为您服务", Pattern.CASE_INSENSITIVE);
        String str = "您好，感谢您的耐心等待，系统已为您接入人工服务。\n1557号客服小马为您服务。";
        Matcher matcher = personRecoginer.matcher(str);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }
    }

}
