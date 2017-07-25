package test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountRegistrationResponseDto {
    private boolean success;
    private String description;
    private String password;
}
