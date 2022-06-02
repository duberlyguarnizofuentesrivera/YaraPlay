package model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegistroAcceso {
    private long id;
    private LocalDateTime fechaLogin;
    private LocalDateTime fechaLogout;
}
