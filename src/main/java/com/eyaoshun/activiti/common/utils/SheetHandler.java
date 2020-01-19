package com.eyaoshun.activiti.common.utils;

import com.eyaoshun.activiti.model.ApplicationInfo;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.helpers.DefaultHandler;

import java.text.BreakIterator;

/**
 * @author zmm
 * @date 2020/1/19
 */
public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private ApplicationInfo applicationInfo = null;

    @Override
    public void startRow(int i) {
        if (i > 0) {
            applicationInfo = new ApplicationInfo();
        }


    }

    @Override
    public void endRow(int i) {
        System.out.println(applicationInfo);


        System.out.println("第  =================="+i +"行数据  ");
    }

    @Override
    public void cell(String s, String s1, XSSFComment xssfComment) {
        if (applicationInfo != null) {

            String cel = s.substring(0, 1);
            switch (cel) {


                case "B":
                    applicationInfo.setAppNo(s1);
                    break;

                case "E":
                    applicationInfo.setTotal(s1);
                    break;
                case "M":
                    applicationInfo.setApplicantname(s1);
                    break;
                default:
                    break;

            }

        }

    }


    @Override
    public void headerFooter(String celling, boolean value, String var3) {

    }
}
