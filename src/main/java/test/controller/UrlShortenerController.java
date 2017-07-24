package test.controller;

import org.springframework.web.bind.annotation.*;
import test.dto.RegisterUrlRequestDto;
import test.dto.RegisterUrlResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/url-shortener")
public class UrlShortenerController {

    @GetMapping(value = "/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        final String fullUrl = null;
        if (fullUrl != null) {
            response.sendRedirect(fullUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping(value = "/register")
    public RegisterUrlResponseDto registerUrl(@RequestBody RegisterUrlRequestDto urlData) {
        throw new UnsupportedOperationException();
    }
}
