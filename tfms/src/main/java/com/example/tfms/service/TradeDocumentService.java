package com.example.tfms.service;

import com.example.tfms.model.entity.TradeDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TradeDocumentService {
    TradeDocument upload(TradeDocument document, MultipartFile file);
    TradeDocument getById(Long documentId);
    List<TradeDocument> listAll();
    void delete(Long documentId);
    long count();
}