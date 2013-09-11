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
 * 说明： 因为需要返回一个iterator，所以该类其实就是一个数据集合，该集合的数据来自csv文件，用于通过注解提供给外界
 * 用于参数化使用。所以该类应该是一个集合的形式，用于存储数据。
 * 在面向对象设计中，一个难点就是辨认对象的职责。理想的状态下，一个类应该只有一个单一的职责。职责分离可以
 * 最大限度的去耦合，但是职责单一说起来容易，做起来难。具体到本模式，我们明显可以看到，一个聚合对象它提供了
 * 两个职责 一是组织管理数据对象，二是提供遍历算法。如果该遍历算法有变化，那么我们就隔离变化 把它单独提取
 * 出来抽象为一个迭代器，这就是本模式的本质。
 * 通俗的来说，就是，别人可能要用到我们的容器元素，但是我只想让他用元素，不想让他知道容器是怎么样的。
 * 也就是最基本的，访问各个元素而不暴露容器内部细节。
 * 
 * 步骤：
 * 1 找到指定的csv文件
 * 2 对文件进行基本判断
 * 3 文件存在，则读取文件内容到reader中，写入object[]
 * 4 将object[]中的数据，按照method的参数要求，类型转换为所需要的
 * 4 通过iterator的方式，返回
 * 
 * 学习见demo： DriverDataProvider
 * 
 * 源码见：invokeDataProvider
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
	public static Iterator<Object[]> csvDataProvider(Method mtd) {
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
