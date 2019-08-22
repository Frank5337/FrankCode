package com.zbl.code.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @Author: zb
 */
public class PinyinUtil {  
  
    //字母Z使用了两个标签，这里有２７个值  
    //i, u, v都不做声母, 跟随前面的字母  
    private char[] chartable =  
            {  
                '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',  
                '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',  
                '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'  
            };  
  
    private char[] alphatable =  
            {  
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',  
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',   
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'  
            };  
  
  
    private int[] table = new int[27];  
    //初始化  
    {  
        for (int i = 0; i < 27; ++i) {  
            table[i] = gbValue(chartable[i]);  
        }  
    }  
  
    public PinyinUtil() {  
    }  
  
    //主函数,输入字符,得到他的声母,  
    //英文字母返回对应的大写字母  
    //其他非简体汉字返回 '0'  
  
    public char Char2Alpha(char ch) {  
  
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }
        int gb = gbValue(ch);  
        if (gb < table[0]) {
            return ch;
        }
        int i;  
        for (i = 0; i < 26; ++i) {  
            if (match(i, gb)) {
                break;
            }
        }  
        if (i >= 26) {
            return '0';
        }
        else {
            return alphatable[i];
        }
    }  
  
    //根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串  
    public String String2Alpha(String SourceStr){  
          
        StringBuilder Result = new StringBuilder();
        int strLength = SourceStr.length();
        int i;  
        try {  
            for (i = 0; i < strLength; i++) {
                Result.append(Char2Alpha(SourceStr.charAt(i)));
            }  
        } catch (Exception e) {  
            Result = new StringBuilder();
        }  
        return Result.toString();
    }  
  
    private boolean match(int i, int gb) {  
          
        if (gb < table[i]) {
            return false;
        }
        int j = i + 1;  
  
        //字母Z使用了两个标签  
        while (j < 26 && (table[j] == table[i])) {
            ++j;
        }
        if (j == 26) {
            return gb <= table[j];
        }
        else {
            return gb < table[j];
        }
    }  
  
    //取出汉字的编码  
    private int gbValue(char ch) {  
          
        String str = new String();  
        str += ch;  
        try {  
            byte[] bytes = str.getBytes("GB2312");  
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &  
                    0xff);  
        } catch (Exception e) {  
            return 0;  
        }  
    }  
    
    public static boolean hasFullChar(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        } 
        return true;    
    }
    
    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（重当参:cdc,zds,cds,zdc）
     * 
     * @param chines
     *            汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines) {
        StringBuffer pinyinName = new StringBuffer();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                //System.out.println(nameChar[i]);
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            // 取首字母
                            pinyinName.append(strs[j].charAt(0));
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
            pinyinName.append(" ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }
    
    /**
     * 去除多音字重复数据
     * 
     * @param theStr
     * @return
     */
    public static List<Map<String, Integer>> discountTheChinese(String theStr) {
        // 去除重复拼音后的拼音列表
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
        // 用于处理每个字的多音字，去掉重复
        Map<String, Integer> onlyOne = null;
        String[] firsts = theStr.split(" ");
        // 读出每个汉字的拼音
        for (String str : firsts) {
            onlyOne = new Hashtable<String, Integer>();
            String[] china = str.split(",");
            // 多音字处理
            for (String s : china) {
                Integer count = onlyOne.get(s);
                if (count == null) {
                    onlyOne.put(s, 1);
                } else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s, count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }
 
    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     * 
     * @return
     */
    public static String parseTheChineseByObject(
            List<Map<String, Integer>> list) {
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
        // 遍历每一组集合
        for (int i = 0; i < list.size(); i++) {
            // 每一组集合与上一次组合的Map
            Map<String, Integer> temp = new Hashtable<String, Integer>();
            // 第一次循环，first为空
            if (first != null) {
                // 取出上次组合与此次集合的字符，并保存
                for (String s : first.keySet()) {
                    for (String s1 : list.get(i).keySet()) {
                        String str = s + s1;
                        temp.put(str, 1);
                    }
                }
                // 清理上一次组合数据
                if (temp.size() > 0) {
                    first.clear();
                }
            } else {
                for (String s : list.get(i).keySet()) {
                    String str = s;
                    temp.put(str, 1);
                }
            }
            // 保存组合数据以便下次循环使用
            if (temp.size() > 0) {
                first = temp;
            }
        }
        StringBuilder returnStr = new StringBuilder();
        if (first != null) {
            // 遍历取出组合字符串
            for (String str : first.keySet()) {
                returnStr.append(str).append(",");
            }
        }
        if (returnStr.length() > 0) {
            returnStr = new StringBuilder(returnStr.substring(0, returnStr.length() - 1));
        }
        return returnStr.toString();
    }
 
    public static void main(String[] args) {
        System.out.println(converterToFirstSpell("枇杷").toUpperCase());
        System.out.println(converterToFirstSpell("保林").toUpperCase());
        System.out.println(converterToFirstSpell("万全").toUpperCase());
    }
} 