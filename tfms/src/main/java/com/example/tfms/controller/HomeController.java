package com.example.tfms.controller;

import com.example.tfms.service.BankGuaranteeService;
import com.example.tfms.service.ComplianceService;
import com.example.tfms.service.LetterOfCreditService;
import com.example.tfms.service.RiskAssessmentService;
import com.example.tfms.service.TradeDocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final LetterOfCreditService letterOfCreditService;
    private final BankGuaranteeService bankGuaranteeService;
    private final TradeDocumentService tradeDocumentService;
    private final RiskAssessmentService riskAssessmentService;
    private final ComplianceService complianceService;

    public HomeController(LetterOfCreditService letterOfCreditService,
                         BankGuaranteeService bankGuaranteeService,
                         TradeDocumentService tradeDocumentService,
                         RiskAssessmentService riskAssessmentService,
                         ComplianceService complianceService) {
        this.letterOfCreditService = letterOfCreditService;
        this.bankGuaranteeService = bankGuaranteeService;
        this.tradeDocumentService = tradeDocumentService;
        this.riskAssessmentService = riskAssessmentService;
        this.complianceService = complianceService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        try {
            // Get counts for dashboard cards
            model.addAttribute("lcCount", letterOfCreditService.count());
            model.addAttribute("guaranteeCount", bankGuaranteeService.count());
            model.addAttribute("documentCount", tradeDocumentService.count());
            model.addAttribute("riskCount", riskAssessmentService.count());

            // Get recent data for dashboard
            model.addAttribute("recentLC", letterOfCreditService.findRecent(5));
            model.addAttribute("complianceAlerts", complianceService.findNonCompliant());

            model.addAttribute("title", "Dashboard");
        } catch (Exception e) {
            // Handle any service errors gracefully
            model.addAttribute("error", "Unable to load dashboard data. Please try again later.");
        }

        return "index";
    }
}