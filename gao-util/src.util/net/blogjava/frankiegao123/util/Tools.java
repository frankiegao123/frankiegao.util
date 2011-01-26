package net.blogjava.frankiegao123.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Transient;

/**
 * �����г��õĹ��÷���
 * 
 * @author Gao Baowen
 * @since  2009-03-12
 */
public class Tools {
    
    private final static String PROPERTY_ID = "id";
    
    public static <T extends Object> void increment(T key, int increment, Map<T, Integer> map) {
        Integer orignal = map.get(key);
        if(orignal == null) {
            orignal = 0;
        }
        orignal += increment;
        map.put(key, orignal);
    }
    
    public static int parseInt(String str, int defaultValue) {
        if(Tools.isBlank(str)) {
            return defaultValue;
        }
        int result = defaultValue;
        try {
            result = Integer.parseInt(str);
        }catch(Exception e) {
            result = defaultValue;
        }
        return result;
    }
    
    public static long parseLong(String str, long defaultValue) {
        if(Tools.isBlank(str)) {
            return defaultValue;
        }
        long result = defaultValue;
        try {
            result = Long.parseLong(str);
        }catch(Exception e) {
            result = defaultValue;
        }
        return result;
    }
    
    /**
     * ȥ���ַ�����β�Ŀհ��ַ�
     * 
     * @param str
     * @return
     *
     * @author Gao Baowen
     * @since 2009-6-1 ����02:53:39
     */
    public static String trim(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        return str.trim();
    }
    
    /**
     * ȥ�������еĿհ��ַ�
     * @param str
     * @return
     */
    public static String removeSpace(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        return str.trim().replaceAll("\\s", "");
    }
    
