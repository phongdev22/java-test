
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.EntryRight;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Relative;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RelativeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.EntryRightService;

@RestController
@RequestMapping("/api/entries")
public class ApiEntryRightController {

    @Autowired
    private EntryRightService parkingRightService;

    @Autowired
    private RelativeService relativeService;

    @PostMapping(path = "/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> createEntryRight(@RequestBody Map<String, String> params) {
        try {
            Relative relative = relativeService.getRelativeById(Integer.parseInt(params.get("relativeId")));

            if (relative == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Relative not found with ID: " + params.get("relativeId"));
            }

            EntryRight pr = new EntryRight();
            pr.setRelativeId(relative);

            parkingRightService.addOrUpdate(pr);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create entry right successfully!");
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @PutMapping(path = "/{pId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> updateEntryRight(@RequestBody Map<String, String> params,
            @PathVariable("pId") int pId) {
        try {
            EntryRight pr = parkingRightService.getEntryRightById(pId);

            if (pr == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Entry right not found with ID: " + pId);
            }

            pr.setStatus(params.get("status"));

            parkingRightService.addOrUpdate(pr);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Update entry right successfully!");
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
}
