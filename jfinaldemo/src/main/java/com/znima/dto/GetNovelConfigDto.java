/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.dto;

import com.znima.entity.GetNovelConfig;
import com.znima.myenum.GetAuthorWay;
import com.znima.myenum.GetContentWay;
import com.znima.myenum.GetContentWay2;
import com.znima.myenum.GetDescWay;
import com.znima.myenum.GetImgWay;
import com.znima.myenum.GetIndexWay;
import com.znima.myenum.GetNovelNameWay;
import com.znima.util.MyStringUtil;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class GetNovelConfigDto implements Serializable {

    private String webSiteName;
    private String indexUrl;
    private String rootUrl;

    private Integer getIndexWayValue;
    private GetIndexWay getIndexWay;
    private String[] key;

    private Integer getImgWayValue;
    private GetImgWay getImgWay;
    //封面图
    private String[] imgKeys;

    private Integer getNovelNameWayValue;
    private GetNovelNameWay getNovelNameWay;
    //小说名key
    private String[] novelNameKeys;

    private Integer getAuthorWayValue;
    private GetAuthorWay getAuthorWay;
    //作者key
    private String[] novelAuthorKeys;

    private Integer getDescWayValue;
    private GetDescWay getDescWay;
    //简介
    private String[] novelDescKeys;
    private Integer descKey2Index;

    private Integer getContentWayValue;
    private GetContentWay getContentWay;
    private GetContentWay2 getConentWay2;
    private String itemKey; //章节KEY
    
    private String nextPMark;   //章节分页显示时，是否有下一页的标志
    private String nextPSelect; // cssSelect 下一面a标签的获取值
    
    public GetNovelConfigDto() {}

    public GetNovelConfigDto(Map<String, String[]> params) throws Exception {
        Set<String> keySet = params.keySet();
        for (String prop : keySet) {
            this.inject(this, prop, params.get(prop));
        }
    }
    
    public GetNovelConfigDto(GetNovelConfig configEntity) {
        this.webSiteName = configEntity.get("webSiteName");
        this.rootUrl = configEntity.get("url");

        this.setGetIndexWayValue((Integer) configEntity.get("getIndexWayValue"));
        this.key = configEntity.get("key").toString().split(",");

        this.setGetImgWayValue((Integer) configEntity.get("getImgWayValue"));
        this.imgKeys = configEntity.get("imgKey").toString().split(",");

        this.setGetNovelNameWayValue((Integer) configEntity.get("getNovelNameWayValue"));
        this.novelNameKeys = configEntity.get("novelNameKeys").toString().split(",");

        this.setGetAuthorWayValue((Integer) configEntity.get("getAuthorWayValue"));
        this.novelAuthorKeys = configEntity.get("novelAuthorKeys").toString().split(",");

        this.setGetDescWayValue((Integer) configEntity.get("getDescWayValue"));
        //简介
        this.novelDescKeys = configEntity.get("novelDescKeys").toString().split(",");
        this.descKey2Index = configEntity.get("descKey2Index");

        this.setGetContentWayValue((Integer) configEntity.get("getContentWayValue"));
        this.itemKey = configEntity.get("itemKey");
        
        this.nextPMark = configEntity.get("nextPMark");
        this.nextPSelect = configEntity.get("nextPSelect");
    }

    private void inject(Object obj, String prop, String[] values) throws Exception {
        
        if (values == null || values.length == 0) {
            return;
        }
        
        Class<?> type = obj.getClass().getDeclaredField(prop).getType();
        if (type == null) {
            return;
        }
        
        Method set = obj.getClass().getMethod(toMethodName("set", prop), type);
        if (set == null) {
            return;
        }
        
        if (type == String.class) {
            set.invoke(obj, values[0]);
        } else if (type == String[].class) {
            set.invoke(obj, new Object[]{values});
        } else if (type == Integer.class) {
            set.invoke(obj, Integer.parseInt(values[0]));
        } else if (type == Integer[].class) {
            Integer[] iValues = new Integer[values.length];
            for (int i = 0; i < values.length; i++) {
                iValues[i] = Integer.parseInt(values[i]);
            }
            set.invoke(obj, iValues);
        } else if (type == Long.class) {
            set.invoke(obj, Long.parseLong(values[0]));
        } else if (type == Long[].class) {
            Long[] iValues = new Long[values.length];
            for (int i = 0; i < values.length; i++) {
                iValues[i] = Long.parseLong(values[i]);
            }
            set.invoke(obj, iValues);
        } else if (type == Date.class) {
            set.invoke(obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(values[0]));
        } else if (type == Date[].class) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date[] iValues = new Date[values.length];
            for (int i = 0; i < values.length; i++) {
                iValues[i] = sdf.parse(values[i]);
            }
            set.invoke(obj, iValues);
        } else if (type == Float.class) {
            set.invoke(obj, Float.parseFloat(values[0]));
        } else if (type == Float[].class) {
            Float[] iValues = new Float[values.length];
            for (int i = 0; i < values.length; i++) {
                iValues[i] = Float.parseFloat(values[i]);
            }
            set.invoke(obj, iValues);
        } else if (type == Double.class) {
            set.invoke(obj, Double.parseDouble(values[0]));
        } else if (type == Double[].class) {
            Double[] iValues = new Double[values.length];
            for (int i = 0; i < values.length; i++) {
                iValues[i] = Double.parseDouble(values[i]);
            }
            set.invoke(obj, iValues);
        } else {
            set = obj.getClass().getMethod(toMethodName("set", prop), String.class);
            set.invoke(obj, values[0]);
        }

    }

    private String toMethodName(String prefix, String name) {
        String methodName = prefix + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        return methodName;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String[] getImgKeys() {
        return imgKeys;
    }

    public void setImgKeys(String[] imgKey) {
        this.imgKeys = imgKey;
    }

    public Integer getGetIndexWayValue() {
        return getIndexWayValue;
    }

    public void setGetIndexWayValue(Integer getIndexWay) {
        this.getIndexWayValue = getIndexWay;
        this.getIndexWay = GetIndexWay.getIndexWayBayValue(getIndexWay);
    }

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }

    public GetIndexWay getWay() {
        return getIndexWay;
    }

    public void setWay(GetIndexWay way) {
        this.getIndexWay = way;
    }

    public String[] getNovelNameKeys() {
        return novelNameKeys;
    }

    public void setNovelNameKeys(String[] novelNameKeys) {
        this.novelNameKeys = novelNameKeys;
    }

    public Integer getGetNovelNameWayValue() {
        return getNovelNameWayValue;
    }

    public void setGetNovelNameWayValue(Integer getNovelNameWayValue) {
        this.getNovelNameWayValue = getNovelNameWayValue;
        this.getNovelNameWay = GetNovelNameWay.getIndexWayBayValue(getNovelNameWayValue);
    }

    public GetNovelNameWay getGetNovelNameWay() {
        return getNovelNameWay;
    }

    public void setGetNovelNameWay(GetNovelNameWay getNovelNameWay) {
        this.getNovelNameWay = getNovelNameWay;
    }

    public String[] getNovelAuthorKeys() {
        return novelAuthorKeys;
    }

    public void setNovelAuthorKeys(String[] novelAuthorKeys) {
        this.novelAuthorKeys = novelAuthorKeys;
    }

    public Integer getGetAuthorWayValue() {
        return getAuthorWayValue;
    }

    public void setGetAuthorWayValue(Integer getAuthorWayValue) {
        this.getAuthorWayValue = getAuthorWayValue;
        this.getAuthorWay = GetAuthorWay.getIndexWayBayValue(getAuthorWayValue);
    }

    public GetAuthorWay getGetAuthorWay() {
        return getAuthorWay;
    }

    public void setGetAuthorWay(GetAuthorWay getAuthorWay) {
        this.getAuthorWay = getAuthorWay;
    }

    public String[] getNovelDescKeys() {
        return novelDescKeys;
    }

    public void setNovelDescKeys(String[] novelDescKeys) {
        this.novelDescKeys = novelDescKeys;
    }

    public Integer getDescKey2Index() {
        return descKey2Index;
    }

    public void setDescKey2Index(Integer descKey2Index) {
        this.descKey2Index = descKey2Index;
    }

    public Integer getGetDescWayValue() {
        return getDescWayValue;
    }

    public void setGetDescWayValue(Integer getDescWayValue) {
        this.getDescWayValue = getDescWayValue;
        this.getDescWay = GetDescWay.getIndexWayBayValue(getDescWayValue);
    }

    public GetDescWay getGetDescWay() {
        return getDescWay;
    }

    public void setGetDescWay(GetDescWay getDescWay) {
        this.getDescWay = getDescWay;
    }

    public GetIndexWay getGetIndexWay() {
        return getIndexWay;
    }

    public void setGetIndexWay(GetIndexWay getIndexWay) {
        this.getIndexWay = getIndexWay;
    }

    public void setGetIndexWay(String getIndexWayValue) {
        GetIndexWay getIndexWay = GetIndexWay.getIndexWayBayValue(Integer.parseInt(getIndexWayValue));
        this.getIndexWay = getIndexWay;
    }

    public Integer getGetContentWayValue() {
        return getContentWayValue;
    }

    public void setGetContentWayValue(Integer getContentWayValue) {
        this.getContentWayValue = getContentWayValue;
        this.getContentWay = GetContentWay.getIndexWayBayValue(getContentWayValue);
        this.getConentWay2 = GetContentWay2.getIndexWayBayValue(getContentWayValue);
    }

    public GetContentWay getGetContentWay() {
        return getContentWay;
    }

    public void setGetContentWay(GetContentWay getContentWay) {
        this.getContentWay = getContentWay;
    }

    public GetContentWay2 getGetContentWay2() {
        return getConentWay2;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public Integer getGetImgWayValue() {
        return getImgWayValue;
    }

    public void setGetImgWayValue(Integer getImgWayValue) {
        this.getImgWayValue = getImgWayValue;
        this.getImgWay = GetImgWay.getIndexWayBayValue(getImgWayValue);
    }

    public GetImgWay getGetImgWay() {
        return getImgWay;
    }

    public void setGetImgWay(GetImgWay getImgWay) {
        this.getImgWay = getImgWay;
    }

    public String getNextPMark() {
        return nextPMark;
    }

    public void setNextPMark(String nextPMark) {
        this.nextPMark = nextPMark;
    }

    public String getNextPSelect() {
        return nextPSelect;
    }

    public void setNextPSelect(String nextPSelect) {
        this.nextPSelect = nextPSelect;
    }

    @Override
    public String toString() {
        return "GetNovelConfig{" + "indexUrl=" + indexUrl + ", getIndexWay=" + getIndexWayValue + ", key=" + key
                + ", imgKey=" + MyStringUtil.arryToString(imgKeys) + ", novelNameKeys=" + MyStringUtil.arryToString(novelNameKeys)
                + ", novelAuthorKeys=" + MyStringUtil.arryToString(novelAuthorKeys)
                + ", novelDescKeys=" + MyStringUtil.arryToString(novelDescKeys) + ", way=" + getIndexWay + ", itemKey=" + itemKey + '}';
    }

}
