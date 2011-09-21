package com.lyh.licenseworkflow.system;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20下午1:43
 * @Email liuyuhui007@gmail.com
 */
public class Version {
    public final static String VERSION_MAJOR = "version.major";

    public final static String VERSION_MINOR = "version.minor";

    public final static String VERSION_REVISION = "version.revision";

    public final static String VERSION_NAME = "version.name";

    public final static String VERSION_SVNTAG = "version.svntag";

    public final static String VERSION_SVNTAG_PREFIX = "version.svntag.prefix";

    public final static String BUILD_NUMBER = "build.number";

    public final static String BUILD_ID = "build.id";

    public final static String BUILD_DATE = "build.day";

    public final static String BUILD_JVM_VERSION = "java.vm.version";

    public final static String BUILD_JVM_VENDOR = "java.vendor";

    public final static String BUILD_OS = "os.name";

    public final static String BUILD_OS_ARCH = "os.arch";

    public final static String BUILD_OS_VERSION = "os.version";

    public final static String SYSTEM_TITLE = "system.title";

    /**
     * The single instance.
     */
    private static Version instance = null;

    /**
     * The version properties.
     */
    private Properties props;

    /**
     * Do not allow direct public construction.
     */
    private Version() {
        props = loadProperties();
    }

    /**
     * Get the single <tt>Version</tt> instance.
     *
     * @return The single <tt>Version</tt> instance.
     */
    public static Version getInstance() {
        if (instance == null) {
            instance = new Version();
        }
        return instance;
    }

    /**
     * Returns a property value as an int.
     *
     * @param name - The name of the property.
     * @return The property value, or -1 if there was a problem converting it to an int.
     */
    private int getIntProperty(final String name) {
        try {
            return Integer.valueOf(props.getProperty(name)).intValue();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Returns an unmodifiable map of version properties.
     *
     * @return An unmodifiable map of version properties.
     */
    public Map getProperties() {
        return Collections.unmodifiableMap(props);
    }

    /**
     * Returns the major number of the version.
     *
     * @return The major number of the version.
     */
    public int getMajor() {
        return getIntProperty(VERSION_MAJOR);
    }

    /**
     * Returns the minor number of the version.
     *
     * @return The minor number of the version.
     */
    public int getMinor() {
        return getIntProperty(VERSION_MINOR);
    }

    /**
     * Returns the revision number of the version.
     *
     * @return The revision number of the version.
     */
    public int getRevision() {
        return getIntProperty(VERSION_REVISION);
    }

    /**
     * Returns the value for the given property name.
     *
     * @param name - The name of the property.
     * @return The property value or null if the property is not set.
     */
    public String getProperty(final String name) {
        return props.getProperty(name);
    }

    /**
     * @param key - The key of the property.
     * @return The property value or null if the property is not set.
     */
    public void setProperty(String key, String newValue) {
        props.setProperty(key, newValue);
    }

    /**
     * Returns the SVN tag of the version.
     *
     * @return The SVN tag of the version.
     */
    public String getSvnTag() {
        String svnTag = props.getProperty(VERSION_SVNTAG_PREFIX);
        String value = "";
        try {
            value = new String(svnTag.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value + props.getProperty(VERSION_SVNTAG);
    }

    /**
     * Returns the name number of the version.
     *
     * @return The name of the version.
     */
    public String getName() {
        return props.getProperty(VERSION_NAME);
    }

    /**
     * Returns the build identifier for this version.
     *
     * @return The build identifier for this version.
     */
    public String getBuildID() {
        return props.getProperty(BUILD_ID);
    }

    /**
     * Returns the build number for this version.
     *
     * @return The build number for this version.
     */
    public String getBuildNumber() {
        return props.getProperty(BUILD_NUMBER);
    }

    /**
     * Returns the build date for this version.
     *
     * @return The build date for this version.
     */
    public String getBuildDate() {
        return props.getProperty(BUILD_DATE);
    }

    /**
     * Returns the BUILD_JVM_VERSION (BUILD_JVM_VENDOR) which should look like: 1.4.2_05-b04 (Sun Microsystems Inc.)
     *
     * @return
     */
    public String getBuildJVM() {
        String vm = props.getProperty(BUILD_JVM_VERSION);
        String vendor = props.getProperty(BUILD_JVM_VENDOR);
        return vm + '(' + vendor + ')';
    }

    /**
     * Returns the BUILD_OS (BUILD_OS_ARCH,BUILD_OS_VERSION) which should look like: Windows XP (x86,5.1) Linux
     * (i386,2.4.21-4.ELsmp)
     *
     * @return
     */
    public String getBuildOS() {
        String os = props.getProperty(BUILD_OS);
        String arch = props.getProperty(BUILD_OS_ARCH);
        String version = props.getProperty(BUILD_OS_VERSION);
        return os + '(' + arch + ',' + version + ')';
    }

    /**
     * Returns the full version number, e.g. 5.0.0.GA
     *
     * @return The full version number as string
     */
    public String getVersionNumber() {
        StringBuffer buff = new StringBuffer();
        buff.append(getMajor()).append(".");
        buff.append(getMinor()).append(".");
        buff.append(getRevision());
        return buff.toString();
    }

    /**
     * 获得系统前缀，默认为APEX-OSSWorks
     *
     * @return
     */
    public String getSystemTitle() {
        String systemTitle = props.getProperty(SYSTEM_TITLE);
        String value = "";
        try {
            value = new String(systemTitle.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Load the version properties from a resource.
     */
    public Properties loadProperties() {
        props = new Properties();
        try {
            //由于高级系统设置中可能会多次修改properties文件，故这里的InputStream从物理路径读出，确保每次读出的数据是最新的
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            InputStream in = new FileInputStream(new File(path + "/version.properties"));
            props.load(in);
            in.close();
        } catch (IOException e) {
            throw new Error("Missing version.properties");
        }
        return props;
    }

    /**
     * 修改配置文件信息,主要用做高级系统设置模块
     *
     * @param map 属性信息
     * @return 状态值，1表示操作成功
     */
    public int modifyProperty(Map<String, String> map) {
        try {
            // 获得properties对象
            Properties props = this.loadProperties();
            for (String key : map.keySet()) {
                String value = map.get(key);
                value = new String(value.getBytes("UTF-8"), "ISO8859-1");
                props.setProperty(key, value);
            }
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            System.out.println(path);
            FileOutputStream fout = new FileOutputStream(path + "/version.properties");
            props.store(fout, "");// 保存文件
            fout.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Returns the version information as a string.
     *
     * @return Basic information as a string.
     */
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("产品版本：");
        buff.append(getVersionNumber());
        buff.append(" (Build: SVNTag=");
        buff.append(getSvnTag());
        buff.append(")\n");
        buff.append("发布日期：");
        buff.append(getBuildDate());
        buff.append("\n");
        buff.append("Java：");
        buff.append(System.getProperty("java.runtime.version") + ";" + System.getProperty("java.vm.name") + ";"
                + System.getProperty("java.vm.vendor"));
        buff.append("\n");
        buff.append("系统：");
        buff.append(System.getProperty("os.arch") + ";");
        buff.append(System.getProperty("os.name") + " " + System.getProperty("os.version"));
        buff.append("\n");
        buff.append("编码方式：");
        buff.append(System.getProperty("file.encoding"));
        return buff.toString();
    }
}
