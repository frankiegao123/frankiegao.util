package net.blogjava.frankiegao123.jpa.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.blogjava.frankiegao123.log.slf4j.Log;
import net.blogjava.frankiegao123.log.slf4j.LogFactory;
import net.blogjava.frankiegao123.stuts2.entity.User;

public class JpaQueryParserTest {

    private static Log log = LogFactory.getLog(JpaQueryParserTest.class);

    private static Logger logger = LoggerFactory.getLogger(JpaQueryParserTest.class);

    private static JpaQueryBuilder builder = new JpaQueryBuilder();

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setUsername("gao%");
        user.setPassword("1234567");
        user.getDate().setFromDateStr("2010-06-01");
        user.getDate().setToDateStr("2010-06-01");

        Paging paging = new Paging();
        paging.setOrderColumn("username");
        paging.setOrderDirection("der");
        builder.doQuery(user, paging);
    }
}
