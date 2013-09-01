package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jumpmind.symmetric.csv.CsvReader;
import org.testng.annotations.DataProvider;

/**
 * 
 * @author yu.liuyly
 * 
 */
public class CsvDataProvider {

	private static final String RESOURCES = "./src/main/resources/dataprovider";

	private static final Logger logger = Logger
			.getLogger(CsvDataProvider.class);

	/**
	 * 
	 * @param mtd
	 * @return Iterator<Object[]>
	 */
	@DataProvider(name = "CsvDataProvider2")
	public static Iterator<Object[]> csvDataProvider2(Method mtd) {
		if (logger.isInfoEnabled()) {
			logger.info("Begin to input the parameters for testing.");
		}
		return loadCsvFile(mtd, assembleFileDir(mtd)).iterator();

	}

	/**
	 * 组装csv文件绝对路径
	 * 
	 * @param mtd
	 * @return File
	 */
	public static File assembleFileDir(Method mtd) {
		if (null == mtd) {
			if (logger.isInfoEnabled()) {
				logger.info("The method denoted is null.");
			}
		}

		Class<?> clz = mtd.getDeclaringClass();
		String clzName = clz.getSimpleName().toLowerCase();
		String mtdName = mtd.getName().toLowerCase();
		String uri = RESOURCES + "/" + clzName + "/" + mtdName + ".csv";

		if (logger.isInfoEnabled()) {
			logger.info("The file denoted is: " + uri);
		}

		File file = new File(uri);

		if (!isFileValidate(file)) {
			if (logger.isInfoEnabled()) {
				logger.info("Assebling the diretory of file is failed.");
			}
			return null;
		}
		return file;
	}

	/**
	 * 根据csv文件，组装list
	 * 
	 * @param file
	 * @return List<Object[]>
	 */
	public static List<Object[]> loadCsvFile(Method mtd, File file) throws IllegalArgumentException {
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Something wrong with the IO or the csv file is not be found.");
				return null;
			}
			e.printStackTrace();
		}
		CsvReader csvReader = new CsvReader(reader);
		List<Object[]> storage = new ArrayList<Object[]>();

		try {
			while (csvReader.readRecord()) {
				if (!isParamValidate(mtd, csvReader.getValues())) {
					throw new IllegalArgumentException();
				}
				storage.add(csvReader.getValues());
			}
		} catch (IOException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Something wrong with the IO, and the csv file has not be loaded.");
				return null;
			}
			e.printStackTrace();
		}

		return storage;
	}

	/**
	 * 文件校验
	 * 
	 * @param file
	 * @return boolean
	 */
	public static boolean isFileValidate(File file) {
		if (!file.exists()) {
			if (logger.isInfoEnabled()) {
				logger.info("The file do not exist.");
			}
			return false;
		}

		if (!file.isFile()) {
			if (logger.isInfoEnabled()) {
				logger.info("The file is not a file.");
			}
			return false;
		}

		if (!file.canRead()) {
			if (logger.isInfoEnabled()) {
				logger.info("The file can not be read.");
			}
			return false;
		}
		return true;
	}

	/**
	 * 参数校验
	 * 
	 * @param mtd
	 * @param obj
	 * @return
	 */
	public static boolean isParamValidate(Method mtd, Object[] obj) {
		Class<?>[] params = mtd.getParameterTypes();
		if (params.length != obj.length) {
			if (logger.isInfoEnabled()) {
				logger.info("Wrong number of arguments.");
			}
			return false;
		}
		/*TODO
		 * 将csv数据类型转换为method参数的数据类型
		 */
		return true;
	}

}
