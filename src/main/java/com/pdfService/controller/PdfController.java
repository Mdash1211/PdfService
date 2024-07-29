package com.pdfService.controller;

import com.pdfService.util.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private PdfService pdfService;
    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdf() throws IOException{
        ByteArrayInputStream byteArrayInputStream = pdfService.generatePdf();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline; filename=generated.pdf");
         return ResponseEntity
                 .ok()
                 .headers(httpHeaders)
                 .contentType(MediaType.APPLICATION_PDF)
                 .body(new InputStreamResource(byteArrayInputStream));
    }
}
