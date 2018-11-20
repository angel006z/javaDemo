package com.meida.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * 字符串相关的工具类
 *
 */
public class StringUtils {

	private static final String HEX_DIGITS = "0123456789ABCDEF";
	private static final Logger LOG = Logger.getLogger(StringUtils.class);
	private static final String COMMA = ",";
	private static final String EMPTY = "";
	private static final String BLANK_SPACE = " ";

	/**
	 * 通配符 %
	 */
	public static final String WILDCARD_STRING = "%";

	/**
	 * 通配符: 加到右边
	 */
	public static final int WILDCARD_RIGHT = 1;

	/**
	 * 通配符: 加到两边
	 */
	public static final int WILDCARD_BOTH = 0;

	/**
	 * 加密方式: SHA
	 */
	public static final String SHA = "SHA";

	/**
	 * 加密方式: MD5
	 */
	public static final String MD5 = "MD5";

	/**
	 * 加密方式: 普通文本
	 */
	public static final String PLAIN = "Plain";

	/**
	 * 表示true的字符串
	 */
	public static final String[] TRUE_STRING = { "true", "yes", "on", "是", "はい" };

	/**
	 * 判断str是否在strArray中
	 *
	 * @param str      字符串
	 * @param strArray 字符串数组
	 * @return true存在；false不存在
	 */
	public static boolean isStringInArray(String str, String[] strArray) {
		for (String a : strArray) {
			if (a.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符是否在字符数组中
	 *
	 * @param c     字符
	 * @param array 数组
	 * @return true:在,false:不在
	 */
	public static boolean isCharInArray(char c, char[] array) {
		for (char ca : array) {
			if (ca == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把传入的str根据pos加上通配符 %,如果str为null或者空字符串不做任何操作
	 *
	 * @param str 要加通配符的字符串
	 * @param pos 通配符的位置 WILDCARD_RIGHT、WILDCARD_BOTH
	 * @return 处理过的字符串
	 */
	public static String wildcard(String str, int pos) {
		String result = str;
		if (str != null && !str.trim().equals("")) {
			if (pos == WILDCARD_RIGHT) {
				result = str.trim() + WILDCARD_STRING;
			} else if (pos == WILDCARD_BOTH) {
				result = WILDCARD_STRING + str.trim() + WILDCARD_STRING;
			}
		}
		return result;
	}

	/**
	 * 把传入的str两边加上通配符 %,如果str为null或者空字符串不做任何操作
	 *
	 * @param str 要加通配符的字符串
	 * @return 处理过的字符串
	 */
	public static String wildcardBoth(String str) {
		return wildcard(str, WILDCARD_BOTH);
	}

	/**
	 * 把传入的str右边加上通配符 %,如果str为null或者空字符串不做任何操作
	 *
	 * @param str 要加通配符的字符串
	 * @return 处理过的字符串
	 */
	public static String wildcardRight(String str) {
		return wildcard(str, WILDCARD_RIGHT);
	}

	/**
	 * 把Map中的key对应的字符串加上通配符
	 *
	 * @param map 要为其中的字符串加上通配符的Map
	 * @param key 要进行本操作的字符串的key
	 * @param pos 通配符的位置 WILDCARD_RIGHT、WILDCARD_BOTH
	 */
	public static void wildcard(Map<String, Object> map, String key, int pos) {
		if (map != null) {
			Object obj = map.get(key);
			if ((obj != null) && (!isEmpty(obj.toString()))) {
				String result = obj.toString();
				if (pos == WILDCARD_RIGHT) {
					map.put(key, StringUtils.wildcard(result, WILDCARD_RIGHT));
				} else if (pos == WILDCARD_BOTH) {
					map.put(key, StringUtils.wildcard(result, WILDCARD_BOTH));
				}
			}
		}
	}

	/**
	 * 加密字符串,可以通过decodeString解密
	 *
	 * @param src       字符串
	 * @param algorithm 算法,支持Hex,Base64,Binary
	 * @return 加密后的字符串
	 */
	public static String encodeString(String src, String algorithm) {
		String result = src;
		BinaryEncoder encoder = null;
		if (algorithm.equalsIgnoreCase("Hex")) {
			encoder = new Hex();
		} else if (algorithm.equalsIgnoreCase("Base64")) {
			encoder = new Base64();
		} else if (algorithm.equalsIgnoreCase("Binary")) {
			encoder = new BinaryCodec();
		}
		if (encoder != null) {
			try {
				result = new String(encoder.encode(src.getBytes()));
			} catch (EncoderException e) {
				LOG.warn(e.getMessage(), e);
			}
		}
		return result;
	}

	/**
	 * 解密字符串,字符串必须是通过encodeString加密的
	 *
	 * @param src       字符串
	 * @param algorithm 算法,支持Hex,Base64,Binary
	 * @return 加密后的字符串
	 */
	public static String decodeString(String src, String algorithm) {
		String result = src;
		BinaryDecoder decoder = null;
		if (algorithm.equalsIgnoreCase("Hex")) {
			decoder = new Hex();
		} else if (algorithm.equalsIgnoreCase("Base64")) {
			decoder = new Base64();
		} else if (algorithm.equalsIgnoreCase("Binary")) {
			decoder = new BinaryCodec();
		}
		if (decoder != null) {
			try {
				result = new String(decoder.decode(src.getBytes()));
			} catch (DecoderException e) {
				LOG.warn(e.getMessage(), e);
			}
		}
		return result;
	}

	/**
	 * 判断字符串是否为空, null,或trim size==0
	 *
	 * @param src 要判断的字符串
	 * @return true: 空, false:非空
	 */
	public static boolean isEmpty(String src) {
		return src == null || src.trim().length() == 0;
	}

	/**
	 * 截取字符串
	 *
	 * @param src    要截取的字符串
	 * @param length 长度
	 * @param end    结尾
	 * @return 截取后的字符串
	 */
	public static String truncate(String src, int length, String end) {
		if (src == null) {
			return null;
		}

		if (src.length() > length) {
			return src.substring(0, length) + end;
		} else {
			return src;
		}
	}

	/**
	 * 去掉字符串中的所有空格
	 *
	 * @param src 字符串
	 * @return 没有空格的字符串
	 */
	public static String removeAllBlank(String src) {
		if (src == null) {
			return null;
		} else {
			return src.replaceAll("\\s+", "");
		}
	}

	/**
	 * 对HTML进行编码
	 * @param s
	 * @return
	 */
	public static String htmlEncode(String s) {
		return htmlEncode(s, true);
	}

	/**
	 * Escape html entity characters and high characters (eg "curvy" Word
	 * quotes). Note this method can also be used to encode XML.
	 *
	 * @param s                  the String to escape.
	 * @param encodeSpecialChars if true high characters will be encode other
	 *                           wise not.
	 * @return the escaped string
	 */
	public static String htmlEncode(String s, boolean encodeSpecialChars) {
		s = s == null ? EMPTY : s;

		StringBuffer buffer = new StringBuffer();

		char[] chars = s.toCharArray();
		for (char c : chars) {
			// encode standard ASCII characters into HTML entities where needed
			if (c < '\200') {
				switch (c) {
				case '"':
					buffer.append("&quot;");
					break;
				case '&':
					buffer.append("&amp;");
					break;
				case '<':
					buffer.append("&lt;");
					break;
				case '>':
					buffer.append("&gt;");
					break;
				default:
					buffer.append(c);
				}
			}
			// encode 'ugly' characters (ie Word "curvy" quotes etc)
			else if (encodeSpecialChars && (c < '\377')) {
				String hexChars = "0123456789ABCDEF";
				int a = c % 16;
				int b = (c - a) / 16;
				String hex = "" + hexChars.charAt(b) + hexChars.charAt(a);
				buffer.append("&#x").append(hex).append(";");
			}
			// add other characters back in - to handle charactersets
			// other than ascii
			else {
				buffer.append(c);
			}
		}

		return buffer.toString();
	}

	/**
	 * Join an Iteration of Strings together. <p/>
	 * <h5>Example</h5>
	 * <p/>
	 * <p/>
	 * <pre>
	 * // get Iterator of Strings (&quot;abc&quot;,&quot;def&quot;,&quot;123&quot;);
	 * Iterator i = getIterator();
	 * out.print(TextUtils.join(&quot;, &quot;, i));
	 * // prints: &quot;abc, def, 123&quot;
	 * </pre>
	 *
	 * @param glue   Token to place between Strings.
	 * @param pieces Iteration of Strings to join.
	 * @return String presentation of joined Strings.
	 */
	public static String join(String glue, Iterator<String> pieces) {
		StringBuffer s = new StringBuffer();

		while (pieces.hasNext()) {
			s.append(pieces.next());

			if (pieces.hasNext()) {
				s.append(glue);
			}
		}

		return s.toString();
	}

	/**
	 * Join an array of Strings together.
	 *
	 * @param glue   Token to place between Strings.
	 * @param pieces Array of Strings to join.
	 * @return String presentation of joined Strings.
	 * @see #join(String,java.util.Iterator)
	 */
	public static String join(String glue, String[] pieces) {
		return join(glue, Arrays.asList(pieces).iterator());
	}

	/**
	 * 把Collection中的内容用glue链接起来
	 *
	 * @param glue   连接符.
	 * @param pieces 要链接的字符串集合.
	 * @return String 链接完的字符串.
	 * @see #join(String,java.util.Iterator)
	 */
	public static String join(String glue, Collection<String> pieces) {
		return join(glue, pieces.iterator());
	}

	/**
	 * 解析字符串中的UTF-8字符, \\uxxxx;
     *
     * @param src 源字符串
     * @return char
     */
	public static char decodeUTF8(String src) {
		if (src == null) {
			throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
		}

		if (!(src.startsWith("\\u") && src.length() <= 6)) {
			throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
		}

		char[] sources = src.substring(2).toCharArray();
		char res = 0;
		for (char nextChar : sources) {
			int digit = HEX_DIGITS.indexOf(Character.toUpperCase(nextChar));
			res = (char) (res * 16 + digit);
		}
		return res;
	}

	/**
	 * 解析字符串中的UTF-8字符
	 * @param src
	 * @return 字符串
	 */
	public static String decodeUTF8String(String src) {
		StringBuilder sb = new StringBuilder();
		char[] sources = src.toCharArray();
		for (int i = 0; i < sources.length; i++) {
			if (sources[i] == '\\' && i < sources.length - 5 && sources[i + 1] == 'u') {
				String utf8 = "" + sources[i++] + sources[i++] + sources[i++] + sources[i++] + sources[i++] + sources[i];
				sb.append(decodeUTF8(utf8));
				i = i + 5;
			} else {
				sb.append(sources[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 把十六进制的字符串转换为十进制字符串
	 *
	 * @param hex 六进制的字符串
	 * @return 十进制字符串
	 */
	public static String hexToDec(String hex) {
		char[] sources = hex.toCharArray();
		int dec = 0;
		for (int i = 0; i < sources.length; i++) {
			int digit = HEX_DIGITS.indexOf(Character.toUpperCase(sources[i]));
			dec += digit * Math.pow(16, (sources.length - (i + 1)));
		}
		return String.valueOf(dec);
	}

	/**
	 * 在一个字符串的某个位置插入字符串
	 * @param str 原字符串
	 * @param text 要插入的字符串
	 * @param start 什么位置开始插入
	 * @return 新的字符串
	 */
	public static String insertAt(String str, String text, int start) {
		if (str == null || str.length() < start) {
			return str;
		} else {
			return str.substring(0, start) + text + (str.length() == start ? "" : str.substring(start));
		}
	}

	/**
	 * 替换字符串  
	 * @param str 原字符串
	 * @param text 要替换的字符串
	 * @param start 替换开始位置
	 * @param end 替换结束位置
	 * @return
	 */
	public static String replaceAt(String str, String text, int start, int end) {
		if (str == null || str.length() < end) {
			return str;
		} else {
			return str.substring(0, start) + text + (str.length() == end ? "" : str.substring(end));
		}
	}

	/**
	 * 生成随机字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((char) (48 + rand.nextInt(122 - 48)));
		}
		return sb.toString();
	}

	/**
	 * 将一个为null的字符串转换成空字符串
	 * 如果一个字符串为空，则返回空字符串，否则返回原字符串
	 * @param str
	 * @return
	 */
	public static String safe(String str) {
		return safe(str, "");
	}

	/**
	 * 将一个为null的字符串转换成指定的字符串
	 * 如果一个字符串为空，则返回指定的字符串，否则返回原字符串
	 * @param str
	 * @return
	 */
	public static String safe(String str, String defaultValue) {
		return str == null ? defaultValue : str;
	}

	/**
	 * 计算字符串中的某个字符的数量
	 *
	 * @param text 要计算的字符串
	 * @param c    字符
	 * @return 数量,如果text为null,返回0
	 */
	public static int count(String text, char c) {
		if (text == null) {
			return 0;
		}
		int result = 0;
		for (char it : text.toCharArray()) {
			if (it == c) {
				++result;
			}
		}
		return result;
	}

	/**
	 * 计算在字符串text中的第n个字符c的位置
	 *
	 * @param text 要计算的字符串
	 * @param c    字符
	 * @param n    第几个
	 * @return 如果没有找到c,返回-1,如果c的数量不够n返回-1
	 */
	public static int charAt(String text, char c, int n) {
		int pos = -1;
		char[] charArray = text.toCharArray();
		int count = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == c) {
				++count;
				if (count == n) {
					pos = i;
					break;
				}
			}
		}

		return pos;
	}

	/**
	 * 用字符c把字符串src分割成字符串数组,如果字符c前面有逃逸符esc,不进行分割
	 *
	 * @param src 字符串
	 * @param c   分隔符
	 * @param esc 逃逸符
	 * @return 分割后的字符串数组
	 */
	public static String[] splitWith(String src, char c, char esc) {
		if (src == null || src.trim().equals(EMPTY)) {
			return new String[0];
		}

		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		char[] chars = src.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == c) {
				if (i > 0 && chars[i - 1] != esc) {
					result.add(sb.toString());
					sb = new StringBuilder();
				} else {
					sb.deleteCharAt(sb.length() - 1);
					sb.append(chars[i]);
				}
			} else if (i != 0 || chars[i] != c) {
				sb.append(chars[i]);
			}
		}
		if (!sb.toString().equals("")) {
			result.add(sb.toString());
		}

		return result.toArray(new String[result.size()]);
	}

	/**
	 * 用字符c把字符串src分割成字符串数组,只分割第一个后面的不进行分割,如果字符c前面有逃逸符esc,不进行分割
	 *
	 * @param src 字符串
	 * @param c   分隔符
	 * @param esc 逃逸符
	 * @return 分割后的字符串数组
	 */
	public static String[] splitFirstWith(String src, char c, char esc) {
		if (src == null || src.trim().equals("")) {
			return new String[0];
		}

		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		char[] chars = src.toCharArray();
		boolean hit = false;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == c && !hit) {
				if (i > 0 && chars[i - 1] != esc) {
					result.add(sb.toString());
					sb = new StringBuilder();
					hit = true;
				} else {
					sb.deleteCharAt(sb.length() - 1);
					sb.append(chars[i]);
				}
			} else if (i != 0 || chars[i] != c) {
				sb.append(chars[i]);
			}
		}
		if (!sb.toString().equals("")) {
			result.add(sb.toString());
		}

		return result.toArray(new String[result.size()]);
	}

	/**
	 * 根据空格来分割字符串
	 *
	 * @param src 要分割的字符串
	 * @return 分割结果
	 */
	public static List<String> splitBySpace(String src) {
		src = src.replaceAll("\t", BLANK_SPACE);
		return asList(src, BLANK_SPACE);
	}

	/**
	 * 根据separater把字符串分割成字符串数组
	 *
	 * @param src       要分割的字符串
	 * @param separater 分隔符
	 * @return 分割结果
	 */
	public static List<String> asList(String src, String separater) {
		List<String> result = new ArrayList<String>();
		if (src == null || src.trim().equals(EMPTY)) {
			return result;
		} else {
			String values[] = src.split(separater);
			for (String value : values) {
				if (!value.trim().equals(EMPTY)) {
					result.add(value.trim());
				}
			}
			return result;
		}
	}

	public static String htmlSafe(String strText) {
		// returns safe code for preloading in the RTE
		String tmpString = strText;

		tmpString = tmpString.replace("\n", EMPTY);

		// convert all types of single quotes
		tmpString = tmpString.replace((char) 145, (char) 39);
		tmpString = tmpString.replace((char) 146, (char) 39);
		tmpString = tmpString.replace("'", "&#39;");

		// convert all types of double quotes
		tmpString = tmpString.replace((char) 147, (char) 34);
		tmpString = tmpString.replace((char) 148, (char) 34);

		// replace carriage returns & line feeds
		tmpString = tmpString.replace((char) 10, (char) 32);
		tmpString = tmpString.replace((char) 13, (char) 32);

		return tmpString;

	}

	/**
	 * 把一个字符串按逗号分割, 并且去除两端空白
	 *
	 * @param str 字符串
	 * @return 分割后的列表
	 */
	public static List<String> splitToList(String str) {
		StringTokenizer st = new StringTokenizer(str, COMMA);
		List<String> result = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String t = st.nextToken();
			if (!isEmpty(t)) {
				result.add(t.trim());
			}
		}
		return result;
	}

	/**
	 * trim全角和半角空格
	 *
	 * @param str 要trim的字符串
	 * @return 两边去掉所有的全角和半角空格
	 */
	public static String fullSpaceTrim(String str) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		int start = 0;
		while ((start < length) && (str.charAt(start) == ' ' || str.charAt(start) == '\u3000')) {
			++start;
		}
		int end = length - 1;
		while ((end >= 0) && (str.charAt(end) == ' ' || str.charAt(end) == '\u3000')) {
			--end;
		}
		return start > end ? "" : str.substring(start, end + 1);
	}

	/**
	 * 密码加密,单向加密<br>
	 * 现在的加密算法支持SHA,MD5,PlainText,
	 *
	 * @param password  要加密的密码
	 * @param algorithm 加密算法名,可以为sha md5 plain,如果不是这些密码原样返回
	 * @return 加密后的字符串
	 */
	public static String encodePassword(String password, String algorithm) {
		return encodePassword(password, algorithm, null);
	}

	/**
	 * 密码加密,单向加密<br>
	 * 现在的加密算法支持SHA,MD5,PlainText,
	 *
	 * @param password  要加密的密码
	 * @param algorithm 加密算法名,可以为sha md5 plain,如果不是这些密码原样返回
	 * @param salt      混淆
	 * @return 加密后的字符串
	 */
	public static String encodePassword(String password, String algorithm, Object salt) {
		// null不进行加密
		if (password == null) {
			return null;
		}
		String saltedPwd;
		if ((salt == null) || "".equals(salt)) {
			saltedPwd = password;
		} else {
			saltedPwd = password + "{" + salt.toString() + "}";
		}

		byte[] encoded;
		if (algorithm.equalsIgnoreCase(SHA)) {
			encoded = Base64.encodeBase64(DigestUtils.sha(saltedPwd));
		} else if (algorithm.equalsIgnoreCase(MD5)) {
			encoded = Base64.encodeBase64(DigestUtils.md5(saltedPwd));
		} else if (algorithm.equalsIgnoreCase(PLAIN)) {
			encoded = Base64.encodeBase64(saltedPwd.getBytes());
		} else {
			return password;
		}

		return new String(encoded);
	}

	/**
	 * 这个字符串是否表示true:
	 * true, yes, on , 是, はい
	 *
	 * @param str 要验证的字符串
	 * @return 是否表示true
	 * @see StringUtils#TRUE_STRING
	 */
	public static boolean isTrue(String str) {
		if (str == null) {
			return false;
		} else {
			for (String tstr : TRUE_STRING) {
				if (tstr.equalsIgnoreCase(str)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 把一个字符串按逗号分割, 并且去除两端空白
	 * @param str String
	 * @return Set
	 */
	public static Set<String> splitToSet(String str) {
		return new HashSet<String>(splitToList(str));
	}

	/**
	 * 将ID转换成逗号分割的字符串
	 * @param ids
	 * @return
	 */
	public static String idsToString(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		StringBuilder idString = new StringBuilder();
		for (int i = 0; i < ids.size(); i++) {
			Long id = ids.get(i);
			if (id == null) {
				continue;
			}
			idString.append(String.valueOf(id));
			if (i != ids.size() - 1) {
				idString.append(COMMA);
			}
		}
		return idString.toString();
	}

	/**
	 * 把字符串按字符顺序排列，再用逗号串联返回
	 * @param strs
	 * @return
	 */
	public static String sortAsStringWithComma(String[] strs) {
		Arrays.sort(strs);
		String joinedStr = join(COMMA, strs);
		return joinedStr;
	}

	/**
	 * 把字符串先转化为数组，按字符顺序排列，再用逗号串联返回
	 * @param strs  如：aa,cc
	 * @return
	 */
	public static String sortAsStringWithComma(String str) {
		String[] strs = str.split(COMMA);
		Arrays.sort(strs);
		String joinedStr = join(COMMA, strs);
		return joinedStr;
	}

	/**
	 * 把字符串先转化为数组，按字符升序排列，再用逗号串联返回
	 * @param strs 100003,100004,100001 只能为数字
	 * @return
	 */
	public static String sortAsStringWithCommaASC(String str) {
		String[] strs = str.split(COMMA);
		List<String> strList = Arrays.asList(strs);
		Comparator comp = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Long p1 = Long.valueOf(String.valueOf(o1));
				Long p2 = Long.valueOf(String.valueOf(o2));
				if (p1.longValue() > p2.longValue())
					return 1;
				else
					return 0;
			}
		};
		Collections.sort(strList, comp);
		String joinedStr = join(COMMA, strList);
		return joinedStr;
	}

	/**
	 * 将List转换为string 以逗号进行分隔
	 * @param idList
	 * @return
	 */
	public static String arrayToString(List<Long> idList) {
		String ids = "";
		for (int i = 0; i < idList.size(); i++) {
			ids += idList.get(i);
			if (i < idList.size() - 1) {
				ids += ",";
			}
		}
		return ids;
	}

	/**
	 * 检测是否存在sql注入的常用字符
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) {
		String inj_str = "':and:exec:insert:select:delete:update:count:*:%:chr:mid:master:truncate:char:declare:;:or:-:+:,";
		String inj_stra[] = inj_str.split(":");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) != -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 防止sql注入 去掉特殊字符
	 * @param str	 原字符串
	 * @param str2	替换后的字符
	 * @return
	 */
	public static String replaceSQLInjection(String str, String str2) {
		str = str.replace(";", str2);
		str = str.replace("&", str2);
		str = str.replace("--", str2);
		str = str.replace("%", str2);
		str = str.replace("~", str2);
		str = str.replace(",", str2);
		str = str.replace("`", str2);
		str = str.replace("!", str2);
		str = str.replace("@", str2);
		str = str.replace("#", str2);
		str = str.replace("$", str2);
		str = str.replace("^", str2);
		str = str.replace("*", str2);
		str = str.replace("(", str2);
		str = str.replace(")", str2);
		str = str.replace("+", str2);
		str = str.replace("<", str2);
		str = str.replace(">", str2);
		str = str.replace("?", str2);
		str = str.replace("/", str2);
		str = str.replace("\\", str2);
		str = str.replace("\"", str2);
		str = str.replace("{", str2);
		str = str.replace("}", str2);
		str = str.replace("[", str2);
		str = str.replace("]", str2);
		str = str.replace("-", str2);
		str = str.replace("_", str2);
		str = str.replace("！", str2);
		str = str.replace("~", str2);
		str = str.replace("￥", str2);
		str = str.replace("……", str2);
		str = str.replace("（", str2);
		str = str.replace("）", str2);
		str = str.replace("——", str2);
		str = str.replace("、", str2);
		str = str.replace("select", str2);
		str = str.replace("update", str2);
		str = str.replace("insert", str2);
		str = str.replace("delete", str2);
		str = str.replace("drop", str2);
		str = str.replace("delcare", str2);
		str = str.replace("create", str2);
		str = str.replace("truncate", str2);
		str = str.replace("master", str2);
		str = str.replace("exec", str2);
		return str;
	}

	/**
	 * 去掉字符串最后的符号
	 * @param str  字符串
	 * @param strSign  符号：如：,
	 * @return
	 */
	public static String removeLastComma(String str, String strSign) {
		if (!isEmpty(str) && str.endsWith(strSign))
			return str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 对文本进行URL编码
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str) {
		String result = "";
		try {
			result = java.net.URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对文本进行URL解码
	 * @param str
	 * @return
	 */
	public static String urlDecode(String str) {
		String result = "";
		try {
			result = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将文件转化为byte数组字符串，并对其进行Base64编码处理
	 * @return string Base64编码过的字节数组字符串
	 */
	public static String encodeBase64File(String filePath) {
		InputStream in = null;
		byte[] data = null;
		String result = null;
		try {
			in = new FileInputStream(filePath);
			data = new byte[in.available()];
			in.read(data);
			result = new String(Base64.encodeBase64(data));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 返回Base64编码过的字节数组字符串
		return result;
	}

	/**
	 * 返回某个对象的字符串形式，主要是为了避免对象为null时，抛空指针异常
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 将输入的字符串中包含的特殊字符('&','<', '>', '"')转变为 HTML 代码输出。
	 * 
	 * @param value
	 *            目标字符串
	 * @return
	 */
	public static String escapeHTML(String value) {
		if (isEmpty(value)) {
			return value;
		}
		value = value.replaceAll("&", "&amp;");
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll("\t", "    ");
		value = value.replaceAll(" ", "&nbsp;");

		return value;
	}

	/**
	 * 人民币大小写转换
	 * 
	 * @param money
	 * @return
	 */
	public static String currencyToChinese(double money) {
		if (money == 0) {
			return "零";
		}
		String number[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[] = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万" };
		String unit2[] = { "角", "分" };

		// 解释整数部分
		String moneyStr1 = formatNumber("###", money);
		int length = moneyStr1.length();
		StringBuffer sb = new StringBuffer("");
		int lastNumber = -1;
		for (int i = length; i > 0; i--) {
			int bit = Integer.parseInt(moneyStr1.substring(length - i, length - i + 1));
			lastNumber = bit;
			if (bit == 0) {
				if (i != 1 && !sb.toString().endsWith(number[0])) {
					sb.append(number[0]);
				}
				if (i == 5 || i == 9) {
					if (sb.toString().endsWith(number[0])) {
						String sbStr = sb.toString();
						sb = new StringBuffer("");
						sbStr = sbStr.substring(0, sbStr.length() - 1);
						sb.append(sbStr + unit[i - 1] + number[0]);
					}
				}

			} else {
				sb.append(number[bit]);
				sb.append(unit[i - 1]);
			}
		}

		if (sb.toString().endsWith(number[0])) {
			String sbStr = sb.toString();
			sb = new StringBuffer("");
			sbStr = sbStr.substring(0, sbStr.length() - 1);
			sb.append(sbStr);
			sb.append("元");
		}

		// 解释小数部分
		String moneyStr2 = formatNumber("###.##", money);
		// 如果没有小数部分
		if (moneyStr2.indexOf(".") == -1) {
			return sb.toString() + "整";
		}

		if (lastNumber == 0) {
			sb.append("零");
		}
		moneyStr2 = moneyStr2.substring(moneyStr2.lastIndexOf(".") + 1, moneyStr2.length());
		int length2 = moneyStr2.length();
		for (int i = 0; i < length2; i++) {
			int bit = Integer.parseInt(moneyStr2.substring(i, i + 1));

			if (bit != 0) {
				sb.append(number[bit]);
				sb.append(unit2[i]);
			} else {
				if (lastNumber != 0) {
					sb.append(number[0]);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将指点的数字对象按照指点的规则格式化, 例如:###.00,
	 * 
	 * @param regExp
	 * @param obj
	 * @return
	 */
	public static String formatNumber(String regExp, double obj) {
		DecimalFormat format = new DecimalFormat(regExp);
		return format.format(obj);
	}

	/**
	 * 补齐数据位
	 * @author dqwu
	 * @date 2015-5-27 下午4:03:40
	 * @param regExp
	 * @param obj
	 * @return
	 */
	public static String subStringNumber(String regExp, Long obj) {
		String numberStr = String.valueOf(obj);
		if (StringUtils.isEmpty(regExp)) {
			return numberStr;
		}
		if (regExp.length() >= numberStr.length()) {
			numberStr = regExp.substring(0, regExp.length() - numberStr.length()) + numberStr;
		} else {
			numberStr = numberStr.substring(numberStr.length() - regExp.length());
		}
		return numberStr;
	}
	
	/**
	 * 向后补齐数据位
	 * @author dqwu
	 * @date 2015-5-27 下午4:03:40
	 * @param regExp
	 * @param obj
	 * @return
	 */
	public static String subStringNumbers(String regExp, String obj) {
		String numberStr = obj;
		if (StringUtils.isEmpty(regExp)) {
			return numberStr;
		}
		if (regExp.length() >= numberStr.length()) {
			numberStr = numberStr + regExp.substring(0, regExp.length() - numberStr.length());
		} else {
			numberStr = numberStr.substring(numberStr.length() - regExp.length());
		}
		return numberStr;
	}

	/**
	 * 获取指定位数的随机数
	 * 
	 * @param size
	 *            指定的位数
	 * @return
	 */
	public static long getRandom(int size) {
		Double value = (Math.random() * Math.pow(10, size));
		return value.longValue();
	}

	/**
	 * 将以逗号分隔开的字符串转换成数组
	 * @param str
	 * @return
	 */
	public static String[] toStringArray(String str) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(",");
	}

	/**
	 * 获取4位随机数 字母或数字
	 * @return
	 */
	public static String getRandom4Char() {
		String[] hourArray = new String[] { "A", "Q", "5", "i", "l", "d", "F", "8", "D", "S", "X", "M", "6", "2", "4", "p", "h", "7", "f", "B", "C", "1", "Y", "N" };
		String[] minArray = new String[] { "p", "P", "L", "7", "y", "d", "9", "v", "S", "6", "b", "4", "C", "0", "Y", "a", "n", "V", "j", "3", "1", "Z", "M", "u", "q", "H", "e", "h", "5", "8", "F", "J", "X", "w", "B", "o", "O", "x", "i", "2", "E", "r", "f", "K", "g", "m", "G", "Q", "N", "s", "I", "A", "U", "D", "l", "c", "z", "W", "T", "R" };
		String[] secArray = new String[] { "i", "9", "t", "0", "4", "q", "1", "O", "U", "G", "2", "y", "5", "f", "D", "d", "z", "Z", "8", "v", "I", "o", "6", "j", "H", "p", "g", "C", "7", "b", "u", "3", "F", "V", "N", "B", "T", "M", "W", "K", "Q", "k", "X", "r", "l", "c", "s", "w", "A", "a", "n", "J", "e", "L", "m", "S", "P", "E", "x", "h" };
		String[] randomArray = new String[] { "7", "8", "5", "l", "g", "6", "H", "d", "c", "9", "S", "u", "1", "e", "T", "0", "Y", "G", "B", "V", "K", "X", "4", "3", "p", "v", "Q", "m", "s", "f", "i", "R", "2", "D", "j", "E", "M", "I", "W", "F", "o", "z", "C", "N", "a", "b", "P", "r", "t", "U", "O", "L", "h", "w", "q", "n", "A", "y", "x", "J", "k", "Z" };

		Date date = new Date();
		String sDate = DateUtils.formatDate(date, "hh:mm:ss");
		String[] times = sDate.split(":");
		int hour = Integer.valueOf(times[0]);
		int min = Integer.valueOf(times[1]);
		int sec = Integer.valueOf(times[2]);
		Random random = new Random();
		int num = random.nextInt(62);
		return hourArray[hour - 1] + minArray[min - 1] + secArray[sec - 1] + randomArray[num - 1];
	}

	/**
	 * 获取4位数字随机数
	 * @return
	 */
	public static String getRandom4Number() {
		String[] randomArray1 = new String[] { "5", "3", "7", "0", "1", "8", "6", "9", "2", "4" };
		String[] randomArray2 = new String[] { "3", "7", "2", "0", "9", "4", "5", "8", "6", "1" };
		String[] randomArray3 = new String[] { "8", "0", "5", "4", "2", "9", "6", "7", "1", "3" };
		String[] randomArray4 = new String[] { "2", "5", "8", "1", "0", "7", "9", "4", "3", "6" };

		Random random = new Random();
		int num1 = random.nextInt(10);
		int num2 = random.nextInt(10);
		int num3 = random.nextInt(10);
		int num4 = random.nextInt(10);
		return randomArray1[num1] + randomArray2[num2] + randomArray3[num3] + randomArray4[num4];
	}

	/**
	 * 获取字母和数字混合随机数
	 * @param length 长度
	 * @return
	 */
	public static String getCharacterAndNumber(int length) {
		// String val = "";

		StringBuffer sbuf = new StringBuffer();
		List<String> charList = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				char val = (char) (choice + random.nextInt(26));
				if (charList.contains(String.valueOf(val))) {
					i--;
					continue;
				} else {
					charList.add(String.valueOf(val));
					sbuf.append(String.valueOf(val));
				}
				// val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				String val = String.valueOf(random.nextInt(10));
				if (charList.contains(val)) {
					i--;
					continue;
				} else {
					charList.add(val);
					sbuf.append(val);
				}
				// val += String.valueOf(random.nextInt(10));
			}
		}

		return sbuf.toString();
	}

	/**
	 * 获取通用的唯一识别码
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}
	
	/**
	 * 获取通用的唯一识别码
	 * @return
	 */
	public static String getUUIDOriginal() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	/**
	 * 获取要生成的文件名称
	 * @return
	 */
	public static String getFileName() {
		return DateUtils.formatDate(new Date(), "yyMMddHHmmss") + getRandom4Number();
	}

	/**
	 * 获取来访IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return "";
		}
		String proxs[] = { "X-Forwarded-For", "Proxy-Client-IP",
				"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" ,"x-real-ip" };
		String ip = "";
		for (String prox : proxs) {
			ip = request.getHeader(prox);
			request.getHeaderNames();
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				continue;
			} else {
				break;
			}
		}
		

		if (StringUtils.isEmpty(ip)) {
			ip = request.getRemoteAddr();
		} else {
			ip = ip.split(",").length > 1 ? ip.split(",")[0] : ip;
		}
		
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip="127.0.0.1";
		}
		return ip;
	}

	/**
	 * 将byte[]转换成16进制字符串
	 * @author xh
	 * @date 2016-5-11 上午9:22:41
	 * @param bytea
	 * @return
	 * @throws Exception
	 */
	public static String bytesToHex(byte[] bytea) throws Exception {
		String sHex = "";
		int iUnsigned = 0;
		StringBuffer sbHex = new StringBuffer();
		for (int i = 0; i < bytea.length; i++) {
			iUnsigned = bytea[i];
			if (iUnsigned < 0) {
				iUnsigned += 256;
			}
			if (iUnsigned < 16) {
				sbHex.append("0");
			}
			sbHex.append(Integer.toString(iUnsigned, 16));
		}
		sHex = sbHex.toString();
		return sHex;
	}

	/**
	 * 将16进制字符串转换成byte[]
	 * @author xh
	 * @date 2016-5-11 上午9:23:30
	 * @param sHex
	 * @return
	 * @throws Exception
	 */
	public static byte[] hexToBytes(String sHex) throws Exception {
		if (sHex.length() % 2 != 0) {
			sHex = "0" + sHex;
		}
		byte[] bytea = new byte[sHex.length() / 2];

		String sHexSingle = "";
		for (int i = 0; i < bytea.length; i++) {
			sHexSingle = sHex.substring(i * 2, i * 2 + 2);
			bytea[i] = (byte) Integer.parseInt(sHexSingle, 16);
		}
		return bytea;
	}
	
	
	/**
	 * 取票号
	  * @author wxg
	  * @date 2016-5-21 下午1:35:46 
	  * @return
	 */
	public static String getRandomNum() {
		String[] randomArray1 = new String[] { "5", "3", "7", "0", "1", "8", "6", "9", "2", "4" };
		String[] randomArray2 = new String[] { "3", "7", "2", "0", "9", "4", "5", "8", "6", "1" };
		String[] randomArray3 = new String[] { "8", "0", "5", "4", "2", "9", "6", "7", "1", "3" };
		String[] randomArray4 = new String[] { "2", "5", "8", "1", "0", "7", "9", "4", "3", "6" };

		Random random = new Random();
		int num1 = random.nextInt(10);
		int num2 = random.nextInt(10);
		int num3 = random.nextInt(10);
		int num4 = random.nextInt(10);
		int num5 = random.nextInt(10);
		int num6 = random.nextInt(10);
		return randomArray1[num1] + randomArray2[num2] + randomArray3[num3] + randomArray4[num4]+ randomArray3[num5] + randomArray4[num6];
	}
	
	/**
	 * 身份证校验工具（支持15位或者18位身份证）<br/>
	 * 身份证号码结构：
	* <ol>
	 * <li>17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序码，1位校验码。</li>
	 * <li>地址码（前6位）：表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。</li>
	 * <li>出生日期码（第七位至十四位）：表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。</li>
	 * <li>顺序码（第十五位至十七位） ：表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
	* 顺序码的奇数分配给男性，偶数分配给女性。</li>
	 * <li>校验码（第十八位数）：<br/>
	 * <ul>
	 * <li>十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, , 16 ，先对前17位数字的权求和；
	* Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2；</li>
	 * <li>计算模 Y = mod(S, 11)</li>
	 * <li>通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2</li>
	 * </ul>
	 * </li>
	 * </ol>
	 *
	 * 身份证号是否基本有效
	  * 
	 * @param s
	  *            号码内容
	  * @return 是否有效，null和""都是false
	  */
	public static boolean isIdcard(String s) {

		try {

			final Map<Integer, String> zoneNum = new HashMap<Integer, String>();
			zoneNum.put(11, "北京");
			zoneNum.put(12, "天津");
			zoneNum.put(13, "河北");
			zoneNum.put(14, "山西");
			zoneNum.put(15, "内蒙古");
			zoneNum.put(21, "辽宁");
			zoneNum.put(22, "吉林");
			zoneNum.put(23, "黑龙江");
			zoneNum.put(31, "上海");
			zoneNum.put(32, "江苏");
			zoneNum.put(33, "浙江");
			zoneNum.put(34, "安徽");
			zoneNum.put(35, "福建");
			zoneNum.put(36, "江西");
			zoneNum.put(37, "山东");
			zoneNum.put(41, "河南");
			zoneNum.put(42, "湖北");
			zoneNum.put(43, "湖南");
			zoneNum.put(44, "广东");
			zoneNum.put(45, "广西");
			zoneNum.put(46, "海南");
			zoneNum.put(50, "重庆");
			zoneNum.put(51, "四川");
			zoneNum.put(52, "贵州");
			zoneNum.put(53, "云南");
			zoneNum.put(54, "西藏");
			zoneNum.put(61, "陕西");
			zoneNum.put(62, "甘肃");
			zoneNum.put(63, "青海");
			zoneNum.put(64, "宁夏");
			zoneNum.put(65, "新疆");
			zoneNum.put(71, "台湾");
			zoneNum.put(81, "香港");
			zoneNum.put(82, "澳门");
			zoneNum.put(91, "国外");

			int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
			int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			if (s == null || (s.length() != 15 && s.length() != 18))
				return false;
			final char[] cs = s.toUpperCase().toCharArray();
			// （1）校验位数
			int power = 0;
			for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
				if (i == cs.length - 1 && cs[i] == 'X')
					break;// 最后一位可以是X或者x
				if (cs[i] < '0' || cs[i] > '9')
					return false;
				if (i < cs.length - 1)
					power += (cs[i] - '0') * POWER_LIST[i];
			}
			// （2）校验区位码
			if (!zoneNum.containsKey(Integer.valueOf(s.substring(0, 2)))) {
				return false;
			}
			// （3）校验年份
			String year = s.length() == 15 ? "19" + s.substring(6, 8) : s.substring(6, 10);
			final int iyear = Integer.parseInt(year);
			if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
				return false;// 1900年的PASS，超过今年的PASS
			}
			// （4）校验月份
			String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10, 12);
			final int imonth = Integer.parseInt(month);
			if (imonth < 1 || imonth > 12)
				return false;
			// （5）校验天数
			String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12, 14);
			final int iday = Integer.parseInt(day);
			if (iday < 1 || iday > 31)
				return false;
			// （6）校验一个合法的年月日
			if (!validate(iyear, imonth, iday))
				return false;
			// （7）校验“校验码”
			if (s.length() == 15)
				return true;
			return cs[cs.length - 1] == PARITYBIT[power % 11];
		} catch (Exception e) {
			return false;
		}
	}
	
	static boolean validate(int year, int month, int day) {
		// 比如考虑闰月，大小月等
		return true;
	}
}
