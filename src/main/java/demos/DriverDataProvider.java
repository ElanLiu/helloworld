package demos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.alipay.ats.config.PropertyConfig;
import com.alipay.ats.enums.EnvParamEnum;
import com.alipay.ats.util.RuntimeContextHolder;
import com.alipay.ats.util.StringUtils;

/**
 * 测试数据驱动
 * 
 * @author fred.fanj
 * @version $Id: DriverDataProvider.java, v 0.1 2009-7-22 下午03:46:48 fred.fanj
 *          Exp $
 */
public class DriverDataProvider implements Iterator<Object[]> {

    CSVReader                   reader     = null;

    private Class<?>[]          parameterTypes;

    private Converter[]         parameterConverters;

    public int                  sum        = 0;

    private static final Log    log        = LogFactory.getLog(DriverDataProvider.class);

    /**
     * 冒烟测试模式 smoke_test = true 执行基本测试集
     */
    public static final boolean smokeModel = PropertyConfig.isSmokeModel();

    /**
     * Basic constructor that will provide the data from the given file for the
     * given method *@throws IOException when file io fails
     */
    public DriverDataProvider(Class cls, Method method, String csvFilePath) {

        InputStream is = null;
        if (StringUtils.isNotBlank(System.getProperty(EnvParamEnum.DATA_PROVIDER_FILE.getEnvKey()))) {
            String prividerFile = System.getProperty(EnvParamEnum.DATA_PROVIDER_FILE.getEnvKey());
            try {
                is = new FileInputStream(prividerFile);
            } catch (FileNotFoundException e) {
                log.error("数据驱动文件不存在 [" + prividerFile + "]");
            }
        } else {
            is = cls.getClassLoader().getResourceAsStream(csvFilePath);
        }
        try {
            InputStreamReader isr = new InputStreamReader(is);
            reader = new CSVReader(isr, ',', '\"', 1);// 跳过data title;
            parameterTypes = (Class<String>[]) method.getParameterTypes();
            int len = parameterTypes.length;
            parameterConverters = new Converter[len];
            for (int i = 0; i < len; i++) {
                parameterConverters[i] = ConvertUtils.lookup(parameterTypes[i]);
            }
        } catch (RuntimeException e) {
            log.error(cls.getName() + "." + method.getName() + " TestData is not exist!");
        }

    }

    // The latest row we returned
    private String[] last;

    public boolean hasNext() {
        try {
            if (reader == null) {
                return false;
            }
            // 读取一行驱动数据
            last = reader.readNext();
            String jarVersion = PropertyConfig.getJarVersion();
            // 判断读取的驱动数据是否有效,如果无效则继续读取
            if (StringUtils.isNotBlank(jarVersion)
                && PropertyConfig.testConfigs.getPropertyValue("jarCaseControl").equals("true")) {
                if (smokeModel) {
                    for (int i = 0; last != null
                                    && (!last[0].startsWith("P1") || (last[0].startsWith("P1V") && StringUtils
                                        .compareLarger(last[0].substring(3, 4),
                                            jarVersion.substring(1)))); i++)
                        last = reader.readNext();
                } else {
                    for (int i = 0; last != null
                                    && (last[0].startsWith("V") && StringUtils.compareLarger(
                                        last[0].substring(1, 2), jarVersion.substring(1))); i++)
                        last = reader.readNext();
                }
            } else {
                for (int i = 0; smokeModel && last != null && !last[0].startsWith("P1"); i++)
                    last = reader.readNext();
            }
        } catch (IOException e) {
            log.error("Read row data error!");
        }
        return last != null;
    }

    /**
     * Get the next line,or the current line if it's already there.
     * 
     * @return the line.
     */
    private String[] getNextLine() {
        if (last == null) {
            try {
                last = reader.readNext();
            } catch (IOException ioe) {
                log.error("get next line error!");
                throw new RuntimeException(ioe);
            }
        }
        return last;
    }

    /**
     * @return the Object[]representation of the next line
     */
    public Object[] next() {
        String[] next;
        if (last != null) {
            next = last;
        } else {
            next = getNextLine();
        }
        last = null;
        Object[] args = parseLine(next);
        return args;
    }

    /**
     * @return the correctly parsed and wrapped values
     */
    private Object[] parseLine(String[] svals) {

        if (svals.length != parameterTypes.length) {
            log.error("驱动数据个数 [" + svals.length + "] 与参数个数 [" + parameterTypes.length + "] 不相等 , "
                      + svals[0]);
            return null;
        }

        int len = svals.length;
        if (len > 0) {
            RuntimeContextHolder.setCaseId(svals[0]);
        }
        Object[] result = new Object[len];
        log.info("=============== START [" + svals[0] + "] ===============");
        log.debug("======> 测试数据 用例ID [" + svals[0] + "] <======");

        for (int i = 0; i < len; i++) {
            result[i] = parameterConverters[i].convert(parameterTypes[i], (Object) svals[i]);
            log.debug(result[i]);
        }
        return result;
    }

    public void remove() {

    }

    /**
     * 获取reader的句柄，直接对之进行操作
     * 
     * @return
     * @author tinghe
     * @version $Id: getReader,v 0.1 2010-1-21 下午02:11:51 tinghe Exp $
     */
    public CSVReader getReader() {
        return this.reader;
    }

}
