package com.RealState.Controlller;

import com.RealState.Entity.User;
import com.RealState.Repository.UserRepository;
import com.RealState.Service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pdf")
public class PdfController {
//http://localhost:8080/pdf/generate
    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdfReport() {
        User user = getUserFromDatabase();

        if (user != null) {
            byte[] pdfBytes = pdfGenerationService.generatePdf(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "user-report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .body(pdfBytes);
        } else {
            // Handle the case where the user is not found
            return ResponseEntity.notFound().build();
        }
    }

    private User getUserFromDatabase() {
        // Replace this with your actual logic to fetch user details from the database
        // For demonstration, let's assume you have a UserRepository
        Optional<User> optionalUser = userRepository.findById(1L); // Replace 1L with the actual user ID
        return optionalUser.orElse(null);
    }
}
