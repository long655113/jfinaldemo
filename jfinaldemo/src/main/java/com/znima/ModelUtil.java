/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima;

import com.jfinal.plugin.activerecord.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ModelUtil {

    public static List<Map<String, Object>> modelToMap(List list) {

        List<Map<String, Object>> results = new ArrayList();

        for (Object u : list) {
            Map<String, Object> map = modelToMap((Model) u);

            if (map == null) {
                return null;
            }

            results.add(map);
        }

        return results;
    }

    public static Map<String, Object> modelToMap(Model model) {
        if (model == null) {
            return null;
        }
        Set<Map.Entry<String, Object>> attrs = model._getAttrsEntrySet();
        Map<String, Object> map = new HashMap();
        for (Map.Entry<String, Object> m : attrs) {
            map.put(m.getKey(), m.getValue());
        }

        if (map.isEmpty()) {
            return null;
        }

        return map;
    }
}
