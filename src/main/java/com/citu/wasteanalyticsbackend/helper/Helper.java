package com.citu.wasteanalyticsbackend.helper;

import com.citu.wasteanalyticsbackend.entities.User;
import com.citu.wasteanalyticsbackend.entities.WasteEntry;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static WasteEntry mapToWasteEntry(Map<String, Object> data) {
        WasteEntry entry = new WasteEntry();
        entry.setId((String) data.get("id"));
        entry.setOffice((String) data.get("office"));
        entry.setWasteCategory((String) data.get("wasteCategory"));
        entry.setCollector((String) data.get("collector"));
        entry.setNote((String) data.get("note"));
        entry.setWeight(data.get("weight") instanceof Number ? ((Number) data.get("weight")).floatValue() : 0f);
        entry.setDate(data.get("date").toString());

        return entry;
    }

    public static User mapToUser(Map<String, Object> data) {
        User user = new User();
        user.setUsername((String) data.get("username"));
        user.setPassword((String) data.get("password"));
        user.setAdmin((boolean) data.get("admin"));

        return user;
    }

   /* public static Map<String, Object> wasteEntryToMap(WasteEntry entry) {
        Map<String, Object> map = new HashMap<>();

        if (entry.getOffice() != null) map.put("office", entry.getOffice());
        if (entry.getWasteCategory() != null) map.put("wasteCategory", entry.getWasteCategory());
        if (entry.getCollector() != null) map.put("collector", entry.getCollector());
        if (entry.getNote() != null) map.put("note", entry.getNote());
        if (entry.getWeight() != 0) map.put("weight", entry.getWeight());
        if (entry.getDate() != null) map.put("date", entry.getDate());


        return map;
    }*/
}
