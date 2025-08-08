package com.example.tfms.service;

import com.example.tfms.model.entity.TradeDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TradeDocumentService {
    TradeDocument upload(TradeDocument meta, MultipartFile file) throws IOException;
    TradeDocument getById(Long documentId);
    TradeDocument update(Long documentId, TradeDocument updates);
    List<TradeDocument> listAll();
    byte[] downloadFile(Long documentId) throws IOException;
}