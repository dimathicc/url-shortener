package com.dimathicc.ens.urlshortener.service;

import com.dimathicc.ens.urlshortener.config.base62encoder.Base62EncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Base62Encoder {
    private final Base62EncoderConfig config;

    public List<String> encode(List<Long> numbers) {
        List<String> encodedStrings = new ArrayList<>();
        for (Long number : numbers) {
            encodedStrings.add(encodeBas62(number));
        }
        return encodedStrings;
    }

    private String encodeBas62(Long value) {
        if (value == 0) {
            return String.valueOf(config.getBASE_62_ALPHABET().charAt(0));
        }
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(config.getBASE_62_ALPHABET().charAt((int)(value % config.getENCODING_FACTOR())));
            value /= 62;
        }
        return sb.reverse().toString();
    }

    public List<String> encodeNumbers(List<Long> numbers) {
        return numbers.stream()
                .map(this::encodeNumber)
                .toList();
    }

    private String encodeNumber(Long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(config.getBASE_62_ALPHABET().charAt((int) (number % config.getBASE_62_ALPHABET().length())));
            number /= config.getBASE_62_ALPHABET().length();
        }
        return sb.toString();
    }
}