    /**
     * �� byte[] д���ļ�
     * @param bys
     * @param file
     * @throws IOException
     *
     * @author Gao Baowen
     * @since 2009-5-27 ����04:22:27
     */
    public static void writeBytes2File(byte[] bys, File path, String filename) throws IOException {
        if(Tools.isBlank(bys) || path == null || isBlank(filename)) {
            return;
        }        
        BufferedOutputStream bos = null;        
        try {
            File file = new File(path, filename);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bys);
        } finally {
            bos.close();
        }
    }
    
    /**
     * ����ļ��ĺ�׺��

     * @param filename
     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-27 ����04:23:56
     */
    public static String getLowerCaseFileSuffixName(String filename) {
        int idx = filename.lastIndexOf(".");
        if(idx < 0) {
            return "";
        }
        return filename.substring(idx).toLowerCase();
    }
    
    /**
     * �� yyyy-MM-dd HH:mm:ss �ĸ�ʽ�������ڵĸ�ʽ��

     * @param date
     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-25 ����11:40:08
     */
    public static String defaultFormat(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    public static String shortFormat(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    /**
     * �жϼ����Ƿ�Ϊ�ջ����ǿհ��ַ�
     * @param str
     * @return
     *
     * @author Gao Baowen
     * @since 2009-4-10 ����04:11:51
     */
    public static boolean isBlank(Collection<?> c) {
        if(c == null || c.size() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * �ж������Ƿ�Ϊ��
     * @param num
     * @return
     *
     * @author Gao Baowen
     * @since 2009-4-16 ����10:20:29
     */
    public static boolean isBlank(Number num) {
        return num == null;
    }
    
    /**
     * ��� byte[] �Ƿ�Ϊ��
     * @param bys
     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-27 ����04:18:52
     */
    public static boolean isBlank(byte[] bys) {
        return (bys == null || bys.length == 0);
    }
    
    /**
     * ���������Ƿ��в�Ϊ�յ�ֵ��ֻҪ��һ����Ϊ��ʱ�ͷ��� true
     * @param args
     * @return
     *
     * @author Gao Baowen
     * @since 2009-6-3 ����10:24:21
     */
    public static boolean hasAnyNotBlank(Object...args) {
        for(int i = 0; i < args.length; i++) {
            if(!isBlank(args[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isBlank(Object obj) {
        if(obj == null) {
            return true;
        }
        if(obj instanceof String) {
            return ((String)obj).trim().length() == 0;
        }
        if(obj instanceof Collection<?>) {
            return ((Collection<?>)obj).size() == 0;
        }
        if(obj instanceof byte[]) {
            return ((byte[])obj).length == 0;
        }
        if(obj instanceof String[]) {
            return ((String[])obj).length == 0;
        }
        throw new IllegalArgumentException("undefined type: " + obj.getClass().getName());
    }
    
    /**
     * �� Map �ļ�ֵ��ת


     * @param <K>
     * @param <V>
     * @param map
     * @return
     *
     * @author Gao Baowen
     * @since 2009-4-16 ����11:06:18
     */
    public static <K, V> Map<V, K> convertKey2Value(Map<K, V> map) {
        if(map == null) {
            return null;
        }
        Map<V, K> result = new LinkedHashMap<V, K>();
        for(Iterator<K> i = map.keySet().iterator(); i.hasNext(); ) {
            K key = i.next();
            result.put(map.get(key), key);
        }
        return result;
    }
    
    /**
     * ��ô������ڵ�ǰһ�죬�ض����գ�ʱ���֡��롢����ȫ�����㣩
     * @param date
     * @return
     *
     * @author Gao Baowen
     * 2009-4-2 ����09:54:23
     */
    public static Date getPreDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
    
    /**
     * ����ַ�����

     * 
     * @param str           ��Ҫ�����ַ���

     * @param maxChar       ���������ַ���    
     * @param infoPrefix    �������ʱ��ʾ����Ϣ
     * @return
     *
     * @author Gao Baowen
     * 2009-4-1 ����09:56:32
     */
    public static boolean checkLength(String str, int maxChar, String infoPrefix) {
        if(str == null || str.length() == 0) {
            return true;
        }
        int len = getStringCharLength(str);
        if(len > maxChar) {
            return false;
        }
        return true;
    }
    
    /**
     * ����ַ����ֽڳ��ȣ�������Ϊ 2 �����ȣ���ĸ������Ϊ 1 ������

     * @param str           ��Ҫ��鳤�ȵ��ַ���

     * @param maxByte       �������ĳ���
     * @param infoPrefix    �ֶε����֣����ڳ���ʱ����ʾ
     * @return
     *    true  --> ���ȼ��ͨ��<br />
     *    false --> ���ȼ�鲻ͨ��
     *
     * @author Gao Baowen
     * @since 2009-6-17 ����04:58:12
     */
    public static boolean checkByteLength(String str, int maxByte, String infoPrefix) {
        if(str == null || str.length() == 0) {
            return true;
        }
        int len = getStringByteLength(str);
        if(len > maxByte) {
            return false;
        }
        return true;
    }
    
    public static boolean isNotEmpty(String str) {
        if(str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }
    
    public static boolean isBlank(String str) {
        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * ��� yyyyMM ��ʽ��һ����ֵ

     * @param ym
     * @return
     *
     * @author Gao Baowen
     * 2009-3-30 ����01:53:38
     */
    public static int nextMonth(int ym) {
        int m = ym % 100;
        return (ym / 100 + m / 12) * 100 + (m % 12) + 1;
    }
    
    public static int decrementOneMonth(int ym) {
        int m = ym % 100 - 1;
        int y = ym / 100;
        if(m == 0) {
            m = 12;
            y--;
        }
        return y * 100 + m;
    }
    
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(parseShortDate("2009-13-1")));
    }
    
    /**
     * �� Map �ļ�ֵ��ת

     * @param map
     * @return
     *
     * @author Gao Baowen
     * 2009-3-28 ����01:52:56
     */
    public static Map<Integer, String> convertValue2Key(Map<String, Integer> map) {
        Map<Integer, String> result = new LinkedHashMap<Integer, String>();
        if(map == null || map.size() == 0) {
            return result;
        }
        for(Iterator<String> i = map.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            result.put(map.get(key), key);
        }
        return result;
    }
    
    /**
     * �����ֽ�ĳ���ַ����ĳ��ȣ���ĸ�� ASCII �ַ���Ϊһ�����ȣ����ֵ�ȫ���ַ���Ϊ�������ȡ�

     * @param str
     * @return
     *
     * @author Gao Baowen
     * 2009-3-27 ����03:16:22
     */
    public static int getStringByteLength(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        int count = 0;
        char[] chs = str.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            count += (chs[i] > 0xff) ? 2 : 1;
        }
        return count;
    }
    
    /**
     * �����ַ�����ĳ���ַ����ĳ��ȡ�

     * @param str
     * @return
     *
     * @author Gao Baowen
     * 2009-3-27 ����03:16:22
     */
    public static int getStringCharLength(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        return str.length();
    }

    /**
     * �ж��ַ����Ƿ�Ϊ�գ�Ϊ������ null or "";
     * 
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        
        if(string==null||string.trim().equals("")) return true;
        else return false;
        
    }
    
    public static Date parseShortDate(String str) {
        if(isBlank(str)) {
            return null;
        }
        Date date = null;       
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }       
        return date;
    }
    
    /**
     * ����ָ�������ں�ʱ���ʽȡ����Ӧ���ַ��� <br>
     * ������null
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormatDateString(Date date,String pattern){
        String formatDateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            formatDateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sdf = null;
        }
        return formatDateString;
    }
    
    /**
     * ����ָ���������ַ��������ڸ�ʽȡ��Dateʵ�� <br>
     * ������ null
     * 
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date getDateFromString(String dateString,String pattern){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sdf = null;
        }
        return date;
    }    
    
    public static Date parseDate(String date) {
        if(date == null || date.length() == 0) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date.trim());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean containsDate(Date current, Date start, Date end) {
        long currentMillis = current.getTime();
        return (currentMillis >= start.getTime()) && (currentMillis < end.getTime());
    }
    
    /**
     * �õ�����µĵ�һ�죬�������ֶ�����

     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-8 ����03:05:11
     */
    public static Date getMonthFirstDate() {
        Calendar c = getCurrentCalendar();
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }
    
    /**
     * �õ����죬�������ֶ�����
     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-8 ����07:48:01
     */
    public static Date getCurrentDate() {
        return getCurrentCalendar().getTime();
    }
    
    /**
     * �õ�����ĺ�һ�죬�������ֶ�����

     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-8 ����03:05:28
     */
    public static Date getNextDate() {
        Calendar c = getCurrentCalendar();
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
    
    /**
     * �õ��¸��µĵ�һ�죬�������ֶ�����

     * @return
     *
     * @author Gao Baowen
     * @since 2009-5-8 ����03:05:39
     */
    public static Date getNextMonthFirstDate() {
        Calendar c = getCurrentCalendar();
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }
    
    public static Date getCurrentMonthLastSecond() {
        Date date = getNextMonthFirstDate();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }
    
    private static Calendar getCurrentCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }   
    
    
    /**
     * setת���ַ���

     * @param set
     * @return
     */
    public static String set2String(Set<String> set) {
        if(Tools.isBlank(set)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(Iterator<String> i = set.iterator(); i.hasNext(); ) {
            if(k++ > 0) {
                sb.append(",");
            }
            sb.append("'").append(i.next()).append("'");
        }
        return sb.toString();
    }
    
    
    /**
     * setת���ַ���

     * @param set
     * @return
     */
    public static String set2Long(Set<Long> set) {
        if(Tools.isBlank(set)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(Iterator<Long> i = set.iterator(); i.hasNext(); ) {
            if(k++ > 0) {
                sb.append(",");
            }
            sb.append("'").append(i.next()).append("'");
        }
        return sb.toString();
    }
    
    /**
     * ���ڵ����ļ�ʱ�ļ�������
     * @param filename
     * @return
     *
     * @author Gao Baowen
     * @since 2009-6-11 ����02:09:49
     */
    public static String encodeFilename(String filename) {
        if(Tools.isBlank(filename)) {
            return "undefined";
        }
        String str = null;
        try {
            str = new String(filename.getBytes("GB2312"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "undefined";
            int index = filename.lastIndexOf(".");
            if(index > 0) {
                str += filename.substring(index);
            }
        }
        return str;
    }
    
    /**
     * ���� toString ����
     * @param obj
     * @return
     *
     * @author Gao Baowen
     * @since 2009-6-18 ����10:51:08
     */
    public static String buildString(Object target) {
        if(target == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        appendBasicInfo(target, sb);
        try {
            BeanInfo info = Introspector.getBeanInfo(target.getClass(), Object.class);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            for(int i = 0; i < pds.length; i++) {
                if(PROPERTY_ID.equals(pds[i].getName())) {
                    appendProperty(pds[i], target, sb);
                }
            }            
            for(int i = 0; i < pds.length; i++) {
                sb.append(", ");
                appendProperty(pds[i], target, sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    private static StringBuilder appendBasicInfo(Object target, StringBuilder sb) {
        sb.append(target.getClass().getName());
        sb.append("@");
        sb.append(target.hashCode());
        sb.append(": ");
        return sb;
    }
    
    private static StringBuilder appendProperty(PropertyDescriptor pd, Object target, StringBuilder sb)
            throws Exception {
        Method readMethod = pd.getReadMethod();
        if(readMethod == null) {
            return sb;
        }       
        // ���� @Transient ����

        // if(readMethod == null || readMethod.getAnnotation(Transient.class) != null) {
        //    return sb;
        // }
        if(readMethod.getAnnotation(Transient.class) != null) {
            sb.append("[@Transient]");
        }
        sb.append(pd.getName());
        sb.append(" = ");
        sb.append(invokeGetMethod(readMethod, target));
        return sb;
    }
    
    /**
     * �������Ե� get ������������һ�� String ���͵�ֵ

     * @param pd
     * @param target
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *
     * @author Gao Baowen
     * @since 2009-6-18 ����10:56:44
     */
    private static String invokeGetMethod(Method method, Object target)
            throws Exception {
        if(method == null) {
            return "[GET METHOD UNKNOW]";
        }
        Object result = method.invoke(target, new Object[0]);
        if(result == null) {
            return "null";
        }
        if(result instanceof Date) {
            // ���ý��Ϊ Data ����ʱ����ʽ��
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)result);
        }
        if(result instanceof byte[]) {
            return "[BLOB length: " + ((byte[])result).length + "]";
        }
        return String.valueOf(result);
    }
    
    /**
     * ��ȡ�ַ��������ڽ�ȡ���ַ��������ָ����׺������ַ�������С��ָ������ʱ����Ӻ�׺
     * ԭ�����ء�
     * 
     * @param str      ��Ҫ��ȡ���ַ���
     * @param length   ��ȡ�ַ����ĳ��ȣ���ĸ�� 1 ���֣�ȫ���ַ��� 2 ����
     * @param suffix   ����ʱ��ӵĺ�׺
     * @return
     *
     * @author Gao Baowen
     * @since 2009-8-6 ����02:28:48
     */
    public static String truncate(String str, int length, String suffix) {
        if((str == null) || (str.length() == 0) || (length < 1)) {
            return str;
        }
        char[] chs = str.toCharArray();
        int len = 0;
        int offset = 0;
        for(int i = 0; i < chs.length; i++, offset++) {
            len += (chs[i] > 0xff) ? 2 : 1;
            if(len > length) {
                break;
            }
        }
        if(offset == chs.length) {
            return str;
        }
        if(suffix == null || suffix.trim().length() == 0) {
            return new String(chs, 0, offset);
        }
        return new String(chs, 0, offset) + suffix.trim();
    }
}