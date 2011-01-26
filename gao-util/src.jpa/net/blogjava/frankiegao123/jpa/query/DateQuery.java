package net.blogjava.frankiegao123.jpa.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.blogjava.frankiegao123.util.Tools;

/**
 * ��ѯ��Χʱ�� JavaBean
 *
 * @author frankiegao
 * @since 2009-1-6 ����08:07:59
 */
public class DateQuery {
    
    /**
     * ������ʼʱ��
     */
    private Date fromDate;
    
    /**
     * ���ڽ���ʱ��
     */
    private Date toDate;
    
    /**
     * ��ʼʱ����ַ�����ʽ
     */
    private String fromDateStr;
    
    /**
     * ����ʱ����ַ�����ʽ
     */
    private String toDateStr;
    
    private SimpleDateFormat sdf = null;
    
    private boolean isMonth = false;
    
    public DateQuery() {
        this(false);
    }
    
    public DateQuery(boolean isMonth) {
        this.isMonth = isMonth;
        if(isMonth) {
            // ��¼����£������²�ѯ��
            sdf = new SimpleDateFormat("yyyy-MM");
        } else {
            // ��¼�����գ��������ڲ�ѯ��
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
    }
    
    public Date getFromDate() {
        if(this.fromDate == null && Tools.isNotEmpty(this.fromDateStr)) {
            this.fromDate = parse(this.fromDateStr);
        }
        return this.fromDate;
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        if(fromDate != null) {
            this.fromDateStr = sdf.format(fromDate);
        } else {
            this.fromDateStr = null;
        }
    }
    
    public Date getToDate() {
        if(this.toDate == null && Tools.isNotEmpty(this.toDateStr)) {
            this.toDate = parse(this.toDateStr);
        }
        return toDate;        
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
        if(toDate != null) {
            this.toDateStr = format(this.toDate);
        } else {
            this.toDateStr = null;
        }
    }
    
    public String getFromDateStr() {
        if(Tools.isNotEmpty(this.fromDateStr) && this.fromDate != null) {
            this.fromDateStr = format(this.fromDate);
        }
        return this.fromDateStr;
    }
    
    public void setFromDateStr(String fromDateStr) {
        this.fromDateStr = fromDateStr;
        if(fromDateStr != null) {
            this.fromDate = parse(fromDateStr);
        }
    }
    
    public String getToDateStr() {
        if(Tools.isNotEmpty(this.toDateStr) && this.toDate != null) {
            this.toDateStr = format(this.toDate);
        }
        return toDateStr;
    }
    
    public void setToDateStr(String toDateStr) {
        this.toDateStr = toDateStr;
        if(toDateStr != null) {
            this.toDate = parse(toDateStr);
        }
    }
    
    /**
     * ��ý���ʱ�����һ�������һ�£����ڲ�ѯ������
     * @return
     *
     * @author frankiegao
     * @since 2009-1-6 ����08:09:54
     */
    public Date getNextToDate() {
        Date date = getToDate();
        if(date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int add = isMonth ? Calendar.MONTH : Calendar.DATE;
        c.add(add, 1);
        return c.getTime();
    }
    
    public int getFromDateInt() {
        if(Tools.isEmpty(fromDateStr)) {
            return 0;
        }
        return Integer.parseInt(fromDateStr.replace("-", ""));
    }
    
    public int getToDateInt() {
        if(Tools.isEmpty(toDateStr)) {
            return 0;
        }
        return Integer.parseInt(toDateStr.replace("-", ""));
    }
    
    /**
     * ���ַ���תΪ Date
     * @param str
     * @return
     *
     * @author frankiegao
     * @since 2009-1-6 ����08:10:15
     */
    private Date parse(String str) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * ��ʽ���ַ���
     * @param date
     * @return
     *
     * @author frankiegao
     * @since 2009-1-6 ����08:10:25
     */
    private String format(Date date) {
        return sdf.format(date);
    }
}