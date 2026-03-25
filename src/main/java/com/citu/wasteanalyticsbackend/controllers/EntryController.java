package com.citu.wasteanalyticsbackend.controllers;

import com.citu.wasteanalyticsbackend.entities.WasteEntry;
import com.citu.wasteanalyticsbackend.helper.Helper;
import com.citu.wasteanalyticsbackend.services.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.citu.wasteanalyticsbackend.helper.Helper.mapToWasteEntry;

@RestController
@RequestMapping("/api/v3")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("entries/add")
    public ResponseEntity<String> addEntry(@RequestBody WasteEntry entryData) throws Exception {
        String id = entryService.addEntry("entries", entryData);

        return ResponseEntity.status(HttpStatus.CREATED).body("Entry Logged with ID: " + id);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<WasteEntry>> getEntries() throws Exception {
        List<Map<String, Object>> entry = entryService.getAllEntry("entries");
        List<WasteEntry> entries = entry.stream().map(Helper::mapToWasteEntry).toList();
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<WasteEntry> getEntryById(@PathVariable String id) throws Exception {
        List<Map<String, Object>> rawEntry = entryService.getEntryById("entries", id);

        if(rawEntry == null || rawEntry.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        WasteEntry entry = mapToWasteEntry(rawEntry.getFirst());
        return ResponseEntity.ok(entry);
    }

    @DeleteMapping("entries/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable String id) throws Exception {
        boolean result = entryService.deleteEntry("entries", id);
        if (!result) {
            return ResponseEntity.status((HttpStatus.OK)).body("Entry with Id: \"" + id + "\" does not exist.");
        }
        return ResponseEntity.status((HttpStatus.OK)).body("Entry with Id: \"" + id + "\" successfuly deleted.");

    }

    @PatchMapping("entries/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable String id,@RequestBody Map<String, Object> entry) throws Exception {
        System.out.println(entry);
        boolean result = entryService.updateEntry("entries", id, entry);

        if (!result)
            return ResponseEntity.status((HttpStatus.OK)).body("Entry with id: \"" + id + "\" does not exist.");
        return ResponseEntity.status((HttpStatus.OK)).body("Entry with id: \"" + id + "\" successfuly updated.");
    }

}
